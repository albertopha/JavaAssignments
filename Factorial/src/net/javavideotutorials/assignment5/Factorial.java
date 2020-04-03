package net.javavideotutorials.assignment5;

public class Factorial {
  /**
   * This method is where all the magic happens.  This will be
   * your recursive method that will (in the end) return the
   * proper total of the factorial number that's passed in. 
   * @param value variable represents the factorial numbers being
   *        multiplied... so if you're solving 5!, the first 'value'
   *        passed in here should be 5.
   * @return the total of the factorial calculation so, 5! should equal 120
   */
  public int factorial(int value) {
    if (value == 0) {
      return 1;
    }

    if (value < 3) {
      return value;
    }

    return value * factorial(value-1);
  }
}
