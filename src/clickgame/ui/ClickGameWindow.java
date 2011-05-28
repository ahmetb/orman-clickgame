package clickgame.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clickgame.ClickGameMain;
import clickgame.service.ClickGameService;
import clickgame.service.HighScore;

@SuppressWarnings("serial")
public class ClickGameWindow extends JFrame {
	
	private static int GAME_SECONDS = 4;
	private static double timeLeft = 0;
	private static int score = 0;
	
	final OrmanLogWindow logWindow;

	final HighScoresWindow hiScoreWindow;
	
	public ClickGameWindow(){
		super("Clickgame!");
		setSize(300,300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		logWindow = new OrmanLogWindow();
		hiScoreWindow = new HighScoresWindow();
		
		Container c = getContentPane();
		
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.PAGE_AXIS));
		
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new BoxLayout(pnTop, BoxLayout.LINE_AXIS));
		
		final JButton btnStart = new JButton("New Game");
		final JButton btnHiScores = new JButton("High Scores");
		btnHiScores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hiScoreWindow.setVisible(!hiScoreWindow.isVisible());
			}
		});
		
		JButton btnLogs = new JButton("Logs");
		btnLogs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logWindow.setVisible(!logWindow.isVisible());
			}
		});
		
		pnTop.add(btnStart);
		pnTop.add(btnHiScores);
		pnTop.add(btnLogs);
		
		
		JPanel pnStats = new JPanel();
		pnStats.setLayout(new BoxLayout(pnStats, BoxLayout.LINE_AXIS));
		
		JPanel timeLine = new JPanel();
		JLabel lblTime = new JLabel("Time left:");
		final JTextField txtTime = new JTextField();
		txtTime.setEditable(false);
		JLabel lblScore = new JLabel("Score:");
		final JTextField txtScore = new JTextField();
		txtScore.setEditable(false);
		
		pnStats.add(lblTime);
		pnStats.add(txtTime);
		pnStats.add(lblScore);
		pnStats.add(txtScore);

		JPanel pnGame = new  JPanel();
		JButton btnClick = new JButton("CLICK TO SCORE!");
		btnClick.setPreferredSize(new Dimension(200, 200));
		pnGame.add(btnClick);
		
		pnMain.add(pnTop);
		pnMain.add(pnStats);
		pnMain.add(pnGame);

		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Runnable r = new Runnable() {
					@Override
					public void run() {
						timeLeft = GAME_SECONDS;
						txtTime.setText(timeLeft + " s.");
						score = 0;
						txtScore.setText(score+"");
						btnStart.setEnabled(false);
						while(timeLeft>0) {
							try {
								Thread.sleep(100); // sleep 1 secs
							} catch (InterruptedException e) {
								System.exit(0);
							}
							timeLeft -= 0.1;
							txtTime.setText(((int)(timeLeft*10))/10.0 + " s.");
						}
						btnStart.setEnabled(true);
						// Game finished.
						String name = null;
						do{
							name = JOptionPane.showInputDialog("Your name");
						} while(name == null || "".equals(name.trim()));
						
						ClickGameMain.service.queueScore(name, score);
					}
				};
				Thread t = new Thread(r);
				t.start();
			}
		});
		
		btnClick.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				score++;
				txtScore.setText(score+"");
			}
		});
		
		c.add(pnMain, BorderLayout.CENTER);
	}

	public OrmanLogWindow getLogWindow() {
		return logWindow;
	}
	
	public HighScoresWindow getHiScoresWindow() {
		return hiScoreWindow;
	}
}
