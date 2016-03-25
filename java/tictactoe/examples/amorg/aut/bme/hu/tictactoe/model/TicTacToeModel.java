package tictactoe.examples.amorg.aut.bme.hu.tictactoe.model;

import android.view.View;

import tictactoe.examples.amorg.aut.bme.hu.tictactoe.GameActivity;

public class TicTacToeModel {
 
  private static TicTacToeModel instance = null;

  //singleton tervezés
  private TicTacToeModel () {
  }

  public static TicTacToeModel getInstance() {
    if (instance == null) {
      instance = new TicTacToeModel();
    }
    return instance;
  }

  public static final short EMPTY = 0;
  public static final short CIRCLE = 1;
  public static final short CROSS = 2;
  public static final short NOTSET = 3;
  public static final short DRAW = 4;


  private short[][] model = {
    { EMPTY, EMPTY, EMPTY },
    { EMPTY, EMPTY, EMPTY },
    { EMPTY, EMPTY, EMPTY }
  };
  private short nextPlayer = CIRCLE;
 
  public void resetModel() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        model[i][j] = EMPTY;
      }
    }
    nextPlayer = CIRCLE;
  }
 
  public short getFieldContent(int x, int y) {
    return model[x][y];
  }
 
  public short setFieldContent(int x, int y, short content) {
    return model[x][y] = content;
  }
 
  public short getNextPlayer() {
    return nextPlayer;
  }
 
  public void changeNextPlayer() {
    nextPlayer = (nextPlayer == CIRCLE) ? CROSS : CIRCLE;
  }

}