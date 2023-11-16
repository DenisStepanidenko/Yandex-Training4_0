package Lesson2.ProblemB;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        long p = (long) (Math.pow(10, 9) + 7); // простое число чтобы не было переполнений
        long x = 257; // то значение, которое мы будем подставлять в полином
        // основная идея задачи - найти максимальный префикс, равный суффиксу
        String sTemp = input.nextLine();
        String s = " " + sTemp;
        // нужно сделать предпосчёт
        // создадим массив для хэшей
        long[] h = new long[s.length()]; // хэши для префиксов
        h[0] = 0;
        long[] degrees = new long[s.length()]; // посчитаем все степени
        degrees[0] = 1;

        for (int i = 1; i < s.length(); i++) {
            h[i] = (h[i - 1] * x + (s.charAt(i) - 'a' + 1)) % p;
            degrees[i] = (degrees[i - 1] * x) % p;
        }
        int answer = -1;
        for (int i = 1; i < s.length() - 1; i++) {
            if ((h[i] + h[(s.length() - 1) - i] * degrees[i]) % p == ((h[s.length() - 1]) % p)) {
                answer = i;

            }
        }
        if (answer == -1) {
            System.out.println(sTemp.length());
        } else {
            System.out.println(sTemp.length() - answer);
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
