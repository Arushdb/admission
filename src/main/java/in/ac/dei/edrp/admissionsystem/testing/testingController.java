package in.ac.dei.edrp.admissionsystem.testing;
import in.ac.dei.edrp.admissionsystem.account.*;
import in.ac.dei.edrp.admissionsystem.addMoreProgram.addMoreProgramDao;
import in.ac.dei.edrp.admissionsystem.application.ApplicationDAO;
import in.ac.dei.edrp.admissionsystem.common.CommonBean;
import in.ac.dei.edrp.admissionsystem.common.beans.Applicant;
import in.ac.dei.edrp.admissionsystem.common.beans.StudentProgramPaper;
//import in.ac.dei.edrp.admissionsystem.testing2.testing2Controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class testingController extends MultiActionController
{
//private TestingDAO testingDAO;
//	
//	public void setTestingDAO(TestingDAO testingDAO)
//	{
//		this.testingDAO = testingDAO;
//		System.out.println("testingDAO:"+testingDAO);
//	}

private AccountDAO accountDAO;
	
	public void setAccountDAO(AccountDAO accountDAO)
	{
		this.accountDAO = accountDAO;
	}
	
	private addMoreProgramDao addMoreProgramDao;
	public void setAddMoreProgramDao(addMoreProgramDao addMoreProgramDao) {
		this.addMoreProgramDao = addMoreProgramDao;
	}


	//public void createAccount(HttpServletRequest request,HttpServletResponse response) throws Exception
	public void createAccount(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		
		System.out.println("atul don");
		String userName = request.getParameter("testname");
		String num = request.getParameter("num");
		System.out.println("number value"+num);
		int aa = Integer.valueOf(num);
		//String userName = "Long Shree";
		for (int i=0;i<aa;i++)
		{
			String a=Integer.toString(i);
			String  userName1= userName+a;
			String category= "GN"+a;
			String gender= "M";
			String country="Indian"+a;
			String state ="UP"+a;
			String city ="Agra"+a;
			String phoneNo="999"+a;
			String emailID=userName1+"@gmail.com";
			String question="ques"+a;
			String answer="ans"+a;
			Applicant applicant = new Applicant();
			applicant.setApplicantName(userName1);
			applicant.setPrimaryEmailID(emailID);
			applicant.setDob("1987-06-21");
			applicant.setCategory(category);
			applicant.setGender(gender);
			applicant.setCorCountry(country);
			applicant.setCorState(state);
			applicant.setCorCity(city);
			applicant.setHomePhone(phoneNo);
			applicant.setUniversityID("0001");
			applicant.setQuestion(question);
			applicant.setAnswer(answer);
			
			String password = accountDAO.generatePassword();
			applicant.setPassword(password);
			System.out.println("ho gaya"+applicant.getPassword());
			List<Applicant> applicantList = accountDAO.createAccount(applicant);
			String message = null;
			System.out.println("applicantList size"+applicantList.size());
			if(applicantList.size()==1)
			{
				String mailID = applicantList.get(0).getPrimaryEmailID();
				String applicantName = applicantList.get(0).getApplicantName();
				String appNumber = applicantList.get(0).getApplicantName();
				System.out.println("System.out.println(applicant.getApplicationNumber())"+applicant.getApplicationNumber());
				System.out.println("applicant.getPassword())"+applicant.getPassword());
				System.out.println("applicant.getUniversityID())"+applicant.getUniversityID());
				System.out.println("applicant.getApplicantName())"+applicant.getApplicantName());
				
				login(applicant.getApplicationNumber(),applicant.getPassword(),applicant.getUniversityID(),applicant.getApplicantName());
//	String mailSentFlag = accountDAO.sendMailtoUser(applicantName, password, mailID, appNumber);
//				if(mailSentFlag=="success")
//				{
//					message="";
//				}
//				else
//				{
//					message = "There is some problem in Sending Email to your Email Address.";
//				}

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
	
	private void login(String applicationNumber,String password, String universityID,String ApplicantName)
	{
		Applicant applicant = new Applicant();
		applicant.setApplicationNumber(applicationNumber);
		applicant.setPassword(password);
		applicant.setUniversityID(universityID);
		
		List<Applicant> applicantList = accountDAO.getUserList(applicant);
		int noOfUser = applicantList.size();
		System.out.println("Login applicantList"+applicantList.size());
		if(noOfUser==1)
		{
//			HttpSession session = request.getSession(false);
//			if(session==null)
//			{
//				session = request.getSession(true);
//			}
//			else
//			{
//				session.invalidate();
//				session = request.getSession(true);
//			}
			applicant = applicantList.get(0);
			applicant.setDd(String.format("%02d", Integer.parseInt(applicant.getDd())));
			applicant.setMm(String.format("%02d", Integer.parseInt(applicant.getMm())));
			String applicationStatus = applicant.getApplicationStatus();
			
//			session.setAttribute("applicationNumber", applicationNumber);
//			session.setAttribute("applicationStatus", applicationStatus);
//			session.setAttribute("tabStatus", applicant.getTabStatus());
//			session.setAttribute("applicant", applicant);
			List<Applicant> list1=null;
			//testing2Controller testing_applicant = new testing2Controller();
			System.out.println("ApplicantName"+ApplicantName);
			System.out.println("applicantList.get(0).getApplicationNumber()"+applicantList.get(0).getApplicationNumber());
			System.out.println("applicantList.get(0).getUserID("+applicantList.get(0).getUserID());
			System.out.println("applicantList.get(0).getApplicationStatus()"+applicantList.get(0).getApplicationStatus());
			System.out.println("applicantList.get(0).getTabStatus()"+applicantList.get(0).getTabStatus());
			List<Applicant> applicants=null;
			//applicants = accountDAO.savePersonalDetailstesting(applicant);
			
			list1=savePersonalDetails(ApplicantName,applicantList.get(0).getApplicationNumber(),applicantList.get(0).getUserID(),applicantList.get(0).getApplicationStatus(),applicantList.get(0).getTabStatus());
			//System.out.println("list1"+list1.size());
			List<String> programList =  new ArrayList<String>();
			List<String> optedPaperList = new ArrayList<String>();
			
			
		}
		else 
		{	
			String message = null;
			if(noOfUser==0)
			{
				message = "User Name or Password is incorrect";
			}
			else
			{
				message = "Duplicate User Found";
			}
			
		}
		
	}
	
	public List<Applicant> savePersonalDetails(String ApplicantName,String appnum,String UserId,String appsta,String tabsta)
	{
		List<Applicant> applicants=null;
		try
		{
		Applicant applicant = new Applicant();
		applicant.setApplicantName(ApplicantName);
		applicant.setFatherName(ApplicantName+"Father");
		applicant.setMotherName(ApplicantName+"Mother");
		applicant.setDob("1987-06-21");
		applicant.setCategory("GN");
		applicant.setNationality("Indina");
		applicant.setGender("M");
		applicant.setReligion("Hindu");
		applicant.setPwd("N");
		applicant.setPrimaryEmailID(ApplicantName+"@gmail.com");
		applicant.setHomePhone("9999999999");
		applicant.setMaritalStatus("UN");
		applicant.setCorCountry("IndiaCOR");
		applicant.setCorState("UP");
		applicant.setCorCity("Agra");
		applicant.setCorAddressL1("COR Line1");
		applicant.setCorAddressL2("COR Line2");
		applicant.setCorPincode("282005");
		applicant.setApplicationNumber(appnum);
		applicant.setUserID(UserId);
		applicant.setApplicationStatus(appsta);
		applicant.setTabStatus(tabsta);
		applicant.setUniversityID("0001");
		System.out.println("Yaha aa gaya" +applicant.getApplicantName());
		
		//applicants = applicationDAO.savePersonalDetails(applicant);
		applicants = accountDAO.savePersonalDetailstesting(applicant);
		savePaperOptions(applicants);
		}
		catch(Exception e)
		{
			System.out.println("error-"+e);
		}
		
		return applicants;
	}
	
	public void savePaperOptions(List<Applicant> applicants)
	{
		Applicant applicant = new Applicant();
//		List<String> programs=null;
//		programs.add("0001090");
//		List<String> papers = null;
//		papers.add("07");
//		papers.add("06");
//		papers.add("05");
//		applicant.setPrograms(programs);
		List<StudentProgramPaper> programPapers = new ArrayList<StudentProgramPaper>();
		StudentProgramPaper stdProgramPaper =   new StudentProgramPaper();
		stdProgramPaper.setProgramID("0001090");
		stdProgramPaper.setMainGroup("A");
		stdProgramPaper.setGrouping("G1");
		stdProgramPaper.setPaperCode("07");
		programPapers.add(stdProgramPaper);
		//applicant.setPrograms(programPapers)
		//applicant.getApplicationForm().getProgramWiseDetails().get(0).setStudentProgramPapers(programPapers);
		applicant.setApplicationNumber(applicants.get(0).getApplicationNumber());
		applicant.setUserID(applicants.get(0).getUserID());
		applicant.setUniversityID("0001");
		accountDAO.savePaperOptionstesting(applicant);
		//applicant.setApplicationForm
		//applicant.getApplicationForm().getProgramWiseDetails().size();
		System.out.println("applicant.getApplicationForm().getProgramWiseDetails().size();"+applicant);
	}
/**	
	public ModelAndView getAddProgramPage(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView("addProgramToApplication/AddProgram");
		List<CommonBean> appliedPrograms = new ArrayList<CommonBean>();
		List<CommonBean> transferPrograms = new ArrayList<CommonBean>();
		model.addObject("appliedPrograms", appliedPrograms);
		model.addObject("transferPrograms", transferPrograms);
		return model;
	}
**/	
	public ModelAndView getAppliedPrograms(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView model = null;
		
		String applicationNumber = request.getParameter("applicationNumber");
		String dd = request.getParameter("dd");
		String mm = request.getParameter("mm");
		String yyyy = request.getParameter("yyyy");
		String dob = yyyy+"-"+mm+"-"+dd;
		String password = request.getParameter("password");
		
		if((applicationNumber == null || applicationNumber.trim().equals("")) || (dd == null || dd.trim().equals(""))|| (mm == null || mm.trim().equals(""))||(yyyy==null || yyyy.trim().equals(""))
				|| (password == null || password.trim().equals("")))
		{
			model = new ModelAndView("addProgramToApplication/ApplyTransferApplication");
			model.addObject("error", "PLEASE ENTER ALL REQUIRED DATA.");
		}
		else
		{
			Applicant applicant = new Applicant();
			applicant.setApplicationNumber(applicationNumber);
			applicant.setDob(dob);
			applicant.setPassword(password);
			
			try
			{
				List<CommonBean> appliedPrograms = addMoreProgramDao.getAppliedPrograms(applicant);
				List<CommonBean> transferPrograms = addMoreProgramDao.getTransferPrograms(applicant);
				System.out.println(transferPrograms.size()+" is size of transferPrograms");
				if(appliedPrograms.size()>0)
				{
					int count=0;
					for(int i=0;i<appliedPrograms.size();i++){
						if(!(appliedPrograms.get(i).getUniversityID().equalsIgnoreCase("N"))){
							count++;
						}
					}
					System.out.println("Added program count is "+count);
					if(count>=2){
						model = new ModelAndView("addProgramToApplication/ApplyTransferApplication");
						model.addObject("error", "YOU HAVE ALREADY ADDED EXTRA PROGRAMS TO YOUR APPLICATION");	
					}
					else if(count==1){
					
					model = new ModelAndView("addProgramToApplication/ApplyTransferApplication");
					model.addObject("applicationNumber", applicationNumber);
					model.addObject("dd", dd);
					model.addObject("mm", mm);
					model.addObject("yyyy", yyyy);
					model.addObject("appliedPrograms", appliedPrograms);
					model.addObject("transferPrograms", transferPrograms);
					model.addObject("showProgramsOption", "yes");
					model.addObject("optionCount", "1");
					}else{
						model = new ModelAndView("addProgramToApplication/ApplyTransferApplication");
						model.addObject("applicationNumber", applicationNumber);
						model.addObject("dd", dd);
						model.addObject("mm", mm);
						model.addObject("yyyy", yyyy);
						model.addObject("appliedPrograms", appliedPrograms);
						model.addObject("transferPrograms", transferPrograms);
						model.addObject("showProgramsOption", "yes");
						model.addObject("optionCount", "2");
					}
				}
				else
				{
					model = new ModelAndView("addProgramToApplication/ApplyTransferApplication");
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


	public ModelAndView applyMorePrograms(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView("addProgramToApplication/SuccessAddProgram");
		
		String applicationNumber = request.getParameter("appNumber");
//		String fromProgram = request.getParameter("appliedProgram");
		String toProgram1 = request.getParameter("transferProgram1");
		String toProgram2 = request.getParameter("transferProgram2");
		int count=0;
		String flag="";
		String error = "";
				System.out.println("toProgram1 is "+toProgram1+"-");
				System.out.println("toProgram2 is "+toProgram2+"-");
		if(applicationNumber == null || applicationNumber.trim().equalsIgnoreCase(""))
		{
			model = new ModelAndView("addProgramToApplication/ApplyTransferApplication");
			error = "PLEASE ENTER APPLICATION NUMBER.";
			model.addObject("error", error);
			return model;
		}
		
		if((toProgram1 == null || toProgram1.trim().equalsIgnoreCase("")) && (toProgram2 == null || toProgram2.trim().equalsIgnoreCase("")))
		{
			model = new ModelAndView("addProgramToApplication/ApplyTransferApplication");
			error = "PLEASE SELECT ATLEAST ONE PROGRAM";
			model.addObject("error", error);
			return model;
		}
		if(!(toProgram1 == null || toProgram1.trim().equalsIgnoreCase("")) && !(toProgram2 == null || toProgram2.trim().equalsIgnoreCase("")))
		{
		if(toProgram1.trim().equalsIgnoreCase(toProgram2.trim()))
		{
			model = new ModelAndView("addProgramToApplication/ApplyTransferApplication");
			error = "PLEASE SELECT TWO DIFFERENT PROGRAMS";
			model.addObject("error", error);
			return model;
		}
		}
		CommonBean inputObject = new CommonBean();
		
		if(!(toProgram1 == null || toProgram1.trim().equalsIgnoreCase("")) && !(toProgram2 == null || toProgram2.trim().equalsIgnoreCase("")))
		{
			count=2;
			flag="F";
		}else if(!(toProgram1 == null || toProgram1.trim().equalsIgnoreCase("")) && (toProgram2 == null || toProgram2.trim().equalsIgnoreCase(""))){
			flag="F";
			count=1;
		}else if((toProgram1 == null || toProgram1.trim().equalsIgnoreCase("")) && !(toProgram2 == null || toProgram2.trim().equalsIgnoreCase(""))){
			flag="S";
			count=1;
		}else
		{
			count=0;
			flag="N";
		}
//		System.out.println("here in applyMorePrograms");
		StudentProgramPaper inputToSR=new StudentProgramPaper();
	String[] branches1= request.getParameterValues("program1branches");
		String[] branches2= request.getParameterValues("program2branches");
		try
		{
			for(int i=0;i<count;i++){
				if(i==0 && flag=="F"){
						inputObject.setDescription(toProgram1);
						inputObject.setUniversityID("F");
						if(request.getParameterValues("program1branches")!=null){
						inputToSR.setGrouping(branches1[0]==null?null:branches1[0]); //first preferences
						inputToSR.setMainGroup(branches1[1]==null?null:branches1[1]); //Second preference
						inputToSR.setPaperCode(branches1[2]==null?null:branches1[2]); //Third preference
						}
				}else if(i==0 && flag=="S"){
					inputObject.setDescription(toProgram2);
					inputObject.setUniversityID("S");
					if(request.getParameterValues("program1branches")!=null){
					inputToSR.setGrouping(branches1[0]==null?null:branches1[0]); //first preferences
					inputToSR.setMainGroup(branches1[1]==null?null:branches1[1]); //Second preference
					inputToSR.setPaperCode(branches1[2]==null?null:branches1[2]); //Third preference
					}
				}else if (i==1){
						inputObject.setDescription(toProgram2);
						inputObject.setUniversityID("S");
						if(request.getParameterValues("program2branches")!=null){
						inputToSR.setGrouping(branches2[0]==null?null:branches2[0]); //first preferences
						inputToSR.setMainGroup(branches2[1]==null?null:branches2[1]); //Second preference
						inputToSR.setPaperCode(branches2[2]==null?null:branches2[2]); //Third preference
						}
				}else{
					System.out.println("went in blank for i=");
				}
			
				inputObject.setOtherProperty1(applicationNumber);

				addMoreProgramDao.addProgramsToApplication(inputObject,inputToSR);
			}
//				List<CommonBean> outputList = addMoreProgramDao.checkApplication(inputObject);
//				if(outputList.size() > 0)
//				{
					String message = "Dear "+applicationNumber+ ", You have successfully added program to your application.";
					model = new ModelAndView("addProgramToApplication/SuccessAddProgram", "message", message);
				}
		catch(Exception ex)
				{
			System.out.print(ex);
			ex.printStackTrace();
					error = "There is an error in adding programs.";
					model.addObject("error", error);
				}
		return model;
	}
		public ModelAndView showBranches(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView model = new ModelAndView("addProgramToApplication/AskBranches");
		String applicationNumber = request.getParameter("appNumber");
		String toProgram1 = request.getParameter("transferProgram1");
		String toProgram2 = request.getParameter("transferProgram2");
		int count=0;
		String error = "";
		String branchInP1 = "N";
		String branchInP2 = "N";
		String flag="";
		if(applicationNumber == null || applicationNumber.trim().equalsIgnoreCase(""))
		{
			model = new ModelAndView("addProgramToApplication/ApplyTransferApplication");
			error = "PLEASE ENTER APPLICATION NUMBER.";
			model.addObject("error", error);
			return model;
		}
		if((toProgram1 == null || toProgram1.trim().equalsIgnoreCase("")) && (toProgram2 == null || toProgram2.trim().equalsIgnoreCase("")))
		{
			model = new ModelAndView("addProgramToApplication/ApplyTransferApplication");
			error = "PLEASE SELECT ATLEAST ONE PROGRAM";
			model.addObject("error", error);
			return model;
		}
		if(!(toProgram1 == null || toProgram1.trim().equalsIgnoreCase("")) && !(toProgram2 == null || toProgram2.trim().equalsIgnoreCase("")))
		{
		if(toProgram1.trim().equalsIgnoreCase(toProgram2.trim()))
		{
			model = new ModelAndView("addProgramToApplication/ApplyTransferApplication");
			error = "PLEASE SELECT TWO DIFFERENT PROGRAMS";
			model.addObject("error", error);
			return model;
		}
		}
		if(!(toProgram1 == null || toProgram1.trim().equalsIgnoreCase("")) && !(toProgram2 == null || toProgram2.trim().equalsIgnoreCase("")))
		{
			count=2;
			flag="F";
		}else if(!(toProgram1 == null || toProgram1.trim().equalsIgnoreCase("")) && (toProgram2 == null || toProgram2.trim().equalsIgnoreCase(""))){
			flag="F";
			count=1;
		}else if((toProgram1 == null || toProgram1.trim().equalsIgnoreCase("")) && !(toProgram2 == null || toProgram2.trim().equalsIgnoreCase(""))){
			flag="S";
			count=1;
		}else
		{
			count=0;
			flag="N";
		}
		boolean branchesExist = false;
		if(count == 1 && flag=="F")
		{
			String branchToAsk = addMoreProgramDao.toAskForBranches(toProgram1);
			if(branchToAsk.trim().equalsIgnoreCase("Y"))
			{
				List<CommonBean> branches = addMoreProgramDao.getProgramBranches(toProgram1);
				if(branches.size()>1)
				{
					branchesExist = true;
					branchInP1 = "Y";
					model.addObject("program1Branches", branches);
					model.addObject("program1Name", branches.get(0).getOtherProperty1());
					//return model;
				}
				else
				{
					return applyMorePrograms(request, response);
				}
			}
		} else if(count == 1 && flag=="S"){
			String branchToAsk = addMoreProgramDao.toAskForBranches(toProgram2);
			if(branchToAsk.trim().equalsIgnoreCase("Y"))
			{
				List<CommonBean> branches = addMoreProgramDao.getProgramBranches(toProgram2);
				if(branches.size()>1)
				{
					branchesExist = true;
					branchInP2 = "Y";
					model.addObject("program2Branches", branches);
					model.addObject("program2Name", branches.get(0).getOtherProperty1());
				}
				else
				{
					return applyMorePrograms(request, response);
				}
			}
		}
		else if(count == 2)
		{
			String branchToAsk_1 = addMoreProgramDao.toAskForBranches(toProgram1);
			if(branchToAsk_1.trim().equalsIgnoreCase("Y"))
			{
				List<CommonBean> branches1 = addMoreProgramDao.getProgramBranches(toProgram1);
				if(branches1.size()>1)
				{
					branchesExist = true;
					branchInP1 = "Y";
					model.addObject("program1Branches", branches1);
					model.addObject("program1Name", branches1.get(0).getOtherProperty1());
				}
			}
			String branchToAsk_2 = addMoreProgramDao.toAskForBranches(toProgram2);
			if(branchToAsk_2.trim().equalsIgnoreCase("Y"))
			{
				List<CommonBean> branches2 = addMoreProgramDao.getProgramBranches(toProgram2);
				if(branches2.size()>1)
				{
					branchesExist = true;
					branchInP2 = "Y";
					model.addObject("program2Branches", branches2);
					model.addObject("program2Name", branches2.get(0).getOtherProperty1());
				}
			}
		}
		else
		{
		}
		if(!branchesExist)
		{
			return applyMorePrograms(request, response);
		}
		model.addObject("appNumber", applicationNumber);
		model.addObject("transferProgram1", toProgram1);
		model.addObject("transferProgram2", toProgram2);
		model.addObject("count", count);
		model.addObject("branchInP1", branchInP1);
		model.addObject("branchInP2", branchInP2);
		return model;
	}
}
