package cn.majingjing.tmblog.service.hessian.handler;

import java.lang.reflect.Proxy;

/**
 * @author majinding888@foxmail.com
 * @date 2016-7-11 下午3:30:42
 */
public class HessianProxy {

	public static <T> T getProxy(Class<T> clz) {
		return (T) Proxy.newProxyInstance(clz.getClassLoader(),
				new Class[] { clz }, new HessianApiHandler());
	}

}
