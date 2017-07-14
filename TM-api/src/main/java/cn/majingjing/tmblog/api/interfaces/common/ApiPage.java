package cn.majingjing.tmblog.api.interfaces.common;

import cn.majingjing.tmblog.api.interfaces.common.annotation.TmApiField;
import cn.majingjing.tmblog.api.interfaces.dto.in.BaseIn;

/**
 * 
 * 
 * @author Administrator - JingjingMa
 * @Email <a href="majinding888@163.com">majinding888@163.com</a>
 * @date 2017年4月15日 下午5:00:05
 * @version 3.2
 * @DevTool Created By Eclipse 4.6.2
 *
 */

public abstract class ApiPage implements BaseIn {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@TmApiField("分页号")
	private int pageNo = 1;

	@TmApiField("每页条数")
	private int pageSize = 10;

	@TmApiField(value = "总记录数", required = false)
	private int totalCount;

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPage() {
		return totalCount / pageSize + ((totalCount % pageSize == 0) ? 0 : 1);
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public String toString() {
		return "ApiPage [pageNo=" + pageNo + ", pageSize=" + pageSize + ", totalCount=" + totalCount + ", getTotalPage()=" + getTotalPage() + "]";
	}

}
