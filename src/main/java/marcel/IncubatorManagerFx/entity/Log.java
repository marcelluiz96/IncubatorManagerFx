package marcel.IncubatorManagerFx.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Log {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="log_seq")
	@SequenceGenerator(name="log_seq", sequenceName="log_seq", allocationSize=1)
	@Column(name = "log_seq", unique = true, nullable = false)
	private long id;
	
	private Date date; 
	
	private String incubatorName;
	
	private int noiseInDb;

	@Enumerated(EnumType.STRING)
	private LogType logType;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getIncubatorName() {
		return incubatorName;
	}

	public void setIncubatorName(String incubatorName) {
		this.incubatorName = incubatorName;
	}

	public LogType getLogType() {
		return logType;
	}

	public void setLogType(LogType logType) {
		this.logType = logType;
	}
	public int getNoiseInDb() {
		return noiseInDb;
	}

	public void setNoiseInDb(int noiseInDb) {
		this.noiseInDb = noiseInDb;
	}
	
	

}
