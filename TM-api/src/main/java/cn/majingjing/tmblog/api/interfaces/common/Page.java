package cn.majingjing.tmblog.api.interfaces.common;

import java.io.Serializable;

import cn.majingjing.tmblog.api.interfaces.common.annotation.TmApiModel;
import cn.majingjing.tmblog.api.interfaces.common.annotation.TmApiField;

/**
 * @Describe: 分页对象
 * 
 * @version 6.0
 * 
 * @DevTool: Created By Eclipse 4.3
 * 
 * @author: Administrator - JingjingMa
 * @Email: <a href="majinding888@163.com">majinding888@163.com</a>
 * @date: 2014-7-30 下午12:36:44
 * @since 1.0
 * 
 */
@TmApiModel("分页对象")
public class Page<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 每页显示记录数(默认为10) */
	@TmApiField(value = "每页显示记录数(默认为10)", required = true)
	private int pageSize = 10;

	/** 当前页数(默认为1) */
	@TmApiField(value = "当前页数(默认为1)", required = true)
	private int currentPage = 1;

	/** 总页数 */
	@TmApiField(value = "总页数", required = true)
	private int totalPage;

	/** 总记录数 */
	@TmApiField(value = "总记录数", required = true)
	private int totalCount;

	//@TmApiField(value = "实际包裹对象", required = false)
	private T obj;

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

	/**
	 * 计算总页数
	 * 
	 * @param totalCount
	 *            总记录数
	 * @param pageSize
	 *            每页显示记录数
	 * @return int 总页数
	 */
	public int computeTotalPage(final int totalCount, final int pageSize) {
		return totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
	}

	/**
	 * 计算开始页
	 * 
	 * @param currentPage
	 *            当前页数
	 * @param pageSize
	 *            每页显示记录数
	 * @return int 开始页
	 */
	public int computeStartPage(final int currentPage, final int pageSize) {
		return pageSize * (currentPage - 1);
	}

	/**
	 * 计算结束页
	 * 
	 * @param currentPage
	 *            当前页数
	 * @param pageSize
	 *            每页显示记录数
	 * @return int 结束页
	 */
	public int computeEndPage(final int currentPage, final int pageSize) {
		return pageSize * currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

}
