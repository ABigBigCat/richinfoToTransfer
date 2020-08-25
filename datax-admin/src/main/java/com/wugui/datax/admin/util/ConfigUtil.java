package com.wugui.datax.admin.util;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ConfigUtil {
    public static Config getConfT() {
        return ConfigFactory.load("tencentcloud.conf");
    }
}
