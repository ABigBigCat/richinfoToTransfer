package com.wugui.datax.admin.dataobject;

import java.util.List;

public class MigrateThreadDO {

    private Integer migrateid;

    private String label;

    private Integer tasks;

    private List<ModelDO> model;

    public Integer getMigrateid() {
        return migrateid;
    }

    public void setMigrateid(Integer migrateid) {
        this.migrateid = migrateid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getTasks() {
        return tasks;
    }

    public void setTasks(Integer tasks) {
        this.tasks = tasks;
    }

    public List<ModelDO> getModel() {
        return model;
    }

    public void setModel(List<ModelDO> model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "MigrateThreadDO{" +
                "migrateid=" + migrateid +
                ", label='" + label + '\'' +
                ", tasks=" + tasks +
                ", model=" + model +
                '}';
    }
}
