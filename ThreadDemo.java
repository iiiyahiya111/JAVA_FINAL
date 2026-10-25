class MyThread extends Thread {
    boolean suspend = false;
    boolean stop = false;

    public void run() {
        try {
            int i = 1;
            while (!stop) {
                synchronized (this) {
                    while (suspend)
                        wait();
                }
                System.out.println(i++);
                Thread.sleep(500);
            }
        } catch (Exception e) {
            System.out.println("Thread interrupted");
        }
    }

    synchronized void suspendThread() {
        suspend = true;
    }

    synchronized void resumeThread() {
        suspend = false;
        notify();
    }

    void stopThread() {
        stop = true;
        resumeThread();
    }
}

public class ThreadDemo {
    public static void main(String[] args) throws Exception {
        MyThread t = new MyThread();
        t.start();

        Thread.sleep(2000);
        t.suspendThread();
        System.out.println("Thread Suspended");

        Thread.sleep(2000);
        t.resumeThread();
        System.out.println("Thread Resumed");

        Thread.sleep(2000);
        t.stopThread();
        System.out.println("Thread Stopped");
    }
}


