package com.wugui.datax.admin.entity;

import java.io.Serializable;

public class BucketEntry implements Serializable {

    private String bucket;

    private String endpoint;

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public BucketEntry(String bucket, String endpoint) {
        this.bucket = bucket;
        this.endpoint = endpoint;
    }

    @Override
    public String toString() {
        return "BucketEntry{" +
                "bucket='" + bucket + '\'' +
                ", endpoint='" + endpoint + '\'' +
                '}';
    }
}
