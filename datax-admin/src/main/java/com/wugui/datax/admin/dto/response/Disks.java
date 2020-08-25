package com.wugui.datax.admin.dto.response;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jiahui on 2020-08-24 13:25
 */
@Data
public class Disks implements Serializable {

    private String name;

    private Long size;

    private String deviceUse;

}
