package cn.stylefeng.roses.core.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

public class MultipartPathConfig {

    public static String UPLOAD_PATH_WIN = "C:/JavaImages/";
    public static String UPLOAD_PATH_LINUX = "/resources/JavaImages/";
    public static String BUCKET_NAME = "wulin_file";

    public static String getUploadPath() {
        String os = System.getProperty("os.name");
        //如果是Windows系统
        if (os.toLowerCase().startsWith("win")) {
            return UPLOAD_PATH_WIN;
        } else {
            return UPLOAD_PATH_LINUX;
        }
    }

}
