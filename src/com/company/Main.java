package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        System.out.println("Starting DB...");
        Database BluerayDB = new Database();
        long endTime   = System.nanoTime();
        System.out.println("DB took " + (Math.round((endTime - startTime) / 100000.0)/10000.0) + " seconds to open");
        System.out.println();
        System.out.println();

        Boolean exit = false;
        //CLI
        while(!exit) {
            System.out.println("What do you want to do? [A]dd to Database, [V]iew Database or [E]xit Database");
            Scanner inputScanner = new Scanner(System.in);
            String input = inputScanner.nextLine();
            switch (input.toUpperCase()) {
                case "A":
                    System.out.println("Enter Key");
                    String Key = inputScanner.nextLine();
                    System.out.println("Enter Name");
                    String Name = inputScanner.nextLine();
                    System.out.println("Enter Date");
                    String Date = inputScanner.nextLine();
                    System.out.println("Enter Amount Of Copies");
                    int Copies = Integer.parseInt(inputScanner.nextLine());
                    System.out.println("Enter Runtime In Mins");
                    int Runtime = Integer.parseInt(inputScanner.nextLine());
                    System.out.println("Enter Score");
                    int Score = Integer.parseInt(inputScanner.nextLine());
                    BluerayDB.Add(Key, Name, Date, Copies, Runtime, Score);
                    break;
                case "V":
                    System.out.println("Would you like to sort [A]scending, [D]escending or [C]ancel?");
                    String sortInput = inputScanner.nextLine();
                    switch (sortInput.toUpperCase()){
                        case "A":
                            System.out.println("List sorted by ascending:");
                            BluerayDB.ViewList(BluerayDB.Sort(false,1));

                            break;
                        case "D":
                            System.out.println("List sorted by descending:");
                            BluerayDB.Sort(true,1);
                            break;
                        case "C":
                            System.out.println("You have cancelled your search");
                            exit = true;
                        default:
                            break; //Loops if incorrect input is entered
                    }

                case "E":
                    exit = true;
                default:
                    break; //invalid input does nothing - loops again
            }
            //Input
        }
        //End CLI

        BluerayDB.WriteFile();
    }
}
