package Lesson1.ProblemD;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        int[] arrTest = new int[10];
        System.out.println(Arrays.toString(arrTest));
        int n = input.nextInt();
        int[] arr = new int[n];
        boolean flag = true;
        for (int i = 0; i < n; i++) {
            int x = input.nextInt();
            arr[i] = x;
            if (i != 0) {
                if (flag) {
                    if (x < arr[i - 1]) {
                        flag = false;
                    }
                }
            }
        }

        if (flag) {
            StringBuilder answer = new StringBuilder();
            for (int i : arr) {
                answer.append(i).append(" ");

            }
            System.out.println(answer);
        } else {
            mergeSort(arr);
            StringBuilder answer = new StringBuilder();
            for (int i : arr) {
                answer.append(i).append(" ");

            }
            System.out.println(answer);
        }
    }

    private static void mergeSort(int[] arr) {
        if (arr.length == 1) {
            return;
        }
        int mid = arr.length / 2;
        int[] l = new int[mid];
        int[] r = new int[arr.length - mid];
        for (int i = 0; i < mid; i++) {
            l[i] = arr[i];
        }

        for (int i = mid; i < arr.length; i++) {
            r[i - mid] = arr[i];
        }
        mergeSort(l);
        mergeSort(r);

        merge(l, r, arr);
    }

    public static void merge(int[] l, int[] r, int[] arr) {
        // нужно объединить элементы из l и r в массив arr
        int i = 0, j = 0, count = 0;
        while (i < l.length && j < r.length) {
            if (l[i] <= r[j]) {
                arr[count] = l[i];
                count++;
                i++;
            } else {
                //System.out.println(j);
                arr[count] = r[j];
                count++;
                j++;
            }
        }
        while (i < l.length) {
            arr[count] = l[i];
            count++;
            i++;
        }
        while (j < r.length) {
            arr[count] = r[j];
            count++;
            j++;
        }
    }

    static class Reader extends PrintWriter {

        private BufferedReader r;
        private StringTokenizer st;
        // standard input

        public Reader() {
            this(System.in, System.out);
        }

        public Reader(InputStream i, OutputStream o) {
            super(o);
            r = new BufferedReader(new InputStreamReader(i));
        }
        // USACO-style file input

        public Reader(String problemName) throws IOException {
            super(problemName + ".out");
            r = new BufferedReader(new FileReader(problemName));
        }

        // returns null if no more input
        String nextLine() {
            String str = "";
            try {
                str = r.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        public String next() {
            try {
                while (st == null || !st.hasMoreTokens()) {
                    st = new StringTokenizer(r.readLine());
                }
                return st.nextToken();
            } catch (Exception e) {
            }
            return null;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long nextLong() {

            return Long.parseLong(next());
        }
    }
}
