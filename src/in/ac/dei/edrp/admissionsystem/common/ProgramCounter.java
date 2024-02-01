package in.ac.dei.edrp.admissionsystem.common;

public class ProgramCounter {
	
	private String programName;
	private int totalApplicationsCount;
	private int feeVerifiedAppsCount;
	private int feeNotVerifiedAppsCount;
	
	public ProgramCounter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public int getTotalApplicationsCount() {
		return totalApplicationsCount;
	}

	public void setTotalApplicationsCount(int totalApplicationsCount) {
		this.totalApplicationsCount = totalApplicationsCount;
	}

	public int getFeeVerifiedAppsCount() {
		return feeVerifiedAppsCount;
	}

	public void setFeeVerifiedAppsCount(int feeVerifiedAppsCount) {
		this.feeVerifiedAppsCount = feeVerifiedAppsCount;
	}

	public int getFeeNotVerifiedAppsCount() {
		return feeNotVerifiedAppsCount;
	}

	public void setFeeNotVerifiedAppsCount(int feeNotVerifiedAppsCount) {
		this.feeNotVerifiedAppsCount = feeNotVerifiedAppsCount;
	}
	
	
	

}
