package com.tms.lesson12.prog01UsingReentrantLock.model;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Основная роль производителя - сгенерировать определенный объем данных в буфер, а затем повторить процесс.
 * Производители не будут добавлять данные, когда буфер заполнен, а потребители не будут потреблять данные, когда буфер пуст.
 *
 * Т. обр., производитель должен спать, когда буфер заполнен, а производитель может быть разбужен и начать добавлять данные
 * в буфер до следующего раза, когда потребитель потребит данные в буфере. Точно так же вы также можете позволить потребителю перейти в спящий режим,
 * когда буфер пуст, а затем разбудить потребителя после того, как производитель добавит данные в буфер. Обычно используемые методы включают
 * метод сигнальной лампы и ламповую лампу.
 */
public class ProducerR implements Runnable {
    // Буфер, в который будут "складироваться" числа. Пока он не определен и будет задан через конструктор
    private BufferUsingReentrantLock bufferR;
    ReentrantLock locker;

    // Конструктор производителя
    public ProducerR(BufferUsingReentrantLock bufferR, ReentrantLock lock){
        this.bufferR = bufferR;
        locker = lock;
    }

    @Override
    public void run() {
        doInnerLogic(locker);
    }

    // Метод (бесконечный!) для добавления чисел в буфер
    private void doInnerLogic(ReentrantLock locker) {
        while (true) {
            // добавить элемент в буфер
            bufferR.put(locker);
        }
    }
}
