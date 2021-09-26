package com.tms.lesson12.prog02UsingSynchronized.model;

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
public class BufferUsingSynchronized {
    // Данные в буфере
    private String product = null;

    // Семафор:
    // флаг -> T производит производство, потребитель ждет и уведомляет потребление после завершения производства
    // флаг -> F потребление потребителем, производитель ждет и уведомляет производство после завершения потребления
    private boolean flag = true;

    public synchronized void put(){
        if (! flag) {// Производитель ждёт
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Запускаем производство
        putProduct();
        // Производство завершено

        // Уведомляем потребление
        this.notify();

        // Производитель останавливается
        this.flag = false;
    }

    // Получение элементов из буфера
    public synchronized void get() {
        if (flag) {// Потребитель ждет
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Начинаем потребление
        getProduct();
        // Завершенное потребление

        // Уведомляем производство
        this.notifyAll();

        // Остановить потребление
        this.flag = true;
    }

    private void putProduct() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        product = "рис";

        System.out.println("Производитель добавил на склад " + product);
        System.out.println();
    }

    private void getProduct() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Покупатель ЗАБРАЛ со склада " + product);
        System.out.println("На складе закончился " + product);
        System.out.println();

        product = null;
    }
}
