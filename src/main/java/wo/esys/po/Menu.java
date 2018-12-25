package wo.esys.po;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 菜单
 * @author cailei
 * @date Nov 4, 2018
 */
@Entity
@Table(name="sys_menu")
public class Menu {

	/**
	 * id
	 */
	@Id
	@Column(length=50)
	private String id;
	
	/**
	 * 名称
	 */
	@Column(length = 20)
	private String name;
	
	/**
	 * 编号
	 */
	@Column(length = 30)
	private String no;
	
	/**
	 * 图标
	 */
	@Column(length=200)
	private String icon;
	
	/**
	 * URL
	 */
	@Column(length=200)
	private String url;
	
	/**
	 * 上级菜单.
	 */
	@ManyToOne
	@JoinColumn(name="parent_id")
	private Menu parent;

	/**
	 * 子菜单.
	 */
	@OneToMany(mappedBy="parent")
	private List<Menu> children = new ArrayList<Menu>();
	
	public Menu(String id, String name, String no, String icon, String url, Menu parent) {
		super();
		this.id = id;
		this.name = name;
		this.no = no;
		this.icon = icon;
		this.url = url;
		this.parent = parent;
	}

	public Menu() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Menu> getChildren() {
		Collections.sort(children, new Comparator<Menu>() {

			@Override
			public int compare(Menu o1, Menu o2) {
				return o1.getNo().compareTo(o2.getNo());
			}

		});
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	public String getParentId () {
		return parent == null ? "" : parent.getId();
	}
	
	public String getParentName() {
		return parent == null ? "" : parent.getName();
	}
	
}
