package wo.esys.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import wo.common.exception.WoResultCode;
import wo.common.util.WoUtil;
import wo.esys.po.Menu;
import wo.esys.service.CoreService;
import wo.esys.util.ESysConstant;
import wo.esys.vo.WoMenu;

/**
 * @author cailei
 * @date 2018年11月14日
 */
@Controller
@SessionAttributes(ESysConstant.SESSION_USER)
public class HomeController {
	
	private final static Logger LOG = LogManager.getLogger(HomeController.class);

	/**
	 * @return
	 */
	@RequestMapping("/")
	public String toMain () {
		return "main";
	}
	
	/**
	 * 当main.html加载完成之后，前端会发送请求，询问是否已经登录
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/isAuthenticated")
	public WoResultCode isAuthenticated(Map<String, Object>map) {
		return WoResultCode.getSuccessCode();
	}

	/**
	 * @param user
	 * @param password
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/authentication")
	public WoResultCode authenticate(String user, String password, Map<String, Object> map) {
		// TODO
		return WoResultCode.getSuccessCode();
	}
	
	/**
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/logout")
	public WoResultCode logout(Map<String, Object>map) {
		// TODO
		return WoResultCode.getSuccessCode();
	}
	
	@Resource
	private CoreService coreService;
	
	/**
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/getChildren")
	@ResponseBody
	public List<WoMenu> getChildren(String id, Map<String, Object> map) {
		List<Menu> dtos = coreService.findChildren(id);
		return WoMenu.getVos(dtos);
	}
}
