package clickgame.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.JTextArea;

import org.orman.datasource.Database;
import org.orman.mapper.MappingSession;
import org.orman.mapper.Model;
import org.orman.mapper.ModelQuery;
import org.orman.sql.Query;
import org.orman.sqlite.SQLite;
import org.orman.util.logging.Log;
import org.orman.util.logging.LoggingLevel;

import clickgame.ClickGameMain;
import clickgame.ui.ClickGameWindow;

public class ClickGameService {
	
	Thread queryProcessor;
	private Queue<HighScore> addQueue = new LinkedList<HighScore>();
	private boolean requestedHiScore;
	
	public ClickGameService(final JTextArea logArea){
			Runnable r = new Runnable() {
			
			@Override
			public void run() {
				Database db = new SQLite("highscore.db");
				JTextAreaLogAppender log = new JTextAreaLogAppender(logArea);
				Log.setLogger(log);
				Log.setLevel(LoggingLevel.TRACE);
				
				MappingSession.registerPackage("clickgame.service");
				MappingSession.registerDatabase(db);
				MappingSession.start();
				
				ClickGameMain.service.requestHiScore();
				
				while(true){
					try {
						if (addQueue.isEmpty()){
							Thread.sleep(100);
						} else {
							HighScore hs = addQueue.poll();
							hs.insert();
							ClickGameMain.service.requestHiScore();
						}
						
						if (requestedHiScore){
							requestedHiScore = false;
							Query q = ModelQuery.select().from(HighScore.class)
									.orderBy("-HighScore.score").limit(10).getQuery();
							List<HighScore> scores = Model.fetchQuery(q, HighScore.class);
							StringBuffer sb = new StringBuffer("TOP 10\n");
							for(HighScore hs : scores)
								sb.append(hs.score + " - " + hs.name + '\n');
							ClickGameMain.gui.getHiScoresWindow().getTextArea().setText(sb.toString());
						}
					} catch (InterruptedException e) {
						return;
					}
					
				}
			}
			
		};
		Thread t = new Thread(r);
		t.start();
		
	}
	
	public void queueScore(String name, long score){
		HighScore hs = new HighScore(name,score);
		addQueue.add(hs);
	}
	
	public void requestHiScore(){
		requestedHiScore = true;
	}
}
