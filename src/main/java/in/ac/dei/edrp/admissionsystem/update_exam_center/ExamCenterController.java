package in.ac.dei.edrp.admissionsystem.update_exam_center;

import in.ac.dei.edrp.admissionsystem.common.CommonBean;
import in.ac.dei.edrp.admissionsystem.common.User;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class ExamCenterController extends MultiActionController {
	
	private ExamCenterDao examCenterDao ;

	public ExamCenterDao getExamCenterDao() {
		return examCenterDao;
	}

	public void setExamCenterDao(ExamCenterDao examCenterDao) {
		this.examCenterDao = examCenterDao;
	}
	
/*	public ModelAndView getUpdateExamCenterPage(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView model = new ModelAndView("exam_center_update/ExamCenterUpdate");
		
		try {
			
			List<CommonBean> examCenters = examCenterDao.getExamCenterList();
			model.addObject("examCenters", examCenters);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			model.addObject("error", "There is an exception in getting examination centers.");
		}
		return model;
	} */ //commented to close the link
	
	public ModelAndView updateExamCenter(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView model = new ModelAndView("exam_center_update/ExamCenterUpdate");
		
		String applicationNumber = request.getParameter("applicationNumber");
		String password = request.getParameter("password");
		String examCenter = request.getParameter("examCenter");
		
		try {
			
			if((applicationNumber == null || applicationNumber.trim().equals(""))
					|| (password == null || password.trim().equals(""))
					|| (examCenter == null || examCenter.trim().equals(""))) {
				
				model.addObject("error","Please enter required data.");
				
			}
			else {
				
				User user = new User();
				user.setApplicationNumber(applicationNumber);
				user.setPassword(password);
				user.setExamCenterCode(examCenter);
				
				List<String> userNames = examCenterDao.checkUserLogin(user);
				
				if(userNames.size() == 0 ) {
					
					model.addObject("error","Invalid Application Number or Password.");
					
				}
				else {
					
					examCenterDao.updateExamCenter(user);
					List<String> uecns = examCenterDao.checkExamCenter(user);
					model = new ModelAndView("exam_center_update/UpdateECsuccess");
					model.addObject("message", "Dear "+userNames.get(0)+" , Your examination center has been updated to "+uecns.get(0)+". ");
					return model;
				}
			}
			
			List<CommonBean> examCenters = examCenterDao.getExamCenterList();
			model.addObject("examCenters", examCenters);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			model.addObject("error", "There is an exception in updating examination center.");
		}
		return model;
	}

}
