package net.xdclass.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSON;
import net.xdclass.exception.code.ExceptionCodeEnum;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class XdClassUrlBlockHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        Map<String, Object> backMap = new HashMap<>();
        if (e instanceof FlowException) {
            backMap = ExceptionCodeEnum.getMapMode(ExceptionCodeEnum.FLOW_EXCEPTION.getCode());
        } else if (e instanceof DegradeException) {
            backMap = ExceptionCodeEnum.getMapMode(ExceptionCodeEnum.DEGRADE_EXCEPTION.getCode());
        } else if (e instanceof ParamFlowException) {
            backMap = ExceptionCodeEnum.getMapMode(ExceptionCodeEnum.PARAM_FLOW_EXCEPTION.getCode());
        } else if (e instanceof SystemBlockException) {
            backMap = ExceptionCodeEnum.getMapMode(ExceptionCodeEnum.SYSTEM_BLOCK_EXCEPTION.getCode());
        } else if (e instanceof AuthorityException) {
            backMap = ExceptionCodeEnum.getMapMode(ExceptionCodeEnum.AUTHORITY_EXCEPTION.getCode());
        }

        httpServletResponse.setStatus(200);
        httpServletResponse.setHeader("content-Type","application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(backMap));
    }
}