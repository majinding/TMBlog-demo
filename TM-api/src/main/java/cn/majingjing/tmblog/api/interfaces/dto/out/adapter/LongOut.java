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

public class LongOut extends TmNomalOut<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static LongOut instance(Long value) {
		return new LongOut(value);
	}

	private LongOut(Long value) {
		super();
		this.value = value;
	}

}
