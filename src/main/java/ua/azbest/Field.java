package ua.azbest;

import java.util.Random;
import java.util.Scanner;

public class Field {

    int[][] num;
    boolean[][] right;
    boolean[][] user;

    public Field(int w, int h) {
        num = new int[w][h];
        right = new boolean[w][h];
        user = new boolean[w][h];
        Random rand = new Random();
        for(int i=0; i<w; i++) {
            for(int j=0; j<h; j++) {
                num[i][j] = rand.nextInt(9)+1;
                right[i][j] = rand.nextBoolean();
                user[i][j] = true;
            }
        }
    }

    public int getRightRow(int row) {
        int sum = 0;
        for (int j = 0; j < num[row].length; j++)
            if (right[row][j]) sum += num[row][j];
        return sum;
    }

    public int getUserRow(int row) {
        int sum = 0;
        for (int j = 0; j < num[row].length; j++)
            if (user[row][j]) sum += num[row][j];
        return sum;
    }

    public int getRightCol(int col) {
        int sum = 0;
        for (int i = 0; i < num.length; i++)
            if (right[i][col]) sum += num[i][col];
        return sum;
    }

    public int getUserCol(int col) {
        int sum = 0;
        for (int i = 0; i < num.length; i++)
            if (user[i][col]) sum += num[i][col];
        return sum;
    }

    public void turn(int row, int col) {
        user[row][col] ^= true;
    }

    public boolean isWin() {
        int i=0;
        while (i<num.length) {
            int j = 0;
            while (j < num[0].length) {
                if (user[i][j] != right[i][j])
                    return false;
                j++;
            }
            i++;
        }
        return true;
    }

    public int getElement(int row, int col) {
        return num[row][col];
    }

    public void show() {
        System.out.print("       ");
        for (int j=0; j<num[0].length;j++) {
            System.out.print(String.format("%3d", getRightCol(j)));
        }
        System.out.println();

        System.out.print("       ");
        for (int j=0; j<num[0].length;j++) {
            System.out.print(String.format("%3d", getUserCol(j)));
        }
        System.out.println();

        System.out.print("----------------------  ");
        for (int q=0; q<3*num[0].length; q++) System.out.print("-");
        System.out.println();

        for (int k=0; k<num.length; k++) {
            int sum = 0;
            for (int j = 0; j < num[k].length; j++)
                if (right[k][j]) sum += num[k][j];
            System.out.print(String.format("%3d", sum));

            sum = 0;
            for (int j = 0; j < num[k].length; j++)
                if (user[k][j]) sum += num[k][j];
            System.out.print(String.format("%3d", sum) + "|");

            for (int i = 0; i < num[k].length; i++) {
                System.out.print(String.format("%3d", num[k][i]));
            }

            System.out.print("  ");
            for (int i = 0; i < num[k].length; i++) {
                System.out.print(String.format("%3d", user[k][i]?1:0));
            }

            System.out.println();
        }


        /* for gebug
        System.out.print("======================  ");
        for (int q=0; q<3*num[0].length; q++) System.out.print("=");
        System.out.println();
        for (int i=0; i<num.length; i++) {
            for (int j=0; j<num[0].length; j++) {
                System.out.print(right[i][j]?1:0);
            }
            System.out.println();
        }
        //*/

    }

    public static void main(String[] args) {
        Field f = new Field(2,2);
        while (!f.isWin()){
            f.show();
            Scanner sc = new Scanner(System.in);
            int row, col;
            row = sc.nextInt();
            col = sc.nextInt();
            f.turn(row, col);
        }
        System.out.println("You win");
    }

}