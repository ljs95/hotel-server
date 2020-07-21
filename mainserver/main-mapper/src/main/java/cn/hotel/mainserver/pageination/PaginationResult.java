package cn.hotel.mainserver.pageination;

/**
 * 分页查询类
 * @author Johnson
 * @date 2020/03/30/ 18:06:43
 */
public class PaginationResult {

    private Long count;

    private Object data;

    public PaginationResult(Long count, Object object) {
        this.count = count;
        this.data = object;
    }

    public Long getCount() {
        return count;
    }

    public Object getData() {
        return data;
    }
}
