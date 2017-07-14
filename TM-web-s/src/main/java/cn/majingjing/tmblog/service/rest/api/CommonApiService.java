package cn.majingjing.tmblog.service.rest.api;

import cn.majingjing.core.tool.Tools;
import cn.majingjing.tmblog.api.interfaces.common.ApiResponse;
import cn.majingjing.tmblog.api.interfaces.common.auth.ApiRequestAuth;
import cn.majingjing.tmblog.service.hessian.api.proxy.StaticApiProxy;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author majinding888@foxmail.com
 * @date 2016-7-15 上午10:41:24
 */
@Controller
@RequestMapping("/v2")
public class CommonApiService {

	private static Logger log = LoggerFactory.getLogger(CommonApiService.class);

	@RequestMapping(value = "/common", headers = "plantId=xxx")
	@ResponseBody
	public Object commonApi(String api, String p) {
		ApiResponse<Object> resp = new ApiResponse<>();

		long t1 = System.currentTimeMillis();
		// 根据api获取到对应的接口和方法
		log.info("请求API: {}", api);
		// 参数p是对应的参数
		log.info("请求API参数 : {}", p);
		JSONObject jsonObject = JSONObject.parseObject(p);
		ApiRequestAuth requestAuth = jsonObject.toJavaObject(ApiRequestAuth.class);
		if ("tzx5201314majj".equals(requestAuth.getPlantId())) {
			// try {
			// //ApiTools.getParamterType(requestAuth.getInterfaceName());
			Object obj = StaticApiProxy.invokeApi(api, jsonObject.getString("obj"));
			resp.setObj(obj);
			long t2 = System.currentTimeMillis();
			resp.setMsg(200, Tools.toStrings("处理耗时(ms): {}", t2 - t1));
			// } catch (ClassNotFoundException e) {
			// log.error("{}", e);
			// }
		} else {
			resp.setMsg(405, "API未授权 , 请确保有访问权限 .");
		}

		log.info("{}", resp.getMsg());
		return resp;
	}

	@RequestMapping("heartbeat")
	@ResponseBody
	public Object heartbeat() {
		Map<String, Object> out = new HashMap<>();
		out.put("counter", StaticApiProxy.counter.get());
		out.put("status", "success");
		return out;
	}

}
