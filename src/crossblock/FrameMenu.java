package crossblock;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class FrameMenu extends JFrame {

	//Attributes
	private PanelMenu panel = new PanelMenu(this);
	
	//Constructor FrameMenu
	public FrameMenu(){
		this.setTitle("Main Menu");
		this.setSize(714,408);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setContentPane(panel);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	//Useful Getters and Setters
	public PanelMenu getPanel(){
		return this.panel;
	}
	
	public void setPanel(PanelMenu panel){
		this.panel=panel;
	}
	
}
