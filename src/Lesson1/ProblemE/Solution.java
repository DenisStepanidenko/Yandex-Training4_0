package Lesson1.ProblemE;

import java.io.*;
import java.util.*;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        int n = input.nextInt();
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            String x = input.nextLine();
            arr[i] = x;
        }
        StringBuilder initial = new StringBuilder();
        initial.append("Initial array:").append('\n');
        for (int i = 0; i < arr.length; i++) {
            initial.append(arr[i]);
            if (i != arr.length - 1) {
                initial.append(", ");
            }
        }
        initial.append('\n').append("**********");
        System.out.println(initial);

        arr = radixSort(arr);

        StringBuilder sortedArr = new StringBuilder();
        sortedArr.append("Sorted array:").append('\n');
        for (int i = 0; i < arr.length; i++) {
            sortedArr.append(arr[i]);
            if(i != arr.length - 1){
                sortedArr.append(", ");
            }
        }
        System.out.println(sortedArr);
    }

    private static String[] radixSort(String[] arr) {
        // нужно реализовать поразрадяную сортировку
        int maxLength = arr[0].length();

        for (int i = maxLength - 1; i >= 0; i--) {
            System.out.println("Phase " + (maxLength - i));

            arr = countingSort(arr, i);
        }
        return arr;
    }

    private static String[] countingSort(String[] arr, int j) {
        // нужно сортировать по i - цифре
        int[] count = new int[10];
        int[] pos = new int[10];

        // заполним массив count
        for (int i = 0; i < arr.length; i++) {
            int number = Integer.parseInt(String.valueOf(arr[i].charAt(j)));
            count[number]++;
        }

        // заполним массив pos
        int sum = count[0];
        for (int i = 1; i < 10; i++) {
            pos[i] = sum;
            sum += count[i];
        }

        String[] output = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int number = Integer.parseInt(String.valueOf(arr[i].charAt(j)));
            output[pos[number]] = arr[i];
            pos[number]++;
        }

        Map<Integer, List<String>> bucket = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            bucket.put(i, new ArrayList<>());
        }

        for (String s : output) {
            int number = Integer.parseInt(String.valueOf(s.charAt(j)));
            List<String> temp = bucket.get(number);
            temp.add(s);
            bucket.put(number, temp);
        }

        StringBuilder answer = new StringBuilder();
        for (Map.Entry<Integer, List<String>> entry : bucket.entrySet()) {
            List<String> temp = entry.getValue();
            if (temp.size() == 0) {
                answer.append("Bucket ").append(entry.getKey()).append(": empty").append('\n');
            } else {
                answer.append("Bucket ").append(entry.getKey()).append(": ");

                for (int i = 0; i < temp.size(); i++) {
                    answer.append(temp.get(i));
                    if (i != temp.size() - 1) {
                        answer.append(", ");
                    }
                }
                answer.append('\n');
            }
        }
        answer.append("**********");
        System.out.println(answer);
        return output;
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
