package cn.hotel.hotelserver.vo;

/**
 * 分页查询类
 * @author Johnson
 * @date 2020/03/30/ 18:06:43
 */
public class PaginationResult {

    private Integer count;

    private Object data;

    public PaginationResult(Integer count, Object object) {
        this.count = count;
        this.data = object;
    }

    public Integer getCount() {
        return count;
    }

    public Object getData() {
        return data;
    }
}
