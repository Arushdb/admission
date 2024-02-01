package in.ac.dei.edrp.admissionsystem.paymentDetails;

import in.ac.dei.edrp.admissionsystem.application.ApplicationDAO;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class PaymentDAOImpl extends SqlMapClientDaoSupport implements PaymentDAO{

	 Logger logObj = Logger.getLogger(ApplicationDAO.class);
	
	 public boolean getAlreadyVoucherGenerated(PaymentDocumentBean input){
		 try{
			 List<PaymentDocumentBean> output=getSqlMapClientTemplate().queryForList("payment.getRecordExist",input);
		 
			 if(output.size()>0){
				return true; 
			 }else{
				 return false;
			 }
		 
		 }catch (Exception e) {
			logObj.error(e);
		}
		return false;
		 
	 }
	 
	 public void insertVoucherDetails(PaymentDocumentBean input){
		 try{
			 getSqlMapClientTemplate().insert("payment.insertVoucherRecord",input);
		 }catch (Exception e) {
				logObj.error(e);
		}
	 }
	
}
