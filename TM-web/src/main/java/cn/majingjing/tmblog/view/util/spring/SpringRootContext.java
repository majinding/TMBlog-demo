package cn.majingjing.tmblog.view.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringRootContext implements ApplicationContextAware {

	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		SpringRootContext.context = context;
	}

	public static ApplicationContext getContext() {
		return context;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) throws BeansException {
		return (T) context.getBean(name);
	}

}
