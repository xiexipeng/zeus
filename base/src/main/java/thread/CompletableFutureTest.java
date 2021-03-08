package thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/03/08 15:27:43
 * @description:
 * @Version
 **/
public class CompletableFutureTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CompletableFuture.supplyAsync(() -> {
            System.out.println("...");
            return "test";
        }, executorService).exceptionally(e -> {
            System.out.println();
            return "false";
        }).thenAccept(s->{

        });

    }
}
