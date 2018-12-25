package wo.esys.vo;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import wo.common.entity.WoPage;

/**
 * EasyUI-datagrid控件需要的数据对象.
 * @author cailei
 * @date 2018年11月14日
 * @param <T>
 */
public class WoGrid<T> {
	
	private final static Logger LOG = LogManager.getLogger(WoGrid.class);
	
	private Long total;
	
	private List<T> rows;

	public WoGrid(Long total, List<T> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	
	public WoGrid(WoPage<T> page) {
		super();
		this.total = page.getResults();
		this.rows = page.getRows();
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
