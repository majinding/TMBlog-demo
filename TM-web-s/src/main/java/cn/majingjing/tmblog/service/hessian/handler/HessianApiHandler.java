package cn.majingjing.tmblog.service.hessian.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


/**
 * @author majinding888@foxmail.com
 * @date 2016-7-11 下午3:31:12
 */
public class HessianApiHandler implements InvocationHandler {

	/**
	 * 在代理实例上处理方法调用并返回结果
	 * 
	 * @param proxy
	 *            代理类
	 * @param method
	 *            被代理的方法
	 * @param args
	 *            该方法的参数数组
	 */
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result = null;
		String callApi = null;
		Class<?>[] targetProxys = proxy.getClass().getInterfaces();
		if (targetProxys.length != 1) {
			throw new Exception("please check it : "
					+ method.getClass().getName());
		} else {
			callApi = targetProxys[0].getName() + "." + method.getName();
		}

		System.out.println("proxy:" + proxy.getClass());
		System.out.println("callApi:" + callApi);
		System.out.println("method:" + method);
		System.out.println("args:" + args);
		System.out.println("--------------------------");
		System.out.println("call api ...");
		return result;
	}

}
