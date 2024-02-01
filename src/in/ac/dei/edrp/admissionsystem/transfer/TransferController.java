package in.ac.dei.edrp.admissionsystem.transfer;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ac.dei.edrp.admissionsystem.account.AccountController;
import in.ac.dei.edrp.admissionsystem.account.AccountDAO;
import in.ac.dei.edrp.admissionsystem.application.ApplicationDAO;
import in.ac.dei.edrp.admissionsystem.common.CommonBean;
import in.ac.dei.edrp.admissionsystem.common.beans.Applicant;
import in.ac.dei.edrp.admissionsystem.common.beans.Program;
import in.ac.dei.edrp.admissionsystem.common.beans.ProgramWiseDetail;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class TransferController extends MultiActionController
{
	public static Logger logObj=Logger.getLogger(AccountController.class);
	
	private TransferDao transferDao;
		
	
	public void setTransferDao(TransferDao transferDao)
	{
		this.transferDao = transferDao;
	}
	
	public ModelAndView getApplyTransferPage(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView("transferApplication/ApplyTransferApplication");
		List<CommonBean> appliedPrograms = new ArrayList<CommonBean>();
		List<CommonBean> transferPrograms = new ArrayList<CommonBean>();
		model.addObject("appliedPrograms", appliedPrograms);
		model.addObject("transferPrograms", transferPrograms);
		return model;
	}
	
	public ModelAndView getAppliedPrograms(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView model = null;
		
		String applicationNumber = request.getParameter("applicationNumber");
		String dd = request.getParameter("dd");
		String mm = request.getParameter("mm");
		String yyyy = request.getParameter("yyyy");
		String dob = yyyy+"-"+mm+"-"+dd;
		
		if((applicationNumber == null || applicationNumber.trim().equals("")) || (dd == null || dd.trim().equals(""))|| (mm == null || mm.trim().equals(""))||(yyyy==null || yyyy.trim().equals("")))
		{
			model = new ModelAndView("transferApplication/ApplyTransferApplication");
			model.addObject("error", "PLEASE ENTER ALL REQUIRED DATA.");
		}
		else
		{
			Applicant applicant = new Applicant();
			applicant.setApplicationNumber(applicationNumber);
			applicant.setDob(dob);
			
			try
			{
				List<CommonBean> appliedPrograms = transferDao.getAppliedPrograms(applicant);
				List<CommonBean> transferPrograms = transferDao.getTransferPrograms();
				if(appliedPrograms.size()>0)
				{
					model = new ModelAndView("transferApplication/ApplyTransferApplication");
					model.addObject("applicationNumber", applicationNumber);
					model.addObject("dd", dd);
					model.addObject("mm", mm);
					model.addObject("yyyy", yyyy);
					model.addObject("appliedPrograms", appliedPrograms);
					model.addObject("transferPrograms", transferPrograms);
					model.addObject("showProgramsOption", "yes");
				}
				else
				{
					model = new ModelAndView("transferApplication/ApplyTransferApplication");
					model.addObject("error", "THERE IS NO RECORD WITH THESE DETAILS.");
				}
			}
			catch(Exception ex)
			{
				System.out.print(ex);
				ex.printStackTrace();
			}
			
		}
		
		return model;
	}
	
	public ModelAndView applyTransferApplication(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView model = getApplyTransferPage(request, response);
		
		String applicationNumber = request.getParameter("appNumber");
		String fromProgram = request.getParameter("appliedProgram");
		String toProgram = request.getParameter("transferProgram");
		
		String error = "";
				
		if(applicationNumber == null || applicationNumber.trim().equalsIgnoreCase(""))
		{
			error = "PLEASE ENTER APPLICATION NUMBER.";
			model.addObject("error", error);
			return model;
		}
		
		if(fromProgram == null || fromProgram.trim().equalsIgnoreCase(""))
		{
			error = "PLEASE SELECT TRANSFER FROM-PROGRAM.";
			model.addObject("error", error);
			return model;
		}
		
		if(toProgram == null || toProgram.trim().equalsIgnoreCase(""))
		{
			error = "PLEASE SELECT TRANSFER TO-PROGRAM.";
			model.addObject("error", error);
			return model;
		}
		
		try
		{
			CommonBean inputObject = new CommonBean();
			inputObject.setCode(fromProgram);
			inputObject.setDescription(toProgram);
			inputObject.setOtherProperty1(applicationNumber);
			
			List<CommonBean> duplicateList = transferDao.checkApplication(inputObject);
			if(duplicateList.size() > 0)
			{
				error = "Dear "+duplicateList.get(0).getOtherProperty1()+ ", You have already applied for transfer application from "+duplicateList.get(0).getCode()+" to "+duplicateList.get(0).getDescription();
				model.addObject("error", error);
			}
			else
			{
				transferDao.applyTransferApplications(inputObject);
				List<CommonBean> outputList = transferDao.checkApplication(inputObject);
				if(outputList.size() > 0)
				{
					String message = "Dear "+outputList.get(0).getOtherProperty1()+ ", You have successfully applied for transfer application from "+outputList.get(0).getCode()+" to "+outputList.get(0).getDescription();
					model = new ModelAndView("transferApplication/SuccessApplyTransfer", "message", message);
				}
				else
				{
					error = "There is an error in transfering application.";
					model.addObject("error", error);
				}
			}
		}
		catch(Exception ex)
		{
			System.out.print(ex);
			ex.printStackTrace();
		}
		return model;
	}

}
