package cn.majingjing.tmblog.view.util.api.scan;

import cn.majingjing.core.api.scan.DefaultScanFilter;
import cn.majingjing.core.api.scan.ScanFilter;
import cn.majingjing.core.api.scan.ScanPackage;
import cn.majingjing.tmblog.view.util.api.handler.HessianInvocationHandler;
import cn.majingjing.tmblog.view.util.api.proxy.ApiProxyFactory;

import java.lang.reflect.InvocationHandler;
import java.util.Set;

/**
 * API扫包，并将之注入的spring容器中
 * 
 * @author majinding888@foxmail.com
 * @date 2016-7-14 上午9:51:16
 */
@SuppressWarnings("all")
public class ApiScan {

	private String pack;
	private InvocationHandler handler;
	private ScanFilter filter;
	

	public void run() {
		ApiProxyFactory.init(this);
		ApiProxyFactory.registerApi();
	}
	
	public Set<Class<?>> scan(){
		Set<Class<?>> apiClassList = new ScanPackage(filter).scanClass(pack);
		return apiClassList;
	}

	public String getPack() {
		if (null == pack) {
			throw new RuntimeException("ApiScan 扫描的路径未知，程序无法继续。");
		}
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

	public InvocationHandler getHandler() {
		if (null == handler) {
			handler = new HessianInvocationHandler();
		}
		return handler;
	}

	public void setHandler(InvocationHandler handler) {
		this.handler = handler;
	}

	public ScanFilter getFilter() {
		if (null == filter) {
			filter = new DefaultScanFilter();
		}
		return filter;
	}

	public void setFilter(ScanFilter filter) {
		this.filter = filter;
	}

}
