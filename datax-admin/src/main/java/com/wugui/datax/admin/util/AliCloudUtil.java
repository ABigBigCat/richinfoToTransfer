package com.wugui.datax.admin.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.Bucket;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import com.wugui.datax.admin.entity.BucketEntry;

import java.util.ArrayList;
import java.util.List;

public class AliCloudUtil {

    //查询对象储存bucket
    public static List<BucketEntry> getBucketList(String secretId, String secretKey) {

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, secretId, secretKey);

        // 列举存储空间。
        List<com.aliyun.oss.model.Bucket> buckets = ossClient.listBuckets();

        ArrayList<BucketEntry> bucketList = new ArrayList<>();
        for (Bucket bucket : buckets) {
            bucketList.add(new BucketEntry(bucket.getName(),"http://" + bucket.getLocation() + ".aliyuncs.com")) ;
        }

        // 关闭OSSClient。
        ossClient.shutdown();

        return bucketList;
    }


    public static void main(String[] args) {

        List<BucketEntry> bucketList = AliCloudUtil.getBucketList("LTAI4GAx1vvNERxJRcHAwUgm", "cPG2q3qoIIwdJpVoOlCRSd7NN5WKGx");
        System.out.println(bucketList);
    }


}
