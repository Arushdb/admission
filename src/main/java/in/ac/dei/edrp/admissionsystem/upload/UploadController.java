package in.ac.dei.edrp.admissionsystem.upload;

import in.ac.dei.edrp.admissionsystem.Utility;
import in.ac.dei.edrp.admissionsystem.application.ApplicationController;
import in.ac.dei.edrp.admissionsystem.common.CommonController;
import in.ac.dei.edrp.admissionsystem.common.beans.Applicant;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class UploadController extends MultiActionController
{
	public static Logger logObj=Logger.getLogger(UploadController.class);
	private UploadDAO uploadDAO;
	private ApplicationController applicationController;
	private CommonController commonController;
	
	private boolean isMultipart;
//	private String filePath;

	public void setUploadDAO(UploadDAO uploadDAO)
	{
		this.uploadDAO = uploadDAO;
	}
	
	public void setApplicationController(ApplicationController applicationController) {
		this.applicationController = applicationController;
	}

	

	public CommonController getCommonController() {
		return commonController;
	}

	public void setCommonController(CommonController commonController) {
		this.commonController = commonController;
	}

	public ModelAndView getUploadDocsPanel(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession(false);
		String errorMessage=request.getParameter("message");
		System.out.println("error message is "+errorMessage);
		if(session==null)
		{
			return new ModelAndView("errorPages/SessionExpired","message","Your Session has been expired.");
		}
		
		String applicationStatus = (String) session.getAttribute("applicationStatus");
		System.out.println("application status "+applicationStatus);
//		applicationStatus="D";
//		return null;
		if(applicationStatus.equalsIgnoreCase("D") || applicationStatus.equalsIgnoreCase("P")){
			ModelAndView obj=new ModelAndView("application/UploadDocsAlready");
			obj.addObject("applicationStatus", applicationStatus);
			obj.addObject("message", errorMessage);
			return obj;
//		return new ModelAndView("application/UploadDocsAlready","applicationStatus",applicationStatus);
		}else{
			
			UploadDocumentBean documentsizes=uploadDAO.getDocumentSize();
			
			ModelAndView obj=new ModelAndView("application/UploadDocs");
			obj.addObject("applicationStatus", applicationStatus);
			obj.addObject("message", errorMessage);
			obj.addObject("photoSize",documentsizes.getPhotoSize());
			obj.addObject("signSize",documentsizes.getSignSize());
			return obj;
		}
//		return new ModelAndView("application/UploadDocs", "applicationNumber", "123456");
	}
	
	
	public ModelAndView uploadDocument(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession(false);
		Applicant sessionApplicant = (Applicant) session.getAttribute("applicant");
		if(session==null || sessionApplicant == null)
		{
			return new ModelAndView("errorPages/SessionExpired","message","Your Session has been expired.");
		}
		
		//String filePath="";
		int tabStatus = Integer.parseInt(sessionApplicant.getTabStatus());
		if(tabStatus>3)
		{
			session.invalidate();
			return new ModelAndView("errorPages/Error","error","YOU HAVE ALREADY UPLOADED YOUR PHOTO AND SIGNATURE.");
		}
		
		String fees = applicationController.getApplicationDAO().getApplicationFee(sessionApplicant);
		if(fees == null || fees.trim().equalsIgnoreCase("error"))
		{
			return new ModelAndView("application/UploadDocs","message","THERE IS EXCEPTION IN GETTING APPLICATION FEES.");
		}
		else
		{
			sessionApplicant.setApplicationFee(fees);
		}
		
		try
		{
			UploadDocumentBean documentsizes=uploadDAO.getDocumentSize();	
			String tomcatHome = System.getProperty("catalina.base");
			String docRootFolder = null;
			if(tomcatHome==null || tomcatHome.trim().equalsIgnoreCase(""))
			{
				docRootFolder = this.getServletContext().getInitParameter("DOC_FOLDER");
			}
			else
			{
				String str = tomcatHome.replace(tomcatHome.substring(tomcatHome.lastIndexOf(File.separator)),"");
				docRootFolder = str.substring(str.lastIndexOf(File.separator)+1);
			}
		
			docRootFolder=docRootFolder+File.separator+"ApplicantDocuments";
			String applicationNumber = (String) session.getAttribute("applicationNumber");
			//filePath = docRootFolder+File.separator+applicationNumber+File.separator; 
			
			String filePath = Utility.getDocumentPath(request, this.getServletContext());
			
			File folder=new File(filePath);
			if(!folder.exists())
			{
				folder.mkdirs(); 
			}
			
			filePath = filePath + File.separator;
			
			isMultipart = ServletFileUpload.isMultipartContent(request);
			response.setContentType("text/html");
			java.io.PrintWriter out = response.getWriter( );
			if( !isMultipart )
			{
				return null;
			}
			DiskFileItemFactory factory = new DiskFileItemFactory();
	        ServletFileUpload upload = new ServletFileUpload(factory);
	     
	        try
	        { 
	     
	        	List fileItems = upload.parseRequest(request);
	        	Iterator i = fileItems.iterator();
	        	
	        	while(i.hasNext()) 
	        	{
	        		FileItem fi = (FileItem)i.next();
	        		if(!fi.isFormField())	
	        		{
	        			String recievedFileName = fi.getName().toLowerCase();
	        			String fileType = recievedFileName.substring(recievedFileName.lastIndexOf(".") + 1);
	        			File file = new File(filePath);
	        			double fileSize = fi.getSize()/1024; //IN KiloBytes
	        			if(fi.getFieldName().equalsIgnoreCase("photo"))
	        			{
	        				if(fileSize>Integer.parseInt(documentsizes.getPhotoSize()))
	        				{
	        					response.getWriter().write("N");
								return new ModelAndView("application/TabController", "message", "Photo file size exceeded "+documentsizes.getPhotoSize()+ "KB");
	        				}
	        			}
					
	        			if(fi.getFieldName().equalsIgnoreCase("sign"))
	        			{
	        				if(fileSize>Integer.parseInt(documentsizes.getSignSize()))
	        				{
	        					response.getWriter().write("N");
								return new ModelAndView("application/TabController", "message", "Signature file size exceeded "+documentsizes.getSignSize()+ "KB");
	        				}				
	        			}
					
	        			if(!(fileType.equalsIgnoreCase("JPG")||fileType.equalsIgnoreCase("JPEG")))
	        			{
	        				response.getWriter().write("N");
							if(fi.getFieldName().equalsIgnoreCase("photo"))
							{
								return new ModelAndView("application/TabController", "message", "Wrong file format for photo. File must be in jpg/jpeg format");
							}
							else
							{
								return new ModelAndView("application/TabController", "message", "Wrong file format signature. File must be in jpg/jpeg format");
							}
						}
	        			else
	        			{
	        				String fieldName = fi.getFieldName();
			                String fileName = fi.getName();
			                if(fieldName.equalsIgnoreCase("photo"))
			                {
			                	fileName="photo.jpg";
			                }
			                else if(fieldName.equalsIgnoreCase("sign"))
			                {
			                	fileName="signature.jpg";
			                }
			                else
			                {
			            	
			                }
			            String contentType = fi.getContentType();
			            boolean isInMemory = fi.isInMemory();
			            long sizeInBytes = fi.getSize();
			            // Write the file
			            if( fileName.lastIndexOf("\\") >= 0 ){
			               file = new File( filePath + 
			               fileName.substring( fileName.lastIndexOf("\\"))) ;
			            }else{
			               file = new File( filePath + 
			               fileName.substring(fileName.lastIndexOf("\\")+1)) ;
			            }
			            fi.write( file ) ;

					}
	            
	         }
	      }
	      
	       UploadDocumentBean applicant=new UploadDocumentBean();
		   applicant.setApplicationNumber(applicationNumber);
		   applicant.setApplicationStatus("D");
		   applicant.setTabStatus("4");
		   applicant.setDocumentPath(filePath);
		   uploadDAO.updateDocumentPath(applicant) ;
		   }
	       catch(Exception ex) 
	       {
	    	   logObj.error(ex);
	    	   System.out.println(ex);
	    	   return new ModelAndView("application/TabController", "message", ex.getMessage());	
	       }
	   sessionApplicant.setApplicationStatus("D");
	   sessionApplicant.setTabStatus("4");
	   applicationController.refreshSessionVars(request, response, sessionApplicant);
	}
		
	catch(Exception e) 
	{
		logObj.error(e);
		System.out.println(e);
		return new ModelAndView("application/TabController", "message", e.getMessage());	
	}
  
		commonController.updateApplicationPDF(request, response);
		return new ModelAndView("application/TabController");

	}

	
}
