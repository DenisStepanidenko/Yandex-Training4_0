package Lesson2.ProblemE;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        // здесь будет реализован алгоритм Манакера
        // поиск подпалиндромов

        String s = input.nextLine();
        // будем хранить пару чисел (l,r) - самый правый подпалиндром (самое большое значение r)

        // будем хранить два массива d1[] d2[]
        // d1[] - массив где мы будем хранить количество палиндромов нечётное длины с центром в i
        // d2[] - массив где мы будем хранить количество палиндромов нечётной длины с центром в i


        int[] d1 = new int[s.length()];
        int[] d2 = new int[s.length()];

        // посчитаем палиндромы нечётной длины
        // главная идея, что если наш индекс i будет лежать в самом правом палиндроме
        // то мы можем найти симметричную точку точке i : j = l + (r-i)
        // и почти всегда d[i] = d[j], но тонкость в том, что если левая граница
        // вышла за левую границу исходного палиндрома - то ответ мы не знаем, ведь
        // симметричности за пределами самого правого палиндрома мы не знаем
        // но тогда начиная с d[i] просто сделаем тривиальный алгоритм
        int left = 0, right = -1;
        long answer = 0;
        for (int i = 0; i < s.length(); i++) {
            int k = i > right ? 1 : Math.min(d1[left + (right - i)], right - i + 1);
            // k = 1 , так как
            while ((i + k < s.length()) && (i - k >= 0) && (s.charAt(i - k) == s.charAt(i + k))) {
                k++;
            }
            d1[i] = k;
            if (i + k - 1 > right) {
                // обновим правую границу
                right = i + k - 1;
                left = i - k + 1;
            }
        }
        answer = Arrays.stream(d1).sum();

        // посчитаем теперь палиндромы чётной длины
        left = 0;
        right = -1;
        for (int i = 0; i < s.length(); i++) {
            int k = i > right ? 0 : Math.min(right - i + 1, d2[left + (right - i) + 1]);
            // k = 0 так как палиндром из одного символа - это палиндром нечётной длины
            while ((i + k < s.length()) && (i - k - 1 >= 0) && (s.charAt(i + k) == s.charAt(i - k - 1))) {
                k++;
            }
            d2[i] = k;
            if (i + k - 1 > right) {
                right = i + k - 1;
                left = i - k;
            }
        }
        answer += Arrays.stream(d2).sum();

        System.out.println(answer);
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
