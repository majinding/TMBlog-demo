package cn.majingjing.tmblog.view.util.hessian.client;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import cn.majingjing.tmblog.api.interfaces.common.CommonApi;
import cn.majingjing.core.tool.SysPropertiesUtils;

/**
 * @author majinding888@foxmail.com
 * @date 2016-10-8 下午4:15:45
 */
public class CommonHessianClient {

	private static HessianProxyFactory factory = new HessianProxyFactory();
	private static CommonApi commonApi = null;

	/**
	 * 获取通用api
	 * 
	 * @author majinding888@foxmail.com
	 * @date 2016-10-24 下午1:53:34
	 * @return
	 */
	public static CommonApi getApi() {
		try {
			if (null == commonApi) {
				commonApi = (CommonApi) factory.create(CommonApi.class, SysPropertiesUtils.getProp("HESSIAN_URL") + "commonApi");
			}
			return commonApi;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 重置api
	 * 
	 * @author majinding888@foxmail.com
	 * @date 2016-10-24 下午1:53:54
	 */
	public static void resetApi() {
		commonApi = null;
	}

}
