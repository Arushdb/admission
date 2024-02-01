package in.ac.dei.edrp.admissionsystem.common.beans;

import java.util.ArrayList;
import java.util.List;

public class StudentPaper 
{
	private List<StudentProgramPaper> studentProgramPapers = new ArrayList<StudentProgramPaper>();
	
	public StudentPaper()
	{
		
	}

	public List<StudentProgramPaper> getStudentProgramPapers() {
		return studentProgramPapers;
	}

	public void setStudentProgramPapers(
			List<StudentProgramPaper> studentProgramPapers) {
		this.studentProgramPapers = studentProgramPapers;
	}
	
	

}
