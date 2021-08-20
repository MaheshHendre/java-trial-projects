package com.mythread;

import java.util.Optional;

public interface MyExecutorService{
	
	void execute(Runnable r) ;
	
	void shutDown();
	
	Optional<Runnable> getTaskToBeExecuted();
	
	boolean isTaskPending();

}
