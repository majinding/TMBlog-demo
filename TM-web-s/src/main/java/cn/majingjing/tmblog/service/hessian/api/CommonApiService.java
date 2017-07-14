package cn.majingjing.tmblog.service.hessian.api;

import cn.majingjing.tmblog.service.hessian.handler.HessianServerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.majingjing.tmblog.api.interfaces.common.CommonApi;
import cn.majingjing.tmblog.service.rmi.comm.CommonApiImpl;

/**
 * @author majinding888@foxmail.com
 * @date 2016-7-11 下午1:44:36
 */
@Service("/commonApi")
public class CommonApiService extends HessianServerApi {

	@Autowired
	private CommonApiImpl service;

	@Override
	public Object getService() {
		return service;
	}

	@Override
	public Class getServiceInterface() {
		return CommonApi.class;
	}

}
