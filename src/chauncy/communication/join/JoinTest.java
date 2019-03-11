package chauncy.communication.join;

class ThreadJoin extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 40; i++) {
			try {
				Thread.sleep(30);
			} catch (Exception e) {
			
			}
			System.out.println(getName() + "---------i:" + i);
		}
	}
}

/**
 * @classDesc: 功能描述(Join Demo)
 * @author: ChauncyWang
 * @createTime: 2019年3月11日 下午3:41:43
 * @version: 1.0
 */
public class JoinTest {
	public static void main(String[] args) throws InterruptedException {
		ThreadJoin threadJoin1 = new ThreadJoin();
		ThreadJoin threadJoin2 = new ThreadJoin();
		threadJoin1.start();
		threadJoin1.join();//让其他线程等待，只有当前线程执行完毕，才会释放资格，如果传入参数则根据传入参数的时间大小来释放资格
		threadJoin2.start();
		for (int i = 0; i < 40; i++) {
			System.out.println("main---------i:" + i);
		}
	}
}
