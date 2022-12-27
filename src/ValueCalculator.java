import java.text.DecimalFormat;

public class ValueCalculator {

    private int arraySize = 1000000; // 1 000 0000 элементов массива
    private double[] array = new double[arraySize];
    double[] a1;
    double[] a2;
    private long startTime;
    private long finishTime;

    void calcTime() {
        startTime = System.currentTimeMillis();

        //заполнение перевого массива
        for (int i = 0; i < arraySize; i++) {
//                array[i] = Math.random();          // заполнение исх. массива случ. значениями (от 0 до 1)
                array[i] = 1;
        }

        // опредедение половины строк в первом массиве, пополам или первый из
        // двух созданных массивов будет на 1 строку меньше
        int half = (int) Math.floor(array.length) / 2;

        // создаються второй и третий массивы
        a1 = new double[half];
        System.arraycopy(array, 0, a1, 0, half);

        if (array.length % 2 != 0) {
            // количество элементов первого массива нечетное
            a2 = new double[half + 1];
            System.arraycopy(array, half, a2, 0, half + 1);
        } else {
            // количество элементов первого массива четное
            a2 = new double[half];
            System.arraycopy(array, half, a2, 0, half);
        }

        Thread thread01 = new Thread(new Thread01(a1, 1));
//        thread01.run();     // вычисления будут выполнены в текущем потоке
        thread01.start();   // вычисления будут выполнены в новом потоке

        Thread thread02 = new Thread(new Thread01(a2, 2));
//        thread02.run();   // вычисления будут выполнены в текущем потоке
        thread02.start();   // вычисления будут выполнены в новом потоке

        System.arraycopy(a1, 0, array, 0, half);
        if (array.length % 2 != 0) {
            // количество элементов первого массива нечетное
            System.arraycopy(a2, 0, array, half, half+1);
        } else {
            // количество элементов первого массива четное
            System.arraycopy(a2, 0, array, half, half);
        }

        finishTime = System.currentTimeMillis() - startTime;
        System.out.println("total time: " + (double) finishTime / 1000 + " s");
    }

    // метод печатает массив с округлением, использовался на малых
    // размерах массива для проверки работоспособности программы
    private void printArray(double[] arr) {

        DecimalFormat df = new DecimalFormat("0.0000");
        for (int i = 0; i < arr.length; i++) {
                System.out.print(df.format(arr[i]) + " ");
        }
        System.out.println();
    }
}
