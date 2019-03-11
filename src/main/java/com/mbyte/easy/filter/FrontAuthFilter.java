package com.mbyte.easy.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * FileName: FrontAuthFilter
 * Author:   wpw
 * Date:     19-2-21 上午10:14
 * Description: 前台登录校验过滤器
 * History:
 * <author>          <time>          <version>          <desc>
 * wpw             19-2-21            1.0
 */
public class FrontAuthFilter implements Filter {
    private Logger log =  LoggerFactory.getLogger(FrontAuthFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session  = request.getSession();
//        LeadingUser leadingUser = (LeadingUser) session.getAttribute(FrontConfig.FONT_SESSION);
//        if(leadingUser == null){
//            //用户想要访问的路径
//            String url = request.getRequestURI();
//            //设置session值
//            session.setAttribute(FrontConfig.FONT_FRIST_URL,url);
//            //用户未登录-跳转到登录页面
//            response.sendRedirect("/login");
//        } else {
            //用户已经登录-允许访问其他资源
            chain.doFilter(request, response);
//        }
    }
    @Override
    public void destroy() {

    }
}
 
