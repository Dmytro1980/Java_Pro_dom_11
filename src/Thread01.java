public class Thread01 implements Runnable {

    private double[] arr;
    private long startTime;
    private long finishTime;
    private int nOfTThread;

    public Thread01(double[] arr, int nOfThread) {
        this.arr = arr;
        this.nOfTThread = nOfThread;
    }

    @Override
    public void run() {
        startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
                arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) *
                        Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        finishTime = System.currentTimeMillis() - startTime;
        System.out.println("Thread #" + nOfTThread + " time: " + (double) finishTime / 1000 + " s");
    }
}
