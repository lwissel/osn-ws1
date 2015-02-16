package testpool;

import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {
   private final LinkedBlockingQueue<Runnable> workerQueue;
   private final Thread[] workerThreads;
   private volatile boolean shutdown;

   public ThreadPool(int nThreads) {
      this.workerQueue = new LinkedBlockingQueue<Runnable>();
      this.workerThreads = new Thread[nThreads];

      // start all threads
      for(int i = 0; i < nThreads; i++) {
         workerThreads[i] = new Worker("Thread in Pool, no: " + i);
         workerThreads[i].start();
      }
   }

   // add a task to the queue
   // put() may block the thread, therefore we have to catch the interrupt
   public void addTask(Runnable rTask) {
      try {
         workerQueue.put(rTask);
      } catch (InterruptedException e) {
         Thread.currentThread().interrupt();
      }
   }

   public void shutdown() {
      while(!workerQueue.isEmpty()) {
         try {
            Thread.sleep(1000);
         } catch (InterruptedException e) {
            //break;
         }
      }  
      shutdown = true;
      for(Thread workerThread : workerThreads) {
         workerThread.interrupt();
      }
   }

   private class Worker extends Thread {
      public Worker(String name) {
         super(name);
      }

      public void run() {
         while(!shutdown) {
            try{
               Runnable r = workerQueue.take();
               r.run();
            } catch (InterruptedException e) {
               // ignore
            }
         }
      }
   }

}
