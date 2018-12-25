package wo.esys.service;

import java.util.List;

import wo.esys.po.Menu;

public interface CoreService {

	List<Menu> findChildren (String parentId);
	
}
