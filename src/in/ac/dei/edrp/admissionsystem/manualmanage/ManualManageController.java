package in.ac.dei.edrp.admissionsystem.manualmanage;

import java.util.ArrayList;
import java.util.List;

import in.ac.dei.edrp.admissionsystem.account.AccountController;
import in.ac.dei.edrp.admissionsystem.account.AccountDAO;
import in.ac.dei.edrp.admissionsystem.application.ApplicationDAO;
import in.ac.dei.edrp.admissionsystem.common.beans.AcademicDetail;
import in.ac.dei.edrp.admissionsystem.common.beans.Applicant;
import in.ac.dei.edrp.admissionsystem.common.beans.ProgramWiseDetail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class ManualManageController extends MultiActionController
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

	public ModelAndView editApplication(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		ModelAndView model = new ModelAndView("manualmanage/ApplicationNumberPrompt");
		return model;
	}
	
	public ModelAndView getEditScreen(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String applicationNumber = request.getParameter("applicationNumber");
		String universityID = getServletContext().getInitParameter("universityID");
		
		if(applicationNumber == null)
		{
			ModelAndView model = new ModelAndView("manualmanage/ApplicationNumberPrompt","error","PLEASE ENTER APPLICATION NUMBER.");
			return model;
		}
		
		
		Applicant applicant = new Applicant();
		applicant.setApplicationNumber(applicationNumber);
		applicant.setUniversityID(universityID);
		
		applicant = applicationDAO.getApplicantDetails(applicant);
		
		
		if(applicant.getApplicationStatus() == null || (!(applicant.getApplicationStatus().trim().equalsIgnoreCase("D"))))
		{
			ModelAndView model = new ModelAndView("manualmanage/ApplicationNumberPrompt","error","PART-1 IS NOT COMPLETED.");
			return model;
		}
		
		if(applicant.getPhase2Edited()!=null && applicant.getPhase2Edited().trim().equalsIgnoreCase("Y"))
		{
			ModelAndView model = new ModelAndView("manualmanage/EditApplication","applicant",applicant);
			return model;
		}
		
		ModelAndView model = new ModelAndView("manualmanage/ApplicationNumberPrompt");
		return model;
	}
	
	public ModelAndView saveManualAcademicDetails(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView model = null;
		HttpSession session = request.getSession(false);
		if(session==null)
		{
			model = new ModelAndView("errorPages/SessionExpired","message","Your Session has been expired.");
			return model;
		}
		
		Applicant sessionApplicant = new Applicant();
		sessionApplicant.setApplicationNumber(request.getParameter("applicationNumber"));
		sessionApplicant.setUniversityID(getServletContext().getInitParameter("universityID"));
		sessionApplicant = applicationDAO.getApplicantDetails(sessionApplicant);
		
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
			
			String phase2Edited = sessionApplicant.getPhase2Edited();
			String resultResponse = "";
			
			if(errors.size()>0)
			{
				model = new ModelAndView("manualmanage/EditApplication");
				model.addObject("errors", errors);
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
					model = new ModelAndView("manualmanage/ManualAcademicPerformanceViewMode", "applicant", sessionApplicant);
					return model;
				}
				else
				{
					model = new ModelAndView("manualmanage/ApplicationNumberPrompt", "error", "There is some Error.");
				}

			}
			
			
		}
		catch(Exception ex)
		{
			logObj.error("EXCEPTION FOR APPLICATION NUMBER : "+sessionApplicant.getApplicationNumber()+"\n"+ ex);
			model = new ModelAndView("manualmanage/ApplicationNumberPrompt", "error", "There is some Exception in saving your Academic Details.");
			session.invalidate();
		}
		
		return model;
				
	}

	public boolean isNullOrBlank(String param)
	{
		return (param == null || param.trim().equals(""));
	}
}
