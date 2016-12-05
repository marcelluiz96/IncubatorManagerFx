package marcel.IncubatorManagerFx.datamodel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import marcel.IncubatorManagerFx.entity.Log;

public class LogDataModel {

	private SimpleLongProperty idColumnProperty;
	private SimpleStringProperty dateColumnProperty;
	private SimpleStringProperty uniqueCodeColumnProperty;
	private SimpleStringProperty logTypeColumnProperty;
	private SimpleIntegerProperty noiseColumnProperty;

	public LogDataModel(Log log) {
		idColumnProperty = new SimpleLongProperty(log.getId());
		dateColumnProperty = new SimpleStringProperty(log.getDate().toString());
		uniqueCodeColumnProperty =new SimpleStringProperty(log.getIncubatorName());
		logTypeColumnProperty = new SimpleStringProperty(log.getLogType().getDescription());
		noiseColumnProperty = new SimpleIntegerProperty(log.getNoiseInDb());
	}

	public SimpleLongProperty getIdColumnProperty() {
		return idColumnProperty;
	}

	public void setIdColumnProperty(SimpleLongProperty idColumnProperty) {
		this.idColumnProperty = idColumnProperty;
	}

	public SimpleStringProperty getDateColumnProperty() {
		return dateColumnProperty;
	}

	public void setDateColumnProperty(SimpleStringProperty dateColumnProperty) {
		this.dateColumnProperty = dateColumnProperty;
	}

	public SimpleStringProperty getUniqueCodeColumnProperty() {
		return uniqueCodeColumnProperty;
	}

	public void setUniqueCodeColumnProperty(SimpleStringProperty uniqueCodeColumnProperty) {
		this.uniqueCodeColumnProperty = uniqueCodeColumnProperty;
	}

	public SimpleStringProperty getLogTypeColumnProperty() {
		return logTypeColumnProperty;
	}

	public void setLogTypeColumnProperty(SimpleStringProperty logTypeColumnProperty) {
		this.logTypeColumnProperty = logTypeColumnProperty;
	}

	public SimpleIntegerProperty getNoiseColumnProperty() {
		return noiseColumnProperty;
	}

	public void setNoiseColumnProperty(SimpleIntegerProperty noiseColumnProperty) {
		this.noiseColumnProperty = noiseColumnProperty;
	}

}
