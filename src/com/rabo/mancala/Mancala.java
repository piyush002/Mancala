package com.rabo.mancala;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.rabo.mancala.domain.Player;

public class Mancala {
  private int currentPlayer = 1;
  private int playertoAddStones;
  Scanner reader = null;

  public static void main(String[] args) {

    Mancala mancala = new Mancala();
    mancala.reader = new Scanner(System.in);
    System.out.println("Welcome to mancala game\n\n");
    Player player1 = new Player();
    Player player2 = new Player();
    mancala.play(player1, player2);
  }


  /**
   * Method to display Mancala board
   * 
   * @param player1Pit the player1 pit
   * @param player2Pit the player2 pit
   * @param player1mancala the player1 mancala
   * @param player2mancala the player2 mancala
   */
  public void displayBoard(Integer[] player1Pit, Integer[] player2Pit, int player1mancala, int player2mancala) {
    System.out.println("--------------------------------------------------------\n");
    System.out.print("\t");
    for (int i = player2Pit.length - 1; i >= 0; i--) {
      System.out.print(player2Pit[i] + " ");
    }
    System.out.println("\n" + player2mancala + "\t\t\t" + player1mancala);
    System.out.print("\t");
    for (int i = 0; i < player1Pit.length; i++) {
      System.out.print(player1Pit[i] + " ");
    }
    System.out.println("\n--------------------------------------------------------");
  }


  /**
   * Method to add stones to Selected player's pit
   * 
   * @param startIndex the start index
   * @param numberofStones the number of stones
   * @param selectedPlayer the player's pit to add stone
   * @param otherPlayer the opponent player
   * @return the remaining stones after adding
   */
  public int addStoneToSelectedPlayerPit(int startIndex, int numberofStones, Player selectedPlayer,
      Player otherPlayer) {
    while (numberofStones > 0 && startIndex < 6) {
      selectedPlayer.getPit()[startIndex]++;
      numberofStones--;
      startIndex++;
    }
    if (numberofStones == 0) {
      if (selectedPlayer.getPit()[startIndex - 1] == 1 && currentPlayer == playertoAddStones) {
        selectedPlayer.setMancala(selectedPlayer.getMancala() + selectedPlayer.getPit()[startIndex - 1]
            + otherPlayer.getPit()[6 - startIndex]);
        otherPlayer.getPit()[6 - startIndex] = 0;
        selectedPlayer.getPit()[startIndex - 1] = 0;
      }
      switchTurn();
      swapPlayertoAddStone();
      return 0;
    } else {
      if (currentPlayer == playertoAddStones) {
        selectedPlayer.setMancala(selectedPlayer.getMancala() + 1);
        numberofStones--;
      }
      swapPlayertoAddStone();
      return numberofStones;
    }
  }

  /**
   * Method to swap current player
   * 
   */
  public void switchTurn() {
    if (currentPlayer == 1)
      currentPlayer = 2;
    else
      currentPlayer = 1;
  }

  /**
   * Select the player's pit to add stone
   * 
   * @return void
   */
  public void swapPlayertoAddStone() {
    if (playertoAddStones == 1)
      playertoAddStones = 2;
    else
      playertoAddStones = 1;
  }

  /**
   * To check if current player has any pit with stones or not
   * 
   * @param player1 the player1
   * @param player2 the player2
   * @return the boolean value if there are stones in current player's pit
   */
  public boolean isGameValid(Player player1, Player player2) {
    if (currentPlayer == 1) {
      for (int noofMarble : player1.getPit()) {
        if (noofMarble > 0) {
          return true;
        }
      }
    } else {
      for (int noofMarble : player2.getPit()) {
        if (noofMarble > 0) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Method to add stones to player
   * 
   * @param index the index to add stones
   * @param numberOfMarbles the number of marbles
   * @param player1 the player1
   * @param player2 the player2
   */
  private void addStoneToPlayer(int index, int numberOfMarbles, Player player1, Player player2) {
    if (numberOfMarbles > 0) {
      if (playertoAddStones == 1) {
        addStoneToPlayer(0, addStoneToSelectedPlayerPit(index, numberOfMarbles, player1, player2), player1, player2);
      } else {
        addStoneToPlayer(0, addStoneToSelectedPlayerPit(index, numberOfMarbles, player2, player1), player1, player2);
      }
    }
  }

  /**
   * Select the pit to add stone
   * 
   * @return the pit number
   */
  public int selectPit() {
    while (true) {
      System.out.println("player " + currentPlayer + " select pit (1-6)");
      playertoAddStones = currentPlayer;
      int selectedPit = 0;
      try {
        selectedPit = reader.nextInt();
        if (selectedPit < 1 || selectedPit > 6) {
          System.out.println("Wrong Input. Please enter again");
          continue;
        } else {
          return selectedPit;
        }
      } catch (InputMismatchException e) {
        reader.nextLine();
        System.out.println("Wrong Input. Please enter again");
        continue;
      }
    }
  }


  /**
   * method to play Mancala
   * 
   * @param player1 the player1
   * @param player2 the player2
   */
  public void play(Player player1, Player player2) {
    while (isGameValid(player1, player2)) {
      displayBoard(player1.getPit(), player2.getPit(), player1.getMancala(), player2.getMancala());
      int selectedPit = selectPit();
      int numberOfMarbels;
      if (currentPlayer == 1) {

        numberOfMarbels = player1.getPit()[selectedPit - 1];
        player1.getPit()[selectedPit - 1] = 0;

      } else {
        numberOfMarbels = player2.getPit()[selectedPit - 1];
        player2.getPit()[selectedPit - 1] = 0;
      }
      if (numberOfMarbels > 0) {
        addStoneToPlayer(selectedPit, numberOfMarbels, player1, player2);
      }
    }
    reader.close();
    selectWinner(player1, player2);
  }

  /**
   * Display the winner
   * 
   * @param player1 the player1
   * @param player2 the player2
   */
  public void selectWinner(Player player1, Player player2) {
    if (player1.getMancala() > player2.getMancala()) {
      System.out.println("Player 1 wins");
    } else if (player1.getMancala() < player2.getMancala()) {
      System.out.println("Player 2 wins");
    } else {
      System.out.println("Match Tied!");
    }
  }
}
