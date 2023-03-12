package com.toyproject.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class ResponseMessage {
    private Map<String, Object> message(int status, String error, String message, String path) {
        Map<String, Object> obj = new LinkedHashMap<>();

        obj.put("timestamp", new Date().getTime());
        obj.put("status", status);
        obj.put("error", error);
        obj.put("message", message);
        obj.put("path", path);

        return obj;
    }

    public Map<String, Object> message(StatusMsg statusMsg, String path) {
        Map<String, Object> obj = new LinkedHashMap<>();

        obj.put("timestamp", new Date().getTime());
        obj.put("status", statusMsg.getStatus_code());
        obj.put("error", statusMsg.getStatus_msg());
        obj.put("message", statusMsg.getCustom_msg());
        obj.put("path", path);

        return obj;
    }
}
