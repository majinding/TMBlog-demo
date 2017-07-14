package cn.majingjing.tmblog.api.interfaces.common.auth;

/**
 * 
 * 
 * @author Administrator - JingjingMa
 * @Email <a href="majinding888@163.com">majinding888@163.com</a>
 * @date 2017年4月19日 下午6:35:49
 * @version 3.2
 * @DevTool Created By Eclipse 4.6.2
 *
 */

public class ApiResponseAuth {
	private int status = 200;
	private String msg;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
