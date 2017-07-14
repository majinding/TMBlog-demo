package cn.majingjing.tmblog.api.interfaces.common;

import cn.majingjing.tmblog.api.interfaces.dto.in.BaseIn;

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

public interface CommonApi {

	public <T> ApiResponse<T> call(ApiRequest<? extends BaseIn> req);

}
