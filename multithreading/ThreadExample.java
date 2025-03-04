package multithreading;

class DownloadImage {
    synchronized void downloadImage(String name, int size) {
        for (int i = 0; i < size; i++) {
            System.out.println("donwloading image " + name + i + ".jpg");

        }

    }
}

class MyThread extends Thread {
    DownloadImage dref;

    MyThread(DownloadImage dref) {
        this.dref = dref;
    }

    @Override
    public void run() {
        dref.downloadImage("cat", 10);
    }
}

class YourThread extends Thread {
    DownloadImage dref;

    YourThread(DownloadImage dref) {
        this.dref = dref;
    }

    @Override
    public void run() {
        dref.downloadImage("dog", 10);

    }
}

public class ThreadExample {

    public static void main(String[] args) {
        System.out.println("Application started");

        for (int i = 0; i < 10; i++) {
            System.out.println("downlaod iamge xtra " + i + ".jpg");
        }

        DownloadImage dref = new DownloadImage();
        MyThread t1 = new MyThread(dref);
        YourThread t2 = new YourThread(dref);
        t1.start();
        // try {
        //     t1.join();

        // } catch (Exception e) {
        //     System.out.println("Got error while joining > " + e);
        // }
        t2.start();
        System.out.println("Application stop");

    }

}

// public class ThreadExample extends Thread {

// @Override
// public void run() {
// for (int i = 0; i < 100; i++) {
// System.out.println("downlaod image cat" + i + ".jpg");
// }
// }

// public static void main(String[] args) {

// System.out.println("application started");
// for (int i = 0; i < 100; i++) {
// System.out.println("downlaod image dog" + i + ".jpg");
// }

// ThreadExample t1 = new ThreadExample();
// Thread thrd = new Thread(t1);
// thrd.start();

// System.out.println("application stopped");

// }

// }