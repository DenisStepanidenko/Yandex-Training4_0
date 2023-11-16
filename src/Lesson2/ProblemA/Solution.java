package Lesson2.ProblemA;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        // тут используется хэши для строк
        long p = (long) (Math.pow(10, 9) + 7); // простое число чтобы не было переполнений
        long x = 257; // то значение, которое мы будем подставлять в полином
        String sTemp = input.nextLine();
        int q = input.nextInt();
        String s = " " + sTemp; // добавляем пустую чтобы символы читать с 1

        long[] h = new long[sTemp.length() + 1]; // хэши для префиксов
        h[0] = 0;
        long[] degrees = new long[sTemp.length() + 1];
        degrees[0] = 1;

        // сделаем предпросчёт
        for (int i = 1; i < s.length(); i++) {
            h[i] = (h[i - 1] * x + (s.charAt(i) - 'a' + 1)) % p;
            degrees[i] = (degrees[i - 1] * x) % p;
        }


        for (int i = 0; i < q; i++) {
            int l = input.nextInt();
            int a = input.nextInt();
            int b = input.nextInt();
            a++;
            b++;

            boolean flag = (((h[(a + l - 1)] + h[(b - 1)] * degrees[l]) % p) == ((h[(b + l - 1)] + h[(a - 1)] * degrees[l]) % p));
            if (flag) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
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
