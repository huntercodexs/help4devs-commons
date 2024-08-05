package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.services.crypto.Help4DevsCryptographyService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Help4DevsThreadsUnitaryTests extends Help4DevsBridgeTests {

    private static final String path = "src/test/resources/help4devs/images";
    private static final String key = "F1F2F3F4F5F6F7F8F9F00000";
    private static final String salt = "1";

    /*THREAD*/
    public static class MyThread extends Thread {

        private final int id;
        private final String str;
        private List<String> resultList = new ArrayList<>();

        public MyThread(int id, String str) {
            this.id = id;
            this.str = str+str+str+str+str;
            start();
        }

        public void printResultList() {
            for (String result : this.resultList) {
                System.out.println(result);
            }
        }

        public void run() {
            this.resultList.add(id+":"+Help4DevsCryptographyService.encryptAesCbc256(str, key, salt));
            printResultList();
        }
    }

    @Test
    public void threadTest() {

        /*Start Threads*/
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threadList.add(i, new MyThread(i, UUID.randomUUID().toString()));
        }

        /*Await Threads*/
        boolean noStop = true;
        while (noStop) {
            try {
                Thread.sleep(100);
                for (Thread thread : threadList) {
                    if (thread.isAlive()) {
                        noStop = true;
                        break;
                    } else {
                        noStop = false;
                    }
                }
            } catch (InterruptedException ie) {
                System.out.println(ie.getMessage());
            }
        }

        /*Result Threads*/
        for (Thread thread : threadList) {
            System.out.println(thread.getName());
        }
    }

    /*THREAD UNIFIED*/
    @Test
    public void threadUnifiedTest() {

        class MyThread extends Thread {

            private final int id;
            private final String str;
            private List<String> resultList = new ArrayList<>();

            public MyThread(int id, String str) {
                this.id = id;
                this.str = str+str+str+str+str;
                start();
            }

            public void printResultList() {
                for (String result : this.resultList) {
                    System.out.println(result);
                }
            }

            public void run() {
                this.resultList.add(id+":"+Help4DevsCryptographyService.encryptAesCbc256(str, key, salt));
                printResultList();
            }
        }

        /*Start Threads*/
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threadList.add(i, new MyThread(i, UUID.randomUUID().toString()));
        }

        /*Await Threads*/
        boolean noStop = true;
        while (noStop) {
            try {
                Thread.sleep(100);
                for (Thread thread : threadList) {
                    if (thread.isAlive()) {
                        noStop = true;
                        break;
                    } else {
                        noStop = false;
                    }
                }
            } catch (InterruptedException ie) {
                System.out.println(ie.getMessage());
            }
        }

        /*Result Threads*/
        for (Thread thread : threadList) {
            System.out.println(thread.getName());
        }
    }

    /*RUNNABLE*/
    public static class MyRunnable implements Runnable {

        private final int id;
        private final String data;
        private final String threadName;
        private static final ProcessTest processTest = new ProcessTest();

        public MyRunnable(int id, String data, String threadName) {
            this.id = id;
            this.data = data;
            this.threadName = threadName;
            new Thread(this, threadName).start();
        }

        @Override
        public void run() {
            System.out.println("Starting " + this.threadName);
            processTest.process(this.id, this.data);
            processTest.print();
            System.out.println("Finishing " + this.threadName);
        }
    }

    public static class ProcessTest {

        public List<String> resultList = new ArrayList<>();

        public synchronized void process(int id, String str) {
            try {
                Thread.sleep(100);
                this.resultList.add(id+":"+Help4DevsCryptographyService.encryptAesCbc256(str, key, salt));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        public synchronized void print() {
            System.out.println(this.resultList);
        }

    }

    @Test
    public void runnableTest() {

        /*Start Threads*/
        List<Runnable> runnableList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            runnableList.add(i, new MyRunnable(i, UUID.randomUUID().toString(), "Thread-Sample-" + i));
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("FINISHED");
    }
}
