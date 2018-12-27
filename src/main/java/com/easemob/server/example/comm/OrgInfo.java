package com.easemob.server.example.comm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class OrgInfo {

    public static String ORG_NAME;
    public static String APP_NAME;
    public static final Logger logger = LoggerFactory.getLogger(OrgInfo.class);

    static {
        /**
         * 读取配置文件配置的环信信息 wstv
         */
        InputStream inputStream = OrgInfo.class.getClassLoader().getResourceAsStream("config.properties");
        Properties prop = new Properties();
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        ORG_NAME = prop.getProperty("ORG_NAME");
        APP_NAME = prop.getProperty("APP_NAME");
    }
}
