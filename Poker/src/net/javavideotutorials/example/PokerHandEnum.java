package net.javavideotutorials.example;

public enum PokerHandEnum {
  HIGH_CARD(1, 1, "High Card"),
  PAIR(2, 2, "Pair"),
  TWO_PAIR(3, 4, "Two pair"),
  THREE_OF_A_KIND(4, 3, "Three of a kind"),
  STRAIGHT(5, 5, "Straight"),
  FLUSH(6, 5, "Flush"),
  FULL_HOUSE(7, 5, "Full house"),
  FOUR_OF_A_KIND(8, 4, "Four of a kind"),
  STRAIGHT_FLUSH(9, 5, "Straight flush"),
  ROYAL_FLUSH(10, 5, "Royal flush");
  
  private int strength;
  private int numSignificantCards;
  private String name;
  
  private PokerHandEnum(int strength, int numSignificantCards, String name) {
    this.strength = strength;
    this.numSignificantCards = numSignificantCards;
    this.name = name;
  }

  public int getStrength() {
    return strength;
  }

  public int getNumSignificantCards() {
    return numSignificantCards;
  }

  public String getName() {
    return name;
  }
}
