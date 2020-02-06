package com.java;

import org.apache.log4j.Logger;
import org.junit.Test;

public class Log4jDemo {
    private Logger logger = Logger.getLogger(Log4jDemo.class);

    @Test
    public void demo1(){
        logger.fatal("致命错误！");
        logger.warn("警告");
        logger.info("普通信息");
    }
}
