package org.lacassandra.smooshyfaces.api.servlets.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class SetCharacterEncodingFilter implements Filter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected String encoding = null;
    protected FilterConfig filterConfig = null;
    protected boolean ignore = true;

    public SetCharacterEncodingFilter(String encoding) {
        this.encoding = encoding;
    }

    public void destroy() {

        this.encoding = null;
        this.filterConfig = null;

    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        if (ignore || (request.getCharacterEncoding() == null)) {
            String encoding = selectEncoding(request);
            if (encoding != null) {
                logger.debug("setting character encoding to {}", encoding);
                request.setCharacterEncoding(encoding);
            }
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    protected String selectEncoding(ServletRequest request) {
        return (this.encoding);
    }
}
