package com.roulettewheel;

import java.util.Random;

public class NumberGenerator {
  private static NumberGenerator numberGenerator = null;

  private NumberGenerator() {}

  public static NumberGenerator getInstance() {
    if (numberGenerator == null) {
      numberGenerator = new NumberGenerator();
    }
    return numberGenerator;
  }

  public Integer generateNumber(Integer range) {
    Random rand = new Random();
    return 1 + rand.nextInt(range);
  }
}
