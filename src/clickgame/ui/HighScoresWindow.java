package clickgame.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import clickgame.ClickGameMain;

@SuppressWarnings("serial")
public class HighScoresWindow extends JFrame{
	private JTextArea jScores;
	
	public HighScoresWindow(){
		setTitle("High Scores");
		setResizable(false);
		setSize(new Dimension(200, 300));
		setLocation(310, 230);
		
		jScores = new JTextArea();
		jScores.setEditable(false);
		JScrollPane pScroll = new JScrollPane(jScores);

		Container c = getContentPane();
		c.add(pScroll, BorderLayout.CENTER);
		c.setVisible(true);
		setVisible(true);
	}
	
	public JTextArea getTextArea(){
		return jScores;
	}

}
