package cn.majingjing.tmblog.service.rmi.impl;

import cn.majingjing.tmblog.api.interfaces.api.DemoApi;
import cn.majingjing.tmblog.api.interfaces.dto.in.DemoIn;
import cn.majingjing.tmblog.api.interfaces.dto.out.DemoOut;
import cn.majingjing.tmblog.api.interfaces.dto.out.adapter.ListOut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator - JingjingMa
 * @version 6.0
 * @description
 * @Email <a href="majinding888@163.com">majinding888@163.com</a>
 * @date 2016-5-8 下午6:30:37
 * @DevTool Created By Eclipse 4.3
 */
@Service("DemoApiImpl")
@Transactional
public class DemoApiImpl implements DemoApi {

    @Override
    public ListOut<DemoOut> list(DemoIn in) {
        List<DemoOut> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            DemoOut out = new DemoOut();
            out.setId(in.getId());
            out.setMsg(in.getMsg());
            out.setNowTime(new Date());
            list.add(out);
        }
        return ListOut.instance(list);
    }
}
