package cn.majingjing.tmblog.service.rmi.comm;

import cn.majingjing.tmblog.service.hessian.api.proxy.StaticApiProxy;
import org.springframework.stereotype.Service;

import cn.majingjing.tmblog.api.interfaces.common.ApiRequest;
import cn.majingjing.tmblog.api.interfaces.common.ApiResponse;
import cn.majingjing.tmblog.api.interfaces.common.CommonApi;
import cn.majingjing.tmblog.api.interfaces.dto.in.BaseIn;

/**
 * @author majinding888@foxmail.com
 * @date 2016-7-12 下午12:14:21
 */
@Service("CommonApiImpl")
public class CommonApiImpl implements CommonApi {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T> ApiResponse<T> call(ApiRequest<? extends BaseIn> req) {
		System.out.println("调用接口：" + req.getInterfaceName());

		ApiResponse resp = new ApiResponse();
		if(!"xxx".equals(req.getPlantId())){
			resp.setMsg(400, "未授权的请求.");
			return resp;
		}
		
		//System.out.println(req.getObj());

		// Object obj = new
		// ApiProxy(req.getInterfaceName()).invokeApi(req.getObj());
		Object obj = StaticApiProxy.invokeApi(req.getInterfaceName(), req.getObj());

		System.out.println("返回接口调用：" + req.getInterfaceName());
		resp.setObj(obj);
		return resp;
	}

}
