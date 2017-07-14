package cn.majingjing.tmblog.api.interfaces.dto.in.adapter;

import java.util.Date;

/**
 * 
 * 
 * @author Administrator - JingjingMa
 * @Email <a href="majinding888@163.com">majinding888@163.com</a>
 * @date 2017年4月13日 下午9:59:36
 * @version 3.2
 * @DevTool Created By Eclipse 4.6.2
 *
 */

public class VoidIn extends TmNomalIn<Date> {

	public static VoidIn instance() {
		return new VoidIn();
	}

	private VoidIn() {
		this.value = new Date();
	}

}
