package com.jk.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class MyFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String uri = request.getRequestURI();
        String substring = uri.substring(uri.lastIndexOf("/")+1);
        System.out.println(substring);
        if(substring.equals("login")||substring.contains(".js")||substring.contains(".css")||substring.contains(".jpg")){
            return false;
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        if(user==null){
            System.out.println("拦截");
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(403);
            //session.setAttribute("user","1");
        }else{
            System.out.println("未拦截");
            currentContext.setSendZuulResponse(true);
        }
        return null;
    }
}
