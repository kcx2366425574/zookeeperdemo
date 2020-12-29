package com.kcx.zookeeperDemo.connectDemo;

import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;


public class ZKGetChildren {
	
	private static ZookeeperConnection connection;
	private static ZooKeeper zoo;
	
	public static Stat znode_exists(String path) throws KeeperException, InterruptedException{
		return zoo.exists(path, true);
	}
	
	public static void main(String[] args) {
		
		String path = "/MyFirstZnode";
		
		// TODO Auto-generated method stub
		try {
			connection = new ZookeeperConnection();
			zoo = connection.connect("localhost");
			
			Stat stat = znode_exists(path);
			
			if(stat != null) {
				List<String> children = zoo.getChildren(path, false);
				for (int i = 0;i<children.size();i++) {
					System.out.println(children.get(i));
				}
			}else {
				System.out.println("znode does not exist");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}
