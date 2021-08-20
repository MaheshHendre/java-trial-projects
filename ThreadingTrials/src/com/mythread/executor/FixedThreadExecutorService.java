package com.mythread.executor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class FixedThreadExecutorService implements MyExecutorService{
	
	private List<Runnable> taskList = new LinkedList<>();;
	
	private List<MyTaskExecutor> taskExecutorList;
	
	private boolean isShutDown = false;

	private FixedThreadExecutorService(int threadPoolSize) {
		taskExecutorList = new ArrayList<MyTaskExecutor>();
		for (int i = 0;i < threadPoolSize; i++) {
			taskExecutorList.add(i,new MyTaskExecutor(this));
			 new Thread(taskExecutorList.get(i)).start();
		}
	}
	
	public static FixedThreadExecutorService getFixedThreadExecutorService(int threadPoolSize) {
		return new FixedThreadExecutorService(threadPoolSize);
	}

	@Override
	public synchronized void execute(Runnable task) {
		if(!isShutDown()) {
			this.taskList.add(0,task);
			this.notifyAll();
		}
	}
	
	public boolean isShutDown() {
		return isShutDown;
	}

	public void setShutDown(boolean isShutDown) {
		this.isShutDown = isShutDown;
	}


	@Override
	public void shutDown() {
		this.taskExecutorList.forEach(taskExecutor -> taskExecutor.setShutDown(true));
		
	}

	@Override
	public synchronized Optional<Runnable> getTaskToBeExecuted() {
		return taskList.size() > 0 ? Optional.ofNullable(taskList.remove(taskList.size() - 1)) 
								   : Optional.empty();
	}
	
	public boolean isTaskPending() {
		return this.taskList.size() > 0;
	}
}
