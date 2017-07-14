package cn.majingjing.tmblog.view.util.spring;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;

/**
 * @author majinding888@foxmail.com
 * @date 2016-7-15 下午4:44:41
 */
public class SpringBeanUtils {
	/**
	 * 注册对象到spring的content中
	 * 
	 * @author majinding888@foxmail.com
	 * @date 2016-7-15 下午4:43:55
	 * @param ctx
	 * @param beanName
	 * @param obj
	 */
	public static void registerSpringBean(ApplicationContext ctx,
			String beanName, Object obj) {

		DefaultListableBeanFactory acf = (DefaultListableBeanFactory) ctx
				.getAutowireCapableBeanFactory();
		acf.registerSingleton(beanName, obj);
	}

	/**
	 * 注册对象到spring的content中
	 * 
	 * @author majinding888@foxmail.com
	 * @date 2016-7-15 下午4:43:24
	 * @param ctx
	 * @param clz
	 */
	public static void registerSpringBean(ApplicationContext ctx, Class<?> clz) {
		DefaultListableBeanFactory acf = (DefaultListableBeanFactory) ctx
				.getAutowireCapableBeanFactory();

		// 这种方式可以根据class注入
		BeanDefinitionBuilder beanBuider = BeanDefinitionBuilder
				.genericBeanDefinition(clz);
		acf.registerBeanDefinition(clz.getSimpleName(),
				beanBuider.getBeanDefinition());
	}
	
	public static void removeSpringBean(ApplicationContext ctx, String beanId) {
		DefaultListableBeanFactory acf = (DefaultListableBeanFactory) ctx
				.getAutowireCapableBeanFactory();
		/*String[] singletonNames = acf.getSingletonNames();
		for (int i = 0; i < singletonNames.length; i++) {
			System.out.println(singletonNames[i]);
		}*/

		if(acf.containsBean(beanId)){
			//这种方式是无法移除掉代理bean的
//			acf.removeBeanDefinition(beanId);
			acf.destroySingleton(beanId);
		}
	}
}
