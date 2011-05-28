package clickgame.service;

import org.orman.mapper.Model;
import org.orman.mapper.annotation.Entity;
import org.orman.mapper.annotation.Index;
import org.orman.mapper.annotation.PrimaryKey;
import org.orman.sql.IndexType;

@Entity
public class HighScore extends Model<HighScore>{
	
	@PrimaryKey(autoIncrement=false)
	public int id;
	
	public String name;
	
	@Index(type=IndexType.BTREE)
	public long score;
	
	public HighScore(){
		
	}
	
	public HighScore(String name, long score){
		this.name = name;
		this.score = score;
	}
	
	public String toString(){
		return score + " : " + name;
	}
}
