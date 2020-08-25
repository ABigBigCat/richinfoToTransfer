package com.wugui.datax.admin.dataobject;

public class ModelDO {

    private Integer taskid;

    private Integer modelid;

    private String name;

    private Double rate;

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    public Integer getModelid() {
        return modelid;
    }

    public void setModelid(Integer modelid) {
        this.modelid = modelid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "ModelDO{" +
                "taskid=" + taskid +
                ", modelid=" + modelid +
                ", name='" + name + '\'' +
                ", rate=" + rate +
                '}';
    }
}
