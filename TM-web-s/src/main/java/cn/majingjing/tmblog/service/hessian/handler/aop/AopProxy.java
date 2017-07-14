package cn.majingjing.tmblog.service.hessian.handler.aop;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AopProxy {
	// @Around(value="execution(* *..*.*(..))")
	@Around(value = "@annotation(cn.majingjing.tmblog.service.hessian.handler.aop.MyAnno)")
	public Object process(ProceedingJoinPoint point) throws Throwable {
		System.out.println("模拟执行目标方法前的增强处理：代理开始...");
		System.out.println("拦截的方法： "
		// + point.getSignature().getDeclaringTypeName() + "."
				+ point.getSignature().getName());
		System.out.println("参数： " + Arrays.toString(point.getArgs()));

		// 执行目标方法，并保存目标方法执行后的返回值
		Object returnValue = point.proceed(point.getArgs());
		// Object returnValue = exec(point.getSignature().getName(),
		// point.getTarget(), point.getArgs());

		System.out.println("模拟执行目标方法后的增强处理：代理结束...");
		// 返回修改后的返回值
		// return "方法实际返回值：" + returnValue + "，这是返回值的后缀";
		return returnValue;
	}

	private Object execOld(String method, Object target, Object[] args)
			throws Exception {
		Class clazz = target.getClass();
		Method m1 = clazz.getMethod(method, String.class);
		Object obj = m1.invoke(target, args);

		return obj;
	}

}
