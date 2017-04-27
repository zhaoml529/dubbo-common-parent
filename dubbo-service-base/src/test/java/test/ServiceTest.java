package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.zml.base.entity.TableEntity;
import com.zml.base.service.ISysGeneratorService;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-context.xml"})
public class ServiceTest {

	@Autowired
	private ISysGeneratorService sysGeneratorService;
	
	@Test
	public void test() {
		//Map<String, Object> params = new HashMap<String, Object>();
		Parameter<TableEntity> params = new Parameter<>();
		params.setCurrPage(1);
		params.setNumPage(10);
		Page page = this.sysGeneratorService.getTableList(params);
		System.out.println(page.getRecordList().size());
		System.out.println(JSON.toJSONString(page.getRecordList()));
	}
	
	@Test
	public void test1() throws IOException {
		//String[] tableNames = new String[]{"TB_AREA", "TB_MERCHANT"};
		String[] tableNames = new String[]{"TB_DISTRICT"};
		byte[] data = sysGeneratorService.generatorCode(tableNames);
		
		File file = new File("d:/dubbo.zip");
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(data);
		fos.close();
		System.out.println("写入成功！");
	}
	
}
