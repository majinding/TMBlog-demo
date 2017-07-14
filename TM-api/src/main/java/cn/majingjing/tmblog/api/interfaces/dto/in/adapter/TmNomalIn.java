package cn.majingjing.tmblog.api.interfaces.dto.in.adapter;

import cn.majingjing.tmblog.api.interfaces.dto.in.BaseIn;

/**
 * 
 * 
 * @author Administrator - JingjingMa
 * @Email <a href="majinding888@foxmail.com">majinding888@foxmail.com</a>
 * @date 2017年5月30日 下午8:55:36
 * @version 3.2
 * @DevTool Created By Eclipse 4.6.2
 *
 */

public abstract class TmNomalIn<T> implements BaseIn {

	protected T value;
	
	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
}
