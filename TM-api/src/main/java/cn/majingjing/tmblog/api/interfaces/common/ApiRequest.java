package cn.majingjing.tmblog.api.interfaces.common;

/**
 * @Describe:
 * 
 * @DevTool: Spring Tool Suite 3.7.2.RELEASE
 * 
 * @author: Administrator - JingjingMa
 * @Email: <a href="majinding888@163.com">majinding888@163.com</a>
 * @date: 2016年1月27日 上午9:34:46
 * @since 1.0
 *
 */

public class ApiRequest<T>  implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String plantId;
	private String interfaceName;

	private T obj;
	
	public ApiRequest() {
	}

	public ApiRequest(T obj) {
		this.obj = obj;
	}

	/**
	 * 获取参数对象
	 * 
	 * @return
	 *
	 * @since 1.0
	 *
	 */
	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}
	/**
	 * 获取平台ID
	 * 
	 * @return
	 *
	 * @since 1.0
	 *
	 */
	public String getPlantId() {
		return plantId;
	}

	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	
}
