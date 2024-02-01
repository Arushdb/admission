package in.ac.dei.edrp.admissionsystem.upload;

import java.io.File;
import java.util.List;

import in.ac.dei.edrp.admissionsystem.application.ApplicationDAO;
import in.ac.dei.edrp.admissionsystem.common.beans.Applicant;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class UploadDAOImpl extends SqlMapClientDaoSupport implements UploadDAO{

	 Logger logObj = Logger.getLogger(ApplicationDAO.class);
	
	//Following method added by manpreet
	public Integer updateDocumentPath(UploadDocumentBean applicant) 
	{
		System.out.println("reached impl");
		int noOfRecords=0;
		try
		{	
		CM_StudentInfoGetter studentInfo = new CM_StudentInfoGetter();
			
			studentInfo.setForm_number(applicant.getApplicationNumber());
			studentInfo.setSourcePath(applicant.getDocumentPath());
			String targetPath = applicant.getDocumentPath().replace(System.getProperty("user.home")+File.separator,"");
			studentInfo.setTargetPath(targetPath);
			
//			if(applicant.getApplicationStatus().trim().equalsIgnoreCase("S"))
//			{
		System.out.println("before running query");
				noOfRecords = getSqlMapClientTemplate().update("uploadDocs.updateStatusWithDocumentPath", applicant);
				System.out.println("record updated "+noOfRecords);
				studentInfo.setDocId("signature");
				studentInfo.setFile_name("signature.jpg");
//			}
//			else if(applicant.getApplicationStatus().trim().equalsIgnoreCase("F"))
//			{
////				String pdfGenerated = generateApplicationPDF(applicant.getApplicationNumber());
//				if(pdfGenerated.trim().equalsIgnoreCase("Y"))
//				{
					CM_StudentInfoGetter studentInfoForAppPDF = new CM_StudentInfoGetter();
					studentInfoForAppPDF.setForm_number(applicant.getApplicationNumber());
					studentInfoForAppPDF.setSourcePath(applicant.getDocumentPath());
					studentInfoForAppPDF.setTargetPath(targetPath);
//					studentInfoForAppPDF.setDocId(applicant.getApplicationNumber());
//					studentInfoForAppPDF.setFile_name(applicant.getApplicationNumber()+".pdf");
//					
//					noOfRecords = getSqlMapClientTemplate().update("updateApplicationStatusWithoutPath", applicant);
					getSqlMapClientTemplate().insert("common.insertFTPDocumentDetails", studentInfo);
					
					
					studentInfo.setDocId("photo");
					studentInfo.setFile_name("photo.jpg");
//					noOfRecords = getSqlMapClientTemplate().update("updateApplicationStatusWithoutPath", applicant);
					getSqlMapClientTemplate().insert("common.insertFTPDocumentDetails", studentInfo);
					
//				}
//				else
//				{
//					return 0;
//				}
//						
//				
//			}
			
			
//			getSqlMapClientTemplate().insert("insertFTPDocumentDetails", studentInfo);
		}
		catch(Exception ex)
		{
			logObj.error("Error inside updating File Path for "+ applicant.getStudentID()+ " and Exception is"+ex);
		System.out.println(ex);
		}
		return new Integer(noOfRecords);
	}
	
	public UploadDocumentBean getDocumentSize(){
		try{
			UploadDocumentBean inputObj=new UploadDocumentBean();
			inputObj.setDocumentType("PHSIZE");
			
			UploadDocumentBean photoSizeObj=(UploadDocumentBean) getSqlMapClientTemplate().queryForObject("uploadDocs.getDocumentSize", inputObj);
			
			inputObj.setDocumentType("SNSIZE");
			UploadDocumentBean signSizeObj=(UploadDocumentBean) getSqlMapClientTemplate().queryForObject("uploadDocs.getDocumentSize", inputObj);

			UploadDocumentBean outputObj=new UploadDocumentBean();
			outputObj.setPhotoSize(photoSizeObj.getDocId());
			outputObj.setSignSize(signSizeObj.getDocId());
			System.out.println("photo "+outputObj.getPhotoSize());
			System.out.println("sign "+outputObj.getSignSize());

			return outputObj;
			
		}catch (Exception e) {
logObj.error("Exception while getting document size");
}
		return null;
		
	}
	
}
