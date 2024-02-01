package in.ac.dei.edrp.admissionsystem.report;

import in.ac.dei.edrp.admissionsystem.common.beans.Applicant;
import in.ac.dei.edrp.admissionsystem.common.CommonBean;
import java.util.List;

public interface ReportDao {
	public List<ReportBean> getPrograms();
	public List<ReportBean> getInterviewlist(String progID); 
	public List<ReportBean> getSession();
	public void insertInterviewLog(ReportBean reportBean); 
	public String getProgramName(String progID);
	public void updateStatus(ReportBean report);
	
public List<Candidate> getUserList(Candidate candidate);
public int updateDocumentUploadStatus(String documentName, Candidate candidate);

public List<Candidate> getUserListLE(Candidate candidate);
public int updateDocumentUploadStatusLE(String documentName, Candidate candidate);

public List<Candidate> getUserWithProgram(Candidate candidate);
public List<CommonBean> getPreferredPrograms(Candidate candidate);
public List<Candidate> insertPreferences(Candidate applicant);

public List<CommonBean> getProgramsWithShifts();

public List<Candidate> getAttdApplicants(CommonBean cb);

public List<CommonBean> getProgramsWithShiftsVanue();

public List<CommonBean> getProgramsWithInterviewVanue();

public List<Candidate> getIntAttdApplicants(CommonBean cb);

public List<CommonBean> getDistanceCenters();

public List<Candidate> getIntAttdApplicantsDis(CommonBean cb);

}
