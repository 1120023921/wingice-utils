package com.wingice.utils.http;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author 胡昊
 * Description:
 * Date: 2019/5/26
 * Time: 12:06
 * Create: DoubleH
 */
public class HttpRequestTest {

    @Test
    public void sendGet() {
        //发送 GET 请求
        String s = HttpRequestUtil.sendGet("http://www.baidu.com", null, null);
        System.out.println(s);
    }

    @Test
    public void sendPost() {
        //发送 GET 请求
        String s = HttpRequestUtil.sendPost("http://www.baidu.com", null, null);
        System.out.println(s);
    }
}
