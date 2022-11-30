```java
/**
     * 调整收藏次数(加一、减一)
     *
     * @param targetId     内容Id
     * @param targetDesc   内容描述
     * @param businessType 操作业务类型
     * @param sourceMark   资源标识
     * @param tenantId     租户ID
     * @param changeType   调整类型（加一 up ，减一 down）
     * @return
     */
    public void changeCollectionCount(String targetId, String targetDesc, String businessType
            , Integer sourceMark, String tenantId, String changeType) {
        Assert.isTrue(StringUtils.isNotBlank(targetId), "内容Id 不能为空！");
        Assert.isTrue(StringUtils.isNotBlank(tenantId), "租户ID 不能为空！");
        if (CHANGE_TYPE_UP.equals(changeType)) {
            log.info("收藏次数+1服务开始：targetId={},tenantId={}", targetId, tenantId);
        } else if (CHANGE_TYPE_DOWN.equals(changeType)) {
            log.info("收藏次数-1服务开始：targetId={},tenantId={}", targetId, tenantId);
        } else {
            log.error("收藏次数的调整类型[{}]输入有误！", changeType);
            throw new BusinessException("收藏次数的调整类型[" + changeType + "]输入有误！");
        }
        String lockKey = null;
        boolean isGetLock = false;
        try {
            //业务操作类型+主键id 创建redisson锁锁
            lockKey = "incrementCount_" + businessType + "_" + targetId;
            log.info("redisson锁：{}", lockKey);
            isGetLock = redissonDistributedLocker.tryLock(lockKey, TimeUnit.SECONDS, 30, Integer.MAX_VALUE);
            if (isGetLock) {
                Integer selectCount = statisticsMapper.selectCount(Wrappers.<Statistics>lambdaQuery().eq(Statistics::getTargetId, targetId)
                        .eq(Statistics::getTargetType, businessType));
                //存在修改，不存在增加
                if (selectCount != null && selectCount > 0) {
                    //收藏次数(加一、减一)
                    if (CHANGE_TYPE_UP.equals(changeType)) {
                        statisticsMapper.incrementCount(targetId, CountTypeEnum.COLLECT_COUNT.getCountType(), targetDesc, businessType, sourceMark);
                    } else {
                        statisticsMapper.collectDecrementCount(targetId, CountTypeEnum.COLLECT_COUNT.getCountType(), targetDesc, businessType, sourceMark);
                    }
                } else {
                    statisticsMapper.insert(new Statistics() {{
                        setTargetId(targetId);
                        setTargetDesc(targetDesc);
                        setTargetType(businessType);
                        setSourceMark(sourceMark);
                        setCollectCount(1);
                    }});
                }
                log.info("调整收藏次数服务结束：TargetId={} tenantId={}", targetId, tenantId);
            } else {
                throw new BusinessException("次数自增服务超过锁的等待时间，放弃任务：TargetId=" + targetId + "tenantId=" + tenantId);
            }
        } catch (Exception e) {
            log.error("TargetId:{},自增异常:{}", tenantId, e.getMessage(), e);
            throw e;
        } finally {
            if (isGetLock) {
                log.info("--------------------------释放锁-----------------------");
                redissonDistributedLocker.unlock(lockKey);
            }
        }
    }
```

