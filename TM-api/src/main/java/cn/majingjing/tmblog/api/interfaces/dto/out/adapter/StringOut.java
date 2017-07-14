package cn.majingjing.tmblog.api.interfaces.dto.out.adapter;

/**
 * @author Administrator - JingjingMa
 * @version 3.2
 * @Email <a href="majinding888@163.com">majinding888@163.com</a>
 * @date 2017年4月13日 下午9:43:55
 * @DevTool Created By Eclipse 4.6.2
 */

public class StringOut extends TmNomalOut<String> {

    public static StringOut instance(String value) {
        return new StringOut(value);
    }

    private StringOut() {
    }

    private StringOut(String value) {
        super();
        this.value = value;
    }

}
