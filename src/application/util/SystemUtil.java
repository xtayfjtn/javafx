package application.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
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

    public static SqlSession openAndGetSession() {
        SqlSession session = null;
        try {
            String resource = "configuration.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(reader);
            session = factory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return session;
    }
}
