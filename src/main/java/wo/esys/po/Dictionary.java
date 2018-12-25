package wo.esys.po;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * 数据字典
 * @author cailei
 * @date Nov 4, 2018
 */
@Entity
@Table(name="sys_dictionary")
public class Dictionary  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6668800196790039529L;

	private final static Logger LOG = LogManager.getLogger(Dictionary.class);

	/**
	 * ID
	 */
	@Id
	@Column(length = 50)
	// @GeneratedValue(generator = "uuid")
	// @GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;
	
	/**
	 * 类型
	 */
	@Column(length = 50)
	private String dicType;
	
	/**
	 * 值
	 */
	@Column(length = 50)
	private String val;

	/**
	 * 键
	 */
	@Column(length = 50)
	private String name;
	
	/**
	 * 描述
	 */
	@Column(length = 100)
	private String description;

	/**
	 * 参数
	 */
	@Column(length = 500)
	private String params;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDicType() {
		return dicType;
	}

	public void setDicType(String dicType) {
		this.dicType = dicType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

}
