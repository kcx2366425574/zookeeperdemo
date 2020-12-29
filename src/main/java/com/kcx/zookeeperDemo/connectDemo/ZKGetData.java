package com.kcx.zookeeperDemo.connectDemo;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZKGetData {

	private static ZooKeeper zoo;
	private static ZookeeperConnection connection;
	
	public static Stat znode_exists(String path) throws KeeperException, InterruptedException{
		return zoo.exists(path, true);
	}
	
	public static String get(String path) throws KeeperException, InterruptedException{
		// TODO Auto-generated method stub
		final CountDownLatch connectionSignal = new CountDownLatch(1);
		String data = "";
		try {
			connection = new ZookeeperConnection();
			zoo = connection.connect("localhost");
			Stat stat = znode_exists(path);
			
			if(stat != null) {
				byte[] b = zoo.getData(path, new Watcher() {
					
					@Override
					public void process(WatchedEvent event) {
						// TODO Auto-generated method stub
						if(event.getType() == Event.EventType.None) {
							switch (event.getState()) {
							case Expired: 
								connectionSignal.countDown();
								break;
							}
						} else {
							try {
								byte[] bn = zoo.getData(path, false, null);
								String data = new String(bn, "UTF-8");
								System.out.println(data);
								connectionSignal.countDown();
							} catch (Exception e) {
								// TODO: handle exception
								System.out.println(e.getMessage());
							}
						}
					}
				}, null);
				
				data = new String(b, "UTF-8");
			} else {
				System.out.println("Node does not exists");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return data;
	}
	
	public static void main(String[] args) throws KeeperException, InterruptedException {
		ZKGetData getData = new ZKGetData();
		String data = getData.get("/mydata");
		System.out.println(data);
	}

}
