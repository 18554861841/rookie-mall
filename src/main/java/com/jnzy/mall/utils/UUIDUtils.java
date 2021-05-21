package com.jnzy.mall.utils;

import java.util.UUID;

/**
 * @author 北城
 */
public class UUIDUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
