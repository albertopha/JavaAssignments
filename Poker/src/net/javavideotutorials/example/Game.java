package net.javavideotutorials.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {
  private Deck deck;
  private List<Player> players = new ArrayList<Player>();
  private List<Card> communityCards = new ArrayList<Card>();
  
  public Game () {
    for (int i=1; i<=4; i++)
      players.add(new Player("player"+i));
    
    deck = new Deck();
    deck.shuffleDeck();
    
    deal();
    // Once you have a good chunk of your Unit Tests passing, you can
    // uncomment the line below and start running this code via the Runnable class
    //System.out.println(determineWinner());
  }
  
  /**
   * This method is fully implemented and should work properly... if you need to make changes
   * then feel free
   * @return either the winning or tieing statement
   */
  String determineWinner() {
    int winningPlayerHandStrength = 0;
    List<Card> winningPlayerWinningCards = null;
    List<Card> winningPlayerWinningHand = null;
    Player winningPlayer = null;
    boolean draw = false;
    String kicker = "";
    
    for (Player player : players) {
      player.setPlayerHandStrength(checkForHand(player.getHand(), communityCards));
      player.setPlayableHand(player.getPlayerHandStrength().getPokerHand());
      List<Card> playerWinningCards = player.getPlayerHandStrength().getWinningCards();
      if (player.getPlayerHandStrength().getPokerHandEnum().getStrength() > winningPlayerHandStrength) {
        winningPlayer = player;
        winningPlayerHandStrength = player.getPlayerHandStrength().getPokerHandEnum().getStrength();
        winningPlayerWinningCards = playerWinningCards;
        winningPlayerWinningHand = player.getPlayableHand();
        draw = false;
        kicker = "";
      }
      else if (player.getPlayerHandStrength().getPokerHandEnum().getStrength() == winningPlayerHandStrength && winningPlayer != player) {
        Boolean newWinner = null;
        
        // compare two players' winning cards first
        Collections.sort(winningPlayerWinningCards);
        Collections.sort(playerWinningCards);
        for (int i = playerWinningCards.size()-1; i > 0; i--) {
          if (playerWinningCards.get(i).getValue().getSuitValue() > winningPlayerWinningCards.get(i).getValue().getSuitValue()) {
            newWinner = true;
            draw = false;
            kicker = "";
            if (player.getPlayerHandStrength().getPokerHandEnum().equals(PokerHandEnum.FLUSH)) {
              kicker = ", " + playerWinningCards.get(i).getValue() + " kicker";
            }
          } else if (playerWinningCards.get(i).getValue().getSuitValue() == winningPlayerWinningCards.get(i).getValue().getSuitValue()) {
          } else {
            newWinner = false;
            break;
          }
        }
        
        // if no winner is found then compare the entire hands against each other
        if (newWinner == null) {
          for (int i = 4; i >= 0; i--) {
            if (player.getPlayableHand().get(i).getValue().getSuitValue() > winningPlayerWinningHand.get(i).getValue().getSuitValue()) {
              newWinner = true;
              draw = false;
              if (!player.getPlayerHandStrength().getPokerHand().equals(PokerHandEnum.HIGH_CARD))
                  kicker = ", " + player.getPlayableHand().get(i).getValue() + " kicker";
              break;
            }
            else if (player.getPlayableHand().get(i).getValue().getSuitValue() == winningPlayerWinningHand.get(i).getValue().getSuitValue())
            {
            }
            else
            {
              newWinner = false;
              break;
            }
          }
        }
        
        if (newWinner == null)
        {
          draw = true;
        }
        else if (newWinner)
        {
          draw = false;
          winningPlayer = player;
          winningPlayerHandStrength = player.getPlayerHandStrength().getPokerHandEnum().getStrength();
          winningPlayerWinningHand = player.getPlayableHand();
          winningPlayerWinningCards = playerWinningCards;
        }
      }
      System.out.print(player);
      System.out.print(" - " + player.getPlayerHandStrength());
      System.out.println();
    }
    
    System.out.println();
    for (Card card : communityCards)
    {
      System.out.println(card);
    }
    System.out.println();
    if (draw)
     return "There was a draw with hand: " + winningPlayer.getPlayerHandStrength();
    else
      return winningPlayer + " wins with " + winningPlayer.getPlayerHandStrength() + kicker;
  }

  /**
   * This method is the core of the game.  This is where we will determine what poker hands can be
   *  created from the cards in the player's hand and the community cards.  In my solution, I identified
   *  all the different Poker Hands that could be created from the 7 cards (2 in hand, 5 community cards on 'table')
   *  then returned only the strongest hand.
   *  
   * @param hand This is the list of cards that are held by an individual player (2 cards)
   * @param communityCards This is the list of cards that can be shared by all players (5 cards)
   * @return The strongest poker hand that a particular player holds
   *         This hand does not have to be made with the hards in the player's hand, it can be solely
   *         made up of just the community cards if need be... or it can be made up of either 1 or both
   *         cards in the player's hand.
   */
  ActualPokerHand checkForHand(List<Card> hand, List<Card> communityCards)
  {
    // initialize an empty list of actual poker hands that will be populated
    // as it gets passed into the individual PokerHand checking methods
    List<ActualPokerHand> pokerHands = new ArrayList<ActualPokerHand>();
    
    // All poker hands have a high card, this method will determine which card
    // is the high card.  Once it figures out which card is the high card, it will
    // assign a new ActualPokerHand to the List of pokerHands.
    //
    // ex. pokerHands.add(new ActualPokerHand(PokerHandEnum.HIGH_CARD, winningCard));
    checkForHighcard(hand, communityCards, pokerHands);
    checkForPair(hand, communityCards, pokerHands);
    checkForThreeOfAKind(hand, communityCards, pokerHands);
    checkForTwoPair(hand, communityCards, pokerHands);
    checkForStraight(hand, communityCards, pokerHands);
    checkForFlush(hand, communityCards, pokerHands);
    checkForFullHouse(hand, communityCards, pokerHands);
    checkForFourOfAKind(hand, communityCards, pokerHands);
    checkForStraightFlush(hand, communityCards, pokerHands);

    return Collections.max(pokerHands);
  }

  /**
   * In my solution, I used this method to determine if we had a straight flush AND if we had a royal flush... so I didnt
   * create a separate method to check for a royal flush.
   * 
   * @param hand the 2 cards in a given player's hand
   * @param communityCards the 5 community cards
   * @param pokerHands a list of all the actual PokerHands that can be made from the 7 available cards
   */
  private void checkForStraightFlush(List<Card> hand, List<Card> communityCards, List<ActualPokerHand> pokerHands) {
    Map<Suit, List<Card>> suitMap = new HashMap<>();

    setSuitMap(suitMap, hand);
    setSuitMap(suitMap, communityCards);

    List<Card> possibleFlushCards = null;
    for (List<Card> cards : suitMap.values()) {
      if (cards.size() >= 5) {
        possibleFlushCards = cards;
        break;
      }
    }

    if (possibleFlushCards != null) {
      Collections.sort(possibleFlushCards);
      for (int i = 0; i < possibleFlushCards.size(); i++) {
        int start = i;
        int end = i+4;
        List<Card> flushCards = new ArrayList<>();

        if (end < possibleFlushCards.size()) {
          while (start < end) {
            Card current = possibleFlushCards.get(start);
            Card next = possibleFlushCards.get(start + 1);
            Integer currentVal = current.getValue().getSuitValue();
            Integer nextVal = next.getValue().getSuitValue();

            if (currentVal + 1 != nextVal) {
              break;
            }

            flushCards.add(current);
            // Check for last card
            if (start+1 == end) {
              flushCards.add(next);
            }
            start++;
          }

          if (flushCards.size() == 5) {
            // Check if it's royal flush
            if(flushCards.get(0).getValue().getSuitValue() == 10) {
              ActualPokerHand newPokerHand = new ActualPokerHand(PokerHandEnum.ROYAL_FLUSH, flushCards);
              pokerHands.add(newPokerHand);
            } else {
                ActualPokerHand newPokerHand = new ActualPokerHand(PokerHandEnum.STRAIGHT_FLUSH, flushCards);
                pokerHands.add(newPokerHand);
            }
          }
        } else {
          break;
        }
      }
    }
  }

  private void checkForFullHouse(List<Card> hand, List<Card> communityCards, List<ActualPokerHand> pokerHands) {
    Map<Integer, List<Card>> numMap = new HashMap<>();
    setNumMap(numMap, hand);
    setNumMap(numMap, communityCards);

    List<List<Card>> twos = new ArrayList<>();
    List<List<Card>> threes = new ArrayList<>();
    for (List<Card> cards : numMap.values()) {
      if (cards.size() >= 3) {
        threes.add(cards);
      } else if (cards.size() >= 2) {
        twos.add(cards);
      }
    }

    // No Full house detected
    if ((twos.size() == 0 && threes.size() < 2) || threes.size() == 0) {
      return;
    }

    // Get maximum list of cards out of twos list
    List<Card> maxTwo = null;
    for (List<Card> two : twos) {
      if (maxTwo == null) {
        maxTwo = two;
      } else if (two.get(0).getValue().getSuitValue() > maxTwo.get(0).getValue().getSuitValue()) {
        maxTwo = two;
      }
    }

    // Get maximum list of cards out of threes list
    List<Card> maxThree = null;
    for (List<Card> three : threes) {
      if (maxThree == null) {
        maxThree = three;
        continue;
      }

      Integer threeVal = three.get(0).getValue().getSuitValue();
      Integer maxThreeVal = maxThree.get(0).getValue().getSuitValue();

      if (threeVal >= maxThreeVal) {
        // When more than two threes exist
        if (maxTwo == null || maxTwo.get(0).getValue().getSuitValue() < maxThreeVal) {
          maxTwo = maxThree.subList(0, 2);
        }
        maxThree = three;
      }
    }

    if (maxTwo != null && maxThree != null) {
      maxTwo.addAll(maxThree);
      ActualPokerHand actualPokerHand = new ActualPokerHand(PokerHandEnum.FULL_HOUSE, maxTwo);
      pokerHands.add(actualPokerHand);
    }
  }

  private void checkForTwoPair(List<Card> hand, List<Card> communityCards, List<ActualPokerHand> pokerHands) {
    Map<Suit, List<Card>> suitMap = new HashMap<>();
    setSuitMap(suitMap, hand);
    setSuitMap(suitMap, communityCards);

    List<Card> maxCards = null;
    for (Map.Entry mapElement: suitMap.entrySet()) {
      Suit suit = (Suit) mapElement.getKey();
      List<Card> cards = (List<Card>) mapElement.getValue();

      if (cards.size() >=2) {
        if (maxCards == null ||
            maxCards.get(0).getSuit().getSuitValue() < suit.getSuitValue()) {
          maxCards = cards;
        }
      }
    }

    if (maxCards != null) {
      Collections.sort(maxCards);
      maxCards = maxCards.subList(maxCards.size()-2, maxCards.size());
      ActualPokerHand actualPokerHand = new ActualPokerHand(PokerHandEnum.TWO_PAIR, maxCards);
      pokerHands.add(actualPokerHand);
    }
  }
  
  private void checkForHighcard(List<Card> hand, List<Card> communityCards, List<ActualPokerHand> pokerHands) {
    List<Card> combinedList = Stream.of(hand, communityCards).flatMap(x -> x.stream()).collect(Collectors.toList());
    Card maxCard = Collections.max(combinedList);
    pokerHands.add(new ActualPokerHand(PokerHandEnum.HIGH_CARD, new ArrayList<>(Arrays.asList(maxCard))));
  }

  private void checkForFlush(List<Card> hand, List<Card> communityCards, List<ActualPokerHand> pokerHands) {
    Map<Suit, List<Card>> suitMap = new HashMap<>();
    setSuitMap(suitMap, hand);
    setSuitMap(suitMap, communityCards);


    List<Card> flushCards = null;

    try {
      flushCards = suitMap.values()
              .stream()
              .filter(cards -> cards.size() == 5)
              .findFirst()
              .get();
    } catch (Exception e) {
      // Do nothing
    }

    if (flushCards != null) {
      pokerHands.add(new ActualPokerHand(PokerHandEnum.FLUSH, flushCards));
    }
  }

  private void checkForStraight(List<Card> hand, List<Card> communityCards, List<ActualPokerHand> pokerHands) {
    List<Card> combinedList = Stream.of(hand, communityCards).flatMap(x -> x.stream()).collect(Collectors.toList());
    Collections.sort(combinedList);
    List<Card> straightCards = null;

    for (int i = 0; i < 3; i++) {
      List<Card> currentStraightCard = new ArrayList<>();
      int[] suitArr = new int[]{1,1,1,1};
      for (int j = i; j < i+4; j++) {
        Card current = combinedList.get(j);
        Card next = combinedList.get(j+1);
        Integer currentVal = current.getValue().getSuitValue();
        Integer nextVal = next.getValue().getSuitValue();

        if (currentVal+1 == nextVal) {
          suitArr[current.getSuit().getSuitValue()-1]++;
          currentStraightCard.add(combinedList.get(j));

          if (j+1 == i+4) {
            currentStraightCard.add(next);
          }
        } else {
          break;
        }
      }

      if (currentStraightCard.size() == 5
        && suitArr[0] != 5
        && suitArr[1] != 5
        && suitArr[2] != 5
        && suitArr[3] != 5
      ) {
        straightCards = new ArrayList<>(currentStraightCard);
      }
    }

    if (straightCards != null) {
      pokerHands.add(new ActualPokerHand(PokerHandEnum.STRAIGHT, straightCards));
    }
  }

  private void checkForPair(List<Card> hand, List<Card> communityCards, List<ActualPokerHand> pokerHands) {
    Map<Integer, List<Card>> numMap = new HashMap<>();
    List<Card> combinedList = Stream.of(hand, communityCards).flatMap(x -> x.stream()).collect(Collectors.toList());
    Collections.sort(combinedList);
    setNumMap(numMap, combinedList);

    List<Card> maxCards = null;
    for (List<Card> cards : numMap.values()) {
      if (cards.size() >= 2) {
        maxCards = cards.subList(cards.size()-2, cards.size());
      }
    }

    if (maxCards != null) {
      pokerHands.add(new ActualPokerHand(PokerHandEnum.PAIR, maxCards));
    }
  }

  private void checkForThreeOfAKind(List<Card> hand, List<Card> communityCards, List<ActualPokerHand> pokerHands) {
    Map<Suit, List<Card>> suitMap = new HashMap<>();
    List<Card> combinedList = Stream.of(hand, communityCards).flatMap(x -> x.stream()).collect(Collectors.toList());
    Collections.sort(combinedList);
    setSuitMap(suitMap, combinedList);

    List<Card> threeOfKinds = null;
    for (List<Card> cards : suitMap.values()) {
      if (cards.size() == 3) {
        threeOfKinds = new ArrayList<>(cards);
      }
    }

    if (threeOfKinds != null) {
      pokerHands.add(new ActualPokerHand(PokerHandEnum.THREE_OF_A_KIND, threeOfKinds));
    }
  }
  
  private void checkForFourOfAKind(List<Card> hand, List<Card> communityCards, List<ActualPokerHand> pokerHands) {
    Map<Suit, List<Card>> suitMap = new HashMap<>();
    List<Card> combinedList = Stream.of(hand, communityCards).flatMap(x -> x.stream()).collect(Collectors.toList());
    Collections.sort(combinedList);
    setSuitMap(suitMap, combinedList);

    List<Card> fourOfKinds = null;
    for (List<Card> cards : suitMap.values()) {
      if (cards.size() == 4) {
        fourOfKinds = new ArrayList<>(cards);
      }
    }

    if (fourOfKinds != null) {
      pokerHands.add(new ActualPokerHand(PokerHandEnum.THREE_OF_A_KIND, fourOfKinds));
    }
  }

  void deal ()
  {
    for (Player player : players)
    {
      List<Card> playerHand = new ArrayList<Card>();
      playerHand.add(deck.getCards().remove(0));
      playerHand.add(deck.getCards().remove(0));
      
      player.setHand(playerHand);
    }
    
    for (int i=0; i<5; i++)
    {
      communityCards.add(deck.getCards().remove(0));
    }
  }

  public void setDeck(Deck deck)
  {
    this.deck = deck;
  }

  public void setPlayers(List<Player> players)
  {
    this.players = players;
  }

  public void setCommunityCards(List<Card> communityCards)
  {
    this.communityCards = communityCards;
  }

  private void setSuitMap(Map<Suit, List<Card>> suitMap, List<Card> cards) {
    for (int i = 0; i < cards.size(); i++) {
      Card card = cards.get(i);
      Suit suit = card.getSuit();

      if (!suitMap.containsKey(suit)) {
        List<Card> listofCards = new ArrayList<>();
        listofCards.add(card);
        suitMap.put(suit, listofCards);
      } else {
        suitMap.get(suit).add(card);
      }
    }
  }

  private void setNumMap(Map<Integer, List<Card>> map, List<Card> cards) {
    for (int i = 0; i < cards.size(); i++) {
      Card card = cards.get(i);
      Integer val = card.getValue().getSuitValue();

      if (!map.containsKey(val)) {
        List<Card> listofCards = new ArrayList<>();
        listofCards.add(card);
        map.put(val, listofCards);
      } else {
        map.get(val).add(card);
      }
    }
  }
}
