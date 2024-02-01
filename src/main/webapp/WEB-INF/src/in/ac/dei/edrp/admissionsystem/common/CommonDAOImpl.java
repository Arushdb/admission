package in.ac.dei.edrp.admissionsystem.common;

import java.util.ArrayList;
import java.util.List;

import in.ac.dei.edrp.admissionsystem.application.ApplicationDAO;
import in.ac.dei.edrp.admissionsystem.upload.CM_StudentInfoGetter;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class CommonDAOImpl extends SqlMapClientDaoSupport implements CommonDAO{

	 Logger logObj = Logger.getLogger(ApplicationDAO.class);
	
	 public Applicant getApplicantBean(String applicationNumber)
		{
			Applicant applicant = new Applicant();
			List<ApplicantProgram> applicantPrograms = new ArrayList<ApplicantProgram>();
			List<ProgramComponent> programComponents = new ArrayList<ProgramComponent>();
			try
			{
				applicant = (Applicant)getSqlMapClientTemplate().queryForObject("common.getApplicantPersonalDetails", applicationNumber);
				
				System.out.println(applicant.getApplicationDocumentPath()+" on impl");
				
				applicantPrograms = getSqlMapClientTemplate().queryForList("common.getApplicantPrograms", applicationNumber);
				programComponents = getSqlMapClientTemplate().queryForList("common.getApplicantPComponents", applicationNumber);
				for(int i = 0; i < applicantPrograms.size(); i++)
				{
					List<EntranceTest> entranceTests = getSqlMapClientTemplate().queryForList("common.getEntranceTests", applicantPrograms.get(i));
					applicantPrograms.get(i).setEntranceTests(entranceTests);
				}
				applicant.setApplicantPrograms(applicantPrograms);
				applicant.setProgramComponents(programComponents);
				applicant.setStaff_ward(applicantPrograms.get(0).getStaff_ward());
				
				return applicant;
			}
			catch(Exception ex)
			{
				logObj.error(ex);
				System.out.println(ex);
			}
			
			
			return applicant;
		}
	 
	 public String generateErrorLogs(CM_StudentInfoGetter studentInfo){
		 try{
			System.out.println("in impl generateErrorLogs "+studentInfo.getForm_number()+"|"+studentInfo.getFile_name());
			 getSqlMapClientTemplate().insert("common.applicationGenerationErrorLogs",studentInfo);	 
			System.out.println("inserted error logs"); 
		 }catch (Exception e) {
			 System.out.println(e);
			return e.getMessage();
		}
		return null;
		 
	 }
	 
	 public List<String> getResearchPrograms(String applicationNumber) 
	 {
			List<String> researchPrograms = new ArrayList<String>();
			try
			{
				researchPrograms = getSqlMapClientTemplate().queryForList("application.getResearchPrograms", applicationNumber);
			}
			catch(Exception ex)
			{
				
			}
			return researchPrograms;
	 }

	 
}
