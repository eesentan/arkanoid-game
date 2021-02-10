import java.awt.*;
import javax.swing.*;
/*
 * 
 *    Paddle class creates and draws paddle(bottom bar of the screen)
 * 
 *    Elif Naz ESENTAN 
 *    
 *   
 * 
 */
public class Paddle {

	  public Ball ball;
	  private final Arkanoid arkanoid;

	  boolean left = false;
	  boolean right = false;
	  int squareLeft;
	  int squareTop;
	 

	  public Paddle(Arkanoid breakinst){
	    this.arkanoid = breakinst;
	    squareLeft = 120;
	    squareTop = 620;
	  }

	  void updateNewFrame() { 
	    if(squareLeft > arkanoid.width-4-arkanoid.paddleWidth){   //  if paddle is off screen 
	      squareLeft = (arkanoid.width - 4 - arkanoid.paddleWidth);}   // put it back on screen
	    else if(squareLeft < 3){
	      squareLeft = 3;
	    }
	  }
	  void draw(Graphics g){      //draws paddle
	    g.setColor(arkanoid.squareColor);
	    g.fillRoundRect(squareLeft, squareTop, arkanoid.paddleWidth, arkanoid.paddleHeight,20,20);
	  }
}
