package cn.majingjing.tmblog.api.interfaces.api;

import cn.majingjing.tmblog.api.interfaces.common.annotation.TmApi;
import cn.majingjing.tmblog.api.interfaces.common.annotation.TmApiRequest;
import cn.majingjing.tmblog.api.interfaces.common.enums.TmApiType;
import cn.majingjing.tmblog.api.interfaces.dto.in.DemoIn;
import cn.majingjing.tmblog.api.interfaces.dto.out.DemoOut;
import cn.majingjing.tmblog.api.interfaces.dto.out.adapter.ListOut;

/**
 * @description
 * 
 * @author Administrator - JingjingMa
 * @Email <a href="majinding888@163.com">majinding888@163.com</a>
 * @date 2016-6-5 下午7:05:51
 * @version 6.0
 * @DevTool Created By Eclipse 4.3
 * 
 */
@TmApi(value = "demo示例API", apiType = TmApiType.web)
public interface DemoApi {

	@TmApiRequest("列表")
	ListOut<DemoOut> list(DemoIn in);

}
