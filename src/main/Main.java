package main;

import data.Map;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map map = new Map();

        for (int i=0; i<map.getBlocksCount(); i++) {
            System.out.print("Enter x: ");
            Scanner myObj = new Scanner(System.in);
            int row = myObj.nextInt();

            System.out.print("Enter y: ");
            int col = myObj.nextInt();

            System.out.println("Changing: " + row + " - " + col);
            map.change_cell(row,col);

            map.display_on_out();
        }
    }
}
