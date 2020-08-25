package com.wugui.datax.admin.service.impl;

import com.wugui.datax.admin.datamapper.ContainerDOMapper;
import com.wugui.datax.admin.dataobject.ContainerDO;
import com.wugui.datax.admin.service.ContainerService;
import com.wugui.datax.admin.service.model.ContainerModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by LXM on 2020-07-22 10:00
 */
@Service
public class ContainerServiceImpl implements ContainerService {

    @Autowired
    private ContainerDOMapper containerDOMapper;

    @Override
    public ContainerModel addContainer(ContainerModel containerModel) {
        ContainerDO containerDO = this.convertContainerDOFromContainerModel(containerModel);
        containerDOMapper.insertSelective(containerDO);
        return containerModel;
    }

    @Override
    public Boolean deleteContainer(Integer id) {
        if (containerDOMapper.deleteByPrimaryKey(id) == 1){
            return true;
        }
        return false;
    }

    @Override
    public List<ContainerDO> listContainer(Integer offset, Integer pagesize, String name) {
        List<ContainerDO> containerDOS = containerDOMapper.pageList(offset, pagesize, name);
        return containerDOS;
    }

    @Override
    public Boolean updateContainer(ContainerModel containerModel) {
        ContainerDO containerDO = this.convertContainerDOFromContainerModel(containerModel);
        containerDO.setUpdateTime(new Date());

        if (containerDOMapper.updateByPrimaryKeySelective(containerDO) == 1){
            return true;
        }
        return false;
    }

    private ContainerDO convertContainerDOFromContainerModel(ContainerModel containerModel) {
        if(containerModel == null) {
            return null;
        }
        ContainerDO containerDO = new ContainerDO();
        BeanUtils.copyProperties(containerModel,containerDO);
        containerDO.setCreateTime(new Date());
        containerDO.setUpdateTime(new Date());
        containerDO.setStatus(0);
        return containerDO;
    }


}
