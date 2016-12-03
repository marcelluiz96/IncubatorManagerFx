package marcel.IncubatorManagerFx.entity;

public enum LogType {
	COMMON("Common"),
	ALARM("Alarm");
	
	String description;
	
	LogType(final String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return description;
	}
	
	public static LogType fromString(String text) {
	    if (text != null) {
	      for (LogType b : LogType.values()) {
	        if (text.equalsIgnoreCase(b.description)) {
	          return b;
	        }
	      }
	    }
	    throw new IllegalArgumentException("No constant with text " + text + " found");
	  }

}
