package cn.majingjing.tmblog.view.util.api.bean;

import cn.majingjing.tmblog.api.interfaces.common.ApiTools;

/**
 * 
 * 
 * @author Administrator - JingjingMa
 * @Email <a href="majinding888@163.com">majinding888@163.com</a>
 * @date 2017年4月18日 下午10:15:17
 * @version 3.2
 * @DevTool Created By Eclipse 4.6.2
 *
 */

public class CallApiDescriptor {

	private String api;
	private String name;
	private String method;
	private Class<?> paramterType;
	private Class<?> returnType;
	private String paramterTypeName;
	private String returnTypeName;

	public CallApiDescriptor(String api) throws ClassNotFoundException {
		this.api = api;
		this.name = ApiTools.getName(api);
		this.method = ApiTools.getMethod(api);
		this.paramterType = ApiTools.getParamterType(api);
		this.returnType = ApiTools.getReturnType(api);
		this.paramterTypeName = ApiTools.getParamterTypeName(api);
		this.returnTypeName = ApiTools.getReturnTypeName(api);
	}

	public String getApi() {
		return api;
	}

	public String getName() {
		return name;
	}

	public String getMethod() {
		return method;
	}

	public Class<?> getParamterType() {
		return paramterType;
	}

	public Class<?> getReturnType() {
		return returnType;
	}

	public String getParamterTypeName() {
		return paramterTypeName;
	}

	public String getReturnTypeName() {
		return returnTypeName;
	}

	@Override
	public String toString() {
		return "CallApiDescriptor [api=" + api + ", name=" + name + ", method=" + method + ", paramterType="
				+ paramterType + ", returnType=" + returnType + ", paramterTypeName=" + paramterTypeName
				+ ", returnTypeName=" + returnTypeName + "]";
	}

}
