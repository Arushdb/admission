package in.ac.dei.edrp.admissionsystem.common;

import java.io.Serializable;

public class EntranceTest implements Serializable
{

private String group;
private String paperName;
private String paperCode;

public EntranceTest() 
{
	
}



public String getGroup() {
	return group;
}

public void setGroup(String group) {
	this.group = group;
}

public String getPaperName() {
	return paperName;
}

public void setPaperName(String paperName) {
	this.paperName = paperName;
}



public String getPaperCode() {
	return paperCode;
}



public void setPaperCode(String paperCode) {
	this.paperCode = paperCode;
}


}
