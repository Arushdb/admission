package in.ac.dei.edrp.admissionsystem.common;

import java.io.Serializable;

public class ProgramComponent implements Serializable
{
private String componentName;
private String obtainedMarks;
private String totalMarks;
private String board;
private String existingStudent;
private String passingYear;
private String resultAwaited;
private String evaluationType;
private String score;

public ProgramComponent() 
{

}

public String getComponentName() {
	return componentName;
}

public void setComponentName(String componentName) {
	this.componentName = componentName;
}


public String getBoard() {
	return board;
}

public void setBoard(String board) {
	this.board = board;
}

public String getExistingStudent() {
	return existingStudent;
}

public void setExistingStudent(String existingStudent) {
	this.existingStudent = existingStudent;
}

public String getObtainedMarks() {
	return obtainedMarks;
}

public void setObtainedMarks(String obtainedMarks) {
	this.obtainedMarks = obtainedMarks;
}

public String getTotalMarks() {
	return totalMarks;
}

public void setTotalMarks(String totalMarks) {
	this.totalMarks = totalMarks;
}

public String getPassingYear() {
	return passingYear;
}

public void setPassingYear(String passingYear) {
	this.passingYear = passingYear;
}

public String getResultAwaited() {
	return resultAwaited;
}

public void setResultAwaited(String resultAwaited) {
	this.resultAwaited = resultAwaited;
}

public String getEvaluationType() {
	return evaluationType;
}

public void setEvaluationType(String evaluationType) {
	this.evaluationType = evaluationType;
}

public String getScore() {
	return score;
}

public void setScore(String score) {
	this.score = score;
}


}
