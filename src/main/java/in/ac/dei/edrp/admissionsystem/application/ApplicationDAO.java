package in.ac.dei.edrp.admissionsystem.application;


import in.ac.dei.edrp.admissionsystem.common.ApplicationDetail;
import in.ac.dei.edrp.admissionsystem.common.CommonBean;
import in.ac.dei.edrp.admissionsystem.common.YearSemesterWiseMarks;
import in.ac.dei.edrp.admissionsystem.common.beans.AcademicDetail;
import in.ac.dei.edrp.admissionsystem.common.beans.AppForm;
import in.ac.dei.edrp.admissionsystem.common.beans.Applicant;
import in.ac.dei.edrp.admissionsystem.common.beans.Form;
import in.ac.dei.edrp.admissionsystem.common.beans.InputBean;
import in.ac.dei.edrp.admissionsystem.common.beans.Program;
import in.ac.dei.edrp.admissionsystem.common.beans.Program;
import in.ac.dei.edrp.admissionsystem.common.beans.ProgramWiseDetail;
import in.ac.dei.edrp.admissionsystem.common.beans.StudentPaper;


import java.util.List;

public interface ApplicationDAO {
	public List<CommonBean> getProgramForms(String universityID);
	public List<Program> getFormPrograms(Form form);
	public Applicant getApplicationForm(Applicant applicant);
	
	public Applicant savePersonalDetails(Applicant applicant);
	public Applicant savePaperOptions(Applicant applicant);
	
	public List<Form> getFormsAndPrograms(Applicant applicant);
	public Applicant getApplicantDetails(Applicant applicant);
	public Applicant getProgramPaperOptions(Applicant applicant, boolean reapplyFlag);
	public String getPaymentType();
	public String getPaymentPerProgram(Applicant input);
	public Applicant removeProgram(String[] programs, Applicant applicant);
	public String getPaymentLumpsum(Applicant input);
	public List<String> getDuplicatePrograms(Applicant applicant);
	public List<String> getAppliedPrograms(Applicant applicant);
	
	
	public String validatePersonalDetails(Applicant applicant, Applicant sessionApplicant);
	public ApplicationDetail getApplicationDetails(ApplicationDetail input);
	public String getApplicationFee(Applicant applicant);
	public String insertAcademicDetails(List<ProgramWiseDetail> programWiseDetails) throws Exception;
	public String updateAcademicDetails(List<ProgramWiseDetail> programWiseDetails) throws Exception;
	public List<CommonBean> getStudyCenters(InputBean input);
	public List<String> getResearchPrograms(String applicationNumber);
	public String insertPhdAcademicDetails(Applicant applicant) throws Exception;
	public String updatePhdAcademicDetails(Applicant applicant) throws Exception;
	
	public List<CommonBean> getEmbkup();
	public List<YearSemesterWiseMarks> getYearSemWiseMarks(String applicationNumber);
	public String saveYearSemWiseMarks(List<YearSemesterWiseMarks> inputList) throws Exception;
	public List<CommonBean> getStateDistricts(String inpState);  //added by Jyoti on 29 Apr 2021
	public String getStudyCenterName(InputBean input); //added by Jyoti 
	public List<CommonBean> getSemestersOnly();
	public boolean ifDeiBoard(String applicationNumber);
public boolean validateCenterCode(InputBean input);	
}
