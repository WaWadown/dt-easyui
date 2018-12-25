package wo.esys.init;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import wo.esys.mapper.MenuMapper;
import wo.esys.po.Menu;

/**
 * @author cailei
 * @date Nov 4, 2018
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ESysInitializer {
	
	private final static Logger LOG = LogManager.getLogger(ESysInitializer.class);

	@Resource
	private MenuMapper menuMapper;

	@Test
	@Rollback(value = false)
	@Transactional
	public void save() {
		Menu esys = new Menu ("esys", "系统管理", "00", null, null, null);
		menuMapper.create(esys);
		Menu menu = new Menu ("esys-menu", "菜单", "0010", null, "esys/menu", esys);
		menuMapper.create(menu);
	}

}
