package com.roulettewheel;

import java.util.Objects;

public class Roulette {
  Color color;
  String number;

  public Roulette(String number) {
    this.number = number;
    if (number == "0" || number == "00") {
      color = Color.GREEN;
    } if (Integer.parseInt(number) % 2 == 0) {
      color = Color.BLACK;
    } else {
      color = Color.RED;
    }
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Roulette roulette = (Roulette) o;
    return color == roulette.color &&
            Objects.equals(number, roulette.number);
  }

  @Override
  public int hashCode() {
    return Objects.hash(color, number);
  }

  @Override
  public String toString() {
    return "Roulette{" +
            "color=" + color +
            ", number='" + number + '\'' +
            '}';
  }
}
