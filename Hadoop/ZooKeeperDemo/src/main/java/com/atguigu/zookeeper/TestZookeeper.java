package com.atguigu.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author Vanas
 * @create 2020-04-21 3:45 下午
 */
public class TestZookeeper {
    private String connectionString = "hadoop130:2181,hadoop133:2181,hadoop134:2181";
    private int sessionTimeOut = 10000;
    private ZooKeeper zkClient;



    /**
     * 删除
     */
    @Test
    public void testDeleteNode() throws KeeperException, InterruptedException {
        Stat stat = zkClient.exists("/vanas/java", false);
        if (stat == null) {
            System.out.println("删除的节点不存在");
        } else {
            zkClient.delete("/vanas/java", stat.getVersion());
        }

    }

    /**
     * 获取节点内容，设置节点内容
     */
    @Test
    public void testNodeData() throws KeeperException, InterruptedException {
        Stat stat = zkClient.exists("/vanas", false);
        if (stat == null) {
            System.out.println("不存在");
        } else {
            byte[] data = zkClient.getData("/vanas", false, stat);
            System.out.println("data:" + new String(data));
        }
//          如果版本为-1 ，则忽略版本的限制
        zkClient.setData("/vanas", "vanaswang".getBytes(), stat.getVersion());//乐观锁

    }

    /**
     * 获取子节点
     *
     * @throws IOException
     */
    @Test
    public void testChildNode() throws KeeperException, InterruptedException {
//        List<String> children = zkClient.getChildren("/", false);
//        监听只管一次
        List<String> children = zkClient.getChildren("/", new Watcher() {
            public void process(WatchedEvent event) {
                System.out.println("子节点发生变化");
//                重新获取新的子节点
            }
        });

        for (String child : children) {
            System.out.println(child);
        }
//        让线程不结束
        Thread.sleep(Long.MAX_VALUE);
    }

    //    创建节点
    @Test
    public void testCreateNode() throws KeeperException, InterruptedException {
        String s = zkClient.create("/vanas/html", "Java Is Good".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        System.out.println("s:" + s);
    }

    @Before
    public void before() throws IOException {
        zkClient = new ZooKeeper(connectionString, sessionTimeOut, new Watcher() {
            public void process(WatchedEvent event) {

            }
        });
    }

    @After
    public void after() throws InterruptedException {
        zkClient.close();
    }


    @Test
    public void testZKClient() throws IOException, InterruptedException {
//        获取客户端连接对象
        String connectionString = "hadoop130:2181,hadoop133:2181,hadoop134:2181";
//        minSessionTimeout=4000
//        maxSessionTimeout=40000
        int sessionTimeOut = 10000;

        ZooKeeper zkClient = new ZooKeeper(connectionString, sessionTimeOut, new Watcher() {
//            回调方法，当watch对象监听到感兴趣的事件后，会调用process方法
//            在process方法中作出相应处理

            //            watcheEvent 事件对象，封装了所发生的事
            public void process(WatchedEvent event) {
//
            }
        });
//        操作
        System.out.println("zkClient" + zkClient);

//        关闭
        zkClient.close();
    }
}
