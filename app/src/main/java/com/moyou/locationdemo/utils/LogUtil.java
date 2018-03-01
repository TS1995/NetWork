package com.moyou.locationdemo.utils;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 日志打印
 */
public class LogUtil {
    
    /** 是否Debug模式打印日志 */
    public static boolean IS_DEBUG = true;
    
    /**
     * 打印警告
     * 
     * @param tag
     *            标记
     * @param log
     *            日志
     */
    public static void i(String tag, Object log) {
        if (IS_DEBUG)
            Log.i(String.format("---------- %s ----------", tag),
                  String.valueOf(log));
    }
    
    /**
     * 打印错误或者异常
     * 
     * @param tag
     *            标记
     * @param log
     *            日志
     */
    public static void e(String tag, String log) {
        if (IS_DEBUG)
            Log.e(tag, log);
    }
    
    /**
     * 打印到文件
     * 
     * @param tag
     *            标记
     * @param log
     *            日志
     */
    @SuppressLint("DefaultLocale")
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void toFile(String tag, Object log) {
        File fileDir;
        // 判断sd卡是否存在
        boolean isSDExist = Environment.getExternalStorageState()
                                       .equals(Environment.MEDIA_MOUNTED);
        if (isSDExist) {
            // 获取跟目录
            fileDir = new File(Environment.getExternalStorageDirectory(),
                               String.format("%s/%d.txt",
                                             "BodeLibLog",
                                             System.currentTimeMillis()));
            if (!fileDir.exists())
                try {
                    File file = fileDir.getParentFile();
                    // 判断文件夹是否存在
                    if (!file.mkdir())
                                      // 不存在创建
                                      file.mkdir();
                    // 创建文件
                    fileDir.createNewFile();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            FileOutputStream outputStream;
            try {
                outputStream = new FileOutputStream(fileDir, true);
                outputStream.write(String.format("---------- %s ----------          %s\n",
                                                 tag,
                                                 log)
                                         .getBytes());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }
    //可以全局控制是否打印log日志
    private static boolean isPrintLog = true;

    private static int LOG_MAXLENGTH = 5000;

    public static void LogShitou(String msg) {
        if (isPrintLog) {

            int strLength = msg.length();
            int start = 0;
            int end = LOG_MAXLENGTH;
            for (int i = 0; i < 100; i++) {
                if (strLength > end) {
                    Log.e("shitou___" + i, msg.substring(start, end));
                    start = end;
                    end = end + LOG_MAXLENGTH;
                } else {
                    Log.e("shitou___" + i, msg.substring(start, strLength));
                    break;
                }
            }
        }
    }

    public static void LogShitou(String type, String msg) {

        if (isPrintLog) {

            int strLength = msg.length();
            int start = 0;
            int end = LOG_MAXLENGTH;
            for (int i = 0; i < 100; i++) {
                if (strLength > end) {
                    Log.e(type + "___" + i, msg.substring(start, end));
                    start = end;
                    end = end + LOG_MAXLENGTH;
                } else {
                    Log.e(type + "___" + i, msg.substring(start, strLength));
                    break;
                }
            }
        }
    }

}
