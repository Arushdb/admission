package in.ac.dei.edrp.admissionsystem.account;




import in.ac.dei.edrp.admissionsystem.common.CommonBean;
import in.ac.dei.edrp.admissionsystem.common.ProgramCounter;
import in.ac.dei.edrp.admissionsystem.common.User;
import in.ac.dei.edrp.admissionsystem.common.beans.Applicant;
import in.ac.dei.edrp.admissionsystem.common.beans.Component;
import in.ac.dei.edrp.admissionsystem.common.beans.ProgramForm;
import in.ac.dei.edrp.admissionsystem.common.beans.ProgramWiseDetail;
import in.ac.dei.edrp.admissionsystem.common.beans.StudentProgramPaper;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class AccountDAOImpl extends SqlMapClientDaoSupport implements AccountDAO{

	private Logger loggerObject = Logger.getLogger(AccountDAO.class);
	private TransactionTemplate transactionTemplate;
	
	/** defining setter method for object of TransactionTemplate */
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	public List<Applicant> getUserList(Applicant applicant)
	{
		List<Applicant> applicants = new ArrayList<Applicant>();
		try
		{
			List<CommonBean> allCategories = getSqlMapClientTemplate().queryForList("account.getAllCategories", applicant);
			List<CommonBean> allGenders = getSqlMapClientTemplate().queryForList("account.getAllGenders", applicant);
			applicants = getSqlMapClientTemplate().queryForList("account.getUserList", applicant);
			if(applicants.size()==1)
			{
				applicants.get(0).setAllCategories(allCategories);
				applicants.get(0).setAllGenders(allGenders);
			}
		}
		catch(Exception ex)
		{
		System.out.println(ex+"");	
		}
		return applicants;
		
	}
		
	public Applicant getApplicantDetails(String applicationNumber)
	{
		Applicant applicant = null;
		
		try
		{
			applicant = (Applicant) getSqlMapClientTemplate().queryForObject("account.getApplicantDetails", applicationNumber);
		}
		catch(Exception ex)
		{
			System.out.println(ex+"");
		}
		return applicant;
	}
	
	
	public List<CommonBean> getCategories(String universityID) 
	{
		List<CommonBean> categoryList = null;
		try
		{
			categoryList = getSqlMapClientTemplate().queryForList("account.getCategoryList", universityID);
		}
		catch(Exception ex)
		{
			loggerObject.error("EXCEPTION IN GETTING CATEGORIES : " + ex);
		}
		
		return categoryList;
	}
	
	public List<CommonBean> getSQuestions() 
	{
		List<CommonBean> sQuestionsList = null;
		try
		{
			sQuestionsList = getSqlMapClientTemplate().queryForList("account.getSQuestionsList", "0001");
		}
		catch(Exception ex)
		{
			loggerObject.error("EXCEPTION IN GETTING SECURITY QUESTIONS : " + ex);
		}
		return sQuestionsList;
	}

	
	public List<Applicant> createAccount(final Applicant applicant) 
	{
		loggerObject.info("AccountDAOImpl:createAccount starts here.");
		List<Applicant> applicantList = null;
		try
		{
			int updatedRecords1 = 0;
			while(updatedRecords1==0)
			{
				CommonBean idBean = (CommonBean)getSqlMapClientTemplate().queryForObject("account.getStdIDandValue", applicant.getUniversityID());
				if(idBean == null)
				{
					getSqlMapClient().insert("account.insertSTUDID", applicant.getUniversityID());
					continue;
				}
				else
				{
					updatedRecords1 = getSqlMapClientTemplate().update("account.updateStdID", idBean);
				}
				
				if(updatedRecords1==1)
				{
					String userID = idBean.getDescription()+String.format("%05d", Integer.parseInt(idBean.getCode()));
					applicant.setUserID(userID);
					applicant.setRegisteredSession(idBean.getOtherProperty1());
				}
			}
			
			 int updatedRecords2 = 0;
				while(updatedRecords2==0)
				{
					CommonBean appBean = (CommonBean)getSqlMapClientTemplate().queryForObject("account.getAppNumber", applicant.getUniversityID());
					if(appBean == null)
					{
						getSqlMapClient().insert("account.insertAPNUMB", applicant.getUniversityID());
						continue;
					}
					else
					{
						updatedRecords2 = getSqlMapClientTemplate().update("account.updateAppNumber",appBean);
					}
					
					//String applicationNumber = appBean.getOtherProperty1()+"E"+String.format("%05d", Integer.parseInt(appBean.getCode())); Commented on 29-01-2015
					String applicationNumber = "1"+String.format("%05d", Integer.parseInt(appBean.getCode())); 
					if(updatedRecords2==1)
					{
						applicant.setApplicationNumber(applicationNumber);
						applicant.setTabStatus("0");
						
					}
				}
				
				transactionTemplate.execute(new TransactionCallback()
				{
					Object savePoint ;
					public Object doInTransaction(TransactionStatus ts) {
						try
						{
							savePoint= new Object();
							savePoint = ts.createSavepoint();
								
								//Added by Arjun on 28-05-2019
								List<String> feePasswords = getSqlMapClient().queryForList("account.checkFeePassword", applicant);
								if(feePasswords.size() == 0)
								{
									getSqlMapClient().insert("account.insertFeePassword", applicant);
								}
								
								applicant.setPassword(getFeePassword(applicant.getApplicationNumber()));
								getSqlMapClientTemplate().insert("account.createAccount", applicant);
								getSqlMapClientTemplate().insert("account.insertPersonalDetails", applicant);
								getSqlMapClientTemplate().insert("account.insertAddressDetailsCOR", applicant);
								getSqlMapClientTemplate().insert("account.insertAddressDetailsPER", applicant);
								getSqlMapClientTemplate().insert("account.insertSAS", applicant);
								
								/**Commented by Arjun on 28-05-2019
								List<String> feePasswords = getSqlMapClient().queryForList("account.checkFeePassword", applicant);
								if(feePasswords.size() == 0)
								{
									getSqlMapClient().insert("account.insertFeePassword", applicant);
								}**/
								
						}
						catch(Exception ex)
						{
							ts.rollbackToSavepoint(savePoint);							
							loggerObject.error("ERROR IN ACCOUNT CREATION : " + ex);
						}
						
						
						return null;
					}
					
				});
				
				applicantList = getSqlMapClientTemplate().queryForList("account.getUserDetails", applicant);
				return applicantList;
			
		}
		catch(Exception ex)
		{
			loggerObject.error("ERROR IN SETTING STUDENT ID OR APPLICATION NUMBER : " + ex);
		}
		
		return applicantList;
	}
	
	/**
	 * Method to generate a string(password).
	 * 
	 * @return auto-generated string(password)
	 */
	public String generatePassword() {
		Random r = new Random();
		int i = 1;
		int n = 0;
		char c;
		String str = "";

		for (int t = 0; t < 3; t++) {
			while (true) {
				i = r.nextInt(10);

				if ((i > 5) && (i < 10)) {
					if (i == 9) {
						i = 90;
						n = 90;

						break;
					}

					if (i != 90) {
						n = (i * 10) + r.nextInt(10);

						while (n < 65) {
							n = (i * 10) + r.nextInt(10);
						}
					}

					break;
				}
			}

			c = (char) n;

			str = String.valueOf(c) + str;
		}

		while (true) {
			i = r.nextInt(100000);

			if (i > 9999) {
				break;
			}
		}

		str = str + i;
		loggerObject.info("password :" + str);

		return str;
	}

	/**
	 * Method to send mail to the user for delivering account access details
	 * 
	 * @param userName
	 *            userName to be used as login id
	 * @param password
	 *            password to access the application
	 * @param email
	 *            mail-id of the user
	 * @return String
	 */
	public String sendMailtoUser(String userName, String password,
			String email, String applicationNumber) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("in"+File.separator+"ac"+File.separator+"dei"+File.separator+"edrp"+File.separator+"admissionsystem"+File.separator+"shared"+File.separator+"constants",
				new Locale("en", "US"));
		final String senderEmail = resourceBundle.getString("senderEmail");
		final String senderEmailPWD = resourceBundle.getString("senderEmailPWD");
		String sentFlag = "error";
		try
		{
		Properties properties = System.getProperties();
		Session smtpSession = null;
			
			properties.setProperty("mail.smtp.port", "465");
		    properties.setProperty("mail.smtp.socketFactory.port", "465"); 
		    properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		    properties.setProperty("mail.smtp.startssl.enable","true");
		    properties.setProperty("mail.smtp.starttls.enable", "true");
		    properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		    properties.setProperty("mail.smtp.auth", "true");
		    properties.put("mail.store.protocol", "pop3");//for incoming mail
		    properties.put("mail.transport.protocol", "smtp");//for outgoing mail
		    
		    smtpSession = Session.getDefaultInstance(properties, 
	                new Authenticator(){
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(senderEmail, senderEmailPWD);
	            }});
		
		
			
		   
		
       	
		    String msg = "<font face='century gothic'>Dear "+userName+",<br/>"+"" +
			"<br>Your account has been created successfully." +
			"<br>Please use the following Account Detail to login :-" +
			"<table border='0' cellspacing='5' cellpadding='5'>"+
			"<tr><th>User Name/Application Number</th><th>:</th><td>"+applicationNumber+"</td></tr>"+
			"<tr><th>Password</th><th>:</th><td>"+password+"</td></tr>"+
			"</table>";
		msg = msg+ "<br/><b><a href='"+resourceBundle.getString("projectLink")+"'>Click Here</a> to go to Login Page.</b>";
		msg = msg+ "This is a System Generated Mail. Please do not reply or send any mail to this Email Address</font>";
		
		String mailTo = email;
       
      
       
       
     
     
		smtpSession.setDebug(false);  
       String mailFrom=senderEmail;
       MimeMessage message = new MimeMessage(smtpSession);  
       message.setFrom(new InternetAddress(mailFrom)); 
       message.setRecipients(Message.RecipientType.TO, 
               InternetAddress.parse(mailTo,false));
       
       final String encoding = "UTF-8";  
       
       message.setSubject("DEI-ACCOUNT SUCCESSFUL CREATION", encoding);  
       message.setText(msg, encoding); 
       message.setContent(msg,"text/html");
       message.setSentDate(new Date());
       
       
       System.out.println("Sending Message......");
       Transport.send(message);
       System.out.println("Message Sent.......");
       sentFlag = "success";
		}
		catch(Exception e){
			e.printStackTrace();
			//logObj.logger.error(e.getMessage());
		}

		return sentFlag;
	}

	
	public List<Applicant> savePersonalDetailstesting(Applicant applicant) {
		
		System.out.println("savePersonalDetails"+applicant.getApplicantName());
		List<Applicant> applicants = new ArrayList<Applicant>();
		try
		{
			System.out.println("USER ID is : "+applicant.getUserID());
			System.out.println("Application Number is : "+applicant.getApplicationNumber());
			applicant.setTabStatus("1");
			getSqlMapClientTemplate().update("application.updatePersonalDetails", applicant);
			getSqlMapClientTemplate().update("application.updateCORAddressDetails", applicant);
			//getSqlMapClientTemplate().update("application.updatePERAddressDetails", applicant);
			getSqlMapClientTemplate().update("application.updateSAS", applicant);
			applicants = getApplicantDetails(applicant);
			
		}
		catch(Exception ex)
		{
			System.out.println(ex+"");
		}
		
		return applicants;
	}
	
	
	public List<Applicant> getApplicantDetails(Applicant applicant)
	{
		List<Applicant> applicants = new ArrayList<Applicant>();
		try
		{
			String tabStatusSTR = (String) getSqlMapClientTemplate().queryForObject("account.getTabStatus", applicant);
			System.out.println("App Status is : "+tabStatusSTR);
			int tabStatus = Integer.parseInt(tabStatusSTR);
			if(tabStatus>=0 && tabStatus <=1)
			{
				List<CommonBean> allCategories = getSqlMapClientTemplate().queryForList("account.getAllCategories", applicant);
				List<CommonBean> allGenders = getSqlMapClientTemplate().queryForList("account.getAllGenders", applicant);
				applicants =  getSqlMapClientTemplate().queryForList("account.getUserDetails",applicant);
				if(applicants.size()==1)
				{
					applicants.get(0).setAllCategories(allCategories);
					applicants.get(0).setAllGenders(allGenders);
				}
			} 
			else
			{
				//Fetch Academic Details
				//List<Applicant> applicants = getSqlMapClientTemplate().queryForList("account.getUserDetails",user);
				//applicant = applicants.get(0);
				//Also needs academic details
				//applicant = getApplicantAcademicDetails(applicant);		
		
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex+"");
		}
				
		
		return applicants;
	}
	
	
	public List<Applicant> savePaperOptionstesting(Applicant applicant) 
	{
		
		int updatedRecords = 0;
		String registrationNumber = null;
		ProgramWiseDetail programWiseDetail = new ProgramWiseDetail();
		while(updatedRecords == 0)
		{
			registrationNumber = (String) getSqlMapClientTemplate().queryForObject("application.getRegNumValue", applicant.getUniversityID());
			updatedRecords = getSqlMapClientTemplate().update("application.updateRegNumValue", registrationNumber);
			System.out.println("registrationNumber"+registrationNumber);
		}
		
		programWiseDetail.setRegistrationNumber(registrationNumber);
		programWiseDetail.setSessionStartDate("2014-07-01");
		programWiseDetail.setSessionEndDate("2015-06-30");
		programWiseDetail.setUserID(applicant.getUserID());
		programWiseDetail.setUniversityID(applicant.getUniversityID());
		programWiseDetail.setApplicationNumber(applicant.getApplicationNumber());
		programWiseDetail.setProgramID("0001090");
		getSqlMapClientTemplate().insert("application.insertSR", programWiseDetail);
		getSqlMapClientTemplate().insert("application.insertAPR", programWiseDetail);
		
		StudentProgramPaper stdPrgPaper = new StudentProgramPaper();
		ProgramWiseDetail pwd =new ProgramWiseDetail();
		stdPrgPaper.setRegistrationNumber(programWiseDetail.getRegistrationNumber());
		stdPrgPaper.setSessionStartDate(programWiseDetail.getSessionStartDate());
		stdPrgPaper.setSessionEndDate(programWiseDetail.getSessionEndDate());
		stdPrgPaper.setUserID(programWiseDetail.getUserID());
		stdPrgPaper.setGrouping("G1");
		stdPrgPaper.setMainGroup("A");
		stdPrgPaper.setPaperCode("07");
		stdPrgPaper.setProgramID("0001090");
		pwd.setProgramID("0001090");
		pwd.setRegistrationNumber(programWiseDetail.getRegistrationNumber());
		pwd.setEntityId("00010024");
		pwd.setCalled("Y");
		pwd.setCosValue("GNXX");
		pwd.setStatus("Eligible");
		pwd.setReasonCode("testing");
		pwd.setSessionStartDate(programWiseDetail.getSessionStartDate());
		pwd.setSessionEndDate(programWiseDetail.getSessionEndDate());
		getSqlMapClientTemplate().insert("application.insertStudentPaper", stdPrgPaper);
		getSqlMapClientTemplate().insert("application.insertStudenttestNumber", pwd);
		
		applicant.setApplicationStatus("A");
		applicant.setTabStatus("2");
		getSqlMapClientTemplate().insert("application.updateSAS", applicant);
		
		
		return null;
	}

	public String updatePassword(User user) {
		int noOfRecords = 0;
		String userName = null;
		try
		{
			noOfRecords = getSqlMapClientTemplate().update("application.updatePassword", user);
			if(noOfRecords==0)
			{
				return userName;
			}
			else
			{
				userName = (String) getSqlMapClientTemplate().queryForObject("application.getUserName", user);
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex+"");
		}
		return userName;
	}
	
	public String resendMailtoUser(String userName, String password,
			String email, String applicationNumber) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("in"+File.separator+"ac"+File.separator+"dei"+File.separator+"edrp"+File.separator+"admissionsystem"+File.separator+"shared"+File.separator+"constants",
				new Locale("en", "US"));
		final String senderEmail = resourceBundle.getString("senderEmail");
		final String senderEmailPWD = resourceBundle.getString("senderEmailPWD");
		String sentFlag = "error";
		try
		{
		Properties properties = System.getProperties();
		Session smtpSession = null;
			
			properties.setProperty("mail.smtp.port", "465");
		    properties.setProperty("mail.smtp.socketFactory.port", "465"); 
		    properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		    properties.setProperty("mail.smtp.startssl.enable","true");
		    properties.setProperty("mail.smtp.starttls.enable", "true");
		    properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		    properties.setProperty("mail.smtp.auth", "true");
		    properties.put("mail.store.protocol", "pop3");//for incoming mail
		    properties.put("mail.transport.protocol", "smtp");//for outgoing mail
		    
		    smtpSession = Session.getDefaultInstance(properties, 
	                new Authenticator(){
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(senderEmail, senderEmailPWD);
	            }});
		
		    String msg = "<font face='century gothic'>Dear "+userName+",<br/>"+"" +
			"<br>Your Password has been changed successfully." +
			"<br>Please use the following Account Detail to login :-" +
			"<table border='0' cellspacing='5' cellpadding='5'>"+
			"<tr><th>User Name/Application Number</th><th>:</th><td>"+applicationNumber+"</td></tr>"+
			"<tr><th>Password</th><th>:</th><td>"+password+"</td></tr>"+
			"</table>";
		msg = msg+ "<br/><b><a href='"+resourceBundle.getString("projectLink")+"'>Click Here</a> to go to Login Page.</b>";
		msg = msg+ "This is a System Generated Mail. Please do not reply or send any mail to this Email Address</font>";
		
		String mailTo = email;
       
      
       
       
     
     
		smtpSession.setDebug(false);  
       String mailFrom=senderEmail;
       MimeMessage message = new MimeMessage(smtpSession);  
       message.setFrom(new InternetAddress(mailFrom)); 
       message.setRecipients(Message.RecipientType.TO, 
               InternetAddress.parse(mailTo,false));
       
       final String encoding = "UTF-8";  
       
       message.setSubject("DEI-ACCOUNT SUCCESSFUL CREATION", encoding);  
       message.setText(msg, encoding); 
       message.setContent(msg,"text/html");
       message.setSentDate(new Date());
       
       
       System.out.println("Sending Message......");
       Transport.send(message);
       System.out.println("Message Sent.......");
       sentFlag = "success";
		}
		catch(Exception e){
			e.printStackTrace();
			//logObj.logger.error(e.getMessage());
		}

		return sentFlag;
	}

	
	public List<String> validateAccountDetails(Applicant applicant) {
		List<String> errors = new ArrayList<String>();
		if(invalidString(applicant.getApplicantName()))
		{
			errors.add("Please Enter Applicant Name.");
		}
		if(invalidString(applicant.getPrimaryEmailID()))
		{
			errors.add("Please Enter Email ID.");
		}
		else
		{
			/** (Email Exists Check) commented by Arjun from session 2016-2017 
			try
			{
				List<String> emails = new ArrayList<String>();
				emails = getSqlMapClient().queryForList("account.getEmailCount", applicant.getPrimaryEmailID());
				if(emails.size()>0)
				{
					errors.add("Email is already being used. Please use different Email ID.");
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}**/
			
		}
		if(invalidString(applicant.getCategory()))
		{
			errors.add("Please Select Category.");
		}
		if(invalidString(applicant.getGender()))
		{
			errors.add("Please Select Gender.");
		}
		if(invalidString(applicant.getCorCountry()))
		{
			errors.add("Please Select Country.");
		}
		if(invalidString(applicant.getHomePhone()))
		{
			errors.add("Please Enter Contact Number.");
		}
		if(invalidString(applicant.getQuestion()))
		{
			errors.add("Please Select Security Question.");
		}
		if(invalidString(applicant.getAnswer()))
		{
			errors.add("Please Enter Security Answer.");
		}
		if(invalidString(applicant.getDd())||invalidString(applicant.getMm())||invalidString(applicant.getYyyy()))
		{
			errors.add("Please Enter Date of Birth.");
		}
		
		return errors;
	}
	
	public boolean invalidString(String param)
	{
		return (param==null || param.trim().equalsIgnoreCase(""));
	}

	
	public List<User> checkApplication(User user) 
	{
		List<User> users = null;
		try
		{
			user.setContactNumber(user.getContactNumber());
			users = getSqlMapClient().queryForList("account.checkApplication", user);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			loggerObject.error(ex+"");
		}
		return users;
	}

	
	public List<ProgramWiseDetail> getProgramAdmitCardDetails(Applicant applicant) 
	{
		List<ProgramWiseDetail> programAdmitCardDetails = new ArrayList<ProgramWiseDetail>();
		try
		{
			programAdmitCardDetails = getSqlMapClient().queryForList("account.getProgramAdmitCardDetails", applicant);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			loggerObject.error(ex+"");
		}
		return programAdmitCardDetails;
	}

	
	/**
	public Applicant getApplicant(User user)
	{
		//Get the Application Status
		String appStatus = null;
		Applicant applicant = new Applicant();
		int applicationStatus = Integer.parseInt(appStatus);
		try
		{
			if(applicationStatus==0)
			{
				List<Applicant> applicants =  getSqlMapClientTemplate().queryForList("getUserDetails",user);
			} 
			else if(applicationStatus==1)
			{
				List<Applicant> applicants = getSqlMapClientTemplate().queryForList("getUserDetails",user);
				applicant = applicants.get(0);
				//Also needs academic details
				//List<Component> components = getSqlMapClientTemplate().queryForList("application.getRGLPrgComponents", user);
				//List<Component> componentsForGATE = getSqlMapClientTemplate().queryForList("application.getGTPrgComponents", user);
				//List<CommonBean> boards = getSqlMapClientTemplate().queryForList("application.getBoard");
				/**for(int i=0; i < applicants.size(); i++)
				{
					ProgramWiseDetail programWDtl = new ProgramWiseDetail();
					String prgName = (String)getSqlMapClientTemplate().queryForObject("application.getProgramName", programs.get(i));
					List<CommonBean> branches = getSqlMapClientTemplate().queryForList("application.getBranches", programs.get(i));
					prgForm.setProgramID(programs.get(i));
					prgForm.setProgramName(prgName);
					prgForm.setBranches(branches);
					programForms.add(prgForm);
					
					if(branches.size()>0)
					{
						applicationForm.setBranchesAvailable("Y");
					}
				}**/
				
		/**
			}
			else if(applicationStatus==3)
			{
				
			}
			else if(applicationStatus==4)
			{
				
			}
			else
			{
				
			}

		}
		catch(Exception ex)
		{
			
		}
				
		
		return applicant;
	}**/
	
/**
* getAccountsWithSameDetails() added by Arjun to prevent multiple accounts creation for same details
* Date : 18-04-2016
*/
public List<User> getAccountsWithSameDetails(Applicant applicant)
{
	List<User> users = new ArrayList<User>();
	try
	{		
		users = getSqlMapClient().queryForList("account.getAccountsWithSameDetails", applicant);
	}
	catch(Exception ex)
	{
		loggerObject.error("EXCEPTION IN CHECKING LAST SAME RECORD : " + ex);
	}
	
	return users;
}
	
public String getFeePassword(String appNumber) 
{
	String password = null;
	try
	{		
		password = getSqlMapClient().queryForObject("account.feePassword", appNumber).toString();
	}
	catch(Exception ex)
	{
		loggerObject.error("EXCEPTION IN GETTING FEE PASSWORD AND PASSWORD GENERATION : " + ex);
	}
	
	return password;
}

public List<ProgramCounter> getApplicationsCount() {
	List<ProgramCounter> programsList = new ArrayList<ProgramCounter>();
	try
	{		
		programsList = getSqlMapClient().queryForList("account.getApplicationsCount");
	}
	catch(Exception ex)
	{
		loggerObject.error("EXCEPTION IN getApplicationsCount() : " + ex);
	}
	
	return programsList;
}

}
