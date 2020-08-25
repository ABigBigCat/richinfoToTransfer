package com.wugui.datax.admin.util;

import com.jcraft.jsch.*;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.Bucket;
import com.qcloud.cos.region.Region;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.cvm.v20170312.CvmClient;
import com.tencentcloudapi.cvm.v20170312.models.*;
import com.wugui.datax.admin.dataobject.ContainerDO;
import com.wugui.datax.admin.entity.BucketEntry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Go2TencentCloudUtil {

    /**
     * 启动go2tencent
     * @param host ip地址
     * @param port 端口号
     * @param username  用户名
     * @param password  密码
     */
    public static void startGoToTencent(String host,Integer port,String username,String password){

            JSch jsch = new JSch();
            BufferedReader reader = null;
            ChannelExec channelExec = null;
            Session sshSession = null;

            try {
               // FileUtils.makeUserJsonFile();

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
                System.out.println("连接ftp成功!" + sshSession);

                channelExec = (ChannelExec) sshSession.openChannel("exec");
                channelExec.setCommand("nohup /var/www/go2tencentcloud/go2tencentcloud_x64 --log-file=my.log --log-level=3 > err.file 2>&1 &");

                channelExec.setInputStream(null);
                channelExec.setErrStream(System.err);

                channelExec.connect();

//                InputStream in = null;
//
//                in = channelExec.getInputStream();
//
//                reader = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
//                String buf = null;
//                while ((buf = reader.readLine()) != null) {
//                    System.out.println(buf);
//                }


            }
            catch (JSchException e) {
                e.printStackTrace();
            }

    }


    public static void stopGoToTencent(String host,Integer port,String username,String password){

        JSch jsch = new JSch();
        BufferedReader reader = null;
        ChannelExec channelExec = null;
        Session sshSession = null;

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
            System.out.println("连接ftp成功!" + sshSession);

            channelExec = (ChannelExec) sshSession.openChannel("exec");
            channelExec.setCommand("nohup /var/www/go2tencentcloud/killgo2tencentcloud.sh > stop.file 2>&1 &");
            channelExec.setInputStream(null);
            channelExec.setErrStream(System.err);

            channelExec.connect();

        }
        catch (JSchException e) {
            e.printStackTrace();
        }

    }

    //查看Go2TencentCloud的运行日志
    public static ArrayList<String> tailLog(String host,Integer port,String username,String password,int rows) {
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
            channelExec.setCommand("tail -n "+rows+" /var/www/go2tencentcloud/rsync.log");
            channelExec.setInputStream(null);
            channelExec.setErrStream(System.err);

            channelExec.connect();

            InputStream in = null;

            in = channelExec.getInputStream();

            reader = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
            String buf = null;

            while ((buf = reader.readLine()) != null) {
                strings.add("【 腾讯云在线迁移 " + buf + " 】");
            }

        }
        catch (JSchException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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

        return strings;
    }


    //查询地域列表
    public static List<String> listDescribeRegions(String secretId, String secretKey) {
        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey
            Credential cred = new Credential(secretId, secretKey);

            // 实例化要请求产品(以cvm为例)的client对象
            CvmClient client = new CvmClient(cred, "");

            // 实例化一个请求对象
            DescribeRegionsRequest req = new DescribeRegionsRequest();

            // 通过client对象调用想要访问的接口，需要传入请求对象
            DescribeRegionsResponse resp = client.DescribeRegions(req);

            // 输出json格式的字符串回包
            //System.out.println(DescribeRegionsRequest.toJsonString(resp));

            ArrayList<String> instanceList = new ArrayList();

            for(RegionInfo r : resp.getRegionSet()) {
                instanceList.add( r.getRegion());

            }
            return instanceList;

        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        } finally {
            return null;
        }
    }

    public static List<String> getInstanceIdList(String secretId,String secretKey,String region){
        ArrayList<String> instanceList = new ArrayList();

        try {
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey
            Credential cred = new Credential(secretId, secretKey);

            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setReqMethod("GET"); // get请求(默认为post请求)
            httpProfile.setConnTimeout(30); // 请求连接超时时间，单位为秒(默认60秒)
            httpProfile.setEndpoint("cvm.tencentcloudapi.com"); // 指定接入地域域名(默认就近接入)

            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setSignMethod("HmacSHA256"); // 指定签名算法(默认为HmacSHA256)
            clientProfile.setHttpProfile(httpProfile);
            //clientProfile.setDebug(true);
            // 实例化要请求产品(以cvm为例)的client对象,clientProfile是可选的
            CvmClient client = new CvmClient(cred, region, clientProfile);

            // 实例化一个cvm实例信息查询请求对象,每个接口都会对应一个request对象。
            DescribeInstancesRequest req = new DescribeInstancesRequest();

            DescribeInstancesResponse resp = client.DescribeInstances(req);

            System.out.println(DescribeInstancesResponse.toJsonString(resp));

            for(Instance s : resp.getInstanceSet()) {
                instanceList.add(s.getInstanceId());
            }

        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

        return instanceList;
    }


    public static boolean isExistAccount(String accesskeyId, String accesskeySecret){
        boolean  isExist=false;
        try {
            isExist = true;
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey
            Credential cred = new Credential(accesskeyId, accesskeySecret);

            // 实例化要请求产品(以cvm为例)的client对象
            CvmClient client = new CvmClient(cred, "");

            // 实例化一个请求对象
            DescribeRegionsRequest req = new DescribeRegionsRequest();

            // 通过client对象调用想要访问的接口，需要传入请求对象
            DescribeRegionsResponse resp = client.DescribeRegions(req);
        } catch (Exception e) {
            if (e.getMessage().contains("AuthFailure")) {
                isExist = false;
            }
        }
        return isExist;

    }


    //查询对象储存bucket
    public static List<BucketEntry> getBucketList(String secretId, String secretKey) {

        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的区域, COS 地域的简称请参照 https://rich-bucket-1254170439.cos.ap-guangzhou.myqcloud.com
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region("");
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);

        List<Bucket> buckets = cosClient.listBuckets();
        ArrayList<BucketEntry> bucketList = new ArrayList<>();
        for (Bucket bucketElement : buckets) {
            bucketList.add(new BucketEntry(bucketElement.getName(),"https://" + bucketElement.getName() + ".cos." + bucketElement.getLocation() + ".myqcloud.com"));
        }
        return bucketList;
    }


    //启动对象存储任务(阿里云->腾讯云)
    public static void startContainerTask2TencentCloud() {

            JSch jsch = new JSch();
            BufferedReader reader = null;
            ChannelExec channelExec = null;
            Session sshSession = null;

            try {
                //根据用户名，密码，端口号获取session
                sshSession = jsch.getSession("root", "192.168.22.242", 22);
                sshSession.setPassword("richinfo@139");
                //修改服务器/etc/ssh/sshd_config 中 GSSAPIAuthentication的值yes为no，解决用户不能远程登录
                sshSession.setConfig("userauth.gssapi-with-mic", "no");

                //为session对象设置properties,第一次访问服务器时不用输入yes
                sshSession.setConfig("StrictHostKeyChecking", "no");
                sshSession.connect();
                //获取sftp通道
                ChannelSftp channel = (ChannelSftp)sshSession.openChannel("sftp");
                channel.connect();
                System.out.println("连接ftp成功!" + sshSession);

                channelExec = (ChannelExec) sshSession.openChannel("exec");

                String command = "nohup /data/cos_migrate_tool_v5-master/start_migrate.sh > err.file 2>&1";

                channelExec.setCommand("source /etc/profile && source ~/.bash_profile && source ~/.bashrc && " + command);

                channelExec.setInputStream(null);
                channelExec.setErrStream(System.err);

                channelExec.connect();

            }
            catch (JSchException e) {
                e.printStackTrace();
            }

    }


    //启动对象存储任务(腾讯云->阿里云)
    public static void startContainerTask2AliYun() {

        JSch jsch = new JSch();
        BufferedReader reader = null;
        ChannelExec channelExec = null;
        Session sshSession = null;

        try {
            //根据用户名，密码，端口号获取session
            sshSession = jsch.getSession("root", "192.168.22.242", 22);
            sshSession.setPassword("richinfo@139");
            //修改服务器/etc/ssh/sshd_config 中 GSSAPIAuthentication的值yes为no，解决用户不能远程登录
            sshSession.setConfig("userauth.gssapi-with-mic", "no");

            //为session对象设置properties,第一次访问服务器时不用输入yes
            sshSession.setConfig("StrictHostKeyChecking", "no");
            sshSession.connect();
            //获取sftp通道
            ChannelSftp channel = (ChannelSftp)sshSession.openChannel("sftp");
            channel.connect();
            System.out.println("连接ftp成功!" + sshSession);

            channelExec = (ChannelExec) sshSession.openChannel("exec");

            String command = "nohup /data/ossimport-2.3.4/import.sh > err.file 2>&1";

            channelExec.setCommand("source /etc/profile && source ~/.bash_profile && source ~/.bashrc && " + command);

            channelExec.setInputStream(null);
            channelExec.setErrStream(System.err);

            channelExec.connect();

        }
        catch (JSchException e) {
            e.printStackTrace();
        }

    }


    public static void updateContainerTaskStatuss(Integer id) {
        JSch jsch = new JSch();
        BufferedReader reader = null;
        ChannelExec channelExec = null;
        Session sshSession = null;

        try {
            //根据用户名，密码，端口号获取session
            sshSession = jsch.getSession("root", "192.168.22.242", 22);
            sshSession.setPassword("richinfo@139");
            //修改服务器/etc/ssh/sshd_config 中 GSSAPIAuthentication的值yes为no，解决用户不能远程登录
            sshSession.setConfig("userauth.gssapi-with-mic", "no");

            //为session对象设置properties,第一次访问服务器时不用输入yes
            sshSession.setConfig("StrictHostKeyChecking", "no");
            sshSession.connect();
            //获取sftp通道
            ChannelSftp channel = (ChannelSftp)sshSession.openChannel("sftp");
            channel.connect();
            System.out.println("连接ftp成功!" + sshSession);

            channelExec = (ChannelExec) sshSession.openChannel("exec");

            String command = "nohup sh /data/update.sh " + id + " &";

            channelExec.setCommand("source /etc/profile && source ~/.bash_profile && source ~/.bashrc && " + command);

            channelExec.setInputStream(null);
            channelExec.setErrStream(System.err);

            channelExec.connect();

        }
        catch (JSchException e) {
            e.printStackTrace();
        }
    }



    public static void updateSystemTaskStatus(Integer id) {
        JSch jsch = new JSch();
        BufferedReader reader = null;
        ChannelExec channelExec = null;
        Session sshSession = null;

        try {
            //根据用户名，密码，端口号获取session
            sshSession = jsch.getSession("root", "192.168.22.242", 22);
            sshSession.setPassword("richinfo@139");
            //修改服务器/etc/ssh/sshd_config 中 GSSAPIAuthentication的值yes为no，解决用户不能远程登录
            sshSession.setConfig("userauth.gssapi-with-mic", "no");

            //为session对象设置properties,第一次访问服务器时不用输入yes
            sshSession.setConfig("StrictHostKeyChecking", "no");
            sshSession.connect();
            //获取sftp通道
            ChannelSftp channel = (ChannelSftp)sshSession.openChannel("sftp");
            channel.connect();
            System.out.println("连接ftp成功!" + sshSession);

            channelExec = (ChannelExec) sshSession.openChannel("exec");

            String command = "nohup sh /data/update_systemtask.sh " + id + " &";

            channelExec.setCommand("source /etc/profile && source ~/.bash_profile && source ~/.bashrc && " + command);

            channelExec.setInputStream(null);
            channelExec.setErrStream(System.err);

            channelExec.connect();

        }
        catch (JSchException e) {
            e.printStackTrace();
        }
    }


    public static void insertMysql() {
        JSch jsch = new JSch();
        BufferedReader reader = null;
        ChannelExec channelExec = null;
        Session sshSession = null;

        try {
            //根据用户名，密码，端口号获取session
            sshSession = jsch.getSession("root", "192.168.22.242", 22);
            sshSession.setPassword("richinfo@139");
            //修改服务器/etc/ssh/sshd_config 中 GSSAPIAuthentication的值yes为no，解决用户不能远程登录
            sshSession.setConfig("userauth.gssapi-with-mic", "no");

            //为session对象设置properties,第一次访问服务器时不用输入yes
            sshSession.setConfig("StrictHostKeyChecking", "no");
            sshSession.connect();
            //获取sftp通道
            ChannelSftp channel = (ChannelSftp)sshSession.openChannel("sftp");
            channel.connect();
            System.out.println("连接ftp成功!" + sshSession);

            channelExec = (ChannelExec) sshSession.openChannel("exec");

            String command = "nohup sh /data/insert_mysql.sh &";

            channelExec.setCommand("source /etc/profile && source ~/.bash_profile && source ~/.bashrc && " + command);

            channelExec.setInputStream(null);
            channelExec.setErrStream(System.err);

            channelExec.connect();

        }
        catch (JSchException e) {
            e.printStackTrace();
        }
    }



    //停止对象存储任务
    public static void stopContainerTask(ContainerDO sourceBucketName,ContainerDO targetBucketName) {

    }


    //
    public static ArrayList<String> tailContainerTencentLog(String host,Integer port,String username,String password,int rows) {
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
            channelExec.setCommand("tail -n "+rows+" /data/cos_migrate_tool_v5-master/log/info.log");
            channelExec.setInputStream(null);
            channelExec.setErrStream(System.err);

            channelExec.connect();

            InputStream in = null;

            in = channelExec.getInputStream();

            reader = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
            String buf = null;

            strings.add("【                   对象存储迁移 START                  】");
            while ((buf = reader.readLine()) != null) {
                strings.add(buf);
            }
            strings.add("【                   对象存储迁移 END                    】");

        }
        catch (JSchException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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

        return strings;
    }

}
