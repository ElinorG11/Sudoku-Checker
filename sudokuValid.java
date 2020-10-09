/*package whatever //do not write package name here */

import java.util.*;
import java.lang.*;
import java.io.*;


class sudokuValid {
    public static int MAT_SIZE = 9;
    public static int SQUARE_SIZE = 3;
    public static int NUM_OF_SQUARES = MAT_SIZE/SQUARE_SIZE;
    public static int DEC_BASE = 10;

    public static boolean checkHist(int[] histogram){
        for(int i = 0; i < DEC_BASE; i++){
            if(histogram[i] > 1){
                return false;
            }
        }
        return true;
    }
    public static boolean checkRows(int[][] matrix){
        int[] histogram = new int[DEC_BASE];
        for(int i = 0; i < MAT_SIZE; i++){
            for(int j = 0; j < MAT_SIZE; j++){
                if(matrix[i][j] == 0){
                    continue;
                }
                histogram[matrix[i][j]]++;
            }
            if(!checkHist(histogram)){
                return false;
            }
            Arrays.fill(histogram,0);
        }

        return true;
    }

    public static void transposeMat(int[][] transpose,int[][] matrix){
        // transpose matrix
        for(int i = 0; i < MAT_SIZE; i++){
            for(int j = 0; j < MAT_SIZE; j++){
                transpose[i][j] = matrix[j][i];
            }
        }
    }

    public static boolean checkColumns(int[][] matrix){
        int[][] transpose = new int[MAT_SIZE][MAT_SIZE];
        int[] histogram = new int[DEC_BASE];

        transposeMat(transpose,matrix);

        for(int i = 0; i < MAT_SIZE; i++){
            for(int j = 0; j < MAT_SIZE; j++){
                if(transpose[i][j] == 0){
                    continue;
                }
                histogram[transpose[i][j]]++;
            }
            if(!checkHist(histogram)){
                return false;
            }
            Arrays.fill(histogram,0);
        }
        return true;
    }

    public static void getSquareHist(int squareNum, int[][] matrix, int[] histogram){
        for (int i = squareNum*3; i < SQUARE_SIZE; i++){
            for(int j = 0; j < SQUARE_SIZE; j++){
                if(matrix[i][j] != 0){
                    histogram[matrix[i][j]]++;
                }
            }
        }
    }

    public static boolean checkSquares(int[][] matrix){
        int[] histogram = new int[DEC_BASE];
        for(int i = 0; i < NUM_OF_SQUARES; i++){
            getSquareHist(i,matrix, histogram);
            if(!checkHist(histogram)){
                return false;
            }
            Arrays.fill(histogram,0);
        }
        return true;
    }
    public static boolean checkValid(int[][] matrix){
        return checkRows(matrix) && checkColumns(matrix) && checkSquares(matrix);
        // return checkRows(matrix) && checkColumns(matrix);
    }


    public static void getInput(){
        //Enter data using BufferReader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Reading data using readLine
        String name = null;
        try {
            name = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Printing the read line
        // System.out.println(name);
    }

    public static void parseInput(String values, int[][] matrix){
        if(values == null) return;
        int index = 0;
        String delim = " ";
        String[] tokens = values.split(delim);
        for(int i = 0; i < MAT_SIZE; i++){
            for(int j = 0; j < MAT_SIZE; j++){
                matrix[i][j] = Integer.parseInt(tokens[index]);
                index++;
            }
        }
    }

    public static void main (String[] args) {
        int T; // number of test cases
        int counter = 0;
        int number; // get sudoku values
        ArrayList<int[][]> matrixList = new ArrayList<int[][]>(); // store all matrices

        Scanner in = new Scanner(System.in); // get number of cases
        T = in.nextInt();

        while(counter < T){
            int[][] matrix = new int[MAT_SIZE][MAT_SIZE];

            //Enter data using BufferReader
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            // Reading data using readLine
            String values = null;
            try {
                values = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            parseInput(values, matrix);

            matrixList.add(matrix);
            counter++;
        }

        // check if sudoku valid
        for(int[][] mat : matrixList){
            if(checkValid(mat)){
                System.out.println(1);
            }
            else System.out.println(0);
        }

    }
}