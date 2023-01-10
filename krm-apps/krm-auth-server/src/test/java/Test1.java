import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Function;

/**
 * @author yangbin
 * @since 2023/1/6
 **/
@Slf4j
public class Test1 {

    @Test
    public void t1() {
        log.info("t1 start...");
        CountDownLatch cdl = new CountDownLatch(3);
        CompletableFuture<Boolean> powerStateFuture = CompletableFuture.supplyAsync(() -> {
            try {
                int second = new Random().nextInt(5);
                TimeUnit.SECONDS.sleep(second);
                log.info("powerStateFuture...{}", second);
                cdl.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        });

        CompletableFuture<Integer> brightnessFuture = CompletableFuture.supplyAsync(() -> {
            try {
                int second = new Random().nextInt(5);
                TimeUnit.SECONDS.sleep(second);
                log.info("brightnessFuture...{}", second);
                cdl.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });

        CompletableFuture<Integer> colorTemperatureFuture = CompletableFuture.supplyAsync(() -> {
            try {
                int second = new Random().nextInt(5);
                TimeUnit.SECONDS.sleep(second);
                log.info("colorTemperatureFuture...{}", second);
                cdl.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 3600;
        });
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject() {{
            put("powerState", powerStateFuture.join());
            put("brightness", brightnessFuture.join());
            put("colorTemperature", colorTemperatureFuture.join());
        }};
        log.info("t1 end...{}", jsonObject.toJSONString());
    }

    @Test
    public void t2() {
        log.info("t1 start...");
        CountDownLatch cdl = new CountDownLatch(3);
        CompletableFuture<Boolean> powerStateFuture = CompletableFuture.supplyAsync(() -> {
            try {
                int second = new Random().nextInt(5);
                TimeUnit.SECONDS.sleep(second);
                log.info("powerStateFuture...{}", second);
                cdl.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("powerStateFuture timeout...{}", 6000);
            return false;
        }), Function.identity());

        CompletableFuture<Integer> brightnessFuture = CompletableFuture.supplyAsync(() -> {
            try {
                int second = new Random().nextInt(5);
                TimeUnit.SECONDS.sleep(second);
                log.info("brightnessFuture...{}", second);
                cdl.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 80;
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("brightnessFuture timeout...{}", 6000);
            return 1;
        }), Function.identity());

        CompletableFuture<Integer> colorTemperatureFuture = CompletableFuture.supplyAsync(() -> {
            try {
                int second = new Random().nextInt(5);
                TimeUnit.SECONDS.sleep(second);
                log.info("colorTemperatureFuture...{}", second);
                cdl.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 3600;
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("colorTemperatureFuture timeout...{}", 6000);
            return 1000;
        }), Function.identity());
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject() {{
            put("powerState", powerStateFuture.join());
            put("brightness", brightnessFuture.join());
            put("colorTemperature", colorTemperatureFuture.join());
        }};
        log.info("t1 end...{}", jsonObject.toJSONString());
    }

    @Test
    public void t3() {
        log.info("张三下班准备回家。。。");
        log.info("张三等待906,539公交。。。");
        CompletableFuture<String> busCF = CompletableFuture.supplyAsync(() -> {
                    log.info("906 路公交在路上。。。");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "906";
                })
                .applyToEither(CompletableFuture.supplyAsync(() -> {
                    log.info("539 路公交在路上。。。");
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "539";
                }), first -> first + "路公交")
                .applyToEither(CompletableFuture.supplyAsync(() -> {
                    log.info("208 路公交在路上。。。");
                    try {
                        Thread.sleep(6000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "208";
                }), Function.identity());
        log.info("张三坐上" + busCF.join());
    }

    @Test
    public void t4() {
        log.info("t1 start...");
        CountDownLatch cdl = new CountDownLatch(3);
        CompletableFuture<Boolean> powerStateFuture = CompletableFuture.supplyAsync(() -> {
            try {
                int second = new Random().nextInt(5);
                TimeUnit.SECONDS.sleep(second);
                log.info("powerStateFuture...{}", second);
                cdl.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        });

        CompletableFuture<Integer> brightnessFuture = CompletableFuture.supplyAsync(() -> {
            try {
                int second = new Random().nextInt(5);
                TimeUnit.SECONDS.sleep(second);
                log.info("brightnessFuture...{}", second);
                cdl.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 80;
        });

        CompletableFuture<Integer> colorTemperatureFuture = CompletableFuture.supplyAsync(() -> {
            try {
                int second = new Random().nextInt(5);
                TimeUnit.SECONDS.sleep(second);
                log.info("colorTemperatureFuture...{}", second);
                cdl.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 3600;
        });
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject() {{
            try {
                put("powerState", powerStateFuture.get(2, TimeUnit.SECONDS));
            } catch (TimeoutException e) {
                put("powerState", false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                put("brightness", brightnessFuture.get(2, TimeUnit.SECONDS));
            } catch (TimeoutException e) {
                put("brightness", 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                put("colorTemperature", colorTemperatureFuture.get(2, TimeUnit.SECONDS));
            } catch (TimeoutException e) {
                put("colorTemperature", 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }};
        log.info("t1 end...{}", jsonObject.toJSONString());
    }

    @Test
    public void t5() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        log.info("t1 start...");
        CountDownLatch cdl = new CountDownLatch(3);
        CompletableFuture<Boolean> powerStateFuture = CompletableFuture.supplyAsync(() -> {
            try {
                int second = new Random().nextInt(5);
                TimeUnit.SECONDS.sleep(second);
                log.info("powerStateFuture...{}", second);
                cdl.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }).applyToEither(new CompletableFuture<Boolean>(){{
            scheduledThreadPoolExecutor.schedule(() -> this.completeExceptionally(new TimeoutException()), 6, TimeUnit.SECONDS);
            //log.info("applyToEither powerStateFuture...");
        }}, Function.identity()).exceptionally(throwable -> {
            throwable.printStackTrace();
            log.error("powerStateFuture timeout:"+throwable.getMessage());
            return false;
        });

        CompletableFuture<Integer> brightnessFuture = CompletableFuture.supplyAsync(() -> {
            try {
                int second = new Random().nextInt(5);
                TimeUnit.SECONDS.sleep(second);
                log.info("brightnessFuture...{}", second);
                cdl.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 80;
        }).applyToEither(new CompletableFuture<Integer>(){{
            //ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
            scheduledThreadPoolExecutor.schedule(() -> this.completeExceptionally(new TimeoutException()), 6, TimeUnit.SECONDS);
            //log.info("applyToEither brightnessFuture...");
        }}, Function.identity()).exceptionally(throwable -> {
            throwable.printStackTrace();
            log.error("brightnessFuture timeout:"+throwable.getMessage());
            return 1;
        });

        CompletableFuture<Integer> colorTemperatureFuture = CompletableFuture.supplyAsync(() -> {
            try {
                int second = new Random().nextInt(5);
                TimeUnit.SECONDS.sleep(second);
                log.info("colorTemperatureFuture...{}", second);
                cdl.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 3600;
        }).applyToEither(new CompletableFuture<Integer>(){{
            //ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
            scheduledThreadPoolExecutor.schedule(() -> this.completeExceptionally(new TimeoutException()), 6, TimeUnit.SECONDS);
            //log.info("applyToEither colorTemperatureFuture...");
        }}, Function.identity()).exceptionally(throwable -> {
            throwable.printStackTrace();
            log.error("colorTemperatureFuture timeout:"+throwable.getMessage());
            return 1000;
        });
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject() {{
            put("powerState", powerStateFuture.join());
            put("brightness", brightnessFuture.join());
            put("colorTemperature", colorTemperatureFuture.join());
        }};
        log.info("t1 end...{}", jsonObject.toJSONString());
    }

    @Test
    public void t6() {

    }
}
