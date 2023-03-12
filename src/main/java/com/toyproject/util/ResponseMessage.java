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

    public HttpServletResponse errorResponse(HttpServletRequest request, HttpServletResponse response,
                                             int status, String error, String message) throws IOException
    {
        Map<String, Object> obj = message(status, error, message, request.getRequestURI());

        response.resetBuffer();
        response.setStatus(status);
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getOutputStream().write(new ObjectMapper().writeValueAsString(obj).getBytes(StandardCharsets.UTF_8));
        response.flushBuffer();

        return response;
    }

    public HttpServletResponse errorResponse(HttpServletRequest request, HttpServletResponse response,
                                             int status, int errorCode,
                                             String error, String message) throws IOException
    {
        Map<String, Object> obj = message(errorCode, error, message, request.getRequestURI());

        response.resetBuffer();
        response.setStatus(status);
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getOutputStream().write(new ObjectMapper().writeValueAsString(obj).getBytes(StandardCharsets.UTF_8));
        response.flushBuffer();

        return response;
    }

    public HttpServletResponse errorResponse(HttpServletRequest request, HttpServletResponse response,
                                             StatusMsg statusMsg) throws IOException {
        Map<String, Object> obj = message(statusMsg, request.getRequestURI());

        response.resetBuffer();
        response.setStatus(statusMsg.getStatus_code());
        response.setHeader("Content-Type", "application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getOutputStream().write(new ObjectMapper().writeValueAsString(obj).getBytes(StandardCharsets.UTF_8));
        response.flushBuffer();

        return response;
    }
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
