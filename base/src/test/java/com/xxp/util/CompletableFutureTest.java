package com.xxp.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author: xiexipeng
 * @create: 2021/04/22 11:43:30
 * @description:
 * @Version
 **/
@Slf4j
public class CompletableFutureTest {

    @Test
    public void test_getAndApply() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFutureUtil.supplyAndApply(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new Random().nextInt();
        }, n -> {
            log.info("" + n);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("处理完成");
            return "add";
        });
        log.info("主线程处理中");
        log.info(future.get());
    }

    @Test
    public void test_getAndApplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFutureUtil.supplyAndApplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new Random().nextInt();
        }, n -> {
            System.out.println(n);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("处理完成");
            return "add";
        });
        log.info("主线程处理中");
        System.out.println(future.get());
    }

    @Test
    public void test_supplyAndAcceptOther() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFutureUtil.supplyAndAcceptOther(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new Random().nextInt();
        }, CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "other";
        }), (a, b) -> {
            log.info(a + ":" + b);
        });
        future.get();
    }

    @Test
    public void test_supplyAndCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<Long> future = CompletableFutureUtil.supplyAndCombine(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new Random().nextInt();
        }, CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "other";
        }), (a, b) -> {
            log.info(a + ":" + b);
            return 121L;
        });
        log.info("" + future.get());
    }

    @Test
    public void test_supplyAndCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFutureUtil.supplyAndCompose(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new Random().nextInt();
        }, (current) -> {
            return CompletableFuture.supplyAsync(() -> {
                log.info("" + current);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "other";
            });
        });
        log.info("" + future.get());
    }

    @Test
    public void test_supplyAndToEither() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFutureUtil.supplyAndToEither(() -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 11;
                }, CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 22;
                }), (fast) -> {
                    log.info("" + fast);
                    return "fast";
                }
        );
        log.info("" + future.get());
    }

    @Test
    public void test_supplyAndExceptionally() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFutureUtil.supplyAndExceptionally(() -> {
                    throw new RuntimeException("手动异常");
                }, e -> {
                    e.printStackTrace();
                    return 11;
                }
        );
        log.info("" + future.get());
    }

    @Test
    public void test_supplyAndWhenComplete1() throws ExecutionException, InterruptedException {
        CompletableFuture<Object> future1 = CompletableFutureUtil.supplyAndWhenComplete(() -> {
            throw new RuntimeException("手动异常");
        }, (r, e) -> {
            if (e != null) {
                e.printStackTrace();
                return;
            }
            log.info("" + r);
        });
        log.info("" + future1.get());
    }

    @Test
    public void test_supplyAndWhenComplete2() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future2 = CompletableFutureUtil.supplyAndWhenComplete(() -> {
            return 11;
        }, (r, e) -> {
            if (e != null) {
                e.printStackTrace();
                return;
            }
            log.info("" + r);
        });
        log.info("" + future2.get());
    }

    @Test
    public void test() throws ExecutionException, InterruptedException {
        CompletableFuture<? extends Serializable> future = CompletableFutureUtil.supplyAndHandle(() -> {
//            throw new RuntimeException("手动异常");
            return 121;
        }, (a, e) -> {
            if (e != null) {
                e.printStackTrace();
                return "aa";
            }
            log.info(a.toString());
            return "bb";
        });
        log.info("" + future.get());
    }

    @Test
    public void test_join1() {
        List<Integer> result = new ArrayList<>();
        CompletableFutureUtil.Pair<Integer, String> pair1 = new CompletableFutureUtil.Pair<>(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 121;
        }, (r, e) -> {
            if (e != null) {
                return "";
            }
            log.info(r.toString());
            result.add(r);
            return "";
        });
        CompletableFutureUtil.Pair<Integer, String> pair2 = new CompletableFutureUtil.Pair<>(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 22;
        }, (r, e) -> {
            if (e != null) {
                return "";
            }
            log.info(r.toString());
            result.add(r);
            return "";
        });
        CompletableFutureUtil.join(Arrays.asList(pair1, pair2));
        log.info(result.toString());
    }

    @Test
    public void test_join2() {
        List<Integer> result = new ArrayList<>();
        CompletableFutureUtil.Pair<Integer, String> pair1 = new CompletableFutureUtil.Pair<>(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 121;
        }, (r, e) -> {
            if (e != null) {
                return "";
            }
            log.info(r.toString());
            result.add(r);
            return "";
        });
        CompletableFutureUtil.Pair<Integer, String> pair2 = new CompletableFutureUtil.Pair<>(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            throw new RuntimeException("手动异常");
        }, (r, e) -> {
            if (e != null) {
                throw new RuntimeException("手动异常1");
            }
            log.info(r.toString());
            result.add(r);
            return "";
        });
        CompletableFutureUtil.Pair<Integer, String> pair3 = new CompletableFutureUtil.Pair<>(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            throw new RuntimeException("手动异常");
        }, (r, e) -> {
            if (e != null) {
                throw new RuntimeException("手动异常2");
            }
            log.info(r.toString());
            result.add(r);
            return "";
        });

        CompletableFutureUtil.Pair<Integer, String> pair4 = new CompletableFutureUtil.Pair<>(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            throw new RuntimeException("手动异常");
        }, (r, e) -> {
            if (e != null) {
                return "";
            }
            log.info(r.toString());
            result.add(r);
            return "";
        });


        CompletableFutureUtil.Pair<Integer, String> pair5 = new CompletableFutureUtil.Pair<>(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            throw new RuntimeException("手动异常");
        }, (r, e) -> {
            if (e != null) {
                return "";
            }
            log.info(r.toString());
            result.add(r);
            return "";
        });
        try {
            log.info("开始执行join");
            CompletableFutureUtil.join(Arrays.asList(pair1, pair2, pair3,pair4,pair5));
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("result:" + result.toString());
    }
}
