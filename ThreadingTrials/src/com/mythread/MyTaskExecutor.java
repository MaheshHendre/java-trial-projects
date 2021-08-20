package com.mythread;

import java.util.Optional;

public class MyTaskExecutor implements Runnable {

	private MyExecutorService executorService;
	
	private boolean isShutDown = false;

	MyTaskExecutor(MyExecutorService executorService) {
		this.executorService = executorService;
	}

	@Override
	public void run() {
		while (!isShutDown() ||  executorService.isTaskPending() ) {
			Optional<Runnable> task;
			synchronized (executorService) {
				task = this.executorService.getTaskToBeExecuted();
				if (!task.isPresent()) {
					try {
						this.executorService.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			// Execute the task
			if (task.isPresent()) {
				task.get().run();
			}
		}
	}
	

	public boolean isShutDown() {
		return isShutDown;
	}

	public void setShutDown(boolean isShutDown) {
		this.isShutDown = isShutDown;
	}
}
