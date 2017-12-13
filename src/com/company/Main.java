package com.company;

import java.util.Scanner;

public class Main {

    public static int[][] matrix1;
    public static int[][] matrix2;
    public static int[][] resultMatrix;
    public static int matrix1Row;
    public static int matrix1Col;
    public static int matrix2Row;
    public static int matrix2Col;
    public static MultiplyThread[][] threads;

    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a row and col of matrix one: ");
        matrix1Row = scanner.nextInt();
        matrix1Col = scanner.nextInt();

        System.out.println("Enter a row and col of matrix two: ");
        matrix2Row = scanner.nextInt();
        matrix2Col = scanner.nextInt();

        if(matrix1Col != matrix2Row){
            System.out.println("First matrix columns size should be same as second matrix row size");
            return;
        }

        matrix1 = new int[matrix1Row][matrix1Col];
        matrix2 = new int[matrix2Row][matrix2Col];

        System.out.println("Enter matrix one nums: ");
        for (int i = 0; i < matrix1Row; i++) {
            for (int j = 0; j < matrix1Col; j++) {
                matrix1[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter matrix two nums: ");
        for (int i = 0; i < matrix1Row; i++) {
            for (int j = 0; j < matrix1Col; j++) {
                matrix2[i][j] = scanner.nextInt();
            }
        }

        resultMatrix = new int[matrix1Row][matrix2Col];
        threads = new MultiplyThread[matrix1Row][matrix2Col];
        for (int i = 0; i < matrix1Row; i++) {
            for (int j = 0; j < matrix2Col; j++) {
                MultiplyThread thread = new MultiplyThread(i, j);
                threads[i][j] = thread;
                thread.start();
            }
        }

        for (int i = 0; i < matrix1Row; i++) {
            for (int j = 0; j < matrix2Col; j++) {
                threads[i][j].join();
            }
        }


        System.out.println("Result: ");
        for(int i = 0; i < matrix1Row; i++){
            for(int j = 0; j < matrix2Col; j++){
                System.out.print(resultMatrix[i][j] + " ");
            }
            System.out.println();
        }

    }


    private static class MultiplyThread extends Thread {

        private int row;
        private int col;

        public MultiplyThread(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void run() {
            int sum = 0;
            for(int i = 0; i < Main.matrix1Col; i++){
                sum += Main.matrix1[row][i] * matrix2[i][col];
            }
            Main.resultMatrix[row][col] = sum;
        }
    }
}
