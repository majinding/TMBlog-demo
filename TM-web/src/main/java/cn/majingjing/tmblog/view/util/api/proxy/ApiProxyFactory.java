package cn.majingjing.tmblog.view.util.api.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Iterator;

import cn.majingjing.tmblog.view.util.api.handler.HessianInvocationHandler;
import cn.majingjing.tmblog.view.util.api.handler.RestfulInvocationHandler;
import cn.majingjing.tmblog.view.util.api.handler.TMSubInvocationHandler;
import cn.majingjing.tmblog.view.util.api.scan.ApiScan;
import cn.majingjing.tmblog.view.util.spring.SpringBeanUtils;
import cn.majingjing.tmblog.view.util.spring.SpringRootContext;
import org.springframework.context.ApplicationContext;

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

public class ApiProxyFactory {

	/**
	 * 
	 * @param clazz
	 * @return
	 * 
	 * @author: Administrator - JingjingMa
	 * @Email: <a href="majinding888@163.com">majinding888@163.com</a>
	 * @date: 2015-08-20 13:51:01
	 */
	public static <T> T getApi(Class<T> clazz, InvocationHandler handler) {
		return (T) newProxyInstance(clazz, handler);
	}

	/**
	 * 
	 * @param clazz
	 * @return
	 * 
	 * @author: Administrator - JingjingMa
	 * @Email: <a href="majinding888@163.com">majinding888@163.com</a>
	 * @date: 2015-08-20 13:51:01
	 */
	public static <T> T getRestfulApi(Class<T> clazz) {
		return (T) newProxyInstance(clazz, new RestfulInvocationHandler());
	}

	/**
	 * 
	 * @param clazz
	 * @return
	 * 
	 * @author: Administrator - JingjingMa
	 * @Email: <a href="majinding888@163.com">majinding888@163.com</a>
	 * @date: 2015-08-20 13:51:01
	 */
	public static <T> T getHessianApi(Class<T> clazz) {
		return (T) newProxyInstance(clazz, new HessianInvocationHandler());
	}

	/**
	 * 
	 * @param clazz
	 * @return
	 * 
	 * @author: Administrator - JingjingMa
	 * @Email: <a href="majinding888@163.com">majinding888@163.com</a>
	 * @date: 2015-08-20 13:51:10
	 */
	@SuppressWarnings({ "unchecked" })
	private static <T> T newProxyInstance(Class<T> clazz,
			InvocationHandler handler) {

		return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
				new Class[] { clazz }, handler);
	}
	
	/**
	 * 初始化API代理工厂
	 * 
	 * @author majinding888@foxmail.com
	 * @date 2016-10-10 下午3:39:40
	 * @param apiScan
	 */
	public static void init(ApiScan apiScan){
		ApiProxyFactory.apiScan = apiScan;
	}
	
	/**
	 * 注册api
	 * 
	 * @author majinding888@foxmail.com
	 * @date 2016-10-10 下午3:39:58
	 */
	public static void registerApi(){
		ApplicationContext ctx = SpringRootContext.getContext();
		Iterator<Class<?>> it = apiScan.scan().iterator();
		while (it.hasNext()) {
			Class<?> clz = it.next();
			System.out.println(clz);
			InvocationHandler handler = new TMSubInvocationHandler();//apiScan.getHandler()
			Object bean = getApi(clz, handler);
			SpringBeanUtils.registerSpringBean(ctx, clz.getSimpleName(), bean);
		}
	}
	
	/**
	 * 注销api
	 * 
	 * @author majinding888@foxmail.com
	 * @date 2016-10-10 下午3:40:10
	 */
	public static void destroyApi(){
		if(null == apiScan){
			throw new RuntimeException("未指明[ApiScan]的处理方式,无法销毁API.");
		}
		ApplicationContext ctx = SpringRootContext.getContext();
		Iterator<Class<?>> it = apiScan.scan().iterator();
		while (it.hasNext()) {
			Class<?> clz = it.next();
			System.out.println(clz);
			SpringBeanUtils.removeSpringBean(ctx, clz.getSimpleName());
		}
	}
	
	private static ApiScan apiScan;

}
