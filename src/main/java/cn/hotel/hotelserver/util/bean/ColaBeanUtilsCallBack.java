package cn.hotel.hotelserver.util.bean;

/**
 * @author Johnson
 * @date 2019/12/30/ 16:26:16
 */
@FunctionalInterface
public interface ColaBeanUtilsCallBack<S, T> {

    void callBack(S t, T s);
}
