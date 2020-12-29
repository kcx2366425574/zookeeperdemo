package com.kcx.zookeeperDemo.connectDemo;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class ZKCreate {

	//create static instance for zookeeper class
	private static ZooKeeper zk;
	
	//create static instance for ZookeeperConnection class
	private static ZookeeperConnection connection;
	
	//method to create znode in zookeeper ensemble
	public static void create(String path, byte[] data) throws KeeperException, InterruptedException{
		zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
	}
	
	public static void main(String[] args) {
		
		//znode path
		String path = "/MyFirstZnode";
		
		//data in byte array
		byte[] data = "My first zookeeper app".getBytes();
		
		try {
			connection = new ZookeeperConnection();
			zk = connection.connect("localhost");
			create(path, data);
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
	}
}
