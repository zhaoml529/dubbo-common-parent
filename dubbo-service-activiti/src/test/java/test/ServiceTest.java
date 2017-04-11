package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zml.activiti.service.IProcessModelService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-context.xml"})
public class ServiceTest {

	@Autowired
	private IProcessModelService processModelService;
	
	@Test
	public void test() {
		System.out.println(this.processModelService);
	}
}
