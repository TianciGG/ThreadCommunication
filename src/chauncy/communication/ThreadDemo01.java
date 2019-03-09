package chauncy.communication;

/**
 * 
 * @classDesc: 功能描述:(共享资源)
 * @author: ChauncyWang
 * @createTime: 2019年3月8日 上午12:18:17
 * @verssion: v1.0
 */
class Res {
	public String name;
	public String sex;
	// flag为false表示out线程未读取值
	public boolean flag = false;
}

/**
 * 
 * @classDesc: 功能描述:(写入线程)
 * @author: ChauncyWang
 * @createTime: 2019年3月8日 上午12:20:01
 * @verssion: v1.0
 */
class InputThread extends Thread {
	public Res res;

	public InputThread(Res res) {
		this.res = res;
	}

	@Override
	public void run() {
		int count = 0;
		while (true) {
			synchronized (res) {
				if (res.flag) {
					// 当前线程等待，wait()可以让当前线程从运行状态变为休眠状态，类似于Thread.sleep()，但是sleep和wait有本质上区别
					// wait使用在多线程之间同步 和synchronized一起用，可以释放锁，而sleep不能释放锁
					try {
						res.wait();
					} catch (InterruptedException e) {
					}
				}
				if (count == 0) {
					res.name = "ChauncyWang";
					res.sex = "male";
				} else {
					res.name = "xiaohong";
					res.sex = "female";
				}
				// 实现奇数和偶数
				count = (count + 1) % 2;
				res.flag = true;
				// 和wait一起使用，唤起另一个线程，唤醒：就是使线程从阻塞转台变成运行状态
				res.notify();
			}
		}
	}
}

/**
 * 
 * @classDesc: 功能描述:(读的线程)
 * @author: ChauncyWang
 * @createTime: 2019年3月8日 上午12:22:37
 * @verssion: v1.0
 */
class OutThread extends Thread {
	public Res res;

	public OutThread(Res res) {
		this.res = res;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (res) {
				if (!res.flag) {
					try {
						res.wait();
					} catch (InterruptedException e) {
					}
				}
				System.out.println(res.name + "----" + res.sex);
				res.flag = false;
				res.notify();
			}
		}
	}
}

/**
 * @classDesc: 功能描述:(第一个线程写入(input)用户，另一个线程读取(out)用户，实现读一个，写一个操作。)
 * @author: ChauncyWang
 * @createTime: 2019年3月8日 上午12:14:54
 * @verssion: v1.0
 */
public class ThreadDemo01 {
	public static void main(String[] args) {
		Res res = new Res();
		InputThread inputThread = new InputThread(res);
		OutThread outThread = new OutThread(res);
		inputThread.start();
		outThread.start();
	}
}
