package com.wugui.datax.admin.tool;

import com.alibaba.fastjson.JSON;
import com.wugui.datax.admin.datamapper.CloudAccountDOMapper;
import com.wugui.datax.admin.datamapper.ContainerDOMapper;
import com.wugui.datax.admin.datamapper.ContainerTaskDOMapper;
import com.wugui.datax.admin.dataobject.CloudAccountDO;
import com.wugui.datax.admin.dataobject.ContainerDO;
import com.wugui.datax.admin.dataobject.ContainerTaskDO;
import com.wugui.datax.admin.dto.ChangeServerNameReqDto;
import com.wugui.datax.admin.dto.response.HWSourceServerRespDto;
import com.wugui.datax.admin.service.*;
import com.wugui.datax.admin.service.model.*;
import com.wugui.datax.admin.util.Go2TencentCloudUtil;
import com.wugui.datax.admin.util.HWApiUtils;
import org.checkerframework.checker.units.qual.A;
import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by jiahui on 2020-07-09 15:47
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestCloud {

    @Autowired
    private CloudAccountService cloudAccountService;

    @Autowired
    private SourceServerService sourceServerService;

    @Autowired
    private SystemTaskService systemTaskService;

    @Autowired
    private CloudAccountDOMapper cloudAccountDOMapper;

    @Autowired
    private ContainerService containerService;

    @Autowired
    private ContainerTaskService containerTaskService;

    @Autowired
    private ContainerDOMapper containerDOMapper;

    @Autowired
    private ContainerTaskDOMapper containerTaskDOMapper;

    @Test
    public void testCloud(){
        cloudAccountService.deleteAccount(2);
    }

    @Test
    public void allCloud(){
        CloudAccountModel cloudAccountModel = new CloudAccountModel();
        cloudAccountModel.setAccesskeyId("aaa");
        cloudAccountModel.setAccesskeySecret("bbb");
        cloudAccountModel.setName("one");
        cloudAccountModel.setType(0);
        cloudAccountModel.setRemarks("test");
        cloudAccountService.addCloudAccount(cloudAccountModel);
    }

    @Test
    public void addServer(){
        SourceServerModel sourceServerModel = new SourceServerModel();
        sourceServerModel.setIp("192.168.22.221");
        sourceServerModel.setName("test");
        sourceServerModel.setType(0);
        sourceServerModel.setUsername("test");
        sourceServerModel.setPassword("11");
        sourceServerModel.setPort("22");
        sourceServerService.addSourceServer(sourceServerModel);
    }

    @Test
    public void addTask(){
        SystemTaskModel systemTaskModel = new SystemTaskModel();
        systemTaskModel.setBroadband(21);
        systemTaskModel.setCloudAccountId(1);
        systemTaskModel.setCloudServerIp("123");
        systemTaskModel.setName("test");
        systemTaskModel.setStartTime(new Date());
        systemTaskModel.setTargetArea("hangzhou");
        systemTaskModel.setSourceServerId(2);
        systemTaskModel.setStartType(1);
        systemTaskService.addSystemTask(systemTaskModel);
    }

    @Test
    public void sourceListTest(){
        List<SourceServerModel> list =  sourceServerService.getSourceList();

        System.out.println(list.get(0).getId());

    }

    @Test
    public void cloudList(){
        List<CloudAccountModel> list = cloudAccountService.getCloudList();

        System.out.println(list.get(0).getName());
    }

    @Test
    public void pageCloud(){
        int result = cloudAccountDOMapper.pageListCount(1,2,"");
        System.out.println(result);
    }

    @Test
    public void pageClouds(){
        List<CloudAccountDO> cloudAccountDOS = cloudAccountDOMapper.pageList(1, 10, "");

        System.out.println(cloudAccountDOS.get(0).getName());
        System.out.println(cloudAccountDOS.get(1).getName());
        //System.out.println(cloudAccountDOS.get(2).getName());

    }

    @Test
    public void testHW(){
        String url = "https://sms.cn-north-1.myhuaweicloud.com/v1/sms/sources";
        HWSourceServerRespDto hwSourceServerRespDto = JSON.parseObject(HWApiUtils.getHttps(url,"GET",null),HWSourceServerRespDto.class);

        System.out.println(hwSourceServerRespDto.getCount());
        System.out.println(hwSourceServerRespDto.getSourceServers());
    }

    @Test
    public void updateHW(){
        ChangeServerNameReqDto changeServerNameReqDto = new ChangeServerNameReqDto();
        changeServerNameReqDto.setName("testJson");
        String url = "https://sms.cn-north-1.myhuaweicloud.com/v1/sms/sources/2a289e49-0f27-4b3c-93fe-572486334230";
        HWApiUtils.getHttps(url,"PUT",JSON.toJSONString(changeServerNameReqDto));
    }

    @Test
    public void delHW(){
        String url = "https://sms.cn-north-1.myhuaweicloud.com/v1/sms/sources/2a289e49-0f27-4b3c-93fe-572486334230";
        HWApiUtils.getHttps(url,"DELETE",null);
    }

    @Test
    public void start() {
//        ContainerModel containerModel = new ContainerModel();
//        containerModel.setAccesskeyId("abc");
//        containerModel.setAccesskeySecret("nn");
//        containerModel.setBucket("b");
//        containerModel.setDataName("ggg");
//        containerModel.setDataType(0);
//        containerModel.setEndpoint("en");
//        containerModel.setPath("la");
//        ContainerModel containerModel1 = containerService.addContainer(containerModel);
//        System.out.println(containerModel1.toString());

//        List<ContainerDO> containerDOS = containerDOMapper.pageList(0, 4, "");
//        System.out.println(containerDOS);


//        containerService.deleteContainer(1);

//        containerService.updateContainer(containerModel);


//        ContainerTaskModel containerTaskModel = new ContainerTaskModel();
//
//        containerTaskModel.setTaskName("hh");
//        containerTaskModel.setSourceAddress("pp");
//        containerTaskModel.setTargetAddress("oo");
//
//        containerTaskService.addContainerTask(containerTaskModel);

        //containerTaskService.updateContainerTask(containerTaskModel);

//        List<ContainerDO> containerDOS = containerDOMapper.pageList(0, 4, "");
//        System.out.println(containerDOS);

        //Go2TencentCloudUtil.startContainer
    }

//
//    @Test
//    public void stop() {
//        Go2TencentCloudUtil.stopGoToTencent("192.168.22.242",22,"root","richinfo@139");
//    }

}
