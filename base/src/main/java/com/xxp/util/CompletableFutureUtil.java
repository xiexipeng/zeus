package com.xxp.util;

import java.util.List;
import java.util.concurrent.*;
import java.util.function.*;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/04/22 10:58:34
 * @description: accept与apply的区别：一般accept方法没有返回值，只单纯消费异步结果，apply除了消费结果还可以返回消费的处理结果
 * 方法是否带async：带async的表明其执行由新的线程执行，而不带async的则由其调用方的线程执行
 * @Version
 **/
public class CompletableFutureUtil {

    /**
     * 异步获取结果
     *
     * @param supplier
     * @param <T>
     * @return
     */
    public static <T> CompletableFuture<T> supplyAsync(Supplier<T> supplier) {
        return CompletableFuture.supplyAsync(supplier);
    }

    /**
     * 异步获取结果
     *
     * @param supplier
     * @param <T>
     * @return
     */
    public static <T> CompletableFuture<T> supplyAsync(Supplier<T> supplier, Executor executor) {
        return CompletableFuture.supplyAsync(supplier, executor);
    }

    /**
     * 提交异步任务，无返回值
     *
     * @param runnable
     * @return
     */
    public static CompletableFuture<Void> submit(Runnable runnable) {
        return CompletableFuture.runAsync(runnable);
    }

    /**
     * 提交异步任务，无返回值
     *
     * @param runnable
     * @return
     */
    public static CompletableFuture<Void> submit(Runnable runnable, Executor executor) {
        return CompletableFuture.runAsync(runnable, executor);
    }

    /**
     * 线程串型化，异步提交，使用提交的线程同步处理结果
     * thenApply与thenApplyAsync的区别：在于其执行线程由supplyAsync的执行线程执行，还是由非supplyAsync执行线程执行
     *
     * @param supplier
     * @param function
     * @param <T>
     * @param <U>
     * @return
     */
    public static <U, T> CompletableFuture<T> supplyAndApply(Supplier<U> supplier, Function<? super U, ? extends T> function) {
        return CompletableFuture.supplyAsync(supplier).thenApply(function);
    }

    /**
     * 线程串型化，异步提交，使用内置的线程异步处理结果
     *
     * @param supplier
     * @param function
     * @param <T>
     * @param <U>
     * @return
     */
    public static <U, T> CompletableFuture<T> supplyAndApplyAsync(Supplier<U> supplier, Function<? super U, ? extends T> function) {
        return CompletableFuture.supplyAsync(supplier).thenApplyAsync(function);
    }

    /**
     * 线程串型化，异步提交，使用指定的线程异步处理结果
     *
     * @param supplier
     * @param function
     * @param <T>
     * @param <U>
     * @return
     */
    public static <U, T> CompletableFuture<T> supplyAndApplyAsync(Supplier<U> supplier, Function<? super U, ? extends T> function, Executor executor) {
        return CompletableFuture.supplyAsync(supplier, executor).thenApplyAsync(function, executor);
    }

    /**
     * 线程串型化，异步提交，使用指定的线程异步处理结果
     *
     * @param supplier
     * @param function
     * @param <T>
     * @param <U>
     * @return
     */
    public static <U, T> CompletableFuture<T> supplyAndApplyAsync(Supplier<U> supplier, Function<? super U, ? extends T> function, Executor supplyExecutor, Executor applyExecutor) {
        return CompletableFuture.supplyAsync(supplier, supplyExecutor).thenApplyAsync(function, applyExecutor);
    }

    /**
     * 异步提交任务，同步消费结果
     *
     * @param supplier
     * @param consumer
     * @param <U>
     * @return
     */
    public static <U> CompletableFuture<Void> supplyAndAccept(Supplier<U> supplier, Consumer<? super U> consumer) {
        return CompletableFuture.supplyAsync(supplier).thenAccept(consumer);
    }

    /**
     * 异步提交任务，异步消费结果
     *
     * @param supplier
     * @param consumer
     * @param <U>
     * @return
     */
    public static <U> CompletableFuture<Void> supplyAndAcceptAsync(Supplier<U> supplier, Consumer<? super U> consumer) {
        return CompletableFuture.supplyAsync(supplier).thenAcceptAsync(consumer);
    }

    /**
     * 异步提交任务，同步消费结果
     *
     * @param supplier
     * @param runnable
     * @param <U>
     * @return
     */
    public static <U> CompletableFuture<Void> supplyAndAccept(Supplier<U> supplier, Runnable runnable) {
        return CompletableFuture.supplyAsync(supplier).thenRun(runnable);
    }

    /**
     * 异步提交任务，同步消费结果
     *
     * @param supplier
     * @param future
     * @param action
     * @param <U>
     * @param <T>
     * @return
     */
    public static <U, T> CompletableFuture<Void> supplyAndAcceptOther(Supplier<U> supplier, CompletableFuture<T> future, BiConsumer<? super U, ? super T> action) {
        return CompletableFuture.supplyAsync(supplier).thenAcceptBoth(future, action);
    }

    /**
     * 异步提交任务，同步消费结果
     *
     * @param supplier
     * @param future
     * @param function
     * @param <U>
     * @param <T>
     * @param <R>
     * @return
     */
    public static <U, T, R> CompletableFuture<R> supplyAndCombine(Supplier<U> supplier, CompletableFuture<T> future, BiFunction<? super U, ? super T, R> function) {
        return CompletableFuture.supplyAsync(supplier).thenCombine(future, function);
    }

    /**
     * 异步提交任务，同步消费结果
     *
     * @param supplier
     * @param function
     * @param <U>
     * @param <T>
     * @return
     */
    public static <U, T> CompletableFuture<T> supplyAndCompose(Supplier<U> supplier, Function<? super U, ? extends CompletionStage<T>> function) {
        return CompletableFuture.supplyAsync(supplier).thenCompose(function);
    }

    /**
     * 取supplier和other二者中执行较快的结果
     *
     * @param supplier
     * @param other
     * @param function
     * @param <U>
     * @param <T>
     * @return
     */
    public static <U, T> CompletableFuture<T> supplyAndToEither(Supplier<U> supplier, CompletionStage<? extends U> other, Function<? super U, T> function) {
        return CompletableFuture.supplyAsync(supplier).applyToEither(other, function);
    }

    /**
     * 处理异常
     *
     * @param supplier
     * @param function
     * @param <U>
     * @return
     */
    public static <U> CompletableFuture<U> supplyAndExceptionally(Supplier<U> supplier, Function<Throwable, ? extends U> function) {
        return CompletableFuture.supplyAsync(supplier).exceptionally(function);
    }

    /**
     * 处理异常，注意：这里如果supper抛了异常，最终如果调用future的get方法将把异常抛给supplyAndWhenComplete的调用者，详情参考测试用例1
     *
     * @param supplier
     * @param action
     * @param <U>
     * @return
     */
    public static <U> CompletableFuture<U> supplyAndWhenComplete(Supplier<U> supplier, BiConsumer<? super U, ? super Throwable> action) {
        return CompletableFuture.supplyAsync(supplier).whenComplete(action);
    }

    /**
     * 处理异常，并返回默认值
     *
     * @param supplier
     * @param function
     * @param <U>
     * @param <T>
     * @return
     */
    public static <U, T> CompletableFuture<T> supplyAndHandle(Supplier<U> supplier, BiFunction<? super U, Throwable, ? extends T> function) {
        return CompletableFuture.supplyAsync(supplier).handle(function);
    }

    /**
     * 等待所有任务执行完毕，已耗时最长的任务为总耗时
     * 注意：如果其中有任务任务抛出异常，其后所有任务都将不会执行，如果存在多个异常任务，总耗时会进行累加
     *
     * @param pairs
     */
    public static void join(List<Pair> pairs) {
        CompletableFuture[] completableFutures = pairs.stream().map(p -> CompletableFuture.supplyAsync(p.getSupplier()).handle(p.getFunction())).toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(completableFutures).join();
    }

    public static class Pair<U, T> {
        private Supplier<U> supplier;
        private BiFunction<? super U, Throwable, ? extends T> function;

        public Pair(Supplier<U> supplier, BiFunction<? super U, Throwable, ? extends T> function) {
            this.supplier = supplier;
            this.function = function;
        }

        public Supplier<U> getSupplier() {
            return supplier;
        }

        public BiFunction<? super U, Throwable, ? extends T> getFunction() {
            return function;
        }
    }
}
