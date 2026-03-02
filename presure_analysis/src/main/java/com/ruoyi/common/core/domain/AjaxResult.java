package com.ruoyi.common.core.domain;

import java.io.Serializable;

/**
 * 响应信息主体
 *
 * @author ruoyi
 */
public class AjaxResult implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 状态码 */
    public static final String CODE_TAG = "code";

    /** 返回内容 */
    public static final String MSG_TAG = "msg";

    /** 数据对象 */
    public static final String DATA_TAG = "data";

    /** 状态类型 */
    public enum Type {
        /** 成功 */
        SUCCESS(200),
        /** 警告 */
        WARN(301),
        /** 错误 */
        ERROR(500);
        
        private final int value;

        Type(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

    private int code;
    private String msg;
    private Object data;

    public AjaxResult() {
    }

    public AjaxResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public AjaxResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static AjaxResult success() {
        return new AjaxResult(Type.SUCCESS.value, "操作成功");
    }

    public static AjaxResult success(String msg) {
        return new AjaxResult(Type.SUCCESS.value, msg);
    }

    public static AjaxResult success(Object data) {
        return new AjaxResult(Type.SUCCESS.value, "操作成功", data);
    }

    public static AjaxResult success(String msg, Object data) {
        return new AjaxResult(Type.SUCCESS.value, msg, data);
    }

    public static AjaxResult error() {
        return new AjaxResult(Type.ERROR.value, "操作失败");
    }

    public static AjaxResult error(String msg) {
        return new AjaxResult(Type.ERROR.value, msg);
    }

    public static AjaxResult error(int code, String msg) {
        return new AjaxResult(code, msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
