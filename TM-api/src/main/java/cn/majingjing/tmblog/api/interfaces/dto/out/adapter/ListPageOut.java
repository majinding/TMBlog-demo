package cn.majingjing.tmblog.api.interfaces.dto.out.adapter;

import java.io.Serializable;
import java.util.List;

import cn.majingjing.tmblog.api.interfaces.common.annotation.TmApiField;
import cn.majingjing.tmblog.api.interfaces.common.ApiPage;

/**
 * 
 * 
 * @author Administrator - JingjingMa
 * @Email <a href="majinding888@163.com">majinding888@163.com</a>
 * @date 2017年4月15日 下午4:34:27
 * @version 3.2
 * @DevTool Created By Eclipse 4.6.2
 *
 */

public class ListPageOut<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static <T> ListPageOut<T> instance(ApiPage page, List<T> list) {
		return new ListPageOut<T>(page, list);
	}

	private ListPageOut() {
	}

	private ListPageOut(ApiPage page, List<T> list) {
		this.page = page;
		this.list = list;
	}

	@TmApiField(value = "分页", required = true)
	private ApiPage page;

	@TmApiField(value = "列表", required = true)
	private List<T> list;

	public ApiPage getPage() {
		return page;
	}

	public void setPage(ApiPage page) {
		this.page = page;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "ListPageOut [page=" + page + ", list=" + list + "]";
	}

}
