package com.dinghao.core.interceptor.filter;

import com.dinghao.common.filter.RepeatedlyRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class HttpServletRequestReplacedFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            ServletRequest requestWrapper = new RepeatedlyRequestWrapper((HttpServletRequest) request);
            //获取请求中的流，将取出来的字符串，再次转换成流，然后把它放入到新request对象中。
            // 在chain.doFiler方法中传递新的request对象
            chain.doFilter(requestWrapper, response);
            return;
        }
        chain.doFilter(request, response);
    }

}
