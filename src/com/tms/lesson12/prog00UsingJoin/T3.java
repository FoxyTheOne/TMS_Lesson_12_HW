package com.tms.lesson12.prog00UsingJoin;

public class T3 implements Runnable {

    @Override
    public void run() {
        doInnerLogic();
    }

    private void doInnerLogic() {
        String currentThreadName = Thread.currentThread().getName();
        for (int i = 0; i < 10; i++) {
            System.out.println(currentThreadName + " is running!" + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(currentThreadName + " completed");
    }

}
