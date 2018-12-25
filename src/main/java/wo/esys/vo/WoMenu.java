package wo.esys.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wo.esys.po.Menu;

/**
 * EasyUI的菜单数据dto，对应EasyUI的tree控件的node数据，具体参数参考：http://www.jeasyui.com/documentation/index.php中的tree-Events中的说明.
 * 
 * @author cailei
 *
 */
public class WoMenu {

	public static final String STATE_OPEN = "open";

	public static final String STATE_CLOSED = "closed";

	/**
	 * An identity value bind to the node.
	 */
	private String id;

	/**
	 * Text to be showed.
	 */
	private String text;

	/**
	 * The css class to display icon.
	 */
	private String iconCls;

	/**
	 * The node state, 'open' or 'closed'.
	 */
	private String state = STATE_OPEN;

	/**
	 * Whether the node is checked.
	 */
	private Boolean checked;

	/**
	 * custom attributes can be added to a node.
	 */
	private Map<String, Object> attributes = new HashMap<String, Object>();
	
	public WoMenu(Menu m) {
		this.id = m.getId();
		this.text = m.getName();
		this.iconCls = m.getIcon();
		if (m.getParent() == null) {
			this.state = STATE_CLOSED;
		}
		attributes.put("url", m.getUrl());
	}
	
	public WoMenu(String id, String text, String iconCls, Boolean hasChildren, String url) {
		this.id = id;
		this.text = text;
		this.iconCls = iconCls;
		if (hasChildren) {
			this.state = STATE_CLOSED;
		}
		attributes.put("url", url);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	public static List<WoMenu> getVos (List<Menu> pos) {
		List<WoMenu> vos = new ArrayList<WoMenu>();
		for (Menu po : pos) {
			vos.add(new WoMenu (po));
		}
		return vos;
	}
}
