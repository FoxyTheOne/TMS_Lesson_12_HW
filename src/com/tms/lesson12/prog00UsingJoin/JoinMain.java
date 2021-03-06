package com.tms.lesson12.prog00UsingJoin;

/**
 * 0. Создать три потока Т1, Т2 и Т3
 * Реализовать выполнение потоков в последовательности Т3 -> Т2 -> Т1
 * (используя метод join)
 */
public class JoinMain {
    public static void main(String[] args) {
        T t1 = new T();
        T t2 = new T();
        T t3 = new T();

        Thread one = new Thread(t1);
        one.setName("Т1");
        Thread two = new Thread(t2);
        two.setName("Т2");
        Thread three = new Thread(t3);
        three.setName("Т3");

        try {
            three.start();
            three.join();

            two.start();
            two.join();

            one.start();
            one.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
