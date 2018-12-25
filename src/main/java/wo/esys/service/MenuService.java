package wo.esys.service;
import wo.common.entity.WoPage;
import wo.esys.po.Menu;
public interface MenuService {

	WoPage<Menu> getPageData(Integer page, Integer rows, String search);

	void create(Menu m);

}
