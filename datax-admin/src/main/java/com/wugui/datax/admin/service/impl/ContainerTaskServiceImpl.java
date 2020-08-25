package com.wugui.datax.admin.service.impl;

import com.wugui.datax.admin.datamapper.ContainerDOMapper;
import com.wugui.datax.admin.datamapper.ContainerTaskDOMapper;
import com.wugui.datax.admin.dataobject.ContainerDO;
import com.wugui.datax.admin.dataobject.ContainerTaskDO;
import com.wugui.datax.admin.service.ContainerService;
import com.wugui.datax.admin.service.ContainerTaskService;
import com.wugui.datax.admin.service.model.ContainerModel;
import com.wugui.datax.admin.service.model.ContainerTaskModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by LXM on 2020-07-22 10:00
 */
@Service
public class ContainerTaskServiceImpl implements ContainerTaskService {

    @Autowired
    private ContainerTaskDOMapper containerTaskDOMapper;

    @Autowired
    private ContainerDOMapper containerDOMapper;

    @Override
    public ContainerTaskModel addContainerTask(ContainerTaskModel containerTaskModel) {
        ContainerTaskDO containerTaskDO = this.convertContainerTaskDOFromContainerTaskModel(containerTaskModel);
        containerTaskDOMapper.insertSelective(containerTaskDO);
        return containerTaskModel;
    }

    @Override
    public Boolean deleteContainerTask(Integer id) {
        if (containerTaskDOMapper.deleteByPrimaryKey(id) == 1){
            return true;
        }
        return false;
    }

    @Override
    public List<ContainerTaskDO> listContainerTask(Integer offset, Integer pagesize, String name) {
        List<ContainerTaskDO> containerTaskDOS = containerTaskDOMapper.pageList(offset, pagesize, name);
        return containerTaskDOS;
    }

    @Override
    public Boolean updateContainerTask(ContainerTaskModel containerTaskModel) {
        ContainerTaskDO containerTaskDO = this.convertContainerTaskDOFromContainerTaskModel(containerTaskModel);
        containerTaskDO.setUpdateTime(new Date());

        if (containerTaskDOMapper.updateByPrimaryKeySelective(containerTaskDO) == 1){
            return true;
        }
        return false;
    }

    private ContainerTaskDO convertContainerTaskDOFromContainerTaskModel(ContainerTaskModel containerTaskModel) {
        if(containerTaskModel == null) {
            return null;
        }
        ContainerTaskDO containerTaskDO = new ContainerTaskDO();
        BeanUtils.copyProperties(containerTaskModel,containerTaskDO);
        containerTaskDO.setCreateTime(new Date());
        containerTaskDO.setUpdateTime(new Date());

        ContainerDO containerDO1 = containerDOMapper.selectByPrimaryKey(containerTaskModel.getSourceAddressId());
        String dataName1 = containerDO1.getDataName();
        ContainerDO containerDO2 = containerDOMapper.selectByPrimaryKey(containerTaskModel.getTargetAddressId());
        String dataName2 = containerDO2.getDataName();
        containerTaskDO.setSourceAddress(dataName1);
        containerTaskDO.setTargetAddress(dataName2);

        if(containerTaskModel.getStatus() == null || containerTaskModel.getStatus().equals("")) {
            containerTaskDO.setStatus(0);
        }
        return containerTaskDO;
    }

}
