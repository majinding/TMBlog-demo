package cn.majingjing.tmblog.view.util.hessian.client;

import org.springframework.remoting.caucho.HessianProxyFactoryBean;

import cn.majingjing.core.tool.SysPropertiesUtils;

/**
 * @Describe:
 * 
 * @version 6.0
 * 
 * @DevTool: Created By Eclipse 4.3
 * 
 * @author: Administrator - JingjingMa
 * @Email: <a href="majinding888@163.com">majinding888@163.com</a>
 * @date: 2015-1-8 上午9:24:15
 * @since 1.0
 * 
 */

public class HessianClientApi extends HessianProxyFactoryBean {
	
	/**
	 * 服务接口名
	 */
	private String serviceInterfaceName;
	
	/**
	 * 填充调用的接口url
	 * 
	 * @param spcServiceInterfaceImpl
	 */
	public void setServiceInterfaceName(String spcServiceInterfaceImpl) {
		this.serviceInterfaceName = spcServiceInterfaceImpl;
		super.setServiceUrl(SysPropertiesUtils.getProp("HESSIAN_URL") + serviceInterfaceName);
	}

}
