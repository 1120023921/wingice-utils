package com.wingice.utils.security;

import org.junit.Test;

/**
 * @author 胡昊
 * Description:
 * Date: 2019/5/19
 * Time: 1:01
 * Create: DoubleH
 */
public class DesUtilTest {

    @Test
    public void decrypt() {
        String password = "0B40DA547209313EF246FBB980706F9D";
        System.out.println(DesUtil.decrypt(password));
    }

    @Test
    public void encrypt() {
        String str = "2017012222";
        System.out.println(DesUtil.encrypt(str));
    }
}
