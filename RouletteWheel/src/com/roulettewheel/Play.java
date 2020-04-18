package com.roulettewheel;

import java.util.Scanner;

public class Play {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.println("Welcome to Roulette!");
    String enter;

    while(true) {
      System.out.println("Press enter to play or \"q\" for quitting");
      enter = scan.nextLine();
      System.out.println(enter);
      if (enter.equalsIgnoreCase("q") || enter.equalsIgnoreCase("quit") || enter.isEmpty()) {
        break;
      }
      System.out.println("Cannot recognize " + enter);
    }

    if (enter.equalsIgnoreCase("q") || enter.equalsIgnoreCase("quit")) {
      System.out.println("See you next time!");
      return;
    }

    boolean playContinue = true;
    RouletteWheel rouletteWheel = RouletteWheel.getInstance();
    NumberGenerator numberGenerator = NumberGenerator.getInstance();

    while(playContinue) {
      System.out.println("Pick your number between 1 - 36: ");
      int guessed = scan.nextInt();
      int number = numberGenerator.generateNumber(rouletteWheel.roulettes.size());
      scan.nextLine();

      if (guessed == number) {
        System.out.println("You got the number!");
      } else {
        System.out.println("Your guess was wrong. Number is: " + number);
      }

      while(true) {
        System.out.println("Would you like to play again? Press enter to play. Press quit to quit the game: ");
        String next = scan.nextLine();
        if (next.equalsIgnoreCase("q") || next.equalsIgnoreCase("quit")) {
          System.out.println("See you next time!");
          playContinue = false;
          break;
        }
        if (next.isEmpty()) {
          playContinue = true;
          break;
        }
        System.out.println("Cannot recognize " + next);
      }
    }
  }
}
