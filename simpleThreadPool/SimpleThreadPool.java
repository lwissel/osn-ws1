package simpleThreadPool;

import java.util.concurrent.LinkedBlockingQueue;
/*
 *   @author   Lennart Wissel
 *   @version  16.02.2015
 */

/**
 *   <<-- Thread Pool -->> 
 *   It manages a queue of tasks, starts some pool threads.
 * 		
 *   #1. Create a task queue by using queue data structures, or designing your own data structure. 
 */
public class SimpleThreadPool {
	
	/**
	 *   #1. Initialize your queue (or do so in somewhere)
	 *   #2. Starts some ISimplePoolThreads.
	 */
   private LinkedBlockingQueue<ISimpleTask> taskQueue;
   private Thread[] workerThreads;


   public SimpleThreadPool() {
      this.taskQueue = new LinkedBlockingQueue<ISimpleTask>();
      this.workerThreads = new Thread[2];
   }

   public SimpleThreadPool(int nThreads) {
      this.taskQueue = new LinkedBlockingQueue<ISimpleTask>();
      this.workerThreads = new Thread[nThreads];
   }

	public void start() {
      // start all threads
      for(int i = 0; i < this.workerThreads.length; i++) {
         SimplePoolThread spThread = new SimplePoolThread(taskQueue);
         workerThreads[i] = new Thread(spThread);
         workerThreads[i].start();
      }
   }
	
	/**
	 *   #1. Stops everything
	 */
	public void stop() {
      for (Thread workerThread : workerThreads) {
         workerThread.interrupt();
      }
   }
	
	/**
	 *   #1. Add a task to your queue.
	 */
	public void addTask(ISimpleTask task) {
      try {
         taskQueue.put(task);
      } catch (InterruptedException e) {
         Thread.currentThread().interrupt();
      }
   }
}

