package cn.majingjing.tmblog.api.interfaces.dto.out.adapter;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * 扩展HashMap,便捷操作每次都需要转换类型的繁琐操作
 * 
 * @author majinding888@foxmail.com
 */
public class BeanHashMap extends HashMap<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8675591126645639297L;

	public static BeanHashMap instance(){
		return new BeanHashMap();
	}

	public String getString(String key) {
		return (String) super.get(key);
	}

	public Integer getInteger(String key) {
		return (Integer) super.get(key);
	}

	public Long getLong(String key) {
		return (Long) super.get(key);
	}
	
	public Float getFloat(String key) {
		return (Float) super.get(key);
	}

	/**
	 * 如果是字节数组,则转换为字符串存储 (non-Javadoc)
	 * 
	 * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public Object put(String key, Object value) {
		if (value instanceof byte[]) {
			try {
				String msg = new String((byte[]) value, "utf-8");
				return super.put(key, msg);
			} catch (UnsupportedEncodingException e) {
				return super.put(key, value);
			}
		} else {
			return super.put(key, value);
		}
	}

}
