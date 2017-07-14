package cn.majingjing.tmblog.api.interfaces.dto.in.adapter;

/**
 * 
 * 
 * @author Administrator - JingjingMa
 * @Email <a href="majinding888@163.com">majinding888@163.com</a>
 * @date 2017年4月13日 下午9:43:55
 * @version 3.2
 * @DevTool Created By Eclipse 4.6.2
 *
 */

public class IntegerIn extends TmNomalIn<Integer> {

	public static IntegerIn instance(Integer value) {
		return new IntegerIn(value);
	}

	private IntegerIn() {
	}

	private IntegerIn(Integer value) {
		super();
		this.value = value;
	}

}
