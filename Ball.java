import java.awt.*;
/*
 * 
 *    Ball class creates and draws ball
 *    
 *    Elif Naz ESENTAN 
 * 
 * 
 */
	public class Ball {
		
		private Paddle paddle;
		private Arkanoid arkanoid;
	    public Color squareColor;
		int ballX,ballY;
		boolean isMoving;
		boolean northEast;
		boolean northWest;
		boolean southEast;
		boolean southWest;
		int balls = 3;   //  3 ball lives
		
		
		public Ball(Paddle paddle, Arkanoid arkanoid){
			this.paddle = paddle;
			this.arkanoid = arkanoid;
			isMoving = false;
		}
		
		public void updateNewFrame() { 
			//checks if ball hits wall 
	        	if(isMoving){
	            		if(ballX-10 < 0){
	            			if(northWest){
	            				northWest = false;
	            				northEast = true;
	            			}
	            			else if(southWest){
	            				southWest = false;
	            				southEast = true;
	            			}
	            		}
	        	 	if(ballX+30 > arkanoid.width){
	        	    		if(northEast){
	            				northEast = false;
	            				northWest = true;
	            			}
	            			else if(southEast){
	            				southEast = false;
	            				southWest = true;		
	            			}
	            		}
	            		if(ballY-10 < 0){
	            			if(northEast){
	            				northEast = false;
	            				southEast = true;	
	            			}
	            			else if(northWest){
	            				northWest = false;
	            				southWest = true;	
	            			}
	            		}
	            		if(ballY+10 > arkanoid.height){
	            			balls -=1;
	            			isMoving = false;
	            			southEast = false;
	            			southWest = false;
	            		}
	            		
	            		//  checks if ball hits paddle
	            		if(ballY+10 > 620-arkanoid.paddleHeight && ballY+10 < 620 && ballX < paddle.squareLeft+(arkanoid.paddleWidth/3) && ballX > paddle.squareLeft){
	            			if(southEast){
	            				southEast = false;
	            				northWest = true;			
	            			}
	            			else if(southWest){
	            				southWest = false;
	            				northWest = true;			
	            			}
	            		}
	            		if(ballY+10 > 620-arkanoid.paddleHeight && ballY+10 < 620 && ballX < paddle.squareLeft+arkanoid.paddleWidth && ballX > paddle.squareLeft+(2*arkanoid.paddleWidth/3)){
	            			if(southWest){
	            				southWest = false;
	            				northEast = true;			
	            			}
	            			else if(southEast){
	            				southEast = false;
	            				northEast = true;			
	            			}
	            		}
	            		if(ballY+10 > 620-arkanoid.paddleHeight && ballY+10 < 620 && ballX < (paddle.squareLeft+arkanoid.paddleWidth) && ballX > paddle.squareLeft){
	            			if(southEast){
	            				northEast = true;
	            				southEast = false;			
	            			}
	            			else if(southWest){
	            				northWest = true;
	            				southWest = false;	
	            			}
	            		}
	            		if(northEast){
	            			ballY-=10;
	            			ballX+=10;
	            		}
	            		if(northWest){
	            			ballY-=10;
	            			ballX-=10;
	            		}
	            		if(southEast){
	            			ballY+=10;
	            			ballX+=10;
	            		}
	            		if(southWest){
	            			ballY+=10;
	            			ballX-=10;
	            		}
		 	}
	    	}
	    	
		public void draw(Graphics g){
			if(!isMoving){
				ballY = paddle.squareTop-20;
				ballX = paddle.squareLeft+60;
			}
			g.setColor(Color.BLUE);
			g.fillOval(ballX, ballY, arkanoid.CIRCLE, arkanoid.CIRCLE);
		}
	}


