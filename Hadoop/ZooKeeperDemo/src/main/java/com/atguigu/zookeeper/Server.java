package com.atguigu.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * 服务器上线后 将服务器对应信息写入zk中
 *
 * @author Vanas
 * @create 2020-04-22 9:13 上午
 */
public class Server {

    private String connectionsString = "hadoop130:2181,hadoop133:2181,hadoop134:2181";
    private int sessionTimeOut = 10000;
    private ZooKeeper zkClient = null;
    private String parentNode = "/server";

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        Server server = new Server();
//        初始化zk客户端对象
        server.init();
//        判断zk中存储服务器信息的znode是否存在
        server.parentNodeExits();
//        将服务器信息写入到zk中
        server.writeServer(args);
//        保持线程不结束
        Thread.sleep(Long.MAX_VALUE);
    }

    private void writeServer(String[] args) throws KeeperException, InterruptedException {
        String s = zkClient.create(parentNode + "/" + args[0], args[1].getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL);
        System.out.println("***********" + s + "is online ******************");
    }

    private void parentNodeExits() throws KeeperException, InterruptedException {
        Stat stat = zkClient.exists(parentNode, false);
        if (stat == null) {
//            创建节点
            zkClient.create(parentNode, "servers".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
    }

    private void init() throws IOException {
        zkClient = new ZooKeeper(connectionsString, sessionTimeOut, new Watcher() {
            public void process(WatchedEvent event) {

            }
        });
    }
}
