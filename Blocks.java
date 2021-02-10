import java.awt.*;
/*
 * 
 *   Blocks class creates and draws blocks 
 * 
 *   Elif Naz ESENTAN 
 *   
 *   
 */

	public class Blocks{
			private Ball ball;
			private Arkanoid arkanoid;
			
			int width;    //   Width of blocks
			int height;   //   Height of blocks
			int yposition;  //   pixels from the top of the screen
			int xposition;  //  pixels from the left side of the screen
			int bottom;
			int top;
			int left;
			int right;
			int centerX;
			int centerY;     //Center of Ball
			int i;
			public int levelCount =0;
			public int level = 1;
			boolean noTiles;
			
			Blocks(Ball ball,int i, Arkanoid arkanoid){     
				this.i = i;
				this.arkanoid = arkanoid;
				this.ball = ball;
				width = 70;
				height = 30;
				
				int y = 0;
				int x = i;
				if(i > 9){
					int b =	i/10;
					y = b;
					x = i-(b*10);
				}
				xposition = (180+(x*width));     //pixels to the left side of the screen
				yposition = (100+(y*height));
				left = 180;
				right = left + 10*width;
				top = 100;
				bottom = top + height;
			}
			void updateNewFrame(){
				
				int leftX= ball.ballX;
				int rightX = ball.ballX+20;
				int topY= ball.ballY;
				int bottomY= ball.ballY+20;
				if(ball.northEast){
					if(topY > yposition && topY <= (yposition+height) && rightX < (xposition+width) && rightX >= xposition){    //if ball hits block
						if(topY == yposition+height){     //if top of ball is equal to y plane of block
							ball.northEast = false;
							ball.southEast = true;   //changes direction
							arkanoid.ifExists[i] = false;   //destroys block
							arkanoid.crashSound();
							arkanoid.score+=10;   //adds 10 to score
							noTiles = true;
							levelCount++;
							for(int i = 0;i < 70;i++){
								if(arkanoid.ifExists[i] == true){
									noTiles = false;
								}
							}//check if no blocks are remaining
							if(noTiles){
								arkanoid.winner=true;   //  if no blocks are remaining you are the winner
							}
						}
						else{
							ball.northEast = false;
							ball.northWest = true;
							arkanoid.ifExists[i] = false;
							arkanoid.score+=10;
							noTiles = true;
							levelCount++;
							for(int i = 0;i < 70;i++){
								if(arkanoid.ifExists[i] == true){
									noTiles = false;
								}
							}
							if(noTiles){
								arkanoid.winner=true;
							}
						}
					}
				}
				else if(ball.northWest){
					if(topY > yposition && topY <= (yposition+height) && leftX <= (xposition+width) && leftX > xposition){
						if(topY == yposition+height){
							ball.northWest = false;
							ball.southWest = true;
							arkanoid.ifExists[i] = false;
							arkanoid.crashSound();
							arkanoid.score+=10;
							noTiles = true;
							levelCount++;
							for(int i = 0;i < 70;i++){
								if(arkanoid.ifExists[i] == true){
									noTiles = false;
								}
							}
							if(noTiles){
								arkanoid.winner=true;
							}
						}
						else{
							ball.northWest = false;
							ball.northEast = true;
							arkanoid.ifExists[i] = false;
							arkanoid.crashSound();
							arkanoid.score+=10;
							noTiles = true;
							levelCount++;
							for(int i = 0;i < 70;i++){
								if(arkanoid.ifExists[i] == true){
									noTiles = false;
								}
							}
							if(noTiles){
								arkanoid.winner=true;
							}
						}
					}
				}
				else if(ball.southEast){
					if(bottomY >= yposition && bottomY < (yposition+height) && rightX < (xposition+width) && rightX >= xposition){
						if(bottomY == yposition){
							ball.southEast = false;
							ball.northEast = true;
							arkanoid.ifExists[i] = false;
							arkanoid.crashSound();
							arkanoid.score+=10;
							noTiles = true;
							levelCount++;
							for(int i = 0;i < 70;i++){
								if(arkanoid.ifExists[i] == true){
									noTiles = false;
								}
							}
							if(noTiles){
								arkanoid.winner=true;
							}
						}
						else{
							ball.southEast = false;
							ball.southWest = true;
							arkanoid.ifExists[i] = false;
							arkanoid.crashSound();
							arkanoid.score+=10;
							noTiles = true;
							levelCount++;
							for(int i = 0;i < 70;i++){
								if(arkanoid.ifExists[i] == true){
									noTiles = false;
								}
							}
							if(noTiles){
								arkanoid.winner=true;
							}
						}
					}
				}
				else if(ball.southWest){
					if(bottomY >= yposition && bottomY < (yposition+height) && leftX <= (xposition+width) && leftX > xposition){
						if(bottomY == yposition){
							ball.southWest = false;
							ball.northWest = true;
							arkanoid.ifExists[i] = false;
							arkanoid.crashSound();
							arkanoid.score+=10;
							noTiles = true;
							levelCount++;
							for(int i = 0;i < 70;i++){
								if(arkanoid.ifExists[i] == true){
									noTiles = false;
								}
							}
							if(noTiles){
								arkanoid.winner=true;
							}
						}
						else{
							ball.southWest = false;
							ball.southEast = true;
							arkanoid.ifExists[i] = false;
							arkanoid.crashSound();
							arkanoid.score+=10;
							noTiles = true;
							levelCount++;
							for(int i = 0;i < 70;i++){
								if(arkanoid.ifExists[i] == true){
									noTiles = false;
								}
							}
							if(noTiles){
								arkanoid.winner=true;
							}
						}
						if(levelCount >6) {
							level = levelCount/6;
						}
					}
				}
			}
			void draw(Graphics g, int i){
				int y = 0;
				int x = i;
				if(i > 9){
					int b =	i/10;
					y = b;
					x = i-(b*10);
					}
				if(i< 70){
					g.setColor(Color.YELLOW);
				}
				
				g.fillRect(180+(x*width), 100+(y*height), width, height);
				g.setColor(Color.BLACK);
				g.drawRect(180+(x*width), 100+(y*height), width, height); 
			}
			
		}


