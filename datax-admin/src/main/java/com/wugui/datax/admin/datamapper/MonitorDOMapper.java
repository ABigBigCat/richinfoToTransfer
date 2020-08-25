package com.wugui.datax.admin.datamapper;

import com.wugui.datax.admin.dataobject.MigrateThreadDO;
import com.wugui.datax.admin.dataobject.ModelDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitorDOMapper {

    int insertModelDO(ModelDO record);

    List<ModelDO> listModelDOByModelId(Integer modelId);

}
