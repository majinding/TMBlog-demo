package cn.majingjing.tmblog.view.util.api.handler;

import cn.majingjing.core.cache.TMCache;
import cn.majingjing.core.tool.SysPropertiesUtils;
import cn.majingjing.core.tool.Tools;
import cn.majingjing.tmblog.api.interfaces.common.ApiRequest;
import cn.majingjing.tmblog.api.interfaces.dto.in.BaseIn;
import cn.majingjing.tmblog.api.interfaces.dto.in.adapter.VoidIn;
import cn.majingjing.tmblog.view.util.api.bean.CallApiDescriptor;
import cn.majingjing.tmblog.view.util.cache.CacheName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * @author majinding888@foxmail.com
 * @date 2016-7-15 下午5:25:05
 */
public abstract class AbstractInvocationHandler implements InvocationHandler {

	private static Logger log = LoggerFactory.getLogger(AbstractInvocationHandler.class);
	private List<String> defaultMethod = Arrays.asList("toString", "equals");
	protected ApiRequest<BaseIn> req = new ApiRequest<BaseIn>();
	String plantId = "xxx";

	protected abstract Object invokeApi(CallApiDescriptor api, Object args) throws Exception;

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object rtn = null;

		String callApi = null;
		Class<?>[] targetProxys = proxy.getClass().getInterfaces();
		String methodName = method.getName();
		if (targetProxys.length != 1) {
			throw new Exception("please check it : " + method.getClass().getName());
		} else if (defaultMethod.contains(methodName)) {
			// 暂时不支持toString的方法调用
			// throw new Exception("暂时不支持toString的方法 : "
			// + method.getClass().getName());
			return Tools.toStrings(
					"this is [{}] and was proxied by [{}] , so it was not method:[{}] \t[\u4f5c\u8005\uff1a\u9a6c\u6676\u6676]",
					targetProxys[0].getName(), proxy.getClass(), methodName);
		} else {
			callApi = targetProxys[0].getName() + "." + method.getName();
		}

		log.debug("当前调用方式[1=hessian,2=rest]  {}", SysPropertiesUtils.getProp("REMOTE_CALL"));
		log.debug("proxy:{}", proxy.getClass());
		log.debug("callApi:{}", callApi);
		log.debug("method:{}", method);
		log.debug("args:{}", Arrays.toString(args));
		// log.debug("--------------------------");

		CallApiDescriptor callApiDescriptor = new CallApiDescriptor(callApi);
		if (callApiDescriptor.getParamterType() == null || args.length == 0) {
			args[0] = VoidIn.instance();
		}
		rtn = invokeApi(callApiDescriptor, args[0]);
		// log.debug("--------------------------");

		return rtn;
	}

	/**
	 * 获取方法的参数类型
	 * 
	 * @author majinding888@foxmail.com
	 * @date 2016-7-15 上午10:35:06
	 * @param callApi
	 * @return
	 * @throws ClassNotFoundException
	 */
	protected Class<?> getMethodParamterType(String callApi) throws ClassNotFoundException {

		// 判断缓存中是否存在
		if (TMCache.newTMCacheInstance(CacheName.API_PROXY_METHOD_PARAMTER_TYPE).exists(callApi)) {
			log.debug("缓存命中 API_PROXY_METHOD_PARAMTER_TYPE : {}", callApi);
			// 直接从缓存中获取
			return (Class<?>) TMCache.newTMCacheInstance(CacheName.API_PROXY_METHOD_PARAMTER_TYPE).get(callApi);
		}

		Class<?> methodParamterType = null;

		String apiName = callApi.substring(0, callApi.lastIndexOf("."));
		String method = callApi.substring(callApi.lastIndexOf(".") + 1);
		// 此方式会触发static代码
		// Class clz = Class.forName(apiName);
		Class<?> clz = Thread.currentThread().getContextClassLoader().loadClass(apiName);

		Method[] m = clz.getMethods();
		for (int i = 0; i < m.length; i++) {
			if (method.equals(m[i].getName())) {
				Class<?>[] paramTypesClasss = m[i].getParameterTypes();
				if (paramTypesClasss != null && paramTypesClasss.length > 0) {
					methodParamterType = paramTypesClasss[0];
					break;
				}
			}
		}

		// 放置到缓存中
		TMCache.newTMCacheInstance(CacheName.API_PROXY_METHOD_PARAMTER_TYPE).put(callApi, methodParamterType);
		return methodParamterType;
	}

	/**
	 * 获取方法的返回类型
	 * 
	 * @author majinding888@foxmail.com
	 * @date 2016-7-15 上午11:11:31
	 * @param callApi
	 * @return
	 * @throws ClassNotFoundException
	 */
	protected Class<?> getApiProxyReturnType(String callApi) throws ClassNotFoundException {

		// 判断缓存中是否存在
		if (TMCache.newTMCacheInstance(CacheName.API_PROXY_RETURN_TYPE).exists(callApi)) {
			log.debug("缓存命中 API_PROXY_RETURN_TYPE : {}", callApi);
			// 直接从缓存中获取
			return (Class<?>) TMCache.newTMCacheInstance(CacheName.API_PROXY_RETURN_TYPE).get(callApi);
		}

		Class<?> returnType = null;

		String apiName = callApi.substring(0, callApi.lastIndexOf("."));
		String method = callApi.substring(callApi.lastIndexOf(".") + 1);
		Class<?> clz = Thread.currentThread().getContextClassLoader().loadClass(apiName);

		Method[] m = clz.getMethods();
		for (int i = 0; i < m.length; i++) {
			if (method.equals(m[i].getName())) {
				returnType = m[i].getReturnType();
				break;
			}
		}

		// 放置到缓存中
		TMCache.newTMCacheInstance(CacheName.API_PROXY_RETURN_TYPE).put(callApi, returnType);
		return returnType;
	}

	/**
	 * 获取方法的返回类型名称（主要用途：泛型返回值）
	 * 
	 * @author majinding888@foxmail.com
	 * @date 2016-7-15 上午11:11:31
	 * @param callApi
	 * @return
	 * @throws ClassNotFoundException
	 */
	protected String getApiProxyReturnTypeName(String callApi) throws ClassNotFoundException {
		StringBuffer returnTypeName = new StringBuffer();

		// 判断缓存中是否存在
		if (TMCache.newTMCacheInstance(CacheName.API_PROXY_RETURN_TYPE_NAME).exists(callApi)) {
			log.debug("缓存命中 API_PROXY_RETURN_TYPE : {}", callApi);
			// 直接从缓存中获取
			return (String) TMCache.newTMCacheInstance(CacheName.API_PROXY_RETURN_TYPE_NAME).get(callApi);
		}

		String apiName = callApi.substring(0, callApi.lastIndexOf("."));
		String method = callApi.substring(callApi.lastIndexOf(".") + 1);
		Class<?> clz = Thread.currentThread().getContextClassLoader().loadClass(apiName);

		Method[] m = clz.getMethods();
		for (int i = 0; i < m.length; i++) {
			if (method.equals(m[i].getName())) {
				returnTypeName.append(m[i].getReturnType().getName());
				returnTypeName.append(getGenericReturnType(m[i]));
				break;
			}
		}

		// 放置到缓存中
		TMCache.newTMCacheInstance(CacheName.API_PROXY_RETURN_TYPE_NAME).put(callApi, returnTypeName.toString());
		return returnTypeName.toString();
	}

	protected String getGenericReturnType(Method method) {
		Type type = method.getGenericReturnType();// 获取参数，可能是多个，所以是数组,例如：Map<String,Integer>
		if (type instanceof ParameterizedType) {
			Type[] types = ((ParameterizedType) type).getActualTypeArguments();// 强制转型为带参数的泛型类型
			return "<" + types[0].getTypeName() + ">";// api规范只支持单参数泛型
		}
		return "";
	}

	protected BaseIn getArgsToBaseIn(CallApiDescriptor api, Object arg) {
		BaseIn in = null;
		if (null == arg) {
			try {
				in = (BaseIn) api.getParamterType().newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		} else {
			in = (BaseIn) arg;
		}
		return in;
	}

}
