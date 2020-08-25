package com.wugui.datax.admin.dataobject;

import java.util.Date;

public class ContainerDO {

    private Integer id;

    private String dataName;

    //0 腾讯云 1 阿里云
    private Integer dataType;

    private String accesskeyId;

    private String accesskeySecret;

    private String bucket;

    private String endpoint;

    private String path;

    //0 正常 1 异常
    private Integer status;

    private Date createTime;

    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public String getAccesskeyId() {
        return accesskeyId;
    }

    public void setAccesskeyId(String accesskeyId) {
        this.accesskeyId = accesskeyId;
    }

    public String getAccesskeySecret() {
        return accesskeySecret;
    }

    public void setAccesskeySecret(String accesskeySecret) {
        this.accesskeySecret = accesskeySecret;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ContainerDO{" +
                "id=" + id +
                ", dataName='" + dataName + '\'' +
                ", dataType=" + dataType +
                ", accesskeyId='" + accesskeyId + '\'' +
                ", accesskeySecret='" + accesskeySecret + '\'' +
                ", bucket='" + bucket + '\'' +
                ", endpoint='" + endpoint + '\'' +
                ", path='" + path + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
