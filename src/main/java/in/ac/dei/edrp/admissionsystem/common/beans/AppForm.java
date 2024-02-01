package in.ac.dei.edrp.admissionsystem.common.beans;

import java.util.ArrayList;
import java.util.List;

public class AppForm {
	private String applicationNumber;
	private List<String> programs = new ArrayList<String>();
	
	public AppForm()
	{
		
	}

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public List<String> getPrograms() {
		return programs;
	}

	public void setPrograms(List<String> programs) {
		this.programs = programs;
	}
	
	
	
}
