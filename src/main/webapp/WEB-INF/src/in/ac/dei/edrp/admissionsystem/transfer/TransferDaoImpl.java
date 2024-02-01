package in.ac.dei.edrp.admissionsystem.transfer;

import in.ac.dei.edrp.admissionsystem.common.CommonBean;
import in.ac.dei.edrp.admissionsystem.common.beans.Applicant;
import in.ac.dei.edrp.admissionsystem.common.beans.Program;
import in.ac.dei.edrp.admissionsystem.common.beans.ProgramWiseDetail;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.support.TransactionTemplate;

public class TransferDaoImpl extends SqlMapClientDaoSupport implements TransferDao
{
	private Logger loggerObject = Logger.getLogger(TransferDao.class);
	private TransactionTemplate transactionTemplate;
	
	/** defining setter method for object of TransactionTemplate */
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	
	public List<CommonBean> getAppliedPrograms(Applicant applicant) throws Exception
	{
		List<CommonBean> appliedPrograms = new ArrayList<CommonBean>();
		appliedPrograms = getSqlMapClient().queryForList("transfer.getAppliedPrograms", applicant);
		return appliedPrograms;
	}
	
	public List<CommonBean> getTransferPrograms() throws Exception
	{
		List<CommonBean> transferPrograms = new ArrayList<CommonBean>();
		transferPrograms = getSqlMapClient().queryForList("transfer.getTransferPrograms");
		return transferPrograms;
	}
	
	public List<CommonBean> checkApplication(CommonBean inputObject) throws Exception
	{
		List<CommonBean> outputList = getSqlMapClient().queryForList("transfer.checkApplication", inputObject);
		return outputList;
	}
	
	public void applyTransferApplications(CommonBean inputObject) throws Exception
	{
		getSqlMapClient().insert("transfer.insertTransferApplication", inputObject);
	}
	
}
