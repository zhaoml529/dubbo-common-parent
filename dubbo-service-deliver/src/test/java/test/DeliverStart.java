package test;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DeliverStart {
	private static final Log log = LogFactory.getLog(DeliverStart.class);
	
	public static void main(String[] args) {
		
		try {
			ClassPathXmlApplicationContext xmlContext=new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
			xmlContext.start();
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("== DubboProvider context start error:",e);
		}
		synchronized (DeliverStart.class) {
			while (true) {
				try {
					DeliverStart.class.wait();
				} catch (InterruptedException e) {
					log.error("== synchronized error:",e);
				}
			}
		}
	}
}
