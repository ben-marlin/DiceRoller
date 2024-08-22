package edu.guilford;

import java.util.Scanner;

public class Main {

    // calculates p! / s! = (s+1)*(s+2)*...*(p-1)*p
    private static int perm(int p, int s) {
        // set product to 1 in case p == s
        int product = 1;

        // for loop starts at s, ends at p-1
        for (int index = s; index < p; index++) {

            // multiply by index + 1 so product goes from s+1 to p
            product *= index + 1;
        }

        // every method ends with a return statement
        return product;
    }

    public static void main(String[] args) {

        // I like to instantiate this at the start
        Scanner scan = new Scanner(System.in);

        System.out.println("This program calculates permutations - how many ordered subgroups can be formed from a population.");

        System.out.print("What is the size of the population?  ");
        int pop = scan.nextInt();

        System.out.print("What is the size of the subgroup? ");
        int sub = scan.nextInt();

        System.out.println(pop + "_P_" + sub + " = " + perm(pop,sub));

        // forgetting to close a scanner creates a "resource leak"
        scan.close();
    }
}