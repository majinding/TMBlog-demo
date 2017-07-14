package cn.majingjing.tmblog.api.interfaces.common;

import cn.majingjing.tmblog.api.interfaces.dto.in.adapter.BooleanIn;
import cn.majingjing.tmblog.api.interfaces.dto.in.adapter.StringIn;
import cn.majingjing.tmblog.api.interfaces.dto.out.adapter.BooleanOut;
import cn.majingjing.tmblog.api.interfaces.dto.out.adapter.VoidOut;
import cn.majingjing.tmblog.api.interfaces.dto.out.adapter.IntegerOut;
import cn.majingjing.tmblog.api.interfaces.dto.in.BaseIn;
import cn.majingjing.tmblog.api.interfaces.dto.in.adapter.IntegerIn;
import cn.majingjing.tmblog.api.interfaces.dto.in.adapter.LongIn;
import cn.majingjing.tmblog.api.interfaces.dto.in.adapter.VoidIn;
import cn.majingjing.tmblog.api.interfaces.dto.out.adapter.LongOut;
import cn.majingjing.tmblog.api.interfaces.dto.out.adapter.StringOut;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author majinding888@foxmail.com
 * @date 2016-12-19 上午11:55:06
 */
public class ApiTools {

    /**
     * 加载api,此方法不会触发类的静态方法
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     * @author Administrator - JingjingMa
     * @Email <a href="majinding888@163.com">majinding888@163.com</a>
     * @date 2017年4月18日 下午11:00:33
     * @version 3.2
     * @DevTool Created By Eclipse 4.3
     */
    public static Class<?> loadClass(String name) throws ClassNotFoundException {
        return Thread.currentThread().getContextClassLoader().loadClass(name);
    }

    /**
     * 获取api的接口名
     *
     * @param callApi
     * @return
     * @author Administrator - JingjingMa
     * @Email <a href="majinding888@163.com">majinding888@163.com</a>
     * @date 2017年4月18日 下午10:55:38
     * @version 3.2
     * @DevTool Created By Eclipse 4.3
     */
    public static String getName(String callApi) {
        return callApi.substring(0, callApi.lastIndexOf("."));
    }

    /**
     * 获取api的接口方法名
     *
     * @param callApi
     * @return
     * @author Administrator - JingjingMa
     * @Email <a href="majinding888@163.com">majinding888@163.com</a>
     * @date 2017年4月18日 下午10:56:39
     * @version 3.2
     * @DevTool Created By Eclipse 4.3
     */
    public static String getMethod(String callApi) {
        return callApi.substring(callApi.lastIndexOf(".") + 1);
    }

    /**
     * 获取方法的参数类型
     *
     * @param callApi
     * @return
     * @throws ClassNotFoundException
     * @author majinding888@foxmail.com
     * @date 2016-7-15 上午10:35:06
     */
    public static Class<?> getParamterType(String callApi) throws ClassNotFoundException {

        Class<?> methodParamterType = null;

        String name = ApiTools.getName(callApi);
        String method = ApiTools.getMethod(callApi);

        Method[] m = ApiTools.loadClass(name).getMethods();
        for (int i = 0; i < m.length; i++) {
            if (method.equals(m[i].getName())) {
                Class<?>[] paramTypesClasss = m[i].getParameterTypes();
                if (paramTypesClasss != null && paramTypesClasss.length > 0) {
                    methodParamterType = paramTypesClasss[0];
                    break;
                }
            }
        }

        return methodParamterType;
    }

    /**
     * 获取方法的参数类型名称
     *
     * @param callApi
     * @return
     * @throws ClassNotFoundException
     * @author majinding888@foxmail.com
     * @date 2016-7-15 上午10:35:06
     */
    public static String getParamterTypeName(String callApi) throws ClassNotFoundException {
        Class<?> paramterType = ApiTools.getParamterType(callApi);
        if (null == paramterType) {
            return null;
        }
        return paramterType.getName();
    }

    /**
     * 获取方法的返回类型
     *
     * @param callApi
     * @return
     * @throws ClassNotFoundException
     * @author majinding888@foxmail.com
     * @date 2016-7-15 上午11:11:31
     */
    public static Class<?> getReturnType(String callApi) throws ClassNotFoundException {

        Class<?> returnType = null;

        String name = ApiTools.getName(callApi);
        String method = ApiTools.getMethod(callApi);

        Method[] m = ApiTools.loadClass(name).getMethods();
        for (int i = 0; i < m.length; i++) {
            if (method.equals(m[i].getName())) {
                if (m[i].getReturnType() != null && m[i].getReturnType() != void.class) {
                    returnType = m[i].getReturnType();
                }
                break;
            }
        }

        return returnType;
    }

    /**
     * 获取方法的返回类型名称（主要用途：泛型返回值）
     *
     * @param callApi
     * @return
     * @throws ClassNotFoundException
     * @author majinding888@foxmail.com
     * @date 2016-7-15 上午11:11:31
     */
    public static String getReturnTypeName(String callApi) throws ClassNotFoundException {
        StringBuffer returnTypeName = new StringBuffer();

        String name = ApiTools.getName(callApi);
        String method = ApiTools.getMethod(callApi);

        Method[] m = ApiTools.loadClass(name).getMethods();
        for (int i = 0; i < m.length; i++) {
            if (method.equals(m[i].getName())) {
                if (m[i].getReturnType() != null && m[i].getReturnType() != void.class) {
                    returnTypeName.append(m[i].getReturnType().getName());
                    returnTypeName.append(getGenericReturnType(m[i]));
                } else {
                    return null;
                }
                break;
            }
        }

        return returnTypeName.toString();
    }

    /**
     * 获取返回值的泛型类型
     *
     * @param method
     * @return
     * @author Administrator - JingjingMa
     * @Email <a href="majinding888@163.com">majinding888@163.com</a>
     * @date 2017年4月18日 下午11:03:34
     * @version 3.2
     * @DevTool Created By Eclipse 4.3
     */
    private static String getGenericReturnType(Method method) {
        Type type = method.getGenericReturnType();// 获取参数，可能是多个，所以是数组,例如：Map<String,Integer>
        if (type instanceof ParameterizedType) {
            Type[] types = ((ParameterizedType) type).getActualTypeArguments();// 强制转型为带参数的泛型类型
            return "<" + types[0].getTypeName() + ">";// api规范只支持单参数泛型
        }
        return "";
    }

    /**
     * 返回类型名称是否包含泛型
     *
     * @param returnTypeName
     * @return
     * @author Administrator - JingjingMa
     * @Email <a href="majinding888@163.com">majinding888@163.com</a>
     * @date 2017年4月19日 下午9:01:01
     * @version 3.2
     * @DevTool Created By Eclipse 4.3
     */
    public static boolean isGenericOfReturnTypeName(String returnTypeName) {
        return (returnTypeName != null && returnTypeName.indexOf("<") > 0 && returnTypeName.indexOf(">") > 0);
    }

    /**
     * 获取返回类型的泛型名称
     *
     * @param returnTypeName
     * @return
     * @author Administrator - JingjingMa
     * @Email <a href="majinding888@163.com">majinding888@163.com</a>
     * @date 2017年4月19日 下午9:01:32
     * @version 3.2
     * @DevTool Created By Eclipse 4.3
     */
    public static String getReturnTypeGenericName(String returnTypeName) {
        if (ApiTools.isGenericOfReturnTypeName(returnTypeName)) {
            return returnTypeName.substring(returnTypeName.indexOf("<") + 1, returnTypeName.indexOf(">"));
        }
        return null;
    }

    public static boolean isNomalRequestIn(Class<?> clz) {
        if (clz == BooleanIn.class) {

        } else if (clz == IntegerIn.class) {

        } else if (clz == LongIn.class) {

        } else if (clz == StringIn.class) {

        } else if (clz == VoidIn.class) {

        } else {
            return false;
        }

        return true;
    }

    public static boolean isNomalResponseOut(Class<?> clz) {
        if (clz == BooleanOut.class) {

        } else if (clz == IntegerOut.class) {

        } else if (clz == LongOut.class) {

        } else if (clz == StringOut.class) {

        } else if (clz == VoidOut.class) {

        } else {
            return false;
        }

        return true;
    }

    public static BaseIn getNomalRequestIn(Class<?> clz, String obj) {
        BaseIn in = null;
        if (clz == BooleanIn.class) {
            in = BooleanIn.instance(Boolean.valueOf(obj));
        } else if (clz == IntegerIn.class) {
            in = IntegerIn.instance(Integer.valueOf(obj));
        } else if (clz == LongIn.class) {
            in = LongIn.instance(Long.valueOf(obj));
        } else if (clz == StringIn.class) {
            in = StringIn.instance(obj);
        } else if (clz == VoidIn.class) {
            in = VoidIn.instance();
        }

        return in;
    }

    public static Object getNomalResponseOut(Class<?> clz, String obj) {
        Object out = null;
        if (clz == BooleanOut.class) {
            out = BooleanOut.instance(Boolean.valueOf(obj));
        } else if (clz == IntegerOut.class) {
            out = IntegerOut.instance(Integer.valueOf(obj));
        } else if (clz == LongOut.class) {
            out = LongOut.instance(Long.valueOf(obj));
        } else if (clz == StringOut.class) {
            out = StringOut.instance(obj);
        } else if (clz == VoidOut.class) {
            out = VoidOut.instance();
        }

        return out;
    }

    private static Class[] clzs = new Class[]{
            //TmNomalIn.class, BooleanIn.class, IntegerIn.class, LongIn.class, StringIn.class, VoidIn.class, TmNomalOut.class, BooleanOut.class, IntegerOut.class, LongOut.class,
            StringOut.class
            //, VoidOut.class
    };

    /**
     * 该方法暂时还不能达到预期的效果
     */
    @Deprecated
    public static void registerApiInOut() {
        for (Class clz : clzs) {
            Method[] methods = clz.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println(method);
                boolean accessible = method.isAccessible();
                System.out.println(accessible);
                method.setAccessible(true);
                accessible = method.isAccessible();
                System.out.println(accessible);
            }

            Constructor[] constructors = clz.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                System.out.println(constructor);
                System.out.println(constructor.isAccessible());
                constructor.setAccessible(true);
                System.out.println(constructor.isAccessible());
            }

        }
    }
}
