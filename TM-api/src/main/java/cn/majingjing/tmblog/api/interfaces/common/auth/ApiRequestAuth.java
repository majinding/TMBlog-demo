package cn.majingjing.tmblog.api.interfaces.common.auth;

/**
 * 
 * 
 * @author Administrator - JingjingMa
 * @Email <a href="majinding888@163.com">majinding888@163.com</a>
 * @date 2017年4月19日 下午6:33:47
 * @version 3.2
 * @DevTool Created By Eclipse 4.6.2
 *
 */

public class ApiRequestAuth {
	
	private String plantId;
	private String interfaceName;

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
