package cn.hotel.mainserver.util.response;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;

/**
 * @author Johnson
 * @date 2020/01/15/ 16:58:59
 */
public class ResponseJson {

    public static void responseJson(ServletResponse response, ResponseVo responseVo) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            String result = new ObjectMapper().writeValueAsString(responseVo);
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
}
