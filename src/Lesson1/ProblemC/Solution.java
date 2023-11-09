package Lesson1.ProblemC;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        int n = input.nextInt();
        int[] l = new int[n];
        for (int i = 0; i < n; i++) {
            int x = input.nextInt();
            l[i] = x;
        }
        int m = input.nextInt();
        int[] r = new int[m];
        for (int i = 0; i < m; i++) {
            int x = input.nextInt();
            r[i] = x;
        }

        int[] answer = new int[n+m];
        merge(l , r , answer);

        StringBuilder out = new StringBuilder();
        for (int i : answer) {
            out.append(i).append(" ");
        }
        System.out.println(out);
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
