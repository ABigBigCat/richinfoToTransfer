package com.wugui.datax.admin.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jiahui on 2020-08-24 11:21
 */
@Data
public class HWSourceServerRespDto implements Serializable {

    private Integer count;

    private List<SourceServers> sourceServers;

}
