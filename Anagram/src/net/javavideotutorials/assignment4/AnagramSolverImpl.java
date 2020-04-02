package net.javavideotutorials.assignment4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This class should implement the AnagramSolver interface, the
 * naming convention here is a Java standard, any class that implements
 * an interface should end with the letters Impl (suggesting it's an
 * implementation of an interface)
 * 
 * @author Trevor Page
 */
public class AnagramSolverImpl implements AnagramSolver {

  /**
   * Using hashmap to check the anagram.
   * Time complexity O(n)
   * Space complexity O(n)
   * @param word1
   * @param word2
   * @return true for anagram; false otherwise
   */
  private boolean isAnagramHash(String word1, String word2) {
    Map<Character, Integer> hashmap = new HashMap<>();

    for (int i = 0; i < word1.length(); i++) {
      char chr = word1.charAt(i);

      if (!hashmap.containsKey(chr)) {
        hashmap.put(chr, 1);
      } else {
        int count = hashmap.get(chr) + 1;
        hashmap.put(chr, count);
      }
    }

    for (int i = 0; i < word2.length(); i++) {
      char chr = word2.charAt(i);

      if (!hashmap.containsKey(chr)) {
        return false;
      } else {
        int count = hashmap.get(chr) - 1;
        if (count < 0) {
          return false;
        }
        hashmap.put(chr, count);
      }
    }

    return hashmap.values().stream().allMatch(val -> val == 0);
  }

  /**
   * Using sort to check anagram.
   * Time complexity O(n log (n))
   * Space complexity O(n)
   * @param word1
   * @param word2
   * @return true for anagram; false otherwise
   */
  private boolean isAnagramSort(String word1, String word2) {
    char[] word1Arr = word1.toCharArray();
    char[] word2Arr = word2.toCharArray();

    Arrays.sort(word1Arr);
    Arrays.sort(word2Arr);

    return Arrays.equals(word1Arr, word2Arr);
  }

  @Override
  public boolean isAnAnagram(String word1, String word2) {
    if (word1 == null || word2 == null) {
      return false;
    }

    if (word1.length() != word2.length()) {
      return false;
    }

    return isAnagramSort(word1.toLowerCase(), word2.toLowerCase());
  }
}
