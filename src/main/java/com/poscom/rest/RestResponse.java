package com.poscom.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Standard response class to be used when interacting via REST / AJAX.
 *
 * @author Dan Bennett (dbennett) / Tier2 Consulting
 * @since 23/12/2016
 */
public class RestResponse<T> {

    private static final Logger LOG = LoggerFactory.getLogger(RestResponse.class);

    private boolean success;
    private boolean auditable = true;
    private String error;
    private int status = 200;
    private Map<String, Object> auditAttributes = new HashMap<>();
    private Map<String, Object> headers = new HashMap<>();
    private T body;

    public static <T> RestResponse<T> successful() {
        return new RestResponse<T>().success();
    }

    public static <T> RestResponse<T> successful(T body) {
        return new RestResponse<T>().body(body).success();
    }

    public static <T> RestResponse<T> failed(String error, String...message) {
        return new RestResponse<T>().fail(error, message);
    }

    public static <T> RestResponse<T> failed(String error, int code, String...message) {
        return new RestResponse<T>().fail(error, code, message);
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isAuditable() {
        return auditable;
    }

    public String getError() {
        return error;
    }

    public int getStatus() {
        return status;
    }

    @Deprecated
    public RestResponse<T> success() {
        this.success = true;
        return this;
    }

    public RestResponse<T> dontAudit() {
        this.auditable = false;
        return this;
    }

    @Deprecated
    public RestResponse<T> fail(String error, String...message) {
        this.fail(error, 500, message);
        return this;
    }

    @SuppressWarnings({"unchecked", "varargs"})
    @Deprecated
    public RestResponse<T> fail(String error, int code, String...message) {
        this.success = false;
        this.error = error;
        this.status = code;
        if (message != null) {
            this.error = String.format(error, (Object[]) message);
        }
        return this;
    }

    public List<String> headerNames() {
        return new ArrayList<>(headers.keySet());
    }

    public RestResponse<T> header(String name, Object value) {
        LOG.debug("[addHeader] Adding header to response [{} -> {}]...", name, value);
        headers.put(name, value);
        return this;
    }

    public Object header(String name) {
        return headers.get(name);
    }

    public RestResponse<T> audit(String name, Object value) {
        auditAttributes.put(name, value);
        return this;
    }

    public Object audit(String name) {
        return auditAttributes.get(name);
    }

    public Set<String> auditAttributes() {
        return auditAttributes.keySet();
    }

    public boolean hasHeader(String name) {
        return headers.keySet()
                .stream()
                .anyMatch(key -> key.equals(name));
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public <R> R getBody(TypeReference<R> typeRef) {
        return new ObjectMapper().convertValue(body, typeRef);
    }

    public T getBody() {
        return new ObjectMapper().convertValue(body, new TypeReference<T>() {});
    }

    public RestResponse<T> body(T body) {
        this.body = body;
        return this;
    }
}
