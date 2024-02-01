package in.ac.dei.edrp.admissionsystem.addMoreProgram;

import in.ac.dei.edrp.admissionsystem.common.CommonBean;
import in.ac.dei.edrp.admissionsystem.common.beans.Applicant;
import in.ac.dei.edrp.admissionsystem.common.beans.Program;
import in.ac.dei.edrp.admissionsystem.common.beans.ProgramWiseDetail;
import in.ac.dei.edrp.admissionsystem.common.beans.StudentProgramPaper;

import java.util.List;

public interface addMoreProgramDao 
{
	public List<CommonBean> getAppliedPrograms(Applicant applicant) throws Exception;
	
	public List<CommonBean> getTransferPrograms(Applicant applicant) throws Exception;
	
	public List<CommonBean> checkApplication(CommonBean inputObject) throws Exception;
	
	
	public void addProgramsToApplication(CommonBean inputObject,StudentProgramPaper inputToSR) throws Exception;
	
	public String toAskForBranches(String programId) throws Exception;
	public List<CommonBean> getProgramBranches(String programId) throws Exception;
}
