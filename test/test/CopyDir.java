package test;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 把一个文件夹里的所有文件包括文件夹，
 * 经过过滤文件大小，最后修改时间
 * 将符合条件的文件拷贝到另一个目录中；
 * Created by LGZ on 2016/8/16.
 */
public class CopyDir {
    static Calendar cal;
    static SimpleDateFormat format;

    static {
        cal = Calendar.getInstance();
        format = new SimpleDateFormat("yyyy-MM-dd");
    }

    // 目标路径创建文件夹
    public static void listFileInDir(File file, File dirTo, String dateFormat, long minFileSize) throws ParseException {
        File[] files = file.listFiles();
        for (File f : files) {
            if (checkFile(f, dateFormat, minFileSize))
                continue;

            String tempFrom = f.getAbsolutePath();
            String tempTo = tempFrom.replace(file.getAbsolutePath(),
                    dirTo.getAbsolutePath()); // 后面的路径 替换前面的路径名
            if (f.isDirectory()) {
                File tempFile = new File(tempTo);
                tempFile.mkdirs();
                listFileInDir(f, dirTo, dateFormat, minFileSize);
            } else {
                System.out.println("源文件:" + f.getAbsolutePath());
                //
                int endIndex = tempTo.lastIndexOf("\\");// 找到"/"所在的位置
                String mkDirPath = tempTo.substring(0, endIndex);
                File tempFile = new File(mkDirPath);
                tempFile.mkdirs();// 创建立文件夹

                tempTo = tempTo + ".png";
                System.out.println("目标点:" + tempTo);
                copy(tempFrom, tempTo);
            }
        }
    }

    /**
     * 封装好的文件拷贝方法
     */
    public static void copy(String from, String to) {
        try {
            InputStream in = new FileInputStream(from);
            OutputStream out = new FileOutputStream(to);

            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = in.read(buff)) != -1) {
                out.write(buff, 0, len);
            }
            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证大小和日期,
     * 如果在指定日期之前 返回true
     * 如果小于指定的最小字节，返回true
     *
     * @return
     */
    static boolean checkFile(File file, String dateFormat, long minFileSize) throws ParseException {
        long lastModifiedTime = file.lastModified();
        cal.setTimeInMillis(lastModifiedTime);
        Date updateTime = cal.getTime();
        Date date = format.parse(dateFormat);
        if (updateTime.before(date))
            return true;
        if (file.length() < minFileSize)
            return true;
        return false;
    }

    public static void main(String[] args) throws ParseException {
        File fromFile = new File("C:\\Users\\LGZ\\AppData\\Local\\Packages\\Microsoft.Windows.ContentDeliveryManager_cw5n1h2txyewy\\LocalState\\Assets");// 源文件夹
        File toFile = new File("D:\\bizhi");// 目标

        String dateFormat = "2017-3-12";//最后修改日期在这个日期以后的文件
        long minFileSize = 300 * 1024;//字节，文件大于300kb的文件
        listFileInDir(fromFile, toFile, dateFormat, minFileSize);
    }
}