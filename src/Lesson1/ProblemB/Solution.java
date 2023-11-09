package Lesson1.ProblemB;

import java.io.*;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.function.Predicate;

public class Solution {
    static Reader input = new Reader();
    static Random random = new Random();
    public static void main(String[] args) {
        int n = input.nextInt();
        int[] arr = new int[n];
        boolean flag = true;
        for (int i = 0; i < n; i++) {
            int x = input.nextInt();
            arr[i] = x;
            if(i != 0){
                if(flag) {
                    if (x < arr[i - 1]) {
                        flag = false;
                    }
                }
            }
        }

        if(flag){
            StringBuilder answer = new StringBuilder();
            for (int i : arr) {
                answer.append(i).append(" ");
            }
            System.out.println(answer);
        }
        else {
            quicksort(arr, 0, arr.length - 1);
            StringBuilder answer = new StringBuilder();
            for (int i : arr) {
                answer.append(i).append(" ");
            }
            System.out.println(answer);
        }
    }

    private static void quicksort(int[] arr, int start, int end) {
        //System.out.println(start + " " + end);
        // выберем рандомный элемент
        if (start >= end) {
            return;
        }
        int rand = arr[random.nextInt(start , end + 1)];
        Predicate<Integer> less = x -> x < rand;
        Predicate<Integer> equal = x -> x == rand;
        int pivot = partition(less, equal, arr, start, end);
        quicksort(arr, start, pivot - 1);
        quicksort(arr, pivot + 1, end);
    }

    public static int partition(Predicate<Integer> less, Predicate<Integer> equal, int[] arr, int left, int right) {
        for (int i = left; i <= right; ) {
            if (less.test(arr[i])) {
                // элемент меньше чем pivot
                //System.out.println(left + " " + right);
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
