package wo.esys.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import wo.common.entity.WoPage;
import wo.common.util.WoUtil;
import wo.esys.mapper.MenuMapper;
import wo.esys.po.Menu;
import wo.esys.service.MenuService;
@Service
@Transactional
public class MenuServiceImpl implements MenuService{
	private static Logger LOG = LogManager.getLogger(MenuServiceImpl.class);

	@Resource
	private MenuMapper menuMapper;
	@Override
	public WoPage<Menu> getPageData(Integer page, Integer rows, String search) {
		PageHelper.startPage(page, rows, "m.no");
		Page<Menu> pg = (Page<Menu>)menuMapper.findAllBySearch(search);
		return new WoPage<>(pg.getResult(), pg.getTotal());
	}
	
	@Override
	public void create(Menu m) {
		if(WoUtil.isEmpty(m.getId())) {
			m.setId(WoUtil.uuid());
		}
		if(m.getParent() != null && "".equals(m.getParent().getId())) {
			m.setParent(null);
		}
		menuMapper.create(m);
	}
}
