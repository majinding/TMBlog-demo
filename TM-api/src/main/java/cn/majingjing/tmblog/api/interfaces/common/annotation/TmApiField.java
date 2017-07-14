package cn.majingjing.tmblog.api.interfaces.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.majingjing.tmblog.api.interfaces.common.enums.TmNull;

/**
 * @author majinding888@foxmail.com
 * @date 2016-11-3 上午9:33:59
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TmApiField {

	/**
	 * 描述
	 * 
	 * @author majinding888@foxmail.com
	 * @date 2016-11-3 上午9:03:04
	 * @return
	 */
	String value() default "";

	/**
	 * 是否必须
	 * 
	 * @author majinding888@foxmail.com
	 * @date 2016-11-3 上午9:37:16
	 * @return
	 */
	boolean required() default false;

	/**
	 * 实际的包装对象
	 * 
	 * @author majinding888@foxmail.com
	 * @date 2016-11-3 下午3:02:40
	 * @return
	 */
	Class<?> wrapper() default TmNull.class;

}
