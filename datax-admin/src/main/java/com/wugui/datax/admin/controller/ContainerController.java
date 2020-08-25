package com.wugui.datax.admin.controller;

import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.datamapper.ContainerDOMapper;
import com.wugui.datax.admin.dataobject.ContainerDO;
import com.wugui.datax.admin.entity.BucketEntry;
import com.wugui.datax.admin.service.ContainerService;
import com.wugui.datax.admin.service.model.ContainerModel;
import com.wugui.datax.admin.util.AliCloudUtil;
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
@RequestMapping("/api/container")
@Api(tags = "对象存储迁移-数据地址接口")
public class ContainerController {

    @Autowired
    private ContainerService containerService;

    @Autowired
    private ContainerDOMapper containerDOMapper;

    @PostMapping("/add")
    @ApiOperation("添加数据地址")
    public ReturnT addContainer(@RequestBody ContainerModel containerModel){

        ContainerModel containerModel1 = containerService.addContainer(containerModel);

        return new ReturnT(containerModel1);

    }


    @GetMapping("/pageList")
    @ApiOperation("数据地址列表分页")
    public ReturnT<Map<String, Object>> pageList(@RequestParam(required = false, defaultValue = "1") int current,
                                                 @RequestParam(required = false, defaultValue = "10") int size,
                                                 String username) {

        List<ContainerDO> list = containerService.listContainer((current - 1) * size, size, username);
        int recordsTotal = containerDOMapper.pageListCount((current - 1) * size, size, username);

        // package result
        Map<String, Object> maps = new HashMap<>();
        maps.put("recordsTotal", recordsTotal);        // 总记录数
        maps.put("recordsFiltered", recordsTotal);    // 过滤后的总记录数
        maps.put("data", list);                    // 分页列表
        maps.put("page",(recordsTotal + size - 1) / size);//分页数
        return new ReturnT<>(maps);
    }

    @GetMapping("/list")
    @ApiOperation("数据地址列表不分页 ")
    public ReturnT<List<ContainerDO>> list() {
        List<ContainerDO> list = containerDOMapper.listContainer();
        return new ReturnT<>(list);
    }


    @PostMapping("/del")
    @ApiOperation("删除数据地址")
    public ReturnT delContainer(@RequestParam(name = "id")Integer id){
        Boolean result = containerService.deleteContainer(id);
        return new ReturnT(result);
    }

    @PostMapping("/update")
    @ApiOperation("更新数据地址")
    public ReturnT updateTask(@RequestBody ContainerModel containerModel){
        Boolean result = containerService.updateContainer(containerModel);
        return new ReturnT(result);
    }

    @GetMapping("/listBucket")
    @ApiOperation("查询bucket列表")
    public ReturnT listBucket(@RequestParam(name = "dataType")Integer dataType,
                              @RequestParam(name = "accessKeyID")String accessKeyID,
                              @RequestParam(name = "accessKey")String accessKey){
        List<BucketEntry> bucketList = new ArrayList<>();

        //0 腾讯云
        if(dataType == 0) {
            bucketList  = Go2TencentCloudUtil.getBucketList(accessKeyID, accessKey);
        }
        //1 阿里云
        if(dataType == 1) {
            bucketList = AliCloudUtil.getBucketList(accessKeyID,accessKey);
        }

        return new ReturnT(bucketList);
    }




}
