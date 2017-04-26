package application.Util;

import java.util.Date;
import java.util.Locale;

/**
 * 获取系统信息类
 * Created by ZQ on 2017/3/28.
 */
public class SystemUtil {
    public static String getLanguage() {
        Locale locale = Locale.getDefault();
        return locale.getLanguage();
    }

    public static Date getTime() {
        Date date = new Date();
        return date;
    }
}
