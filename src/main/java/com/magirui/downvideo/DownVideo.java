package com.magirui.downvideo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author MagiRui
 * @description
 * @date 2018/12/28
 */
public class DownVideo {

    public static void main(String[] args) {
        try {

            String urlname = "https://tsvideo.oss-cn-hangzhou.aliyuncs.com/%E5%B4%94%E5%BA%86%E6%89%8D%E5%8F%82%E5%9B%A2python/140602.mp4";
            URL url = new URL(urlname);
            URLConnection conn = url.openConnection();
            int length = conn.getContentLength();
            System.out.println("length=" + length);
            // 定义线程数量
            int size = 2;
            int block = length % size == 0 ? length / size : length / size + 1;
            for (int i = 0; i < size; i++) {
                int start = i * block;
                System.out.println("block=" + block);
                int end = start + (block - 1);
                if (i == size - 1) { end++; }
                Thread1 tt1 = new Thread1(start, end, url);
                new Thread().start();
                System.out.println("start=" + start + "end=" + end);
            }
        } catch (MalformedURLException e) { // TODO Auto-generated catch block e.printStackTrace();

        } catch (IOException e) { // TODO Auto-generated catch block
             e.printStackTrace();
        }
    }


}

class Thread1 implements Runnable {

    private int start;
    private int end;
    private URL url;

    public Thread1(int start, int end, URL url) {
        super();
        this.start = start;
        this.end = end;
        this.url = url;
    }


    public void run() { // TODO Auto-generated method stub
        System.out.println("下载中");
        try {
            HttpURLConnection urlconn = (HttpURLConnection) url.openConnection();
            urlconn.setRequestProperty("Range", "bytes=" + start + "-" + end);
            if (urlconn.getResponseCode() == 206) {
                String path = "/Users/magirui/Desktop/01.mv";
                File file = new File(path);
                InputStream in = urlconn.getInputStream();
                byte[] b = new byte[1024];
                RandomAccessFile raf = new RandomAccessFile(file, "rw");
                raf.seek(start);
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