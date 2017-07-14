package cn.majingjing.tmblog.api.interfaces.dto.in;

/**
 * Created by Administrator on 2017/7/7.
 */
public class DemoIn implements BaseIn {
    private Long id;
    private String msg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
