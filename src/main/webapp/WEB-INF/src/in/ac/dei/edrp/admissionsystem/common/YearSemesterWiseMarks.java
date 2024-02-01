package in.ac.dei.edrp.admissionsystem.common;

public class YearSemesterWiseMarks {
	
	private String applicationNumber;
	private String examination;
	private String yearSemester;
	private String obtainedMarks;
	private String totalMarks;
	private String university;
	private String passingYear;
	
	public YearSemesterWiseMarks() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public YearSemesterWiseMarks(String applicationNumber, String examination,
			String yearSemester, String obtainedMarks, String totalMarks,
			String university, String passingYear) {
		super();
		this.applicationNumber = applicationNumber;
		this.examination = examination;
		this.yearSemester = yearSemester;
		this.obtainedMarks = obtainedMarks;
		this.totalMarks = totalMarks;
		this.university = university;
		this.passingYear = passingYear;
	}



	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public String getExamination() {
		return examination;
	}

	public void setExamination(String examination) {
		this.examination = examination;
	}

	public String getYearSemester() {
		return yearSemester;
	}

	public void setYearSemester(String yearSemester) {
		this.yearSemester = yearSemester;
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

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getPassingYear() {
		return passingYear;
	}

	public void setPassingYear(String passingYear) {
		this.passingYear = passingYear;
	}
	
	
	
	
}
