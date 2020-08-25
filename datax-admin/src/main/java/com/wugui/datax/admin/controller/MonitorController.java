package com.wugui.datax.admin.controller;

import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.datamapper.MonitorDOMapper;
import com.wugui.datax.admin.dataobject.MigrateThreadDO;
import com.wugui.datax.admin.dataobject.ModelDO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LXM on 2020-07-22 09:45
 */
@RestController
@RequestMapping("/api/monitortask")
@Api(tags = "监控管理-任务并发，速率接口")
public class MonitorController {

    @Autowired
    private MonitorDOMapper monitorDOMapper;

    @GetMapping("/list")
    @ApiOperation("并发任务饼状图")
    public ReturnT<List<MigrateThreadDO>> list() {

        MigrateThreadDO migrateThreadDO1 = new MigrateThreadDO();
        MigrateThreadDO migrateThreadDO2 = new MigrateThreadDO();

        List<ModelDO> modelDOS1 = monitorDOMapper.listModelDOByModelId(0);
        int tasks1 = modelDOS1.size();

        migrateThreadDO1.setModel(modelDOS1);
        migrateThreadDO1.setTasks(tasks1);
        migrateThreadDO1.setLabel("主机迁移");
        migrateThreadDO1.setMigrateid(0);

        List<ModelDO> modelDOS2 = monitorDOMapper.listModelDOByModelId(1);
        int task2 = modelDOS2.size();

        migrateThreadDO2.setModel(modelDOS2);
        migrateThreadDO2.setTasks(task2);
        migrateThreadDO2.setLabel("对象存储迁移");
        migrateThreadDO2.setMigrateid(1);

        ArrayList<MigrateThreadDO> migrateThreadDOS = new ArrayList<>();
        migrateThreadDOS.add(migrateThreadDO1);
        migrateThreadDOS.add(migrateThreadDO2);

        return new ReturnT<>(migrateThreadDOS);
    }

}
