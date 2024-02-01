package in.ac.dei.edrp.admissionsystem.common.beans;

import in.ac.dei.edrp.admissionsystem.common.CommonBean;

import java.util.ArrayList;
import java.util.List;



public class Component 
{
	private String componentID;
	private String componentName;
	private String evaluationType;
	private String weightage;
	private String weightageGiven;
	private String eligibilityImpact;
	private String spclWeigtageGiven;
	private String boardFlag;
	private String passingYear;
	private String resultAwaitedFlag;
	
	
	private String obtainedMarks;
	private String totalMarks;
	private String boardValue;
	private String passingYearValue;
	private String resultAwaitedValue;
	private String existingStudentValue;
	
	private String examinationType;
	private String level;
	
	private String ranking;
	private String score;
	private String totalApplicants;
	private String gateBranch;
	private String gateYear;

	private String optionCode;
	private String optionName;
	
	private List<CommonBean> optionsList = new ArrayList<CommonBean>();
	private List<CommonBean> subjectOptions = new ArrayList<CommonBean>();
	private String subjectOptionCode;
	private String subjectOptionName;
	
	private String otherBoardName;
	private String boardName;
	private String compulsory;
	private String percentage;
	private String percentile;
	private String rollnumber;
	
	public Component() 
	{
	
	}

	public Component(String componentID, String componentName,
			String evaluationType, String weightage, String weightageGiven,
			String eligibilityImpact, String spclWeigtageGiven, String boardFlag) 
	{
		this.componentID = componentID;
		this.componentName = componentName;
		this.evaluationType = evaluationType;
		this.weightage = weightage;
		this.weightageGiven = weightageGiven;
		this.eligibilityImpact = eligibilityImpact;
		this.spclWeigtageGiven = spclWeigtageGiven;
		this.boardFlag = boardFlag;
	}

	public String getComponentID() {
		return componentID;
	}

	public void setComponentID(String componentID) {
		this.componentID = componentID;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getEvaluationType() {
		return evaluationType;
	}

	public void setEvaluationType(String evaluationType) {
		this.evaluationType = evaluationType;
	}

	public String getWeightage() {
		return weightage;
	}

	public void setWeightage(String weightage) {
		this.weightage = weightage;
	}

	public String getWeightageGiven() {
		return weightageGiven;
	}

	public void setWeightageGiven(String weightageGiven) {
		this.weightageGiven = weightageGiven;
	}

	public String getEligibilityImpact() {
		return eligibilityImpact;
	}

	public void setEligibilityImpact(String eligibilityImpact) {
		this.eligibilityImpact = eligibilityImpact;
	}

	public String getSpclWeigtageGiven() {
		return spclWeigtageGiven;
	}

	public void setSpclWeigtageGiven(String spclWeigtageGiven) {
		this.spclWeigtageGiven = spclWeigtageGiven;
	}

	public String getBoardFlag() {
		return boardFlag;
	}

	public void setBoardFlag(String boardFlag) {
		this.boardFlag = boardFlag;
	}

	public String getPassingYear() {
		return passingYear;
	}

	public void setPassingYear(String passingYear) {
		this.passingYear = passingYear;
	}

	
	public String getResultAwaitedFlag() {
		return resultAwaitedFlag;
	}

	public void setResultAwaitedFlag(String resultAwaitedFlag) {
		this.resultAwaitedFlag = resultAwaitedFlag;
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

	public String getBoardValue() {
		return boardValue;
	}

	public void setBoardValue(String boardValue) {
		this.boardValue = boardValue;
	}

	public String getPassingYearValue() {
		return passingYearValue;
	}

	public void setPassingYearValue(String passingYearValue) {
		this.passingYearValue = passingYearValue;
	}

	public String getResultAwaitedValue() {
		return resultAwaitedValue;
	}

	public void setResultAwaitedValue(String resultAwaitedValue) {
		this.resultAwaitedValue = resultAwaitedValue;
	}

	public String getExistingStudentValue() {
		return existingStudentValue;
	}

	public void setExistingStudentValue(String existingStudentValue) {
		this.existingStudentValue = existingStudentValue;
	}

	public String getExaminationType() {
		return examinationType;
	}

	public void setExaminationType(String examinationType) {
		this.examinationType = examinationType;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public List<CommonBean> getOptionsList() {
		return optionsList;
	}

	public void setOptionsList(List<CommonBean> optionsList) {
		this.optionsList = optionsList;
	}

	public String getRanking() {
		return ranking;
	}

	public void setRanking(String ranking) {
		this.ranking = ranking;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getTotalApplicants() {
		return totalApplicants;
	}

	public void setTotalApplicants(String totalApplicants) {
		this.totalApplicants = totalApplicants;
	}

	public String getGateBranch() {
		return gateBranch;
	}

	public void setGateBranch(String gateBranch) {
		this.gateBranch = gateBranch;
	}

	public String getGateYear() {
		return gateYear;
	}

	public void setGateYear(String gateYear) {
		this.gateYear = gateYear;
	}

	public String getOptionCode() {
		return optionCode;
	}

	public void setOptionCode(String optionCode) {
		this.optionCode = optionCode;
	}

	public String getOtherBoardName() {
		return otherBoardName;
	}

	public void setOtherBoardName(String otherBoardName) {
		this.otherBoardName = otherBoardName;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public List<CommonBean> getSubjectOptions() {
		return subjectOptions;
	}

	public void setSubjectOptions(List<CommonBean> subjectOptions) {
		this.subjectOptions = subjectOptions;
	}

	public String getSubjectOptionCode() {
		return subjectOptionCode;
	}

	public void setSubjectOptionCode(String subjectOptionCode) {
		this.subjectOptionCode = subjectOptionCode;
	}

	public String getSubjectOptionName() {
		return subjectOptionName;
	}

	public void setSubjectOptionName(String subjectOptionName) {
		this.subjectOptionName = subjectOptionName;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public String getCompulsory() {
		return compulsory;
	}

	public void setCompulsory(String compulsory) {
		this.compulsory = compulsory;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public String getPercentile() {
		return percentile;
	}

	public void setPercentile(String percentile) {
		this.percentile = percentile;
	}

	public String getRollnumber() {
		return rollnumber;
	}

	public void setRollnumber(String rollnumber) {
		this.rollnumber = rollnumber;
	}

	
	
	

}
