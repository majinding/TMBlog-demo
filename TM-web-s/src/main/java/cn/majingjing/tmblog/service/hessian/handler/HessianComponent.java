package cn.majingjing.tmblog.service.hessian.handler;

import org.springframework.stereotype.Service;

/**
 * @description  
 * 
 * @author    Administrator - JingjingMa
 * @Email     <a href="majinding888@163.com">majinding888@163.com</a>
 * @date      2016-5-8  下午7:08:11
 * @version    6.0
 * @DevTool   Created By Eclipse 4.3
 *
 */
@Service
public @interface HessianComponent {
	String value() default "";
}
