package net.javavideotutorials.assignment1;

import java.util.HashSet;
import java.util.Set;
import java.util.Random;

public class LotteryNumberGenerator 
{

  /**
   * This method should return a Set of 6 different
   * integers.  
   * 
   * Hint: use the 'Random' class located here: java.util.Random
   *       to generate random numbers
   * @return
   */
  public Set<Integer> generateLotteryNumbers ()
  {
    int min = 1;
    int max = 50;
    Random rand = new Random();
    Set<Integer> set = new HashSet<>();
    while (set.size() < 6) {
      int nextRand = rand.nextInt(max-min) + min;
      if (!set.contains(nextRand)) {
        set.add(nextRand);
      }
    }
    return set;
  }
}
