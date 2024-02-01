package in.ac.dei.edrp.admissionsystem.addMoreProgram;

import in.ac.dei.edrp.admissionsystem.common.CommonBean;
import in.ac.dei.edrp.admissionsystem.common.beans.Applicant;
import in.ac.dei.edrp.admissionsystem.common.beans.StudentProgramPaper;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class addMoreProgramDaoImpl extends SqlMapClientDaoSupport implements addMoreProgramDao
{
	private Logger loggerObject = Logger.getLogger(addMoreProgramDao.class);
	private TransactionTemplate transactionTemplate;
	
	/** defining setter method for object of TransactionTemplate */
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	
	public List<CommonBean> getAppliedPrograms(Applicant applicant) throws Exception
	{
		List<CommonBean> appliedPrograms = new ArrayList<CommonBean>();
		appliedPrograms = getSqlMapClient().queryForList("addMoreProgram.getAppliedPrograms", applicant);
		return appliedPrograms;
	}
	
	public List<CommonBean> getTransferPrograms(Applicant applicant) throws Exception
	{
		List<CommonBean> transferPrograms = new ArrayList<CommonBean>();
		transferPrograms = getSqlMapClient().queryForList("addMoreProgram.getTransferPrograms",applicant);
		return transferPrograms;
	}
	
	public List<CommonBean> checkApplication(CommonBean inputObject) throws Exception
	{
		List<CommonBean> outputList = getSqlMapClient().queryForList("addMoreProgram.checkApplication", inputObject);
		return outputList;
	}
	
	public void addProgramsToApplication(final CommonBean inputObject,final StudentProgramPaper inputToSR) throws Exception
	{

		

				CommonBean regBean = (CommonBean) getSqlMapClientTemplate().queryForObject("application.getRegNumValue", "0001");
		final String	registrationNumber = regBean.getOtherProperty1()+String.format("%07d", Integer.parseInt(regBean.getCode()));
				 getSqlMapClientTemplate().update("application.updateRegNumValue", regBean);
				
				System.out.println("registrationNumber is "+registrationNumber);

			inputObject.setCode(registrationNumber);
			transactionTemplate.execute(new TransactionCallback()
			{
				Object savePoint ;
				public Object doInTransaction(TransactionStatus ts) {
					//int noOfPrograms = programWiseDetails.size();
					try
					{
						savePoint= new Object();
						savePoint = ts.createSavepoint();
			CommonBean studentId= (CommonBean) getSqlMapClientTemplate().queryForObject("addMoreProgram.getStudentId",inputObject);
			CommonBean entityId= (CommonBean) getSqlMapClientTemplate().queryForObject("addMoreProgram.getEntityId",inputObject);
//			StudentProgramPaper inputToSR=new StudentProgramPaper();
			inputToSR.setRegistrationNumber(registrationNumber);
			inputToSR.setApplicationNumber(studentId.getCode());
			inputToSR.setProgramID(inputObject.getDescription());

			inputToSR.setUserID(inputObject.getOtherProperty1());
			inputToSR.setUniversityID(entityId.getCode());
			
			
			getSqlMapClientTemplate().insert("addMoreProgram.addInSR", inputToSR);
			
			System.out.println(inputToSR.getRegistrationNumber()+"|"+inputToSR.getApplicationNumber()+"|"+
					inputToSR.getProgramID()+"|"+inputToSR.getUserID()+"|"+inputToSR.getUniversityID());
			
			getSqlMapClientTemplate().insert("addMoreProgram.addInAPR", inputObject);

			getSqlMapClientTemplate().insert("addMoreProgram.addInSCL", inputToSR);
			
			getSqlMapClientTemplate().insert("addMoreProgram.addInStudentPaper", inputToSR);

			getSqlMapClientTemplate().insert("addMoreProgram.addInSTN", inputToSR);
					}
					catch(Exception ex)
					{
						System.out.println(ex+"");
						ts.rollbackToSavepoint(savePoint);							
						System.out.println(ex+"");
					}

					return null;
				}
				
			});
		
	}


	public String toAskForBranches(String programId) throws Exception
	{
		String branchFlag = getSqlMapClient().queryForObject("addMoreProgram.getPrefferedBranchFlag", programId).toString();
		return branchFlag;
	}
	public List<CommonBean> getProgramBranches(String programId) throws Exception
	{
		List<CommonBean> branches = getSqlMapClient().queryForList("addMoreProgram.programBranches", programId);
		return branches;
	}

	
}
