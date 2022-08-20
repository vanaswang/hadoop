package com.atguigu.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.URI;
import java.util.Arrays;

/**
 * @author Vanas
 * @create 2020-04-13 9:21 上午
 */
public class TestHDFS {

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
    public void testIODowanload1() throws IOException {
        String src = "/user/vanas/xixi";
        String dest = "/Users/vanas/Desktop/home/leon.txt.part1";
        FSDataInputStream in = fs.open(new Path(src));
        FileOutputStream out = new FileOutputStream(new File(dest));
//        0-128
        byte[] buffer = new byte[1024];
        for (int i = 0; i < 128 * 1024; i++) {
            in.read(buffer);
            out.write(buffer);
        }

        IOUtils.closeStreams(in, out);

    }

    @Test
    public void testIODowanload2() throws IOException {
        String src = "/user/vanas/xixi";
        String dest = "/Users/vanas/Desktop/home/leon.txt";
        FSDataInputStream in = fs.open(new Path(src));
        FileOutputStream out = new FileOutputStream(new File(dest));
//      定位流读取的位置
        in.seek(128 * 1024 * 1024);

        IOUtils.copyBytes(in, out, conf);

        IOUtils.closeStreams(in, out);

    }

    //下载和上传
    @Test
    public void testIODowanload() throws IOException {
        String src = "/user/vanas/xixi";
        String dest = "/Users/vanas/Desktop/home/leon.txt";
        FSDataInputStream in = fs.open(new Path(src));
        FileOutputStream out = new FileOutputStream(new File(dest));
        IOUtils.copyBytes(in, out, conf);
        IOUtils.closeStreams(in, out);

    }

    @Test
    public void testIOUpload() throws IOException {
        String src = "/Users/vanas/Desktop/home/haha.txt";
        String dest = "/hello.txt";

//       输入流
        FileInputStream in = new FileInputStream(new File(src));
//       输出流
        FSDataOutputStream out = fs.create(new Path(dest));
//       流的拷贝
//        int i;
//        byte[] buffer = new byte[1024];
//        while ((i = in.read()) != -1) {
//            out.write(buffer, 0, i);
//        }


        IOUtils.copyBytes(in, out, conf);
//       关闭

//        if (in != null) {
//            in.close();
//        }
        IOUtils.closeStreams(in, out);

    }


    /*
        文件和文件夹的判断
        思考：将所有文件都列出
        递归
     */
    public void printAllDirAndFile(String path, FileSystem fs) throws IOException {
        FileStatus[] listStatus = fs.listStatus(new Path(path));
        for (FileStatus status : listStatus) {
            if (status.isFile()) {
                System.out.println("File:" + status.getPath().toString());

            } else {
                System.out.println("DIR:" + status.getPath().toString());
                printAllDirAndFile(status.getPath().toString(), fs);
            }
        }
    }

    @Test
    public void testprintAllDirAndFile() throws IOException {
        printAllDirAndFile("/", fs);

    }

    @Test
    public void isDirOrFile() throws IOException {
        FileStatus[] listStatus = fs.listStatus(new Path("/"));
        for (FileStatus status : listStatus) {
            if (status.isDirectory()) {
                System.out.println(status.getPath().getName() + "目录");
            } else
                System.out.println(status.getPath().getName() + "文件");
        }
    }


    /*
        查看文件详情
     */
    @Test
    public void testListFiles() throws IOException {
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
        while (listFiles.hasNext()) {
            LocatedFileStatus next = listFiles.next();
//            文件信息
            System.out.println(next.getPermission() + "\t" +
                    next.getOwner() + "\t" +
                    next.getGroup() + "\t" +
                    next.getLen() + "\t" +
                    next.getReplication() + "\t" +
                    next.getBlockSize() + "\t" +
                    next.getPath().getName());
//            当前文件块信息
            BlockLocation[] blockLocations = next.getBlockLocations();
            System.out.println(Arrays.toString(blockLocations));
            System.out.println("======================================");
        }
    }

    /*
        文件改名和移动
     */
    @Test
    public void testRename() throws IOException {
//        fs.rename(new Path("/testjava"), new Path("/haha"));
        fs.rename(new Path("/haha"), new Path("/user/vanas/xixi"));
    }

    /*
        如果删目录 目录非空 需要设置为true
     */
    @Test
    public void testDelete() throws IOException {
//        fs.delete(new Path("/testjava/aaa.txt"), true);
        fs.delete(new Path("/testjava"), false);

    }

    @Test
    public void testDownLoad() throws IOException {
        fs.copyToLocalFile(false, new Path("/testjava/aaa.txt"),
                new Path("/Users/vanas/Desktop/home/haha.txt"), true);
    }


    //    上传文件
//    configuration >module xxx-sile.xml>集群 xxx-site.xml>集群 xxx-default.xml
    @Test
    public void testUpload() throws IOException, InterruptedException {
        //1.获取客户端操作
        URI uri = URI.create("hdfs://hadoop130:9820");
        Configuration conf = new Configuration();
        String user = "vanas";
        conf.set("dfs.replication", "1");

        FileSystem fileSystem = FileSystem.get(uri, conf, user);
//        2。操作
        fileSystem.copyFromLocalFile(false, true, new Path("/Users/vanas/Desktop/aaa.txt"), new Path("/testjava"));
//        3关闭
        fileSystem.close();
    }

    //    创建目录
    @Test
    public void testMkdir() throws IOException, InterruptedException {
//        1.获取客户端对象
//        URI uri = new URI("hdfs://hadoop130:9820");
        URI uri = URI.create("hdfs://hadoop130:9820");
        Configuration conf = new Configuration();
        String user = "vanas";
        FileSystem fileSystem = FileSystem.get(uri, conf, user);
//        2.操作
        fileSystem.mkdirs(new Path("/testjava"));
//      3.关闭资源
        fileSystem.close();
    }
}
