class MyThread extends Thread {
    public void run() {
        System.out.println(getName() + " is running");
    }
}

public class ThreadPriority {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();

        t1.setPriority(3);
        t2.setPriority(6);
        t3.setPriority(9);

        t1.start();
        t2.start();
        t3.start();
    }
}

