package com.mythread.executor.main;

import com.mythread.executor.FixedThreadExecutorService;
import com.mythread.executor.MyExecutorService;

public class TestExecutor {

	public static void main(String[] args) {
		int executorSize = 5;
		MyExecutorService executor = FixedThreadExecutorService.getFixedThreadExecutorService(executorSize);
		
		for (int i=0; i < executorSize*2; i++) {
			executor.execute(()->{
				try {
					Thread.sleep(5000);
					System.out.println("Finished task execution with: " + Thread.currentThread().getName());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
		executor.shutDown();
	}
	
}
