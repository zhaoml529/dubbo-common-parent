package test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zml.log.entity.SystemExceptionLog;
import com.zml.log.service.ISystemExceptionLogService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-context.xml"})
public class ServiceTest {

	@Autowired
	private ISystemExceptionLogService systemLogService;
	
	@Test
	public void test() {
		List<SystemExceptionLog> list = this.systemLogService.getList();
		System.out.println(list.size());
	}
}
