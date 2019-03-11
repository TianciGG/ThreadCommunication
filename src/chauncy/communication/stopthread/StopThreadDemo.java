package chauncy.communication.stopthread;

class StopThread extends Thread {
	// flag为true，线程一直在运行状态，为false,停止线程
	public boolean flag = true;

	@Override
	public synchronized void run() {
		while (flag) {
			try {
				wait();
			} catch (Exception e) {
				StopThread();
			}
			System.out.println(Thread.currentThread().getName() + "---我是子线程");
		}
	}

	/**
	 * 
	 * @methodDesc: 功能描述(停止线程)
	 * @author: ChauncyWang
	 * @param:
	 * @createTime: 2019年3月11日 下午2:20:37
	 * @returnType: void
	 */
	public void StopThread() {
		flag = false;
		System.out.println(getName() + "------线程已经停止");
	}
}

/**
 * 一般使用多线程的时候run方法会一直在执行，大多数会使用for、while循环
 * 
 * @classDesc: 功能描述(使用退出标识停止线程，没有根本解决问题，因为run方法使用同步函数并且wait,线程虽然死掉了但是还在等待着唤醒)
 * @author: ChauncyWang
 * @createTime: 2019年3月11日 下午2:11:15
 * @version: 1.0
 */
public class StopThreadDemo {
	public static void main(String[] args) {
		StopThread stopThread1 = new StopThread();
		StopThread stopThread2 = new StopThread();
		stopThread1.start();
		stopThread2.start();
		for (int i = 0; i < 30; i++) {
			try {
				Thread.sleep(10);
			} catch (Exception e) {

			}
			System.out.println("main ....." + i);
			if (i == 29) {
				// stopThread1.StopThread();
				// stopThread2.StopThread();
				stopThread1.interrupt();
				stopThread2.interrupt();
			}
		}
	}
}
