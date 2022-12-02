package com.kiramie.core.base.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;

@Data
public class PageData<T> {

    /**
     * 总数
     */
    private long total;

    /**
     * 当前list
     */
    private List<T> list;

    /**
     * 当前页数
     */
    private int pageNum;

    /**
     * 每页显示条数
     */
    private int pageSize;


    public PageData(IPage page) {
        this.list = page.getRecords();
        this.total = page.getTotal();
        this.pageNum = (int) page.getCurrent();
        this.pageSize = Math.toIntExact(page.getSize());
    }

    public PageData(List<T> list, Integer pageSize, Integer pageNum) {
        this.list = list;
        this.pageNum = pageNum == null ? 0 : pageNum;
        this.pageSize = pageSize == null ? 0 : pageSize;
        this.total = list.size();
    }
}
