package com.todo.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;

public class PreRequestHeaderAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {
    private String principalRequestHeader = "SM_USER";
    private String credentialsRequestHeader;
    private boolean exceptionIfHeaderMissing = true;

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        /*获取principal信息*/
        String principal = request.getHeader(principalRequestHeader);

        if (principal == null && exceptionIfHeaderMissing) {
            // 对于request进行BadException处理
            request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, new BadCredentialsException("No pre-authenticated credentials found in request."));

            return "N/A";
        }

        return principal;
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        if (credentialsRequestHeader != null) {
            return request.getHeader(credentialsRequestHeader);
        }

        return "N/A";
    }

    public void setPrincipalRequestHeader(String principalRequestHeader) {
        Assert.hasText(principalRequestHeader,
                "principalRequestHeader must not be empty or null");
        this.principalRequestHeader = principalRequestHeader;
    }

    public void setCredentialsRequestHeader(String credentialsRequestHeader) {
        Assert.hasText(credentialsRequestHeader,
                "credentialsRequestHeader must not be empty or null");
        this.credentialsRequestHeader = credentialsRequestHeader;
    }

    /**
     * Defines whether an exception should be raised if the principal header is missing.
     * Defaults to {@code true}.
     *
     * @param exceptionIfHeaderMissing set to {@code false} to override the default
     * behaviour and allow the request to proceed if no header is found.
     */
    public void setExceptionIfHeaderMissing(boolean exceptionIfHeaderMissing) {
        this.exceptionIfHeaderMissing = exceptionIfHeaderMissing;
    }
}
