public class ThreadAB {

    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            synchronized (resourceA) {
                System.err.println("ThreadA get resourceA lock");

                try {
                    System.err.println("ThreadA run begin");
                    resourceA.wait();
                    System.err.println("ThreadA run end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(() -> {
            synchronized (resourceA) {
                System.err.println("ThreadB get resourceA lock");

                try {
                    System.err.println("ThreadB run begin");
                    resourceA.wait();
                    System.err.println("ThreadB run end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadC = new Thread(() -> {
            synchronized (resourceA) {
                System.err.println("ThreadC run begin");
                resourceA.notify();
                System.err.println("ThreadC run end");
            }
        });

        threadA.start();
        threadB.start();
        Thread.sleep(10);
        threadC.start();
        threadA.join();
        threadB.join();
        threadC.join();
        System.err.println("main over");
    }
}
