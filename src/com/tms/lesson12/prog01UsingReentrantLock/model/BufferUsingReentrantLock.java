package com.tms.lesson12.prog01UsingReentrantLock.model;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Основная роль производителя - сгенерировать определенный объем данных в буфер, а затем повторить процесс.
 * В то же время потребители потребляют эти данные в буфере. Ключ к этой проблеме - гарантировать, что производители не будут добавлять данные,
 * когда буфер заполнен, а потребители не будут потреблять данные, когда буфер пуст.
 *
 * Чтобы решить эту проблему, производитель должен спать, когда буфер заполнен, а производитель может быть разбужен и начать добавлять данные
 * в буфер до следующего раза, когда потребитель потребит данные в буфере. Точно так же вы также можете позволить потребителю перейти в спящий режим,
 * когда буфер пуст, а затем разбудить потребителя после того, как производитель добавит данные в буфер. Обычно используемые методы включают
 * метод сигнальной лампы и ламповую лампу.
 */
public class BufferUsingReentrantLock {
    // Числа на складе
    LinkedList<Integer> numbersInStore = new LinkedList<>();
    int amountOfNumbers = 0;

    // Добавление случайных чисел в буфер
    public void put(ReentrantLock locker) {
        while (amountOfNumbers < 10) {
            locker.lock();// устанавливаем блокировку
            try {
                int randomNumber = (int) (Math.random() * (99 - 2)) + 2; // Диапазон целых чисел [2; 99)(99 не включительно). Формула (Math.random()*(b-a))+a) = [a; b)
                numbersInStore.add(randomNumber);
                amountOfNumbers++;
                Thread.sleep(500);

                System.out.println("Производитель добавил число " + randomNumber);
                System.out.println("Чисел на складе: " + amountOfNumbers + ". Числа на складе: " + numbersInStore);
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                locker.unlock(); // снимаем блокировку
            }
        }
    }

    // Получение элементов из буфера
    public void get(ReentrantLock locker) {
        while (amountOfNumbers > 0) {
            locker.lock();// устанавливаем блокировку
            try {
                numbersInStore.removeFirst();
                amountOfNumbers--;
                Thread.sleep(500);

                System.out.println("Покупатель ЗАБРАЛ 1 число");
                System.out.println("Чисел на складе: " + amountOfNumbers + ". Числа на складе: " + numbersInStore);
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                locker.unlock(); // снимаем блокировку
            }
        }
    }
}
