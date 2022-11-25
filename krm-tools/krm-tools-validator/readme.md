# valid验参速查

### 1.字符串非空

```java
@NotBlank(message = "下架原因不能为空")
private String offReason;
```

### 2.任意类型非空（!=null）

```java
@NotNull(message = "渠道价比率不能为空")
private Integer channelPriceRatio;
```

### 3.集合非空(Collection、Map、数组，长度不为0、集合大小不为0)

```java
@NotEmpty(message = "标源id集合不能为空")
private List<String> ids;
```

### 4.范围（BigDecimal,BigInteger,CharSequence, byte, short, int, long等原子类型和包装类型，验证注解的元素值在最小值和最大值之间）

```java
@Range(min = 1, max = 100, message = "条数必须为1~100之间的整数")
private Integer num = 10;
```

### 5.正数（BigDecimal、BigInteger、byte 、 short 、 int 、 long 、 float 、 double以及它们各自的包装器）

```java
@Positive(message = "出售底价必须是正数")
private Long basePrice;
```

### 6.元素值的整数位数和小数位数上限

```java
@Positive(message = "价格下限必须是正数")
@Digits(integer = 20, fraction = 2, message = "价格只允许在20位整数和2位小数范围内")
private BigDecimal priceUpper;
```

### 7.嵌套

```java
@Valid // 嵌套验证必须用@Valid
@NotNull(message = "标源编号list不能为空")
@Size(min = 1, message = "标源编号list个数至少为1")
private List<@NotBlank(message = "标源编号不能为空") String> ids;
```

### Controller

```java
package com.gongsibao.controller;

import com.alibaba.fastjson.JSONObject;
import com.gongsibao.common.enums.ExportStateEnum;
import com.gongsibao.core.base.BaseResponse;
import com.gongsibao.core.base.page.PageData;
import com.gongsibao.request.tmSourceManage.*;
import com.gongsibao.response.tmSourceManage.*;
import com.gongsibao.service.TmSourceManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.MessageFormat;
import java.util.List;

/**
 * @Author YangBin
 * @Date 2021/7/21
 **/
@Api(tags = "标源管理")
@RestController
@RequestMapping("/tmSourceManage")
@Validated//单个验参
/**
 * @GetMapping("/detail/{id}")
 * @ApiOperation("商标详情")
 * public BaseResponse<TmDetailResponse> tradeMarkDetail(@PathVariable @NotNull String id) {
 *     return BaseResponse.success(searchService.tradeMarkDetail(id));
 * }
 */
public class TmSourceManageController {

    @Resource
    private TmSourceManageService tmSourceManageService;

    @ApiOperation(value = "业务员下拉", notes = "http://ww1.sinaimg.cn/large/007bJVBggy1gsvb59h41xj60nr0af78402.jpg")
    @GetMapping("/empOption")
    public BaseResponse<List<EmpOptionResponse>> listEmpOption() {
        return BaseResponse.success(tmSourceManageService.listEmpOption());
    }

    @ApiOperation(value = "标源管理列表查询", notes = "http://ww1.sinaimg.cn/large/007bJVBggy1gsvb59h41xj60nr0af78402.jpg")
    @PostMapping("/listTmSource")
    public BaseResponse<PageData<ListTmSourceResponse>> listTmSource(@RequestBody @Valid TmSourceManageListVo vo) {
        return BaseResponse.success(tmSourceManageService.listTmSource(vo));
    }

    @ApiOperation(value = "标源管理列表查询导出", notes = "http://ww1.sinaimg.cn/large/007bJVBggy1gsvb59h41xj60nr0af78402.jpg")
    @PostMapping(value = "/exportListTmSource")
    public BaseResponse<String> exportListTmSource(@RequestBody @Valid TmSourceManageListVo vo) {
        return BaseResponse.success(tmSourceManageService.exportListTmSource(vo));
    }

    @ApiOperation(value = "根据id获取导出记录的url", notes = "http://ww1.sinaimg.cn/large/007bJVBggy1gsvb59h41xj60nr0af78402.jpg")
    @GetMapping(value = "/getExportRecord/{id}")
    public BaseResponse getExportRecord(@PathVariable("id") String id) {
        JSONObject exportRecord = tmSourceManageService.getExportRecord(id);
        Integer exportState = exportRecord.getInteger("exportState");
        String exportUrl = exportRecord.getString("exportUrl");
        if (StringUtils.isNotBlank(exportUrl) && exportState == ExportStateEnum.GENERATE_SUCCESS.getCode()) {
            return BaseResponse.success("文件生成成功！", exportRecord);
        }
        if (exportState == ExportStateEnum.GENERATE_FALSE.getCode()) {
            return BaseResponse.success("文件生成失败!", exportRecord);
        }
        return BaseResponse.success("文件正在生成…", exportRecord);
    }

    @ApiOperation(value = "查看详情", notes = "http://ww1.sinaimg.cn/large/007bJVBggy1gsvb59h41xj60nr0af78402.jpg")
    @PostMapping(value = "/getTmSourceDetail")
    public BaseResponse<TmSourceDetailResponse> getTmSourceDetail(@RequestBody @Valid TmSourceDetailVo vo) {
        return BaseResponse.success(tmSourceManageService.getTmSourceDetail(vo));
    }

    @ApiOperation(value = "根据标源id查询操作日志", notes = "http://ww1.sinaimg.cn/large/007bJVBggy1gsvb59h41xj60nr0af78402.jpg")
    @PostMapping(value = "/listTmOpLog")
    public BaseResponse<List<ListTmOpLogResponse>> listTmOpLog(@RequestBody @Valid TmIdVo vo) {
        return BaseResponse.success(tmSourceManageService.listTmOpLog(vo));
    }

    @ApiOperation(value = "查看详情-价格调整(待发布、已下架)", notes = "http://ww1.sinaimg.cn/large/007bJVBggy1gsvb59h41xj60nr0af78402.jpg")
    @PostMapping(value = "/updatePrice")
    public BaseResponse<UpdatePriceResponse> updatePrice(@RequestBody @Valid UpdatePriceVo vo) {
        return BaseResponse.success(tmSourceManageService.updatePrice(vo));
    }

    @ApiOperation(value = "单个发布", notes = "http://ww1.sinaimg.cn/large/007bJVBggy1gsvb59h41xj60nr0af78402.jpg")
    @PostMapping(value = "/singlePublish")
    public BaseResponse<String> singlePublish(@RequestBody @Valid SinglePublishVo vo) {
        String id = tmSourceManageService.singlePublish(vo);
        return BaseResponse.success(MessageFormat.format("单个发布成功，标源编号:{0}", id), id);
    }

    @ApiOperation(value = "单个下架", notes = "http://ww1.sinaimg.cn/large/007bJVBggy1gsvb59h41xj60nr0af78402.jpg")
    @PostMapping(value = "/singleOff")
    public BaseResponse<String> singleOff(@RequestBody @Valid SingleOffVo vo) {
        String id = tmSourceManageService.singleOff(vo);
        return BaseResponse.success(MessageFormat.format("单个下架成功，标源编号:{0}", id), id);
    }

    @ApiOperation(value = "转让中的标源标记为已转让(单个)", notes = "http://ww1.sinaimg.cn/large/007bJVBggy1gsvb59h41xj60nr0af78402.jpg")
    @PostMapping(value = "/markTraded")
    public BaseResponse<String> markTraded(@RequestBody @Valid TmSourceIdVo vo) {
        return BaseResponse.success(tmSourceManageService.markTraded(vo));
    }

    @ApiOperation(value = "批量发布", notes = "http://ww1.sinaimg.cn/large/007bJVBggy1gsvb59h41xj60nr0af78402.jpg")
    @PostMapping(value = "/batchPublish")
    public BaseResponse<List<String>> batchPublish(@RequestBody @Valid BatchPublishTmVo vo) {
        List<String> ids = tmSourceManageService.batchPublishTm(vo);
        return BaseResponse.success("批量发布成功", ids);
    }

    @ApiOperation(value = "批量下架", notes = "http://ww1.sinaimg.cn/large/007bJVBggy1gsvb59h41xj60nr0af78402.jpg")
    @PostMapping(value = "/batchOff")
    public BaseResponse<List<String>> batchOff(@RequestBody @Valid BatchOffTmVo vo) {
        BatchUpdateResponse batchUpdateResponse = tmSourceManageService.batchOffTm(vo);
        List<String> updateSuccessIds = batchUpdateResponse.getUpdateSuccessIds();
        List<String> updateFalseIds = batchUpdateResponse.getUpdateFalseIds();
        if (CollectionUtils.isEmpty(updateSuccessIds)) {
            String message = MessageFormat.format("{0}个标源{1}批量下架失败(传入的商标编号中存在商标状态不是\"待发布\"或\"已发布(未转让)\"的情况)"
                    , updateFalseIds.size(), updateFalseIds);
            return BaseResponse.error(message);
        }
        if (!CollectionUtils.isEmpty(updateFalseIds)) {
            String message = MessageFormat.format("{0}个标源{1}批量下架成功,{2}个标源{3}失败(传入的商标编号中存在商标状态不是\"待发布\"或\"已发布(未转让)\"的情况)"
                    , updateSuccessIds.size(), updateSuccessIds, updateFalseIds.size(), updateFalseIds);
            return BaseResponse.success(message, updateSuccessIds);
        }
        return BaseResponse.success("批量下架成功", updateSuccessIds);
    }

    @ApiOperation(value = "转让中的标源标记为已转让(批量)", notes = "http://ww1.sinaimg.cn/large/007bJVBggy1gsvb59h41xj60nr0af78402.jpg")
    @PostMapping(value = "/batchMarkTraded")
    public BaseResponse<List<String>> batchMarkTraded(@RequestBody @Valid BatchCurationVo vo) {
        BatchUpdateResponse batchUpdateResponse = tmSourceManageService.batchMarkTraded(vo);
        List<String> updateSuccessIds = batchUpdateResponse.getUpdateSuccessIds();
        List<String> updateFalseIds = batchUpdateResponse.getUpdateFalseIds();
        if (CollectionUtils.isEmpty(updateSuccessIds)) {
            String message = MessageFormat.format("{0}个标源{1}批量转让失败(传入的商标编号中存在商标状态不是\"转让中\"的情况)"
                    , updateFalseIds.size(), updateFalseIds);
            return BaseResponse.error(message);
        }
        if (!CollectionUtils.isEmpty(updateFalseIds)) {
            String message = MessageFormat.format("{0}个标源{1}批量转让成功,{2}个标源{3}失败(传入的商标编号中存在商标状态不是\"转让中\"的情况)"
                    , updateSuccessIds.size(), updateSuccessIds, updateFalseIds.size(), updateFalseIds);
            return BaseResponse.success(message, updateSuccessIds);
        }
        return BaseResponse.success("批量转让成功", updateSuccessIds);
    }

    @ApiOperation(value = "批量标记精选", notes = "http://ww1.sinaimg.cn/large/007bJVBggy1gsvb59h41xj60nr0af78402.jpg")
    @PostMapping(value = "/batchCuration")
    public BaseResponse<List<String>> batchCuration(@RequestBody @Valid BatchCurationVo vo) {
        BatchUpdateResponse batchUpdateResponse = tmSourceManageService.batchCuration(vo);
        List<String> updateSuccessIds = batchUpdateResponse.getUpdateSuccessIds();
        List<String> updateFalseIds = batchUpdateResponse.getUpdateFalseIds();
        if (CollectionUtils.isEmpty(updateSuccessIds)) {
            String message = MessageFormat.format("{0}个标源{1}批量标记精选失败(传入的商标编号中存在商标已经标记精选的情况)"
                    , updateFalseIds.size(), updateFalseIds);
            return BaseResponse.error(message);
        }
        if (!CollectionUtils.isEmpty(updateFalseIds)) {
            String message = MessageFormat.format("{0}个标源{1}批量标记精选成功,{2}个标源{3}失败(传入的商标编号中存在商标已经标记精选的情况)"
                    , updateSuccessIds.size(), updateSuccessIds, updateFalseIds.size(), updateFalseIds);
            return BaseResponse.success(message, updateSuccessIds);
        }
        return BaseResponse.success("批量标记精选成功", updateSuccessIds);
    }

    @ApiOperation(value = "单个取消精选", notes = "http://ww1.sinaimg.cn/large/007bJVBggy1gsvb59h41xj60nr0af78402.jpg")
    @PostMapping(value = "/cancelCuration")
    public BaseResponse<String> cancelCuration(@RequestBody @Valid TmSourceIdVo vo) {
        return BaseResponse.success(tmSourceManageService.cancelCuration(vo));
    }
}
```

### 异常全局处理

```java
/**
 * 捕获BindException:实体对象前不加@RequestBody注解,单个对象内属性校验未通过抛出的异常类型
 *
 * @param e BindException实例
 * @return
 */
@ResponseBody
@ExceptionHandler(BindException.class)
public BaseResponse handleException(BindException e) {
    log.error("方法参数校验异常：{}", e.getMessage(), e);
    BindingResult bindingResult = e.getBindingResult();
    if (bindingResult.hasErrors()) {
        Map<String, String> errorMap = new HashMap<>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            String field = fieldError.getField();
            String defaultMessage = fieldError.getDefaultMessage();
            errorMap.put(field, defaultMessage);
        });
        return BaseResponse.error("参数校验失败。" + StringUtils.join(errorMap.values(), ","), errorMap);
    }
    return BaseResponse.error("参数校验失败");
}

/**
     * 捕获ConstraintViolationException：方法参数校验异常。JDK自带类型 参数校验不通过时 报此错，如8大类型包装类、String等
     *
     * @param e ConstraintViolationException实例等
     * @return
     */
@ResponseBody
@ExceptionHandler(ConstraintViolationException.class)
public BaseResponse handleException(ConstraintViolationException e) {
    log.error("方法参数校验异常：{}", e.getMessage(), e);
    StringBuffer sb = new StringBuffer();
    Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
    if (CollectionUtils.isNotEmpty(constraintViolations)) {
        for (ConstraintViolation constraintViolation : constraintViolations) {
            sb.append(constraintViolation.getMessage());
        }
    }
    return BaseResponse.error("参数校验失败。" + sb);
}
```



[优雅的校验参数-javax.validation - 简书 (jianshu.com)](https://www.jianshu.com/p/67d3637493c7)