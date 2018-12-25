package wo.esys.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import wo.esys.mapper.MenuMapper;
import wo.esys.po.Menu;
import wo.esys.service.CoreService;

@Transactional
@Service
public class CoreServiceImpl implements CoreService {

	@Resource
	private MenuMapper menuMapper;
	
	@Override
	public List<Menu> findChildren(String parentId) {
		return menuMapper.findAllByParentId(parentId);
	}
	
}
