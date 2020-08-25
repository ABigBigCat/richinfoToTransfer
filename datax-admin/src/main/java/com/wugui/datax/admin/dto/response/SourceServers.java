package com.wugui.datax.admin.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jiahui on 2020-08-24 11:30
 */
@Data
public class SourceServers implements Serializable {

    private String id;

    private String ip;

    private String name;

    private String hostname;

    private Long addDate;

    private String osType;

    private String osVersion;

    private Boolean oemSystem;

    private String state;

    private Boolean connected;

    private CurrentTask currentTask;

    private InitTargetServer initTargetServer;

}
