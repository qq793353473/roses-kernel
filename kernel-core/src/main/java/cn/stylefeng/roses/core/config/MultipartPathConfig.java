package cn.stylefeng.roses.core.config;

public class MultipartPathConfig {

    public static String UPLOAD_PATH_WIN = "C:/JavaImages";
    public static String UPLOAD_PATH_LINUX = "/resources/JavaImages/";
    public static String BUCKET_NAME = "/wulin_file";

    public static String getUploadPath() {
        return getSystemPath() + BUCKET_NAME;
    }

    public static String getSystemPath() {
        String os = System.getProperty("os.name");
        //如果是Windows系统
        if (os.toLowerCase().startsWith("win")) {
            return UPLOAD_PATH_WIN;
        } else {
            return UPLOAD_PATH_LINUX;
        }
    }
}
