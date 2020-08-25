package com.wugui.datax.admin.test;

import cn.hutool.extra.ftp.Ftp;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import com.jcraft.jsch.*;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class test {

    //打印log日志
    private static final Log logger = LogFactory.getLog(Ftp.class);

    private static Date last_push_date = null;

    private Session sshSession;

    private ChannelSftp channel;

    private static ThreadLocal<Ftp> sftpLocal = new ThreadLocal<Ftp>();

    public  test(String host, int port, String username, String password) throws Exception {
        JSch jsch = new JSch();
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
        channel = (ChannelSftp)sshSession.openChannel("sftp");
        channel.connect();
       // logger.info("连接ftp成功!" + sshSession);
    }

    /**
     * @param directory  上传ftp的目录
     * @param uploadFile 本地文件目录
     *
     */
    public void upload(String directory, String uploadFile) throws Exception {
        try {
                channel.ls(directory);
                channel.cd(directory);
            List<File> files = getFiles(uploadFile, new ArrayList<File>());
            for (int i = 0; i < files.size(); i++) {
                File file = files.get(i);
                InputStream input = new BufferedInputStream(new FileInputStream(file));
                channel.put(input, file.getName());
                try {
                    if (input != null) input.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(file.getName() + "关闭文件时.....异常!" + e.getMessage());
                }
//                if (file.exists()) {
//                    boolean b = file.delete();
//                    //logger.info(file.getName() + "文件上传完毕!删除标识:" + b);
//                }
            }
        }catch (Exception e) {
            logger.error("【子目录创建中】：",e);
            //创建子目录
            channel.mkdir(directory);
        } finally {
            channel.disconnect();
        }

    }

    //获取文件
    public List<File> getFiles(String realpath, List<File> files) {
        File realFile = new File(realpath);
        if (realFile.isDirectory()) {
            File[] subfiles = realFile.listFiles(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    if (null == last_push_date ) {
                        return true;
                    } else {
                        long modifyDate = file.lastModified();
                        return modifyDate > last_push_date.getTime();
                    }
                }
            });
            for (File file : subfiles) {
                if (file.isDirectory()) {
                    getFiles(file.getAbsolutePath(), files);
                } else {
                    files.add(file);
                }
                if (null == last_push_date) {
                    last_push_date = new Date(file.lastModified());
                } else {
                    long modifyDate = file.lastModified();
                    if (modifyDate > last_push_date.getTime()) {
                        last_push_date = new Date(modifyDate);
                    }
                }
            }
        }
        return files;
    }



    public static void main(String[] args) {

        // 1 初始化用户身份信息（secretId, secretKey）。
        String secretId = "AKIDSfbJp6v23nicGgHolSea1BMPHuRnC40i";
        String secretKey = "c1yLRG5d1EGCWaOAgwwiLYTArLbbSBdC";
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region("");
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
//
//        // 指定要上传的文件
//        File localFile = new File("F:\\test.txt");
//        // 指定要上传到的存储桶
//        String bucketName = "rich-bucket-1254170439";
//        // 指定要上传到 COS 上对象键
//        String key = "F/test.txt";
//        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
//        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);


        //腾讯云迁移
        List<Bucket> buckets = cosClient.listBuckets();
        for (Bucket bucketElement : buckets) {
            String bucketName = bucketElement.getName();
            String bucketLocation = bucketElement.getLocation();
            String displayName = bucketElement.getOwner().getDisplayName();
            System.out.println(bucketName + "   " + bucketLocation + "   " + displayName);
        }



//        // 同地域同账号拷贝
//      // 源 Bucket, Bucket的命名格式为 BucketName-APPID，此处填写的存储桶名称必须为此格式
//        String srcBucketName = "rich-bucket-1254170439";
//     // 要拷贝的源文件
//        String srcKey = "F/test.txt";
//     // 目标存储桶, Bucket的命名格式为 BucketName-APPID，此处填写的存储桶名称必须为此格式
//        String destBucketName = "rich-dest-bucket-1254170439";
//     // 要拷贝的目的文件
//        String destKey = "D/test.txt";
//        CopyObjectRequest copyObjectRequest = new CopyObjectRequest(srcBucketName, srcKey, destBucketName, destKey);
//        CopyObjectResult copyObjectResult = cosClient.copyObject(copyObjectRequest);

     // 跨账号跨地域拷贝（需要拥有对源文件的读取权限以及目的文件的写入权限）
      //  String srcBucketNameOfDiffAppid = "bucket-own-by-others-1251668577";
      //  Region srcBucketRegion = new Region("ap-shanghai");
     //   copyObjectRequest = new CopyObjectRequest(srcBucketRegion, srcBucketNameOfDiffAppid, srcKey, destBucketName, destKey);
      //  copyObjectResult = cosClient.copyObject(copyObjectRequest);




//        // Endpoint以杭州为例，其它Region请按实际情况填写。
//        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
//        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
//        String accessKeyId = "LTAI4GAx1vvNERxJRcHAwUgm";
//        String accessKeySecret = "cPG2q3qoIIwdJpVoOlCRSd7NN5WKGx";
//
//        // 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//
//        // 列举存储空间。
//        List<com.aliyun.oss.model.Bucket> buckets = ossClient.listBuckets();
//        for (Bucket bucket : buckets) {
//            System.out.println(" - " + bucket.getName() + " - " + bucket.getLocation());
//        }
//
//        // 关闭OSSClient。
//        ossClient.shutdown();



    }
}
