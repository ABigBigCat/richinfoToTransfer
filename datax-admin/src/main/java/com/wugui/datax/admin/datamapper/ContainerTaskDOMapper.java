package com.wugui.datax.admin.datamapper;

import com.wugui.datax.admin.dataobject.ContainerTaskDO;
import com.wugui.datax.admin.dataobject.ModelDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContainerTaskDOMapper {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_task
     *
     * @mbg.generated Fri Jul 10 15:40:51 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_task
     *
     * @mbg.generated Fri Jul 10 15:40:51 CST 2020
     */
    int insert(ContainerTaskDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_task
     *
     * @mbg.generated Fri Jul 10 15:40:51 CST 2020
     */
    int insertSelective(ContainerTaskDO record);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_task
     *
     * @mbg.generated Fri Jul 10 15:40:51 CST 2020
     */
    ContainerTaskDO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_task
     *
     * @mbg.generated Fri Jul 10 15:40:51 CST 2020
     */
    int updateByPrimaryKeySelective(ContainerTaskDO record);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_task
     *
     * @mbg.generated Fri Jul 10 15:40:51 CST 2020
     */
    int updateByPrimaryKey(ContainerTaskDO record);

    /**
     * 获取对象存储任务列表分页
     * @param offset
     * @param pagesize
     * @param name
     * @return
     */
    List<ContainerTaskDO> pageList(@Param("offset") int offset,
                                   @Param("pagesize") int pagesize,
                                   @Param("name") String name);

    /**
     * 获取对象存储任务分页总数
     * @param offset
     * @param pagesize
     * @param name
     * @return
     */
    int pageListCount(@Param("offset") int offset,
                      @Param("pagesize") int pagesize,
                      @Param("name") String name);


}
