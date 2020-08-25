package com.wugui.datax.admin.service;

import com.wugui.datax.admin.dataobject.ContainerDO;
import com.wugui.datax.admin.service.model.ContainerModel;
import java.util.List;

/**
 * Created by LXM on 2020-07-21 15:12
 */
public interface ContainerService {

    ContainerModel addContainer(ContainerModel containerModel);

    Boolean deleteContainer(Integer id);

    List<ContainerDO> listContainer(Integer offset, Integer pagesize, String name);

    Boolean updateContainer(ContainerModel containerModel);

}
