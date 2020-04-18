package com.roulettewheel;

import java.util.ArrayList;
import java.util.List;

public class RouletteWheel {
  final List<Roulette> roulettes = new ArrayList<>();
  static RouletteWheel rouletteWheel = null;

  private RouletteWheel() {
    for (int i = 1; i <= 36; i++) {
      Roulette roulette = new Roulette(String.valueOf(i));
      roulettes.add(roulette);
    }

    Roulette roulette0 = new Roulette("0");
    Roulette roulette00 = new Roulette("00");
    roulettes.add(roulette0);
    roulettes.add(roulette00);
  }

  public static RouletteWheel getInstance() {
    if (rouletteWheel == null) {
      rouletteWheel = new RouletteWheel();
    }

    return rouletteWheel;
  }

}
