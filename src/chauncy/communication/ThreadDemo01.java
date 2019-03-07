package chauncy.communication;

/**
 * 
 * @classDesc: 功能描述:(共享资源)
 * @author: ChauncyWang
 * @createTime: 2019年3月8日 上午12:18:17
 * @verssion: v1.0
 */
class Res{
	public String name;
	public String sex;
}

/**
 * 
 * @classDesc: 功能描述:(写入线程)
 * @author: ChauncyWang
 * @createTime: 2019年3月8日 上午12:20:01
 * @verssion: v1.0
 */
class InputThread extends Thread{
	int count = 0;
	@Override
	public void run() {
		while(true){
			if(count==0){
				
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
class OutThread extends Thread{
	@Override
	public void run() {
		
	}
}

/**
 * @classDesc: 功能描述:(第一个线程写入(input)用户，另一个线程读取(out)用户，实现读一个，写一个操作。)
 * @author: ChauncyWang
 * @createTime: 2019年3月8日 上午12:14:54
 * @verssion: v1.0
 */
public class ThreadDemo01 {

}
