package net.xdclass.exception.code;

import java.util.HashMap;
import java.util.Map;

public enum ExceptionCodeEnum {

    /**
     * 错误代码
     */
    FLOW_EXCEPTION(-1, "限流异常"),
    DEGRADE_EXCEPTION(-2, "降级异常"),
    PARAM_FLOW_EXCEPTION(-3, "参数限流异常"),
    SYSTEM_BLOCK_EXCEPTION(-4, "系统负载异常"),
    AUTHORITY_EXCEPTION(-5, "授权异常");
    /**
     * 错误码
     */
    private int code;
    /**
     * 错误消息
     */
    private String msg;

    public static String getExceptionCode(Integer code) {
        for (ExceptionCodeEnum type : ExceptionCodeEnum.values()) {
            if (type.getCode() == code) {
                return type.msg;
            }
        }
        return null;
    }

    ExceptionCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
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

    public static Map<String, Object> getMapMode(Integer code){
        Map<String, Object> backMap = new HashMap<>();
        for (ExceptionCodeEnum type : ExceptionCodeEnum.values()) {
            if (type.getCode() == code) {
                backMap.put("code", code);
                backMap.put("msg", type.getMsg());
            }
        }
        return backMap;
    }
}

