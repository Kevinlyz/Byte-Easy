package com.mbyte.easy.security.auth;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;
/**
 * 该类提供了获取用户登录时携带的额外信息的功能，默认实现WebAuthenticationDetails提供了remoteAddress与sessionId信息。
 * 开发者可以通过Authentication的getDetails()获取WebAuthenticationDetails。
 * @author 王震
 *
 */
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {

	private static final long serialVersionUID = 1L;
	
	private final String validateCode;

    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        validateCode = request.getParameter("validateCode");
    }

    public String getValidateCode() {
        return validateCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("; validateCode: ").append(this.getValidateCode());
        return sb.toString();
    }


}
