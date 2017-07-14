package cn.majingjing.tmblog.view.util.api.handler;

import cn.majingjing.tmblog.api.interfaces.common.ApiResponse;
import cn.majingjing.tmblog.api.interfaces.common.CommonApi;
import cn.majingjing.tmblog.api.interfaces.dto.in.BaseIn;
import cn.majingjing.tmblog.view.util.api.bean.CallApiDescriptor;
import cn.majingjing.tmblog.view.util.hessian.client.CommonHessianClient;

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
public class HessianInvocationHandler extends AbstractInvocationHandler {
	// private static Logger log =
	// LoggerFactory.getLogger(HessianInvocationHandler.class);
	// 封装请求数据

	@Override
	public Object invokeApi(CallApiDescriptor api, Object arg) {
		BaseIn in = getArgsToBaseIn(api, arg);
		req.setPlantId(plantId);
		req.setInterfaceName(api.getApi());
		req.setObj(in);

		// 执行请求API
		// CommonApi api = SpringContext.getBean("commonApi");
		CommonApi commonApi = CommonHessianClient.getApi();
		ApiResponse<Object> resp = commonApi.call(req);

		// 返回响应值
		// log.debug("响应码：{}",resp.getStatus());
		Object rtn = resp.getObj();
		return rtn;
	}

}