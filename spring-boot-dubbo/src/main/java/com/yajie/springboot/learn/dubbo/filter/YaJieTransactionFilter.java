/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: YaJieTransactionFilter
 * Author:   Administrator
 * Date:     2019/2/14 0014 14:41
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yajie.springboot.learn.dubbo.filter;



import com.alibaba.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 〈Dubbo中的Filter实现是 专门为服务提供方和服务消费方调用过程进行拦截，
 * Dubbo本身的大多功能均基于此扩展点实现，每次远程方法执行，该拦截都会被执行〉<br>
 * 〈所以，在实际业务开发中，使用最多的可能就是对Filter接口进行扩展，
 * 在服务调用链路中嵌入我们自身的处理逻辑，如日志打印、调用耗时统计等。
 * Dubbo官方针对Filter做了很多的原生支持，目前大致有20来个吧，
 * 包括我们熟知的RpcContext，accesslog功能都是通过filter来实现了，
 *〉
 *
 * @author Administrator
 * @create 2019/2/14 0014
 * @since 1.0.0
 */
public class YaJieTransactionFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(YaJieTransactionFilter.class);

//    private IpWhiteList ipWhiteList;
//
//    //注意：只能通过setter方式来注入其他的bean，且不要标注注解！
//dubbo自己会对这些bean进行注入，不需要再标注@Resource让Spring注入，参见扩展点加载
//    public void setIpWhiteList(IpWhiteList ipWhiteList) {
//        this.ipWhiteList = ipWhiteList;
//    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        String methodName = invocation.getMethodName();
        //get service interface
        Class clazz = invoker.getInterface();
        Class[] args = invocation.getParameterTypes();
        //获取参数集合
        final Object[] arguments = invocation.getArguments();
        return null;
    }
}
