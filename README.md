关于TMBlog-api核心代码的开源说明

> 1. 有部分功能在实际项目中已经使用了，相关代码还未做调整。
2. 代码经历时间周期也很长，这个系统之前的开发思想是将接触到的技术尽量用上。例如：view层做的多视图。 正是因为这些冗杂的程序导致整个系统看上去过于复杂。
3. 整体代码过于混乱，所以只开源部分代码。

TMBlog核心代码能干嘛，有什么用处？
```
如果你熟悉spring+hessian的开发方式，那么你肯定会讨厌繁琐的配置申明。
如果你熟悉spring+restful的开发方式，那么你肯定会想如何基于接口的方式来发布服务
如果你熟悉HttpClient的网络服务调用，那么你肯定会想如何基于接口的方式来调用服务
如果你熟悉spring的bean管理，那么你肯定想知道如何动态管理这些bean
如果你熟悉代理，反射。。。，那么你在这里或许也会有不一样的收获
```
TMBlog-api可以帮你解决上面的疑惑

现在开源的代码是接口服务的发布和调用部分[`将接口映射为服务，通过接口调用远程服务`]。代码基于版本`V3.0.1`做删减和调整
代码的整体思想是基于`hessian`和`rest`做的集成包装。使接口服务开发起来更加的快速。



------------



代码是基于前后端分离来做的设计。

在这个框架的基础上完成一个简单的接口开发。
开发步骤：

1 创建一个接口

```java
@TmApi(value = "demo示例API", apiType = TmApiType.web)
public interface DemoApi {

	@TmApiRequest("列表")
	ListOut<DemoOut> list(DemoIn in);

}

```

2 将这个接口发布为服务

```java
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
```

3 调用这个接口服务

```java
@Controller
public class DemoController {

    @Autowired
    @Qualifier("DemoApi")
    DemoApi demoApi;

    @RequestMapping("/list")
    public @ResponseBody Object list(DemoIn in, Model model) {
        return demoApi.list(in);
    }

}
```

4 启动两个服务，用浏览器方法
![image.png](https://www.majingjing.cn/tmfile/img/20170707/5dbf3a6001b54066a2d55a07fd20ec8d.png "image.png")

整个过程就是如此简单。

> 由于系统已经处理好了接口的服务发布和服务映射，所以你不用关注服务是如何发布出来的，也不用去管接口是如何调用到远程的服务的。




------------



源代码地址： [tmblog-demo](https://git.oschina.net/majinding/tmblog-demo.git "tmblog-demo")

到这里已经全部结束了，用这个来开发完全是没问题的，尤其是可以快速的将接口发布为服务，并且暴露为不同方式的服务（目前只实现了两种hessian和rest，其实其他的协议只需要扩展接口即可）


这里只是简单描述下使用示例，最重要的是源代码的实现方式。
