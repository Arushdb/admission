package in.ac.dei.edrp.admissionsystem.transfer;

import in.ac.dei.edrp.admissionsystem.common.CommonBean;
import in.ac.dei.edrp.admissionsystem.common.beans.Applicant;
import in.ac.dei.edrp.admissionsystem.common.beans.Program;
import in.ac.dei.edrp.admissionsystem.common.beans.ProgramWiseDetail;

import java.util.List;

public interface TransferDao 
{
	public List<CommonBean> getAppliedPrograms(Applicant applicant) throws Exception;
	
	public List<CommonBean> getTransferPrograms() throws Exception;
	
	public List<CommonBean> checkApplication(CommonBean inputObject) throws Exception;
	
	public void applyTransferApplications(CommonBean inputObject) throws Exception;
	
}
