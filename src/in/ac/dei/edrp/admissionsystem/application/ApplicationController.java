package in.ac.dei.edrp.admissionsystem.application;



import in.ac.dei.edrp.admissionsystem.common.ApplicationDetail;
import in.ac.dei.edrp.admissionsystem.common.CommonBean;
import in.ac.dei.edrp.admissionsystem.common.CommonController;
import in.ac.dei.edrp.admissionsystem.common.YearSemesterWiseMarks;
import in.ac.dei.edrp.admissionsystem.common.beans.AcademicDetail;
import in.ac.dei.edrp.admissionsystem.common.beans.Applicant;
import in.ac.dei.edrp.admissionsystem.common.beans.ApplicationForm;
import in.ac.dei.edrp.admissionsystem.common.beans.Form;
import in.ac.dei.edrp.admissionsystem.common.beans.GroupPaper;
import in.ac.dei.edrp.admissionsystem.common.beans.InputBean;
import in.ac.dei.edrp.admissionsystem.common.beans.Program;
import in.ac.dei.edrp.admissionsystem.common.beans.ProgramWiseDetail;
import in.ac.dei.edrp.admissionsystem.common.beans.StudentProgramPaper;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public final class ApplicationController extends MultiActionController{
	
	private ApplicationDAO applicationDAO;
	private CommonController commonController;
	
	private static Logger logObj = Logger.getLogger(ApplicationController.class);
	
	public void setApplicationDAO(ApplicationDAO applicationDAO)
	{
		this.applicationDAO = applicationDAO;
	}
	
	
	
	public ApplicationDAO getApplicationDAO() {
		return applicationDAO;
	}

	

	public CommonController getCommonController() {
		return commonController;
	}



	public void setCommonController(CommonController commonController) {
		this.commonController = commonController;
	}



	public ModelAndView getPersonalInformationScreen(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession(false);
		if(session==null)
		{
			return new ModelAndView("errorPages/SessionExpired","message","Your Session has been expired.");
		}

		String errorMessage=request.getParameter("message");
		String errors = request.getParameter("errors");
		
		ModelAndView model = new ModelAndView("application/PersonalDetails");
		model.addObject("message", errorMessage);
		model.addObject("errors", errors);
		return model;
	}
	
	
	public ModelAndView savePersonalDetails(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession session = request.getSession(false);
		if(session == null)
		{
			return new ModelAndView("errorPages/SessionExpired","message","Your Session has been expired.");
		}
		
		Applicant sessionApplicant = (Applicant) session.getAttribute("applicant");
		ModelAndView model = null;
		
		try
		{
			Applicant applicant = new Applicant();
			/**Commented by Arjun on 23-06-2016
			applicant.setApplicantName(request.getParameter("applicantName"));
			applicant.setFatherName(request.getParameter("fatherName"));
			applicant.setMotherName(request.getParameter("motherName"));**/
			
			if(sessionApplicant.getApplicationStatus() == null)
			{
				applicant.setDob(request.getParameter("yyyy")+"-"+request.getParameter("mm")+"-"+request.getParameter("dd"));
				applicant.setCategory(request.getParameter("category"));
				applicant.setGender(request.getParameter("gender"));
				
				//Added by Arjun on 23-06-2016
				applicant.setApplicantName(request.getParameter("applicantName"));
				applicant.setFatherName(request.getParameter("fatherName"));
				applicant.setMotherName(request.getParameter("motherName"));
				applicant.setNationality(request.getParameter("nationality"));
				applicant.setReligion(request.getParameter("religion"));
				applicant.setPwd(request.getParameter("physicalDisability"));
				applicant.setMinority(request.getParameter("minority"));
				applicant.setAadharNumber(request.getParameter("aadharNumber"));
				applicant.setBloodGroup(request.getParameter("bloodGroup"));
				applicant.setEws(request.getParameter("ews"));
				applicant.setKashmiriPandit(request.getParameter("kashmiriPandit"));
				
				applicant.setApplicantNameHi(request.getParameter("applicantNameHi"));
				applicant.setFatherNameHi(request.getParameter("fatherNameHi"));
				applicant.setMotherNameHi(request.getParameter("motherNameHi"));
				applicant.setTypeOfId(request.getParameter("typeOfId"));
				applicant.setIdProofNumber(request.getParameter("numberOfIdProof"));
				
				
				
				if(request.getParameter("emailID") == null)
				{
					applicant.setPrimaryEmailID(sessionApplicant.getPrimaryEmailID());
				}
				else
				{
					applicant.setPrimaryEmailID(request.getParameter("emailID"));
				}
				applicant.setHomePhone(request.getParameter("phoneNumber"));
				
			}
			else
			{
				applicant.setDob(sessionApplicant.getDob());
				applicant.setCategory(sessionApplicant.getCategory());
				applicant.setGender(sessionApplicant.getGender());
				
				//Added by Arjun on 23-06-2016
				applicant.setApplicantName(sessionApplicant.getApplicantName());
				applicant.setFatherName(sessionApplicant.getFatherName());
				applicant.setMotherName(sessionApplicant.getMotherName());
				applicant.setNationality(sessionApplicant.getNationality());
				applicant.setReligion(sessionApplicant.getReligion());
				applicant.setPwd(sessionApplicant.getPwd());
				applicant.setMinority(sessionApplicant.getMinority());
				applicant.setAadharNumber(sessionApplicant.getAadharNumber());
				applicant.setBloodGroup(sessionApplicant.getBloodGroup());
				applicant.setEws(request.getParameter("ews"));
				applicant.setKashmiriPandit(sessionApplicant.getKashmiriPandit());
				applicant.setApplicantNameHi(sessionApplicant.getApplicantNameHi());
				applicant.setFatherNameHi(sessionApplicant.getFatherNameHi());
				applicant.setMotherNameHi(sessionApplicant.getMotherNameHi());
				applicant.setTypeOfId(sessionApplicant.getTypeOfId());
				applicant.setIdProofNumber(sessionApplicant.getIdProofNumber());
				
				
				
				if(request.getParameter("emailID") == null)
				{
					applicant.setPrimaryEmailID(sessionApplicant.getPrimaryEmailID());
				}
				else
				{
					applicant.setPrimaryEmailID(request.getParameter("emailID"));
				}
				//applicant.setHomePhone(sessionApplicant.getHomePhone());
				applicant.setHomePhone(request.getParameter("phoneNumber"));
				
			}
			
			/**
			Commented by Arjun on 23-06-2016
			
			applicant.setNationality(request.getParameter("nationality"));
			applicant.setReligion(request.getParameter("religion"));
			applicant.setPwd(request.getParameter("physicalDisability"));
			applicant.setMinority(request.getParameter("minority"));
			if(request.getParameter("emailID") == null)
			{
				applicant.setPrimaryEmailID(sessionApplicant.getPrimaryEmailID());
			}
			else
			{
				applicant.setPrimaryEmailID(request.getParameter("emailID"));
			}
			
			applicant.setHomePhone(request.getParameter("phoneNumber"));**/
			applicant.setCuetFlag(request.getParameter("cuetFlag"));
			applicant.setCuetAppNumber(request.getParameter("cuetAppNumber"));
			applicant.setCuetRollNumber(request.getParameter("cuetRollNumber"));
			
			applicant.setMaritalStatus(request.getParameter("maritalStatus"));
			applicant.setCorCountry(request.getParameter("cor_country"));
			
			if(applicant.getCorCountry().trim().equalsIgnoreCase("IN"))
			{
				applicant.setCorState(request.getParameter("cor_state"));
				applicant.setCorCity(request.getParameter("cor_city"));
				applicant.setCorDistrict(request.getParameter("cor_district"));
				applicant.setCorPincode(request.getParameter("cor_pincode"));
			}
			else
			{
				applicant.setCorState("");
				applicant.setCorCity("");
				applicant.setCorDistrict("");
				applicant.setCorPincode("");
			}
			applicant.setCorAddressL1(request.getParameter("cor_line1"));
			applicant.setCorAddressL2(request.getParameter("cor_line2"));
			
			/**
			 * For Permanent Address added by Arjun on 28-01-2016 Starts Here
			 */
			applicant.setPerCountry(request.getParameter("per_country"));
			
			if(applicant.getPerCountry().trim().equalsIgnoreCase("IN"))
			{
				applicant.setPerState(request.getParameter("per_state"));
				applicant.setPerCity(request.getParameter("per_city"));
				applicant.setPerDistrict(request.getParameter("per_district"));
				applicant.setPerPincode(request.getParameter("per_pincode"));
			}
			else
			{
				applicant.setPerState("");
				applicant.setPerCity("");
				applicant.setPerDistrict("");
				applicant.setPerPincode("");
			}
			applicant.setPerAddressL1(request.getParameter("per_line1"));
			applicant.setPerAddressL2(request.getParameter("per_line2"));
			/**
			 * For Permanent Address added by Arjun on 28-01-2016 Ends Here
			 */
			
			applicant.setApplicationNumber(sessionApplicant.getApplicationNumber());
			applicant.setUserID(sessionApplicant.getUserID());
			applicant.setApplicationStatus(sessionApplicant.getApplicationStatus());
			applicant.setTabStatus(sessionApplicant.getTabStatus());
			applicant.setUniversityID(getServletContext().getInitParameter("universityID"));
			
			model = new ModelAndView("application/TabController");
			String errors = applicationDAO.validatePersonalDetails(applicant, sessionApplicant);
			
			if(errors.trim().equals(""))
			{
				applicant = applicationDAO.savePersonalDetails(applicant);
				if(applicant == null)
				{
					model = new ModelAndView("errorPages/Error", "error", "There is some Error in Saving Personal Details.");
				}
				else
				{
					refreshSessionVars(request, response, applicant);
					
				}
				commonController.updateApplicationPDF(request, response);
				return model;
				
			
			}
			else
			{
				model.addObject("errors", errors);
				model.addObject("tabToOpen", "0");
			}
		}
		catch(Exception ex)
		{
			model = new ModelAndView("errorPages/ExceptionPage");
			model.addObject("exceptionSource", "THERE IS SOME PROBLEM IN SAVING PERSONAL DETAILS.");
			logObj.error("EXCEPTION IN ApplicationController:savePersonalDetails : " + ex);
			session.invalidate();
		}
		
		return model;
	}
	
	public ModelAndView getAcademicPage(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession(false);
		if(session == null)
		{
			return new ModelAndView("errorPages/SessionExpired","message","Your Session has been expired.");
		}
		
		Applicant applicant = (Applicant)session.getAttribute("applicant");
		ModelAndView model = null;
		try
		{
			String error = request.getParameter("error");
			String errorMessage = request.getParameter("message");
			
			boolean formSelected = session.getAttribute("formSelected") == null ? false : (Boolean) session.getAttribute("formSelected");
			boolean programSelected = session.getAttribute("programSelected") == null ? false : (Boolean) session.getAttribute("programSelected");
			
			int tabStatus = Integer.parseInt(applicant.getTabStatus());
			
			if(tabStatus == 1)
			{
				if(programSelected)
				{
					model = new ModelAndView("application/AcademicPaperOption");
					model.addObject("message", errorMessage);
				}
				else if(formSelected)
				{
					model = new ModelAndView("application/FormPrograms");
				}
				else
				{
					model = getProgramForms(request, response);
				}
			}
			else if(tabStatus > 1)//This check is there because user can add or delete programs even after tab status being greater than 1 (before uploading docs)
			{
				
				if(programSelected)
				{
					model = new ModelAndView("application/AcademicPaperOption");
					model.addObject("message", errorMessage);
				}
				else if(formSelected)
				{
					model = new ModelAndView("application/FormPrograms");
				}
				else
				{
					model = new ModelAndView("application/ApplicantProgramPapers");
					model.addObject("message", errorMessage);
				}
			}
			
			model.addObject("error", error);
			model.addObject("tabToOpen", "1");
		}
		catch(Exception ex)
		{
			model = new ModelAndView("errorPages/ExceptionPage");
			model.addObject("exceptionSource", "THERE IS SOME PROBLEM IN GETTING PAGE FOR ACADEMIC DETAILS.");
			logObj.error("EXCEPTION IN ApplicationController:getAcademicPage for Application Number = " + applicant.getApplicationNumber() + " : " + ex);
			session.invalidate();
		}
		
		return model;
	}
	
	public ModelAndView getProgramForms(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession(false);
		if(session == null)
		{
			return new ModelAndView("errorPages/SessionExpired","message","Your Session has been expired.");
		}
		Applicant applicant = (Applicant) session.getAttribute("applicant");
		List<CommonBean> forms = applicationDAO.getProgramForms(applicant.getUniversityID());
		if(forms.size() == 0)
		{
			return new ModelAndView("errorPages/GenericError","error","No Program Form is available to apply.");
		}
		else
		{
			String errorMessage = request.getParameter("message");
			ModelAndView obj = new ModelAndView("application/AcademicDetailsForm");
			obj.addObject("message", errorMessage);
			obj.addObject("forms", forms);
			return obj;
		}
	}
	

	public ModelAndView getFormPrograms(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession(false);
		if(session == null)
		{
			return new ModelAndView("errorPages/SessionExpired","message","Your Session has been expired.");
		}
		
		Applicant applicant = (Applicant) session.getAttribute("applicant");
		int tabStatus = Integer.parseInt(applicant.getTabStatus());
		if(tabStatus > 3)
		{
			session.invalidate();
			return new ModelAndView("errorPages/Error","error","YOU CANNOT UPDATE YOUR ACADEMIC DETAILS NOW.");
		}
		
		String formNumber = request.getParameter("formNumber");
		ModelAndView model = new ModelAndView("application/TabController");
		model.addObject("tabToOpen", "1");
		if(formNumber == null || formNumber.trim().equalsIgnoreCase(""))
		{
			model.addObject("error", "Please Select Group of Programs.");
		}
		else
		{
			Form form = new Form();
			form.setFormNumber(formNumber);
			form.setInputCategory(applicant.getCategory());//for Age and Gender Eligibility by Arjun Added on 04-04-2016
			form.setApplicationNumber(applicant.getApplicationNumber());
			form.setUniversityID(applicant.getUniversityID());
			List<Program> programs = applicationDAO.getFormPrograms(form);
			session.removeAttribute("selectedfnum");//selected form number
			session.setAttribute("selectedfnum", formNumber);
			session.setAttribute("programs", programs);
			session.setAttribute("formSelected", true);
   
		}
		
		return model;
	}

	public ModelAndView getPaperOptionsPage(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession(false);
		if(session == null)
		{
			return new ModelAndView("errorPages/SessionExpired","message","Your Session has been expired.");
		}
		
		Applicant sessionApplicant = (Applicant) session.getAttribute("applicant");
		
		String genderCode = sessionApplicant.getGender();//for Age and Gender Eligibility by Arjun Added on 04-04-2016
		double ageOnJuly = Double.parseDouble(sessionApplicant.getAgeOnJuly());//for Age and Gender Eligibility by Arjun Added on 04-04-2016
		if((sessionApplicant.getPwd() != null && (sessionApplicant.getPwd().trim().equalsIgnoreCase("Y")) && 
				(sessionApplicant.getCategory().trim().equalsIgnoreCase("GN") || sessionApplicant.getCategory().trim().equalsIgnoreCase("BC"))))
		{
			ageOnJuly = ageOnJuly - 3;
		}
		
		int tabStatus = Integer.parseInt(sessionApplicant.getTabStatus());
		if(tabStatus > 3)
		{
			session.invalidate();
			return new ModelAndView("errorPages/Error","error","YOU CANNOT UPDATE YOUR ACADEMIC DETAILS NOW.");
		}
		
		
		ModelAndView model = new ModelAndView("application/TabController");
		String submitText = request.getParameter("submit");
		if(submitText.trim().toLowerCase().contains("back"))
		{
			session.removeAttribute("formSelected");
		}
		else
		{
			String[] programs = request.getParameterValues("programs");
			String[] paperOptions = request.getParameterValues("paperOptions");
			String[] paperOptionRequired = request.getParameterValues("paperOptionRequired");
			
			//for Age and Gender Eligibility by Arjun Added on 04-04-2016 Starts
			String[] validGenders = request.getParameterValues("validGenders");
			String[] programNames = request.getParameterValues("programNames");
			String[] ageLimits = request.getParameterValues("ageLimits");
			//for Age and Gender Eligibility by Arjun Added on 04-04-2016 Ends
			
			List<String> appliedPrograms = applicationDAO.getAppliedPrograms(sessionApplicant);
			int totalPrograms = 0;
			if(appliedPrograms == null)
			{
				totalPrograms = programs.length;
			}
			else
			{
				if(programs == null)
				{
					totalPrograms = appliedPrograms.size();
				}
				else
				{
					totalPrograms = appliedPrograms.size()+programs.length;
				}
				
			}
			
			int programsLimit = Integer.parseInt(getServletContext().getInitParameter("PRGLMT"));
			
			String error = null;
			if(programs == null || programs.length <= 0 || totalPrograms > programsLimit)
			{
				if(programs == null || programs.length <= 0)
				{
					error = "PLEASE SELECT A PROGRAM.";
				}
				else
				{
					error = "YOU CANNOT APPLY FOR MORE THAN " + programsLimit + " PROGRAMS.";
				}
				model.addObject("error", error);
				model.addObject("tabToOpen","1");
				return model;
			}
			else
			{
				for(int k=0; k < programs.length; k++)
				{
					//for Gender Eligibility by Arjun Added on 04-04-2016
					if(validGenders[k] != null && (!(validGenders[k].trim().equalsIgnoreCase("FM"))))
					{
						String validGenderName = validGenders[k].trim().equalsIgnoreCase("F")?"FEMALES":"MALES";
						if(!(validGenders[k].trim().equalsIgnoreCase(genderCode)))
						{
							error = programNames[k]+" is for "+validGenderName+" only.";
							model.addObject("error", error);
							model.addObject("tabToOpen", "1");
							return model;
						}
					}
					
					//for Age Eligibility by Arjun Added on 04-04-2016
					System.out.println("session.getAttribute formselected toString()=" +session.getAttribute("selectedfnum").toString() );
					if(genderCode.trim().equalsIgnoreCase("M"))
					{
						if(ageLimits[k] != null && (ageOnJuly > Double.parseDouble(ageLimits[k])))
						{
							error = "You are overage for "+programNames[k]+".";
							System.out.println("session.getAttribute formselected toString()=" +session.getAttribute("formSelected").toString() );
							if(session.getAttribute("selectedfnum").toString().trim().equals("0015"))
									//sessionApplicant.getApplicationForm().getFormNumber().trim().equals("0015")) 
							{
								error = error + "This programme has age restriction at the time of admission which is mentioned in the prospectus.<br/>" +
										"However if you wish and you are intermediate pass you can explore <b>B.Voc programmes/programmes</b> available in online mode.<br/>" +
										"In case you are high school pass you can explore our <b>modular skilling programmes</b>.<br/>" +
										"Please refer the prospectus for information about these programmes.";
							}
							else if(session.getAttribute("selectedfnum").toString().trim().equals("0001"))
									//sessionApplicant.getApplicationForm().getFormNumber().trim().equals("0001"))
							{
								error = error + "This programme has age restriction at the time of admission which is mentioned in the prospectus.<br/>"+
								"However if you wish you can explore <b>M.Voc programmes/post graduate programmes</b> available in online mode.<br/>"+
								"Please refer the prospectus for information about these programmes.";
							}
							
							model.addObject("error", error);
							model.addObject("tabToOpen", "1");
							return model;
						}
						
					}
										
					if(paperOptionRequired[k].trim().equalsIgnoreCase("Y")&& (paperOptions ==null || paperOptions[k]==null || paperOptions[k].trim().equalsIgnoreCase("")))
					{
						error = "Please Select Paper Option for "+programNames[k]+".";
						model.addObject("error", error);
						model.addObject("tabToOpen", "1");
						return model;
					}
				}
				
			}
						
			List<String> programList =  Arrays.asList(programs);
			List<String> optedPaperList = Arrays.asList(paperOptions);
			
			
			Applicant inputApplicant = new Applicant(); 
			
			for(String paper : sessionApplicant.getOptedPapers())
			{
				System.out.println("Paper Option is : "+paper);
			}
			
			inputApplicant.setPrograms(programList);
			inputApplicant.setOptedPapers(optedPaperList);
			inputApplicant.setApplicationNumber(sessionApplicant.getApplicationNumber());
			inputApplicant.setApplicationStatus(sessionApplicant.getApplicationStatus());
			inputApplicant.setTabStatus(sessionApplicant.getTabStatus());
			inputApplicant.setExamCenter1(sessionApplicant.getExamCenter1());
			inputApplicant.setSessionStartDate(sessionApplicant.getSessionStartDate());
			inputApplicant.setSessionEndDate(sessionApplicant.getSessionEndDate());
			inputApplicant.setUserID(sessionApplicant.getUserID());
			
			inputApplicant.setUniversityID(getServletContext().getInitParameter("universityID"));
			session.setAttribute("programSelected", true);
			
			inputApplicant = applicationDAO.getProgramPaperOptions(inputApplicant, true); // false means , applicant has not already applied for program ( he is not adding more programs )
			session.setAttribute("formApplicant", inputApplicant);
			}
		model.addObject("tabToOpen", "1");
		return model;
						
	}

	
	public ModelAndView saveAcademicDetails(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView model = null;
		HttpSession session = request.getSession(false);
		if(session == null)
		{
			model = new ModelAndView("errorPages/SessionExpired","message","Your Session has been expired.");
			return model;
		}
		
		Applicant sessionApplicant = (Applicant) session.getAttribute("applicant");
		String applicationNumber = sessionApplicant.getApplicationNumber();
		String userID = sessionApplicant.getUserID();
		
		List<String> errors = new ArrayList<String>();
		
		try
		{
			String[] regularComponents = request.getParameterValues("regularComponents");
			String[] obtainedMarks = request.getParameterValues("obtainedMarks");
			String[] totalMarks = request.getParameterValues("totalMarks");
			String[] boards = request.getParameterValues("boards");
			String[] passingYears = request.getParameterValues("passingYears");
			String[] optionsToShow = request.getParameterValues("optionToShow");
			String[] options = request.getParameterValues("optionName");
			
			String[] compulsoryFlags = request.getParameterValues("compulsoryFlags");
			String[] universityNeeded = request.getParameterValues("universityNeeded");
			String[] passingYearRequired = request.getParameterValues("passingYearRequired");
			String[] brdUnivRequired = request.getParameterValues("brdUnivRequired");
			
			String[] rollnumbers = request.getParameterValues("rollnumbers");//session 2022
			
			//Added by Arjun on 10-05-2019
			String[] rankings = request.getParameterValues("ranking");
			String[] percentiles = request.getParameterValues("percentile");
			
			String formNumber = (String) session.getAttribute("formNumber");
			
			List<AcademicDetail> academicDetails = new ArrayList<AcademicDetail>();
			int counterForExamOptions = 0;
			for(int i = 0 ; i < regularComponents.length; i++)
			{
				AcademicDetail academicDetail = new AcademicDetail();
				
				if(isNullOrBlank(obtainedMarks[i]) && compulsoryFlags[i].trim().equalsIgnoreCase("Y"))
				{
					errors.add("Please enter obtained marks for "+regularComponents[i]+".");
				}
				else if(isNullOrBlank(obtainedMarks[i]) && compulsoryFlags[i].trim().equalsIgnoreCase("N"))
				{
					obtainedMarks[i] = "0.0";
				}
				else
				{
					try
					{
						Double.parseDouble(obtainedMarks[i]);
					}
					catch(Exception parseException)
					{
						errors.add("Please enter obtained marks for "+regularComponents[i]+" in numbers.");
					}
					//SESSION 2022
					if(isNullOrBlank(rollnumbers[i]) && (!obtainedMarks[i].trim().equals("0.00")))
					{
						errors.add("Please enter Roll number for "+regularComponents[i]+".");
					}
				}
				
				if(isNullOrBlank(totalMarks[i]) && compulsoryFlags[i].trim().equalsIgnoreCase("Y"))
				{
					errors.add("Please enter total marks for "+regularComponents[i]+".");
				}
				else if(isNullOrBlank(totalMarks[i]) && compulsoryFlags[i].trim().equalsIgnoreCase("N"))
				{
					totalMarks[i] = "0.0";
				}
				else
				{
					try
					{
						Double.parseDouble(totalMarks[i]);
					}
					catch(Exception parseException)
					{
						errors.add("Please enter total marks for "+regularComponents[i]+" in numbers.");
					}

					//SESSION 2022
					System.out.println(totalMarks[i]);
					if(isNullOrBlank(rollnumbers[i]) && (!totalMarks[i].trim().equals("0.00")))
					{
						errors.add("Please enter Roll number for "+regularComponents[i]+".");
					}
					
				}
				
					
				
				if(errors.size() == 0)
				{
					if(Double.parseDouble(obtainedMarks[i]) > Double.parseDouble(totalMarks[i]))
					{
						errors.add("Obtained marks cannot be greater than Total marks for "+regularComponents[i]+".");
					}
				}
				
				if(compulsoryFlags[i].trim().equalsIgnoreCase("Y"))
				{
					if(isNullOrBlank(boards[i]) && brdUnivRequired[i].trim().equalsIgnoreCase("Y"))
					{
						errors.add("Please enter Board Name for "+regularComponents[i]+".");
					}
					
					if(isNullOrBlank(passingYears[i]) && passingYearRequired[i].trim().equalsIgnoreCase("Y"))
					{
						errors.add("Please enter Passing Year for "+regularComponents[i]+".");
					}
					
					
					
				}
				else
				{
					if(isNullOrBlank(boards[i]) && errors.size() > 0 && brdUnivRequired[i].trim().equalsIgnoreCase("Y"))
					{
						errors.add("Please enter Board Name for "+regularComponents[i]+".");
					}
					
						
					if(isNullOrBlank(passingYears[i]) && errors.size() > 0 && passingYearRequired[i].trim().equalsIgnoreCase("Y"))
					{
						errors.add("Please enter Passing Year for "+regularComponents[i]+".");
					}
				}
				
				
				if(optionsToShow[i].trim().equalsIgnoreCase("1"))
				{
					if(errors.size() > 0 && isNullOrBlank(options[counterForExamOptions]))
					{
						errors.add("Please select option for "+regularComponents[i]+".");
					}
					else
					{
						academicDetail.setOptionCode(options[counterForExamOptions]);
					}
					counterForExamOptions++;
				}
				
				if(regularComponents[i].trim().equalsIgnoreCase("UG"))
				{
					String subjectCode = request.getParameter("subjectOption");
					if(errors.size() > 0 && isNullOrBlank(subjectCode))
					{
						errors.add("Please select Subject for "+regularComponents[i]+".");
					}
					else
					{
						academicDetail.setSubjectCode(subjectCode);
					}
					
				}
				
				if(errors.size()>0)
				{
					model = new ModelAndView("application/TabController");
					model.addObject("errorsStep3", errors);
					model.addObject("tabToOpen","2");
					return model;
				}
				
				
				academicDetail.setExaminationID(regularComponents[i]);
				academicDetail.setObtainedMarks(obtainedMarks[i]);
				academicDetail.setTotalMarks(totalMarks[i]);
				
				//Added by Arjun on 10-05-2019
				academicDetail.setRanking(rankings[i] == null || rankings[i].trim().equalsIgnoreCase("") ? "0" : rankings[i]);
				academicDetail.setPercentile(percentiles[i] == null || percentiles[i].trim().equalsIgnoreCase("") ? "0" : percentiles[i]);
				
				
				
				if(universityNeeded[i].trim().equalsIgnoreCase("Y"))
				{
					academicDetail.setUniversity(boards[i]);
				}
				else
				{
					academicDetail.setBoardID(boards[i]);
				}
				academicDetail.setPassingYear(passingYears[i]);
				academicDetail.setApplicationNumber(applicationNumber);
				academicDetail.setFormNumber(formNumber);
				academicDetail.setUserID(userID);
				
				academicDetail.setRollnumber(rollnumbers[i]);//Session 2022
				/**Today
				if(academicDetail.getExaminationID().trim().equalsIgnoreCase("UG"))
				{
					String subjectCode = request.getParameter("subjectOption");
					academicDetail.setSubjectCode(subjectCode);
				}**/
				
				academicDetails.add(academicDetail);
					
			}
			
			List<AcademicDetail> gateAcademicDetails = new ArrayList<AcademicDetail>();
			String[] gateComponents = request.getParameterValues("gateComponents");
			
			if(gateComponents != null && gateComponents.length > 0 )
			{
				String[] ranking = request.getParameterValues("ranking");
				String[] score = request.getParameterValues("score");
				String[] totalApplicants = request.getParameterValues("totalApplicants");
				String[] gateBranch = request.getParameterValues("gateBranch");
				String[] gateYear = request.getParameterValues("gateYear");
				String[] compulsoryGtFlags = request.getParameterValues("compulsoryGtFlags");
				
				for(int i = 0; i < gateComponents.length; i++)
				{
					AcademicDetail academicDetail = new AcademicDetail();
					academicDetail.setExaminationID(gateComponents[i]);
					
					if(isNullOrBlank(ranking[i]) && compulsoryGtFlags[i].trim().equalsIgnoreCase("Y"))
					{
						errors.add("Please enter Ranking.");
					}
					else if(isNullOrBlank(ranking[i]) && compulsoryGtFlags[i].trim().equalsIgnoreCase("N"))
					{
						academicDetail.setRanking("0");
					}
					else
					{
						try
						{
							Integer.parseInt(ranking[i]);
							academicDetail.setRanking(ranking[i]);
						}
						catch(Exception ex)
						{
							errors.add("Please enter Ranking in number.");
						}
					}
					
					if(isNullOrBlank(score[i]) && compulsoryGtFlags[i].trim().equalsIgnoreCase("Y"))
					{
						errors.add("Please enter Score");
					}
					else if(isNullOrBlank(score[i]) && compulsoryGtFlags[i].trim().equalsIgnoreCase("N"))
					{
						academicDetail.setScore("0");
					}
					else
					{
						try
						{
							Integer.parseInt(score[i]);
							academicDetail.setScore(score[i]);
						}
						catch(Exception ex)
						{
							errors.add("Please enter Score in number.");
						}
					}
					
					if(isNullOrBlank(totalApplicants[i]) && compulsoryGtFlags[i].trim().equalsIgnoreCase("Y"))
					{
						errors.add("Please enter Total Applicants.");
					}
					else if(isNullOrBlank(totalApplicants[i]) && compulsoryGtFlags[i].trim().equalsIgnoreCase("N"))
					{
						academicDetail.setTotalApplicants("0");
					}
					else
					{
						try
						{
							Integer.parseInt(totalApplicants[i]);
							academicDetail.setTotalApplicants(totalApplicants[i]);
						}
						catch(Exception ex)
						{
							errors.add("Please enter Total Applicants in number.");
						}
					}
					
					if(errors.size()>0)
					{
						model = new ModelAndView("application/TabController");
						model.addObject("errorsStep3", errors);
						model.addObject("tabToOpen","2");
						return model;
					}
					
					academicDetail.setGateBranch(gateBranch[i]);
					academicDetail.setGateYear(gateYear[i]);
					academicDetail.setApplicationNumber(applicationNumber);
					academicDetail.setFormNumber(formNumber);
					academicDetail.setUserID(userID);
					gateAcademicDetails.add(academicDetail);
							
				}
				
			}
			
			String fatherIncome = request.getParameter("fatherIncome");
			String motherIncome = request.getParameter("motherIncome");
			String guardianIncome = request.getParameter("guardianIncome");
			String hostelRequired = request.getParameter("hostelRequired");
			String everExpelled = request.getParameter("everExpelled");
			
			String deiStudent = request.getParameter("deiStudent");
			String lastExamFrom = request.getParameter("lastExamFrom");
			String co_ed = request.getParameter("co_ed");
						
			String branchAvailability = sessionApplicant.getApplicationForm().getBranchAvailability();
			
			List<ProgramWiseDetail> programWiseDetails = sessionApplicant.getApplicationForm().getProgramWiseDetails();
			int noOfPrograms = sessionApplicant.getApplicationForm().getProgramWiseDetails().size();
			boolean isValidCenter = true;
			
			for(int i = 0 ; i < noOfPrograms ; i++)
			{
				//int counterForExamOptions = 0; Today
				
				ProgramWiseDetail pwDetail = programWiseDetails.get(i);
				
				String educationMode = request.getParameter(pwDetail.getProgramID()+"_educationMode");
				String studyCenterCode = request.getParameter(pwDetail.getProgramID()+"_studyCenter");
				String studyCenterName = request.getParameter(pwDetail.getProgramID()+"_studyCenterName"); //added by Jyoti on 2 May 2021
				String onlineMode = request.getParameter(pwDetail.getProgramID()+"_onlineMode"); //added by Jyoti on 23 Apr 2021
				String regularMode = request.getParameter(pwDetail.getProgramID()+"_regularMode"); //added by Jyoti on 23 Apr 2021
				String distanceMode = request.getParameter(pwDetail.getProgramID()+"_distanceMode"); //added by Jyoti on 23 Apr 2021
				
				if(isNullOrBlank(onlineMode)) { onlineMode = "N"; }
				if(isNullOrBlank(distanceMode)) { distanceMode = "N"; }
				
				if (isNullOrBlank(regularMode) && (onlineMode.equalsIgnoreCase("Y") || distanceMode.equalsIgnoreCase("Y")))
				{
					regularMode = "N";
				}
				else { regularMode = "Y"; } 
				
				if (regularMode.equalsIgnoreCase("N") && onlineMode.equalsIgnoreCase("N") && distanceMode.equalsIgnoreCase("N"))
				{
					errors.add("Please Select Education Mode."); 
				}
				else if (regularMode.equalsIgnoreCase("Y") && onlineMode.equalsIgnoreCase("N") && distanceMode.equalsIgnoreCase("N"))
				{
					educationMode = "REG";
					InputBean input = new InputBean();
					input.setParam1(pwDetail.getProgramID());
					input.setParam2(studyCenterCode);
					input.setParam3("REG");
					isValidCenter = applicationDAO.validateCenterCode(input);					
					if ((!isValidCenter) || isNullOrBlank(studyCenterCode)) 
					{
						InputBean inp = new InputBean();
						inp.setParam1(pwDetail.getProgramID());
						inp.setParam2("REG");
						List<CommonBean> studyCenters  = applicationDAO.getStudyCenters(inp);
						if (studyCenters.size()>0)
						{
							studyCenterCode = studyCenters.get(0).getCode();
							System.out.println("REG studyCenterCode=" + studyCenterCode );
						}
					}
				}
				
				if(onlineMode.equalsIgnoreCase("Y"))
				{
					InputBean input = new InputBean();
					input.setParam1(pwDetail.getProgramID());
					input.setParam2(studyCenterCode);
					input.setParam3("OLM");
					isValidCenter = applicationDAO.validateCenterCode(input);					
					if   ((!isValidCenter) || isNullOrBlank(studyCenterCode)) 
					{
						errors.add("Please Select Examination Center"); 
						onlineMode = null;
						studyCenterCode = null;
					}
					educationMode = "OLM";
				}
				else // if (onlineMode.equalsIgnoreCase("N") )
				{	
					if (regularMode.equalsIgnoreCase("Y"))
					{
						educationMode = "REG";
					}
					
					if (distanceMode.equalsIgnoreCase("Y"))
					{
						educationMode = "DIS";
						InputBean input = new InputBean();
						input.setParam1(pwDetail.getProgramID());
						input.setParam2(studyCenterCode);
						input.setParam3("DIS");
						isValidCenter = applicationDAO.validateCenterCode(input);					
						if   ((!isValidCenter) || isNullOrBlank(studyCenterCode)) 
						{
							errors.add("Please Select Examination Center"); 
							distanceMode = null;
							studyCenterCode = null;
						}
					}
				}
				
				if (isNullOrBlank(studyCenterName))
				{
					InputBean input = new InputBean();
					input.setParam1(pwDetail.getProgramID());
					input.setParam2(studyCenterCode);
					
					studyCenterName  = applicationDAO.getStudyCenterName(input);
					
				}
				System.out.println("..........programid=" + pwDetail.getProgramID() +"...education mode=" + educationMode + " online mode=" + onlineMode + " regular mode=" + regularMode 
						+ " studyCenterCode=" + studyCenterCode + " studyCenterName="+ studyCenterName);
				
				pwDetail.setFatherIncome(fatherIncome);
				pwDetail.setMotherIncome(motherIncome);
				pwDetail.setGuardianIncome(guardianIncome);
				pwDetail.setEverExpelled(everExpelled);
				pwDetail.setHostelRequired(hostelRequired);
				pwDetail.setDeiStudent(deiStudent);
				pwDetail.setLastExamFrom(lastExamFrom);
				pwDetail.setCo_ed(co_ed);
				pwDetail.setSelectedEducationMode(educationMode);
				pwDetail.setSelectedOnlineMode(onlineMode);
				pwDetail.setSelectedRegularMode(regularMode);
				pwDetail.setSelectedDistanceMode(distanceMode);
				pwDetail.setStudyCenterCode(studyCenterCode);
				pwDetail.setStudyCenterName(studyCenterName);
				/**
				for(int j = 0; j < regularComponents.length; j++)
				{
					if(optionsToShow[j].trim().equalsIgnoreCase("1"))
					{
						academicDetails.get(j).setOptionCode(options[counterForExamOptions]);
						counterForExamOptions++;
					}
				}Today**/
				
				if(gateComponents != null && gateComponents.length > 0 )
				{
					pwDetail.setGateAcademicDetails(gateAcademicDetails);
				}

				pwDetail.setAcademicDetails(academicDetails);
				
				if(branchAvailability != null || branchAvailability.trim().equalsIgnoreCase("Y"))
				{
					ProgramWiseDetail pWiseDetail = sessionApplicant.getApplicationForm().getProgramWiseDetails().get(i);
					if(pWiseDetail.getPreferredBranchAvailable()!=null && pWiseDetail.getPreferredBranchAvailable().trim().equalsIgnoreCase("Y"))
					{						
						
						String[] branches = request.getParameterValues(pWiseDetail.getProgramID()+"_BRANCH");
						int noOfBranches = branches.length;
						switch(noOfBranches)
						{
							case 1:
								pwDetail.setBranch1(branches[0]);
								if(isNullOrBlank(branches[0]))
								{
									errors.add("Please Select All Preferred Branches.");
								}
								break;
							case 2:
								pwDetail.setBranch1(branches[0]);
								pwDetail.setBranch2(branches[1]);
								if(isNullOrBlank(branches[0]) || isNullOrBlank(branches[1]))
								{
									errors.add("Please Select All Preferred Branches.");
								}
								break;
							case 3:
								pwDetail.setBranch1(branches[0]);
								pwDetail.setBranch2(branches[1]);
								pwDetail.setBranch3(branches[2]);
								if(isNullOrBlank(branches[0]) || isNullOrBlank(branches[1]) || isNullOrBlank(branches[2]))
								{
									errors.add("Please Select All Preferred Branches.");
								}
								break;
							case 4:
								pwDetail.setBranch1(branches[0]);
								pwDetail.setBranch2(branches[1]);
								pwDetail.setBranch3(branches[2]);
								pwDetail.setBranch4(branches[3]);
								if(isNullOrBlank(branches[0]) || isNullOrBlank(branches[1]) || isNullOrBlank(branches[2]) || isNullOrBlank(branches[3]))
								{
									errors.add("Please Select All Preferred Branches.");
								}
								break;
							case 5:
								pwDetail.setBranch1(branches[0]);
								pwDetail.setBranch2(branches[1]);
								pwDetail.setBranch3(branches[2]);
								pwDetail.setBranch4(branches[3]);
								pwDetail.setBranch5(branches[4]);
								if(isNullOrBlank(branches[0]) || isNullOrBlank(branches[1]) || isNullOrBlank(branches[2]) || isNullOrBlank(branches[3]) || isNullOrBlank(branches[4]))
								{
									errors.add("Please Select All Preferred Branches.");
								}
								break;
							default:
								//Do Nothing
										
								
						}
						
						pwDetail.setRegistrationNumber(pWiseDetail.getRegistrationNumber());
						pwDetail.setProgramID(pWiseDetail.getProgramID());
						pwDetail.setUserID(pWiseDetail.getUserID());
						pwDetail.setSessionStartDate(pWiseDetail.getSessionStartDate());
						pwDetail.setSessionEndDate(pWiseDetail.getSessionEndDate());
						
				
					}
					else
					{
						//Do Nothing
					}
				}
				else
				{
					//Do Nothing
				}
			}
			
			String phase2Edited = sessionApplicant.getPhase2Edited();
			String resultResponse = "";
			
			if(errors.size()>0)
			{
				//model = new ModelAndView("application/AcademicPerformance");
				model = new ModelAndView("application/TabController");
				model.addObject("errorsStep3", errors);
				model.addObject("tabToOpen","2");
				return model;
			}
			else
			{
				if(phase2Edited == null || phase2Edited.trim().equalsIgnoreCase("N"))
				{
					resultResponse = applicationDAO.insertAcademicDetails(programWiseDetails);
				}
				else
				{
					resultResponse = applicationDAO.updateAcademicDetails(programWiseDetails);
				}
				
				if(resultResponse.trim().equalsIgnoreCase("Y"))
				{
					sessionApplicant = applicationDAO.getApplicantDetails(sessionApplicant);
					refreshSessionVars(request, response, sessionApplicant);
					//model = new ModelAndView("application/AcademicPerformanceViewMode");
					model = new ModelAndView("application/TabController");
					return model;
				}
				else
				{
					model = new ModelAndView("errorPages/Error", "error", "There is some Error.");
				}

			}
			
			
		}
		catch(Exception ex)
		{
			logObj.error("EXCEPTION FOR APPLICATION NUMBER : "+sessionApplicant.getApplicationNumber()+"\n"+ ex);
			model = new ModelAndView("errorPages/Error", "error", "There is some Exception in saving your Academic Details.");
			session.invalidate();
		}
		
		return model;
				
	}
	
	public ModelAndView savePaperOptions(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession(false);
		if(session == null)
		{
			return new ModelAndView("errorPages/SessionExpired","message","Your Session has been expired.");
		}
		
		Applicant sessionApplicant =  (Applicant) session.getAttribute("applicant");
		Applicant formApplicant = (Applicant) session.getAttribute("formApplicant");
		
		int tabStatus = Integer.parseInt(sessionApplicant.getTabStatus());
		if(tabStatus>3)
		{
			session.invalidate();
			return new ModelAndView("errorPages/Error","error","YOU CANNOT UPDATE YOUR ACADEMIC DETAILS NOW.");
		}
		
		formApplicant.setApplicationNumber(sessionApplicant.getApplicationNumber());
		formApplicant.setApplicationStatus(sessionApplicant.getApplicationStatus());
		formApplicant.setUniversityID(sessionApplicant.getUniversityID());
		formApplicant.setCategory(sessionApplicant.getCategory());
		formApplicant.setGender(sessionApplicant.getGender());
				
		String error = null;
		String examCenter = null;
		
		ModelAndView model = new ModelAndView("application/TabController");
		
		List<String> appliedPrograms = applicationDAO.getAppliedPrograms(sessionApplicant);
		int totalPrograms = 0;
		if(appliedPrograms == null)
		{
			totalPrograms = formApplicant.getApplicationForm().getProgramWiseDetails().size();
		}
		else
		{
			totalPrograms = appliedPrograms.size()+formApplicant.getApplicationForm().getProgramWiseDetails().size();
		}
		
		int programsLimit = Integer.parseInt(getServletContext().getInitParameter("PRGLMT"));
		
		if(totalPrograms > programsLimit)
		{
			error = "YOU CANNOT APPLY FOR MORE THAN " + programsLimit + " PROGRAMS.";
			model.addObject("error", error);
			model.addObject("tabToOpen","1");
			return model;
		}
		
		
		if(formApplicant.getApplicationStatus() == null || formApplicant.getApplicationStatus().trim().equalsIgnoreCase(""))
		{
			examCenter = request.getParameter("examCenter1");
		}
		else
		{
			model.addObject("tabToOpen", "1");
			if(sessionApplicant.getApplicationForm().getPapersAvailability().trim().equalsIgnoreCase("Y"))
			{
				examCenter = sessionApplicant.getExamCenter1();
			}
			else
			{
				examCenter = request.getParameter("examCenter1");
			}
			
			
		}
			
		
		
		
		String submitText = request.getParameter("submit");
		if(submitText.trim().toLowerCase().contains("back"))
		{
			session.removeAttribute("programSelected");
		}
		else
		{
			List<String> duplicatePrograms = applicationDAO.getDuplicatePrograms(formApplicant);
			if(duplicatePrograms.size()>0)
			{
				error = "YOU HAVE ALREADY APPLIED FOR THE FOLLOWING PROGRAMS:-<br/><ul>";
				for(int n = 0; n < duplicatePrograms.size(); n++)
				{
					error = error+"<li>"+duplicatePrograms.get(n)+"</li>";
				}
				error=error+"</ul>";
				model.addObject("error", error);
				return model;
			}
			
			
			
			String papersAvailablity = formApplicant.getApplicationForm().getPapersAvailability();
			boolean examCenterSelected = true;
			int noOfPrograms = formApplicant.getApplicationForm().getProgramWiseDetails().size();
			
			for(int n = 0 ; n < noOfPrograms; n++)
			{
				ProgramWiseDetail programWiseDetail = formApplicant.getApplicationForm().getProgramWiseDetails().get(n);
				if(programWiseDetail.getPaperMainGroup().equalsIgnoreCase(""))
				{
					continue;
				}
				else
				{
					List<StudentProgramPaper> programPapers = new ArrayList<StudentProgramPaper>();
					for(GroupPaper groupPaper : programWiseDetail.getProgramPaper().getGroupPapers())
					{
					
						String[] paperOptions = request.getParameterValues(groupPaper.getPaperGroupID());
						int optedGroupPapersMax = Integer.parseInt(request.getParameter(groupPaper.getPaperGroupID()+"_MAX"));
						int optedGroupPapersMin = Integer.parseInt(request.getParameter(groupPaper.getPaperGroupID()+"_MIN"));
						if(paperOptions==null || !(paperOptions.length >= optedGroupPapersMin && paperOptions.length <= optedGroupPapersMax))
						{
							if(paperOptions!=null && paperOptions.length>optedGroupPapersMax)
							{
								error = "PLEASE DO NOT SELECT MORE THAN "+optedGroupPapersMax+" PAPER OPTIONS FOR "+programWiseDetail.getProgramName();
							}
							else
							{
								error = "PLEASE SELECT AT LEAST "+optedGroupPapersMin+" PAPER OPTIONS FOR "+programWiseDetail.getProgramName(); 
							}
						
							model.addObject("error", error);
							return model;
						}
					
					
					
						for(int i =0; i<paperOptions.length; i++)
						{	
							StudentProgramPaper stdProgramPaper =   new StudentProgramPaper();
							stdProgramPaper.setProgramID(groupPaper.getProgramID());
							stdProgramPaper.setMainGroup(groupPaper.getMainGroup());
							stdProgramPaper.setGrouping(groupPaper.getGrouping());
							stdProgramPaper.setPaperCode(paperOptions[i]);
							programPapers.add(stdProgramPaper);
							System.out.println(groupPaper.getMainGroup()+" "+groupPaper.getProgramID()+" "+groupPaper.getGrouping()+" "+paperOptions[i]);
						}
					
				}
				
					if((examCenter==null || examCenter.trim().equalsIgnoreCase("")) && (papersAvailablity.trim().equalsIgnoreCase("Y")))
					{
						examCenterSelected = false;
					}
					
					
				formApplicant.getApplicationForm().getProgramWiseDetails().get(n).setStudentProgramPapers(programPapers);
				formApplicant.getApplicationForm().getProgramWiseDetails().get(n).setExamCenter1(examCenter);
								
			}//Else of papers option ends here
			
				
			}
			
			if(papersAvailablity.trim().equalsIgnoreCase("Y") && !examCenterSelected)
			{
				error = "PLEASE SELECT EXAMINATION CENTER";
				model.addObject("error", error);
				return model;				
			}
			else
			{
				formApplicant = applicationDAO.savePaperOptions(formApplicant);
				if(formApplicant==null)
				{
					return new ModelAndView("errorPages/Error","message","There is some Problem in Saving Academic Details.");
				}
			}
			
			refreshSessionVars(request, response, formApplicant);
				

		}
		
		commonController.updateApplicationPDF(request, response);		
		return model;
		
		
	}
	
	public ModelAndView getPaymentPanel(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession(false);
		Applicant applicantData = (Applicant) session.getAttribute("applicant");
		
		//adding dummy list
		List<String> dummyList=new ArrayList<String>();
		for(ProgramWiseDetail pd : applicantData.getApplicationForm().getProgramWiseDetails())
		{
			dummyList.add(pd.getProgramID());
		}
		
		applicantData.setPrograms(dummyList);
		
		String fees="";
		/** Commented by Arjun on 20-02-2014
		String paymentType=applicationDAO.getPaymentType();
		if(paymentType.equalsIgnoreCase("PP")){
			fees=applicationDAO.getPaymentPerProgram(applicantData);
		}else if (paymentType.equalsIgnoreCase("LS")){
			fees=applicationDAO.getPaymentLumpsum(applicantData);

		}else{
//		Incorrect payment type	
		}**/
		
		ModelAndView model = null;
		
		//Added by Arjun on 20-02-2014
		fees = applicantData.getApplicationFee();
		if(fees.trim().equalsIgnoreCase("error"))
		{
			model = new ModelAndView("errorPages/ExceptionPage","exceptionSource","THERE IS EXCEPTION IN GETTING APPLICATION FEES");
		}
		else
		{
			List<CommonBean> embkups = new ArrayList<CommonBean>();
			List<YearSemesterWiseMarks> yearSemWiseMarks = new ArrayList<YearSemesterWiseMarks>();
			if(applicantData.getApplicationForm().getFormNumber().trim().equalsIgnoreCase("0001"))
			{
				embkups = applicationDAO.getEmbkup();
				yearSemWiseMarks = applicationDAO.getYearSemWiseMarks(applicantData.getApplicationNumber());
				//if(yearSemWiseMarks.size() == 1)
				//{
				int toAdd = 10 - yearSemWiseMarks.size();
					for(int i = 0; i < toAdd; i++)
					{
						YearSemesterWiseMarks yswm = new YearSemesterWiseMarks(applicantData.getApplicationNumber(),
								"UG","","","","","");
						yearSemWiseMarks.add(yswm);
					}
				//}
			}
			model = new ModelAndView("application/Payment");
			model.addObject("fees",fees);
			model.addObject("embkups",embkups);
			model.addObject("yearSemWiseMarksList",yearSemWiseMarks);
			model.addObject("message", request.getParameter("message"));
			
		}
		
		return model;
	}
	
	
	public ModelAndView getFormsAndPrograms(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession(false);
		if(session==null)
		{
			return new ModelAndView("errorPages/SessionExpired","message","Your Session has been expired.");
		}
		
		Applicant applicant = (Applicant) session.getAttribute("applicant");
		List<Form> forms = applicationDAO.getFormsAndPrograms(applicant);
		
		
		if(forms.size()==0)
		{
			return new ModelAndView("errorPages/Error","message","No Data Available.");
		}
		else
		{
//			return new ModelAndView("application/AcademicDetailsForm", "formsAndPrograms", forms);
			String errorMessage=request.getParameter("message");
			ModelAndView obj=new ModelAndView("application/AcademicDetailsForm");
			obj.addObject("message", errorMessage);
			obj.addObject("formsAndPrograms", forms);
			return obj;
		}
		
		
	}
	
	
	public ModelAndView logout(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession(false);
		if(session==null)
		{
			return new ModelAndView("errorPages/SessionExpired","message","Your Session has been expired.");
		}
		else
		{
			session.invalidate();
			String message = "<font color='green'>You have successfully Logged Out.</font>";
			return new ModelAndView("account/ApplicantLogin","message",message);
			
		}
		
		
		
		
	}


	public void refreshSessionVars(HttpServletRequest request,HttpServletResponse response, Applicant applicant)
	{
		HttpSession session = request.getSession(false);
		
		session.removeAttribute("applicationNumber");
		session.removeAttribute("applicationStatus");
		session.removeAttribute("tabStatus");
		session.removeAttribute("applicant");
		session.removeAttribute("programsSelected");//Added on 28 Dec 2014 by Arjun
		
		session.removeAttribute("formSelected");
		session.removeAttribute("programSelected");
		session.removeAttribute("programs");
		session.removeAttribute("formApplicant");
		session.removeAttribute("forms");
		
		
		
		session.setAttribute("applicationNumber", applicant.getApplicationNumber());
		session.setAttribute("applicationStatus", applicant.getApplicationStatus());
		session.setAttribute("tabStatus", applicant.getTabStatus());
		session.setAttribute("applicant", applicant);	
	}

	public ModelAndView getApplicationDetails(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		ModelAndView model = new ModelAndView("application/ApplicationDetails");
		return model;
	}
	
		
		
	

	
	
	
	
		
	
	public ModelAndView removeProgram(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession(false);
		if(session==null)
		{
			return new ModelAndView("errorPages/SessionExpired","message","Your Session has been expired.");
		}
		
		Applicant applicant = (Applicant)session.getAttribute("applicant");
		int tabStatus = Integer.parseInt(applicant.getTabStatus());
		if(tabStatus>2)
		{
			session.invalidate();
			return new ModelAndView("errorPages/Error","error","YOU CANNOT UPDATE YOUR ACADEMIC DETAILS NOW.");
		}
		
		ModelAndView model = new ModelAndView("application/TabController");
		model.addObject("tabToOpen", "1");
		String[] programs = request.getParameterValues("programs");
		if(programs == null || programs.length <= 0)
		{
			model.addObject("error", "<ul><li style='font-family:verdana;color:red;'>Please Select Program to Remove.</li></ul>");
			return model;
		}
		else
		{
			applicant = applicationDAO.removeProgram(programs, applicant);
			if(applicant!=null)
			{
				refreshSessionVars(request, response, applicant);
			}
			else
			{
				model.addObject("error", "<ul><li style='font-family:verdana;color:red;'>There is some problem in the process.</li></ul>");
			}
			
		}
		
		commonController.updateApplicationPDF(request, response);
		return model;
	}
	
	public ModelAndView getAppStatusPage(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		ModelAndView model = new ModelAndView("account/ApplicationStatus");
		
		return model;
	}
	
	public ModelAndView getApplicationStatus(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String applicationNumber = request.getParameter("applicationNumber");
		ModelAndView model = new ModelAndView("account/ApplicationStatus");
		List<String> errors = new ArrayList<String>();
		if(applicationNumber == null || applicationNumber.trim().equals(""))
		{
			errors.add("Please Enter Application Number.");
			model.addObject("errors", errors);
			return model;
		}
		else
		{
			ApplicationDetail input = new ApplicationDetail();
			input.setApplicationNumber(applicationNumber);
			input.setUniversityID(getServletContext().getInitParameter("universityID"));
			ApplicationDetail output = applicationDAO.getApplicationDetails(input);
			if(output!=null)
			{
				model.addObject("applicationDetail", output);
				return model;
			}
			else
			{
				errors.add("There is some problem in getting Application Details.");
				model.addObject("errors", errors);
				return model;
			}
		}
		
	}
	
	public ModelAndView getStudyCenters(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession(false);
		if(session==null)
		{
			return new ModelAndView("errorPages/SessionExpired","message","Your Session has been expired.");
		}
		
		String programID = request.getParameter("programID");
		String educationMode = request.getParameter("educationMode");
		System.out.println("getStudyCenters.....programId=" + programID + " educationMode=" + educationMode );
		InputBean input = new InputBean();
		input.setParam1(programID);
		input.setParam2(educationMode);
		
		List<CommonBean> studyCenters  = applicationDAO.getStudyCenters(input);
		
		
		ModelAndView model = new ModelAndView("application/StudyCenter");
		model.addObject("studyCenters", studyCenters);
		model.addObject("idForCenterField", programID+"_studyCenter");
		
		return model;
	}
	
	public ModelAndView getStateDistricts(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession(false);
		if(session==null)
		{
			return new ModelAndView("errorPages/SessionExpired","message","Your Session has been expired.");
		}
		
		String inpState = request.getParameter("selstate");
		System.out.println("getStateDistricts.....inpState=" + inpState );

		List<CommonBean> distList  = applicationDAO.getStateDistricts(inpState);
		System.out.println("distList size=" + distList.size());
		
		ModelAndView model = new ModelAndView("application/StateDistrict");
		model.addObject("districtsList", distList);
		model.addObject("idForField", "cor_district");
		
		return model;
	}
	
	public ModelAndView getStudyCenterList(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession(false);
		if(session==null)
		{
			return new ModelAndView("errorPages/SessionExpired","message","Your Session has been expired.");
		}
		
		String programID = request.getParameter("programID");
		String educationMode = request.getParameter("educationMode");
		System.out.println("getStudyCenterList.....programId=" + programID + " educationMode=" + educationMode );
		InputBean input = new InputBean();
		input.setParam1(programID);
		input.setParam2(educationMode);
		
		ModelAndView model = null;
		//if (educationMode.equalsIgnoreCase("OLM"))
		//{
			List<CommonBean> studyCenters  = applicationDAO.getStudyCenters(input);
			model = new ModelAndView("application/StudyCenter");
			model.addObject("studyCenters", studyCenters);
			model.addObject("idForCenterField", programID+"_studyCenter");
		/*}
		else
		{
			model = new ModelAndView("application/DefaultCenter");
			//model.addObject("studyCenters", studyCenters);
			model.addObject("idForCenterField", programID+"_studyCenter");
		}*/
		return model;
	}
	
	public boolean isNullOrBlank(String param)
	{
		return (param == null || param.trim().equals(""));
	}
	
	public ModelAndView saveAcademicDetailsPHD(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView model = null;
		HttpSession session = request.getSession(false);
		if(session==null)
		{
			model = new ModelAndView("errorPages/SessionExpired","message","Your Session has been expired.");
			return model;
		}
		
		Applicant sessionApplicant = (Applicant) session.getAttribute("applicant");
		String applicationNumber = sessionApplicant.getApplicationNumber();
		String userID = sessionApplicant.getUserID();
		
		List<String> errors = new ArrayList<String>();
		
		try
		{
			String[] regularComponents = request.getParameterValues("regularComponents");
			String[] obtainedMarks = request.getParameterValues("obtainedMarks");
			String[] totalMarks = request.getParameterValues("totalMarks");
			String[] boards = request.getParameterValues("boards");
			String[] universityNeeded = request.getParameterValues("universityNeeded"); 
			String[] passingYears = request.getParameterValues("passingYears");
			String[] optionsToShow = request.getParameterValues("optionToShow");
			String[] options = request.getParameterValues("optionName");
			
			String fatherIncome = request.getParameter("fatherIncome");
			String motherIncome = request.getParameter("motherIncome");
			String guardianIncome = request.getParameter("guardianIncome");
			
			String formNumber = (String) session.getAttribute("formNumber");
					
			String hostelRequired = request.getParameter("hostelRequired");
			String everExpelled = request.getParameter("everExpelled");
			
			String[] gateComponents = request.getParameterValues("gateComponents");
			
			String branchAvailability = sessionApplicant.getApplicationForm().getBranchAvailability();
			
			List<ProgramWiseDetail> programWiseDetails = sessionApplicant.getApplicationForm().getProgramWiseDetails();
			int noOfPrograms = sessionApplicant.getApplicationForm().getProgramWiseDetails().size();
				
			for(int i = 0 ; i < noOfPrograms ; i++)
			{
				List<AcademicDetail> academicDetails = new ArrayList<AcademicDetail>();
				List<AcademicDetail> gateAcademicDetails = new ArrayList<AcademicDetail>();
				
				int counterForExamOptions = 0;
				
				ProgramWiseDetail pwDetail = programWiseDetails.get(i);
				
				String educationMode = request.getParameter(pwDetail.getProgramID()+"_educationMode");
				String studyCenterCode = request.getParameter(pwDetail.getProgramID()+"_studyCenter");
				
				if(isNullOrBlank(educationMode))
				{
					errors.add("Please Select Education Mode.");					
				}
				
				if(isNullOrBlank(studyCenterCode))
				{
					errors.add("Please Select Study Center.");
				}
				
				System.out.println("........PHD save details.....education mode=" + educationMode  + " studyCenterCode=" + studyCenterCode);
				pwDetail.setFatherIncome(fatherIncome);
				pwDetail.setMotherIncome(motherIncome);
				pwDetail.setGuardianIncome(guardianIncome);
				pwDetail.setEverExpelled(everExpelled);
				pwDetail.setHostelRequired(hostelRequired);
				
				pwDetail.setSelectedEducationMode(educationMode);
				pwDetail.setStudyCenterCode(studyCenterCode);
				
				for(int j = 0; j < regularComponents.length; j++)
				{
					AcademicDetail academicDetail = new AcademicDetail();
					academicDetail.setExaminationID(regularComponents[j]);
					
					String obtnMarks = obtainedMarks[j];
					String ttlMarks = totalMarks[j];
					if(obtnMarks!=null && (!(obtnMarks.trim().equals(""))))
					{
						academicDetail.setObtainedMarks(obtnMarks);
					}
					else
					{
						academicDetail.setObtainedMarks("0.0");
					}
					
					
					if(ttlMarks!=null && (!(ttlMarks.trim().equals(""))))
					{
						academicDetail.setTotalMarks(ttlMarks);
					}
					else
					{
						academicDetail.setTotalMarks("0.0");
					}
					
					//academicDetail.setObtainedMarks(obtainedMarks[j]);
					//academicDetail.setTotalMarks(totalMarks[j]);
					if(universityNeeded[j].trim().equalsIgnoreCase("Y"))
					{
						academicDetail.setUniversity(boards[j]);
					}
					else
					{
						academicDetail.setBoardID(boards[j]);
					}
					
					academicDetail.setPassingYear(passingYears[j]);
					academicDetail.setApplicationNumber(applicationNumber);
					academicDetail.setFormNumber(formNumber);
					academicDetail.setUserID(userID);
					
					if(academicDetail.getExaminationID().trim().equalsIgnoreCase("UG"))
					{
						String subjectCode = request.getParameter("subjectOption");
						academicDetail.setSubjectCode(subjectCode);
					}
					if(optionsToShow[j].trim().equalsIgnoreCase("1"))
					{
						academicDetail.setOptionCode(options[counterForExamOptions]);
						counterForExamOptions++;
					}
					
					if(!(isNullOrBlank(obtainedMarks[j]) || isNullOrBlank(totalMarks[j])))
					{
						if(Double.parseDouble(obtainedMarks[j]) > Double.parseDouble(totalMarks[j]))
						{
							errors.add("Obtained Marks cannot be greater than Total Marks.");
						}
					}
					
					
					
					academicDetails.add(academicDetail);
				
				}
				
				if(gateComponents != null && gateComponents.length > 0 )
				{
					String[] ranking = request.getParameterValues("ranking");
					String[] score = request.getParameterValues("score");
					String[] totalApplicants = request.getParameterValues("totalApplicants");
					String[] gateBranch = request.getParameterValues("gateBranch");
					String[] gateYear = request.getParameterValues("gateYear");
					
					for(int n = 0; n < gateComponents.length; n++)
					{

						AcademicDetail academicDetail = new AcademicDetail();
						academicDetail.setExaminationID(gateComponents[n]);
						if(ranking[n] == null || ranking[n].trim().equals(""))
						{
							academicDetail.setRanking("0");
						}
						else
						{
							academicDetail.setRanking(ranking[n]);
						}
						
						if(score[n] == null || score[n].trim().equals(""))
						{
							academicDetail.setScore("0");
						}
						else
						{
							academicDetail.setScore(score[n]);
						}
						
						if(totalApplicants[n] == null || totalApplicants[n].trim().equals(""))
						{
							academicDetail.setTotalApplicants("0");
						}
						else
						{
							academicDetail.setTotalApplicants(totalApplicants[n]);
						}
						
						academicDetail.setGateBranch(gateBranch[n]);
						academicDetail.setGateYear(gateYear[n]);
						academicDetail.setApplicationNumber(applicationNumber);
						academicDetail.setFormNumber(formNumber);
						academicDetail.setUserID(userID);
						gateAcademicDetails.add(academicDetail);
								
					}
					
					pwDetail.setGateAcademicDetails(gateAcademicDetails);
				}

				pwDetail.setAcademicDetails(academicDetails);
				
				if(branchAvailability != null || branchAvailability.trim().equalsIgnoreCase("Y"))
				{
					ProgramWiseDetail pWiseDetail = sessionApplicant.getApplicationForm().getProgramWiseDetails().get(i);
					if(pWiseDetail.getPreferredBranchAvailable()!=null && pWiseDetail.getPreferredBranchAvailable().trim().equalsIgnoreCase("Y"))
					{
						
						
						String[] branches = request.getParameterValues(pWiseDetail.getProgramID()+"_BRANCH");
						int noOfBranches = branches.length;
						switch(noOfBranches)
						{
							case 1:
								pwDetail.setBranch1(branches[0]);
								if(isNullOrBlank(branches[0]))
								{
									errors.add("Please Select All Preferred Branches.");
								}
								break;
							case 2:
								pwDetail.setBranch1(branches[0]);
								pwDetail.setBranch2(branches[1]);
								if(isNullOrBlank(branches[0]) || isNullOrBlank(branches[1]))
								{
									errors.add("Please Select All Preferred Branches.");
								}
								break;
							case 3:
								pwDetail.setBranch1(branches[0]);
								pwDetail.setBranch2(branches[1]);
								pwDetail.setBranch3(branches[2]);
								if(isNullOrBlank(branches[0]) || isNullOrBlank(branches[1]) || isNullOrBlank(branches[2]))
								{
									errors.add("Please Select All Preferred Branches.");
								}
								break;
							default:
								//Do Nothing
										
								
						}
						
						pwDetail.setRegistrationNumber(pWiseDetail.getRegistrationNumber());
						pwDetail.setProgramID(pWiseDetail.getProgramID());
						pwDetail.setUserID(pWiseDetail.getUserID());
						pwDetail.setSessionStartDate(pWiseDetail.getSessionStartDate());
						pwDetail.setSessionEndDate(pWiseDetail.getSessionEndDate());
						
				
					}
					else
					{
						//Do Nothing
					}
				}
				else
				{
					//Do Nothing
				}
			}
			
			// Code to get Research Programs related details starts here
			String publishedPapers = request.getParameter("publishedPapers");
			String journalDesc = request.getParameter("journalDesc");
			String conferences = request.getParameter("conferences");
			String conferencesDesc = request.getParameter("confrDesc");
			String fellowship = request.getParameter("fellowship");
			String fellowshipDesc = request.getParameter("fellowshipDesc");
			String retQualified = request.getParameter("retQualified");
			String retRemarks = request.getParameter("retRemarks");
			String retYear = request.getParameter("retQualificationYear");
			String retRollNumber = request.getParameter("retRollNumber");
			String instituteTeacher = request.getParameter("instituteTeacher");
			String jrfQualified = request.getParameter("jrfQualified");
			String deiScholor = request.getParameter("scholor");
			String deiMphil = request.getParameter("mphilScholor");
			String deiMedalWinner = request.getParameter("medalWinner");
			String deiPG = request.getParameter("deipg");
			String cgpa9 = request.getParameter("cgpa9");
			String recipientType = request.getParameter("recipientType");
			
			Applicant retApplicant = new Applicant();
			retApplicant.setUserID(sessionApplicant.getUserID());
			retApplicant.setPublishedPapers(publishedPapers);
			retApplicant.setJournalDesc(journalDesc);
			retApplicant.setConferences(conferences);
			retApplicant.setConferencesDesc(conferencesDesc);
			retApplicant.setFellowship(fellowship);
			retApplicant.setFellowshipDesc(fellowshipDesc);
			retApplicant.setRetQualified(retQualified);
			retApplicant.setRetRemarks(retRemarks);
			retApplicant.setRetYear(retYear);
			retApplicant.setRetRollNumber(retRollNumber);
			retApplicant.setInstituteTeacher(instituteTeacher);
			retApplicant.setJrfQualified(jrfQualified);
			retApplicant.setDeiScholor(deiScholor);
			retApplicant.setDeiMphil(deiMphil);
			retApplicant.setDeiMedalWinner(deiMedalWinner);
			retApplicant.setDeiPG(deiPG);
			retApplicant.setCgpa9(cgpa9);
			retApplicant.setRecipientType(recipientType);
			
			// Code to get Research Programs related details ends here
			
			String phase2Edited = sessionApplicant.getPhase2Edited();
			String resultResponse = "";
			
			if(errors.size()>0)
			{
				model = new ModelAndView("application/AcademicPerformance");
				model.addObject("errors", errors);
				model.addObject("tabToOpen","2");
				return model;
			}
			else
			{
				ApplicationForm applicationForm = new ApplicationForm();
				applicationForm.setProgramWiseDetails(programWiseDetails);
				retApplicant.setApplicationForm(applicationForm);
				
				if(phase2Edited == null || phase2Edited.trim().equalsIgnoreCase("N"))
				{
					resultResponse = applicationDAO.insertPhdAcademicDetails(retApplicant);
				}
				else
				{
					resultResponse = applicationDAO.updatePhdAcademicDetails(retApplicant);
				}
				
				if(resultResponse.trim().equalsIgnoreCase("Y"))
				{
					sessionApplicant = applicationDAO.getApplicantDetails(sessionApplicant);
					refreshSessionVars(request, response, sessionApplicant);
					//model = new ModelAndView("application/AcademicPerformancePhdViewMode");
					model = new ModelAndView("application/TabController");
					commonController.updateApplicationPDF(request, response);
					return model;
				}
				else
				{
					model = new ModelAndView("errorPages/Error", "error", "There is some Error.");
				}

			}
			
			
		}
		catch(Exception ex)
		{
			logObj.error("EXCEPTION FOR APPLICATION NUMBER : "+sessionApplicant.getApplicationNumber()+"\n"+ ex);
			model = new ModelAndView("errorPages/Error", "error", "There is some Exception in saving your Academic Details.");
			session.invalidate();
		}
		
		return model;
	}
	
	public ModelAndView getProgramComponentsPage(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession(false);
		if(session == null)
		{
			return new ModelAndView("errorPages/SessionExpired","message","Your Session has been expired.");
		}
		
		Applicant applicant = (Applicant) session.getAttribute("applicant");
		String errors = request.getParameter("errorsStep3");
		applicant = applicationDAO.getApplicationForm(applicant);
		
		String globalEditing = applicant.getGlobalEditing();
		String forceEditing = applicant.getForceEditing();
		
		List<String> researchPrograms = applicationDAO.getResearchPrograms(applicant.getApplicationNumber());
		String jspToReturn = researchPrograms.size()>0?"academicDetails/AcademicPerformancePHD":"academicDetails/AcademicPerformance";
		String viewModeJspToReturn = researchPrograms.size()>0?"academicDetails/AcademicPerformancePhdViewMode":"academicDetails/AcademicPerformanceViewMode";
		
		ModelAndView model = null ;
		
		if(globalEditing == null || globalEditing.trim().equalsIgnoreCase("N"))
		{
			if(forceEditing == null || forceEditing.trim().equalsIgnoreCase("N"))
			{
				if(applicant.getPhase2Edited() == null)
				{
					model = new ModelAndView("academicDetails/Error", "error", "YOU CANNOT UPDATE YOUR PART-2 OF ADMISSION PROCESS NOW.");
					//session.invalidate();
				}
				else
				{
					System.out.println("..................viewModeJsp to return");
					model = new ModelAndView(viewModeJspToReturn, "applicant", applicant);
				}
				
			}
			else
			{
				System.out.println("..................enter/update ModeJsp to return");
				model = new ModelAndView(jspToReturn, "applicant", applicant);
			}
			
		}
		else
		{
			model = new ModelAndView(jspToReturn, "applicant", applicant);
		}
		
		model.addObject("errorsStep3", errors);
		return model;
	}
	
	public ModelAndView saveYearSemWiseMarks(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		HttpSession session = request.getSession(false);
		if(session == null)
		{
			return new ModelAndView("errorPages/SessionExpired","message","Your Session has been expired.");
		}
		
		List<String> errors = new ArrayList<String>();
		
		String[] yearSems = request.getParameterValues("yearSem");
		String[] examinations = request.getParameterValues("examination");
		String[] obtainedMarks = request.getParameterValues("obtainedMarks");
		String[] totalMarks = request.getParameterValues("totalMarks");
		String[] university = request.getParameterValues("university");
		String[] passingYear = request.getParameterValues("passingYear");
		String[] appNumbers = request.getParameterValues("appNumber");
		
		List<YearSemesterWiseMarks> yearSemsWiseMarksList = new ArrayList<YearSemesterWiseMarks>();
		List<String> checkList = new ArrayList<String>();
		
		for(int i = 0; i < yearSems.length; i++)
		{
			if(!(yearSems[i].trim().equals("")))
			{
				YearSemesterWiseMarks obj = new YearSemesterWiseMarks();
				obj.setApplicationNumber(appNumbers[i]);
				obj.setYearSemester(yearSems[i]);
				obj.setExamination(examinations[i]);
				obj.setObtainedMarks(obtainedMarks[i]);
				obj.setTotalMarks(totalMarks[i]);
				obj.setUniversity(university[i]);
				obj.setPassingYear(passingYear[i]);
				
				try
				{
					Double obtainedMarksDbl = Double.parseDouble(obj.getObtainedMarks());
					Double totalMarksDbl = Double.parseDouble(obj.getTotalMarks());
					
					if(obtainedMarksDbl > totalMarksDbl)
					{
						errors.add("Obtained marks cannot be greater than total marks.");
						break;
					}
				}
				catch(Exception ex)
				{
					errors.add("Please enter number in obtained marks and total marks fields.");
					break;
				}
				
				checkList.add(yearSems[i]);
				yearSemsWiseMarksList.add(obj);
			}
				
		}
		
		for(String cli : checkList)
		{
			int occuring = 0;
			for(String str : checkList)
			{
				if(str.equals(cli))
				{
					occuring = occuring + 1;
				}
				
				if(occuring > 1)
				{
					errors.add("Please do not enter duplicate year/semester.");
					break;
				}
			}
		}
		
		/**Set checkSet = new HashSet(Arrays.asList(checkList));
		if(checkSet.size() < checkList.size())
		{
			errors.add("Please do not enter duplicate year/semester");
		}**/
		
		ModelAndView model = getPaymentPanel(request, response);
		model.setViewName("application/TabController");
		
		if(errors.size()>0)
		{
			model.addObject("message", errors.get(0));
		}
		else
		{
			try
			{
				String message = applicationDAO.saveYearSemWiseMarks(yearSemsWiseMarksList);
				model.addObject("message", message);
			}
			catch(Exception ex)
			{
				model.addObject("message", "Please do not enter duplicate or wrong values.");
			}
			
		}
		
		return model;
	}

}