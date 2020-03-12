package cn.hotel.hotelserver.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;

public class ResponseVo {

    private Integer code;

    private String msg;

    private Object data;

    public ResponseVo(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseVo success(String msg) {
        return success(msg, null);
    }

    public static ResponseVo success(String msg, Object data) {
        return new ResponseVo(200, msg, data);
    }

    public static ResponseVo successData(Object data) {
        return new ResponseVo(200, null, data);
    }

    public static ResponseVo error(String msg) {
        return error(msg, null);
    }

    public static ResponseVo error(String msg, Object data) {
        return new ResponseVo(500, msg, data);
    }

    public static ResponseVo error(int code, String msg) {
        return new ResponseVo(code, msg, null);
    }

    public static void responseJson(ServletResponse response, String msg, Object data) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            String result = new ObjectMapper().writeValueAsString(ResponseVo.success(msg, data));
            out = response.getWriter();
            out.write(result);
        } catch (Exception e) {
            // log.error("【JSON输出异常】"+e);
        } finally{
            if(out!=null){
                out.flush();
                out.close();
            }
        }
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
