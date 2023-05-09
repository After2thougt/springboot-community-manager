package com.bee.manager.common.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;

/**
 * DigestUtils
 *
 */
public class DigestUtils {

    /**
     *
     * 功能描述: MD5加密账号密码
     *
     */
    public static String Md5(String userName,String password){
        Md5Hash hash = new Md5Hash(password, ByteSource.Util.bytes(userName), 2);
        return hash.toString();
    }
}
