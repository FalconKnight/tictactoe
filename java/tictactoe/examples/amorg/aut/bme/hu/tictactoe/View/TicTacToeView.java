package tictactoe.examples.amorg.aut.bme.hu.tictactoe.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import tictactoe.examples.amorg.aut.bme.hu.tictactoe.GameActivity;
import tictactoe.examples.amorg.aut.bme.hu.tictactoe.model.TicTacToeModel;

public class TicTacToeView extends View {
 
  Paint paintBg;
  Paint paintLine;
    int movecounter=0;
  public TicTacToeView(Context context, AttributeSet attrs) {
    super(context, attrs);
 
    paintBg = new Paint();
    paintBg.setColor(Color.BLACK);
    paintBg.setStyle(Paint.Style.FILL);
 
    paintLine = new Paint();
    paintLine.setColor(Color.WHITE);
    paintLine.setStyle(Paint.Style.STROKE);
    paintLine.setStrokeWidth(5);
  }
 
  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
 
    canvas.drawRect(0, 0, getWidth(), getHeight(), paintBg);
 
    drawGameArea(canvas);
 
    drawPlayers(canvas);
  }

  private void drawGameArea(Canvas canvas) {
    // border
    canvas.drawRect(0, 0, getWidth(), getHeight(), paintLine);
    // two horizontal lines
    canvas.drawLine(0, getHeight() / 3, getWidth(), getHeight() / 3,
            paintLine);
    canvas.drawLine(0, 2 * getHeight() / 3, getWidth(),
            2 * getHeight() / 3, paintLine);

    // two vertical lines
    canvas.drawLine(getWidth() / 3, 0, getWidth() / 3, getHeight(),
            paintLine);
    canvas.drawLine(2 * getWidth() / 3, 0, 2 * getWidth() / 3, getHeight(),
            paintLine);
  }
  private void drawPlayers(Canvas canvas) {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (TicTacToeModel.getInstance().getFieldContent(i,j) == TicTacToeModel.CIRCLE) {

          // draw a circle at the center of the field

          // X coordinate: left side of the square + half width of the square
          float centerX = i * getWidth() / 3 + getWidth() / 6;
          float centerY = j * getHeight() / 3 + getHeight() / 6;
          int radius = getHeight() / 6 - 2;

          canvas.drawCircle(centerX, centerY, radius, paintLine);

        } else if (TicTacToeModel.getInstance().getFieldContent(i,j) == TicTacToeModel.CROSS) {
          canvas.drawLine(i * getWidth() / 3, j * getHeight() / 3,
                  (i + 1) * getWidth() / 3,
                  (j + 1) * getHeight() / 3, paintLine);

          canvas.drawLine((i + 1) * getWidth() / 3, j * getHeight() / 3,
                  i * getWidth() / 3, (j + 1) * getHeight() / 3, paintLine);
        }
      }
    }
  }
  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int w = MeasureSpec.getSize(widthMeasureSpec);
    int h = MeasureSpec.getSize(heightMeasureSpec);
    int d = w == 0 ? h : h == 0 ? w : w < h ? w : h;
    setMeasuredDimension(d, d);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {

    if (event.getAction() == MotionEvent.ACTION_DOWN) {

      int tX = ((int) event.getX()) / (getWidth() / 3);
      int tY = ((int) event.getY()) / (getHeight() / 3);

      if (tX < 3 && tY < 3 && TicTacToeModel.getInstance().getFieldContent(tX,tY) == TicTacToeModel.EMPTY) {
        TicTacToeModel.getInstance().setFieldContent(tX,tY,TicTacToeModel.getInstance().getNextPlayer());
        TicTacToeModel.getInstance().changeNextPlayer();
        invalidate();
      }

    }
      movecounter++;
    verifier();
    return super.onTouchEvent(event);
  }

  public void  verifier()
  {
    short winner=TicTacToeModel.NOTSET;

      if(movecounter==9)winner=TicTacToeModel.DRAW;
  if(winner==TicTacToeModel.NOTSET) {
      for (int i = 0; i < 3; i++) {
          boolean match = true;
          short current = TicTacToeModel.NOTSET;
          for (int j = 0; j < 3; j++) {
              short temp = TicTacToeModel.getInstance().getFieldContent(i, j);
              if (current == TicTacToeModel.NOTSET) current = temp;
              else if (current != temp || temp == TicTacToeModel.EMPTY) match = false;
          }
          if (match) winner = current;
      }
  }
    if(winner==TicTacToeModel.NOTSET) {
      for (int j = 0; j < 3; j++) {
        boolean match = true;
        short current = TicTacToeModel.NOTSET;
        for (int i = 0; i < 3; i++) {
          short temp = TicTacToeModel.getInstance().getFieldContent(i, j);
          if (current == TicTacToeModel.NOTSET) current = temp;
          else if (current != temp || temp == TicTacToeModel.EMPTY) match = false;
        }
        if (match) winner = current;
      }
    }
      if(winner==TicTacToeModel.NOTSET) {
          short temp1=TicTacToeModel.getInstance().getFieldContent(0,0);
          short temp2=TicTacToeModel.getInstance().getFieldContent(1,1);
          short temp3=TicTacToeModel.getInstance().getFieldContent(2,2);
          if(temp1==temp2 && temp2==temp3)winner=temp1;
      }
      if(winner==TicTacToeModel.NOTSET) {
          short temp1=TicTacToeModel.getInstance().getFieldContent(2,0);
          short temp2=TicTacToeModel.getInstance().getFieldContent(1,1);
          short temp3=TicTacToeModel.getInstance().getFieldContent(0,2);
          if(temp1==temp2 && temp2==temp3)winner=temp1;
      }


      GameActivity gameActivity = (GameActivity) this.getContext();

    String msg=null;
    switch(winner) {
      case TicTacToeModel.CIRCLE:
        msg = "Winner is CIRCLE!";
        break;
      case TicTacToeModel.CROSS:
        msg = "Winner is CROSS!";
        break;
      case TicTacToeModel.DRAW: msg="DRAW!";
        break;
    }
    if(msg!=null)gameActivity.endGame(msg);
  }
}
