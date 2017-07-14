package cn.majingjing.tmblog.view.util.api.handler;

import java.util.HashMap;
import java.util.Map;

import cn.majingjing.core.tool.SysPropertiesUtils;
import cn.majingjing.tmblog.view.util.api.bean.CallApiDescriptor;

/**
 * @author majinding888@foxmail.com
 * @date 2016-10-10 下午5:14:02
 */
public class TMSubInvocationHandler extends AbstractInvocationHandler {

	// private static Logger log =
	// LoggerFactory.getLogger(TMSubInvocationHandler.class);

	private Map<String, AbstractInvocationHandler> handlers = new HashMap<String, AbstractInvocationHandler>();
	{
		handlers.put("1", new HessianInvocationHandler());
		handlers.put("2", new RestfulInvocationHandler());
	}

	@Override
	protected Object invokeApi(CallApiDescriptor api, Object arg) throws Exception {
		return handlers.get(SysPropertiesUtils.getProp("REMOTE_CALL")).invokeApi(api, arg);
	}

}
