package in.ac.dei.edrp.admissionsystem.application;




import in.ac.dei.edrp.admissionsystem.common.ApplicationDetail;
import in.ac.dei.edrp.admissionsystem.common.CommonBean;
import in.ac.dei.edrp.admissionsystem.common.YearSemesterWiseMarks;
import in.ac.dei.edrp.admissionsystem.common.beans.AcademicDetail;
import in.ac.dei.edrp.admissionsystem.common.beans.AppForm;
import in.ac.dei.edrp.admissionsystem.common.beans.Applicant;
import in.ac.dei.edrp.admissionsystem.common.beans.ApplicationForm;
import in.ac.dei.edrp.admissionsystem.common.beans.ApplicationProgram;
import in.ac.dei.edrp.admissionsystem.common.beans.Component;
import in.ac.dei.edrp.admissionsystem.common.beans.Form;
import in.ac.dei.edrp.admissionsystem.common.beans.GroupPaper;
import in.ac.dei.edrp.admissionsystem.common.beans.GroupWiseComponent;
import in.ac.dei.edrp.admissionsystem.common.beans.InputBean;
import in.ac.dei.edrp.admissionsystem.common.beans.Program;
import in.ac.dei.edrp.admissionsystem.common.beans.ProgramForm;
import in.ac.dei.edrp.admissionsystem.common.beans.ProgramPaper;
import in.ac.dei.edrp.admissionsystem.common.beans.ProgramWiseDetail;
import in.ac.dei.edrp.admissionsystem.common.beans.StudentPaper;
import in.ac.dei.edrp.admissionsystem.common.beans.StudentProgramPaper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class ApplicationDAOImpl extends SqlMapClientDaoSupport implements ApplicationDAO{

	private Logger loggerObject = Logger.getLogger(ApplicationDAO.class);
	private TransactionTemplate transactionTemplate;
	
	/** defining setter method for object of TransactionTemplate */
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	
	public List<CommonBean> getProgramForms(String universityID) {
		List<CommonBean> forms = null;
		try
		{
			forms = getSqlMapClientTemplate().queryForList("application.getProgramForms", universityID);
		}
		catch(Exception ex)
		{
			
		}
		return forms;
	}


	
	public List<Program> getFormPrograms(Form form) {
		List<Program> programs = null;
		try
		{
			programs = getSqlMapClientTemplate().queryForList("application.getFormPrograms", form);
			for(int j=0; j < programs.size(); j++)
			{
				CommonBean bean = new CommonBean();
				bean.setCode(programs.get(j).getProgramID());
				bean.setUniversityID(form.getUniversityID());
				List<CommonBean> paperOptions = getSqlMapClientTemplate().queryForList("application.getProgramPapOptions", bean);
				if(paperOptions.size()>0)
				{
					programs.get(j).setPaperOptAvailable("Y");
				}
				programs.get(j).setNoOfPprOptions(paperOptions.size());
				if(paperOptions.size()==1)
				{
					programs.get(j).setTheOnlyPprCode(paperOptions.get(0).getCode());
					programs.get(j).setTheOnlyPprDesc(paperOptions.get(0).getDescription());
				}
				programs.get(j).setPaperOptions(paperOptions);
			}
			
		}
		catch(Exception ex)
		{
			
		}
		return programs;
	}

	public Applicant getApplicationForm(Applicant applicant) 
	{
		String applicationNumber = applicant.getApplicationNumber();
		ApplicationForm applicationForm = applicant.getApplicationForm();
		List<GroupWiseComponent> groupWiseComponents = new ArrayList<GroupWiseComponent>();
		List<ProgramForm> programForms = new ArrayList<ProgramForm>();
		
		try
		{
			List<GroupWiseComponent> gwComponents = getSqlMapClientTemplate().queryForList("application.getComponentGroups", applicationNumber);
			
			for(GroupWiseComponent gwc : gwComponents)
			{
				InputBean inputBean = new InputBean();
				inputBean.setParam1(applicationNumber);
				inputBean.setParam2(gwc.getCode());
				List<Component> components = getSqlMapClientTemplate().queryForList("application.getProgramComponents", inputBean);
				List<Component> componentsForGATE = getSqlMapClientTemplate().queryForList("application.getProgramComponentsForGATE", inputBean);
				GroupWiseComponent groupWiseComponent = new GroupWiseComponent();
				groupWiseComponent.setCode(gwc.getCode());
				groupWiseComponent.setDescription(gwc.getDescription());
				groupWiseComponent.setShowOptions(gwc.getShowOptions());
				groupWiseComponent.setUniversityToAsk(gwc.getUniversityToAsk());
				if(groupWiseComponent.getShowOptions() == 1)
				{
					for(int i = 0 ; i < components.size(); i++)
					{
						List<CommonBean> examinationOptions = getSqlMapClientTemplate().queryForList("application.getExaminationOptions", components.get(i));
						components.get(i).setOptionsList(examinationOptions);
						if(components.get(i).getComponentID().trim().equalsIgnoreCase("UG"))
						{
							List<CommonBean> subjectOptions = getSqlMapClientTemplate().queryForList("application.getSubjectOptions");
							components.get(i).setSubjectOptions(subjectOptions);
						}
					}
					
				}
				
				groupWiseComponent.setComponents(components);
				groupWiseComponent.setGateComponents(componentsForGATE);
				groupWiseComponents.add(groupWiseComponent);
			}
		
			for(int i=0; i < applicant.getApplicationForm().getProgramWiseDetails().size(); i++)
			{
				String programID = applicant.getApplicationForm().getProgramWiseDetails().get(i).getProgramID();
				List<CommonBean> branches = getSqlMapClientTemplate().queryForList("application.getBranches", programID);
				applicant.getApplicationForm().getProgramWiseDetails().get(i).setProgramBranches(branches);
				
				if(branches.size()>0)
				{
					applicant.getApplicationForm().getProgramWiseDetails().get(i).setPreferredBranchAvailable("Y");
					applicationForm.setBranchAvailability("Y");
				}
				else
				{
					applicant.getApplicationForm().getProgramWiseDetails().get(i).setPreferredBranchAvailable("N");
					
				}
				
				List<CommonBean> educationModes = getSqlMapClientTemplate().queryForList("application.getEducationModes", programID);
				List<CommonBean> regStudyCenters = getSqlMapClientTemplate().queryForList("application.getRegStudyCenters", programID);
				
				List<ProgramWiseDetail> pwd = getSqlMapClientTemplate().queryForList("application.getOtherDetailsForAcadPerf", applicant.getApplicationForm().getProgramWiseDetails().get(i) );
				
				applicant.getApplicationForm().getProgramWiseDetails().get(i).setEducationModes(educationModes);
				applicant.getApplicationForm().getProgramWiseDetails().get(i).setRegStudyCenters(regStudyCenters);
				applicant.getApplicationForm().getProgramWiseDetails().get(i).setBranch1(pwd.get(0).getBranch1());
				applicant.getApplicationForm().getProgramWiseDetails().get(i).setBranch2(pwd.get(0).getBranch2());
				applicant.getApplicationForm().getProgramWiseDetails().get(i).setBranch3(pwd.get(0).getBranch3());
				applicant.getApplicationForm().getProgramWiseDetails().get(i).setBranch1Name(pwd.get(0).getBranch1Name());
				applicant.getApplicationForm().getProgramWiseDetails().get(i).setBranch2Name(pwd.get(0).getBranch2Name());
				applicant.getApplicationForm().getProgramWiseDetails().get(i).setBranch3Name(pwd.get(0).getBranch3Name());
				
				//Added by Arjun on 25-05-2021
				applicant.getApplicationForm().getProgramWiseDetails().get(i).setBranch4(pwd.get(0).getBranch4());
				applicant.getApplicationForm().getProgramWiseDetails().get(i).setBranch5(pwd.get(0).getBranch5());
				applicant.getApplicationForm().getProgramWiseDetails().get(i).setBranch4Name(pwd.get(0).getBranch4Name());
				applicant.getApplicationForm().getProgramWiseDetails().get(i).setBranch5Name(pwd.get(0).getBranch5Name());
				
				
				applicant.getApplicationForm().getProgramWiseDetails().get(i).setCcActivities(pwd.get(0).getCcActivities());
				applicant.getApplicationForm().getProgramWiseDetails().get(i).setHostelRequired(pwd.get(0).getHostelRequired());
				applicant.getApplicationForm().getProgramWiseDetails().get(i).setEverExpelled(pwd.get(0).getEverExpelled());
				applicant.getApplicationForm().getProgramWiseDetails().get(i).setStaffWardFlag(pwd.get(0).getStaffWardFlag());
				applicant.getApplicationForm().getProgramWiseDetails().get(i).setStaffCode(pwd.get(0).getStaffCode());
				applicant.getApplicationForm().getProgramWiseDetails().get(i).setFatherIncome(pwd.get(0).getFatherIncome());
				applicant.getApplicationForm().getProgramWiseDetails().get(i).setMotherIncome(pwd.get(0).getMotherIncome());
				applicant.getApplicationForm().getProgramWiseDetails().get(i).setGuardianIncome(pwd.get(0).getGuardianIncome());
				applicant.getApplicationForm().getProgramWiseDetails().get(i).setDeiStudent(pwd.get(0).getDeiStudent());
				applicant.getApplicationForm().getProgramWiseDetails().get(i).setLastExamFrom(pwd.get(0).getLastExamFrom());
				applicant.getApplicationForm().getProgramWiseDetails().get(i).setCo_ed(pwd.get(0).getCo_ed());
			}
			
			List<CommonBean> boards = getSqlMapClientTemplate().queryForList("application.getBoard");
			
			
			applicationForm.setBoards(boards);
			
			
			
			applicationForm.setGroupWiseComponents(groupWiseComponents);
			applicationForm.setProgramForms(programForms);
		}
		catch(Exception ex)
		{
			System.out.println(ex+"");
		}
		
		applicant.setApplicationForm(applicationForm);
		return applicant;
	}



	
	public Applicant savePersonalDetails(final Applicant applicant) {
		
		transactionTemplate.execute(new TransactionCallback()
		{
			Object savePoint ;
			public Object doInTransaction(TransactionStatus ts) {
				
				try
				{
					savePoint= new Object();
					savePoint = ts.createSavepoint();
					applicant.setTabStatus("1");
					getSqlMapClientTemplate().update("application.updatePersonalDetails", applicant);
					getSqlMapClientTemplate().update("application.updateCORAddressDetails", applicant);
					getSqlMapClientTemplate().update("application.updatePERAddressDetails", applicant);
					getSqlMapClientTemplate().update("application.updateSAS", applicant);
					getSqlMapClientTemplate().update("application.updateCOS", applicant);
					
					
				}
				catch(Exception ex)
				{
					ts.rollbackToSavepoint(savePoint);							
					loggerObject.error("EXCEPTION in ApplicationController:savePersonalDetails : "+ ex);
				}

				return null;
			}
			
		});
				 		
		return getApplicantDetails(applicant);
	}

	
	public List<Form> getFormsAndPrograms(Applicant applicant) {
		
		List<Form> forms = new ArrayList<Form>();
		try
		{
			List<CommonBean> programForms = getSqlMapClientTemplate().queryForList("application.getProgramForms", applicant);
			for(int i = 0; i<programForms.size(); i++)
			{
				System.out.println("Form is : "+programForms.get(i).getCode()+" "+programForms.get(i).getDescription());
				Form form = new Form();
				form.setFormNumber(programForms.get(i).getCode());
				form.setFormName(programForms.get(i).getDescription());
				form.setApplicationNumber(applicant.getApplicationNumber());
				form.setUniversityID(applicant.getUniversityID());
				List<Program> programs = getSqlMapClientTemplate().queryForList("application.getFormPrograms", form);
				for(int j=0; j < programs.size(); j++)
				{
					CommonBean bean = new CommonBean();
					bean.setCode(programs.get(j).getProgramID());
					bean.setUniversityID(form.getUniversityID());
					List<CommonBean> paperOptions = getSqlMapClientTemplate().queryForList("application.getProgramPapOptions", bean);
					if(paperOptions.size()>0)
					{
						programs.get(j).setPaperOptAvailable("Y");
					}
					programs.get(j).setPaperOptions(paperOptions);
				}
				
				form.setPrograms(programs);
				for(int j=0; j<programs.size(); j++)
				{
				System.out.println("Programs are : "+programs.get(j).getProgramID());
				}
				forms.add(form);
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex+"");
		}
		return forms;
	}
	
	
	public Applicant getApplicantDetails(Applicant applicant)
	{
		Applicant applicantToReturn = null;
		try
		{	
			String tabStatusSTR = (String) getSqlMapClientTemplate().queryForObject("account.getTabStatus", applicant);
			int tabStatus = Integer.parseInt(tabStatusSTR);
			List<CommonBean> allCategories = getSqlMapClientTemplate().queryForList("account.getAllCategories", applicant);
			List<CommonBean> allGenders = getSqlMapClientTemplate().queryForList("account.getAllGenders", applicant);
			List<CommonBean> allBloodGroups = getSqlMapClientTemplate().queryForList("account.getAllBloodGroups", applicant);
			List<Applicant> applicants =  getSqlMapClientTemplate().queryForList("account.getUserDetails",applicant);
			if(applicants.size()==1)
			{
				applicantToReturn = applicants.get(0);
				applicantToReturn.setDd(String.format("%02d", Integer.parseInt(applicantToReturn.getDd())));
				applicantToReturn.setMm(String.format("%02d", Integer.parseInt(applicantToReturn.getMm())));
				applicantToReturn.setAllCategories(allCategories);
				applicantToReturn.setAllGenders(allGenders);
				applicantToReturn.setAllBloodGroups(allBloodGroups);
				
				if(tabStatus>1)
				{
					List<CommonBean> programAndMGroups = getSqlMapClientTemplate().queryForList("account.getProgramAndMGroup",applicant);
					List<String> programList =  new ArrayList<String>();
					List<String> optedPaperList = new ArrayList<String>();
					for(CommonBean obj : programAndMGroups)
					{
						programList.add(obj.getCode());
						optedPaperList.add(obj.getDescription());
					}
					applicantToReturn.setPrograms(programList);
					applicantToReturn.setOptedPapers(optedPaperList);
					applicantToReturn = getProgramPaperOptions(applicantToReturn, false);
					
					if(applicantToReturn.getPhase2Edited()!=null && applicantToReturn.getPhase2Edited().trim().equalsIgnoreCase("Y"))
					{
						applicantToReturn = getApplicationForm(applicantToReturn);
					}
			
				}
			
			}
			
		}
		catch(Exception ex)
		{
			System.out.println(ex+"");
		}
				
		
		return applicantToReturn;
	}
	
	


	
	public Applicant savePaperOptions(final Applicant applicant) {
		
		for(int n = 0 ; n < applicant.getApplicationForm().getProgramWiseDetails().size(); n++)
		{
			int updatedRecords = 0;
			String registrationNumber = null;
			while(updatedRecords == 0)
			{
				CommonBean regBean = (CommonBean) getSqlMapClientTemplate().queryForObject("application.getRegNumValue", applicant.getUniversityID());
				registrationNumber = regBean.getOtherProperty1()+String.format("%07d", Integer.parseInt(regBean.getCode()));
				updatedRecords = getSqlMapClientTemplate().update("application.updateRegNumValue", regBean);
				applicant.getApplicationForm().getProgramWiseDetails().get(n).setRegistrationNumber(registrationNumber);
			}
		}
		
		transactionTemplate.execute(new TransactionCallback()
		{
			Object savePoint ;
			public Object doInTransaction(TransactionStatus ts) {
				//int noOfPrograms = programWiseDetails.size();
				try
				{
				
					for(int n = 0 ; n < applicant.getApplicationForm().getProgramWiseDetails().size(); n++)
					{
						ProgramWiseDetail programWiseDetail = applicant.getApplicationForm().getProgramWiseDetails().get(n);
						
						/** Commented By Arjun to resolve Transaction LOCK
						int updatedRecords = 0;
						String registrationNumber = null;
						while(updatedRecords == 0)
						{
							CommonBean regBean = (CommonBean) getSqlMapClientTemplate().queryForObject("application.getRegNumValue", applicant.getUniversityID());
							registrationNumber = regBean.getOtherProperty1()+String.format("%07d", Integer.parseInt(regBean.getCode()));
							updatedRecords = getSqlMapClientTemplate().update("application.updateRegNumValue", regBean);
						}
						
						programWiseDetail.setRegistrationNumber(registrationNumber);
						**/
						programWiseDetail.setSessionStartDate(applicant.getSessionStartDate());
						programWiseDetail.setSessionEndDate(applicant.getSessionEndDate());
						programWiseDetail.setUserID(applicant.getUserID());
						programWiseDetail.setUniversityID(applicant.getUniversityID());
						programWiseDetail.setApplicationNumber(applicant.getApplicationNumber());
						programWiseDetail.setCategoryCode(applicant.getCategory());
						programWiseDetail.setGenderCode(applicant.getGender());
						programWiseDetail.setTestNumber(applicant.getApplicationNumber());
						System.out.println(programWiseDetail.getCategoryCode()+programWiseDetail.getGenderCode()+programWiseDetail.getPaperMainGroup());
						String cosValue = (String) getSqlMapClientTemplate().queryForObject("application.getCosValue", programWiseDetail);
						programWiseDetail.setCosValue(cosValue);
						getSqlMapClientTemplate().insert("application.insertSR", programWiseDetail);
						getSqlMapClientTemplate().insert("application.insertAPR", programWiseDetail);
						getSqlMapClientTemplate().insert("application.insertSTN", programWiseDetail);
						
						if(!(programWiseDetail.getPaperMainGroup().trim().equalsIgnoreCase("")))
						{
							for(StudentProgramPaper stdPrgPaper : programWiseDetail.getStudentProgramPapers())
							{	
								stdPrgPaper.setRegistrationNumber(programWiseDetail.getRegistrationNumber());
								stdPrgPaper.setSessionStartDate(programWiseDetail.getSessionStartDate());
								stdPrgPaper.setSessionEndDate(programWiseDetail.getSessionEndDate());
								stdPrgPaper.setUserID(programWiseDetail.getUserID());
								getSqlMapClientTemplate().insert("application.insertStudentPaper", stdPrgPaper);
								System.out.println(stdPrgPaper.getMainGroup()+" "+stdPrgPaper.getProgramID()+" "+stdPrgPaper.getGrouping()+" "+stdPrgPaper.getPaperCode());
							}
							
						}
						else
						{
							//Do Nothing. Just Need to continue the loop.
						}
						
					}
					
					applicant.setApplicationStatus("A");
					applicant.setTabStatus("2");
					getSqlMapClientTemplate().insert("application.updateSAS", applicant);
					 
					
				}
				catch(Exception ex)
				{
					ts.rollbackToSavepoint(savePoint);							
					System.out.println(ex+"");
				}

				return null;
			}
			
		});
				
		return getApplicantDetails(applicant);

	}

	public Applicant getProgramPaperOptions(Applicant applicant, boolean reapplyFlag) 
	{

		ApplicationForm applicationForm = new ApplicationForm();System.out.println("IN DAOIMPL : "+applicant.getApplicationNumber()+" "+applicant.getUniversityID()+" "+applicant.getPrograms().get(0));
		List<ProgramWiseDetail> programWiseDetails = getSqlMapClientTemplate().queryForList("account.getApplicantPrograms", applicant);
		System.out.println("Program Wise Details Size : "+programWiseDetails.size());
		System.out.println("Opted Papers Size : "+applicant.getOptedPapers().size()+" "+applicant.getOptedPapers().get(0));
		List<String> paperMainGrps = new ArrayList<String>();
		if(Integer.parseInt(applicant.getTabStatus())>1 && !(reapplyFlag))
		{
			System.out.println("In 1");
			for(ProgramWiseDetail prgDtl : programWiseDetails)
			{
				paperMainGrps.add(prgDtl.getPaperMainGroup());
			}
		}
		else
		{
			System.out.println("In 2");
			paperMainGrps = applicant.getOptedPapers();
		}
		
		for(int i=0; i < programWiseDetails.size(); i++)
		{
			System.out.println("PROGRAM ID : "+programWiseDetails.get(i).getProgramName()+" SUBJECT : "+paperMainGrps.get(i));
		}
		for(int i=0; i < programWiseDetails.size(); i++)
		{
			ApplicationProgram appProgram = new ApplicationProgram();
			appProgram.setProgramID(programWiseDetails.get(i).getProgramID());
			appProgram.setApplicationNumber(applicant.getApplicationNumber());
			ProgramWiseDetail programWiseDetail = programWiseDetails.get(i);
			
			//Program Papers Start
			if(paperMainGrps.get(i)==null || (paperMainGrps.get(i).trim().equals("")))
			{
				programWiseDetails.get(i).setPaperMainGroup("");
			}
			else
			{
				ProgramPaper pp = new ProgramPaper();
				pp.setMainGroup(paperMainGrps.get(i));
				programWiseDetail.setPaperMainGroup(pp.getMainGroup());//Newly Added on 1-1-2015
				pp.setProgramID(programWiseDetails.get(i).getProgramID());
				pp.setUniversityID(applicant.getUniversityID());
				pp.setProgramName(programWiseDetails.get(i).getProgramName());
				pp.setApplicationNumber(applicant.getApplicationNumber());
				List<GroupPaper> paperGroups = getSqlMapClientTemplate().queryForList("application.getPaperGrouping", pp);
				List<GroupPaper> groupPapers = new ArrayList<GroupPaper>();
				for(int j=0; j < paperGroups.size(); j++)
				{
					pp.setGrouping(paperGroups.get(j).getGrouping());
					pp.setMainGroupName(paperGroups.get(j).getMainGroupName());
					GroupPaper groupPaper = new GroupPaper();
					groupPaper.setApplicationNumber(applicant.getApplicationNumber());
					groupPaper.setProgramID(pp.getProgramID());
					groupPaper.setMainGroup(pp.getMainGroup());
					groupPaper.setGrouping(paperGroups.get(j).getGrouping());
					groupPaper.setUniversityID(applicant.getUniversityID());
					groupPaper.setMaxChoice(paperGroups.get(j).getMaxChoice());
					groupPaper.setMinChoice(paperGroups.get(j).getMinChoice());
					groupPaper.setMainGroupName(paperGroups.get(j).getMainGroupName());
					groupPaper.setPaperGroupID(pp.getProgramID()+pp.getMainGroup()+paperGroups.get(j));
					List<CommonBean> allPapers = getSqlMapClientTemplate().queryForList("application.getAllGroupPapers", pp);
					List<CommonBean> selectedPapers = getSqlMapClientTemplate().queryForList("application.getSelectedGroupPapers", pp);
					groupPaper.setAllPapers(allPapers);
					groupPaper.setSelectedPapers(selectedPapers);
					groupPaper.setAllPaperSize(allPapers.size());
					groupPapers.add(groupPaper);
					if(allPapers.size()>0)
					{
						applicationForm.setPapersAvailability("Y");
					}
				}
				pp.setGroupPapers(groupPapers);
				pp.setGroupPapersSize(groupPapers.size());
				programWiseDetails.get(i).setProgramPaper(pp);
				applicant.setExamCenter1(programWiseDetails.get(i).getExamCenter1());
				//applicant.setExamCenter2(applicant.getApplicationForm().getProgramWiseDetails().get(0).getExamCenter2());
				//applicant.setExamCenter3(applicant.getApplicationForm().getProgramWiseDetails().get(0).getExamCenter3());
				applicant.setExamCenter1Name(programWiseDetails.get(i).getExamCenter1Name());
				
			}
			
		}//Loop Ends
		
		applicationForm.setProgramWiseDetails(programWiseDetails);
		applicant.setApplicationForm(applicationForm);
		applicant.setExaminationCenters(getExaminationCenters(applicant.getUniversityID()));
		applicant.getApplicationForm().setFormNumber(programWiseDetails.get(0).getFormNumber());
		applicant.setStaffWardFlag(applicant.getApplicationForm().getProgramWiseDetails().get(0).getStaffWardFlag());
		applicant.setStaffCode(applicant.getApplicationForm().getProgramWiseDetails().get(0).getStaffCode());
		System.out.println("Branches Availability : "+applicationForm.getBranchAvailability());
		System.out.println("Papers Availability : "+applicationForm.getPapersAvailability());
		return applicant;
		
	}

	@SuppressWarnings("unchecked")
	public String getPaymentType()
	{
		List<CommonBean> result = getSqlMapClientTemplate().queryForList("application.getPaymentType",null);
		if(result.size()>0)
		{
			return result.get(0).getCode();
		}
		else
		{
			return "Payment type not specified";
		}
		
	}

	@SuppressWarnings("unchecked")
	public String getPaymentPerProgram(Applicant input)
	{
		List<CommonBean> result=new ArrayList<CommonBean>();
		try
		{
		 	result = getSqlMapClientTemplate().queryForList("application.getTotalFeePP",input);
		}
		catch (Exception e) 
		{
			System.out.println("in  getPaymentPerProgram  "+e);
		}
		if(result.size()>0)
		{
			return result.get(0).getDescription();
		}
		else
		{
			return "Per-program fee not specified";
		}
		
	}
	

	@SuppressWarnings("unchecked")
	public String getPaymentLumpsum(Applicant input){
		List<CommonBean> result = getSqlMapClientTemplate().queryForList("application.getTotalFeeLS",input);
if(result.size()>0){
	return result.get(0).getDescription();
}else{
	return "Lump Sum fee not specified";
}
		
	}


	public List<CommonBean> getExaminationCenters(String universityID)
	{
		List<CommonBean> examCenters = new ArrayList<CommonBean>();
		try
		{
			examCenters = getSqlMapClientTemplate().queryForList("application.getExamCenters",universityID);
		}
		catch (Exception e) 
		{
			System.out.println("in  getExaminationCenters  "+e);
		}
		return examCenters;
	}


	
	public List<String> getDuplicatePrograms(Applicant applicant) {
		
		List<String> programs = new ArrayList<String>();
		try
		{
			programs = getSqlMapClientTemplate().queryForList("application.getDuplicatePrograms", applicant);
		}
		catch(Exception ex)
		{
			System.out.println("in  getAppliedPrograms  "+ex);
		}
		return programs;
	}


	
	public Applicant removeProgram(final String[] programs, final Applicant applicant) {
		transactionTemplate.execute(new TransactionCallback()
		{
			Object savePoint ;
			public Object doInTransaction(TransactionStatus ts) {
				
				try
				{
					for(int i = 0; i < programs.length ; i++)
					{
						ApplicationProgram applicationProgram = new ApplicationProgram();
						applicationProgram.setProgramID(programs[i]);
						applicationProgram.setApplicationNumber(applicant.getApplicationNumber());
						String registrationNumber = getSqlMapClient().queryForObject("application.getRegistrationNumber", applicationProgram).toString();
						getSqlMapClient().delete("application.deleteSTN", registrationNumber);
						getSqlMapClient().delete("application.deleteStdPaper", registrationNumber);
						getSqlMapClient().delete("application.deleteStdRegistration", registrationNumber);
						getSqlMapClient().delete("application.deleteAPR", registrationNumber);
					
					}
					
				}
				catch(Exception ex)
				{
					ts.rollbackToSavepoint(savePoint);							
					System.out.println(ex+"");
				}

				return null;
			}
			
		});
		
		Applicant applicantToReturn = getApplicantDetails(applicant);
		if(applicantToReturn.getApplicationForm().getProgramWiseDetails().size()<=0)
		{
			try
			{
				applicantToReturn.setTabStatus("1");
				applicantToReturn.setApplicationStatus(null);
				getSqlMapClient().update("application.resetSAS", applicantToReturn);
				
			}
			catch(Exception ex)
			{
				System.out.println(ex+"");
				applicantToReturn = null;
			}
		}
		return applicantToReturn;
	}


	
	public String validatePersonalDetails(Applicant applicant, Applicant sessionApplicant) 
	{
		String errors = "";
		if(invalidString(applicant.getApplicantName()))
		{
			errors=errors+"<li>Please Enter Applicant Name.</li>";
		}
		if(invalidString(applicant.getFatherName()))
		{
			errors=errors+"<li>Please Enter Father Name.</li>";
		}
		if(invalidString(applicant.getMotherName()))
		{
			errors=errors+"<li>Please Enter Mother Name.</li>";
		}
		if(invalidString(applicant.getCategory()))
		{
			errors=errors+"<li>Please Enter Category</li>";
		}
		if(invalidString(applicant.getGender()))
		{
			errors=errors+"<li>Please Enter Gender</li>";
		}
		/**if(invalidString(applicant.getReligion()))
		{
			errors=errors+"<li>Please Enter Religion</li>";
		}**/
		if(invalidString(applicant.getPwd()))
		{
			errors=errors+"<li>Please Enter Physical Disability.</li>";
		}
		/**
		if(!(applicant.getPrimaryEmailID().trim().equals(sessionApplicant.getPrimaryEmailID())))
		{
			errors=errors+"<li>Email ID cannot be changed.</li>";
		}**/
		
		if(invalidString(applicant.getHomePhone()) || applicant.getHomePhone().length()<10)
		{
			errors=errors+"<li>Please Enter Valid Contact No.</li>";
		}
		
		if(invalidString(applicant.getCorCountry()))
		{
			errors=errors+"<li>Please Select Country.</li>";
		}
		else
		{
			if(applicant.getCorCountry().trim().equalsIgnoreCase("IN"))
			{
				if(invalidString(applicant.getCorState()))
				{
					errors=errors+"<li>Please Select State.</li>";
				}
				
				if(invalidString(applicant.getCorCity()))
				{
					errors=errors+"<li>Please Enter City.</li>";
				}
				
				if(invalidString(applicant.getCorPincode()) || applicant.getCorPincode().length()<6)
				{
					errors=errors+"<li>Please Enter Valid Pincode.</li>";
				}
				
				if(invalidString(applicant.getCorDistrict()))
				{
					errors=errors+"<li>Please Enter District.</li>";
				}
			}
		}
		
		if(invalidString(applicant.getCorAddressL1()))
		{
			errors=errors+"<li>Please Enter Address Line 1.</li>";
		}
		
		/**if(invalidString(applicant.getCorAddressL2()))
		{
			errors=errors+"<li>Please Enter Address Line 2.</li>";
		}**/
		
		if(!(errors.trim().equals("")))
		{
			errors = "<ul>"+errors+"</li></ul>";
		}
		else
		{
			//Do nothing
		}
		return errors;
	}

	public boolean invalidString(String param)
	{
		return (param==null || param.trim().equalsIgnoreCase(""));
	}


	
	public List<String> getAppliedPrograms(Applicant applicant) {
		List<String> programs = new ArrayList<String>();
		try
		{
			programs = getSqlMapClientTemplate().queryForList("application.getAppliedPrograms", applicant);
		}
		catch(Exception ex)
		{
			System.out.println("in  getAppliedPrograms  "+ex);
		}
		return programs;
	}


	public ApplicationDetail getApplicationDetails(ApplicationDetail input) 
	{
		ApplicationDetail output = null;
		try
		{
			output = (ApplicationDetail) getSqlMapClient().queryForObject("application.getApplicationDetail", input);
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return output;
	}


	
	public String getApplicationFee(Applicant applicant) 
	{
		String fees = null;
		try
		{
			List<CommonBean> result = getSqlMapClientTemplate().queryForList("application.getPaymentType", applicant);
			if(result.size()>0)
			{
				String paymentType = result.get(0).getCode();
				int noOfRecords = 0;
				if(paymentType.equalsIgnoreCase("PP"))
				{
					fees = getPaymentPerProgram(applicant);
					applicant.setApplicationFee(fees);
					noOfRecords = getSqlMapClient().update("application.updateApplicationFee", applicant);
				}
				else if (paymentType.equalsIgnoreCase("LS"))
				{
					fees = getPaymentLumpsum(applicant);
					applicant.setApplicationFee(fees);
					noOfRecords = getSqlMapClient().update("application.updateApplicationFee", applicant);
				}
				else
				{
					fees = "Incorrect payment type";	
				}

				if(noOfRecords  > 1)
				{
					fees = "error";
				}
			}
			else
			{
				fees = "Payment type not specified";
			}

		}
		catch(Exception ex)
		{
			loggerObject.error("THERE IS EXCEPTION IN ApplicationDaoImpl:getApplicationFee for Application Number : "+applicant.getApplicationNumber()+" : Exception is " + ex );
			fees = "error";
		}
		return fees;
	}
	
	
	public String insertAcademicDetails(final List<ProgramWiseDetail> programWiseDetails) throws Exception
	{	
		final String resultResponse = "Y";
		transactionTemplate.execute(new TransactionCallback()
		{
			Object savePoint ;
			public Object doInTransaction(TransactionStatus ts) 
			{
				try
				{
					savePoint= new Object();
					savePoint = ts.createSavepoint();
					int noOfPrograms = programWiseDetails.size();
					
					for(int i = 0 ; i < noOfPrograms; i++)
					{
						ProgramWiseDetail pWiseDetail = programWiseDetails.get(i);
						int noOfComponents = pWiseDetail.getAcademicDetails().size();
						int noOfGateComponents = pWiseDetail.getGateAcademicDetails().size();
						
						
						getSqlMapClient().update("application.updateSRforAcademicPerformance", pWiseDetail);
						getSqlMapClient().update("application.updateEduModeAndCenter", pWiseDetail);
						
						for(int j = 0 ; j < noOfComponents; j++)
						{
							AcademicDetail academicDetail = pWiseDetail.getAcademicDetails().get(j);
							academicDetail.setRegistrationNumber(pWiseDetail.getRegistrationNumber());
							academicDetail.setProgramID(pWiseDetail.getProgramID());
							System.out.println("Component ID:"+academicDetail.getExaminationID()+" Obtained Marks:"+academicDetail.getObtainedMarks()+" Total Marks:"+academicDetail.getTotalMarks()+" University/Board:"+academicDetail.getBoardID()+" Result Awaited:"+academicDetail.getResultAwaited()+" Year of Passing:"+academicDetail.getPassingYear());
							
							academicDetail.setEntityID(pWiseDetail.getEntityId());
							academicDetail.setSessionStartDate(pWiseDetail.getSessionStartDate());
							academicDetail.setSessionEndDate(pWiseDetail.getSessionEndDate());
							String obtainedMarks = academicDetail.getObtainedMarks();
							String totalMarks = academicDetail.getTotalMarks();
							
							if((obtainedMarks!=null && !(totalMarks.trim().equals(""))))
							{
								Double tm = Double.parseDouble(totalMarks);
								if(tm == 0.0)
								{
									academicDetail.setPercentage("0.0");
								}
								else
								{
									Double percentage = Double.parseDouble(obtainedMarks)*100/Double.parseDouble(totalMarks);
									academicDetail.setPercentage(percentage.toString());
								}
								
							}
							else
							{
								academicDetail.setPercentage("0.0");
							}
							getSqlMapClient().update("application.insertSCL_RegularComponents", academicDetail);
							
						}
						
						for(int j = 0 ; j < noOfGateComponents; j++)
						{
							AcademicDetail academicDetail = pWiseDetail.getGateAcademicDetails().get(j);
							academicDetail.setRegistrationNumber(pWiseDetail.getRegistrationNumber());
							academicDetail.setProgramID(pWiseDetail.getProgramID());
							academicDetail.setEntityID(pWiseDetail.getEntityId());
							academicDetail.setSessionStartDate(pWiseDetail.getSessionStartDate());
							academicDetail.setSessionEndDate(pWiseDetail.getSessionEndDate());
							System.out.println("Component ID : "+academicDetail.getExaminationID()+" Ranking : "+academicDetail.getRanking()+" Score : "+academicDetail.getScore()+" Total Applicants : "+academicDetail.getTotalApplicants()+" Gate Branch : "+academicDetail.getGateBranch()+ " Gate Year: "+academicDetail.getGateYear());
							getSqlMapClient().update("application.insertSCL_GateComponents", academicDetail);
							
						}
						
						getSqlMapClient().update("application.updateFlagsForPhase2", programWiseDetails.get(0).getApplicationNumber() );
					}
					
					getSqlMapClient().update("application.updateFlagsForPhase2", programWiseDetails.get(0).getApplicationNumber());
					getSqlMapClient().update("application.updateTabStatusTo3", programWiseDetails.get(0).getApplicationNumber());
					
				}
				catch(Exception ex)
				{
					ts.rollbackToSavepoint(savePoint);							
					loggerObject.error("EXCEPTION in ApplicationController:insertAcademicDetails : "+ ex);
					System.out.println(ex+"");
					try
					{
						throw ex;
					}
					catch(Exception ex2)
					{
						loggerObject.error("EXCEPTION in ApplicationController:insertAcademicDetails : "+ ex);
						System.out.println(ex+"");	
					}
					
				}

				return null;

			}
		});
		
		return resultResponse;
	}


	public String updateAcademicDetails(final List<ProgramWiseDetail> programWiseDetails) throws Exception 
	{
		String resultResponse = "Y";
		
		transactionTemplate.execute(new TransactionCallback()
		{
			Object savePoint ;
			public Object doInTransaction(TransactionStatus ts) 
			{
				int noOfPrograms = programWiseDetails.size();
				try
				{
					for(int i = 0 ; i < noOfPrograms; i++)
					{
						ProgramWiseDetail pWiseDetail = programWiseDetails.get(i);
						int noOfComponents = pWiseDetail.getAcademicDetails().size();
						int noOfGateComponents = pWiseDetail.getGateAcademicDetails().size();
						
						System.out.println("Application Number:"+pWiseDetail.getApplicationNumber()+" Registration Number:"+pWiseDetail.getRegistrationNumber());
						System.out.println("Father Income : "+pWiseDetail.getFatherIncome()+ " Mother Income : "+pWiseDetail.getMotherIncome()+" Guardian Income : "+pWiseDetail.getGuardianIncome());
						System.out.println("Staff Ward Flag : "+pWiseDetail.getStaffWardFlag()+" Staff Ward Code : "+pWiseDetail.getStaffCode());
						System.out.println("CCA : "+pWiseDetail.getCcActivities()+" Ever Expelled : "+pWiseDetail.getEverExpelled()+" Hostel Required : "+pWiseDetail.getHostelRequired());
						getSqlMapClient().update("application.updateSRforAcademicPerformance", pWiseDetail);
						getSqlMapClient().update("application.updateEduModeAndCenter", pWiseDetail);
						for(int j = 0 ; j < noOfComponents; j++)
						{
							AcademicDetail academicDetail = pWiseDetail.getAcademicDetails().get(j);
							academicDetail.setRegistrationNumber(pWiseDetail.getRegistrationNumber());
							academicDetail.setProgramID(pWiseDetail.getProgramID());
							System.out.println("Component ID:"+academicDetail.getExaminationID()+" Obtained Marks:"+academicDetail.getObtainedMarks()+" Total Marks:"+academicDetail.getTotalMarks()+" University/Board:"+academicDetail.getBoardID()+" Result Awaited:"+academicDetail.getResultAwaited()+" Year of Passing:"+academicDetail.getPassingYear());
							academicDetail.setEntityID(pWiseDetail.getEntityId());
							academicDetail.setSessionStartDate(pWiseDetail.getSessionStartDate());
							academicDetail.setSessionEndDate(pWiseDetail.getSessionEndDate());
							String obtainedMarks = academicDetail.getObtainedMarks();
							String totalMarks = academicDetail.getTotalMarks();
							
							if(obtainedMarks!=null && !(totalMarks.trim().equals("")))
							{
								Double tm = Double.parseDouble(totalMarks);
								if(tm == 0.0)
								{
									academicDetail.setPercentage("0.0");
								}
								else
								{
									Double percentage = Double.parseDouble(obtainedMarks)*100/Double.parseDouble(totalMarks);
									academicDetail.setPercentage(percentage.toString());
								}
							}
							else
							{
								academicDetail.setPercentage("0.0");
							}
						
							getSqlMapClient().update("application.updateSCL_RegularComponents", academicDetail);
							
						}
						
						for(int j = 0 ; j < noOfGateComponents; j++)
						{
							AcademicDetail academicDetail = pWiseDetail.getGateAcademicDetails().get(j);
							academicDetail.setRegistrationNumber(pWiseDetail.getRegistrationNumber());
							academicDetail.setProgramID(pWiseDetail.getProgramID());
							academicDetail.setEntityID(pWiseDetail.getEntityId());
							academicDetail.setSessionStartDate(pWiseDetail.getSessionStartDate());
							academicDetail.setSessionEndDate(pWiseDetail.getSessionEndDate());
							System.out.println("Component ID : "+academicDetail.getExaminationID()+" Ranking : "+academicDetail.getRanking()+" Score : "+academicDetail.getScore()+" Total Applicants : "+academicDetail.getTotalApplicants()+" Gate Branch : "+academicDetail.getGateBranch()+ " Gate Year: "+academicDetail.getGateYear());
							getSqlMapClient().update("application.updateSCL_GateComponents", academicDetail);
							
						}
						
						
					}

				}
				catch(Exception ex)
				{
					ts.rollbackToSavepoint(savePoint);							
					loggerObject.error("EXCEPTION in ApplicationController:insertAcademicDetails : "+ ex);
					System.out.println(ex+"");
					try
					{
						throw ex;
					}
					catch(Exception ex2)
					{
						loggerObject.error("EXCEPTION in ApplicationController:insertAcademicDetails : "+ ex);
						System.out.println(ex+"");	
					}
					
				}
				return null;

			}
		});
		
		return resultResponse;
	}

	public List<CommonBean> getStudyCenters(InputBean input) 
	{
		List<CommonBean> studyCenters = new ArrayList<CommonBean>();
		
		try
		{
				studyCenters = getSqlMapClientTemplate().queryForList("application.getStudyCenters", input);
		}
		catch(Exception ex)
		{
			System.out.println(ex+"");
		}
		
		return studyCenters;
	}
	
	public String getStudyCenterName(InputBean input) 
	{
		String centerName = "";
		try
		{
			centerName = (String)getSqlMapClientTemplate().queryForObject("application.getStudyCenterName", input);
		}
		catch(Exception ex)
		{
			System.out.println(ex+"");
		}	
		return centerName;
	}
	
	public boolean validateCenterCode(InputBean input) 
	{
		boolean isValid = true;
		int cnt = 0;
		try
		{
			cnt = (Integer)getSqlMapClientTemplate().queryForObject("application.chkStudyCenterCode", input);
			System.out.println("...............cnt = " + cnt);
			if (cnt > 0){ isValid = true; }
			else { isValid = false; }
		}
		catch(Exception ex)
		{
			System.out.println(ex+"");
		}	
		return isValid;
	}
	
	public List<CommonBean> getStateDistricts(String inpState) 
	{
		List<CommonBean> districts = new ArrayList<CommonBean>();
		String selectedState = "%" + inpState + "%";
		System.out.println("impl getStateDistricts inpState=" + inpState);
		try
		{
			districts = getSqlMapClientTemplate().queryForList("application.getStateDistricts", selectedState);
		}
		catch(Exception ex)
		{
			System.out.println(ex+"");
		}
		
		return districts;
	}

	public String insertPhdAcademicDetails(final Applicant applicant) throws Exception
	{	
		final String resultResponse = "Y";
		transactionTemplate.execute(new TransactionCallback()
		{
			Object savePoint ;
			public Object doInTransaction(TransactionStatus ts) 
			{
				List<ProgramWiseDetail> programWiseDetails = applicant.getApplicationForm().getProgramWiseDetails();
				try
				{
					savePoint= new Object();
					savePoint = ts.createSavepoint();
					getSqlMapClientTemplate().insert("application.insertRetEligibility", applicant);
					int noOfPrograms = programWiseDetails.size();
					
					for(int i = 0 ; i < noOfPrograms; i++)
					{
						ProgramWiseDetail pWiseDetail = programWiseDetails.get(i);
						int noOfComponents = pWiseDetail.getAcademicDetails().size();
						int noOfGateComponents = pWiseDetail.getGateAcademicDetails().size();
						
						
						getSqlMapClient().update("application.updateSRforAcademicPerformance", pWiseDetail);
						getSqlMapClient().update("application.updateEduModeAndCenter", pWiseDetail);
						
						for(int j = 0 ; j < noOfComponents; j++)
						{
							AcademicDetail academicDetail = pWiseDetail.getAcademicDetails().get(j);
							academicDetail.setRegistrationNumber(pWiseDetail.getRegistrationNumber());
							academicDetail.setProgramID(pWiseDetail.getProgramID());
							System.out.println("Component ID:"+academicDetail.getExaminationID()+" Obtained Marks:"+academicDetail.getObtainedMarks()+" Total Marks:"+academicDetail.getTotalMarks()+" University/Board:"+academicDetail.getBoardID()+" Result Awaited:"+academicDetail.getResultAwaited()+" Year of Passing:"+academicDetail.getPassingYear());
							
							academicDetail.setEntityID(pWiseDetail.getEntityId());
							academicDetail.setSessionStartDate(pWiseDetail.getSessionStartDate());
							academicDetail.setSessionEndDate(pWiseDetail.getSessionEndDate());
							String obtainedMarks = academicDetail.getObtainedMarks();
							String totalMarks = academicDetail.getTotalMarks();
							
							if((obtainedMarks!=null && !(totalMarks.trim().equals(""))))
							{
								Double tm = Double.parseDouble(totalMarks);
								if(tm == 0.0)
								{
									academicDetail.setPercentage("0.0");
								}
								else
								{
									Double percentage = Double.parseDouble(obtainedMarks)*100/Double.parseDouble(totalMarks);
									academicDetail.setPercentage(percentage.toString());
								}
								
							}
							else
							{
								academicDetail.setPercentage("0.0");
							}
							getSqlMapClient().update("application.insertSCL_RegularComponents", academicDetail);
							
						}
						
						for(int j = 0 ; j < noOfGateComponents; j++)
						{
							AcademicDetail academicDetail = pWiseDetail.getGateAcademicDetails().get(j);
							academicDetail.setRegistrationNumber(pWiseDetail.getRegistrationNumber());
							academicDetail.setProgramID(pWiseDetail.getProgramID());
							academicDetail.setEntityID(pWiseDetail.getEntityId());
							academicDetail.setSessionStartDate(pWiseDetail.getSessionStartDate());
							academicDetail.setSessionEndDate(pWiseDetail.getSessionEndDate());
							System.out.println("Component ID : "+academicDetail.getExaminationID()+" Ranking : "+academicDetail.getRanking()+" Score : "+academicDetail.getScore()+" Total Applicants : "+academicDetail.getTotalApplicants()+" Gate Branch : "+academicDetail.getGateBranch()+ " Gate Year: "+academicDetail.getGateYear());
							getSqlMapClient().update("application.insertSCL_GateComponents", academicDetail);
							
						}
						
						getSqlMapClient().update("application.updateFlagsForPhase2", programWiseDetails.get(0).getApplicationNumber() );
					}
					
					getSqlMapClient().update("application.updateFlagsForPhase2", programWiseDetails.get(0).getApplicationNumber());
					getSqlMapClient().update("application.updateTabStatusTo3", programWiseDetails.get(0).getApplicationNumber());
					
				}
				catch(Exception ex)
				{
					ts.rollbackToSavepoint(savePoint);							
					loggerObject.error("EXCEPTION in ApplicationController:insertAcademicDetails : "+ ex);
					System.out.println(ex+"");
					try
					{
						throw ex;
					}
					catch(Exception ex2)
					{
						loggerObject.error("EXCEPTION in ApplicationController:insertAcademicDetails : "+ ex);
						System.out.println(ex+"");	
					}
					
				}

				return null;

			}
		});
		
		return resultResponse;
	}


	public String updatePhdAcademicDetails(final Applicant applicant) throws Exception 
	{
		String resultResponse = "Y";
		
		transactionTemplate.execute(new TransactionCallback()
		{
			Object savePoint ;
			public Object doInTransaction(TransactionStatus ts) 
			{
				getSqlMapClientTemplate().insert("application.updateRetEligibility", applicant);
				List<ProgramWiseDetail> programWiseDetails = applicant.getApplicationForm().getProgramWiseDetails();
				int noOfPrograms = programWiseDetails.size();
				try
				{
					for(int i = 0 ; i < noOfPrograms; i++)
					{
						ProgramWiseDetail pWiseDetail = programWiseDetails.get(i);
						int noOfComponents = pWiseDetail.getAcademicDetails().size();
						int noOfGateComponents = pWiseDetail.getGateAcademicDetails().size();
						
						System.out.println("Application Number:"+pWiseDetail.getApplicationNumber()+" Registration Number:"+pWiseDetail.getRegistrationNumber());
						System.out.println("Father Income : "+pWiseDetail.getFatherIncome()+ " Mother Income : "+pWiseDetail.getMotherIncome()+" Guardian Income : "+pWiseDetail.getGuardianIncome());
						System.out.println("Staff Ward Flag : "+pWiseDetail.getStaffWardFlag()+" Staff Ward Code : "+pWiseDetail.getStaffCode());
						System.out.println("CCA : "+pWiseDetail.getCcActivities()+" Ever Expelled : "+pWiseDetail.getEverExpelled()+" Hostel Required : "+pWiseDetail.getHostelRequired());
						getSqlMapClient().update("application.updateSRforAcademicPerformance", pWiseDetail);
						getSqlMapClient().update("application.updateEduModeAndCenter", pWiseDetail);
						for(int j = 0 ; j < noOfComponents; j++)
						{
							AcademicDetail academicDetail = pWiseDetail.getAcademicDetails().get(j);
							academicDetail.setRegistrationNumber(pWiseDetail.getRegistrationNumber());
							academicDetail.setProgramID(pWiseDetail.getProgramID());
							System.out.println("Component ID:"+academicDetail.getExaminationID()+" Obtained Marks:"+academicDetail.getObtainedMarks()+" Total Marks:"+academicDetail.getTotalMarks()+" University/Board:"+academicDetail.getBoardID()+" Result Awaited:"+academicDetail.getResultAwaited()+" Year of Passing:"+academicDetail.getPassingYear());
							academicDetail.setEntityID(pWiseDetail.getEntityId());
							academicDetail.setSessionStartDate(pWiseDetail.getSessionStartDate());
							academicDetail.setSessionEndDate(pWiseDetail.getSessionEndDate());
							String obtainedMarks = academicDetail.getObtainedMarks();
							String totalMarks = academicDetail.getTotalMarks();
							
							if(obtainedMarks!=null && !(totalMarks.trim().equals("")))
							{
								Double tm = Double.parseDouble(totalMarks);
								if(tm == 0.0)
								{
									academicDetail.setPercentage("0.0");
								}
								else
								{
									Double percentage = Double.parseDouble(obtainedMarks)*100/Double.parseDouble(totalMarks);
									academicDetail.setPercentage(percentage.toString());
								}
							}
							else
							{
								academicDetail.setPercentage("0.0");
							}
						
							getSqlMapClient().update("application.updateSCL_RegularComponents", academicDetail);
							
						}
						
						for(int j = 0 ; j < noOfGateComponents; j++)
						{
							AcademicDetail academicDetail = pWiseDetail.getGateAcademicDetails().get(j);
							academicDetail.setRegistrationNumber(pWiseDetail.getRegistrationNumber());
							academicDetail.setProgramID(pWiseDetail.getProgramID());
							academicDetail.setEntityID(pWiseDetail.getEntityId());
							academicDetail.setSessionStartDate(pWiseDetail.getSessionStartDate());
							academicDetail.setSessionEndDate(pWiseDetail.getSessionEndDate());
							System.out.println("Component ID : "+academicDetail.getExaminationID()+" Ranking : "+academicDetail.getRanking()+" Score : "+academicDetail.getScore()+" Total Applicants : "+academicDetail.getTotalApplicants()+" Gate Branch : "+academicDetail.getGateBranch()+ " Gate Year: "+academicDetail.getGateYear());
							getSqlMapClient().update("application.updateSCL_GateComponents", academicDetail);
							
						}
						
						
					}

				}
				catch(Exception ex)
				{
					ts.rollbackToSavepoint(savePoint);							
					loggerObject.error("EXCEPTION in ApplicationController:insertAcademicDetails : "+ ex);
					System.out.println(ex+"");
					try
					{
						throw ex;
					}
					catch(Exception ex2)
					{
						loggerObject.error("EXCEPTION in ApplicationController:insertAcademicDetails : "+ ex);
						System.out.println(ex+"");	
					}
					
				}
				return null;

			}
		});
		
		return resultResponse;
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


	public List<CommonBean> getEmbkup() {
		List<CommonBean> embkups = new ArrayList<CommonBean>();
		try
		{
			embkups = getSqlMapClientTemplate().queryForList("application.getEmbkup");
		}
		catch(Exception ex)
		{
			System.out.println("Error in getEmbkup()");
			ex.printStackTrace();
		}
		return embkups;
	}


	public List<YearSemesterWiseMarks> getYearSemWiseMarks(String applicationNumber) {
		List<YearSemesterWiseMarks> yearSemWiseMarks = new ArrayList<YearSemesterWiseMarks>();
		try
		{
			yearSemWiseMarks = getSqlMapClientTemplate().queryForList("application.getYearSemWiseMarks", applicationNumber);
		}
		catch(Exception ex)
		{
			System.out.println("Error in yearWiseMarks()");
			ex.printStackTrace();
		}
		return yearSemWiseMarks;
	}


	public String saveYearSemWiseMarks(List<YearSemesterWiseMarks> inputList) throws Exception{
		getSqlMapClientTemplate().delete("application.deleteYearSemWiseMarks", inputList.get(0));
		for(YearSemesterWiseMarks obj : inputList)
		{
			getSqlMapClientTemplate().insert("application.insertYearSemWiseMarks", obj);
		}
		return "Marks updated successfully.";
		
	}
	
	public List<CommonBean> getSemestersOnly() {
		List<CommonBean> embkups = new ArrayList<CommonBean>();
		try
		{
			embkups = getSqlMapClientTemplate().queryForList("application.getSemestersOnly");
		}
		catch(Exception ex)
		{
			System.out.println("Error in getSemestersOnly()");
			ex.printStackTrace();
		}
		return embkups;
	}


	public boolean ifDeiBoard(String applicationNumber) {
		List<String> deiBoards = new ArrayList<String>();
		try
		{
			deiBoards = getSqlMapClientTemplate().queryForList("application.ifDeiBoard", applicationNumber);
		}
		catch(Exception ex)
		{
			System.out.println("Error in ifDeiBoard()");
			ex.printStackTrace();
		}
		return (deiBoards.size()>0);
	}


}
