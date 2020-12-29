package com.kcx.zookeeperDemo.connectDemo;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class InterprocessLock {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CuratorFramework zkClient = getZkClient();
		String lockPath = "/lock";
		InterProcessMutex lock = new InterProcessMutex(zkClient, lockPath);
		
		//模拟50个线程抢锁
		for (int i=0;i<50;i++) {
			new Thread(new TestThread(i, lock)).start();
		}
	}
	
	static class TestThread implements Runnable {
		private Integer threadFlag;
		private InterProcessMutex lock;
		
		public TestThread(Integer threadFlag, InterProcessMutex lock) {
			this.threadFlag = threadFlag;
			this.lock = lock;
		}
		@Override
		public void run() {
			try {
				lock.acquire();
				System.out.println("第"+ threadFlag+"线程获取到了锁");
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					lock.release();
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
			}
		}
		
	}

	private static CuratorFramework getZkClient() {
		String zkServerAddress = "127.0.0.1:2181";
		ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3, 5000);
		CuratorFramework zkClient = CuratorFrameworkFactory.builder().connectString(zkServerAddress)
				.sessionTimeoutMs(5000).connectionTimeoutMs(5000).retryPolicy(retryPolicy)
				.build();
		zkClient.start();
		return zkClient;
	}

}
