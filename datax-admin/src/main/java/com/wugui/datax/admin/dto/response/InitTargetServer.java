package com.wugui.datax.admin.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jiahui on 2020-08-24 13:34
 */
@Data
public class InitTargetServer implements Serializable {

    private List<Disks> disks;

}
