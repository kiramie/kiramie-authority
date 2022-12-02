package com.kiramie.core.base.page;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @FileName: SearchPage
 * @Author: code-fusheng
 * @Date: 2021/6/11 9:02 上午
 * @Version: 1.0
 * @Description: 搜索Page对象
 */

@Data
public class SearchPage<T> implements Serializable {

    private static final long serialVersionUID = 5158070989805614529L;

    /**
     * asc 升序
     * desc 倒序
     */
    private static final String SORT_ASC = "asc";
    private static final String SORT_DESC = "desc";

    @ApiModelProperty("搜索关键字")
    private String keyword;

    @ApiModelProperty("检索目标字段")
    private String[] keyFields;

    @ApiModelProperty(value = "当前页号", example = "1")
    private Integer pageNo = 1;

    @ApiModelProperty(value = "分页大小", example = "10")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "条件参数")
    @Deprecated
    private Map<String, Object> params = new HashMap<>(16);

    @ApiModelProperty(value = "Es目标索引", hidden = true)
    @Deprecated
    private String index;

    @ApiModelProperty(value = "数据", hidden = true)
    private List<T> list;

    @ApiModelProperty(value = "总条数", hidden = true)
    private long total;

    @ApiModelProperty(value = "匹配最高分", hidden = true)
    private float maxScore;

    @ApiModelProperty(value = "排序列")
    private List<String> sortColumns;

    @ApiModelProperty(value = "排序方式")
    private List<String> sortMethods;

    public Integer getPageNo() {
        return pageNo <= 0 ? 1 : pageNo;
    }
}
