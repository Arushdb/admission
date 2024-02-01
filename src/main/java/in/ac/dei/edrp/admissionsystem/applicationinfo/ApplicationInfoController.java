package in.ac.dei.edrp.admissionsystem.applicationinfo;

import in.ac.dei.edrp.admissionsystem.common.CommonBean;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class ApplicationInfoController extends MultiActionController{
	
	public static Logger logObj=Logger.getLogger(ApplicationInfoController.class);

	private ApplicationInfoDAO applicationInfoDAO;
	
	public void setApplicationInfoDAO(ApplicationInfoDAO applicationInfoDAO)
	{
		this.applicationInfoDAO=applicationInfoDAO;
	}
	
	public ModelAndView getApplicationStatus(HttpServletRequest request, HttpServletResponse response) throws Exception
	{System.out.println("Request comes here.");
		HttpSession session = request.getSession(false);
		if(session==null)
		{
			return new ModelAndView("errorPages/SessionExpired","message","Your Session has been expired.");
		}
		//User user = (User)session.getAttribute("user");
		//String emailID = user.getEmailID();
		String emailID = "";
		List<CommonBean> appsStatusList = applicationInfoDAO.getApplicationsStatus(emailID);
		return new ModelAndView("applicationInfo/ApplicationStatus", "appsStatusList", appsStatusList);
		
	}
	
	
	public ModelAndView getAppliedPrograms(HttpServletRequest request, HttpServletResponse response) throws Exception
	{System.out.println("Request comes here.");
		HttpSession session = request.getSession(false);
		if(session==null)
		{
			return new ModelAndView("errorPages/SessionExpired","message","Your Session has been expired.");
		}
		//User user = (User)session.getAttribute("user");
		//String emailID = user.getEmailID();
		String emailID = "";
		List<CommonBean> programs = applicationInfoDAO.getAppliedPrograms(emailID);
		return new ModelAndView("applicationInfo/AppliedPrograms", "programs", programs);
		
	}


}
