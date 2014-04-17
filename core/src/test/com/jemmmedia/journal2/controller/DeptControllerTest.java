/**
 * 
 */
package test.com.jemmmedia.journal2.controller;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.jemmmedia.organise.service.impl.controller.DeptController;
import com.jemmmedia.organise.service.impl.db.mybatis.model.Dept;
import com.jemmmedia.organise.service.impl.service.DeptService;

/**
 * @author hgill
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class DeptControllerTest {
	
	@Mock
	DeptService deptService;
	
	DeptController deptController;
	
	@Before
	public void setup(){
		deptController = new DeptController();
	}
	
	/**
	 * Test method for {@link com.jemmmedia.organise.service.impl.controller.DeptController#addDept(org.springframework.ui.Model, com.jemmmedia.organise.service.impl.db.mybatis.model.Dept)}.
	 */
	@Test
	public void testAddDept() {
		Dept dept = new Dept();
		dept.setId((long) 10);
		dept.setName("name123");
		dept.setCreatedAt(new Date());
		dept.setGroupId((long) 1);
		deptService.insertSelective(dept);
		List<Dept> list = deptService.selectAllDepts(1);
		System.out.println(list.size());
		verify(deptService.insertSelective(dept)).equals(dept);
			
		//assertTrue(list.size()>0);
		
	}

	/**
	 * Test method for {@link com.jemmmedia.organise.service.impl.controller.DeptController#editRegion(org.springframework.ui.Model, com.jemmmedia.organise.service.impl.db.mybatis.model.Dept)}.
	 */
	@Test
	public void testEditRegion() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.jemmmedia.organise.service.impl.controller.DeptController#deleteRegion(org.springframework.ui.Model, long)}.
	 */
	@Test
	public void testDeleteRegion() {
		fail("Not yet implemented");
	}

}
