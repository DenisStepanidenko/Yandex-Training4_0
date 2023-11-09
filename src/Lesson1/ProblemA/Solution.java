package Lesson1.ProblemA;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.function.Predicate;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        int n = input.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            int x = input.nextInt();
            arr[i] = x;
        }

        int pivot = input.nextInt();
        Predicate<Integer> less = x -> x < pivot;
        Predicate<Integer> equal = x -> x == pivot;
        int separation = partition(less, equal, arr, 0, arr.length - 1);
        System.out.println(separation);
        System.out.println(n - separation);
    }

    public static int partition(Predicate<Integer> less, Predicate<Integer> equal, int[] arr, int left, int right) {
        for (int i = 0; i <= right; ) {
            if (less.test(arr[i])) {
                // элемент меньше чем pivot
                int temp = arr[left];
                arr[left] = arr[i];
                arr[i] = temp;
                left++;
                i++;
            } else {
                if (equal.test(arr[i])) {
                    // элемент равен pivot
                    i++;
                } else {
                    // элемент больше чем pivot
                    int temp = arr[right];
                    arr[right] = arr[i];
                    arr[i] = temp;
                    right--;
                }
            }
        }
        return left;
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
