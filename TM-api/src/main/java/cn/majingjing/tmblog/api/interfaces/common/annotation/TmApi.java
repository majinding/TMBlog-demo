package cn.majingjing.tmblog.api.interfaces.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.majingjing.tmblog.api.interfaces.common.enums.TmApiType;

/**
 * @author majinding888@foxmail.com
 * @date 2016-11-3 上午8:59:59
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TmApi {

	/**
	 * 描述
	 * 
	 * @author majinding888@foxmail.com
	 * @date 2016-11-3 上午9:03:04
	 * @return
	 */
	String value() default "";

	/**
	 * 类型
	 * 
	 * @author majinding888@foxmail.com
	 * @date 2016-11-3 上午9:03:17
	 * @return
	 */
	TmApiType apiType() default TmApiType.admin;

}
