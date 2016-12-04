package marcel.IncubatorManagerFx.datamodel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import marcel.IncubatorManagerFx.entity.Incubator;

public class IncubatorDataModel {

	private SimpleStringProperty nameColumnProperty;
	private SimpleStringProperty uniqueCodeColumnProperty;
	private SimpleStringProperty locationColumnProperty;
	private SimpleStringProperty statusColumnProperty;
	private SimpleIntegerProperty noiseColumnProperty;

	public IncubatorDataModel(Incubator incubator) {
		nameColumnProperty = new SimpleStringProperty(incubator.getName());
		uniqueCodeColumnProperty = new SimpleStringProperty(incubator.getUniqueCode());
		locationColumnProperty = new SimpleStringProperty(incubator.getLocation());
		statusColumnProperty = new SimpleStringProperty("Connecting...");
		noiseColumnProperty = new SimpleIntegerProperty(0);
	}
	
	public IncubatorDataModel(Incubator incubator, int noise, String status) {
		nameColumnProperty = new SimpleStringProperty(incubator.getName());
		uniqueCodeColumnProperty = new SimpleStringProperty(incubator.getUniqueCode());
		locationColumnProperty = new SimpleStringProperty(incubator.getLocation());
		statusColumnProperty = new SimpleStringProperty(status);
		noiseColumnProperty = new SimpleIntegerProperty(noise);
	}

	public SimpleStringProperty getNameColumnProperty() {
		return nameColumnProperty;
	}

	public void setNameColumnProperty(SimpleStringProperty nameColumnProperty) {
		this.nameColumnProperty = nameColumnProperty;
	}

	public SimpleStringProperty getUniqueCodeColumnProperty() {
		return uniqueCodeColumnProperty;
	}

	public void setUniqueCodeColumnProperty(SimpleStringProperty uniqueCodeColumnProperty) {
		this.uniqueCodeColumnProperty = uniqueCodeColumnProperty;
	}

	public SimpleStringProperty getLocationColumnProperty() {
		return locationColumnProperty;
	}

	public void setLocationColumnProperty(SimpleStringProperty locationColumnProperty) {
		this.locationColumnProperty = locationColumnProperty;
	}

	public SimpleStringProperty getStatusColumnProperty() {
		return statusColumnProperty;
	}

	public void setStatusColumnProperty(SimpleStringProperty statusColumnProperty) {
		this.statusColumnProperty = statusColumnProperty;
	}

	public SimpleIntegerProperty getNoiseColumnProperty() {
		return noiseColumnProperty;
	}

	public void setNoiseColumnProperty(SimpleIntegerProperty noiseColumnProperty) {
		this.noiseColumnProperty = noiseColumnProperty;
	}
	
	
	
}
