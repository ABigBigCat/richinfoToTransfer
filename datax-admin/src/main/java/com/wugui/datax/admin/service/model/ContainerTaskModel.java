package com.wugui.datax.admin.service.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class ContainerTaskModel implements Serializable {

    private Integer id;

    @ApiModelProperty("任务名称")
    private String taskName;

    @ApiModelProperty("源地址id")
    private Integer sourceAddressId;

    @ApiModelProperty("目标地址id")
    private Integer targetAddressId;

    @ApiModelProperty("迁移状态 0-未启动 1-失败 2-运行中 3-成功 4-停止")
    private Integer status;

    @ApiModelProperty("迁移方式 0-全量 1-增量")
    private Integer method;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "ContainerTaskModel{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", sourceAddressId=" + sourceAddressId +
                ", targetAddressId=" + targetAddressId +
                ", status=" + status +
                ", method=" + method +
                '}';
    }
}
