package helloWorldThread.notInOrder;

public class MyThread5Times extends Thread {
	public void run(){
		for(int i=0; i<50;i++){
			System.out.println("This is my thread");
		}	
	}
	public static void main(String[] args) {
		MyThread5Times t = new MyThread5Times();
		t.start();
		for(int i = 0; i<50; i++){
			System.out.println("This is the Main thread");
		}
	}
}
