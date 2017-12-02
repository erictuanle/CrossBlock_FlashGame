package crossblock;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import crossblockEditeur.FrameEditor;
import crossblockJeu.FrameGame;

@SuppressWarnings("serial")
public class PanelMenu extends JPanel implements ActionListener{
	
	//Attributes
	private JButton newGame = new JButton("New Game");
	private JButton levelEditor = new JButton("Level Editor");
	private FrameMenu FrameMenu;
	
	//Constructor PanelMenu
	public PanelMenu(FrameMenu frameMenu){
		this.add(newGame);
		this.add(levelEditor);
		newGame.addActionListener(this);
		levelEditor.addActionListener(this);
		this.FrameMenu = frameMenu;
	}
	
	//Methods for graphic display
	public void paintComponent(Graphics g){
	    try {
	      Image menu = ImageIO.read(new File("Images/Menu.jpg"));
	      g.drawImage(menu, 0, 0, 714, 408, null);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }               
	  }    

	//Auto generated methods from ActionListener
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == newGame){
			new FrameGame();
			FrameMenu.dispose();
		} else if (arg0.getSource() == levelEditor){
			new FrameEditor();
			FrameMenu.dispose();
		}
	}
	
}
