package cn.hotel.hotelserver.vo;

/**
 * @author Johnson
 * @date 2020/03/31/ 15:16:16
 */
public class Pagination {

    private Integer page;

    private Integer size;

    public Integer getPage() {
        return (page - 1) * size;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
