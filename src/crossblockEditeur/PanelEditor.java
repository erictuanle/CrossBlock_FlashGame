package crossblockEditeur;

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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.io.File;
import java.io.IOException;

import crossblock.FrameMenu;

@SuppressWarnings("serial")
public class PanelEditor extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	
	//Attributes
	private int[][] Editor = new int[20][11];
	private int mouseX, mouseY;
	private String nbBlocksToDestroy;
	private Image Block;
	private Frame frameEditor;
	private String savingAddress=null;
	private int level = 1 ;
	private Font fontLevel = new Font("SansSerif", Font.BOLD, 30);

	//PanelEditeur constructor
	public PanelEditor(Frame FrameEditor){
		this.frameEditor = FrameEditor;
		ImageIcon blockIcon = new ImageIcon("Images/Block.jpg");
		Block = blockIcon.getImage();
		setFocusable(true);
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
	}
	
	//UsefulGetters and Setters
	public int[][] getEditor(){
		return Editor;
	}
	
	public void setEditor(int[][] Editor){
		this.Editor=Editor;
	}
	
	public String getNbBlocksToDestroy(){
		return nbBlocksToDestroy;
	}
	
	public void setNbBlocksToDestroy(String nbBlocksToDestroy){
		this.nbBlocksToDestroy=nbBlocksToDestroy;
	}
	
	public String getSavingAddress(){
		return savingAddress;
	}
	
	public void setSavingAddress (String savingAddress){
		this.savingAddress=savingAddress;
	}
	
	public int getLevel(){
		return this.level;
	}
	
	public void setLevel(int level){
		this.level=level;
	}
	
	//Methods for graphic display
	public void paintComponent(Graphics g){
		try {
			Image background = ImageIO.read(new File("Images/Background.jpg"));
			g.drawImage(background, 0, 0, this);
		} catch (IOException e) {
			e.printStackTrace();
		}               
	}   

	public void paint (Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		for (int i=0;i<20;i++){
			for (int j=0;j<11;j++){
				if (Editor[i][j]==1){
					g2d.drawImage(Block, i*34, j*34, null);
				}
			}
		}
		g2d.drawImage(Block, mouseX, mouseY, null);
		g.setFont(fontLevel);
		g.drawString("Level : "+level, 10, 25);
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
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//Allows to fill in the Editor array by a simple mouse click
		int mouseRX = arg0.getX()/34;
		int mouseRY = arg0.getY()/34;
		if (arg0.getButton() == MouseEvent.BUTTON1){
			try{
				Editor[mouseRX][mouseRY] = 1;
			} catch (ArrayIndexOutOfBoundsException e){}
		} else if (arg0.getButton() == MouseEvent.BUTTON3){
			Editor[mouseRX][mouseRY] = 0;
		}
		repaint();
	}
	
	//Auto-generated methods from MouseMotionListener
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//Allows the current blocks to follow the mouse pointer
		mouseX = arg0.getX()-17;
		mouseY = arg0.getY()-17;
		repaint();
	}
	
	//Auto-generated methods from KeyListener
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//Allows the user to interact with the program (other than setting blocks)
		char touche = arg0.getKeyChar();
		if (touche == 's'){
			//Saves the game board
			new XMLWriter(this);
			Editor = new int[20][11];
			repaint();
		} else if (touche == 'r'){
			//Resets the game board to zero
			Editor = new int[20][11];
			repaint();
		} else if (touche == KeyEvent.VK_ESCAPE){
			//Back to the main menu
			new FrameMenu();
			frameEditor.dispose();
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}
	
}
