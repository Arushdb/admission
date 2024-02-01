package in.ac.dei.edrp.admissionsystem;

import java.io.File;

import in.ac.dei.edrp.admissionsystem.common.beans.Applicant;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class Utility 
{
	public static String getDocumentPath(HttpServletRequest request, ServletContext context)
	{
		String applicantDocumentPath = null;
		String docParamVal = context.getInitParameter("DOC_FOLDER");
		if(docParamVal == null || docParamVal.trim().equalsIgnoreCase(""))
		{
			applicantDocumentPath = null;
		}
		else
		{
			Applicant applicant = (Applicant) request.getSession().getAttribute("applicant");
			
			String homeDir = System.getProperty("user.home");
			String docRootFolder = homeDir + File.separator + docParamVal;
			
			applicantDocumentPath = applicant.getApplicationDocumentPath();
						
			if(applicantDocumentPath == null || applicantDocumentPath.trim().equalsIgnoreCase(""))
			{
				docRootFolder = docRootFolder + File.separator + "ApplicantDocuments" + File.separator + applicant.getSessionStartDate().substring(0, 4)+ "-" +applicant.getSessionEndDate().substring(0, 4);
				applicantDocumentPath = docRootFolder + File.separator + applicant.getApplicationNumber();
			}

		}
				
		return applicantDocumentPath;
	}
}
