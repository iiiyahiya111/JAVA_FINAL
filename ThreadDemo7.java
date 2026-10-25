class MyThread extends Thread {
    boolean running = true;

    public void run() {
        for (int i = 1; i <= 10 && running; i++) {
            System.out.println(i);
            Thread.yield();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    void stopThread() {
        running = false;
        interrupt();
    }
}

public class ThreadDemo7 {
    public static void main(String[] args) throws Exception {
        MyThread t = new MyThread();
        t.start();

        Thread.sleep(2000);
        t.stopThread();
        System.out.println("Thread stopped");
    }
}