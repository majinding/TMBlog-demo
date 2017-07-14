package cn.majingjing.tmblog.api.interfaces.dto.out.adapter;

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

public class IntegerOut extends TmNomalOut<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static IntegerOut instance(Integer value) {
		return new IntegerOut(value);
	}

	private IntegerOut(Integer value) {
		super();
		this.value = value;
	}

}
