package net.javavideotutorials.example;

public class Card implements Comparable<Card> {
  private Suit suit;
  private Value value;
  
  public Card (Suit suit, Value value) {
    this.suit = suit;
    this.value = value;
  }
  
  public Suit getSuit() {
    return suit;
  }

  public Value getValue() {
    return value;
  }

  @Override
  public int compareTo(Card o) {
    int flag = this.getValue().getSuitValue() - o.getValue().getSuitValue();

    if (flag == 0) {
      flag = this.getSuit().getSuitValue() - o.getSuit().getSuitValue();
    }

    return flag;
  }

  @Override
  public String toString() {
    return this.value.toString() + " of " + this.suit.toString();
  }
}
