package cn.majingjing.tmblog.service.hessian.api.proxy;

import com.alibaba.fastjson.JSONObject;
import cn.majingjing.tmblog.api.interfaces.common.ApiTools;
import cn.majingjing.tmblog.api.interfaces.dto.in.BaseIn;
import cn.majingjing.tmblog.service.core.util.spring.SpringContext;
import cn.majingjing.core.json.JacksonUtil;
import cn.majingjing.core.tool.Tools;
import org.apache.commons.beanutils.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @description
 * 
 * @author Administrator - JingjingMa
 * @Email <a href="majinding888@163.com">majinding888@163.com</a>
 * @date 2016-7-13 下午9:19:48
 * @version 6.0
 * @DevTool Created By Eclipse 4.3
 * 
 */
@SuppressWarnings("all")
public class StaticApiProxy {
	
	private static Logger log = LoggerFactory.getLogger(StaticApiProxy.class);
	
	/**
	 * API访问次数
	 */
	public static AtomicLong counter = new AtomicLong();

	public static Object invokeApi(String callApi, String p) {
		BaseIn in = getApiArgs(callApi, p);
		return invokeApi(callApi, in);
	}

	public static Object invokeApi(String callApi, BaseIn in) {
		Object bean = getApiBean(callApi);
		String method = ApiTools.getMethod(callApi);
		Object obj = null;
		try {
			counter.incrementAndGet();

			log.info("请求API：{}", callApi);
			obj = MethodUtils.invokeMethod(bean, method, in);
			log.info("响应API");
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			log.error("请求API出现异常：{}", e);
		}
		return obj;
	}

	/**
	 * @description 得到api调用的接口bean
	 * 
	 * @author Administrator - JingjingMa
	 * @Email <a href="majinding888@163.com">majinding888@163.com</a>
	 * @date 2016-7-13 下午9:30:58
	 * @version 6.0
	 * @DevTool Created By Eclipse 4.3
	 * 
	 * @param callApi
	 * @return
	 */
	private static Object getApiBean(String callApi) {
		Object obj = null;
		try {
			Class clz = ApiTools.loadClass(ApiTools.getName(callApi));
			obj = SpringContext.getBean(clz);
		} catch (BeansException | ClassNotFoundException e) {
			log.error("error",e);
		}
		return obj;
	}

	public static BaseIn getApiArgs(String callApi, String p) {
		BaseIn in = null;
		try {
			Class<?> clz = ApiTools.getParamterType(callApi);
			if (null != p && p.length() > 0) {
				if(ApiTools.isNomalRequestIn(clz)){
					in = ApiTools.getNomalRequestIn(clz, Tools.toStrings(JSONObject.parseObject(p).get("value")));
				}else{
					in = (BaseIn) JacksonUtil.readValue(p, clz);
				}
			} else {
				in = (BaseIn) clz.newInstance();
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			log.error("error",e);
		}

		return in;
	}

}
