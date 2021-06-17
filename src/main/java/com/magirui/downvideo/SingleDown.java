package com.magirui.downvideo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author MagiRui
 * @description
 * @date 2018/12/28
 */
public class SingleDown {

    public static void main(String[] args){

        try {
            String urlname = "https://tsvideo.oss-cn-hangzhou.aliyuncs.com/%E5%B4%94%E5%BA%86%E6%89%8D%E5%8F%82%E5%9B%A2python/140602.mp4";
            URL url = new URL(urlname);
            HttpURLConnection urlconn = (HttpURLConnection) url.openConnection();
            if (urlconn.getResponseCode() == 206) {
                String path = "/Users/magirui/Desktop/01.mv";
                File file = new File(path);
                InputStream in = urlconn.getInputStream();
                byte[] b = new byte[1024];
                RandomAccessFile raf = new RandomAccessFile(file, "rw");
                int readcode = 0;
                while ((readcode = in.read(b)) != -1) {
                    raf.write(b, 0, readcode);
                }
                raf.close();
                in.close();
                System.out.println("下载完成");
            }
        } catch (IOException e) { // TODO Auto-generated catch block
            System.out.println("IO异常");

        }
    }
}
