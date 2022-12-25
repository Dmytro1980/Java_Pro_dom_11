import java.text.DecimalFormat;

public class ValueCalculator {
    private int arraySize = 1000; // 1000 x 1000 = 1 000 0000 элементов массива
    private double[][] array = new double[arraySize][arraySize];
    double[][] a1;
    double[][] a2;
    private long startTime;
    private long finishTime;

    void calcTime() {
        startTime = System.currentTimeMillis();

        //заполнение перевого массива
        for (int i = 0; i < arraySize; i++) {
            for (int j = 0; j < arraySize; j++) {
                //array[i][j] = Math.random();          // заполнение исх. массива случ. значениями (от 0 до 1)
                array[i][j] = 1;
            }
        }

        // опредедение половины строк в первом массиве? пополам или первый из
        // двух созданных массивов будет на 1 строку меньше
        int half = (int) Math.floor(array.length) / 2;

        // проверка исходного массива и определение макс. длины строки
        // в исходном массиве
        int maxRowLength = 0;
        for (int i = 0; i < array.length; i++) {
            if (maxRowLength <= array[i].length) {
                maxRowLength = array[i].length;
            }
        }

        // создаються второй и третий массивы
        a1 = new double[half][maxRowLength];
        System.arraycopy(array, 0, a1, 0, half);

        if (array.length % 2 != 0) {
            // количество строк нечетное
            a2 = new double[half + 1][maxRowLength];
            System.arraycopy(array, half, a2, 0, half + 1);
        } else {
            // количество строк четное
            a2 = new double[half][maxRowLength];
            System.arraycopy(array, half, a2, 0, half);
        }

        Thread thread01 = new Thread(new Thread01(a1, 1));
        thread01.run();

        Thread thread02 = new Thread(new Thread01(a2, 2));
        thread02.run();

        System.arraycopy(a1, 0, array, 0, half);
        if (array.length % 2 != 0) {
            // количество строк нечетное
            System.arraycopy(a2, 0, array, half, half+1);
        } else {
            // количество строк четное
            System.arraycopy(a2, 0, array, half, half);
        }

        finishTime = System.currentTimeMillis() - startTime;
        System.out.println("total time: " + (double) finishTime / 1000);
    }

    // метод печатает массив с округлением, для наглядности
    private void printArray(double[][] arr) {

        DecimalFormat df = new DecimalFormat("0.0000");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(df.format(arr[i][j]) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
