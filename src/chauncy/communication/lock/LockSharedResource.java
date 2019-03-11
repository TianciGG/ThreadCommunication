package chauncy.communication.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
	// 创建重入锁Lock对象，多线程之间通讯一定要定义成单例锁，保持多线程间只有一个一样的锁
	public Lock lock = new ReentrantLock();
	// Lock锁不能使用wait，所以需要创建cdotion对象调用await方法
	Condition condition = lock.newCondition();
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
			try {
				// 获取锁的资源
				res.lock.lock();
				if (res.flag) {
					try {
						res.condition.await();
					} catch (Exception e) {
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
				res.condition.signal();
			} catch (Exception e) {
			} finally {
				// 释放锁资源，为了防止产生异常后锁无法释放，
				res.lock.unlock();
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
			try {
				res.lock.lock();
				if (!res.flag) {
					try {
						res.condition.await();
					} catch (Exception e) {
					}
				}
				System.out.println(res.name + "----" + res.sex);
				res.flag = false;
				res.condition.signal();
			} catch (Exception e) {
			} finally {
				res.lock.unlock();
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
public class LockSharedResource {
	public static void main(String[] args) {
		Res res = new Res();
		InputThread inputThread = new InputThread(res);
		OutThread outThread = new OutThread(res);
		inputThread.start();
		outThread.start();
	}
}
