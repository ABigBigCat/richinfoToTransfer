package com.wugui.datax.admin.dataobject;

import java.util.Date;

public class ContainerTaskDO {

    private Integer id;

    private String taskName;

    private Integer sourceAddressId;

    private String sourceAddress;

    private Integer targetAddressId;

    private String targetAddress;

    private Integer status;

    private Integer method;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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

    public Integer getSourceAddressId() {
        return sourceAddressId;
    }

    public void setSourceAddressId(Integer sourceAddressId) {
        this.sourceAddressId = sourceAddressId;
    }

    public Integer getTargetAddressId() {
        return targetAddressId;
    }

    public void setTargetAddressId(Integer targetAddressId) {
        this.targetAddressId = targetAddressId;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String getTargetAddress() {
        return targetAddress;
    }

    public void setTargetAddress(String targetAddress) {
        this.targetAddress = targetAddress;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "ContainerTaskDO{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", sourceAddressId=" + sourceAddressId +
                ", sourceAddress='" + sourceAddress + '\'' +
                ", targetAddressId=" + targetAddressId +
                ", targetAddress='" + targetAddress + '\'' +
                ", status=" + status +
                ", method=" + method +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
