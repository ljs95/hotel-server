package cn.hotel.mainserver.common.exception;

/**
 * @author Johnson
 * @date 2020/04/01/ 11:50:55
 */
public class CustomException extends RuntimeException {
    public CustomException(String msg) {
        super(msg);
    }
}
