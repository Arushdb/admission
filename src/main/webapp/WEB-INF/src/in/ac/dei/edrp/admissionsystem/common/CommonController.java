package in.ac.dei.edrp.admissionsystem.common;


import in.ac.dei.edrp.admissionsystem.Utility;
import in.ac.dei.edrp.admissionsystem.common.beans.Applicant;
import in.ac.dei.edrp.admissionsystem.common.beans.Component;
import in.ac.dei.edrp.admissionsystem.common.beans.GroupPaper;
import in.ac.dei.edrp.admissionsystem.common.beans.GroupWiseComponent;
import in.ac.dei.edrp.admissionsystem.common.beans.ProgramWiseDetail;
import in.ac.dei.edrp.admissionsystem.paymentDetails.PaymentController;
import in.ac.dei.edrp.admissionsystem.upload.CM_StudentInfoGetter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class CommonController extends MultiActionController{

	private CommonDAO commonDAO;
	public static Logger logObj=Logger.getLogger(CommonController.class);	

	public void setCommonDAO(CommonDAO commonDAO)
	{
		this.commonDAO = commonDAO;
	}
	
	@SuppressWarnings("finally")
	public ModelAndView generateApplicationPdfOLD(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println(request.getParameter("page")+" is calling page");
		
		HttpSession session = request.getSession();
		String applicationNumber = (String) session.getAttribute("applicationNumber");
		Applicant applicantData = (Applicant) session.getAttribute("applicant");
		List<String> researchPrograms = commonDAO.getResearchPrograms(applicationNumber);
		boolean phase2Edited = applicantData.getPhase2Edited()!=null && (!applicantData.getPhase2Edited().trim().equals(""))?applicantData.getPhase2Edited().trim().equalsIgnoreCase("Y"):false;
		String pdfGenerated = "N";
		try{
		
		/** Commented by Arjun 
		in.ac.dei.edrp.admissionsystem.common.Applicant applicant = commonDAO.getApplicantBean(applicationNumber);
		String applicantDocumentPath =applicant.getApplicationDocumentPath(); **/
			
		String applicantDocumentPath = applicantData.getApplicationDocumentPath();//Added by Arjun
		
		System.out.println("application document path is "+applicantDocumentPath);
		String homeDir = System.getProperty("user.home");
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
		System.out.println("server path is "+docRootFolder);
		
		
		
		if(applicantDocumentPath==null || applicantDocumentPath==""){
			applicantDocumentPath=docRootFolder+File.separator+applicationNumber;
		}
		
		
		File file1 =new File(applicantDocumentPath);

		if(!file1.exists()){
			file1.mkdirs();
		}
		
		
//		InputStream input = null;
		Document document = new Document();
//		java.io.PrintWriter out = null;
//		try {
//			out = response.getWriter( );
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}	
		try
		{
//			Properties prop = new Properties();
//			input = new FileInputStream(this.getServletContext().getRealPath("WEB-INF")+File.separator+"classes"+File.separator+"in"+File.separator+"ac"+File.separator+"dei"+File.separator+"edrp"+File.separator+"client"+File.separator+"Shared"+File.separator+"constants.properties");
//			prop.load(input);
			Image applicantPhoto =null;
			Image applicantSignature=null;
			Image universityLogo = null;
		try{	
		 applicantPhoto = Image.getInstance(applicantDocumentPath+File.separator+"photo.jpg");
		
		}catch (Exception e) {
			applicantPhoto=Image.getInstance(this.getServletContext().getRealPath("images")+File.separator+"USER2.jpg");
			if(applicantData.getApplicationStatus().trim().equalsIgnoreCase("D"))
			 {
				 logObj.error("Photo Image Exception "+e);
			 }
			 else
			 {
				 //Do Nothing
			 }
			
			}
		
		try{
		 applicantSignature = Image.getInstance(applicantDocumentPath+File.separator+"signature.jpg");
		 
		}catch (Exception e) {
			 applicantSignature = Image.getInstance(this.getServletContext().getRealPath("images")+File.separator+"blank1.jpg");
			 if(applicantData.getApplicationStatus().trim().equalsIgnoreCase("D"))
			 {
				 logObj.error("signature Image Exception "+e);
			 }
			 else
			 {
				 //Do Nothing
			 }
			
			}
		
		try{
		 universityLogo = Image.getInstance(this.getServletContext().getRealPath("images")+File.separator+"deiLogo.jpg");
		
		}catch (Exception e) {
			System.out.println("Image Exception "+e);	
			logObj.error("university logo Image Exception "+e);
			}
			
			
		 applicantPhoto.setAbsolutePosition(480f, 700f);
	        applicantPhoto.scaleAbsolute(70f, 80f);
			applicantPhoto.setBorder(Image.BOX);
			applicantPhoto.setBorderWidth(2);	
			applicantSignature.scaleAbsolute(50f, 50f);
			applicantSignature.setAlignment(Element.ALIGN_RIGHT);
			universityLogo.scaleAbsolute(60, 60);
			universityLogo.setAbsolutePosition(40f, 725f);
						
		BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,BaseFont.WINANSI,BaseFont.EMBEDDED );
		BaseFont baseBoldFont = BaseFont.createFont(BaseFont.HELVETICA_BOLD,BaseFont.WINANSI,BaseFont.EMBEDDED );
		Font boldFont = new Font(baseBoldFont, 8);
		Font font = new Font(bf, 8);
		
		
		
		
		Font boldHeaderFont = new Font(baseBoldFont, 9);
		
		Font disclaimerFont = new Font(baseBoldFont, 6);
		System.out.println("coming here");
		PdfWriter.getInstance(document, new FileOutputStream(applicantDocumentPath+File.separator+applicationNumber+".pdf"));
		document.setPageSize(PageSize.A4);
		document.setMargins(50, 50, 30, 30);
		document.setMarginMirroring(true);
				
		Paragraph headerLine1 = new Paragraph("Dayalbagh Educational Institute".toUpperCase(), new Font(BaseFont.createFont(this.getServletContext().getRealPath( "Fonts") + File.separator+ "BOLD.TTF",BaseFont.WINANSI,BaseFont.EMBEDDED ), 15));
		headerLine1.setAlignment(Element.ALIGN_CENTER);
		
		Paragraph headerLine2 = new Paragraph("Application PDF "+" : ".toUpperCase()+applicantData.getSessionCombined()+"\n\n\n\n", font);
		headerLine2.setAlignment(Element.ALIGN_CENTER);
		
		System.out.println("coming here 1");
		
		
			
		PdfPTable programTable = new PdfPTable(new float[]{4.F,2F,4F});
		programTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		programTable.setWidthPercentage(90);
		programTable.setSpacingBefore(5);
		programTable.setSpacingAfter(5);
		
		programTable.addCell(new Phrase("Program Name", boldFont));
		if(phase2Edited)
		{
			programTable.addCell(new Phrase("Study Mode", boldFont));
			programTable.addCell(new Phrase("Study Center", boldFont));
		}
		else
		{
			programTable.addCell(new Phrase("", boldFont));
			programTable.addCell(new Phrase("", boldFont));
		}
		
		
		System.out.println("coming here 2");
		PdfPTable branchTable = new PdfPTable(2);
		//branchTable.setWidthPercentage(100);
		branchTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		branchTable.setSpacingAfter(5);
		branchTable.setSpacingBefore(5);
		
		try{
		for(int i=0; i<applicantData.getApplicationForm().getProgramWiseDetails().size(); i++)
		{
			ProgramWiseDetail programDetail = applicantData.getApplicationForm().getProgramWiseDetails().get(i);
			programTable.addCell(new Phrase(programDetail.getProgramName(), font));
			if(phase2Edited)
			{
				programTable.addCell(new Phrase(programDetail.getSelectedEducationModeName(), font));
				programTable.addCell(new Phrase(programDetail.getStudyCenterName(), font));
			}
			else
			{
				programTable.addCell(new Phrase("", font));
				programTable.addCell(new Phrase("", font));
			}
			

			if(applicantData.getApplicationForm().getBranchAvailability().trim().equalsIgnoreCase("Y"))
			{
					if(programDetail.getPreferredBranchAvailable().trim().equalsIgnoreCase("Y"))
					{
						branchTable.addCell(new Phrase("For "+programDetail.getProgramName(), boldFont));
						PdfPTable pbTable = new PdfPTable(2);
						//branchTable.setWidthPercentage(100);
						pbTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
						pbTable.setSpacingAfter(5);
						pbTable.setSpacingBefore(5);
						
						int prgBranchCount = programDetail.getProgramBranches().size()>3 ? 3 : programDetail.getProgramBranches().size();
						switch(prgBranchCount)
						{
							case 1:
								pbTable.addCell(new Phrase("Branch 1", boldFont));
								pbTable.addCell(new Phrase(programDetail.getBranch1Name(), font));
							break;
							case 2:
								pbTable.addCell(new Phrase("Branch 1", boldFont));
								pbTable.addCell(new Phrase(programDetail.getBranch1Name(), font));
								pbTable.addCell(new Phrase("Branch 2", boldFont));
								pbTable.addCell(new Phrase(programDetail.getBranch2Name(), font));
							break;
							case 3:
								pbTable.addCell(new Phrase("Branch 1", boldFont));
								pbTable.addCell(new Phrase(programDetail.getBranch1Name(), font));
								pbTable.addCell(new Phrase("Branch 2", boldFont));
								pbTable.addCell(new Phrase(programDetail.getBranch2Name(), font));
								pbTable.addCell(new Phrase("Branch 3", boldFont));
								pbTable.addCell(new Phrase(programDetail.getBranch3Name(), font));
							break;
							default:
								//Nothing to do
						}
						
						branchTable.addCell(pbTable);
					
					
				}
				
			}
		
			
		}
		
		}catch (Exception e) {
			programTable.addCell(new Phrase("", font));	
			programTable.addCell(new Phrase("No program chosen yet", font));

		System.out.println("No program chosen yet "+e);
		logObj.error("No program selected yet "+e);
		}

		PdfPTable personalDetailsTable = new PdfPTable(new float[]{1.5F,2.8F,2F,1.4F});
		personalDetailsTable.setWidthPercentage(90);
		personalDetailsTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		personalDetailsTable.setSpacingBefore(5);
		personalDetailsTable.setSpacingAfter(5);
		personalDetailsTable.getDefaultCell().setPaddingTop(3);
		personalDetailsTable.getDefaultCell().setPaddingBottom(3);
		
		personalDetailsTable.addCell(new Phrase("Applicant Name", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getApplicantName(), font));
		personalDetailsTable.addCell(new Phrase("", boldFont));
		personalDetailsTable.addCell(new Phrase("", font));
		
		personalDetailsTable.addCell(new Phrase("Father Name", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getFatherName(), font));
		personalDetailsTable.addCell(new Phrase("Father Income", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getApplicationForm().getProgramWiseDetails().get(0).getFatherIncome(), font));
		
		personalDetailsTable.addCell(new Phrase("Mother Name", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getMotherName(), font));
		personalDetailsTable.addCell(new Phrase("Mother Income", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getApplicationForm().getProgramWiseDetails().get(0).getMotherIncome(), font));
		
		personalDetailsTable.addCell(new Phrase("Guardian Name", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getGuardianName(), font));
		personalDetailsTable.addCell(new Phrase("Guardian Income", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getApplicationForm().getProgramWiseDetails().get(0).getGuardianIncome(), font));
		
		personalDetailsTable.addCell(new Phrase("Date of Birth", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getDobIST(), font));
		personalDetailsTable.addCell(new Phrase("Category", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getCategoryDesc(), font));
		
		personalDetailsTable.addCell(new Phrase("Gender", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getGenderDesc(), font));
		personalDetailsTable.addCell(new Phrase("Religion", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getReligion(), font));
				
		personalDetailsTable.addCell(new Phrase("Primary Email", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getPrimaryEmailID(), font));
		personalDetailsTable.addCell(new Phrase("Nationality", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getNationality(), font));
		
		personalDetailsTable.addCell(new Phrase("Address", boldFont));
		
		try
		{
			String address = applicantData.getCorAddressL1()+",\n"+applicantData.getCorAddressL2();
			if(applicantData.getCorCountry().trim().equalsIgnoreCase("IN"))
			{
				address = address + ","+applicantData.getCorCity()+",\n"+applicantData.getCorDistrict()+", "+"Pincode : "+applicantData.getCorPincode()+",\n"+applicantData.getCorState();//+","+applicantData.getCorCountry();
			}
			else
			{
				//address = address + ",\n"+applicantData.getCorCountry();
			}
			PdfPCell addressCell = new PdfPCell(new Phrase(address, font));
			addressCell.setBorder(Rectangle.NO_BORDER);
			addressCell.setColspan(3);
			addressCell.setPadding(0);
			personalDetailsTable.addCell(addressCell);
			
		}
		catch (Exception e) 
		{
			logObj.error(" Address "+e);
		}
		
		personalDetailsTable.addCell(new Phrase("Contact Number", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getHomePhone(), font));
		personalDetailsTable.addCell(new Phrase("Physically Handicapped", boldFont));
		String pwd = applicantData.getPwd().trim().equalsIgnoreCase("Y")?"YES":"NO";
		personalDetailsTable.addCell(new Phrase(pwd, font));
		
		personalDetailsTable.addCell(new Phrase("Hostel Required ? ", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getApplicationForm().getProgramWiseDetails().get(0).getHostelRequired(), font));
		personalDetailsTable.addCell(new Phrase("Ever Expelled ?", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getApplicationForm().getProgramWiseDetails().get(0).getEverExpelled(), font));
		
		
		
		System.out.println("Coming upto 4");

		PdfPTable examCenterTable = new PdfPTable(2);
		examCenterTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		examCenterTable.setSpacingBefore(5);
		examCenterTable.setSpacingAfter(5);
		
		PdfPTable entranceTestTable = new PdfPTable(2);
		entranceTestTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		entranceTestTable.setSpacingBefore(5);
		entranceTestTable.setSpacingAfter(5);
		entranceTestTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		
		System.out.println("Coming upto 6");
		if(applicantData.getApplicationForm().getPapersAvailability().trim().equalsIgnoreCase("Y"))
		{
		try{
		
			examCenterTable.addCell(new Phrase("Preferred Examination Center", boldFont));
			examCenterTable.addCell(new Phrase(applicantData.getExamCenter1Name().toUpperCase(), font));
			
			int noOfPrograms = applicantData.getApplicationForm().getProgramWiseDetails().size();
		for(int i=0; i< noOfPrograms;i++)
		{
			ProgramWiseDetail prgDetail = applicantData.getApplicationForm().getProgramWiseDetails().get(i);
			if(!(prgDetail.getPaperMainGroup()==null || prgDetail.getPaperMainGroup().trim().equalsIgnoreCase("")))
			{
				int paperGroups = prgDetail.getProgramPaper().getGroupPapers().size();
				entranceTestTable.addCell(new Phrase("For "+prgDetail.getProgramName(), boldFont));
				//entranceTestTable.addCell(new Phrase("Subject Group : "+prgDetail.getProgramPaper().getMainGroupName(), boldFont));
				entranceTestTable.addCell(new Phrase("", boldFont));
				for(int j = 0; j<paperGroups; j++)
				{
					PdfPCell groupPaperCell = new PdfPCell(new Phrase("Roll Number", boldFont));
					groupPaperCell.setBorder(Rectangle.NO_BORDER);
					groupPaperCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					PdfPTable paperTable = new PdfPTable(1);
					paperTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
					int columnIndex = (j+1)%2;
					if(columnIndex==1 && (j == (paperGroups-1)))
					{
						groupPaperCell.setColspan(2);
					}
					GroupPaper groupPaper = prgDetail.getProgramPaper().getGroupPapers().get(j);
					if(groupPaper.getAllPaperSize() == groupPaper.getMaxChoice())
					{
						paperTable.addCell(new Phrase("Compulsory Papers", new Font(baseBoldFont, 8)));
					}
					else
					{
						paperTable.addCell(new Phrase("Papers Opted", new Font(baseBoldFont, 8)));
					}
					
					for(int k=0; k < groupPaper.getSelectedPapers().size(); k++)
					{
						paperTable.addCell(new Phrase((k+1)+". "+groupPaper.getSelectedPapers().get(k).getDescription().toUpperCase(), new Font(bf, 8)));
					}
					groupPaperCell.addElement(paperTable);
					entranceTestTable.addCell(groupPaperCell);
				}
			}
			else
			{
				//Do Nothing
			}
		}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			logObj.error(" "+e);
		}
		}//If of Programs Availability Checking Ends Here
		
		PdfPTable compTable = new PdfPTable(5);
		compTable.setWidthPercentage(100);
		compTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		compTable.setSpacingAfter(5);
		compTable.setSpacingBefore(5);
		
		
		
		if(phase2Edited)
		{
			
			int groupWiseComponents = applicantData.getApplicationForm().getGroupWiseComponents().size();
			for(int i = 0 ; i < groupWiseComponents ; i++)
			{
				GroupWiseComponent grpWComponent = applicantData.getApplicationForm().getGroupWiseComponents().get(i);
				
				PdfPCell sectionHeaderCell = new PdfPCell(new Phrase(grpWComponent.getDescription(), boldHeaderFont));
				sectionHeaderCell.setBorder(Rectangle.NO_BORDER);
				sectionHeaderCell.setColspan(5);
				sectionHeaderCell.setPaddingTop(5);
				sectionHeaderCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				compTable.addCell(sectionHeaderCell);
				for(int j = 0; j < grpWComponent.getComponents().size(); j++)
				{
					Component component = grpWComponent.getComponents().get(j);
					if(j==0)
					{
						compTable.addCell(new Phrase("", font));
						compTable.addCell(new Phrase("OBTAINED MARKS", boldFont));
						compTable.addCell(new Phrase("TOTAL MARKS", boldFont));
						
						if(component.getBoardFlag().trim().equalsIgnoreCase("Y"))
						{
							compTable.addCell(new Phrase("BOARD NAME", boldFont));
						}
						else if(grpWComponent.getUniversityToAsk()==1)
						{
							compTable.addCell(new Phrase("UNIVERSITY NAME", boldFont));
						}
						else
						{
							compTable.addCell(new Phrase("", font));
						}
						
						compTable.addCell(new Phrase("YEAR OF PASSING", boldFont));
					}
					
					if(grpWComponent.getShowOptions()==0)
					{
						compTable.addCell(new Phrase(component.getComponentName(), font));
					}
					else
					{
						//Degree Name may be displayed here.
						compTable.addCell(new Phrase(component.getComponentName(), font));
					}
					
					compTable.addCell(new Phrase(component.getObtainedMarks(), font));
					compTable.addCell(new Phrase(component.getTotalMarks(), font));
					
					if(component.getBoardFlag().trim().equalsIgnoreCase("Y"))
					{
						compTable.addCell(new Phrase(component.getBoardName(), font));
					}
					else if(grpWComponent.getUniversityToAsk()==1)
					{
						compTable.addCell(new Phrase(component.getOtherBoardName(), font));
					}
					else
					{
						compTable.addCell(new Phrase("", font));
					}
					
					if(component.getPassingYear().trim().equalsIgnoreCase("Y"))
					{
						compTable.addCell(new Phrase(component.getPassingYearValue(), font));
					}
					else
					{
						compTable.addCell(new Phrase("", font));
					}
					
				
				}
				
				for(int j = 0; j < grpWComponent.getGateComponents().size(); j++)
				{
					Component component = grpWComponent.getGateComponents().get(j);
					if(j==0)
					{
						compTable.addCell(new Phrase("GATE RANK", boldFont));
						compTable.addCell(new Phrase("SCORE", boldFont));
						compTable.addCell(new Phrase("TOTAL APPLICANTS", boldFont));
						compTable.addCell(new Phrase("GATE BRANCH", boldFont));
						compTable.addCell(new Phrase("GATE YEAR", boldFont));
					}
					
					compTable.addCell(new Phrase(component.getRanking(), font));
					compTable.addCell(new Phrase(component.getScore(), font));
					compTable.addCell(new Phrase(component.getTotalApplicants(), font));
					compTable.addCell(new Phrase(component.getGateBranch(), font));
					compTable.addCell(new Phrase(component.getGateYear(), font));
					
				
				}
			}
			
			
		}
		
		PdfPTable applicationNumberTable = new PdfPTable(new float[]{.25F, .15F, .2F, .25F});
		applicationNumberTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		applicationNumberTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		applicationNumberTable.setSpacingBefore(5);
		applicationNumberTable.setSpacingAfter(5);
		applicationNumberTable.addCell(new Phrase("Application Number", boldHeaderFont));
		applicationNumberTable.addCell(new Phrase(applicationNumber, font));
		applicationNumberTable.addCell(new Phrase("Application Fees", boldHeaderFont));
		applicationNumberTable.addCell(new Phrase(applicantData.getApplicationFee()+" INR", font));
		
		
		PdfPTable researchDetails = new PdfPTable(new float[]{30F, 20F, 25F, 25F});
		researchDetails.setWidthPercentage(90);
		if(researchPrograms.size()>0)
		{
			researchDetails.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			researchDetails.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			researchDetails.setSpacingBefore(5);
			researchDetails.setSpacingAfter(5);
			researchDetails.addCell(new Phrase("Published Papers", boldFont));
			researchDetails.addCell(new Phrase(applicantData.getPublishedPapers(), font));
			researchDetails.addCell(new Phrase("Journal Description", boldFont));
			researchDetails.addCell(new Phrase(applicantData.getJournalDesc(), font));
			researchDetails.addCell(new Phrase("Conferences", boldFont));
			researchDetails.addCell(new Phrase(applicantData.getConferences(), font));
			researchDetails.addCell(new Phrase("Conferences Description", boldFont));
			researchDetails.addCell(new Phrase(applicantData.getConferencesDesc(), font));
			researchDetails.addCell(new Phrase("Fellowship", boldFont));
			researchDetails.addCell(new Phrase(getFlagDesc(applicantData.getFellowship()), font));
			researchDetails.addCell(new Phrase("Fellowship Description", boldFont));
			researchDetails.addCell(new Phrase(applicantData.getFellowshipDesc(), font));
			researchDetails.addCell(new Phrase("RET Qualified", boldFont));
			researchDetails.addCell(new Phrase(getFlagDesc(applicantData.getRetQualified()), font));
			if(applicantData.getRetQualified()!=null && applicantData.getRetQualified().trim().equalsIgnoreCase("Y"))
			{
				researchDetails.addCell(new Phrase("RET Qualification Year", boldFont));
				researchDetails.addCell(new Phrase(applicantData.getRetYear(), font));
				researchDetails.addCell(new Phrase("RET Roll Number", boldFont));
				researchDetails.addCell(new Phrase(applicantData.getRetRollNumber(), font));
			}
			researchDetails.addCell(new Phrase("RET Remarks", boldFont));
			researchDetails.addCell(new Phrase(applicantData.getRetRemarks(), font));
			researchDetails.addCell(new Phrase("Institute Teacher", boldFont));
			researchDetails.addCell(new Phrase(getFlagDesc(applicantData.getInstituteTeacher()), font));
			researchDetails.addCell(new Phrase("JRF Qualified", boldFont));
			researchDetails.addCell(new Phrase(getFlagDesc(applicantData.getJrfQualified()), font));
			researchDetails.addCell(new Phrase("Recipient of Fellowship", boldFont));
			researchDetails.addCell(new Phrase(getFlagDesc(applicantData.getDeiScholor()), font));
			researchDetails.addCell(new Phrase("DEI M.Phil. Scholor", boldFont));
			researchDetails.addCell(new Phrase(getFlagDesc(applicantData.getDeiMphil()), font));
			researchDetails.addCell(new Phrase("Recipient Type", boldFont));
			researchDetails.addCell(new Phrase(applicantData.getRecipientType(), font));
			researchDetails.addCell(new Phrase("", boldFont));
			researchDetails.addCell(new Phrase("", font));
			researchDetails.addCell(new Phrase("DEI Medal Winner", boldFont));
			researchDetails.addCell(new Phrase(getFlagDesc(applicantData.getDeiMedalWinner()), font));
			researchDetails.addCell(new Phrase("DEI Post Graduate", boldFont));
			researchDetails.addCell(new Phrase(getFlagDesc(applicantData.getDeiPG()), font));
			researchDetails.addCell(new Phrase("CGPA >= 9 in Post Graduation", boldFont));
			researchDetails.addCell(new Phrase(getFlagDesc(applicantData.getCgpa9()), font));
			researchDetails.addCell(new Phrase("", boldHeaderFont));
			researchDetails.addCell(new Phrase("", boldHeaderFont));
		}
		
			
		System.out.println("coming upto 11");
		document.open();
		document.add(universityLogo);
		document.add(headerLine1);
		document.add(headerLine2);
		document.add(applicantPhoto);
		document.add(applicationNumberTable);
		document.add(new Paragraph("Program(s) Applied for", boldHeaderFont));
		document.add(programTable);
		document.add(new Paragraph("Personal Information", boldHeaderFont));
		document.add(personalDetailsTable);
		if(applicantData.getApplicationForm().getPapersAvailability().trim().equalsIgnoreCase("Y"))
		{
			document.add(new Paragraph("Selected Examination Center", boldHeaderFont));
			document.add(examCenterTable);
			document.add(new Paragraph("Selected Test Papers", boldHeaderFont));
			document.add(entranceTestTable);
			
		}
		
		document.add(compTable);
		if(researchPrograms.size()>0)
		{
			document.add(new Paragraph("Details For Research Programs", boldHeaderFont));
			document.add(researchDetails);
		}
		if(applicantData.getApplicationForm().getBranchAvailability().trim().equalsIgnoreCase("Y"))
		{
			document.add(new Paragraph("Preferred Branches Options", boldHeaderFont));
			document.add(branchTable);
		}
		
		document.add(new Paragraph("Disclaimer", boldHeaderFont));
		document.add(new Paragraph("1. I certify that the above statements have been filled by me and that the entries made are correct.".toUpperCase(),disclaimerFont));
//		document.add(declarationParagraph);
		
		document.add(applicantSignature);
//		document.add(noticeParagraph);
				
		
		
		pdfGenerated = "Y";
		//return pdfGenerated;
		   
//		return returnPage;
   
		  
//			throw new Exception("Exception aa gyi");
		}
		catch(Exception ex)
		{
			logObj.error(" "+ex);
			document.close();
			String error=ex.getMessage()+"\n";
			System.out.println(ex);
			CM_StudentInfoGetter studentInfo = new CM_StudentInfoGetter();
			studentInfo.setForm_number(applicationNumber);
			studentInfo.setFile_name(ex.getMessage());
			error=error+commonDAO.generateErrorLogs(studentInfo);
            System.out.println("returning error is "+error);
//			 out.println(error);
//			return returnPage;

//            return new ModelAndView("application/PdfError","message",error);
		}
		finally
		{	 
			document.close();
		try{	
			String filename = applicationNumber+".pdf";
			System.out.println("file name is "+filename);
			     // Security: '..' in the filename will let sneaky users access files
			     // not in your repository.
//			     filename = filename.replace("..", "");
			     String filepath=applicantDocumentPath+File.separator+filename;
//			     File file = new File(repository + filename);
			     System.out.println("on servlet "+filepath);
			     File file = new File(filepath);
			    
			     if (!file.exists())
					try {
						throw new FileNotFoundException(file.getAbsolutePath());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						logObj.error(" "+e);
						e.printStackTrace();
					}

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

			System.out.println("downloaded"); 
//			return new ModelAndView("application/UploadDocs");
//			return returnPage;

		}catch(Exception e){
			logObj.error("Exception while downloading "+e);
			System.out.println("Exception while downloading "+e.getMessage());
//			return new ModelAndView("application/TabController","message","Exception while downloading "+e.getMessage());
			document.close();
			String error=e.getMessage()+"\n";
			System.out.println(e);
			CM_StudentInfoGetter studentInfo = new CM_StudentInfoGetter();
			studentInfo.setForm_number(applicationNumber);
			studentInfo.setFile_name(e.getMessage());
			error=error+commonDAO.generateErrorLogs(studentInfo);
            System.out.println("returning error is "+error);
//			 out.println(error);
//			return returnPage;
//            return new ModelAndView("application/PdfError","message",error);
		
		}

		}

//		return null;
		}catch (Exception e) {
			logObj.error(e);
		}
		return null;	

	
	}
	 
	
	public String updateApplicationPDF(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println(request.getParameter("page")+" is calling page");
		String applicantDocumentPath = null;
		HttpSession session = request.getSession();
		String applicationNumber = (String) session.getAttribute("applicationNumber");
		Applicant applicantData = (Applicant) session.getAttribute("applicant");
		List<String> researchPrograms = commonDAO.getResearchPrograms(applicationNumber);
		boolean phase2Edited = applicantData.getPhase2Edited()!=null && (!applicantData.getPhase2Edited().trim().equals(""))?applicantData.getPhase2Edited().trim().equalsIgnoreCase("Y"):false;
		String pdfGenerated = "N";
		try{
		
		applicantDocumentPath = Utility.getDocumentPath(request, this.getServletContext());
		if(applicantDocumentPath == null || applicantDocumentPath.trim().equalsIgnoreCase(""))
		{
			throw new Exception();
		}
		
		File file1 = new File(applicantDocumentPath);

		if(!file1.exists()){
			file1.mkdirs();
		}
		
		
//		InputStream input = null;
		Document document = new Document();
//		java.io.PrintWriter out = null;
//		try {
//			out = response.getWriter( );
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}	
		try
		{
//			Properties prop = new Properties();
//			input = new FileInputStream(this.getServletContext().getRealPath("WEB-INF")+File.separator+"classes"+File.separator+"in"+File.separator+"ac"+File.separator+"dei"+File.separator+"edrp"+File.separator+"client"+File.separator+"Shared"+File.separator+"constants.properties");
//			prop.load(input);
			Image applicantPhoto =null;
			Image applicantSignature=null;
			Image universityLogo = null;
		try{	
		 applicantPhoto = Image.getInstance(applicantDocumentPath+File.separator+"photo.jpg");
		
		}catch (Exception e) {
			applicantPhoto=Image.getInstance(this.getServletContext().getRealPath("images")+File.separator+"USER2.jpg");
			if(applicantData.getApplicationStatus().trim().equalsIgnoreCase("D"))
			 {
				 logObj.error("Photo Image Exception "+e);
			 }
			 else
			 {
				 //Do Nothing
			 }
			
			}
		
		try{
		 applicantSignature = Image.getInstance(applicantDocumentPath+File.separator+"signature.jpg");
		 
		}catch (Exception e) {
			 applicantSignature = Image.getInstance(this.getServletContext().getRealPath("images")+File.separator+"blank1.jpg");
			 if(applicantData.getApplicationStatus().trim().equalsIgnoreCase("D"))
			 {
				 logObj.error("signature Image Exception "+e);
			 }
			 else
			 {
				 //Do Nothing
			 }
			
			}
		
		try{
		 universityLogo = Image.getInstance(this.getServletContext().getRealPath("images")+File.separator+"deiLogo.jpg");
		
		}catch (Exception e) {
			System.out.println("Image Exception "+e);	
			logObj.error("university logo Image Exception "+e);
			}
			
			
		 applicantPhoto.setAbsolutePosition(480f, 700f);
	        applicantPhoto.scaleAbsolute(70f, 80f);
			applicantPhoto.setBorder(Image.BOX);
			applicantPhoto.setBorderWidth(2);	
			applicantSignature.scaleAbsolute(50f, 50f);
			applicantSignature.setAlignment(Element.ALIGN_RIGHT);
			universityLogo.scaleAbsolute(60, 60);
			universityLogo.setAbsolutePosition(40f, 725f);
						
		BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,BaseFont.WINANSI,BaseFont.EMBEDDED );
		BaseFont baseBoldFont = BaseFont.createFont(BaseFont.HELVETICA_BOLD,BaseFont.WINANSI,BaseFont.EMBEDDED );
		Font boldFont = new Font(baseBoldFont, 8);
		Font font = new Font(bf, 8);
		
		
		
		
		Font boldHeaderFont = new Font(baseBoldFont, 9);
		
		Font disclaimerFont = new Font(baseBoldFont, 6);
		System.out.println("coming here");
		PdfWriter.getInstance(document, new FileOutputStream(applicantDocumentPath+File.separator+applicationNumber+".pdf"));
		document.setPageSize(PageSize.A4);
		document.setMargins(50, 50, 30, 30);
		document.setMarginMirroring(true);
				
		Paragraph headerLine1 = new Paragraph("Dayalbagh Educational Institute".toUpperCase(), new Font(BaseFont.createFont(this.getServletContext().getRealPath( "Fonts") + File.separator+ "BOLD.TTF",BaseFont.WINANSI,BaseFont.EMBEDDED ), 15));
		headerLine1.setAlignment(Element.ALIGN_CENTER);
		
		Paragraph headerLine2 = new Paragraph("Application PDF "+" : ".toUpperCase()+applicantData.getSessionCombined()+"\n\n\n\n", font);
		headerLine2.setAlignment(Element.ALIGN_CENTER);
		
		System.out.println("coming here 1");
		
		
			
		PdfPTable programTable = new PdfPTable(new float[]{4.F,2F,4F});
		programTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		programTable.setWidthPercentage(90);
		programTable.setSpacingBefore(5);
		programTable.setSpacingAfter(5);
		
		programTable.addCell(new Phrase("Program Name", boldFont));
		if(phase2Edited)
		{
			programTable.addCell(new Phrase("Study Mode", boldFont));
			programTable.addCell(new Phrase("Study Center", boldFont));
		}
		else
		{
			programTable.addCell(new Phrase("", boldFont));
			programTable.addCell(new Phrase("", boldFont));
		}
		
		
		System.out.println("coming here 2");
		PdfPTable branchTable = new PdfPTable(2);
		//branchTable.setWidthPercentage(100);
		branchTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		branchTable.setSpacingAfter(5);
		branchTable.setSpacingBefore(5);
		
		try{
		for(int i=0; i<applicantData.getApplicationForm().getProgramWiseDetails().size(); i++)
		{
			ProgramWiseDetail programDetail = applicantData.getApplicationForm().getProgramWiseDetails().get(i);
			programTable.addCell(new Phrase(programDetail.getProgramName(), font));
			if(phase2Edited)
			{
				programTable.addCell(new Phrase(programDetail.getSelectedEducationModeName(), font));
				programTable.addCell(new Phrase(programDetail.getStudyCenterName(), font));
			}
			else
			{
				programTable.addCell(new Phrase("", font));
				programTable.addCell(new Phrase("", font));
			}
			

			if(applicantData.getApplicationForm().getBranchAvailability().trim().equalsIgnoreCase("Y"))
			{
					if(programDetail.getPreferredBranchAvailable().trim().equalsIgnoreCase("Y"))
					{
						branchTable.addCell(new Phrase("For "+programDetail.getProgramName(), boldFont));
						PdfPTable pbTable = new PdfPTable(2);
						//branchTable.setWidthPercentage(100);
						pbTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
						pbTable.setSpacingAfter(5);
						pbTable.setSpacingBefore(5);
						
						int prgBranchCount = programDetail.getProgramBranches().size()>5 ? 5 : programDetail.getProgramBranches().size();
						switch(prgBranchCount)
						{
							case 1:
								pbTable.addCell(new Phrase("Branch 1", boldFont));
								pbTable.addCell(new Phrase(programDetail.getBranch1Name(), font));
							break;
							case 2:
								pbTable.addCell(new Phrase("Branch 1", boldFont));
								pbTable.addCell(new Phrase(programDetail.getBranch1Name(), font));
								pbTable.addCell(new Phrase("Branch 2", boldFont));
								pbTable.addCell(new Phrase(programDetail.getBranch2Name(), font));
							break;
							case 3:
								pbTable.addCell(new Phrase("Branch 1", boldFont));
								pbTable.addCell(new Phrase(programDetail.getBranch1Name(), font));
								pbTable.addCell(new Phrase("Branch 2", boldFont));
								pbTable.addCell(new Phrase(programDetail.getBranch2Name(), font));
								pbTable.addCell(new Phrase("Branch 3", boldFont));
								pbTable.addCell(new Phrase(programDetail.getBranch3Name(), font));
							break;
							case 4:
								pbTable.addCell(new Phrase("Branch 1", boldFont));
								pbTable.addCell(new Phrase(programDetail.getBranch1Name(), font));
								pbTable.addCell(new Phrase("Branch 2", boldFont));
								pbTable.addCell(new Phrase(programDetail.getBranch2Name(), font));
								pbTable.addCell(new Phrase("Branch 3", boldFont));
								pbTable.addCell(new Phrase(programDetail.getBranch3Name(), font));
								pbTable.addCell(new Phrase("Branch 4", boldFont));
								pbTable.addCell(new Phrase(programDetail.getBranch4Name(), font));
							break;
							case 5:
								pbTable.addCell(new Phrase("Branch 1", boldFont));
								pbTable.addCell(new Phrase(programDetail.getBranch1Name(), font));
								pbTable.addCell(new Phrase("Branch 2", boldFont));
								pbTable.addCell(new Phrase(programDetail.getBranch2Name(), font));
								pbTable.addCell(new Phrase("Branch 3", boldFont));
								pbTable.addCell(new Phrase(programDetail.getBranch3Name(), font));
								pbTable.addCell(new Phrase("Branch 4", boldFont));
								pbTable.addCell(new Phrase(programDetail.getBranch4Name(), font));
								pbTable.addCell(new Phrase("Branch 5", boldFont));
								pbTable.addCell(new Phrase(programDetail.getBranch5Name(), font));
							break;
							
							default:
								//Nothing to do
						}
						
						branchTable.addCell(pbTable);
					
					
				}
				
			}
		
			
		}
		
		}catch (Exception e) {
			programTable.addCell(new Phrase("", font));	
			programTable.addCell(new Phrase("No program chosen yet", font));

		System.out.println("No program chosen yet "+e);
		logObj.error("No program selected yet "+e);
		}

		PdfPTable personalDetailsTable = new PdfPTable(new float[]{1.5F,2.8F,2F,1.4F});
		personalDetailsTable.setWidthPercentage(90);
		personalDetailsTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		personalDetailsTable.setSpacingBefore(5);
		personalDetailsTable.setSpacingAfter(5);
		personalDetailsTable.getDefaultCell().setPaddingTop(3);
		personalDetailsTable.getDefaultCell().setPaddingBottom(3);
		
		personalDetailsTable.addCell(new Phrase("Applicant Name", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getApplicantName(), font));
		personalDetailsTable.addCell(new Phrase("", boldFont));
		personalDetailsTable.addCell(new Phrase("", font));
		
		personalDetailsTable.addCell(new Phrase("Father Name", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getFatherName(), font));
		personalDetailsTable.addCell(new Phrase("Father Income", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getApplicationForm().getProgramWiseDetails().get(0).getFatherIncome(), font));
		
		personalDetailsTable.addCell(new Phrase("Mother Name", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getMotherName(), font));
		personalDetailsTable.addCell(new Phrase("Mother Income", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getApplicationForm().getProgramWiseDetails().get(0).getMotherIncome(), font));
		
		personalDetailsTable.addCell(new Phrase("Guardian Name", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getGuardianName(), font));
		personalDetailsTable.addCell(new Phrase("Guardian Income", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getApplicationForm().getProgramWiseDetails().get(0).getGuardianIncome(), font));
		
		personalDetailsTable.addCell(new Phrase("Date of Birth", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getDobIST(), font));
		personalDetailsTable.addCell(new Phrase("Category", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getCategoryDesc(), font));
		
		personalDetailsTable.addCell(new Phrase("Gender", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getGenderDesc(), font));
		personalDetailsTable.addCell(new Phrase("Religion", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getReligion(), font));
				
		personalDetailsTable.addCell(new Phrase("Primary Email", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getPrimaryEmailID(), font));
		personalDetailsTable.addCell(new Phrase("Nationality", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getNationality(), font));
		
		/**
		personalDetailsTable.addCell(new Phrase("Address", boldFont));
		
		try
		{
			String address = applicantData.getCorAddressL1()+",\n"+applicantData.getCorAddressL2();
			if(applicantData.getCorCountry().trim().equalsIgnoreCase("IN"))
			{
				address = address + ","+applicantData.getCorCity()+",\n"+applicantData.getCorDistrict()+", "+"Pincode : "+applicantData.getCorPincode()+",\n"+applicantData.getCorState();//+","+applicantData.getCorCountry();
			}
			else
			{
				//address = address + ",\n"+applicantData.getCorCountry();
			}
			PdfPCell addressCell = new PdfPCell(new Phrase(address, font));
			addressCell.setBorder(Rectangle.NO_BORDER);
			addressCell.setColspan(3);
			addressCell.setPadding(0);
			personalDetailsTable.addCell(addressCell);
			
		}
		catch (Exception e) 
		{
			logObj.error(" Address "+e);
		}**/
		
		//Address
		PdfPTable addressTable = new PdfPTable(new float[]{1.5F,2.8F,2F,1.4F});
		addressTable.setWidthPercentage(90);
		addressTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		addressTable.setSpacingBefore(5);
		addressTable.setSpacingAfter(5);
		addressTable.getDefaultCell().setPaddingTop(3);
		addressTable.getDefaultCell().setPaddingBottom(3);
		
		PdfPCell lineCell = new PdfPCell(new Phrase("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------", font));
		lineCell.setBorder(Rectangle.NO_BORDER);
		lineCell.setColspan(4);
		lineCell.setPadding(0);
		
		PdfPCell blankCell = new PdfPCell(new Phrase("", font));
		blankCell.setBorder(Rectangle.NO_BORDER);
		blankCell.setColspan(4);
		blankCell.setPadding(2);
		
		
		PdfPCell corAddrHeader = new PdfPCell(new Phrase("Correspondance Address", boldFont));
		corAddrHeader.setBorder(Rectangle.NO_BORDER);
		corAddrHeader.setColspan(4);
		corAddrHeader.setPadding(0);
		
		PdfPCell perAddrHeader = new PdfPCell(new Phrase("Permanent Address", boldFont));
		perAddrHeader.setBorder(Rectangle.NO_BORDER);
		perAddrHeader.setColspan(4);
		perAddrHeader.setPadding(0);
		
		PdfPCell corAddressL1 = new PdfPCell(new Phrase(applicantData.getCorAddressL1(), font));
		corAddressL1.setBorder(Rectangle.NO_BORDER);
		corAddressL1.setColspan(3);
		corAddressL1.setPadding(0);
		
		PdfPCell corAddressL2 = new PdfPCell(new Phrase(applicantData.getCorAddressL2(), font));
		corAddressL2.setBorder(Rectangle.NO_BORDER);
		corAddressL2.setColspan(3);
		corAddressL2.setPadding(0);
		
		addressTable.addCell(corAddrHeader);
		addressTable.addCell(lineCell);
		addressTable.addCell(new Phrase("Address", boldFont));
		addressTable.addCell(corAddressL1);
		addressTable.addCell(new Phrase("", boldFont));
		addressTable.addCell(corAddressL2);
		if(applicantData.getCorCountry().trim().equalsIgnoreCase("IN"))
		{
			addressTable.addCell(new Phrase("City", boldFont));
			addressTable.addCell(new Phrase(applicantData.getCorCity(),font));
			addressTable.addCell(new Phrase("District", boldFont));
			addressTable.addCell(new Phrase(applicantData.getCorDistrict(),font));
			addressTable.addCell(new Phrase("State", boldFont));
			addressTable.addCell(new Phrase(applicantData.getCorState(),font));
			addressTable.addCell(new Phrase("Pincode", boldFont));
			addressTable.addCell(new Phrase(applicantData.getCorPincode(),font));
			
		}
		
		PdfPCell perAddressL1 = new PdfPCell(new Phrase(applicantData.getPerAddressL1(), font));
		perAddressL1.setBorder(Rectangle.NO_BORDER);
		perAddressL1.setColspan(3);
		perAddressL1.setPadding(0);
		
		PdfPCell perAddressL2 = new PdfPCell(new Phrase(applicantData.getPerAddressL2(), font));
		perAddressL2.setBorder(Rectangle.NO_BORDER);
		perAddressL2.setColspan(3);
		perAddressL2.setPadding(0);
		
		addressTable.addCell(blankCell);
		
		addressTable.addCell(perAddrHeader);
		addressTable.addCell(lineCell);
		addressTable.addCell(new Phrase("Address", boldFont));
		addressTable.addCell(perAddressL1);
		addressTable.addCell(new Phrase("", boldFont));
		addressTable.addCell(perAddressL2);
		
		if(applicantData.getPerCountry().trim().equalsIgnoreCase("IN"))
		{
			addressTable.addCell(new Phrase("City", boldFont));
			addressTable.addCell(new Phrase(applicantData.getPerCity(),font));
			addressTable.addCell(new Phrase("District", boldFont));
			addressTable.addCell(new Phrase(applicantData.getPerDistrict(),font));
			addressTable.addCell(new Phrase("State", boldFont));
			addressTable.addCell(new Phrase(applicantData.getPerState(),font));
			addressTable.addCell(new Phrase("Pincode", boldFont));
			addressTable.addCell(new Phrase(applicantData.getPerPincode(),font));
		}
		
		
		
		personalDetailsTable.addCell(new Phrase("Contact Number", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getHomePhone(), font));
		personalDetailsTable.addCell(new Phrase("Physically Handicapped", boldFont));
		String pwd = applicantData.getPwd().trim().equalsIgnoreCase("Y")?"YES":"NO";
		personalDetailsTable.addCell(new Phrase(pwd, font));
		
		personalDetailsTable.addCell(new Phrase("Hostel Required ? ", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getApplicationForm().getProgramWiseDetails().get(0).getHostelRequired(), font));
		personalDetailsTable.addCell(new Phrase("Ever Expelled ?", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getApplicationForm().getProgramWiseDetails().get(0).getEverExpelled(), font));
		
		personalDetailsTable.addCell(new Phrase("EWS", boldFont));
		personalDetailsTable.addCell(new Phrase(applicantData.getCategory().trim().equalsIgnoreCase("GN")?applicantData.getEws():"Not Applicable", font));
						
		if(applicantData.getApplicationForm().getProgramWiseDetails().get(0).getDeiStudent() != null && applicantData.getApplicationForm().getProgramWiseDetails().get(0).getDeiStudent().trim().equalsIgnoreCase("Y"))
		{
			personalDetailsTable.addCell(new Phrase("Last qual. from D.E.I.", boldFont));
			personalDetailsTable.addCell(new Phrase(applicantData.getApplicationForm().getProgramWiseDetails().get(0).getDeiStudent(), font));
		}
		else
		{
			personalDetailsTable.addCell(new Phrase("", boldFont));
			personalDetailsTable.addCell(new Phrase("", font));
			
			PdfPCell lastQualicationCell = new PdfPCell(new Phrase("Last degree/examination passed from school/college : "+applicantData.getApplicationForm().getProgramWiseDetails().get(0).getLastExamFrom(), font));
			lastQualicationCell.setBorder(Rectangle.NO_BORDER);
			lastQualicationCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			lastQualicationCell.setColspan(4);
			personalDetailsTable.addCell(lastQualicationCell);
			
		}
		System.out.println("Coming upto 4");

		PdfPTable examCenterTable = new PdfPTable(2);
		examCenterTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		examCenterTable.setSpacingBefore(5);
		examCenterTable.setSpacingAfter(5);
		
		PdfPTable entranceTestTable = new PdfPTable(2);
		entranceTestTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		entranceTestTable.setSpacingBefore(5);
		entranceTestTable.setSpacingAfter(5);
		entranceTestTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		
		System.out.println("Coming upto 6");
		if(applicantData.getApplicationForm().getPapersAvailability().trim().equalsIgnoreCase("Y"))
		{
		try{
		
			examCenterTable.addCell(new Phrase("Preferred Examination Center", boldFont));
			examCenterTable.addCell(new Phrase(applicantData.getExamCenter1Name().toUpperCase(), font));
			
			int noOfPrograms = applicantData.getApplicationForm().getProgramWiseDetails().size();
		for(int i=0; i< noOfPrograms;i++)
		{
			ProgramWiseDetail prgDetail = applicantData.getApplicationForm().getProgramWiseDetails().get(i);
			if(!(prgDetail.getPaperMainGroup()==null || prgDetail.getPaperMainGroup().trim().equalsIgnoreCase("")))
			{
				int paperGroups = prgDetail.getProgramPaper().getGroupPapers().size();
				entranceTestTable.addCell(new Phrase("For "+prgDetail.getProgramName(), boldFont));
				//entranceTestTable.addCell(new Phrase("Subject Group : "+prgDetail.getProgramPaper().getMainGroupName(), boldFont));
				entranceTestTable.addCell(new Phrase("", boldFont));
				for(int j = 0; j<paperGroups; j++)
				{
					PdfPCell groupPaperCell = new PdfPCell(new Phrase("Roll Number", boldFont));
					groupPaperCell.setBorder(Rectangle.NO_BORDER);
					groupPaperCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					PdfPTable paperTable = new PdfPTable(1);
					paperTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
					int columnIndex = (j+1)%2;
					if(columnIndex==1 && (j == (paperGroups-1)))
					{
						groupPaperCell.setColspan(2);
					}
					GroupPaper groupPaper = prgDetail.getProgramPaper().getGroupPapers().get(j);
					if(groupPaper.getAllPaperSize() == groupPaper.getMaxChoice())
					{
						paperTable.addCell(new Phrase("Compulsory Papers", new Font(baseBoldFont, 8)));
					}
					else
					{
						paperTable.addCell(new Phrase("Papers Opted", new Font(baseBoldFont, 8)));
					}
					
					for(int k=0; k < groupPaper.getSelectedPapers().size(); k++)
					{
						paperTable.addCell(new Phrase((k+1)+". "+groupPaper.getSelectedPapers().get(k).getDescription().toUpperCase(), new Font(bf, 8)));
					}
					groupPaperCell.addElement(paperTable);
					entranceTestTable.addCell(groupPaperCell);
				}
			}
			else
			{
				//Do Nothing
			}
		}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			logObj.error(" "+e);
		}
		}//If of Programs Availability Checking Ends Here
		
		PdfPTable compTable = new PdfPTable(6);
		compTable.setWidthPercentage(100);
		compTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		compTable.setSpacingAfter(5);
		compTable.setSpacingBefore(5);
		
		
		
		if(phase2Edited)
		{
			
			int groupWiseComponents = applicantData.getApplicationForm().getGroupWiseComponents().size();
			for(int i = 0 ; i < groupWiseComponents ; i++)
			{
				GroupWiseComponent grpWComponent = applicantData.getApplicationForm().getGroupWiseComponents().get(i);
				
				PdfPCell sectionHeaderCell = new PdfPCell(new Phrase(grpWComponent.getDescription(), boldHeaderFont));
				sectionHeaderCell.setBorder(Rectangle.NO_BORDER);
				sectionHeaderCell.setColspan(6);
				sectionHeaderCell.setPaddingTop(5);
				sectionHeaderCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				compTable.addCell(sectionHeaderCell);
				for(int j = 0; j < grpWComponent.getComponents().size(); j++)
				{
					Component component = grpWComponent.getComponents().get(j);
					if(j==0)
					{
						compTable.addCell(new Phrase("", font));
						compTable.addCell(new Phrase("OBTAINED MARKS", boldFont));
						compTable.addCell(new Phrase("TOTAL MARKS", boldFont));
						compTable.addCell(new Phrase("%", boldFont));
						
						if(component.getBoardFlag().trim().equalsIgnoreCase("Y"))
						{
							compTable.addCell(new Phrase("BOARD NAME", boldFont));
						}
						else if(grpWComponent.getUniversityToAsk()==1)
						{
							compTable.addCell(new Phrase("UNIVERSITY NAME", boldFont));
						}
						else
						{
							if(grpWComponent.getCode().trim().equalsIgnoreCase("7"))
							{
								compTable.addCell(new Phrase("RANKING", boldFont));
							}
							else
							{
								compTable.addCell(new Phrase("", boldFont));
							}
							
						}
						
						//Added by Arjun on 14-05-2019
						if(grpWComponent.getCode().trim().equalsIgnoreCase("7"))
						{
							compTable.addCell(new Phrase("PERCENTILE", boldFont));
						}
						else
						{
							compTable.addCell(new Phrase("YEAR OF PASSING", boldFont));
						}
						
						//compTable.addCell(new Phrase("YEAR OF PASSING", boldFont)); Commented by Arjun on 14-05-2019
					}
					
					//Added by Arjun on 14-05-2019
					if(grpWComponent.getCode().trim().equalsIgnoreCase("7"))
					{
						if(grpWComponent.getShowOptions()==0)
						{
							compTable.addCell(new Phrase(component.getComponentName(), font));
						}
						else
						{
							//Degree Name may be displayed here.
							compTable.addCell(new Phrase(component.getComponentName(), font));
						}
						
						compTable.addCell(new Phrase(component.getObtainedMarks(), font));
						compTable.addCell(new Phrase(component.getTotalMarks(), font));
						compTable.addCell(new Phrase(component.getPercentage(), font));
						
						if(component.getBoardFlag().trim().equalsIgnoreCase("Y"))
						{
							compTable.addCell(new Phrase(component.getBoardName(), font));
						}
						else if(grpWComponent.getUniversityToAsk()==1)
						{
							compTable.addCell(new Phrase(component.getOtherBoardName(), font));
						}
						else
						{
							compTable.addCell(new Phrase(component.getRanking(), font));
						}
						
						if(component.getPassingYear().trim().equalsIgnoreCase("Y"))
						{
							compTable.addCell(new Phrase(component.getPassingYearValue(), font));
						}
						else
						{
							compTable.addCell(new Phrase(component.getPercentile(), font));
						}
					
						
					}
					else
					{
						if(grpWComponent.getShowOptions()==0)
						{
							compTable.addCell(new Phrase(component.getComponentName(), font));
						}
						else
						{
							//Degree Name may be displayed here.
							compTable.addCell(new Phrase(component.getComponentName(), font));
						}
						
						compTable.addCell(new Phrase(component.getObtainedMarks(), font));
						compTable.addCell(new Phrase(component.getTotalMarks(), font));
						compTable.addCell(new Phrase(component.getPercentage(), font));
						
						if(component.getBoardFlag().trim().equalsIgnoreCase("Y"))
						{
							compTable.addCell(new Phrase(component.getBoardName(), font));
						}
						else if(grpWComponent.getUniversityToAsk()==1)
						{
							compTable.addCell(new Phrase(component.getOtherBoardName(), font));
						}
						else
						{
							compTable.addCell(new Phrase("", font));
						}
						
						if(component.getPassingYear().trim().equalsIgnoreCase("Y"))
						{
							compTable.addCell(new Phrase(component.getPassingYearValue(), font));
						}
						else
						{
							compTable.addCell(new Phrase("", font));
						}
					
					}
					
					/**
					if(grpWComponent.getShowOptions()==0)
					{
						compTable.addCell(new Phrase(component.getComponentName(), font));
					}
					else
					{
						//Degree Name may be displayed here.
						compTable.addCell(new Phrase(component.getComponentName(), font));
					}
					
					compTable.addCell(new Phrase(component.getObtainedMarks(), font));
					compTable.addCell(new Phrase(component.getTotalMarks(), font));
					compTable.addCell(new Phrase(component.getPercentage(), font));
					
					if(component.getBoardFlag().trim().equalsIgnoreCase("Y"))
					{
						compTable.addCell(new Phrase(component.getBoardName(), font));
					}
					else if(grpWComponent.getUniversityToAsk()==1)
					{
						compTable.addCell(new Phrase(component.getOtherBoardName(), font));
					}
					else
					{
						compTable.addCell(new Phrase("", font));
					}
					
					if(component.getPassingYear().trim().equalsIgnoreCase("Y"))
					{
						compTable.addCell(new Phrase(component.getPassingYearValue(), font));
					}
					else
					{
						compTable.addCell(new Phrase("", font));
					}**/
					
				
				}
				
				for(int j = 0; j < grpWComponent.getGateComponents().size(); j++)
				{
					Component component = grpWComponent.getGateComponents().get(j);
					if(j==0)
					{
						compTable.addCell(new Phrase("GATE RANK", boldFont));
						compTable.addCell(new Phrase("SCORE", boldFont));
						compTable.addCell(new Phrase("TOTAL APPLICANTS", boldFont));
						compTable.addCell(new Phrase("GATE BRANCH", boldFont));
						compTable.addCell(new Phrase("GATE YEAR", boldFont));
					}
					
					compTable.addCell(new Phrase(component.getRanking(), font));
					compTable.addCell(new Phrase(component.getScore(), font));
					compTable.addCell(new Phrase(component.getTotalApplicants(), font));
					compTable.addCell(new Phrase(component.getGateBranch(), font));
					compTable.addCell(new Phrase(component.getGateYear(), font));
					
				
				}
			}
			
			
		}
		
		PdfPTable applicationNumberTable = new PdfPTable(new float[]{.25F, .15F, .2F, .25F});
		applicationNumberTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		applicationNumberTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		applicationNumberTable.setSpacingBefore(5);
		applicationNumberTable.setSpacingAfter(5);
		applicationNumberTable.addCell(new Phrase("Application Number", boldHeaderFont));
		applicationNumberTable.addCell(new Phrase(applicationNumber, font));
		applicationNumberTable.addCell(new Phrase("Application Fees", boldHeaderFont));
		applicationNumberTable.addCell(new Phrase(applicantData.getApplicationFee()+" INR", font));
		
		
		PdfPTable researchDetails = new PdfPTable(new float[]{30F, 20F, 25F, 25F});
		researchDetails.setWidthPercentage(90);
		if(researchPrograms.size()>0)
		{
			researchDetails.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			researchDetails.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			researchDetails.setSpacingBefore(5);
			researchDetails.setSpacingAfter(5);
			researchDetails.addCell(new Phrase("Published Papers", boldFont));
			researchDetails.addCell(new Phrase(applicantData.getPublishedPapers(), font));
			researchDetails.addCell(new Phrase("Journal Description", boldFont));
			researchDetails.addCell(new Phrase(applicantData.getJournalDesc(), font));
			researchDetails.addCell(new Phrase("Conferences", boldFont));
			researchDetails.addCell(new Phrase(applicantData.getConferences(), font));
			researchDetails.addCell(new Phrase("Conferences Description", boldFont));
			researchDetails.addCell(new Phrase(applicantData.getConferencesDesc(), font));
			researchDetails.addCell(new Phrase("Fellowship", boldFont));
			researchDetails.addCell(new Phrase(getFlagDesc(applicantData.getFellowship()), font));
			researchDetails.addCell(new Phrase("Fellowship Description", boldFont));
			researchDetails.addCell(new Phrase(applicantData.getFellowshipDesc(), font));
			researchDetails.addCell(new Phrase("RET Qualified", boldFont));
			researchDetails.addCell(new Phrase(getFlagDesc(applicantData.getRetQualified()), font));
			if(applicantData.getRetQualified() != null && applicantData.getRetQualified().trim().equalsIgnoreCase("Y"))
			{
				researchDetails.addCell(new Phrase("RET Qualification Year", boldFont));
				researchDetails.addCell(new Phrase(applicantData.getRetYear(), font));
				researchDetails.addCell(new Phrase("RET Roll Number", boldFont));
				researchDetails.addCell(new Phrase(applicantData.getRetRollNumber(), font));
			}
			researchDetails.addCell(new Phrase("RET Remarks", boldFont));
			researchDetails.addCell(new Phrase(applicantData.getRetRemarks(), font));
			researchDetails.addCell(new Phrase("Institute Teacher", boldFont));
			researchDetails.addCell(new Phrase(getFlagDesc(applicantData.getInstituteTeacher()), font));
			researchDetails.addCell(new Phrase("JRF Qualified", boldFont));
			researchDetails.addCell(new Phrase(getFlagDesc(applicantData.getJrfQualified()), font));
			researchDetails.addCell(new Phrase("Recipient of Fellowship", boldFont));
			researchDetails.addCell(new Phrase(getFlagDesc(applicantData.getDeiScholor()), font));
			researchDetails.addCell(new Phrase("DEI M.Phil. Scholor", boldFont));
			researchDetails.addCell(new Phrase(getFlagDesc(applicantData.getDeiMphil()), font));
			researchDetails.addCell(new Phrase("Recipient Type", boldFont));
			researchDetails.addCell(new Phrase(applicantData.getRecipientType(), font));
			researchDetails.addCell(new Phrase("", boldFont));
			researchDetails.addCell(new Phrase("", font));
			researchDetails.addCell(new Phrase("DEI Director Medal Winner", boldFont));
			researchDetails.addCell(new Phrase(getFlagDesc(applicantData.getDeiMedalWinner()), font));
			researchDetails.addCell(new Phrase("DEI Post Graduate", boldFont));
			researchDetails.addCell(new Phrase(getFlagDesc(applicantData.getDeiPG()), font));
			researchDetails.addCell(new Phrase("CGPA >= 9 in Post Graduation", boldFont));
			researchDetails.addCell(new Phrase(getFlagDesc(applicantData.getCgpa9()), font));
			researchDetails.addCell(new Phrase("", boldHeaderFont));
			researchDetails.addCell(new Phrase("", boldHeaderFont));
		}
		
			
		System.out.println("coming upto 11");
		document.open();
		document.add(universityLogo);
		document.add(headerLine1);
		document.add(headerLine2);
		document.add(applicantPhoto);
		document.add(applicationNumberTable);
		document.add(new Paragraph("Program(s) Applied for", boldHeaderFont));
		document.add(programTable);
		document.add(new Paragraph("Personal Information", boldHeaderFont));
		document.add(personalDetailsTable);
		document.add(new Paragraph("Address Details", boldHeaderFont));
		document.add(addressTable);
		if(applicantData.getApplicationForm().getPapersAvailability().trim().equalsIgnoreCase("Y"))
		{
			document.add(new Paragraph("Selected Examination Center", boldHeaderFont));
			document.add(examCenterTable);
			document.add(new Paragraph("Selected Test Papers", boldHeaderFont));
			document.add(entranceTestTable);
			
		}
		
		document.add(compTable);
		if(researchPrograms.size()>0)
		{
			document.add(new Paragraph("Details For Research Programs", boldHeaderFont));
			document.add(researchDetails);
		}
		if(applicantData.getApplicationForm().getBranchAvailability().trim().equalsIgnoreCase("Y"))
		{
			document.add(new Paragraph("Preferred Branches Options", boldHeaderFont));
			document.add(branchTable);
		}
		
		document.add(new Paragraph("Disclaimer", boldHeaderFont));
		document.add(new Paragraph("1. I declare that the information given in this application form is complete, true and correct.",disclaimerFont));
		document.add(new Paragraph("2. I understand that providing false or misleading information or non-disclosure of relevant information may result in cancellation of my admission.",disclaimerFont));
		document.add(new Paragraph("3. I also acknowledge that my admission can be cancelled at any time, with or without cause, and with or without prior notice at the option of the Institute.",disclaimerFont));
//		document.add(declarationParagraph);
		
		document.add(applicantSignature);
		
		Paragraph finalNotice1 = new Paragraph("Dayalbagh Educational Institute is a ragging-free, tobacco-free and smoking-free campus.\n" +
				"Applicants and students are not permitted to carry mobile phones on campus.",boldHeaderFont);
		Paragraph finalNotice2 = new Paragraph("You must wear Helmet while driving Two Wheelers on campus.",boldHeaderFont);
		Paragraph finalNotice3 = new Paragraph("It is mandatory to update marks of qualifying exams (HS/IN/UG) immediately after the results declaration otherwise the application is likely to be rejected.",boldHeaderFont);
		finalNotice1.setAlignment(Element.ALIGN_CENTER);
		finalNotice2.setAlignment(Element.ALIGN_CENTER);
		finalNotice3.setAlignment(Element.ALIGN_CENTER);
		document.add(finalNotice1);
		document.add(finalNotice2);
		document.add(finalNotice3);
//		document.add(noticeParagraph);
				
		
		
		pdfGenerated = "Y";
	
		}
		catch(Exception ex)
		{
			logObj.error(" "+ex);
			document.close();
			String error=ex.getMessage()+"\n";
			System.out.println(ex);
			CM_StudentInfoGetter studentInfo = new CM_StudentInfoGetter();
			studentInfo.setForm_number(applicationNumber);
			studentInfo.setFile_name(ex.getMessage());
			error=error+commonDAO.generateErrorLogs(studentInfo);
            System.out.println("returning error is "+error);
		}
		finally
		{	 
			document.close();
		}

//		return null;
		}catch (Exception e) {
			logObj.error(e);
		}
			
		return applicantDocumentPath;
	
	
	}
	
		
	public String getFlagDesc(String input)
	{
		return input!=null?input.trim().equalsIgnoreCase("Y")?"YES":"NO":"NO";
	}
	
	public ModelAndView generateApplicationPdf(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session = request.getSession(false);
		if(session == null)
		{
			return new ModelAndView("errorPages/SessionExpired","message","Your Session has been expired.");
		}
		
		String documentPath = updateApplicationPDF(request, response);
		
		
		Applicant sessionApplicant = (Applicant) session.getAttribute("applicant");
		String filename = sessionApplicant.getApplicationNumber()+".pdf";
		String filepath=documentPath+File.separator+filename;
		
		File file = new File(filepath);
		try
		{
			if(!file.exists())
				try 
				{
					throw new FileNotFoundException(file.getAbsolutePath());
				} 
				catch(FileNotFoundException e) 
				{
					logObj.error(" "+e);
					e.printStackTrace();
				}

				response.setHeader("Content-Type", "application/pdf");
				response.setHeader("Content-Length", String.valueOf(file.length()));
				response.setHeader("Content-disposition", "attachment;filename=\"" + filename + "\"");

				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
				BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
				byte[] buf = new byte[1024];
				while (true) 
				{
					int length = bis.read(buf);
					if(length == -1)
				        break;

				         bos.write(buf, 0, length);
				}
				bos.flush();
				bos.close();
				bis.close();

		}
		catch(Exception downloadException)
		{
			logObj.error("Exception while downloading " + downloadException);
			String error = downloadException.getMessage()+"\n";
			CM_StudentInfoGetter studentInfo = new CM_StudentInfoGetter();
			studentInfo.setForm_number(sessionApplicant.getApplicationNumber());
			studentInfo.setFile_name(downloadException.getMessage());
			error = error+commonDAO.generateErrorLogs(studentInfo);
		}
		
		
		return null;
	}
	 
	
}
