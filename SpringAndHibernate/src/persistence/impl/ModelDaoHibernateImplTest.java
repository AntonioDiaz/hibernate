package persistence.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import persistence.ModelDao;
import model.Model;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "applicationContext.xml")
public class ModelDaoHibernateImplTest {

	@Autowired
	ModelDao modelDao;
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Before
	public void before() {
		jdbcTemplate.execute("delete from model");
		jdbcTemplate.execute("delete from model_type");
		jdbcTemplate.execute("insert into model_type (id,name,deleted) values ('T1','Model Type 1',false)");
		jdbcTemplate.execute("insert into model (id,name,deleted,model_type_id) values (1,'Model 1',false,'T1')");
		jdbcTemplate.execute("insert into model (id,name,deleted,model_type_id) values (2,'Model 2',true,'T1')");
		jdbcTemplate.execute("insert into model (id,name,deleted,model_type_id) values (3,'Model 3',false,'T1')");
	}

	@Test
	public void findHqlTest() {
		List<Model> models = modelDao.find(true);
		System.out.println("\nfindHqlTest print list");
		printList(models);
		Assert.assertEquals(models.size(), 1);
	}

	@Test
	public void findSqlTest() {
		List<Model> models = modelDao.find("odel");
		System.out.println("\nfindSqlTest print list");
		printList(models);
		Assert.assertEquals(models.size(), 3);
	}

	@Test
	public void findCriteriaTest() {
		List<Model> models = modelDao.find("2", true);
		System.out.println("\nfindCriteriaTest print list");
		printList(models);
		Assert.assertEquals(models.size(), 1);
	}

	private void printList(List<Model> models) {
		for (Model model : models)
			System.out.println(model.getId() + " - " + model.getName() + " - " + model.getDeleted());
	}

}