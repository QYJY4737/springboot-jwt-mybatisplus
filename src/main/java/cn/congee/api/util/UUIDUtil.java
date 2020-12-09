package cn.congee.api.util;

import java.util.UUID;

/**
 * @Author: yang
 * @Date: 2020-12-10 7:24
 */
public class UUIDUtil {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }

}
