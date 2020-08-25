package com.wugui.datax.admin.util;

import com.jcraft.jsch.*;
import com.wugui.datax.admin.entity.SshMod;
import com.wugui.datax.rpc.remoting.provider.XxlRpcProviderFactory;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {

    public static Logger logger = LoggerFactory.getLogger(XxlRpcProviderFactory.class);

    /**
     *  生成user.json文件
     *
     *  {
     *  "SecretId":"",
     *  "SecretKey": "",
     *  "Region": "",
     *  "InstanceId": "",
     *  "DataDisks":[]
     *  }
     */
    public static void makeUserJsonFile(String secretId,String secretKey,String region,String instanceId,String path) {

        HashMap<String,Object> userJson = new HashMap();
        ArrayList dataDisks = new ArrayList();

        userJson.put("SecretId",secretId);
        userJson.put("SecretKey",secretKey);
        userJson.put("Region",region);
        userJson.put("InstanceId",instanceId);
        userJson.put("DataDisks",dataDisks);

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(path + "/user.json")) ;
            String str = new ObjectMapper().writeValueAsString(userJson);
            logger.info("生成user.json : " + str);
            bw.write(str);
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static boolean isExistDir(String path, ChannelSftp sftp){
        boolean  isExist=false;
        try {
            SftpATTRS sftpATTRS = sftp.lstat(path);
            isExist = true;
            return sftpATTRS.isDir();
        } catch (Exception e) {
            if (e.getMessage().toLowerCase().equals("No such file")) {
                isExist = false;
            }
        }
        return isExist;

    }

    /**
     * @param directory  上传ftp的目录
     * @param uploadFile 本地文件目录
     *
     */
    public static void upload(SshMod remote, String directory, String uploadFile) throws Exception {

        try {
            remote.getChannel().ls(directory);
            remote.getChannel().cd(directory);
            List<File> files = getFiles(remote,uploadFile, new ArrayList<File>());
            for (int i = 0; i < files.size(); i++) {
                File file = files.get(i);
                InputStream input = new BufferedInputStream(new FileInputStream(file));
                remote.getChannel().put(input, file.getName());
                try {
                    if (input != null) input.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(file.getName() + "关闭文件时.....异常!" + e.getMessage());
                }

            }
        }catch (Exception e) {
            logger.error("【子目录创建中】：",e);
            //创建子目录
            remote.getChannel().mkdir(directory);
        } finally {
            remote.getChannel().disconnect();
        }

    }


    //获取文件
    public static List<File> getFiles(SshMod remote, String realpath, List<File> files) {
        File realFile = new File(realpath);

        if (!realFile.isDirectory()) {
            File[] subfiles = realFile.listFiles(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    if (null == remote.getLast_push_date()) {
                        return true;
                    } else {
                        long modifyDate = file.lastModified();
                        return modifyDate > remote.getLast_push_date().getTime();
                    }
                }
            });
            for (File file : subfiles) {
                if (file.isDirectory()) {
                    getFiles(remote,file.getAbsolutePath(), files);
                } else {
                    files.add(file);
                }
                if (null == remote.getLast_push_date()) {
                    remote.setLast_push_date(new Date(file.lastModified()));
                } else {
                    long modifyDate = file.lastModified();
                    if (modifyDate > remote.getLast_push_date().getTime()) {
                        remote.setLast_push_date( new Date(modifyDate));
                    }
                }
            }
        }
        return files;
    }

    //生成目录
    public static void makePath(String host,Integer port,String username,String password,String path) {
        JSch jsch = new JSch();
        BufferedReader reader = null;
        ChannelExec channelExec = null;
        Session sshSession = null;
        StringBuilder sb = new StringBuilder();
        ArrayList<String> strings = new ArrayList<>();
        try {
            jsch.getSession(username, host, port);

            //根据用户名，密码，端口号获取session
            sshSession = jsch.getSession(username, host, port);
            sshSession.setPassword(password);
            //修改服务器/etc/ssh/sshd_config 中 GSSAPIAuthentication的值yes为no，解决用户不能远程登录
            sshSession.setConfig("userauth.gssapi-with-mic", "no");

            //为session对象设置properties,第一次访问服务器时不用输入yes
            sshSession.setConfig("StrictHostKeyChecking", "no");
            sshSession.connect();
            //获取sftp通道
            ChannelSftp channel = (ChannelSftp)sshSession.openChannel("sftp");
            channel.connect();
            // logger.info("连接ftp成功!" + sshSession);

            channelExec = (ChannelExec) sshSession.openChannel("exec");
            channelExec.setCommand("mkdir -p" + path);
            channelExec.setInputStream(null);
            channelExec.setErrStream(System.err);

            channelExec.connect();

        }
        catch (JSchException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(reader != null) {
                    reader.close();
                }
                if(channelExec != null) {
                    channelExec.disconnect();
                }
                if(sshSession != null) {
                    sshSession.disconnect();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }



    public static void makeContainerfile() {

        BufferedReader reader = null;
        BufferedWriter writer = null;
        String buf = null;

        try {
            FileWriter fw = new FileWriter("H:\\local_job_res.cfg");

            InputStream in = FileUtils.class.getResourceAsStream("/local_job.cfg");
            reader = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
            writer = new BufferedWriter(fw);
            StringBuffer sbf=new StringBuffer();
            while ((buf = reader.readLine()) != null) {
                sbf.append(buf + "\r\n");
            }

            String newString=sbf.toString().replace("srcAccessKeyValue", "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
            writer.write(newString);
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        FileUtils.makeContainerfile();
    }


}
