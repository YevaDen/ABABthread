package znewProduce;

public class mythread {

	public static void main(String[] args) {
		A ta = new A();	ta.start();	
		B tb = new B();		tb.start();
		for (int i = 0; i < 20;i++) {
			synchronized (ta) {
				if (A.F) {
					System.out.println("A\t");
					ta.aChange();
				} else {
				System.out.println("B\t");
				tb.bChange();
				}
			}
		}
	}
}

class A extends Thread {
	static boolean F = true;
	public A() {
		aChange();
	}
	synchronized void aChange() {
		for(int i =0; i<10;i++) {
			F = false;
		try {
			sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		}	
	}
}

class B extends Thread {
	public B() {
		bChange();
	}

	synchronized void bChange() {
		for(int i =0; i<10;i++) {
		A.F = true;
		try {
			sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		}	
	}
}
