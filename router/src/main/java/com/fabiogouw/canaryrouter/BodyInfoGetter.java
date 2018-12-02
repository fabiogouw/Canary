package com.fabiogouw.canaryrouter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BodyInfoGetter implements CanaryInfoGetter {
    public String getInfo(HttpServletRequest request) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = null;
        try {
            node = mapper.readTree(request.getInputStream());
        } catch (IOException e) {
			e.printStackTrace();
        }
        if(node != null) {
            node = node.path("canary");
            if(node != null) {
                return node.textValue();
            }
        }
        return "";
    }
}