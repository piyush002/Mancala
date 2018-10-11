package com.rabo.mancala.domain;

/**
 * Domain Class containing players details
 * 
 * @author Piyush Mittal
 * @created Oct 9, 2018
 *
 *
 */
public class Player {

  private Integer[] pit;
  private int mancala;



  public Player() {
    super();
    pit = new Integer[] {4, 4, 4, 4, 4, 4};
    mancala = 0;
  }

  /**
   * Gets the pit
   *
   * @return the pit
   */
  public Integer[] getPit() {
    return pit;
  }

  /**
   * @param pit the pit to set
   */
  public void setPit(Integer[] pit) {
    this.pit = pit;
  }

  /**
   * Gets the mancala
   *
   * @return the mancala
   */
  public int getMancala() {
    return mancala;
  }

  /**
   * @param mancala the mancala to set
   */
  public void setMancala(int mancala) {
    this.mancala = mancala;
  }


}
