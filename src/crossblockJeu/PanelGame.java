package crossblockJeu;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import crossblock.FrameMenu;

@SuppressWarnings("serial")
public class PanelGame extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	
	//Attributes
	private Font fontLevel = new Font("SansSerif", Font.BOLD, 30);
	private Frame frameGame;
	private GameBoard board;
	private Image Block;
	private int[] pointA = new int[2];
	private int[] pointB = new int[2];
	private int[] mouse = new int[2];
	private Boolean mouseClick = false;
	private String address;

	//PanelGame constructor
	public PanelGame(Frame frameGame){		
		this.frameGame = frameGame;
		address = (String) JOptionPane.showInputDialog(null,"Please enter the path of the XML file to load\nType in Game to load the default levels", "Load",JOptionPane.QUESTION_MESSAGE,null,null,"Game");
		board = new GameBoard(address);
		ImageIcon iconeBloc = new ImageIcon("Images/Block.jpg");
		Block = iconeBloc.getImage();
		setFocusable(true);
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
	}
	
	//Useful getters and setters
	
	//MMethods for graphic display
	public void paintComponent(Graphics g){
	    try {
	      Image background = ImageIO.read(new File("Images/Background.jpg"));
	      g.drawImage(background, 0, 0, this);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }               
	  }

	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		int[][] array = board.getArray();
		for (int i=0; i<20;i++){
			for (int j=0; j<11;j++){
				if(array[i][j]==1){
					g2d.drawImage(Block, i*34, j*34,null);
				}
			}
		}
		g2d.getStroke();
		g2d.setStroke(new BasicStroke(5));
		if (mouseClick == true){
			g2d.drawLine(pointA[0], pointA[1], mouse[0], mouse[1]);
		}
		g.setFont(fontLevel);
		g.drawString("Level : "+board.getLevel().getNumLevel(), 10, 25);
		g.drawString("Block by line : "+board.getLevel().getNumberBlocks(), 10, 50);
		if (board.getLevel().getNumLevel()==0){
			//If the number of the level is 0, we can say that the path is not valid
			FrameMenu frame = new FrameMenu();
			frameGame.dispose();
			JOptionPane.showMessageDialog(frame, "The XML file to load was not found");
		} else if (address==null){
			//If the selected path is void, the user either clicked on Cancel or pressed the cross button
			FrameMenu frame = new FrameMenu();
			frameGame.dispose();
			JOptionPane.showMessageDialog(frame, "Levels Loading cancelled");
		}
	}

	//Auto-generated methods from MouseListener
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//Allows to store the first point of the line drew by the player
		pointA[0] = arg0.getX();
		pointA[1] = arg0.getY();
		//Indicates if the player already pressed the mouse left button
		mouseClick = true;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//Allows to launch the line construction by adding the coordinates of the second point
		pointB[0]=arg0.getX();
		pointB[1]=arg0.getY();
		board.blocksDestruction(pointA,pointB);
		mouseClick = false;
		//If the board is empty and not re-filled, we can say that the game is over
		if (board.getLevel().getRemainingBlocks()==null){
			new FrameMenu();
			frameGame.dispose();
		}
		repaint();
	}

	//Auto-generated methods from MouseMotionListener
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//Allows to print the line drew by the player
		mouse[0] = arg0.getX();
		mouse[1] = arg0.getY();
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	
	//Auto-generated methods from KeyListener
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//Allows the player to interact with the program
		int touche = arg0.getKeyCode();
		if (touche == KeyEvent.VK_ESCAPE){
			//Back to main menu
			new FrameMenu();
			frameGame.dispose();
		} else if (touche == KeyEvent.VK_R){
			//Reloads the level if the player is stuck at the current level
			board.reloadLevel();
			repaint();
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

}
