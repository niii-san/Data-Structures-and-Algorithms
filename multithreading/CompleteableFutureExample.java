package multithreading;
import java.util.concurrent.*;

public class CompleteableFutureExample {

    public static void main(String[] args) {

        int a[] = { 100, 200, 300, 400, 500, 600, 700 };
        CompletableFuture<Integer> sum = CompletableFuture.supplyAsync(() -> sum(a));
        CompletableFuture<Integer> sqaure = sum.thenApplyAsync(result->result*result);
        CompletableFuture<Void> printres = sqaure.thenAcceptAsync(result->System.out.println(result));

        System.out.println("ttttttttttt");
        printres.join();
    }

    static int sum(int a[]) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        try {
            Thread.sleep(9000);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sum;
    }

}
