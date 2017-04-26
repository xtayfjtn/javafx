package application.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by ZQ on 2017/4/10.
 */
public class Loger {
    //错误类Log输出
    public static void loge(String e) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        File file = new File("loge.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        String str = df.format(SystemUtil.getTime()) +" " + e + "\r";
        printout(str, file);
    }


    //信息类log输出
    public static void logi(String info) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        File file = new File("logi.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        String str = df.format(SystemUtil.getTime()) +" " + info + "\r";
        printout(str, file);

    }

    //共有输出文件函数
    public static void printout(String str, File file) {
        byte bt[] = new byte[1024];
        bt = str.getBytes();
        try {
            FileOutputStream out = new FileOutputStream(file, true);
            try {
                out.write(bt, 0, bt.length);
                out.close();
            } catch (IOException ex) {
                // TODO Auto-generated catch block
                ex.printStackTrace();
            }
        } catch (FileNotFoundException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }
}
