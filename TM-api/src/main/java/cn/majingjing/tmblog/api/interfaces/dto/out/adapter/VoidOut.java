package cn.majingjing.tmblog.api.interfaces.dto.out.adapter;

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

public class VoidOut extends TmNomalOut<Date> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static VoidOut instance() {
		return new VoidOut();
	}

	private VoidOut() {
		this.value = new Date();
	}

}
