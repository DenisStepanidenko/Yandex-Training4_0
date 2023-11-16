package Lesson2.ProblemD;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        int n = input.nextInt();
        long m = input.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            int x = input.nextInt();
            arr[i] = x;
        }
        long x = m + 1; // значение многочлена будем считать именно в этой точке
        long p = (long) (Math.pow(10, 9) + 7); // считаем по модулю, чтобы не было переполнения
        long[] h = new long[(n / 2) + 1];
        long[] hReverse = new long[n + 1];
        long[] degrees = new long[n + 1];
        h[0] = 0;
        hReverse[0] = 0;
        degrees[0] = 1;
        // сделаем предпосчёт
        for (int i = 0; i < n; i++) {
            degrees[i + 1] = (degrees[i] * x) % p;
            hReverse[i + 1] = (hReverse[i] * x + arr[n - i - 1]) % p;
            if (i < n / 2) {
                h[i + 1] = (h[i] * x + arr[i]) % p;
            }
        }

        List<Integer> answer = new ArrayList<>();
        for (int i = n / 2; i > 0; i--) {
            if( (h[i] + hReverse[n-i-i]*degrees[i]) % p == (hReverse[n-i]) % p){
                answer.add(n-i);
            }
        }
        answer.add(n);
        StringBuilder str = new StringBuilder();
        for (Integer integer : answer) {
            str.append(integer).append(" ");
        }
        System.out.println(str);
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
