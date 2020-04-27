package cn.hotel.hotelserver.vo;

import javax.validation.constraints.NotNull;

/**
 * @author Johnson
 * @date 2020/03/31/ 15:16:16
 */
public class Pagination {

    @NotNull(message = "页数不能为空")
    private Integer page;

    @NotNull(message = "查询大小不能为空")
    private Integer size;

    public Pagination(){}

    public Pagination(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    public Integer getPage() {
        return page > 1 ? (page - 1) * size : 0;
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
