package com.mbyte.easy;

import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.util.ObjectUtils;

/**
 * @auther: wangzhen
 * @date: 19-4-11 20:28
 * @description: 测试空工具类
 */

public class TestEmpty extends TestCase {

    @Test
    public void test(){
        boolean flag = ObjectUtils.isEmpty(0);
        flag = ObjectUtils.isEmpty(" ");
        System.out.println(flag);
    }



}
