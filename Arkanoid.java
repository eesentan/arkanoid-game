import java.awt.*;
import javax.imageio.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/*
 * 
 * 
 *    main class for Arkanoid Game
 *    
 *    Elif Naz ESENTAN 
 *    
 * 
 */


	public class Arkanoid extends JPanel implements Runnable{

		private static final long serialVersionUID = 1L;
		
		public static void main(String args[]){
			
			JFrame myFrame = new JFrame("CSE212 Arkanoid Game");    //creating main frame
			Arkanoid content = new Arkanoid();
			myFrame.setContentPane(content);   
			myFrame.setSize(1000,1000);
			myFrame.setLocation(500,0);
			myFrame.setVisible(true);
			myFrame.setResizable(false);
			myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
			try {
	            myFrame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("space.jpg")))));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			
			DefaultListModel<String> list = new DefaultListModel<>(); 
			
		
			list.addElement("New Game");  
		    //list.addElement("Options");  
		    list.addElement("High Scores");  
		    list.addElement("Help"); 
		    list.addElement("About"); 
		    list.addElement("Exit"); 
		    
			JList<String> myList = new JList<>(list);
			myList.setBounds(100, 100, 200, 200);
			myFrame.add(myList);
		
			myList.setLayout(new FlowLayout());
			JButton myButton = new JButton("OK");
			myFrame.add(myButton);
			myButton.setBounds(80, 80, 200, 100);
			myButton.setSize(60, 80);
			
			
			myButton.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent event) {
					int choice = myList.getSelectedIndex();
					switch(choice) {
					case 0:
						// new game window
						break;
						
					case 1:
						//high scores window
						JFrame scores = new JFrame("High Scores");
						scores.setVisible(true);
						scores.setResizable(false);
						scores.setBounds(200, 200, 100, 100);
						scores.setSize(1000, 1000);
						break;
					case 2:
						//help window
						JFrame help = new JFrame("Help");
						help.setVisible(true);
						help.setResizable(false);
						help.setBounds(200, 200, 100, 100);
						help.setSize(600,400);
						help.setLocation(600, 180);
						
						JPanel panel = (JPanel)help.getContentPane();
						GridLayout gl1 = new GridLayout(4,3); 
						panel.setLayout(new FlowLayout());
						
						panel.setVisible(true);
					
						JLabel label = new JLabel("First,click the screen to activate");
						JLabel label1 = new JLabel("Use left and right arrow keys to move the paddle");
						JLabel label2 = new JLabel("You have 3 balls.If you are out of balls game is over.");
						JLabel label3 = new JLabel("GOOD LUCK ! ENJOY !");
						
						label.setIcon(new ImageIcon("./src/keys.png"));
						label.setLocation(20, 20);
						
						panel.add(label);
						panel.add(label1);
						panel.add(label2);
						panel.add(label3);
						break;
					case 3:
						//about window
						JFrame about = new JFrame("About");
						about.setVisible(true);
						about.setResizable(false);
						about.setBounds(200, 200, 100, 100);
						about.setSize(600,400);
						about.setLocation(600, 180);
						
						JPanel myPanel = new JPanel();
						GridLayout gl2 = new GridLayout(6,1);   // 6 lines 1 column
						myPanel.setLayout(gl2);
						myPanel.setVisible(true);
						JLabel lb1 = new JLabel("Application Developer:");
						JLabel lb2 = new JLabel("Elif Naz Esentan");
						JLabel lb3 = new JLabel("Yeditepe University");
						JLabel lb4 = new JLabel("20170702063");
						JLabel lb5 = new JLabel("All Rights Reserved");
						JLabel lb6 = new JLabel("2019");
						myPanel.add(lb1);
						myPanel.add(lb2);
						myPanel.add(lb3);
						myPanel.add(lb4);
						myPanel.add(lb5);
						myPanel.add(lb6);
						about.add(myPanel);
						
						break;
					case 4:
						//exit window
						
						String YesOrNo[] = {"Yes","No"};
						int AreYouSure = JOptionPane.showOptionDialog(null, "Are you sure ?", "Exit?", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
								YesOrNo, YesOrNo[1]);
						if(AreYouSure == JOptionPane.YES_OPTION) {
							System.exit(0);
						}
						
						break;
				}
				}
			
			});

		}

		public Paddle paddle;
		public Ball ball;
		
		public static final int paddleHeight = 20;   //paddle height
		public static final int paddleWidth = 130;   //paddle width
		public static final int CIRCLE = 20;    //circle diameter
		public static final Color squareColor = Color.YELLOW;   // color of paddle
		 
		public Blocks blocks[];
		public int width, height;
		public Timer timer;    //timer to keep track of time
		boolean ifExists[] = new boolean[70];
		int xDirection;
		public Thread t1;
		public boolean winner;
		public int score = 0;


		public Arkanoid(){
			
			Thread t1 = new Thread(this);
			t1.start();          //thread for paddle move
			setBackground(Color.BLACK);

			ActionListener action =  new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					if(paddle != null){
						paddle.updateNewFrame();
						ball.updateNewFrame();
						if(ball.ballX <= 850 && ball.ballX >= 150 && ball.ballY <= 375 && ball.ballY >= 80){
							for(int i = 0;i < 70;i++){
								if(ifExists[i]){
									blocks[i].updateNewFrame();
								}
							}
						}
					}
				repaint();
				}
			};

			timer = new Timer(30, action);   //call every 30 milliseconds

			addMouseListener(new MouseAdapter() {
				
				public void mousePressed(MouseEvent evt) {
	        			requestFocus();
	        		}
			});    //listen to mouse clicks
			
	
			
			addFocusListener(new FocusListener() {
				
				public void focusGained(FocusEvent evt) {
					timer.start();
					repaint();
				}

				public void focusLost(FocusEvent evt) {
					timer.stop();
					repaint();
				}
			});

			addKeyListener(new KeyAdapter(){

				public void keyPressed(KeyEvent evt){
					int key = evt.getKeyCode();

					if(key == KeyEvent.VK_LEFT){    //if left key is pressed
						setXDirection(-2);
					}
					else if(key == KeyEvent.VK_RIGHT){     //if right key is pressed paddle moves 2 pixels every 5 miliseconds
						setXDirection(2);
					}
					else if(key == KeyEvent.VK_SPACE && ball.balls > 0 && winner == false){//space paddle, ball moves if game isn't won and there are still balls 
						if(!ball.isMoving){
							ball.isMoving = true;
							ball.northWest = true;    //ball moves northwest
						}
					}
				}
			
				public void keyReleased(KeyEvent evt){     // paddle stops moving
					int key = evt.getKeyCode();
					if(key == KeyEvent.VK_LEFT){
						setXDirection(0);
					}
					if(key == KeyEvent.VK_RIGHT){
						setXDirection(0);
					}
					
				}
			});//listen for keyboard
			
			for(int k = 0;k < 70;k++){
				ifExists[k] = true;
			}
		} 
			
		public void run(){      //run method for thread
			try{
				while(true){
					move();
					Thread.sleep(5);
				}
			}
			catch(Exception e){
				System.out.print("Error");
			}
		}
		
		
		public void setXDirection(int xdir){
			xDirection = xdir;
		}
		
		public void move(){    //   runs every 5 milliseconds
			if(paddle!=null){    //   if  paddle is not null
			paddle.squareLeft+=xDirection;
			}
		}

		public void paintComponent(Graphics g){
			super.paintComponent(g);     //    paintComponent of superclass
			Graphics2D g2 = (Graphics2D)g;
	        	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			if(paddle == null){
				width = getWidth();
				height = getHeight();
				paddle = new Paddle(this);
				ball = new Ball(paddle,this);
				blocks= new Blocks[70];
				for(int i = 0;i < 70;i++){
						blocks[i] = new Blocks(ball, i, this);    //instantiating each block
				}
			}
			

			if(hasFocus()){
				g.drawString("Use arrow keys to move the paddle",7,20);
				g.setColor(Color.YELLOW);
				
			}
			else{
				g.drawString("Click to activate",7,20);
				g.setColor(Color.YELLOW);
			}
			g.drawString("Life remaning: "+ball.balls,7,60);
			g.setColor(Color.YELLOW);

			if(ball.balls == 0){
				for(int i = 0;i < 70;i++){
					ifExists[i] = false;
				}
				g.setColor(Color.BLUE);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
				g.drawString("Game Over", 100, 200);
				g.drawString("Press Exit to exit game",150, 250);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
				g.drawString("Your score is "+score,200,400);
			}
			if(winner){
				ball.isMoving=false;
				g.setColor(Color.BLUE);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 150));
				g.drawString("Winner!", 100, 200);
			}

			g.drawRect(0,0,width-1,height-1);
			g.drawRect(1,1,width-3,height-3);
			g.drawRect(2,2,width-5,height-5);

			paddle.draw(g);
			ball.draw(g);
			for(int i = 0;i < 70; i++){
				if(ifExists[i]){
					blocks[i].draw(g, i);    //  if it exists draw it
				}
			}
		}
		
		public void crashSound() {   //   set sound when ball crashes the blocks
			try {
				File crashSound = new File("./src/pop.wav");
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(crashSound));
				clip.start();
			}catch(Exception e) {
				
			}
			
		}
	}
