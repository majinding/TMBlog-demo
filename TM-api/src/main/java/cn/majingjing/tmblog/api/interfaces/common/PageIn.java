package cn.majingjing.tmblog.api.interfaces.common;

import cn.majingjing.tmblog.api.interfaces.common.annotation.TmApiModel;
import cn.majingjing.tmblog.api.interfaces.common.annotation.TmApiField;
import cn.majingjing.tmblog.api.interfaces.dto.in.BaseIn;

/**
 * @author majinding888@foxmail.com
 * @date 2016-7-23 下午2:00:29
 */
@TmApiModel("分页对象")
public abstract class PageIn implements BaseIn {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 分页号
	 */
	@TmApiField("分页号")
	private int page;

	public int getPage() {
		return page == 0 ? 1 : page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}
