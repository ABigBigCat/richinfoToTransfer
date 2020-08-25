package com.wugui.datax.admin.controller;

import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.datamapper.ContainerDOMapper;
import com.wugui.datax.admin.datamapper.ContainerTaskDOMapper;
import com.wugui.datax.admin.datamapper.MonitorDOMapper;
import com.wugui.datax.admin.dataobject.ContainerDO;
import com.wugui.datax.admin.dataobject.ContainerTaskDO;
import com.wugui.datax.admin.dataobject.ModelDO;
import com.wugui.datax.admin.service.ContainerTaskService;
import com.wugui.datax.admin.service.model.ContainerTaskModel;
import com.wugui.datax.admin.util.FileUtils;
import com.wugui.datax.admin.util.Go2TencentCloudUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LXM on 2020-07-22 09:45
 */
@RestController
@RequestMapping("/api/containertask")
@Api(tags = "对象存储迁移-对象存储任务接口")
public class ContainerTaskController {

    @Autowired
    private ContainerTaskService containerTaskService;

    @Autowired
    private ContainerTaskDOMapper containerTaskDOMapper;

    @Autowired
    private ContainerDOMapper containerDOMapper;

    @Autowired
    private MonitorDOMapper monitorDOMapper;

    @PostMapping("/add")
    @ApiOperation("添加任务")
    public ReturnT addContainer(@RequestBody ContainerTaskModel ContainerTaskModel){
        ContainerTaskModel ContainerTaskModel1 = containerTaskService.addContainerTask(ContainerTaskModel);
        String taskName = ContainerTaskModel.getTaskName();
        ModelDO modelDO = new ModelDO();
        modelDO.setModelid(1);
        modelDO.setName(taskName);
        double v = (Math.random() * 5 + 1) /100;
        modelDO.setRate(1.5 + v);
        monitorDOMapper.insertModelDO(modelDO);
        return new ReturnT(ContainerTaskModel1);
    }


    @GetMapping("/pageList")
    @ApiOperation("任务列表")
    public ReturnT<Map<String, Object>> pageList(@RequestParam(required = false, defaultValue = "1") int current,
                                                 @RequestParam(required = false, defaultValue = "10") int size,
                                                 String username) {

        List<ContainerTaskDO> list = containerTaskService.listContainerTask((current - 1) * size, size, username);
        int recordsTotal = containerTaskDOMapper.pageListCount((current - 1) * size, size, username);

        // package result
        Map<String, Object> maps = new HashMap<>();
        maps.put("recordsTotal", recordsTotal);        // 总记录数
        maps.put("recordsFiltered", recordsTotal);    // 过滤后的总记录数
        maps.put("data", list);                    // 分页列表
        maps.put("page",(recordsTotal + size - 1) / size);//分页数
        return new ReturnT<>(maps);//
    }


    @PostMapping("/del")
    @ApiOperation("删除任务")
    public ReturnT delContainer(@RequestParam(name = "id")Integer id){
        Boolean result = containerTaskService.deleteContainerTask(id);
        return new ReturnT(result);
    }

    @PostMapping("/update")
    @ApiOperation("更新任务")
    public ReturnT updateTask(@RequestBody ContainerTaskModel ContainerTaskModel){
        Boolean result = containerTaskService.updateContainerTask(ContainerTaskModel);
        return new ReturnT(result);
    }

    @PostMapping("/startContainerTask")
    @ApiOperation("启动对象存储迁移")
    public ReturnT<String> startContainerTask(@RequestBody ContainerTaskModel ContainerTaskModel) throws Exception {
        //FileUtils.makeContainerfile();
        ContainerTaskModel.setStatus(2);
        containerTaskService.updateContainerTask(ContainerTaskModel);
        Integer sourceAddressId = ContainerTaskModel.getSourceAddressId();
        Integer targetAddressId = ContainerTaskModel.getTargetAddressId();
        ContainerDO containerDO1 = containerDOMapper.selectByPrimaryKey(sourceAddressId);
        ContainerDO containerDO2 = containerDOMapper.selectByPrimaryKey(targetAddressId);
        Integer dataType1 = containerDO1.getDataType();
        Integer dataType2 = containerDO2.getDataType();
        //腾讯云 -> 阿里云
        if(dataType1 == 0 && dataType2 == 1) {
            Go2TencentCloudUtil.startContainerTask2AliYun();
        }
        //阿里云 -> 腾讯云
        if(dataType1 == 1 && dataType2 == 0) {
            Go2TencentCloudUtil.startContainerTask2TencentCloud();
        }
        Go2TencentCloudUtil.updateContainerTaskStatuss(ContainerTaskModel.getId());
        return  ReturnT.SUCCESS;

    }

    @PostMapping("/stopContainerTask")
    @ApiOperation("停止对象存储迁移")
    public ReturnT<String> stopContainerTask(@RequestBody ContainerTaskModel ContainerTaskModel) throws Exception {
        ContainerTaskModel.setStatus(4);
        containerTaskService.updateContainerTask(ContainerTaskModel);
        Integer sourceAddressId = ContainerTaskModel.getSourceAddressId();
        Integer targetAddressId = ContainerTaskModel.getTargetAddressId();
        ContainerDO containerDO1 = containerDOMapper.selectByPrimaryKey(sourceAddressId);
        ContainerDO containerDO2 = containerDOMapper.selectByPrimaryKey(targetAddressId);
        Go2TencentCloudUtil.stopContainerTask(containerDO1,containerDO2);
        return  ReturnT.SUCCESS;

    }


    @GetMapping("/tailLogs")
    @ApiOperation("查看对象存储迁移时运行日志")
    public ReturnT<ArrayList<String>> tailLogs(@RequestParam(name = "id")Integer id) {
        ArrayList<String> log = Go2TencentCloudUtil.tailContainerTencentLog("192.168.22.242", 22, "root", "richinfo@139", 500);
        return  new ReturnT<>(log);
    }

}
