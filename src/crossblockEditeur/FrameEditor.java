package crossblockEditeur;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class FrameEditor extends JFrame{

	//FrameEditor constructor
	public FrameEditor(){
		this.setTitle("Level Editor");
		this.setSize(714,408);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new PanelEditor(this));
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
}
