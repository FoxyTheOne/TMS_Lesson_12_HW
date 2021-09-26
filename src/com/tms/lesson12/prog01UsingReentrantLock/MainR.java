package com.tms.lesson12.prog01UsingReentrantLock;

import com.tms.lesson12.prog01UsingReentrantLock.model.BufferUsingReentrantLock;
import com.tms.lesson12.prog01UsingReentrantLock.model.ConsumerR;
import com.tms.lesson12.prog01UsingReentrantLock.model.ProducerR;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 1. Напишите многопоточный ограниченный буфер с использованием ReentrantLock.
 *
 * Производитель потребительской модели
 *
 * Проблема производителя-потребителя, также известная как проблема ограниченного буфера, является классическим случаем проблем многопоточной синхронизации.
 * Эта проблема описывает, что происходит, когда два потока, совместно использующие буфер фиксированного размера, так называемые «производитель» и «потребитель»,
 * фактически работают. Основная роль производителя - сгенерировать определенный объем данных в буфер, а затем повторить процесс.
 * В то же время потребители потребляют эти данные в буфере. Ключ к этой проблеме - гарантировать, что производители не будут добавлять данные,
 * когда буфер заполнен, а потребители не будут потреблять данные, когда буфер пуст.
 *
 * Чтобы решить эту проблему, производитель должен спать, когда буфер заполнен, а производитель может быть разбужен и начать добавлять данные
 * в буфер до следующего раза, когда потребитель потребит данные в буфере. Точно так же вы также можете позволить потребителю перейти в спящий режим,
 * когда буфер пуст, а затем разбудить потребителя после того, как производитель добавит данные в буфер. Обычно используемые методы включают
 * метод сигнальной лампы и ламповую лампу. Если решение не идеально, оно может зайти в тупик. Когда происходит взаимоблокировка,
 * оба потока переходят в режим сна, ожидая пробуждения другого.
 */
public class MainR {
    public static void main(String[] args) {
        // Создаём многопоточный ограниченный буфер с использованием ReentrantLock
        BufferUsingReentrantLock bufferR = new BufferUsingReentrantLock();

        ReentrantLock locker = new ReentrantLock();

        ProducerR producerR = new ProducerR(bufferR, locker);
        ConsumerR consumerR = new ConsumerR(bufferR, locker);

        // Запускаем потоки
        new Thread(producerR).start();
        new Thread(consumerR).start();
    }
}