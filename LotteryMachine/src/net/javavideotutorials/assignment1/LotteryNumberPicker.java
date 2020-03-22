package net.javavideotutorials.assignment1;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class LotteryNumberPicker 
{

  /**
   * This method should pull input from the user in the console.  It should
   * gather 6 Integers from the user and then store these numbers in a Set of
   * Integers that will then be returned by the method.
   * 
   * Hint: use the following code to get numbers:
   * 
   * Scanner in = new Scanner(System.in);
   * in.nextInt();
   * 
   * @return Set of 6 numbers that were chosen by the user
   * @throws IOException
   */
  public Set<Integer> promptUserForLotteryNumbers () throws IOException, LotterNumberException {
    int count = 0;

    Set<Integer> set = new HashSet<>();
    Scanner in = new Scanner(System.in);

    while(count < 6) {
      System.out.printf("Enter %d more numbers: ", count);

      // Check if there is at least input integers
      if (!in.hasNextInt()) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Requires ");
        stringBuilder.append(String.valueOf(count));
        stringBuilder.append("more numbers.");
        throw new LotterNumberException(stringBuilder.toString());
      }

      int inputInt = in.nextInt();

      // Check if input is between 1 to 49
      if (inputInt < 1 || inputInt > 49) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Number needs to be between 1 to 49 inclusive but found ");
        stringBuilder.append(String.valueOf(inputInt));
        throw new LotterNumberException(stringBuilder.toString());
      }

      // Check if there is duplicate number entered
      if (set.contains(inputInt)) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Duplicate number ");
        stringBuilder.append(String.valueOf(inputInt));
        stringBuilder.append("is found.");
        throw new LotterNumberException(stringBuilder.toString());
      }

      set.add(inputInt);
      count++;
    }

    return set;
  }
}
