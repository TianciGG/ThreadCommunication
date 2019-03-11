package chauncy.communication.join;

/**
 * @classDesc: 功能描述(有T1、T2、T3三个线程，如何怎样保证T2在T1执行完后执行，T3在T2执行完后执行)
 * @author: ChauncyWang
 * @createTime: 2019年3月11日 下午4:01:35
 * @version: 1.0
 */
public class JoinPractice {
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 30; i++) {
					System.out.println(Thread.currentThread().getName() + "-------i:" + i);
				}
			}
		}, "t1");
		t1.start();
		t1.join();
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 30; i++) {
					System.out.println(Thread.currentThread().getName() + "-------i:" + i);
				}
			}
		}, "t2");
		t2.start();
		t2.join();
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 30; i++) {
					System.out.println(Thread.currentThread().getName() + "-------i:" + i);
				}
			}
		}, "t3");
		t3.start();
		t3.join();
	}
}
