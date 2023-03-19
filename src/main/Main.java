package main;

import data.Block;
import data.Map;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map map = new Map();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Current map");
        map.display_on_out();

        System.out.println();
        System.out.println("Change some map blocks");
        boolean keepChanging = true;
        while (keepChanging) {
            System.out.print("Enter x: ");
            int row = scanner.nextInt();

            System.out.print("Enter y: ");
            int col = scanner.nextInt();

            System.out.println("Changing: " + row + " - " + col);
            map.change_cell(row,col);

            System.out.println("Current map");
            map.display_on_out();

            System.out.println();
            System.out.println("Keep changing? Y/N");
            keepChanging = scanner.next().toLowerCase().startsWith("s");
        }

        System.out.println();
        System.out.println("Insert a block");
        System.out.print("Enter x");
        int row = scanner.nextInt();

        System.out.print("Enter y to insert a block: ");
        int col = scanner.nextInt();

        System.out.print("Enter block content: ");
        char content = scanner.next().charAt(0);

        map.insert_at_cords(row, col, new Block(content));

        System.out.println();
        System.out.println("Current map");
        map.display_on_out();

        scanner.close();
        System.out.println("Bye bye");
    }
}
