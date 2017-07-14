package cn.majingjing.tmblog.view.util.api.handler;

import cn.majingjing.core.http.retrofit.RetrofitUtils;
import cn.majingjing.core.json.JacksonUtil;
import cn.majingjing.core.tool.SysPropertiesUtils;
import cn.majingjing.core.tool.TmClassUtils;
import cn.majingjing.core.tool.Tools;
import cn.majingjing.tmblog.api.interfaces.common.ApiPageSimpleAdapter;
import cn.majingjing.tmblog.api.interfaces.common.ApiTools;
import cn.majingjing.tmblog.api.interfaces.common.auth.ApiResponseAuth;
import cn.majingjing.tmblog.api.interfaces.dto.out.adapter.ListOut;
import cn.majingjing.tmblog.api.interfaces.dto.out.adapter.ListPageOut;
import cn.majingjing.tmblog.view.util.api.bean.CallApiDescriptor;
import cn.majingjing.tmblog.view.util.api.interfaceApi.TMServerApi;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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
public class RestfulInvocationHandler extends AbstractInvocationHandler {
	private static Logger log = LoggerFactory.getLogger(RestfulInvocationHandler.class);

	public Object invokeApi(CallApiDescriptor api, Object arg) throws Exception {
		Object rtn = null;

		// 封装请求数据
		req.setPlantId(plantId);
		req.setInterfaceName(api.getApi());
		req.setObj(getArgsToBaseIn(api, arg));

		String in = JacksonUtil.toJson(req);

		@SuppressWarnings("deprecation")
		String url = SysPropertiesUtils.getProp("TM_SERVER_URL");
		retrofit2.Response<String> resp = RetrofitUtils.getRetrofitApi(url, TMServerApi.class)
				.query(plantId, api.getApi(), in).execute();
		if (resp.isSuccessful()) {
			String json = resp.body();

			JSONObject jsonObject = JSONObject.parseObject(json);
			ApiResponseAuth respAuth = jsonObject.toJavaObject(ApiResponseAuth.class);
			if (respAuth.getStatus() == 200) {
				rtn = getApiReturnObject(api, jsonObject.getString("obj"));
			}
		}

		return rtn;
	}

	public Object getApiReturnObject(CallApiDescriptor api, String json) throws ClassNotFoundException {
		Object rtn = null;
		JSONObject jsonObject = JSONObject.parseObject(json);
		if (ApiTools.isGenericOfReturnTypeName(api.getReturnTypeName())) {
			String genericName = ApiTools.getReturnTypeGenericName(api.getReturnTypeName());
			log.info("泛型包裹对象：{}", genericName);


			List<Object> list = JSON.parseArray(jsonObject.getJSONArray("list").toJSONString(),
					TmClassUtils.loadClass(genericName));

			if (api.getReturnType() == ListOut.class) {
				rtn = ListOut.instance(list);
			} else if (api.getReturnType() == ListPageOut.class) {
				rtn = ListPageOut.instance(
						JSON.parseObject(jsonObject.getString("page"), ApiPageSimpleAdapter.class),
						list);
			}
		} else {
			// log.info("响应json为：\n{}",json);
			
			if(ApiTools.isNomalResponseOut(api.getReturnType())){
				rtn = ApiTools.getNomalResponseOut(api.getReturnType(), Tools.toStrings(jsonObject.get("value")));
			}else{
				rtn = JacksonUtil.readValue(json, api.getReturnType());
			}
			// rtn = JsonUtil.json2Object(json, clz);
		}
		return rtn;
	}

}