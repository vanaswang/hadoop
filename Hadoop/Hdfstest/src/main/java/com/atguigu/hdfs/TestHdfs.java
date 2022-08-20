package com.atguigu.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.yarn.webapp.hamlet2.Hamlet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.URI;
import java.util.Arrays;

/**
 * @author Vanas
 * @create 2020-04-13 7:49 下午
 */
public class TestHdfs {
    private FileSystem fs;
    private Configuration conf;

    @Before
    public void before() throws IOException, InterruptedException {
        URI uri = URI.create("hdfs://hadoop130:9820");
        conf = new Configuration();
        String user = "vanas";
        fs = FileSystem.get(uri, conf, user);
    }

    @After
    public void after() throws IOException {
        fs.close();
    }

    @Test
    public void test() {

    }

    @Test
    public void testIODawnload1() throws IOException {
        String src = "/testjava/hadoop-3.1.3.tar";
        String dest = "/Users/vanas/Desktop/hadoop-3.1.3.tar.part1";
        FSDataInputStream in = fs.open(new Path(src));
        FileOutputStream out = new FileOutputStream(dest);
        byte[] buffer = new byte[1024];
        for (int i = 0; i < 1024 * 128; i++) {
            in.read(buffer);
            out.write(buffer);
        }

        IOUtils.closeStreams(in, out);
    }

    @Test
    public void testIODawnload2() throws IOException {
        String src = "/testjava/hadoop-3.1.3.tar";
        String dest = "/Users/vanas/Desktop/hadoop-3.1.3.tar.part2";
        FSDataInputStream in = fs.open(new Path(src));
        FileOutputStream out = new FileOutputStream(dest);

        in.seek(128*1024*1024);
        IOUtils.copyBytes(in,out,conf);
        IOUtils.closeStreams(in, out);
    }

    @Test
    public void testIODowanload() throws IOException {
        String src = "/hello.txt";
        String dest = "/Users/vanas/Desktop/loveu.txt";
        FSDataInputStream in = fs.open(new Path(src));
        FileOutputStream out = new FileOutputStream(dest);

        IOUtils.copyBytes(in, out, conf);
        IOUtils.closeStreams(in, out);

    }


    @Test
    public void testIOUpload() throws IOException {
        String src = "/Users/vanas/Desktop/home/haha.txt";
        String dest = "/hello.txt";
        FileInputStream in = new FileInputStream(src);
        FSDataOutputStream out = fs.create(new Path(dest));

//        int i ;
//        byte[] buffer = new  byte[1024];
//        while ((i=in.read())!=-1){
//            out.write(buffer,0,i);
//        }
//        if (in != null) {
//            in.close();
//        }
//        if (out != null) {
//            out.close();
//        }
        IOUtils.copyBytes(in, out, conf);
        IOUtils.closeStreams(in, out);

    }

    @Test
    public void printAllDirAndFile() throws IOException {
        printAllDirAndFiles("/", fs);

    }

    public void printAllDirAndFiles(String path, FileSystem fs) throws IOException {
        FileStatus[] listStatus = fs.listStatus(new Path(path));
        for (FileStatus status : listStatus) {
            if (status.isFile()) {
                System.out.println("File:" + status.getPath());
            } else {
                System.out.println("Dir:" + status.getPath());
                printAllDirAndFiles(status.getPath().toString(), fs);
            }
        }
    }

    @Test
    public void isDirOrFile() throws IOException {
        FileStatus[] listStatus = fs.listStatus(new Path("/"));
        for (FileStatus status : listStatus) {
            if (status.isDirectory()) {
                System.out.println(status.getPath().getName() + ":目录");
            } else
                System.out.println(status.getPath().getName() + ":文件");
        }


    }

    @Test
    public void testListFiles() throws IOException {
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
        while (listFiles.hasNext()) {
            LocatedFileStatus next = listFiles.next();
            System.out.println(next.getPermission() + "\t" +
                    next.getOwner() + "\t" +
                    next.getGroup() + "\t" +
                    next.getLen() + "\t" +
                    next.getReplication() + "\t" +
                    next.getBlockSize() + "\t" +
                    next.getPath().getName());
            BlockLocation[] blockLocations = next.getBlockLocations();
            System.out.println(Arrays.toString(blockLocations));
            System.out.println("=================================");
        }
    }

    @Test
    public void testRename() throws IOException {
        fs.rename(new Path("/testjava/haha.txt"), new Path("/love.txt"));
    }

    @Test
    public void testDelete() throws IOException {
        fs.delete(new Path("/README.txt"), false);
    }

    @Test
    public void testDownload() throws IOException {
        fs.copyToLocalFile(new Path("/testjava/haha.txt"), new Path("/Users/Vanas/Desktop"));
    }

    @Test
    public void testUpload() throws IOException, InterruptedException {
        URI uri = URI.create("hdfs://hadoop130:9820");
        Configuration conf = new Configuration();
        String user = "vanas";
        FileSystem fileSystem = FileSystem.get(uri, conf, user);

        fileSystem.copyFromLocalFile(new Path("/Users/vanas/Desktop/home/haha.txt"), new Path("/testjava"));

        fileSystem.close();
    }

    @Test
    public void testMkdir() throws IOException, InterruptedException {
//        获取客户端对象
        URI uri = URI.create("hdfs://hadoop130:9820");
        Configuration conf = new Configuration();
        String user = "vanas";
        FileSystem fileSystem = FileSystem.get(uri, conf, user);
        //        操作
        fileSystem.mkdirs(new Path("/testjava"));

        fileSystem.close();
    }
}
