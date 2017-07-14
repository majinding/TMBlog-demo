package cn.majingjing.tmblog.view.controller.web;

import cn.majingjing.tmblog.api.interfaces.api.DemoApi;
import cn.majingjing.tmblog.api.interfaces.dto.in.DemoIn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/7/7.
 */
@Controller
public class DemoController {
    private static Logger log = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    @Qualifier("DemoApi")
    DemoApi demoApi;

    @RequestMapping("/")
    public @ResponseBody Object index(DemoIn in, Model model) {
        return "hello";
    }

    @RequestMapping("/list")
    public @ResponseBody Object list(DemoIn in, Model model) {
        return demoApi.list(in);
    }

}
