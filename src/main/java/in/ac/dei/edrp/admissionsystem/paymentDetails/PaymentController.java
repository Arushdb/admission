package in.ac.dei.edrp.admissionsystem.paymentDetails;


import in.ac.dei.edrp.admissionsystem.common.beans.Applicant;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PaymentController extends MultiActionController{
	public static Logger logObj=Logger.getLogger(PaymentController.class);
	
	private PaymentDAO paymentDAO;
	

	public void setPaymentDAO(PaymentDAO paymentDAO)
	{
		this.paymentDAO = paymentDAO;
	}
	
//	public ModelAndView getUploadDocsPanel(HttpServletRequest request,HttpServletResponse response) throws Exception
//	{
//		HttpSession session = request.getSession(false);
//		if(session==null)
//		{
//			return new ModelAndView("errorPages/SessionExpired","message","Your Session has been expired.");
//		}
//		return new ModelAndView("application/UploadDocs", "applicationNumber", "123456");
//	}
	
	 public void downloadVoucher(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException {
			System.out.println("in servlet");
			HttpSession session = request.getSession();
			String applicationStatus = (String) session.getAttribute("applicationStatus");
			String applicationNumber = (String) session.getAttribute("applicationNumber");
			Applicant applicantData = (Applicant) session.getAttribute("applicant");
			try{
		 
			String programNameList="";
			
			for(int i=0;i<applicantData.getApplicationForm().getProgramWiseDetails().size();i++){
				if(i==0){
					programNameList=applicantData.getApplicationForm().getProgramWiseDetails().get(i).getProgramName();
				}else{
			programNameList=programNameList+", "+applicantData.getApplicationForm().getProgramWiseDetails().get(i).getProgramName();
			}
			}
			
			PaymentDocumentBean inputBean=new PaymentDocumentBean();
		 inputBean.setBankAccountNumber("3633000100175186");
		 inputBean.setBankAccountName("DEI FEE COLLECTION A/C");
		 inputBean.setCity("PUNB0363300");
		 inputBean.setFirstName(applicantData.getApplicantName());
		 inputBean.setProgramName(programNameList);
		 inputBean.setApplicationNumber(applicationNumber);
		 inputBean.setFee(request.getParameter("fee"));
		 inputBean.setDob(applicantData.getDob());
		 System.out.println(request.getParameter("fee")+" is fees "+applicantData.getDob());
		Boolean result= paymentDAO.getAlreadyVoucherGenerated(inputBean);
		 if(result==false){
			 	try {		 
				 generateVoucher(inputBean);
			 		} catch (Exception e) {
			 			logObj.error(applicationNumber + "  "+e);
			 				System.out.println(e);	
			 				e.printStackTrace();
			 		}
			}
//		else{
//			
//		}
 
	
     String filename = applicationNumber+" voucher.pdf";
System.out.println("file name is "+filename);
     // Security: '..' in the filename will let sneaky users access files
     // not in your repository.
//     filename = filename.replace("..", "");
     String filepath=this.getServletContext().getRealPath(File.separator)+"voucher"+File.separator+filename;
//     File file = new File(repository + filename);
     System.out.println("on servlet "+filepath);
     File file = new File(filepath);
    
     if (!file.exists())
         throw new FileNotFoundException(file.getAbsolutePath());

     response.setHeader("Content-Type", "application/pdf");
     response.setHeader("Content-Length", String.valueOf(file.length()));
     response.setHeader("Content-disposition", "attachment;filename=\"" + filename + "\"");

     BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
     BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
     byte[] buf = new byte[1024];
     while (true) {
         int length = bis.read(buf);
         if (length == -1)
             break;

         bos.write(buf, 0, length);
     }
     bos.flush();
     bos.close();
     bis.close();
			}catch (Exception e) {
				logObj.error(applicationNumber + "  "+e);
			}
 }
	 
	 
	 
	 @SuppressWarnings("static-access")
	public String generateVoucher(PaymentDocumentBean inputBean) throws Exception{
	    	
	    	String path = null;
	    	try{
	    		
	    		
	    		
	    		File file =new File(this.getServletContext().getRealPath(File.separator)+"voucher");
	    		if(!file.exists()){
	    			file.mkdir();
	    		}
	    		
	    		
	            Document document = new Document();
	            document.setPageSize(PageSize.A4.rotate());
	            
	            PdfWriter writer= PdfWriter.getInstance(document, new FileOutputStream(this.getServletContext().getRealPath(File.separator)+"voucher"+File.separator+inputBean.getApplicationNumber()+" voucher.pdf"));
	            PdfWriter.getInstance(document,new FileOutputStream(this.getServletContext().getRealPath(File.separator)+"voucher"+File.separator+inputBean.getApplicationNumber()+" voucher.pdf"));
//	            path=this.getServletContext().getRealPath(File.separator);
	            		path=inputBean.getApplicationNumber()+" voucher.pdf";
	            System.out.println("generating voucher at "+this.getServletContext().getRealPath(File.separator)+"voucher"+File.separator+inputBean.getApplicationNumber()+" voucher.pdf");
	            //temp commented
//	            PdfWriter.getInstance(document,new FileOutputStream(path));
	            
//	            path="C:\\Users\\Manpreet\\Desktop\\voucher.pdf";
	            
	            document.open();
	            
//	            PdfContentByte cb = writer.getDirectContent();
	          
	            
	        	PdfPTable mainTable=new PdfPTable(3);
	        	
	        	mainTable.setWidthPercentage(100);
	        	
	            PdfPCell cell1;
	            cell1=new PdfPCell(new Phrase("Cash Voucher (Branch Copy) through 'TM' Menu"));
	            cell1.setHorizontalAlignment(cell1.ALIGN_CENTER);
	            mainTable.addCell(cell1);
	          
	            cell1=new PdfPCell(new Phrase("Cash Voucher (Institute Copy) through 'TM' Menu"));		
	            cell1.setHorizontalAlignment(cell1.ALIGN_CENTER);
	            mainTable.addCell(cell1);
	           
	            cell1=new PdfPCell(new Phrase("Cash Voucher (Candidate Copy) through 'TM' Menu"));
	            cell1.setHorizontalAlignment(cell1.ALIGN_CENTER);
	            mainTable.addCell(cell1);	            
	            
	            
	            String bankLogo = this.getServletContext().getRealPath("images")+File.separator+"banklogo.jpg";

	            
	            PdfPTable t1=new PdfPTable(2);
	            PdfPTable t2=new PdfPTable(2);
	            PdfPTable t3=new PdfPTable(2);
	            //first table
	            {
	            	
	            PdfPCell t1cell;

	            t1cell=new PdfPCell(Image.getInstance(bankLogo));
	            t1cell.setBorderColor(new Color(255,255,255));
	            t1cell.setHorizontalAlignment(t1cell.ALIGN_CENTER);
	            t1cell.setColspan(2);
	            t1.addCell(t1cell);

	            
	            PdfPCell t1Accountcell;	 
	            t1Accountcell=new PdfPCell(new Phrase("Account No. "));
	            t1.addCell(t1Accountcell);
	            t1Accountcell=new PdfPCell(new Phrase(inputBean.getBankAccountNumber()));
	            t1.addCell(t1Accountcell);
	            t1Accountcell=new PdfPCell(new Phrase("Account Name"));
	            t1.addCell(t1Accountcell);
	            t1Accountcell=new PdfPCell(new Phrase(inputBean.getBankAccountName()));
	            t1.addCell(t1Accountcell); 
	            t1Accountcell=new PdfPCell(new Phrase("IFSC "));
	            t1.addCell(t1Accountcell);
	            t1Accountcell=new PdfPCell(new Phrase(inputBean.getCity()));
	            t1.addCell(t1Accountcell);
	            t1Accountcell=new PdfPCell(new Phrase("Applicant's Name "));
	            t1.addCell(t1Accountcell);
	            t1Accountcell=new PdfPCell(new Phrase(inputBean.getFirstName()));
	            t1.addCell(t1Accountcell);
	            t1Accountcell=new PdfPCell(new Phrase("Application Number"));
	            t1.addCell(t1Accountcell);
	            t1Accountcell=new PdfPCell(new Phrase(inputBean.getApplicationNumber()));
	            t1.addCell(t1Accountcell); 
	            t1Accountcell=new PdfPCell(new Phrase("Program Name "));
	            t1.addCell(t1Accountcell);
	            t1Accountcell=new PdfPCell(new Phrase(inputBean.getProgramName()));
	            t1.addCell(t1Accountcell);
	            t1Accountcell=new PdfPCell(new Phrase("Bank Branch Name"));
	            t1.addCell(t1Accountcell);
	            t1Accountcell=new PdfPCell(new Phrase(""));
	            t1.addCell(t1Accountcell);
	            t1Accountcell=new PdfPCell(new Phrase("Branch Code"));
	            t1.addCell(t1Accountcell);
	            t1Accountcell=new PdfPCell(new Phrase(""));	            
	            t1.addCell(t1Accountcell);
	            t1Accountcell=new PdfPCell(new Phrase("Transaction ID"));
	            t1.addCell(t1Accountcell);
	            t1Accountcell=new PdfPCell(new Phrase(""));	            
	            t1.addCell(t1Accountcell);
	            t1Accountcell=new PdfPCell(new Phrase("Deposit Date"));
	            t1.addCell(t1Accountcell);
	            t1Accountcell=new PdfPCell(new Phrase(""));	            
	            t1.addCell(t1Accountcell);
	            t1Accountcell=new PdfPCell(new Phrase("Voucher Expiry Date"));
	            t1.addCell(t1Accountcell);
	            t1Accountcell=new PdfPCell(new Phrase(inputBean.getSessionEndDate()));
	            t1.addCell(t1Accountcell);
	            t1Accountcell=new PdfPCell(new Phrase("Application fee "));
	            t1.addCell(t1Accountcell);
	            t1Accountcell=new PdfPCell(new Phrase("Rs. "+inputBean.getFee()+"/-"));
	            t1.addCell(t1Accountcell);
//	            t1Accountcell=new PdfPCell(new Phrase("Bank fee "));
//	            t1.addCell(t1Accountcell);
//	            t1Accountcell=new PdfPCell(new Phrase(""));
//	            t1.addCell(t1Accountcell);
//	            t1Accountcell=new PdfPCell(new Phrase("Total fee "));
//	            t1.addCell(t1Accountcell);
//	            t1Accountcell=new PdfPCell(new Phrase(""));
//	            t1.addCell(t1Accountcell);
	            t1Accountcell=new PdfPCell(new Phrase("Applicant Signature "));
	            t1Accountcell.setPaddingBottom(45);
	            t1.addCell(t1Accountcell);
	            t1Accountcell=new PdfPCell(new Phrase("Bank Official Signature \n with Seal "));
	            t1Accountcell.setHorizontalAlignment(t1Accountcell.ALIGN_RIGHT);
	            t1.addCell(t1Accountcell);
	            t1Accountcell=new PdfPCell(new Phrase("1.Branches should not refuse to accept the challan. \n 2.In case of any problem, please contact the host branch."));
	            t1Accountcell.setColspan(2);
	            t1.addCell(t1Accountcell);
	            
	            
	            }
	            
	            {
	            	
	            PdfPCell t2cell;

	            t2cell=new PdfPCell(Image.getInstance(bankLogo));
	            t2cell.setBorderColor(new Color(255,255,255));
	            t2cell.setHorizontalAlignment(t2cell.ALIGN_CENTER);
	            t2cell.setColspan(2);
	            t2.addCell(t2cell);

	            
	            PdfPCell t2Accountcell;	 
	            t2Accountcell=new PdfPCell(new Phrase("Account No. "));
	            t2.addCell(t2Accountcell);
	            t2Accountcell=new PdfPCell(new Phrase(inputBean.getBankAccountNumber()));
	            t2.addCell(t2Accountcell);
	            t2Accountcell=new PdfPCell(new Phrase("Account Name"));
	            t2.addCell(t2Accountcell);
	            t2Accountcell=new PdfPCell(new Phrase(inputBean.getBankAccountName()));
	            t2.addCell(t2Accountcell);
	            t2Accountcell=new PdfPCell(new Phrase("IFSC "));
	            t2.addCell(t2Accountcell);
	            t2Accountcell=new PdfPCell(new Phrase(inputBean.getCity()));
	            t2.addCell(t2Accountcell);
	            t2Accountcell=new PdfPCell(new Phrase("Applicant's Name "));
	            t2.addCell(t2Accountcell);
	            t2Accountcell=new PdfPCell(new Phrase(inputBean.getFirstName()));
	            t2.addCell(t2Accountcell);
	            t2Accountcell=new PdfPCell(new Phrase("Application Number"));
	            t2.addCell(t2Accountcell);
	            t2Accountcell=new PdfPCell(new Phrase(inputBean.getApplicationNumber()));
	            t2.addCell(t2Accountcell); 
	            t2Accountcell=new PdfPCell(new Phrase("Program Name "));
	            t2.addCell(t2Accountcell);
	            t2Accountcell=new PdfPCell(new Phrase(inputBean.getProgramName()));
	            t2.addCell(t2Accountcell);
	            t2Accountcell=new PdfPCell(new Phrase("Bank Branch Name"));
	            t2.addCell(t2Accountcell);
	            t2Accountcell=new PdfPCell(new Phrase(""));
	            t2.addCell(t2Accountcell);
	            t2Accountcell=new PdfPCell(new Phrase("Branch Code"));
	            t2.addCell(t2Accountcell);
	            t2Accountcell=new PdfPCell(new Phrase(""));	            
	            t2.addCell(t2Accountcell);
	            t2Accountcell=new PdfPCell(new Phrase("Transaction ID"));
	            t2.addCell(t2Accountcell);
	            t2Accountcell=new PdfPCell(new Phrase(""));	            
	            t2.addCell(t2Accountcell);
	            t2Accountcell=new PdfPCell(new Phrase("Deposit Date"));
	            t2.addCell(t2Accountcell);
	            t2Accountcell=new PdfPCell(new Phrase(""));	            
	            t2.addCell(t2Accountcell);
	            t2Accountcell=new PdfPCell(new Phrase("Voucher Expiry Date"));
	            t2.addCell(t2Accountcell);
	            t2Accountcell=new PdfPCell(new Phrase(inputBean.getSessionEndDate()));
	            t2.addCell(t2Accountcell);
	            t2Accountcell=new PdfPCell(new Phrase("Application fee "));
	            t2.addCell(t2Accountcell);
	            t2Accountcell=new PdfPCell(new Phrase("Rs. "+inputBean.getFee()+"/-"));
	            t2.addCell(t2Accountcell);
//	            t2Accountcell=new PdfPCell(new Phrase("Bank fee "));
//	            t2.addCell(t2Accountcell);
//	            t2Accountcell=new PdfPCell(new Phrase(""));
//	            t2.addCell(t2Accountcell);
//	            t2Accountcell=new PdfPCell(new Phrase("Total fee "));
//	            t2.addCell(t2Accountcell);
//	            t2Accountcell=new PdfPCell(new Phrase(""));
//	            t2.addCell(t2Accountcell);
	            t2Accountcell=new PdfPCell(new Phrase("Applicant Signature "));
	            t2Accountcell.setPaddingBottom(45);
	            t2.addCell(t2Accountcell);
	            t2Accountcell=new PdfPCell(new Phrase("Bank Official Signature \n with Seal "));
	            t2Accountcell.setHorizontalAlignment(t2Accountcell.ALIGN_RIGHT);
	            t2.addCell(t2Accountcell);
	            t2Accountcell=new PdfPCell(new Phrase("1.Branches should not refuse to accept the challan. \n 2.In case of any problem, please contact the host branch."));
	            t2Accountcell.setColspan(2);
	            t2.addCell(t2Accountcell);
	            
	            
	            }
	            
	            {
	            	
	            PdfPCell t3cell;

	            t3cell=new PdfPCell(Image.getInstance(bankLogo));
	            t3cell.setBorderColor(new Color(255,255,255));
	            t3cell.setHorizontalAlignment(t3cell.ALIGN_CENTER);
	            t3cell.setColspan(2);
	            t3.addCell(t3cell);

	            
	            PdfPCell t3Accountcell;	 
	            t3Accountcell=new PdfPCell(new Phrase("Account No. "));
	            t3.addCell(t3Accountcell);
	            t3Accountcell=new PdfPCell(new Phrase(inputBean.getBankAccountNumber()));
	            t3.addCell(t3Accountcell);
	            t3Accountcell=new PdfPCell(new Phrase("Account Name"));
	            t3.addCell(t3Accountcell);
	            t3Accountcell=new PdfPCell(new Phrase(inputBean.getBankAccountName()));
	            t3.addCell(t3Accountcell);
	            t3Accountcell=new PdfPCell(new Phrase("IFSC "));
	            t3.addCell(t3Accountcell);
	            t3Accountcell=new PdfPCell(new Phrase(inputBean.getCity()));
	            t3.addCell(t3Accountcell);
	            t3Accountcell=new PdfPCell(new Phrase("Applicant's Name "));
	            t3.addCell(t3Accountcell);
	            t3Accountcell=new PdfPCell(new Phrase(inputBean.getFirstName()));
	            t3.addCell(t3Accountcell);
	            t3Accountcell=new PdfPCell(new Phrase("Application Number"));
	            t3.addCell(t3Accountcell);
	            t3Accountcell=new PdfPCell(new Phrase(inputBean.getApplicationNumber()));
	            t3.addCell(t3Accountcell); 
	            t3Accountcell=new PdfPCell(new Phrase("Program Name "));
	            t3.addCell(t3Accountcell);
	            t3Accountcell=new PdfPCell(new Phrase(inputBean.getProgramName()));
	            t3.addCell(t3Accountcell);
	            t3Accountcell=new PdfPCell(new Phrase("Bank Branch Name"));
	            t3.addCell(t3Accountcell);
	            t3Accountcell=new PdfPCell(new Phrase(""));
	            t3.addCell(t3Accountcell);
	            t3Accountcell=new PdfPCell(new Phrase("Branch Code"));
	            t3.addCell(t3Accountcell);
	            t3Accountcell=new PdfPCell(new Phrase(""));	            
	            t3.addCell(t3Accountcell);
	            t3Accountcell=new PdfPCell(new Phrase("Transaction ID"));
	            t3.addCell(t3Accountcell);
	            t3Accountcell=new PdfPCell(new Phrase(""));	            
	            t3.addCell(t3Accountcell);
	            t3Accountcell=new PdfPCell(new Phrase("Deposit Date"));
	            t3.addCell(t3Accountcell);
	            t3Accountcell=new PdfPCell(new Phrase(""));	            
	            t3.addCell(t3Accountcell);
	            t3Accountcell=new PdfPCell(new Phrase("Voucher Expiry Date"));
	            t3.addCell(t3Accountcell);
	            t3Accountcell=new PdfPCell(new Phrase(inputBean.getSessionEndDate()));
	            t3.addCell(t3Accountcell);
	            t3Accountcell=new PdfPCell(new Phrase("Application fee "));
	            t3.addCell(t3Accountcell);
	            t3Accountcell=new PdfPCell(new Phrase("Rs. "+inputBean.getFee()+"/-"));
	            t3.addCell(t3Accountcell);
//	            t3Accountcell=new PdfPCell(new Phrase("Bank fee "));
//	            t3.addCell(t3Accountcell);
//	            t3Accountcell=new PdfPCell(new Phrase(""));
//	            t3.addCell(t3Accountcell);
//	            t3Accountcell=new PdfPCell(new Phrase("Total fee "));
//	            t3.addCell(t3Accountcell);
//	            t3Accountcell=new PdfPCell(new Phrase(""));
//	            t3.addCell(t3Accountcell);
	            t3Accountcell=new PdfPCell(new Phrase("Applicant Signature "));
	            t3Accountcell.setPaddingBottom(45);
	            t3.addCell(t3Accountcell);
	            t3Accountcell=new PdfPCell(new Phrase("Bank Official Signature \n with Seal "));
	            t3Accountcell.setHorizontalAlignment(t3Accountcell.ALIGN_RIGHT);
	            t3.addCell(t3Accountcell);
	            t3Accountcell=new PdfPCell(new Phrase("1.Branches should not refuse to accept the challan. \n 2.In case of any problem, please contact the host branch."));
	            t3Accountcell.setColspan(2);
	            t3.addCell(t3Accountcell);
	            
	            
	            }
	            
	            mainTable.addCell(t1);
	            mainTable.addCell(t2);
	            mainTable.addCell(t3);
	            
//	        	document.add(header1);
//	        	document.add(header2);
//	        	document.add(header3);
	        	document.add(mainTable);
	            ;
	            /*
	             * actual line to be used for doc path
	             */
//	            PdfWriter.getInstance(document, new FileOutputStream(path+File.separator+inputBean.getApplicationNumber()+".pdf"));
//	            System.out.println(path+inputBean.getApplicationNumber()+".pdf");
	           
	         
	            
	         
	            document.close();

	            
	    	}catch (Exception e) { 
//	    		logObj.logger.error("voucher"+e);	
	    		logObj.error(e);
				System.out.println("server exception "+e.getMessage());
				throw e;
			}
	    	try{
	    		paymentDAO.insertVoucherDetails(inputBean);
	    	}catch (Exception e) {
	    		logObj.error(e);
				System.out.println("server exception while inserting voucher details in table"+e.getMessage());
				throw e;
			}
	    	
	    	
			return path;
	    	
	    	
	    	
	    }
	 
	 
	}
