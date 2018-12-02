package com.fabiogouw.canaryrouter;

import javax.servlet.http.HttpServletRequest;

public interface CanaryInfoGetter {
    String getInfo(HttpServletRequest request);
}