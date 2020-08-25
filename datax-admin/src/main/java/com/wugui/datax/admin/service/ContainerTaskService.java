package com.wugui.datax.admin.service;



import com.wugui.datax.admin.dataobject.ContainerTaskDO;
import com.wugui.datax.admin.service.model.ContainerTaskModel;

import java.util.List;

/**
 * Created by LXM on 2020-07-21 15:12
 */
public interface ContainerTaskService {

    ContainerTaskModel addContainerTask(ContainerTaskModel containerTaskModel);

    Boolean deleteContainerTask(Integer id);

    List<ContainerTaskDO> listContainerTask(Integer offset, Integer pagesize, String name);

    Boolean updateContainerTask(ContainerTaskModel containerTaskModel);

}
