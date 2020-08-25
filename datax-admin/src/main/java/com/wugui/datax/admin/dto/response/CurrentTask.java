package com.wugui.datax.admin.dto.response;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jiahui on 2020-08-24 13:24
 */
@Data
public class CurrentTask implements Serializable {

    private String id;

    private String name;

    private String state;

}
