package in.ac.dei.edrp.admissionsystem.account;



import in.ac.dei.edrp.admissionsystem.common.CommonBean;
import in.ac.dei.edrp.admissionsystem.common.ProgramCounter;
import in.ac.dei.edrp.admissionsystem.common.User;
import in.ac.dei.edrp.admissionsystem.common.beans.Applicant;
import in.ac.dei.edrp.admissionsystem.common.beans.ProgramWiseDetail;

import java.util.List;

public interface AccountDAO {

	
	public List<Applicant> getUserList(Applicant applicant);
	public List<CommonBean> getCategories(String universityID);
	public List<CommonBean> getSQuestions();
	public List<Applicant> createAccount(Applicant user);
	public Applicant getApplicantDetails(String applicationNumber);
	public String generatePassword();
	public String sendMailtoUser(String userName, String password,
			String email, String applicationNumber);
	public List<Applicant> savePersonalDetailstesting(Applicant applicant);//added by atul dayal
	public List<Applicant> savePaperOptionstesting(Applicant applicant);//added by atul dayal
	
	public String updatePassword(User user);
	public String resendMailtoUser(String userName, String password,
			String email, String applicationNumber) ;
	public List<String> validateAccountDetails(Applicant applicant);
	public List<User> checkApplication(User user);
	
	public List<ProgramWiseDetail> getProgramAdmitCardDetails(Applicant applicant);
	public List<User> getAccountsWithSameDetails(Applicant applicant);
	
	public String getFeePassword(String appNumber);
	
	public List<ProgramCounter> getApplicationsCount();
}
