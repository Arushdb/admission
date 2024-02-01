package in.ac.dei.edrp.admissionsystem.report;

import in.ac.dei.edrp.admissionsystem.common.CommonBean;
import in.ac.dei.edrp.admissionsystem.common.beans.Applicant;

import java.util.List;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ReportDaoImpl extends SqlMapClientDaoSupport implements ReportDao
{
	private Logger loggerObject = Logger.getLogger(ReportDaoImpl.class);
	
	public List<ReportBean> getPrograms() 
	{
		List<ReportBean> programList = null;
		try
		{
			programList = getSqlMapClientTemplate().queryForList("report.getPrograms");
		}
		catch(Exception ex)
		{
			loggerObject.error("EXCEPTION IN GETTING PROGRAM LIST : " + ex);
		}
		return programList;
	}
	
	public String getProgramName(String progID) 
	{
		String programName = null;
		try
		{
			programName = getSqlMapClientTemplate().queryForObject("report.getProgramName",progID).toString();
		}
		catch(Exception ex)
		{
			loggerObject.error("EXCEPTION IN GETTING PROGRAM LIST : " + ex);
		}
		return programName;
	}
	
	public List<ReportBean> getInterviewlist(String progID) 
	{
		List<ReportBean> interviewList = null;
		try
		{
			interviewList = getSqlMapClientTemplate().queryForList("report.getInterviewlist",progID);
		}
		catch(Exception ex)
		{
			loggerObject.error("EXCEPTION IN GETTING INTERVIEW LIST : " + ex);
		}
		return interviewList;
	}
	
	public List<ReportBean> getSession() 
	{
		List<ReportBean> session = null;
		try
		{
			session = getSqlMapClientTemplate().queryForList("report.getSession");
			System.out.println("session in impl:"+session.get(0).getSession());
		}
		catch(Exception ex)
		{
			loggerObject.error("EXCEPTION IN GETTING SESSION : " + ex);
		}
		return session;
	}
	
	public void insertInterviewLog(ReportBean report) 
	{
		
		try
		{
			getSqlMapClient().insert("report.interviewLog",report);
			
		}
		catch(Exception ex)
		{
			loggerObject.error("EXCEPTION IN INSERTING INTERVIEW LOG : " + ex);
		}
		
	}
	
	public void updateStatus(ReportBean report) 
	{
		
		try
		{
			getSqlMapClient().update("report.setStatus",report);
			
		}
		catch(Exception ex)
		{
			loggerObject.error("EXCEPTION IN UPDATING STATUS : " + ex);
		}
		
	}
	
public List<Candidate> getUserList(Candidate candidate)
	{
		List<Candidate> applicants = new ArrayList<Candidate>();
		try
		{
			applicants = getSqlMapClientTemplate().queryForList("report.getUserList", candidate);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return applicants;
		
	}
	
	public int updateDocumentUploadStatus(String documentName, Candidate candidate)
	{
		int updateCount = 0;
		try
		{
			if(documentName.trim().equalsIgnoreCase("cca"))
			{
				updateCount = getSqlMapClientTemplate().update("report.updateCCA", candidate);
			}
			else if(documentName.trim().equalsIgnoreCase("ncc"))
			{
				updateCount = getSqlMapClientTemplate().update("report.updateNCC", candidate);
			}
			else if(documentName.trim().equalsIgnoreCase("nss"))
			{
				updateCount = getSqlMapClientTemplate().update("report.updateNSS", candidate);
			}
			else
			{
				updateCount = getSqlMapClientTemplate().update("report.updateSocial", candidate);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return updateCount;
	}

public List<Candidate> getUserListLE(Candidate candidate)
	{
		List<Candidate> applicants = new ArrayList<Candidate>();
		try
		{
			applicants = getSqlMapClientTemplate().queryForList("report.getUserListLE", candidate);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return applicants;
		
	}
	
	public int updateDocumentUploadStatusLE(String documentName, Candidate candidate)
	{
		int updateCount = 0;
		try
		{
			if(documentName.trim().equalsIgnoreCase("cca"))
			{
				updateCount = getSqlMapClientTemplate().update("report.updateCCALE", candidate);
			}
			else if(documentName.trim().equalsIgnoreCase("ncc"))
			{
				updateCount = getSqlMapClientTemplate().update("report.updateNCCLE", candidate);
			}
			else if(documentName.trim().equalsIgnoreCase("nss"))
			{
				updateCount = getSqlMapClientTemplate().update("report.updateNSSLE", candidate);
			}
			else
			{
				updateCount = getSqlMapClientTemplate().update("report.updateSocialLE", candidate);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return updateCount;
	}
	

public List<Candidate> getUserWithProgram(Candidate candidate)
	{
		List<Candidate> applicants = new ArrayList<Candidate>();
		try
		{
			applicants = getSqlMapClientTemplate().queryForList("report.getUserForCertificatePreference", candidate);
			for(Candidate c : applicants)
			{
				List<CommonBean> programs = getSqlMapClientTemplate().queryForList("report.getUserPrograms", c);
				c.setPrograms(programs);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return applicants;
		
	}

public List<CommonBean> getPreferredPrograms(Candidate candidate)
{
	List<CommonBean> programs = new ArrayList<CommonBean>();
	try
		{
			programs = getSqlMapClientTemplate().queryForList("report.preferencePrograms", candidate);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return programs;
}
public List<Candidate> insertPreferences(Candidate applicant)
{
List<Candidate> candidates = new ArrayList<Candidate>();
try
                {
                   getSqlMapClientTemplate().insert("report.insertPreferences", applicant);
		   candidates = getSqlMapClientTemplate().queryForList("report.checkInsertedPreferences", applicant);
                }
                catch(Exception ex)
                {
                        ex.printStackTrace();
                }
		System.out.println("List size is "+candidates.size());
                return candidates;
}

public List<CommonBean> getProgramsWithShifts() {
	List<CommonBean> programs = new ArrayList<CommonBean>();
	try
	{
		programs = getSqlMapClientTemplate().queryForList("report.getProgramsShift");
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
	System.out.println("List size is "+programs.size());
	return programs;
}
public List<Candidate> getAttdApplicants(CommonBean cb) {
	List<Candidate> candidates = new ArrayList<Candidate>();
	try
	{
		candidates = getSqlMapClientTemplate().queryForList("report.getApplicantsForAttd", cb);
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
	System.out.println("List size is "+candidates.size());
	return candidates;
}	

public List<CommonBean> getProgramsWithShiftsVanue() {
	List<CommonBean> programs = new ArrayList<CommonBean>();
	try
	{
		programs = getSqlMapClientTemplate().queryForList("report.getProgramsShiftVanue");
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
	System.out.println("List size is "+programs.size());
	return programs;
}

public List<CommonBean> getProgramsWithInterviewVanue() {
	List<CommonBean> programs = new ArrayList<CommonBean>();
	try
	{
		programs = getSqlMapClientTemplate().queryForList("report.getProgramsIntVanue");
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
	System.out.println("List size is "+programs.size());
	return programs;
}

public List<Candidate> getIntAttdApplicants(CommonBean cb) {
	List<Candidate> candidates = new ArrayList<Candidate>();
	try
	{
		candidates = getSqlMapClientTemplate().queryForList("report.getApplicantsForIntAttd", cb);
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
	System.out.println("List size is "+candidates.size());
	return candidates;
}


public List<CommonBean> getDistanceCenters() {
	List<CommonBean> ds = new ArrayList<CommonBean>();
	try
	{
		ds = getSqlMapClientTemplate().queryForList("report.getDistanceCenters");
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
	System.out.println("List size is "+ds.size());
	return ds;
}	

public List<Candidate> getIntAttdApplicantsDis(CommonBean cb) {
	List<Candidate> candidates = new ArrayList<Candidate>();
	try
	{
		candidates = getSqlMapClientTemplate().queryForList("report.getAppsForIntAttdDistance", cb);
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
	System.out.println("List size is "+candidates.size());
	return candidates;
}

}
