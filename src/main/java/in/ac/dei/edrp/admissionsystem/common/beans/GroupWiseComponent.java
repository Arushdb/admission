package in.ac.dei.edrp.admissionsystem.common.beans;



import java.util.ArrayList;
import java.util.List;

public class GroupWiseComponent 
{
	private String code;
	private String description;
	private List<Component> components = new ArrayList<Component>();
	private List<Component> gateComponents = new ArrayList<Component>();
	
	private int showOptions;
	private int universityToAsk;
	
	public GroupWiseComponent()
	{
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Component> getComponents() {
		return components;
	}

	public void setComponents(List<Component> components) {
		this.components = components;
	}
	
	
	public List<Component> getGateComponents() {
		return gateComponents;
	}

	public void setGateComponents(List<Component> gateComponents) {
		this.gateComponents = gateComponents;
	}

	public int getShowOptions() {
		return showOptions;
	}

	public void setShowOptions(int showOptions) {
		this.showOptions = showOptions;
	}

	public int getUniversityToAsk() {
		return universityToAsk;
	}

	public void setUniversityToAsk(int universityToAsk) {
		this.universityToAsk = universityToAsk;
	}

	
		
}
