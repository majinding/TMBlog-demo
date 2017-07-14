package cn.majingjing.tmblog.api.interfaces.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author majinding888@foxmail.com
 * @date 2016-11-3 上午9:24:36
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TmApiRequest {
	/**
	 * 描述
	 * 
	 * @author majinding888@foxmail.com
	 * @date 2016-11-3 上午9:03:04
	 * @return
	 */
	String value() default "";

}
