package marcel.IncubatorManagerFx.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Hospital {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="hospital_seq")
	@SequenceGenerator(name="hospital_seq", sequenceName="hospital_seq", allocationSize=1)
	@Column(name = "hospital_id", unique = true, nullable = false)
	private long id;
	
	private String name;
	
	@OneToMany
	private List<Incubator> incubators;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Incubator> getIncubators() {
		return incubators;
	}

	public void setIncubators(List<Incubator> incubators) {
		this.incubators = incubators;
	}
	
	
	

}
