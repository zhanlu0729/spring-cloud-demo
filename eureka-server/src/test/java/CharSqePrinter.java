import java.util.concurrent.TimeUnit;

public class CharSqePrinter {

    private static volatile int index = 1;

    public static void main(String[] args) {
        Object mutexA = new Object();
        Object mutexB = new Object();
        Object mutexC = new Object();

        Thread a = new Thread(() -> {
            while (true) {
                synchronized (mutexA) {
                    if (index == 1) {
                        System.err.print("A");
                        synchronized (mutexB) {
                            index = 2;
                            mutexB.notifyAll();
                        }
                    }
                    try {
                        mutexA.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "thread-a");

        Thread b = new Thread(() -> {
            while (true) {
                synchronized (mutexB) {
                    if (index == 2) {
                        System.err.print("B");
                        synchronized (mutexC) {
                            index = 3;
                            mutexC.notifyAll();
                        }
                    }
                    try {
                        mutexB.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "thread-b");

        Thread c = new Thread(() -> {
            while (true) {
                synchronized (mutexC) {
                    if (index == 2) {
                        System.err.print("C");
                        synchronized (mutexA) {
                            index = 1;
                            mutexA.notifyAll();
                        }
                    }
                    try {
                        mutexC.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "thread-c");

        a.start();
        b.start();
        c.start();
    }
}
