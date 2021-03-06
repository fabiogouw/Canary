package com.fabiogouw.canaryrouter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.ZuulFilter;
import com.google.common.base.Stopwatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.web.util.UriComponentsBuilder;

public class CanaryFilter extends ZuulFilter {

  private static Logger _log = LoggerFactory.getLogger(CanaryFilter.class);
  private static int _count = 0;
  private static long _countTime = 0;
  private final CanaryInfoGetter _infoGetter;
  private final List<String> _canaryList;

  public CanaryFilter(CanaryInfoGetter infoGetter) {
    _infoGetter = infoGetter;
    _canaryList = Arrays.asList("0000", "1111", "2222", "3333", "4444", "5555", "6666", "7777", "8888", "9999");
  }

  @Override
  public String filterType() {
    return FilterConstants.PRE_TYPE;
  }

  @Override
  public int filterOrder() {
    return FilterConstants.SEND_FORWARD_FILTER_ORDER;
  }

  @Override
  public boolean shouldFilter() {
    RequestContext ctx = RequestContext.getCurrentContext();
    _log.info("RouteHost: " + ctx);
    HttpServletRequest request = ctx.getRequest();
    
    String canaryValue = "";
    Stopwatch stopwatch = Stopwatch.createStarted();
    try {
      canaryValue = _infoGetter.getInfo(request);
    } finally {
      stopwatch.stop();
    }
    updateCallTime(stopwatch.elapsed(TimeUnit.MICROSECONDS));
    return !_canaryList.contains(canaryValue);
  }

  @Override
  public Object run() {
    RequestContext ctx = RequestContext.getCurrentContext();
    String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8070").path("/legacy").build().toUriString();
    try {
      //String body = StreamUtils.copyToString(request.getInputStream(), Charset.forName("UTF-8"));
      String body = "{ \"name\": \"Teste\"}";
      ctx.setRequest(new YourRequestWrapper(ctx.getRequest(), body));
      //ctx.set("requestEntity", new ByteArrayInputStream(body.getBytes("UTF-8")));        
    } catch (Exception e1) {
      e1.printStackTrace();
    }
    ctx.set("requestURI", "");
    try {
      ctx.setRouteHost(new URL(url));
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }  
    return null;
  }

  private synchronized void updateCallTime(long time) {
    // TODO: Usar Meter
    _countTime = _countTime + time;
    _count++;
    _log.info("Extração em : " + _countTime / _count);
  }
}