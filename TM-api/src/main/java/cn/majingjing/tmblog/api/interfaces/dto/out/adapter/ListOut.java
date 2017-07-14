package cn.majingjing.tmblog.api.interfaces.dto.out.adapter;

import java.io.Serializable;
import java.util.List;

import cn.majingjing.tmblog.api.interfaces.common.annotation.TmApiField;

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

public class ListOut<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static <T> ListOut<T> instance(List<T> list){
		return new ListOut<>(list);
	}
	
	private ListOut() {
	}
	
	private ListOut(List<T> list) {
		this.list = list;
	}

	/**
	 * 列表
	 */
	@TmApiField(value = "列表", required = true)
	private List<T> list;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
