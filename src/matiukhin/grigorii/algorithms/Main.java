package matiukhin.grigorii.algorithms;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nDigits = scanner.nextInt();
        System.out.println(getPi(nDigits));
    }

    private static String getPi(int nDigits) {
        // Rabinowitz and Wagon argued that it takes [10n/3], where [] is the floor function, digits to calculate n decimal digits.
        // Arndt and Haenel found an inaccuracy in their derivation and changed that to [10n/3] + 1.
        int[] arr = new int[Math.floorDiv(10 * nDigits, 3) + 1];
        Arrays.fill(arr, 2);

        int[] pi = new int[nDigits];
        for (int i = 0; i < nDigits; i++) {
            int quotient = 0;
            for (int j = arr.length - 1; j >= 0; j--) {
                int value = arr[j] * 10 + quotient * (j + 1);
                int divisor = 2 * j + 1;
                arr[j] = value % divisor;
                quotient = value / divisor;
            }

            arr[0] = quotient % 10;
            pi[i] = quotient / 10;

            if (quotient >= 100) {
                pi[i] = 0;
                incrementPrevious(pi, i);
            }
        }

        String pie = Arrays.toString(pi).replaceAll("[,\\s\\[\\]]", "");
        if (pie.length() > 1) {
            pie = pie.replaceFirst(String.valueOf(pie.charAt(1)), "." + pie.charAt(1));
        }
        return pie;
    }

    private static void incrementPrevious(int[] pi, int i) {
        pi[i - 1] += 1;
        if (pi[i - 1] == 10) {
            pi[i - 1] = 0;
            incrementPrevious(pi, i - 1);
        }
    }
}
