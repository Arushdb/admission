/**
 * Class Name	:	AccountController
 * Author		:	Arjun Singh Chauhan
 * Purpose		: 	To handle the activity related to account management
 */
package in.ac.dei.edrp.admissionsystem.account;

import in.ac.dei.edrp.admissionsystem.application.ApplicationDAO;
import in.ac.dei.edrp.admissionsystem.common.CommonBean;
import in.ac.dei.edrp.admissionsystem.common.ProgramCounter;
import in.ac.dei.edrp.admissionsystem.common.User;
import in.ac.dei.edrp.admissionsystem.common.beans.Applicant;
import in.ac.dei.edrp.admissionsystem.common.beans.ProgramWiseDetail;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nl.captcha.Captcha;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class AccountController extends MultiActionController
{
	public static Logger logObj=Logger.getLogger(AccountController.class);
	private AccountDAO accountDAO;
	private ApplicationDAO applicationDAO;
		
	public void setAccountDAO(AccountDAO accountDAO)
	{
		this.accountDAO = accountDAO;
	}
	
	public void setApplicationDAO(ApplicationDAO applicationDAO)
	{
		this.applicationDAO = applicationDAO;
	}
	
	public ModelAndView getApplicantLoginScreen(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		ModelAndView model = new ModelAndView("account/ApplicantLogin");
		return model;
	}


	public ModelAndView getCreateAccountPage(HttpServletRequest request,HttpServletResponse response)
	{
		logObj.info("getCreateAccountPage Starts");
		ModelAndView model = null;
		String error = null;
		try
		{
			String universityID = getServletContext().getInitParameter("universityID");
			List<CommonBean> categories = accountDAO.getCategories(universityID);
			
			if(categories == null || categories.size()<=0)
			{
				model = new ModelAndView("errorPages/Error");
				error = "THERE IS NO CATEGORIES SETUP.";
				model.addObject("error", error);
				return model;
			}
			
			List<CommonBean> sQuestions = accountDAO.getSQuestions();
			
			if(sQuestions == null || sQuestions.size()<=0)
			{
				model = new ModelAndView("errorPages/Error");
				error = "THERE IS NO SECURITY QUESTION SETUP.";
				model.addObject("error", error);
				return model;
			}
			
			model = new ModelAndView("account/AccountCreate");
			model.addObject("categories", categories);
			model.addObject("sQuestions", sQuestions);

		}
		catch(Exception ex)
		{
			logObj.error("Exception in getCreateAccountPage : "+ex);
			model = new ModelAndView("errorPages/ExceptionPage");
			model.addObject("exceptionSource", "THERE IS SOME PROBLEM IN GETTING PAGE FOR ACCOUNT CREATION");
		}
		
		logObj.info("getCreateAccountPage Ends.");
		return model;
		
	}

	public ModelAndView createAccount(HttpServletRequest request,HttpServletResponse response)
	{
		String message = null;
		ModelAndView model = null;
		
		try
		{
			String userName = request.getParameter("applicantName");
			String emailID = request.getParameter("emailID");
			String dd = request.getParameter("dd");
			String mm = request.getParameter("mm");
			String yyyy = request.getParameter("yyyy");
			String category = request.getParameter("category");
			String gender = request.getParameter("gender");
			String aadharNumber = request.getParameter("aadharNumber");
			String country = request.getParameter("country");
			String phoneNo = request.getParameter("phoneNo");
			String question = request.getParameter("question");
			String answer = request.getParameter("answer");
			String captchaValue = request.getParameter("captchaValue");
			
			String otherPhoneNo = request.getParameter("altPhoneNo");
			String secondaryEmailID = request.getParameter("altEmailID");
					
			Applicant applicant = new Applicant();
			applicant.setApplicantName(userName);
			applicant.setPrimaryEmailID(emailID);
			applicant.setDd(dd);
			applicant.setMm(mm);
			applicant.setYyyy(yyyy);
			applicant.setDob(yyyy+mm+dd);
			applicant.setCategory(category);
			applicant.setGender(gender);
			applicant.setAadharNumber(aadharNumber);
			applicant.setCorCountry(country);
			applicant.setPerCountry(country);
			applicant.setHomePhone(phoneNo);
			applicant.setUniversityID(getServletContext().getInitParameter("universityID"));
			applicant.setQuestion(question);
			applicant.setAnswer(answer);
			
			applicant.setOtherPhone(otherPhoneNo);
			applicant.setSecondaryEmailID(secondaryEmailID);
			
			
			Captcha captcha = (Captcha) request.getSession().getAttribute(Captcha.NAME);
			List<String> errors = accountDAO.validateAccountDetails(applicant);
			
			if(captcha == null || !captcha.isCorrect(captchaValue))
			{
				errors.add("Please enter correct captch code.");
			}
			
			if(errors.size() > 0)
			{
				List<CommonBean> catgories = accountDAO.getCategories(applicant.getUniversityID());
				List<CommonBean> sQuestions = accountDAO.getSQuestions();
				model = new ModelAndView("account/AccountCreate");
				model.addObject("applicant",applicant);
				model.addObject("errors",errors);
				model.addObject("categories", catgories);
				model.addObject("sQuestions", sQuestions);
				return model;
			}
			else
			{
				/**
				 * Code Starts : Added by Arjun to prevent multiple accounts creation for same details
				 * Date : 18-04-2016
				 */
				List<User> lastCreatedAccounts = accountDAO.getAccountsWithSameDetails(applicant);
				if(lastCreatedAccounts.size()>0)
				{
					model = new ModelAndView("account/LastCreatedAccounts");
					model.addObject("users",lastCreatedAccounts);
					model.addObject("userName",lastCreatedAccounts.get(0).getApplicantName());
					return model;
				}
				/**
				 * Code Ends : Added by Arjun to prevent multiple accounts creation for same details
				 * Date : 18-04-2016
				 */
				/**String password = accountDAO.generatePassword(); Commented by Arjun on 28-05-2019
				applicant.setPassword(password);**/
				
				List<Applicant> applicantList = accountDAO.createAccount(applicant);
				if(applicantList.size()==1)
				{
					String mailID = applicantList.get(0).getPrimaryEmailID();
					String applicantName = applicantList.get(0).getApplicantName();
					String appNumber = applicantList.get(0).getApplicationNumber();
					//String mailSentFlag = accountDAO.sendMailtoUser(applicantName, password, mailID, appNumber); //Commented by Arjun on 18-04-2016
					String mailSentFlag = "Y";//Added by Arjun on 18-04-2016
					/**
					message = "<div>Dear "+applicantName+",<br/>Your account has been created successfully. Your account details are as follows :-<br/>";
					message = message + "<table>";
					message = message +	"<tr><td><b>User Name/Application Number</b></td><td>: "+appNumber+"</td></tr>";
					message = message +	"<tr><td><b>Password</b></td><td>: "+password+"</td></tr>";
					message = message +"</table>";
					message = message +"<br/>An Email has also been sent to your Email Address with the details given above.<br/>Please use these details to login and apply for the course.</div>";
					message = message +"<br/>Please Note Down this Application Number and Password for Future References.";
					**/
					//applicantList.get(0).setPassword(password);Commented by Arjun on 28-05-2019
					String emailDependancy = getServletContext().getInitParameter("emailDependancy");
					if(emailDependancy == null || emailDependancy.trim().equalsIgnoreCase("N"))
					{
						model = new ModelAndView("account/AccountSuccess");
						model.addObject("applicant",applicantList.get(0));
						model.addObject("mailSent", "N");
						return model;
					}
					else
					{
						if(mailSentFlag=="success")
						{
							model = new ModelAndView("account/AccountSuccess");
							model.addObject("applicant",applicantList.get(0));
							model.addObject("mailSent", "Y");
							return model;					
						}
						else
						{
							message = "There is some problem in Sending Email to your Email Address.";
							model = new ModelAndView("errorPages/Error");
							model.addObject("error",message);
							return model;					
						}
						
					}
					
				}
				else if(applicantList.size()>1)
				{
					message = "Duplicate Records Exist.";
				}
				else
				{
					message = "There is Failure in Account Creation.";
				}

			}
		}
		catch(Exception ex)
		{
			logObj.error("Exception in createAccount : "+ex);
			model = new ModelAndView("errorPages/ExceptionPage");
			model.addObject("exceptionSource", "THERE IS SOME PROBLEM IN ACCOUNT CREATION.");
		}
		
		return new ModelAndView("errorPages/Error", "error", message);
		
	}

	
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView model = null;
		
		try
		{
			String applicationNumber =  request.getParameter("applicationNumber");
			String password = request.getParameter("password");
			String universityID = getServletContext().getInitParameter("universityID");
			
			Applicant applicant = new Applicant();
			applicant.setApplicationNumber(applicationNumber);
			applicant.setPassword(password);
			applicant.setUniversityID(universityID);
				
			List<Applicant> applicantList = accountDAO.getUserList(applicant);
			int noOfUser = applicantList.size();
			
			if(noOfUser==1)
			{
				HttpSession session = request.getSession(false);
				if(session==null || session.getAttribute("applicant") == null)
				{
					session = request.getSession(true);
				}
				else
				{
					String appNumToBeCompared = ((Applicant)session.getAttribute("applicant")).getApplicationNumber();
					if(!(appNumToBeCompared.trim().equalsIgnoreCase(applicationNumber)))
					{
						String message = "A SESSION IS ALREADY ACTIVE FOR THIS BROWSER.PLEASE USE DIFFERENT BROWSER OR TRY AFTER "+session.getMaxInactiveInterval()/60+" MINUTES.";
	               		model = new ModelAndView("errorPages/Error", "error", message);
						return model;
					}
					else
					{
						session = request.getSession(true);
					}
					
				}
				
				applicant = applicationDAO.getApplicantDetails(applicantList.get(0));
				applicant.setDd(String.format("%02d", Integer.parseInt(applicant.getDd())));
				applicant.setMm(String.format("%02d", Integer.parseInt(applicant.getMm())));
				String applicationStatus = applicant.getApplicationStatus();
				
				session.setAttribute("applicationNumber", applicationNumber);
				session.setAttribute("applicationStatus", applicationStatus);
				session.setAttribute("tabStatus", applicant.getTabStatus());
				session.setAttribute("applicant", applicant);
				/**
					if(applicant.getPhaseOptionToShow() == null || applicant.getPhaseOptionToShow().trim().equalsIgnoreCase("N"))
					{**/
						model = new ModelAndView("application/TabController", "applicant", applicant);
					/**}
					else
					{
						model = new ModelAndView("account/PhaseOptions", "applicant", applicant);
					}**/
			
				return model;
			}
			else 
			{	
				String message = null;
				if(noOfUser==0)
				{
					message = "USER NAME OR PASSWORD IS INCORRECT.";
				}
				else
				{
					message = "DUPLICATE USER FOUND.";
				}
				return new ModelAndView("account/ApplicantLogin", "message", message);
			}
		}
		catch(Exception ex)
		{
			logObj.error("Exception in AccountController:login : " + ex);
			model = new ModelAndView("errorPages/ExceptionPage");
			model.addObject("exceptionSource", "THERE IS SOME PROBLEM IN LOGIN FOR YOUR APPLICATION NUMBER.");
			return model;
		}
		
		
	}
	
	public ModelAndView getUserPage(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		request.getSession().setAttribute("backButtonClicked","Y");
		return new ModelAndView("application/TabController");
	}
	
	public ModelAndView getForgotPasswordPage(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		List<CommonBean> sQuestions = accountDAO.getSQuestions();
		ModelAndView model = new ModelAndView("account/ForgotPassword");
		model.addObject("sQuestions", sQuestions);
		return model;
	}
		
	public ModelAndView resendPassword(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		
		String applicationNumber = request.getParameter("applicationNumber");
		String phoneNo = request.getParameter("phoneNo");
		String dd = request.getParameter("dd");
		String mm = request.getParameter("mm");
		String yyyy = request.getParameter("yyyy");
		
		
		User user = new User();
		user.setContactNumber(phoneNo);
		user.setDob(yyyy+"-"+mm+"-"+dd);
		user.setDd(dd);
		user.setMm(mm);
		user.setYyyy(yyyy);
		user.setUniversityID(getServletContext().getInitParameter("universityID"));
		user.setApplicationNumber(applicationNumber);
		
		List<User> users = accountDAO.checkApplication(user);
		ModelAndView model = null;
		
		//String accountDtlRow = "";
		
		
			if(users.size()<=0)
			{
				
				model = new ModelAndView("account/ForgotPassword");
				model.addObject("user", user);
				model.addObject("message", "There is no record with these details.");
				
			}
			else
			{
				for(int i = 0 ; i < users.size(); i++)
				{
					user.setApplicationNumber(users.get(i).getApplicationNumber());
					//user.setPassword(accountDAO.generatePassword()); Commented by Arjun on 28-05-2019
					user.setPassword(accountDAO.getFeePassword(users.get(i).getApplicationNumber()));//Added by Arjun on 28-05-2019
					String userName = accountDAO.updatePassword(user);
					user.setApplicantName(userName);
					user.setEmailID(users.get(i).getEmailID());
					
					String emailDependancy = getServletContext().getInitParameter("emailDependancy");
					
					if(emailDependancy == null || emailDependancy.trim().equalsIgnoreCase("Y"))
					{
						accountDAO.resendMailtoUser(userName, user.getPassword(), user.getEmailID(), user.getApplicationNumber());
					}
					
					users.get(i).setPassword(user.getPassword());
					users.get(i).setApplicantName(userName);
					//accountDtlRow = accountDtlRow+"<tr><td>"+user.getApplicationNumber()+"</td><td>: "+user.getPassword()+"</td></tr>";			
					
					
				}
				
				
				model = new ModelAndView("account/ResendPasswordSuccess");
				model.addObject("users",users);
				model.addObject("userName", users.get(0).getApplicantName());
			}
			
			
			return model;
		
	}
	
	public ModelAndView openPage(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession(false);
		if(session==null)
		{
			return new ModelAndView("errorPages/SessionExpired","message","Your Session has been expired.");
		}
		
		String tabToOpen = request.getParameter("tabToOpen");
		ModelAndView model = new ModelAndView("application/TabController");
		model.addObject("tabToOpen", tabToOpen);
		return model;
	}
	
	
	public ModelAndView closeSession(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		if(session==null)
		{
			out.println("Your Session has been expired.");
			//return new ModelAndView("errorPages/SessionExpired","message","Your Session has been expired.");
		}
		else
		{
			session.invalidate();
			out.println("You are log out of the session.");
			//String message = "<font color='green'>You have successfully Logged Out.</font>";
			//return new ModelAndView("account/ApplicantLogin","message",message);
			
		}	
		System.out.println("SESSION IS CLOSED.");
		return null;
	}
	
	public ModelAndView getDownloadAdmitCardScreen(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return new ModelAndView("account/DownloadAdmitCard");
	}
	public ModelAndView getProgramAdmitCardDetails(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		ModelAndView model = null;
		
		String applicationNumber = request.getParameter("applicationNumber");
		//String dd = String.format("%02d", Integer.parseInt(request.getParameter("dd")));
		//String mm = String.format("%02d", Integer.parseInt(request.getParameter("mm")));
		String dd = request.getParameter("dd");
		String mm = request.getParameter("mm");
		String yyyy = request.getParameter("yyyy");
		String dob = yyyy+"-"+mm+"-"+dd;
		
		if((applicationNumber == null || applicationNumber.trim().equals("")) || (dd == null || dd.trim().equals(""))|| (mm == null || mm.trim().equals(""))||(yyyy==null || yyyy.trim().equals("")))
		{
			model = new ModelAndView("account/DownloadAdmitCard");
			model.addObject("error", "PLEASE ENTER ALL REQUIRED DATA.");
		}
		else
		{
			Applicant applicant = new Applicant();
			applicant.setApplicationNumber(applicationNumber);
			applicant.setDob(dob);
			
			List<ProgramWiseDetail> programAdmitCardDetails = accountDAO.getProgramAdmitCardDetails(applicant);
			if(programAdmitCardDetails.size()>0)
			{
				
				model = new ModelAndView("account/DownloadAdmitCard");
				model.addObject("applicationNumber", applicationNumber);
				model.addObject("dd", dd);
				model.addObject("mm", mm);
				model.addObject("yyyy", yyyy);
				model.addObject("programWiseDetails", programAdmitCardDetails);
			}
			else
			{
				model = new ModelAndView("account/DownloadAdmitCard");
				model.addObject("error", "THERE IS NO RECORD WITH THESE DETAILS.");
			}
		}
		
		return model;
	}
	public ModelAndView downloadAdmitCard(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String admitCardPath = request.getParameter("admitCardPath");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
			
		try
		{
			    String filepath=admitCardPath+".pdf";
			    File file = new File(filepath);
			    if (!file.exists())
			    try 
			    {
			    	throw new FileNotFoundException(file.getAbsolutePath());
			    } 
			    catch (FileNotFoundException e) 
			    {
			    	logObj.error(" "+e);
					e.printStackTrace();
				}

			     response.setHeader("Content-Type", "application/pdf");
			     response.setHeader("Content-Length", String.valueOf(file.length()));
			     response.setHeader("Content-disposition", "attachment;filename=\"" + filepath + "\"");

			     bis = new BufferedInputStream(new FileInputStream(file));
			     bos = new BufferedOutputStream(response.getOutputStream());
			     byte[] buf = new byte[1024];
			     while (true) {
			         int length = bis.read(buf);
			         if (length == -1)
			             break;

			         bos.write(buf, 0, length);
			     }
			     bos.flush();
			     bos.close();
			     bis.close();
		}
		catch(Exception ex)
		{
			bos.flush();
		    bos.close();
		    bis.close();
		}
		
		return null;
	}
	
	public ModelAndView getPhase1(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession(false);
		if(session==null)
		{
			return new ModelAndView("errorPages/SessionExpired","message","Your Session has been expired.");
		}
		
		Applicant applicant = (Applicant) session.getAttribute("applicant");
		ModelAndView model = new ModelAndView("application/TabController", "applicant", applicant);
		return model;
	}
	
	public ModelAndView getPhase2(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession(false);
		if(session==null)
		{
			return new ModelAndView("errorPages/SessionExpired","message","Your Session has been expired.");
		}
		
		Applicant applicant = (Applicant) session.getAttribute("applicant");
		
		if(applicant.getApplicationStatus() == null || (!(applicant.getApplicationStatus().trim().equalsIgnoreCase("D"))))
		{
			ModelAndView model = new ModelAndView("account/PhaseOptions","message","PLEASE COMPLETE PART-1 OF ADMISSION.");
			return model;
		}
		
		applicant = applicationDAO.getApplicationForm(applicant);
		
		String globalEditing = applicant.getGlobalEditing();
		String forceEditing = applicant.getForceEditing();
		
		List<String> researchPrograms = applicationDAO.getResearchPrograms(applicant.getApplicationNumber());
		String jspToReturn = researchPrograms.size()>0?"application/AcademicPerformancePHD":"application/AcademicPerformance";
		String viewModeJspToReturn = researchPrograms.size()>0?"application/AcademicPerformanceViewMode":"application/AcademicPerformancePhdViewMode";
		
		ModelAndView model = null ;
		
		if(globalEditing == null || globalEditing.trim().equalsIgnoreCase("N"))
		{
			if(forceEditing == null || forceEditing.trim().equalsIgnoreCase("N"))
			{
				if(applicant.getPhase2Edited() == null)
				{
					model = new ModelAndView("errorPages/Error", "error", "YOU CANNOT UPDATE YOUR PART-2 OF ADMISSION PROCESS NOW.");
					session.invalidate();
				}
				else
				{
					model = new ModelAndView(viewModeJspToReturn, "applicant", applicant);
				}
				
			}
			else
			{
				model = new ModelAndView(jspToReturn, "applicant", applicant);
			}
			
		}
		else
		{
			model = new ModelAndView(jspToReturn, "applicant", applicant);
		}
		
		return model;
	}
	
	public ModelAndView getApplicationsCount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int totalApplicationsCount = 0;
		int feeVerifiedAppsCount = 0;
		int feeNotVerifiedAppsCount = 0;
		
		List<ProgramCounter> programsList = accountDAO.getApplicationsCount();
		for(ProgramCounter program : programsList)
		{
			totalApplicationsCount = program.getTotalApplicationsCount() + totalApplicationsCount;
			feeVerifiedAppsCount = program.getFeeVerifiedAppsCount() + feeVerifiedAppsCount;
			feeNotVerifiedAppsCount = program.getFeeNotVerifiedAppsCount() + feeNotVerifiedAppsCount;
			
		}
		ModelAndView model = new ModelAndView("account/ApplicationCount");
		model.addObject("programs", programsList);
		model.addObject("totalApplicationsCount", totalApplicationsCount);
		model.addObject("feeVerifiedAppsCount", feeVerifiedAppsCount);
		model.addObject("feeNotVerifiedAppsCount", feeNotVerifiedAppsCount);
		return model;
	}

}
