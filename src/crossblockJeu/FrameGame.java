package crossblockJeu;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class FrameGame extends JFrame{
	
	public FrameGame(){
		this.setTitle("CrossBlock");
		this.setSize(714,408);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new PanelGame(this));
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
}
