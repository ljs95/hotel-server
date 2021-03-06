package cn.hotel.mainserver.common.bean;

/**
 * @author Johnson
 * @date 2019/12/30/ 16:26:16
 */
@FunctionalInterface
public interface CustomBeanUtilsCallBack<S, T> {

    void callBack(S t, T s);
}
