package wo.esys.controller;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import wo.common.entity.WoPage;
import wo.common.exception.WoResultCode;
import wo.esys.po.Menu;
import wo.esys.service.MenuService;
import wo.esys.vo.WoGrid;

/**
 * @author cailei
 * @date 2018年11月14日
 */
@Controller
@RequestMapping("/esys/menu")
public class MenuController {
	private final static Logger LOG = LogManager.getLogger(MenuController.class);
	
	/**
	 * @return
	 */
	@GetMapping
	public String toMain () {
		return "esys/menu";
	}
	
	@Resource
	private MenuService menuService;
	
	@RequestMapping("/list")
	@ResponseBody
	WoGrid<Menu> list (Integer page, Integer rows, String search)	{
		WoPage<Menu> pg = menuService.getPageData(page, rows, search);
		WoGrid<Menu> grid = new WoGrid<>(pg.getResults(), pg.getRows());
		return grid;
	}
	
	@RequestMapping("/create")
	@ResponseBody
	WoResultCode create(Menu m) {
		menuService.create(m);
		return WoResultCode.getSuccessCode().setMsg("创建菜单成功！");
	}
	
	
}
