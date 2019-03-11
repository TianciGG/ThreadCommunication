package chauncy.communication.daemon;

/**
 * 线程分为两种,一种是用户线程(前台线程)，一种是守护线程(后台线程)
 * 守护线程：主线程或jvm进程挂了，守护线程也会被停止掉。gc也是守护线程   
 * @classDesc: 功能描述(守护线程)  
 * @author: ChauncyWang
 * @createTime: 2019年3月11日 下午2:45:31   
 * @version: 1.0  
 */  
public class DaemonThread {
	
	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 100; i++) {
					try {
						Thread.sleep(10);
					} catch (Exception e) {
						
					}
					System.out.println("我是子线程------i:"+i);
				}
			}
		});
		//标识为守护线程
		thread.setDaemon(true);
		thread.start();
		for (int i = 0; i < 30; i++) {
			try {
				Thread.sleep(10);
			} catch (Exception e) {
			
			}
			System.out.println("我是主线程------i:"+i);
		}
		System.out.println("主线程执行完毕");
	}
}
