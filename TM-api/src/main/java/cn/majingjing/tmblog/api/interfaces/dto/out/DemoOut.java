package cn.majingjing.tmblog.api.interfaces.dto.out;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by <a href="mailto:majinding888@foxmail.com">JingjingMa</a> on 2017/7/7.
 * <p>网站: <a href="https://www.majingjing.cn">https://www.majingjing.cn</a></p>
 */
public class DemoOut implements Serializable {

    private Long id;
    private String msg;
    private Date nowTime;

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

    public Date getNowTime() {
        return nowTime;
    }

    public void setNowTime(Date nowTime) {
        this.nowTime = nowTime;
    }
}
