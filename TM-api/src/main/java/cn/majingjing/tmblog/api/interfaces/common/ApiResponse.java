package cn.majingjing.tmblog.api.interfaces.common;

/**
 * @Describe:
 * 
 * @DevTool: Spring Tool Suite 3.7.2.RELEASE
 * 
 * @author: Administrator - JingjingMa
 * @Email: <a href="majinding888@163.com">majinding888@163.com</a>
 * @date: 2016年1月27日 上午9:19:01
 * @since 1.0
 *
 */

public class ApiResponse<T>  implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int status = 200;
	private String msg;
	private T obj;

	
	
	public int getStatus() {
		return status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(int status,String msg) {
		this.status = status;
		this.msg = msg;
	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

}
