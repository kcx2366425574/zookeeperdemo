package com.kcx.zookeeperDemo.connectDemo;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZKExists {

	private static ZooKeeper zoo;
	private static ZookeeperConnection connection;
	
	//method to check existence of znode and its status, if znode is available
	public static Stat znode_exists(String path) throws KeeperException,InterruptedException{
		return zoo.exists(path, true);
	}
	
	public static void main(String[] args) throws KeeperException, InterruptedException{
		// TODO Auto-generated method stub
		String path = "/mydata";
		
		try {
			connection = new ZookeeperConnection();
			zoo = connection.connect("localhost");
			Stat stat = znode_exists(path);
			
			if(stat != null) {
				System.out.println("Node exists and the node version is "+ stat.getVersion());
			} else {
				System.out.println("Node does not exists");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

}
