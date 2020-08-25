package com.wugui.datax.admin.service.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class ContainerModel implements Serializable {

    private Integer id;

    @ApiModelProperty("数据名称")
    private String dataName;

    @ApiModelProperty("数据类型 0-腾讯云 1-阿里云")
    private Integer dataType;

    @ApiModelProperty("accesskeyId")
    private String accesskeyId;

    @ApiModelProperty("accesskeySecret")
    private String accesskeySecret;

    @ApiModelProperty("bucket")
    private String bucket;

    @ApiModelProperty("endpoint")
    private String endpoint;

    @ApiModelProperty("迁移路径")
    private String path;

    public Integer getId() {
        return id;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ContainerModel{" +
                "id=" + id +
                ", dataName='" + dataName + '\'' +
                ", dataType=" + dataType +
                ", accesskeyId='" + accesskeyId + '\'' +
                ", accesskeySecret='" + accesskeySecret + '\'' +
                ", bucket='" + bucket + '\'' +
                ", endpoint='" + endpoint + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
