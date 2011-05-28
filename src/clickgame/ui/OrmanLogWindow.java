package clickgame.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class OrmanLogWindow extends JFrame {
	private JTextArea jLogs;
	
	public OrmanLogWindow(){
		super("ORMAN Framework Log");
		setSize(new Dimension(500,200));
		setVisible(true);
		setLocation(310, 0);
		
		jLogs = new JTextArea();
		jLogs.setEditable(false);
		JScrollPane pScroll = new JScrollPane(jLogs);

		Container c = getContentPane();
		c.add(pScroll, BorderLayout.CENTER);
		c.setVisible(true);
		setVisible(true);
	}
	
	public JTextArea getLogArea(){
		return jLogs;
	}
}
