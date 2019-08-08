package com.raiayy.towerofhanoi;

import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        new MainFrame();
        String a = "A";
        String b = "B";
        String c = "C";
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of disks between 1 to 8:");
        int disk = input.nextInt();
        System.out.println("minimum number of moves are: "+ (int)(Math.pow(2, disk)-1));
       ToHanoi(disk, a, b, c);
    }
    public static void ToHanoi(int n, String a, String b, String c) {
        if (n == 0) {
            return;
        }
        ToHanoi(n - 1, a, c, b);
        System.out.println("Move Disc" + n + " from Tower " + a + " to the Tower " + c + ".");
        ToHanoi(n - 1, b, a, c);
    }

}

