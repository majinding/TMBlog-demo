package cn.majingjing.tmblog.api.interfaces.common.auth;

/**
 * 
 * 
 * @author Administrator - JingjingMa
 * @Email <a href="majinding888@foxmail.com">majinding888@foxmail.com</a>
 * @date 2017年4月22日 下午6:29:57
 * @version 3.2
 * @DevTool Created By Eclipse 4.6.2
 *
 */

public class ApiParameterAuth {

	private String signature;// 加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
	private String timestamp;// 时间戳
	private String nonce;// 随机数
	private String echostr;// 随机字符串

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getEchostr() {
		return echostr;
	}

	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}

}
