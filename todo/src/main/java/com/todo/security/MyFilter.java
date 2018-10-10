
package com.todo.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by yf on 2016/1/20.
 */


public class MyFilter extends AbstractSecurityInterceptor implements Filter {
    private static final Logger logger = LogManager.getLogger(MyFilter.class.getName());


    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return null;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String a=SecurityContextHolder.getContext().getAuthentication().getName();
//		FilterInvocation fi = new FilterInvocation(request, response, chain);
//			this.invke(fi);
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        // 获得当前请求的URI
        String nowPath = req.getRequestURI();
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        this.invke(fi);

        //chain.doFilter(requ
    }

    private void invke(FilterInvocation f) {
        InterceptorStatusToken token = super.beforeInvocation(f);
        try {
            f.getChain().doFilter(f.getRequest(),f.getResponse());
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            /*e.printStackTrace();*/
        } catch (ServletException e) {
            logger.error(e.getMessage(),e);
        }finally {
            super.afterInvocation(token, null);
        }
    }

}
