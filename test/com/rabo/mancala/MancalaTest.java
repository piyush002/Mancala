package com.rabo.mancala;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.rabo.mancala.domain.Player;

@RunWith(JUnit4.class)
public class MancalaTest {
	@Mock
	Scanner reader;
	@InjectMocks
	Mancala mancala;

	@Test
	public void addStoneToSelectedPlayerPitTest() {
		int selectedPitIndex = 3;
		int numberofStones = 4;
		Mancala mancala = new Mancala();
		mancala.swapPlayertoAddStone();
		Player player1 = new Player();
		Player player2 = new Player();
		int result = mancala.addStoneToSelectedPlayerPit(selectedPitIndex, numberofStones, player1, player2);
		assertEquals("No of stones left after adding to selected players pit", 0, result);
	}

	@Test
	public void addStoneToSelectedPlayerPitTest2() {
		int selectedPitIndex = 2;
		int numberofStones = 4;
		Mancala mancala = new Mancala();
		mancala.swapPlayertoAddStone();
		Player player1 = new Player();
		player1.setPit(new Integer[] { 4, 5, 3, 2, 4, 0 });
		Player player2 = new Player();
		int result = mancala.addStoneToSelectedPlayerPit(selectedPitIndex, numberofStones, player1, player2);
		assertEquals("No of stones left after adding to selected players pit", 0, result);
	}

	@Test
	public void addStoneToSelectedPlayerPitTest3() {
		int selectedPitIndex = 3;
		int numberofStones = 12;
		Mancala mancala = new Mancala();
		mancala.swapPlayertoAddStone();
		mancala.swapPlayertoAddStone();
		Player player1 = new Player();
		player1.setPit(new Integer[] { 4, 5, 3, 2, 4, 0 });
		Player player2 = new Player();
		player2.setPit(new Integer[] { 4, 0, 1, 1, 2, 1 });
		int result = mancala.addStoneToSelectedPlayerPit(selectedPitIndex, numberofStones, player1, player2);
		assertEquals("No of stones left after adding to selected players pit", 9, result);
	}

	@Test
	public void selectWinnerTestplayer1() {
		Mancala mancala = new Mancala();
		Player player1 = new Player();
		Player player2 = new Player();
		player1.setMancala(12);
		player2.setMancala(5);
		mancala.selectWinner(player1, player2);
		assertTrue("Player1 wins", player1.getMancala() > player2.getMancala());

	}

	@Test
	public void selectWinnerTestplayer2() {
		Mancala mancala = new Mancala();
		Player player1 = new Player();
		Player player2 = new Player();
		player1.setMancala(2);
		player2.setMancala(5);
		mancala.selectWinner(player1, player2);
		assertTrue("Player2 wins", player1.getMancala() < player2.getMancala());

	}

	@Test
	public void selectWinnerTestDraw() {
		Mancala mancala = new Mancala();
		Player player1 = new Player();
		Player player2 = new Player();
		player1.setMancala(5);
		player2.setMancala(5);
		mancala.selectWinner(player1, player2);
		assertTrue("Match Tied", player1.getMancala() == player2.getMancala());

	}

	@Test
	public void DisplayBoardTest() {
		Mancala mancala = new Mancala();
		Player player1 = new Player();
		Player player2 = new Player();
		player1.setMancala(3);
		player2.setMancala(5);
		player1.setPit(new Integer[] { 4, 5, 3, 2, 4, 0 });
		player2.setPit(new Integer[] { 9, 4, 2, 0, 7, 5 });
		mancala.displayBoard(player1.getPit(), player2.getPit(), player1.getMancala(), player2.getMancala());
	}

	@Test
	public void isGameValidTestPlayer1() {
		Mancala mancala = new Mancala();
		Player player1 = new Player();
		Player player2 = new Player();
		player1.setPit(new Integer[] { 0, 0, 1, 0, 0, 0 });
		boolean result=mancala.isGameValid(player1, player2);
		assertEquals("Game is still not over", true, result);

	}
	@Test
	public void isGameValidTestPlayer2() {
		Mancala mancala = new Mancala();
		Player player1 = new Player();
		Player player2 = new Player();
		player2.setPit(new Integer[] { 0, 0, 1, 0, 0, 0 });
		mancala.switchTurn();
		boolean result=mancala.isGameValid(player1, player2);
		assertEquals("Game is still not over", true, result);
		
	}
	@Test
	public void isGameValidTestplayer1False() {
		Mancala mancala = new Mancala();
		Player player1 = new Player();
		Player player2 = new Player();
		player1.setPit(new Integer[] { 0, 0, 0, 0, 0, 0 });
		boolean result=mancala.isGameValid(player1, player2);
		assertEquals("Player1 pits are empty", false, result);
		
	}
	@Test
	public void isGameValidTestplayer2False() {
		Mancala mancala = new Mancala();
		Player player1 = new Player();
		Player player2 = new Player();
		player2.setPit(new Integer[] { 0, 0, 0, 0, 0, 0 });
		mancala.switchTurn();
		boolean result=mancala.isGameValid(player1, player2);
		assertEquals("Player2 pits are empty", false, result);
		
	}		 
}
