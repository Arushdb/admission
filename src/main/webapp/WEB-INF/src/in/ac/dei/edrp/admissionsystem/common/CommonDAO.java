package in.ac.dei.edrp.admissionsystem.common;

import java.util.List;

import in.ac.dei.edrp.admissionsystem.upload.CM_StudentInfoGetter;

public interface CommonDAO {

	 public Applicant getApplicantBean(String applicationNumber);	
	 public String generateErrorLogs(CM_StudentInfoGetter studentInfo);
	 public List<String> getResearchPrograms(String applicationNumber);
}
