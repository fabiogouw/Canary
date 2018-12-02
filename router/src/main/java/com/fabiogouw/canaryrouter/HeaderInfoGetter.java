package com.fabiogouw.canaryrouter;

import javax.servlet.http.HttpServletRequest;

public class HeaderInfoGetter implements CanaryInfoGetter {
    public String getInfo(HttpServletRequest request) {
        return request.getHeader("canary");
    }
}