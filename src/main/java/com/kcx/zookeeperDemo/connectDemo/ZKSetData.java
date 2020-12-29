package com.kcx.zookeeperDemo.connectDemo;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

public class ZKSetData {
	
	private static ZooKeeper zoo;
	private static ZookeeperConnection connection;
	
	public static void updateData(String path, byte[] data) throws KeeperException, InterruptedException{
		zoo.setData(path, data, zoo.exists(path, true).getVersion());
	}

	public static void set(String path, String data) throws KeeperException, InterruptedException{
		
		try {
			connection = new ZookeeperConnection();
			zoo = connection.connect("localhost");
			
			updateData(path, data.getBytes());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}
