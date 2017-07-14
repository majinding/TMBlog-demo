package cn.majingjing.tmblog.service.hessian.handler.aop;

import java.lang.reflect.Proxy;

/**
 * @Describe:
 * 
 * @version 6.0
 * 
 * @DevTool: Created By Eclipse 4.3
 * 
 * @author: Administrator - JingjingMa
 * @Email: <a href="majinding888@163.com">majinding888@163.com</a>
 * @date: 2015-08-20 11:02:03
 * @since 1.0
 * 
 */

public class ApiFactory {

	/**
	 * 
	 * @param clazz
	 * @return
	 * 
	 * @author:    Administrator - JingjingMa
	 * @Email:     <a href="majinding888@163.com">majinding888@163.com</a>
	 * @date:      2015-08-20  13:51:01
	 */
	public static <T> T getApi(Class<T> clazz) {
		return (T) newProxyInstance(clazz);
	}

	/**
	 * 
	 * @param clazz
	 * @return
	 * 
	 * @author:    Administrator - JingjingMa
	 * @Email:     <a href="majinding888@163.com">majinding888@163.com</a>
	 * @date:      2015-08-20  13:51:10
	 */
	@SuppressWarnings({ "unchecked" })
	public static <T> T newProxyInstance(Class<T> clazz) {

		return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
				new Class[] { clazz }, new MyInvocationHandler());
	}

}
