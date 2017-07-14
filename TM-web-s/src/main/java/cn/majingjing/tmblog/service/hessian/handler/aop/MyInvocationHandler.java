package cn.majingjing.tmblog.service.hessian.handler.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import cn.majingjing.tmblog.service.core.util.spring.SpringContext;

/**
 * @Describe:
 * 
 * @version 6.0
 * 
 * @DevTool: Created By Eclipse 4.3
 * 
 * @author: Administrator - JingjingMa
 * @Email: <a href="majinding888@163.com">majinding888@163.com</a>
 * @date: 2015-08-20 12:00:36
 * @since 1.0
 * 
 */
@SuppressWarnings("all")
public class MyInvocationHandler implements InvocationHandler {

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object,
	 *      java.lang.reflect.Method, java.lang.Object[])
	 * 
	 * @author: Administrator - JingjingMa
	 * @Email: <a href="majinding888@163.com">majinding888@163.com</a>
	 * @date: 2015-08-20 13:50:51
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
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
		Object temp = null;// commonApi.call(callApi, args);
		temp = exec(targetProxys[0].getName(), proxy, method.getName(), args);

		System.out.println("--------------------------");

		return temp;
	}

	private Object exec(String className, Object target, String method,
			Object[] args) throws Exception {
		Class clazz = Class.forName(className);
		Object t = SpringContext.getBean("hello");
		Class[] pargs = null;
		if (null != args) {
			pargs = new Class[args.length];
			for (int i = 0; i < args.length; i++) {
				pargs[i] = args[i].getClass();
			}
		}
		Method m1 = null;
		m1 = clazz.getMethod(method, pargs);
		Object obj = m1.invoke(t, args);

		return obj;
	}
}