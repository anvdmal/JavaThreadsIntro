package edu.rutmiit.threads.notSync;

public class MultiThreadDemo {
    public static void main(String[] args) {
// TODO: Создайте массив для хранения потоков
        Thread[] threads = new Thread[5];
// TODO: Используйте цикл для создания и запуска потоков
        for (int i = 0; i < 5; i++) {
// Используем переменную для хранения номера потока
// используя final, мы гарантируем, что переменная будет захвачена
// внутри потока с неизменным значением
            final int threadNumber = i + 1;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 1; j <= 10; j++) {
                        System.out.println("Поток номер " + threadNumber + ", " + j);
                    }
                    // TODO: Реализуйте логику работы потока
// Подсказка: выведите номер потока 10 раз, используйте переменную threadNumber
                }
            });
            threads[i].start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
// TODO: Дождитесь завершения всех потоков
        System.out.println("Все потоки завершили работу");
    }
}