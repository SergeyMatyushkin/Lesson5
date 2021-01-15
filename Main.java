public class Main {
    private static final int size = 10000000;
    private static final int h = size / 2;


    public static void main(String[] args) {
        makeMetodOne();
        makeMetodTwo();
        glueThreads();

    }



    private static void makeMetodOne() {
        float[] arr = new float[size];
        for (int i = 0; i < size; i++){
            arr[i] = 1.0f;
            }
        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++){
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.currentTimeMillis();
        System.out.println(System.currentTimeMillis() - a);

    }

    private static void makeMetodTwo() {
        float[] arr = new float[size];

        Thread t1 = new Thread(() -> {
            float[] arr1 = new float[h];
            for (int i = 0; i < h; i++){
                arr1[i] = 1.0f;
            }
            long a = System.currentTimeMillis();
            for (int i = 0; i < h; i++){
                arr1[i] = (float)(arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            System.arraycopy(arr, 0, arr1, 0, h);
            System.currentTimeMillis();
            System.out.println(System.currentTimeMillis() - a);
        });

        Thread t2 = new Thread(() -> {
            float[] arr2 = new float[h];
            for (int i = 0; i < h; i++){
                arr2[i] = 1.0f;
            }
            long a = System.currentTimeMillis();
            for (int i = 0; i < h; i++){
                arr2[i] = (float)(arr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            System.arraycopy(arr, h, arr2, 0, h);
            System.currentTimeMillis();
            System.out.println(System.currentTimeMillis() - a);
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static synchronized void glueThreads() {
        float[] arr = new float[size];
        float[] arr1 = new float[h];
        float[] arr2 = new float[h];
        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);
        long a = System.currentTimeMillis();
        for(int i = 0; i<arr.length; i++){
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.currentTimeMillis();
        System.out.println(System.currentTimeMillis() - a);



    }


}
