package com.kiramie.core.base.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

/***
 *
 *
 * 描    述：分页
 *
 * 创 建 者： @author wangxiaodong
 * 创建时间： 2020.09.05
 * 创建描述：
 *
 * 修 改 者：
 * 修改时间：
 * 修改描述：
 *
 * 审 核 者：
 * 审核时间：
 * 审核描述：
 *
 */
@Accessors(chain = true)
@Data
public abstract class LimitVo<T> {

    /**
     * asc 升序
     * desc 倒序
     */
    private static final String SORT_ASC = "asc";
    private static final String SORT_DESC = "desc";

    /**
     * 当前页数
     */
    @ApiModelProperty(value = "当前页数", example = "1")
    private Integer pageNum = 1;

    /**
     * 显示条数
     */
    @ApiModelProperty(value = "显示条数", example = "10")
    private Integer pageSize = 10;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private String beginTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private String endTime;

    private String sortColumn;

    private String sortMethod = "asc";

    public void setSortMethod(String sortMethod) {
        if (StringUtils.isNotBlank(sortMethod)) {
            if (sortMethod.toLowerCase().startsWith(SORT_ASC)) {
                this.sortMethod = SORT_ASC;
            } else if (sortMethod.toLowerCase().startsWith(SORT_DESC)) {
                this.sortMethod = SORT_DESC;
            }
        }
    }

    public String getSortColumn() {
        if (StringUtils.isNotBlank(this.sortColumn)) {
            return this.upperCharToUnderLine(this.sortColumn);
        }
        return null;
    }

    @ApiModelProperty(hidden = true)
    public IPage<T> getPage() {
        return new Page<T>().setCurrent(this.pageNum).setSize(this.pageSize);
    }

    /**
     * 驼峰转下划线
     */
    @SuppressWarnings("all")
    public String upperCharToUnderLine(String param) {
        StringBuilder sb = new StringBuilder(param);
        int temp = 0;
        if (!param.contains("_")) {
            for (int i = 0; i < param.length(); i++) {
                if (Character.isUpperCase(param.charAt(i))) {
                    sb.insert(i + temp, "_");
                    temp += 1;
                }
            }
        }
        return sb.toString().toLowerCase();
    }
}
