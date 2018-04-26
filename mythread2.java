package znewProduce;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class mythread2 {
	static char F = 'A';
	static int cnt=3;
	class A extends Thread {
		StringBuilder sb;
		char c;
		public A( char c, StringBuilder sb) {
			this.c = c;
			this.sb=sb;
		}
		
		@Override
		public void run() {
			int times = 0;
			for (int i = 0; i < 10;) {
				times++;
				synchronized (mythread2.this) {
					while(c != mythread2.F) {
						try {
							mythread2.this.wait();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
//					System.out.println(c + " : " + times);
					sb.append(c);
					i++;
					mythread2.F =(char)(( c+1-'A')%mythread2.cnt+'A');
					mythread2.this.notifyAll();
				}
			}
		}
		
	}

	public static void main(String[] args) {
		File file=new File("F:/javacode/znewProduce/src/znewProduce/mythread2.txt");
		StringBuilder sb=new StringBuilder();
		mythread2 t = new mythread2();
		A a = t.new A('A',sb);
		A b = t.new A('B',sb);
		A c = t.new A( 'C',sb);
		a.start();
		b.start();
		c.start();
		try {
			FileOutputStream rw=new FileOutputStream(file);
//			FileWriter rw=new FileWriter(file);
			rw.write(sb.toString().getBytes());
			rw.flush();
			rw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
