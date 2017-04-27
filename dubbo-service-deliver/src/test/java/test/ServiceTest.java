package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.dili.deliver.entity.Area;
import com.dili.deliver.service.IAreaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-context.xml"})
public class ServiceTest {

	@Autowired
	private IAreaService areaService;
	
	@Test
	public void test() {
		Area area = this.areaService.getById(7L);
		System.out.println(JSON.toJSON(area));
	}
}
