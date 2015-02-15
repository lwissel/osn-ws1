public class HelloRunnable implements Runnable {
   public void run() {
      System.out.println("runnable implementation of thread");
   }

   public static void main(String[] args) {
      HelloRunnable ht = new HelloRunnable();
      Thread t = new Thread(ht);
      t.start();
   }
}
