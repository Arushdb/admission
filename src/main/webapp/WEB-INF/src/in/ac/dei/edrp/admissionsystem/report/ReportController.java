package in.ac.dei.edrp.admissionsystem.report;

import in.ac.dei.edrp.admissionsystem.common.CommonBean;
import in.ac.dei.edrp.admissionsystem.common.beans.Applicant;
import in.ac.dei.edrp.admissionsystem.upload.CM_StudentInfoGetter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.Label;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import java.util.zip.*;
import java.io.*; 

public class ReportController extends MultiActionController
{
	private ReportDao reportDao;

	public void setReportDao(ReportDao reportDao)
	{
		this.reportDao = reportDao;
	}

	public ModelAndView getPrograms(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		List<ReportBean> programs = reportDao.getPrograms();
		for (int i=0;i<programs.size();i++){
			System.out.println("Hello Dharna in getPrograms"+programs.get(i).getProgramName());
		}
		ModelAndView model = new ModelAndView("reports/InterviewReport");
		model.addObject("programList", programs);
		/*model.addObject("beforeCount", request.getParameter("beforeCount"));
		model.addObject("afterCount", request.getParameter("afterCount"));*/

		return model;

	}

	public ModelAndView generateInterviewReport(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String progID=request.getParameter("program");
		List<ReportBean> interviewList = reportDao.getInterviewlist(progID);
		String progname = reportDao.getProgramName(progID);
		String parentDirectoryDnld = "";
		String parentDirectoryDnld1 = "";
		String message = "";
		int afterCount=0;
		int beforeCount=0;
		boolean exlStatus=false;
		boolean pdfStatus=false;

		ReportBean report=new ReportBean();
		List<ReportBean> session1=reportDao.getSession(); 
		String session=session1.get(0).getSession().toString().substring(0, 4).concat("-")+session1.get(0).getSession().toString().substring(8,12);
		System.out.println("session1 before file path"+session1.get(0).getSession().toString().substring(0, 4)+" "+session1.get(0).getSession().toString().substring(8,12)+"session before file path"+session);

		beforeCount = generateExl(progID,progname,interviewList,session);
		afterCount = generateExl(progID,progname,interviewList,session);
		parentDirectoryDnld1 = generatePdf(progID,progname,interviewList,session);
		if(beforeCount==afterCount & beforeCount!=0){
			report.setExlStatus("true");
			report.setPdfStatus("true");

			System.out.println("exlStatus1"+report.getExlStatus());
			System.out.println("parentDirectoryDnld of xls"+parentDirectoryDnld.toString());

			System.out.println("pdf status1"+report.getPdfStatus());
			System.out.println("parentDirectoryDnld1 of pdf"+parentDirectoryDnld1.toString());


			System.out.println("pdf status"+report.getPdfStatus()+"exl status"+report.getExlStatus());
			String sep = System.getProperty("file.separator");
			String fileName = progname+"InterviewList.xls";
			String filePath = "REPORTS"+sep+"Interview List"+sep+session+sep;
			String parentDirectory = this.getServletContext().getRealPath("/")+filePath;
			String xlsAddress = parentDirectory+fileName;
			String fileName1 = progname+"InterviewList.pdf";
			String filePath1 = "REPORTS"+sep+"Interview List"+sep+session+sep;
			String parentDirectory1 = this.getServletContext().getRealPath("/")+filePath1;
			String pdfAddress = parentDirectory1+fileName1;
			String fileName2 = progname+"InterviewList.zip";
			String filePath2 = "REPORTS"+sep+"Interview List"+sep+session+sep;
			String parentDirectory2 = this.getServletContext().getRealPath("/")+filePath2;
			String zipAddress= parentDirectory2+fileName2;


			List<String> files = new ArrayList<String>();
			files.add(xlsAddress);
			files.add(pdfAddress);
			zipFiles(files,zipAddress);

			createZIP(zipAddress,progname);
			String status="error";
			String messsgae="";
			request.setAttribute("beforeCount", beforeCount);
			request.setAttribute("afterCount", afterCount);
			request.setAttribute("zipAddress", zipAddress);
			request.setAttribute("progID", progID);
			request.setAttribute("session", session);
			/*model.addObject("beforeCount", beforeCount);
			model.addObject("afterCount", afterCount);*/
			//dnlZipFile(zipAddress,progID,response);
		}


		try{
			if(report.getPdfStatus().equalsIgnoreCase("true") && report.getExlStatus().equalsIgnoreCase("true")){
				System.out.println("in exlStatus && pdfStatus");
				report.setProgramId(progID);
				report.setAfterCount(afterCount);
				report.setBeforeCount(beforeCount);
				reportDao.insertInterviewLog(report);
				reportDao.updateStatus(report);
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex+"The report has already been generated");
			request.setAttribute("exception","The report has already been generated");
		}
		return getPrograms(request,response);
		//return null;

	}
	public int generateExl(String progID,String progname,List<ReportBean> interviewList,String session){

		String sep = System.getProperty("file.separator");
		String fileName = progname+"InterviewList.xls";
		String filePath = "REPORTS"+sep+"Interview List"+sep+session+sep;
		System.out.println("file path:"+filePath);
		String parentDirectory = this.getServletContext().getRealPath("/")+filePath;
		String parentDirectoryDnld = parentDirectory+fileName;
		File parentDir = new File(parentDirectory);
		File exlFile = new File(parentDirectory+fileName);
		WritableWorkbook writableWorkbook = null;

		WritableFont headerFont = new WritableFont(WritableFont.TAHOMA, 10, WritableFont.BOLD);  
		WritableFont contentFont = new WritableFont(WritableFont.TAHOMA, 10, WritableFont.NO_BOLD);

		WritableCellFormat headerFormat = new WritableCellFormat(headerFont);
		WritableCellFormat contentFormat = new WritableCellFormat(contentFont);

		boolean fileGenerated = false;
		String message = "";
		int afterCount=0;
		int beforeCount=0;


		try
		{
			if(!parentDir.exists())
			{
				parentDir.mkdirs();
			}

			writableWorkbook = Workbook.createWorkbook(exlFile);
			WritableSheet writableSheet = writableWorkbook.createSheet("Interview List", 0);

			Label headingLbl=new Label(1,1,"Interview List of "+progname,headerFormat);
			Label pNameLbl = new Label(0, 4, "S.No.", headerFormat);
			Label appNumLbl = new Label(1, 4, "APPLICATION NUMBER", headerFormat);
			Label cateLbl = new Label(2, 4, "Category", headerFormat);
			Label genderLbl = new Label(3, 4, "Gender", headerFormat);
			Label nameLbl = new Label(4, 4, "NAME", headerFormat);
			Label fNameLbl = new Label(5, 4, "Father Name", headerFormat);


			writableSheet.addCell(headingLbl);
			writableSheet.addCell(pNameLbl);
			writableSheet.addCell(appNumLbl);
			writableSheet.addCell(cateLbl);
			writableSheet.addCell(genderLbl);
			writableSheet.addCell(nameLbl);
			writableSheet.addCell(fNameLbl);

			List<ReportBean> checkList = new ArrayList<ReportBean>();
			interviewList = reportDao.getInterviewlist(progID);

			beforeCount=interviewList.size();

			if(interviewList.size()>0)
			{
				for(int i = 0 , j = 5; i < interviewList.size(); i++, j++)
				{
					ReportBean student = interviewList.get(i);
					Label SNo = new Label(0, j, (i+1)+"",contentFormat);
					Label appNum = new Label(1, j, student.getApplicationNumber(),contentFormat);
					Label cate = new Label(2, j, student.getCategory(),contentFormat);
					Label gender = new Label(3, j, student.getGender(),contentFormat);
					Label name = new Label(4, j, student.getName(),contentFormat);
					Label fName = new Label(5, j, student.getFatherName(),contentFormat);

					writableSheet.addCell(SNo);
					writableSheet.addCell(appNum);
					writableSheet.addCell(cate);
					writableSheet.addCell(gender);
					writableSheet.addCell(name);
					writableSheet.addCell(fName);
					afterCount=j;
				}
				writableWorkbook.write();
				fileGenerated = true;
				if(fileGenerated){

				}

			}
			else
			{
				message = "There are no records for Interview List"; 
			}

		}
		catch(Exception ex)
		{
			System.out.println(ex+"");
			parentDirectoryDnld=null;
		}
		finally
		{
			try
			{
				writableWorkbook.close();

			}
			catch(Exception finalyException)
			{
				System.out.println(finalyException+"");
			}
		}

		System.out.println("return value of xls:"+parentDirectoryDnld);
		//return parentDirectoryDnld;
		return beforeCount & afterCount;

	}

	public String generatePdf(String progID,String progname,List<ReportBean> interviewList, String session){
		String fileName1 = "";
		String sep = System.getProperty("file.separator");
		fileName1 = progname+"InterviewList.pdf";

		String parentDirectory1 ="";
		String parentDirectoryDnld = "";

		boolean fileGenerated = false;

		try
		{
			//for portrait view of pdf
			Document document = new Document();
			//for landscape view of pdf
			//Document document = new Document(PageSize.A4.rotate(),50,30,20,10);

			Font headingFont = new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD,BaseFont.WINANSI,BaseFont.EMBEDDED ), 10, Font.BOLD);
			Font headingFont1 = new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD,BaseFont.WINANSI,BaseFont.EMBEDDED ), 10);

			String filePath1 = "REPORTS"+sep+"Interview List"+sep+session+sep;
			System.out.println("file path 1:"+filePath1);
			parentDirectory1 = this.getServletContext().getRealPath("/")+filePath1;
			System.out.println("parentDirectory1 in generatePdf:"+parentDirectory1);
			parentDirectoryDnld = parentDirectory1;
			System.out.println("parentDirectoryDnld:"+parentDirectoryDnld);

			File parentDir = new File(parentDirectory1);
			if(!parentDir.exists())
			{
				parentDir.mkdirs();
			}

			PdfWriter.getInstance(document, new FileOutputStream(parentDirectory1+fileName1));
			System.out.println("after parentDirectoryDnld:");
			document.setMarginMirroring(false);



			/*PdfPTable contentTable = new PdfPTable(12);

			contentTable.setWidthPercentage(100);
			contentTable.getDefaultCell().setPadding(8); //margins around the whole grid
            //if, grid.setPadding(new Insets(10, 10, 10, 10));
			//(top/right/bottom/left)
			contentTable.getRows();*/

			System.out.println("interviewList.size();"+interviewList.size());

			Chunk headerChunk1=new Chunk("Dayalbagh Educational Institute");
			Chunk headerChunk2=new Chunk("Admissions "+session);
			Chunk headerChunk3=new Chunk("Notice");

			Chunk chunk1=new Chunk("List of the candidates qualified for interview for "+ progname+ " is given below. A CANDIDATE HAS TO APPEAR ONLY ONCE FOR AN INTERVIEW. Details of interview schedule and conditions for consideration of interview marks for other courses are already notified on the DEI Website.");
			Chunk chunk2=new Chunk("PROGRAM NAME:"+progname);
			Chunk chunk3=new Chunk("\n");
			Chunk chunk4=new Chunk("\n Note:");
			Chunk chunk5=new Chunk("Candidates will be required to produce their original certificates along with a set of self-attested photocopies at the time of the interview, failing which their candidature will be rejected forthwith. The set of photocopies will be retained for Institute record. " +
					"A candidate eligible for interview in more than one course will appear for interview only once and the marks he/she scores in that interview, will be considered while preparing the merit lists of all courses for which he/she is eligible."+
			"Therefore, the candidate MUST appear in the interview for the course whose interview is scheduled at the earliest date failing which the claim of the candidate for that course will not be considered.");

			Paragraph headerPara1=new Paragraph(headerChunk1);
			Paragraph headerPara3=new Paragraph(headerChunk2);
			Paragraph headerPara4=new Paragraph(headerChunk3);
			Paragraph para1=new Paragraph(chunk1);
			Paragraph paraNewLine=new Paragraph(chunk3);
			Paragraph para2=new Paragraph(chunk2);
			Paragraph para3=new Paragraph(chunk3);
			Paragraph para4=new Paragraph(chunk4);
			Paragraph para5=new Paragraph(chunk5);
			Paragraph headerPara=new Paragraph();
			headerPara.add(headerPara1);
			headerPara.add(headerPara3);
			headerPara.add(headerPara4);
			headerPara.setAlignment(Element.ALIGN_CENTER);
			headerPara.setFont(headingFont);


			System.out.println("interviewsize:"+interviewList.size());

			int staticValue=30;
			int hi=0;
			int hi1=0;
			int tableSize=0;
			int lastTable=0;
			int var=0;

			if(interviewList.size()>staticValue)
			{
				hi=staticValue-(interviewList.size()%staticValue);
				tableSize=(int)(interviewList.size()/staticValue);
				lastTable=tableSize;
				if((interviewList.size()%staticValue)>0){
					lastTable=tableSize+1;
					var=lastTable;
					System.out.println("in mod if tableSize:"+tableSize+"in mod if lastTable:"+lastTable);
				}

				hi1=staticValue-hi;
				if(tableSize>6){
					tableSize=6;
				}
			}
			else
			{
				hi=staticValue-interviewList.size();
				tableSize=1;
				hi1=staticValue-hi;
			}

			int leftCoulmns=0;
			for(int i=1; leftCoulmns<(interviewList.size()/staticValue);i++){
				leftCoulmns=6-(var-6*i);
				if(leftCoulmns < 6 && leftCoulmns>0)
				{
					break;
				}

			}
			System.out.println("var : "+var);
			System.out.println("Left Columns : "+leftCoulmns);
			PdfPTable contentTable = new PdfPTable(tableSize);
			System.out.println(" tableSize"+tableSize);
			System.out.println(" (interviewList.size()+hi)"+(interviewList.size()+hi));

			PdfPCell cell;
			PdfPTable table;
			int m=0;
			for (int i = 0; i < (interviewList.size()+hi); i+=staticValue) {
				m=m+1;
				/*if(m>1){
	        		   //define tableSize
	        		   tableSize=(interviewList.size()-i)/staticValue;
	        		   if(((interviewList.size()-i)%staticValue)>0){
	        			   tableSize=tableSize+1;
	        			   System.out.println("in inner if m>1:"+tableSize);
	        		   }
	        		   System.out.println("outside inner if m>1:"+tableSize);
	        	   }*/
				System.out.println("m:"+m);
				if((tableSize == 6 & m == tableSize ) || tableSize == 1 || (lastTable==m))
				{
					System.out.println("in or if"+m + tableSize + "lastTable:"+lastTable);
					for(int l=i+1; l<(i+1+hi1); l++)
					{
						cell = new PdfPCell(new Phrase(interviewList.get(l-1).getApplicationNumber().toString()));
						System.out.println("in l loop"+l+" "+interviewList.get(l-1).getApplicationNumber().toString());
					}
					System.out.println("(i+hi1+2):"+(i+hi1+2)+"((i+hi1+2)+hi:"+((i+hi1+2)+hi));
					for(int l=(i+hi1+1); l<((i+hi1+2)+hi); l++)
					{
						cell = new PdfPCell(new Phrase("")); 
						cell.setBorder(Rectangle.NO_BORDER);
					}
				}

				cell = new PdfPCell(new Phrase(interviewList.get(i).getApplicationNumber().toString()));
				System.out.println("in 1st for"+i+" "+interviewList.get(i).getApplicationNumber().toString());
				table = new PdfPTable(1);
				table.addCell(cell);
				for(int j=1;j<=staticValue-1;j++){
					if (i+1 <= interviewList.size() -j) {
						cell = new PdfPCell(new Phrase(interviewList.get(i+j).getApplicationNumber().toString()));
						System.out.println("in 1st if"+(i+j)+" "+interviewList.get(i+j).getApplicationNumber().toString());
						table.addCell(cell);
					}
					else {
						cell = new PdfPCell(new Phrase(""));
						cell.setBorder(Rectangle.NO_BORDER);
						table.addCell(cell);
					}

				}


				contentTable.addCell(table);
				// contentTable.addCell(cell);
			}

			if(tableSize>1){
				cell=new PdfPCell(new Phrase(""));
				cell.setColspan(leftCoulmns);
				contentTable.addCell(cell);
			}
			document.open();
			document.add(headerPara);
			document.add(para1);
			document.add(paraNewLine);
			document.add(para2);
			document.add(para3);
			document.add(contentTable);
			document.add(para4);
			document.add(para5);
			document.close();
			fileGenerated = true;

		}
		catch(Exception ex)
		{
			System.out.println(ex+"");
			parentDirectoryDnld=null;
			System.out.println("Exception while downloading "+ex.getMessage());
		}

		System.out.println("Hello Dharna in generateInterviewReport");
		System.out.println("return value of pdf:"+parentDirectoryDnld+fileName1);
		return parentDirectoryDnld+fileName1;
	}

	public void zipFiles(List<String> files,String zipAddress){

		FileOutputStream fos = null;
		ZipOutputStream zipOut = null;
		FileInputStream fis = null;
		try {
			fos = new FileOutputStream(zipAddress);
			zipOut = new ZipOutputStream(new BufferedOutputStream(fos));
			for(String filePath:files){
				File input = new File(filePath);
				fis = new FileInputStream(input);
				ZipEntry ze = new ZipEntry(input.getName());
				System.out.println("Zipping the file: "+input.getName());
				zipOut.putNextEntry(ze);
				byte[] tmp = new byte[4*1024];
				int size = 0;
				while((size = fis.read(tmp)) != -1){
					zipOut.write(tmp, 0, size);
				}
				zipOut.flush();
				fis.close();
			}
			zipOut.close();
			System.out.println("Done... Zipped the files...");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try{
				if(fos != null) fos.close();
			} catch(Exception ex){

			}
		}
	}

	public String createZIP(String documentsPath, String progname)
	{
		System.out.println("documentsPath:"+documentsPath+"progname:"+progname);
		String returnValue = "failure";
		try
		{
			String zipFile = progname+".zip";
			FileOutputStream fout = new FileOutputStream(zipFile);
			ZipOutputStream zout = new ZipOutputStream(fout);

			File sourceDir = new File(documentsPath);
			String[] sourceFiles = sourceDir.list();
			System.out.println(sourceFiles.length);

			byte[] buffer = new byte[1024];

			for(int i = 0; i < sourceFiles.length; i++)
			{
				FileInputStream fin = new FileInputStream(documentsPath+sourceFiles[i]);
				zout.putNextEntry(new ZipEntry(sourceFiles[i]));

				int length;
				while((length=fin.read((buffer)))>0)
				{
					zout.write(buffer, 0, length);
				}


				zout.closeEntry();
				fin.close();

				File individualFile = new File(documentsPath+sourceFiles[i]);
				individualFile.delete();

			}

			zout.close();
			returnValue = "success";
		}
		catch(Exception ex)
		{
			System.out.println("IOException : "+ex);
		}
		return returnValue;
	}

	public void dnlZipFile(String fullPath, String programId, HttpServletResponse response,HttpServletRequest request,String session){
		try
		{	
			System.out.println("programId in dnlZipFile"+programId);
			String progname = reportDao.getProgramName(programId);
			System.out.println("progname in dnlZipFile"+progname);
			String parentDirectory1 ="";
			//String parentDirectoryDnld = "";
			String sep = System.getProperty("file.separator");
			//String filePath1 = "REPORTS"+sep+"Interview List"+sep+session+sep
			String filePath1 = "REPORTS"+sep+"Interview List"+sep+session+sep+progname+"InterviewList.zip";;
			//session is missing from filePath1  
			System.out.println("filepath1:"+filePath1);
			parentDirectory1 = this.getServletContext().getRealPath("/")+filePath1;
			System.out.println("parentDirectory1 in dnlZipFile:"+parentDirectory1);
			
			//String filepath = fullPath;
			String filepath = parentDirectory1;
			System.out.println("filepath on servlet "+filepath);
			File file = new File(filepath);

			if (!file.exists())
				try {
					throw new FileNotFoundException(file.getAbsolutePath());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					request.setAttribute("exceptionDnld","The report has not been generated");
				}

				response.setHeader("Content-Type", "application/zip");
				response.setHeader("Content-Length", String.valueOf(file.length()));
				response.setHeader("Content-disposition", "attachment;filename=\"" + programId+"Interview List.zip" + "\"");

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

				System.out.println("zip downloaded");

				//response.sendRedirect("http://localhost:8080/newadmission/report/getPrograms.htm?beforeCount="+beforeCount+"&afterCount="+afterCount);
				response.sendRedirect("http://www.google.com");

		}catch(Exception e){

			System.out.println("Exception while downloading zip file"+e.getMessage());
		}
	}

	public void onlyDnlZipFile(String programId, HttpServletResponse response,HttpServletRequest request,String session){
	try
	{	
		System.out.println("onlyDnlZipFile programId in dnlZipFile"+programId);
		String progname = reportDao.getProgramName(programId);
		System.out.println("onlyDnlZipFile progname in dnlZipFile"+progname);
		String parentDirectory1 ="";
		String sep = System.getProperty("file.separator");
		String filePath1 = "REPORTS"+sep+"Interview List"+sep+session+sep+progname+"InterviewList.zip";;
		System.out.println("onlyDnlZipFile filepath1:"+filePath1);
		parentDirectory1 = this.getServletContext().getRealPath("/")+filePath1;
		System.out.println("onlyDnlZipFile parentDirectory1 in dnlZipFile:"+parentDirectory1);
		
		String filepath = parentDirectory1;
		System.out.println("onlyDnlZipFile filepath on servlet "+filepath);
		File file = new File(filepath);

		if (!file.exists())
			try {
				throw new FileNotFoundException(file.getAbsolutePath());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				request.setAttribute("exceptionDnld","The report has not been generated");
			}

			response.setHeader("Content-Type", "application/zip");
			response.setHeader("Content-Length", String.valueOf(file.length()));
			response.setHeader("Content-disposition", "attachment;filename=\"" + programId+"Interview List.zip" + "\"");

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

			System.out.println("onlyDnlZipFile zip downloaded");

			//response.sendRedirect("http://localhost:8080/newadmission/report/getPrograms.htm?beforeCount="+beforeCount+"&afterCount="+afterCount);
			response.sendRedirect("http://www.google.com");

	}catch(Exception e){

		System.out.println("Exception while downloading zip file onlyDnlZipFile"+e.getMessage());
	}
}
	public ModelAndView forDownload(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		String path=request.getParameter("path");
		String progId=request.getParameter("programId");
		String progId2=request.getParameter("programId2");
		String session=request.getParameter("session");
		System.out.println("forDownload path in if:"+path+"progId:"+progId+"session:"+session);

		if(path.isEmpty() & progId.isEmpty() & session.isEmpty()){
			List<ReportBean> session1=reportDao.getSession();
			String session2=session1.get(0).getSession().toString().substring(0, 4).concat("-")+session1.get(0).getSession().toString().substring(8,12);
			//String progID=request.getParameter("program");
			//progID is to be taken as user selects it for downloading not static value as taken below. 
			//Also can not be taken from request as it has already been processed
			//String progID="0001080";
			String progID=progId2;
			System.out.println("forDownload path in else:"+"progID:"+progID+"session2:"+session2+"progId2:"+progId2);
			onlyDnlZipFile(progID,response,request,session2);
		}
		else{
			dnlZipFile(path,progId,response,request,session);
			System.out.println("if of forDownload"+"progId2:"+progId2);
		}
		return null;
	}

	//Upload docs start
	
	
	public ModelAndView getDocumentsUploadLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView model = new ModelAndView("account/LoginForDocumentsUpload");
		return model;
	} 
 //to be uncommented CCA document upload login 
	public ModelAndView getUploadDocumentsPanel(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		ModelAndView model = null;
		
		try
		{
			String applicationNumber =  request.getParameter("applicationNumber");
			String password = request.getParameter("password");
			String universityID = getServletContext().getInitParameter("universityID");
			
			Candidate applicant = new Candidate();
			applicant.setApplicationNumber(applicationNumber);
			applicant.setPassword(password);
			applicant.setUniversityID(universityID);
				
			List<Candidate> applicantList = reportDao.getUserList(applicant);
			int noOfUser = applicantList.size();
			
			if(noOfUser == 1)
			{
				HttpSession session = request.getSession(false);
				if(session == null || session.getAttribute("applicant") == null)
				{
					session = request.getSession(true);
				}
				else
				{
					String appNumToBeCompared = ((Candidate)session.getAttribute("applicant")).getApplicationNumber();
					if(!(appNumToBeCompared.trim().equalsIgnoreCase(applicationNumber)))
					{
						String message = "A SESSION IS ALREADY ACTIVE FOR THIS BROWSER.PLEASE USE DIFFERENT BROWSER OR TRY AFTER "+session.getMaxInactiveInterval()/60+" MINUTES.";
	               		model = new ModelAndView("errorPages/Error", "error", message);
						return model;
					}
					else
					{
						session = request.getSession(true);
					}
					
				}
				
				session.setAttribute("applicant", applicantList.get(0));
				model = new ModelAndView("reports/DocumentsUpload");
				return model;
			}
			else 
			{	
				String message = null;
				if(noOfUser==0)
				{
					message = "USER NAME OR PASSWORD IS INCORRECT.";
				}
				else
				{
					message = "DUPLICATE USER FOUND.";
				}
				return new ModelAndView("account/LoginForDocumentsUpload", "message", message);
			}
		}
		catch(Exception ex)
		{
			model = new ModelAndView("errorPages/ExceptionPage");
			model.addObject("exceptionSource", "THERE IS SOME PROBLEM IN LOGIN FOR YOUR APPLICATION NUMBER.");
			return model;
		}
		
		
	}
	
	public ModelAndView logout(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession(false);
		if(session == null)
		{
			return new ModelAndView("errorPages/SessionExpired","message","Your Session has been expired.");
		}
		else
		{
			session.invalidate();
			String message = "You have successfully Logged Out.";
			return new ModelAndView("account/LoginForDocumentsUpload","message",message);

		}

	}
	
	
	public boolean isEmptyString(String str)
	{
		return (str == null || str.trim().equals(""));
	}
	
	public ModelAndView uploadDocuments(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession(false);
		Candidate sessionApplicant = (Candidate) session.getAttribute("applicant");
		if(session == null || sessionApplicant == null)
		{
			return new ModelAndView("errorPages/SessionExpired","message","Your Session has been expired.");
		}
		
		try
		{
			int fileMaxSize = Integer.parseInt(this.getServletContext().getInitParameter("document_size"));	
			String tomcatHome = System.getProperty("catalina.base");
			String docRootFolder = null;
			if(tomcatHome == null || tomcatHome.trim().equalsIgnoreCase(""))
			{
				docRootFolder = this.getServletContext().getInitParameter("DOC_FOLDER");
			}
			else
			{
				String str = tomcatHome.replace(tomcatHome.substring(tomcatHome.lastIndexOf(File.separator)),"");
				docRootFolder = str.substring(str.lastIndexOf(File.separator)+1);
			}

			docRootFolder=docRootFolder+File.separator+"ApplicantDocuments";
			String applicationNumber = sessionApplicant.getApplicationNumber();
			String filePath = ReportController.getDocumentPath(request, this.getServletContext());

			File folder=new File(filePath);
			if(!folder.exists())
			{
				folder.mkdirs(); 
			}

			filePath = filePath + File.separator;

			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
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
					if((!fi.isFormField()) && (!fi.getName().equals("")))	
					{
						String recievedFileName = fi.getName().toLowerCase();
						String fileType = recievedFileName.substring(recievedFileName.lastIndexOf(".") + 1);
						File file = new File(filePath);
						double fileSize = fi.getSize()/1024; //IN KiloBytes
						if(fi.getFieldName().equalsIgnoreCase("cca"))
						{
							if(fileSize > fileMaxSize)
							{
								response.getWriter().write("N");
								List<Candidate> applicantList = reportDao.getUserList(sessionApplicant);
								session.setAttribute("applicant", applicantList.get(0));
								return new ModelAndView("reports/DocumentsUpload", "message", "CCA file size exceeded "+fileMaxSize+" KB");
							}
						}

						if(fi.getFieldName().equalsIgnoreCase("ncc"))
						{
							if(fileSize > fileMaxSize)
							{
								response.getWriter().write("N");
								List<Candidate> applicantList = reportDao.getUserList(sessionApplicant);
								session.setAttribute("applicant", applicantList.get(0));
								return new ModelAndView("reports/DocumentsUpload", "message", "NCC file size exceeded "+fileMaxSize+" KB");
							}				
						}

						if(fi.getFieldName().equalsIgnoreCase("nss"))
						{
							if(fileSize > fileMaxSize)
							{
								response.getWriter().write("N");
								List<Candidate> applicantList = reportDao.getUserList(sessionApplicant);
								session.setAttribute("applicant", applicantList.get(0));
								return new ModelAndView("reports/DocumentsUpload", "message", "NSS file size exceeded "+fileMaxSize+" KB");
							}				
						}

						if(fi.getFieldName().equalsIgnoreCase("social"))
						{
							if(fileSize > fileMaxSize)
							{
								response.getWriter().write("N");
								List<Candidate> applicantList = reportDao.getUserList(sessionApplicant);
								session.setAttribute("applicant", applicantList.get(0));
								return new ModelAndView("reports/DocumentsUpload", "message", "Social file size exceeded "+fileMaxSize+" KB");
							}				
						}

						if(!(fileType.equalsIgnoreCase("JPG")||fileType.equalsIgnoreCase("JPEG")||fileType.equalsIgnoreCase("PDF")))
						{
							response.getWriter().write("N");
							List<Candidate> applicantList = reportDao.getUserList(sessionApplicant);
							session.setAttribute("applicant", applicantList.get(0));
							return new ModelAndView("reports/DocumentsUpload", "message", "Files must be jpeg/jpg or pdf.");
						}
						else
						{
							String fieldName = fi.getFieldName();
							String fileName = fi.getName();
							String fileExtension = "";
							if(fileType.equalsIgnoreCase("jpg") || fileType.equalsIgnoreCase("jpeg"))
							{
								fileExtension = ".jpg";
							}
							else if(fileType.equalsIgnoreCase("pdf"))
							{
								fileExtension = ".pdf";
							}

							if(fieldName.equalsIgnoreCase("cca"))
							{
								fileName=applicationNumber+"_cca"+fileExtension;
							}
							else if(fieldName.equalsIgnoreCase("ncc"))
							{
								fileName=applicationNumber+"_ncc"+fileExtension;
							}
							else if(fieldName.equalsIgnoreCase("nss"))
							{
								fileName=applicationNumber+"_nss"+fileExtension;
							}
							else if(fieldName.equalsIgnoreCase("social"))
							{
								fileName=applicationNumber+"_social"+fileExtension;
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

							if(fieldName.equalsIgnoreCase("cca"))
							{
								reportDao.updateDocumentUploadStatus("cca", sessionApplicant);
							}
							else if(fieldName.equalsIgnoreCase("ncc"))
							{
								reportDao.updateDocumentUploadStatus("ncc", sessionApplicant);
							}
							else if(fieldName.equalsIgnoreCase("nss"))
							{
								reportDao.updateDocumentUploadStatus("nss", sessionApplicant);
							}
							else if(fieldName.equalsIgnoreCase("social"))
							{
								reportDao.updateDocumentUploadStatus("social", sessionApplicant);
							}
							else
							{

							}


						}

					}
				}

			}
			catch(Exception ex) 
			{
				ex.printStackTrace();
				List<Candidate> applicantList = reportDao.getUserList(sessionApplicant);
								session.setAttribute("applicant", applicantList.get(0));
				return new ModelAndView("reports/DocumentsUpload", "message", ex.getMessage());	
			}
		}
	catch(Exception e) 
	{
		e.printStackTrace();
		List<Candidate> applicantList = reportDao.getUserList(sessionApplicant);
								session.setAttribute("applicant", applicantList.get(0));
		return new ModelAndView("reports/DocumentsUpload", "message", e.getMessage());	
	}
  
	
	List<Candidate> applicantList = reportDao.getUserList(sessionApplicant);
	session.setAttribute("applicant", applicantList.get(0));
	return new ModelAndView("reports/DocumentsUpload");
	
	}


	public static String getDocumentPath(HttpServletRequest request, ServletContext context)
	{
		String applicantDocumentPath = null;
		String docParamVal = context.getInitParameter("DOC_FOLDER");
		if(docParamVal == null || docParamVal.trim().equalsIgnoreCase(""))
		{
			applicantDocumentPath = null;
		}
		else
		{
			Candidate applicant = (Candidate) request.getSession().getAttribute("applicant");
			
			String homeDir = System.getProperty("user.home");
			String docRootFolder = homeDir + File.separator + docParamVal;
			
			applicantDocumentPath = applicant.getApplicationDocumentPath();
						
			if(applicantDocumentPath == null || applicantDocumentPath.trim().equalsIgnoreCase(""))
			{
				docRootFolder = docRootFolder + File.separator + "ApplicantDocuments" + File.separator + applicant.getSessionStartDate().substring(0, 4)+ "-" +applicant.getSessionEndDate().substring(0, 4);
				applicantDocumentPath = docRootFolder + File.separator + applicant.getApplicationNumber();
			}

		}
				
		return applicantDocumentPath;
	}

//LE Documents Upload Starts

		
	public ModelAndView getDocumentsUploadLoginLE(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView model = new ModelAndView("account/LoginForDocumentsUploadLE");
		return model;
	}

	public ModelAndView getUploadDocumentsPanelLE(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		ModelAndView model = null;
		
		try
		{
			String applicationNumber =  request.getParameter("applicationNumber");
			String password = request.getParameter("password");
			String universityID = getServletContext().getInitParameter("universityID");
			
			Candidate applicant = new Candidate();
			applicant.setApplicationNumber(applicationNumber);
			applicant.setPassword(password);
			applicant.setUniversityID(universityID);
				
			List<Candidate> applicantList = reportDao.getUserListLE(applicant);
			int noOfUser = applicantList.size();
			
			if(noOfUser == 1)
			{
				HttpSession session = request.getSession(false);
				if(session == null || session.getAttribute("applicant") == null)
				{
					session = request.getSession(true);
				}
				else
				{
					String appNumToBeCompared = ((Candidate)session.getAttribute("applicant")).getApplicationNumber();
					if(!(appNumToBeCompared.trim().equalsIgnoreCase(applicationNumber)))
					{
						String message = "A SESSION IS ALREADY ACTIVE FOR THIS BROWSER.PLEASE USE DIFFERENT BROWSER OR TRY AFTER "+session.getMaxInactiveInterval()/60+" MINUTES.";
	               		model = new ModelAndView("errorPages/Error", "error", message);
						return model;
					}
					else
					{
						session = request.getSession(true);
					}
					
				}
				
				session.setAttribute("applicant", applicantList.get(0));
				model = new ModelAndView("reports/DocumentsUploadLE");
				return model;
			}
			else 
			{	
				String message = null;
				if(noOfUser==0)
				{
					message = "USER NAME OR PASSWORD IS INCORRECT.";
				}
				else
				{
					message = "DUPLICATE USER FOUND.";
				}
				return new ModelAndView("account/LoginForDocumentsUploadLE", "message", message);
			}
		}
		catch(Exception ex)
		{
			model = new ModelAndView("errorPages/ExceptionPage");
			model.addObject("exceptionSource", "THERE IS SOME PROBLEM IN LOGIN FOR YOUR APPLICATION NUMBER.");
			return model;
		}
		
		
	}
	
	public ModelAndView logoutLE(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession(false);
		if(session == null)
		{
			return new ModelAndView("account/LoginForDocumentsUploadLE","message","Your Session has been expired.");
		}
		else
		{
			session.invalidate();
			String message = "You have successfully Logged Out.";
			return new ModelAndView("account/LoginForDocumentsUploadLE","message",message);

		}

	}
	
	
	public ModelAndView uploadDocumentsLE(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession(false);
		Candidate sessionApplicant = (Candidate) session.getAttribute("applicant");
		if(session == null || sessionApplicant == null)
		{
			return new ModelAndView("account/LoginForDocumentsUploadLE","message","Your Session has been expired.");
		}
		
		try
		{
			int fileMaxSize = Integer.parseInt(this.getServletContext().getInitParameter("document_size"));	
			
			String docRootFolder = "/home/appadmin/LE_DOCUMENTS";
			String applicationNumber = sessionApplicant.getApplicationNumber();
			String filePath = docRootFolder + File.separator + applicationNumber;

			File folder = new File(filePath);
			if(!folder.exists())
			{
				folder.mkdirs(); 
			}

			filePath = filePath + File.separator;

			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
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
					if((!fi.isFormField()) && (!fi.getName().equals("")))	
					{
						String recievedFileName = fi.getName().toLowerCase();
						String fileType = recievedFileName.substring(recievedFileName.lastIndexOf(".") + 1);
						File file = new File(filePath);
						double fileSize = fi.getSize()/1024; //IN KiloBytes
						if(fi.getFieldName().equalsIgnoreCase("cca"))
						{
							if(fileSize > fileMaxSize)
							{
								response.getWriter().write("N");
								List<Candidate> applicantList = reportDao.getUserListLE(sessionApplicant);
								session.setAttribute("applicant", applicantList.get(0));
								return new ModelAndView("reports/DocumentsUploadLE", "message", "CCA file size exceeded "+fileMaxSize+" KB");
							}
						}

						if(fi.getFieldName().equalsIgnoreCase("ncc"))
						{
							if(fileSize > fileMaxSize)
							{
								response.getWriter().write("N");
								List<Candidate> applicantList = reportDao.getUserListLE(sessionApplicant);
								session.setAttribute("applicant", applicantList.get(0));
								return new ModelAndView("reports/DocumentsUploadLE", "message", "NCC file size exceeded "+fileMaxSize+" KB");
							}				
						}

						if(fi.getFieldName().equalsIgnoreCase("nss"))
						{
							if(fileSize > fileMaxSize)
							{
								response.getWriter().write("N");
								List<Candidate> applicantList = reportDao.getUserListLE(sessionApplicant);
								session.setAttribute("applicant", applicantList.get(0));
								return new ModelAndView("reports/DocumentsUploadLE", "message", "NSS file size exceeded "+fileMaxSize+" KB");
							}				
						}

						if(fi.getFieldName().equalsIgnoreCase("social"))
						{
							if(fileSize > fileMaxSize)
							{
								response.getWriter().write("N");
								List<Candidate> applicantList = reportDao.getUserListLE(sessionApplicant);
								session.setAttribute("applicant", applicantList.get(0));
								return new ModelAndView("reports/DocumentsUploadLE", "message", "Social file size exceeded "+fileMaxSize+" KB");
							}				
						}

						if(!(fileType.equalsIgnoreCase("JPG")||fileType.equalsIgnoreCase("JPEG")||fileType.equalsIgnoreCase("PDF")))
						{
							response.getWriter().write("N");
							List<Candidate> applicantList = reportDao.getUserListLE(sessionApplicant);
							session.setAttribute("applicant", applicantList.get(0));
							return new ModelAndView("reports/DocumentsUploadLE", "message", "Files must be jpeg/jpg or pdf.");
						}
						else
						{
							String fieldName = fi.getFieldName();
							String fileName = fi.getName();
							String fileExtension = "";
							if(fileType.equalsIgnoreCase("jpg") || fileType.equalsIgnoreCase("jpeg"))
							{
								fileExtension = ".jpg";
							}
							else if(fileType.equalsIgnoreCase("pdf"))
							{
								fileExtension = ".pdf";
							}

							if(fieldName.equalsIgnoreCase("cca"))
							{
								fileName=applicationNumber+"_cca"+fileExtension;
							}
							else if(fieldName.equalsIgnoreCase("ncc"))
							{
								fileName=applicationNumber+"_ncc"+fileExtension;
							}
							else if(fieldName.equalsIgnoreCase("nss"))
							{
								fileName=applicationNumber+"_nss"+fileExtension;
							}
							else if(fieldName.equalsIgnoreCase("social"))
							{
								fileName=applicationNumber+"_social"+fileExtension;
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

							if(fieldName.equalsIgnoreCase("cca"))
							{
								reportDao.updateDocumentUploadStatusLE("cca", sessionApplicant);
							}
							else if(fieldName.equalsIgnoreCase("ncc"))
							{
								reportDao.updateDocumentUploadStatusLE("ncc", sessionApplicant);
							}
							else if(fieldName.equalsIgnoreCase("nss"))
							{
								reportDao.updateDocumentUploadStatusLE("nss", sessionApplicant);
							}
							else if(fieldName.equalsIgnoreCase("social"))
							{
								reportDao.updateDocumentUploadStatusLE("social", sessionApplicant);
							}
							else
							{

							}


						}

					}
				}

			}
			catch(Exception ex) 
			{
				ex.printStackTrace();
				List<Candidate> applicantList = reportDao.getUserListLE(sessionApplicant);
								session.setAttribute("applicant", applicantList.get(0));
				return new ModelAndView("reports/DocumentsUploadLE", "message", ex.getMessage());	
			}
		}
	catch(Exception e) 
	{
		e.printStackTrace();
		List<Candidate> applicantList = reportDao.getUserListLE(sessionApplicant);
								session.setAttribute("applicant", applicantList.get(0));
		return new ModelAndView("reports/DocumentsUploadLE", "message", e.getMessage());	
	}
  
	
	List<Candidate> applicantList = reportDao.getUserListLE(sessionApplicant);
	session.setAttribute("applicant", applicantList.get(0));
	return new ModelAndView("reports/DocumentsUploadLE");
	
	}
	
	//Certificate Program Preference Starts
	public ModelAndView getCertificatePreferenceLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView model = new ModelAndView("account/LoginForCertificatePreference");
		return model;
	}
	
	public ModelAndView getCertificatePreferenceScreen(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		ModelAndView model = null;
		
		try
		{
			String applicationNumber =  request.getParameter("applicationNumber");
			String password = request.getParameter("password");
			String classPassed = request.getParameter("classPassed");
			String universityID = getServletContext().getInitParameter("universityID");
			
			Candidate applicant = new Candidate();
			applicant.setApplicationNumber(applicationNumber);
			applicant.setPassword(password);
			applicant.setUniversityID(universityID);
			applicant.setClassPassed(classPassed);
				
			List<Candidate> applicantList = reportDao.getUserWithProgram(applicant);
			int noOfUser = applicantList.size();
			
			if(noOfUser == 1)
			{
				HttpSession session = request.getSession(false);
				if(session == null || session.getAttribute("applicant") == null)
				{
					session = request.getSession(true);
				}
				else
				{
					String appNumToBeCompared = ((Candidate)session.getAttribute("applicant")).getApplicationNumber();
					if(!(appNumToBeCompared.trim().equalsIgnoreCase(applicationNumber)))
					{
						String message = "A SESSION IS ALREADY ACTIVE FOR THIS BROWSER.PLEASE USE DIFFERENT BROWSER OR TRY AFTER "+session.getMaxInactiveInterval()/60+" MINUTES.";
	               		model = new ModelAndView("errorPages/Error", "error", message);
						return model;
					}
					else
					{
						session = request.getSession(true);
					}
					
				}
				
				session.setAttribute("applicant", applicantList.get(0));
				model = new ModelAndView("account/CertificatePreference");
				model.addObject("preferencePrograms",reportDao.getPreferredPrograms(applicant));
				model.addObject("message","");
				return model;
			}
			else 
			{	
				String message = null;
				if(noOfUser==0)
				{
					message = "USER NAME OR PASSWORD IS INCORRECT.";
				}
				else
				{
					message = "DUPLICATE USER FOUND.";
				}
				return new ModelAndView("account/LoginForCertificatePreference", "message", message);
			}
		}
		catch(Exception ex)
		{
			model = new ModelAndView("errorPages/ExceptionPage");
			model.addObject("exceptionSource", "THERE IS SOME PROBLEM IN LOGIN FOR YOUR APPLICATION NUMBER.");
			return model;
		}
		
		
	}
	
	public ModelAndView submitPreference(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		ModelAndView model = null;
		try
		{
			String applicationNumber =  request.getParameter("applicationNumber");
			String transferFromProgram = request.getParameter("transferFromProgram");
			String p1 = request.getParameter("p1");
			String p2 = request.getParameter("p2");
			String p3 = request.getParameter("p3");
		System.out.println(applicationNumber+transferFromProgram+p1+p2+p3);	
			String universityID = getServletContext().getInitParameter("universityID");
	
			Candidate applicant = new Candidate();
			applicant.setApplicationNumber(applicationNumber);
			applicant.setFromProgram(transferFromProgram);			
			applicant.setPref1(p1);
			applicant.setPref2(p2);
			applicant.setPref3(p3);
			List<Candidate> candidates = reportDao.insertPreferences(applicant);
			System.out.println("List size is 2 "+candidates.size());
			model = new ModelAndView("account/PreferenceSubmitted");
			String message = "";
			if(candidates.get(0) != null)
			{
			 model.addObject("applicant", candidates.get(0));
			 message = "Your preferences have been submitted successfully and are as below:-";
			}
			else
			{
			 message = "There is some problem in preference submission.";
			}
			model.addObject("message", message);
			HttpSession session = request.getSession(false);
			if(session != null)
			{
				session.invalidate();
			}
			
		}
		catch(Exception ex)
		{
			model = new ModelAndView("errorPages/ExceptionPage");
			model.addObject("exceptionSource", "THERE IS SOME PROBLEM.");
			return model;
		}
		return model;
	}

 public ModelAndView logoutCertificatePreference(HttpServletRequest request,HttpServletResponse response) throws Exception
        {
                HttpSession session = request.getSession(false);
                if(session == null)
                {
                        return new ModelAndView("account/LoginForCertificatePreference","message","Your Session has expired.");
                }
                else
                {
                        session.invalidate();
                        String message = "You have successfully Logged Out.";
                        return new ModelAndView("account/LoginForCertificatePreference","message",message);

                }

        }
 
 public ModelAndView show_att_page(HttpServletRequest request,HttpServletResponse response) throws Exception {
	 ModelAndView model = new ModelAndView("reports/AttendanceSheetDownload");
	 List<CommonBean> programs = reportDao.getProgramsWithShifts();
	 model.addObject("programs", programs);
	 return model;
 }
 public ModelAndView getAttSheetGenPage(HttpServletRequest request,HttpServletResponse response) throws Exception {

	 String program = request.getParameter("program");
	 String shift = request.getParameter("shift");
	 String gender = request.getParameter("btn"); 
	 CommonBean cb = new CommonBean();
	 cb.setCode(program);
	 cb.setDescription(shift);
	 cb.setOtherProperty1(gender); String vanueCode = request.getParameter("vanue"); String vanueName = request.getParameter("vanueName");cb.setCode2(vanueCode);
	 
     List<Candidate> applicants = reportDao.getAttdApplicants(cb);

 	String filename = "attendance_sheet_"+program+shift.replace(":", "").replace(" ", "").replace(".", "").replace("-", "")+vanueCode+".pdf";
 	String docRootFolder = System.getProperty("user.home") + File.separator + this.getServletContext().getInitParameter("DOC_FOLDER");
 	File file1 = new File(docRootFolder);
 	if(!file1.exists()){
		file1.mkdirs();
	}
 		
	Document document = new Document();
	BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,BaseFont.WINANSI,BaseFont.EMBEDDED );
	BaseFont baseBoldFont = BaseFont.createFont(BaseFont.HELVETICA_BOLD,BaseFont.WINANSI,BaseFont.EMBEDDED );
	Font boldFont = new Font(baseBoldFont, 8);
	Font font = new Font(bf, 8);
	
	PdfWriter.getInstance(document, new FileOutputStream(docRootFolder+File.separator+filename));
	document.setPageSize(PageSize.A4.rotate());
	document.setMargins(5, 5, 5, 5);
	document.setMarginMirroring(true);
	
	//Paragraph headerLine1 = new Paragraph("Dayalbagh Educational Institute".toUpperCase(), new Font(BaseFont.createFont(this.getServletContext().getRealPath( "Fonts") + File.separator+ "BOLD.TTF",BaseFont.WINANSI,BaseFont.EMBEDDED ), 15));
	//headerLine1.setAlignment(Element.ALIGN_CENTER);
	
	Paragraph headerLine2 = new Paragraph("Attendance Sheet for the Entrance Test in D.E.I.", new Font(BaseFont.createFont(this.getServletContext().getRealPath( "Fonts") + File.separator+ "BOLD.TTF",BaseFont.WINANSI,BaseFont.EMBEDDED ), 15));
	headerLine2.setAlignment(Element.ALIGN_CENTER);
	
	PdfPTable detailTable = new PdfPTable(new float[]{4F,6F, 9F, 20F, 4F, 4F, 20F, 10F, 10F, 10F, 12F, 8F});
	//detailTable.getDefaultCell().setBorder(Rectangle.);
	detailTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	detailTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
	detailTable.setWidthPercentage(100);
	detailTable.setSpacingBefore(5);
	detailTable.setSpacingAfter(5);
	detailTable.setHeaderRows(3);
	
	
	PdfPCell headerCell = new PdfPCell(headerLine2);
	headerCell.setBorder(Rectangle.NO_BORDER);
	headerCell.setColspan(12);
	headerCell.setPadding(8);
	headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	detailTable.addCell(headerCell);
	
	PdfPCell vanueNameCell = new PdfPCell(new Phrase("Vanue : "+vanueName, boldFont));
	vanueNameCell.setBorder(Rectangle.NO_BORDER);
	vanueNameCell.setColspan(12);
	vanueNameCell.setPadding(8);
	vanueNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	vanueNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	detailTable.addCell(vanueNameCell);
	
		
	detailTable.addCell(new Phrase("S.No.", boldFont));
	detailTable.addCell(new Phrase("Photo", boldFont));
	detailTable.addCell(new Phrase("App. Number", boldFont));
	detailTable.addCell(new Phrase("Applicant Name", boldFont));
	detailTable.addCell(new Phrase("G", boldFont));
	detailTable.addCell(new Phrase("CT", boldFont));
	detailTable.addCell(new Phrase("Father Name", boldFont));
	detailTable.addCell(new Phrase("Program Name", boldFont));
	detailTable.addCell(new Phrase("Center Opted", boldFont));
	//detailTable.addCell(new Phrase("Aadhaar Number", boldFont));
	detailTable.addCell(new Phrase("Contact Number", boldFont));
	detailTable.addCell(new Phrase("Shift-Date and Time", boldFont));
	detailTable.addCell(new Phrase("Sign.", boldFont));
	
	for(int i = 0; i<applicants.size(); i++) {
		
		String applicantDocumentPath = applicants.get(i).getApplicationDocumentPath();//Added by Arjun
			Image applicantPhoto =null;
			
			try {	
				applicantPhoto = Image.getInstance(applicantDocumentPath+File.separator+"photo.jpg");
			}catch (Exception e) {
				applicantPhoto = Image.getInstance(this.getServletContext().getRealPath("images")+File.separator+"USER2.jpg");
				
			}
			applicantPhoto.scaleAbsolute(200f, 200f);
			Candidate applicant = applicants.get(i);
		
		detailTable.addCell(new Phrase((i+1)+"", boldFont));
		detailTable.addCell(applicantPhoto);
		detailTable.addCell(new Phrase(applicant.getApplicationNumber(), boldFont));
		detailTable.addCell(new Phrase(applicant.getApplicantName(), boldFont));
		detailTable.addCell(new Phrase(applicant.getGender(), boldFont));
		detailTable.addCell(new Phrase(applicant.getCategory(), boldFont));
		detailTable.addCell(new Phrase(applicant.getFatherName(), boldFont));
		detailTable.addCell(new Phrase(applicant.getProgramName(), boldFont));
		detailTable.addCell(new Phrase(applicant.getExamCenter(), boldFont));
		//detailTable.addCell(new Phrase(applicant.getAadhaarNumber(), boldFont));
		detailTable.addCell(new Phrase(applicant.getContactNumber(), boldFont));
		detailTable.addCell(new Phrase(applicant.getShift(), boldFont));
		detailTable.addCell(new Phrase(" ", boldFont));
			
	}
	
	document.open();
	//document.add(headerLine1);
	//document.add(headerLine2);
	document.add(detailTable);
	
	document.close();
 		
 	File file = new File(docRootFolder+File.separator+filename);
 	
 	try
 	{
 		if(!file.exists())
 			try 
 			{
 				throw new FileNotFoundException(file.getAbsolutePath());
 			} 
 			catch(FileNotFoundException e) 
 			{
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
 		
 	}
 	
 	 
  
     return null;

 }
 
 public ModelAndView show_att_page2(HttpServletRequest request,HttpServletResponse response) throws Exception {
	 ModelAndView model = new ModelAndView("reports/VanueWiseAttSheet");
	 List<CommonBean> programs = reportDao.getProgramsWithShiftsVanue();
	 model.addObject("programs", programs);
	 return model;
 }
 
 public ModelAndView interview_att_sheet(HttpServletRequest request,HttpServletResponse response) throws Exception {
	 ModelAndView model = new ModelAndView("reports/InterviewAttSheet");
	 List<CommonBean> programs = reportDao.getProgramsWithInterviewVanue();
	 model.addObject("programs", programs);
	 return model;
 }
 
 public ModelAndView getAttSheetInterview(HttpServletRequest request,HttpServletResponse response) throws Exception {

	 String program = request.getParameter("program");
	 String board = request.getParameter("board");
	 String gender = request.getParameter("btn"); 
	 CommonBean cb = new CommonBean();
	 cb.setDescription(program);
	 cb.setCode(board);
	 cb.setOtherProperty1(gender); String vanueCode = request.getParameter("vanue"); String vanueName = request.getParameter("vanueName");cb.setCode2(vanueCode);
	 
     List<Candidate> applicants = reportDao.getIntAttdApplicants(cb);

 	String filename = "attendance_sheet_"+program.replace(":", "").replace(" ", "").replace(".", "").replace("-", "")+vanueCode.replace(":", "").replace(" ", "").replace(".", "").replace("-", "")+board.replace(" ","").replace("-","")+".pdf";
 	String docRootFolder = System.getProperty("user.home") + File.separator + this.getServletContext().getInitParameter("DOC_FOLDER");
 	File file1 = new File(docRootFolder);
 	if(!file1.exists()){
		file1.mkdirs();
	}
 		
	Document document = new Document();
	BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,BaseFont.WINANSI,BaseFont.EMBEDDED );
	BaseFont baseBoldFont = BaseFont.createFont(BaseFont.HELVETICA_BOLD,BaseFont.WINANSI,BaseFont.EMBEDDED );
	Font boldFont = new Font(baseBoldFont, 8);
	Font font = new Font(bf, 8);
	
	PdfWriter.getInstance(document, new FileOutputStream(docRootFolder+File.separator+filename));
	document.setPageSize(PageSize.A4.rotate());
	document.setMargins(5, 5, 5, 5);
	document.setMarginMirroring(true);
	
	//Paragraph headerLine1 = new Paragraph("Dayalbagh Educational Institute".toUpperCase(), new Font(BaseFont.createFont(this.getServletContext().getRealPath( "Fonts") + File.separator+ "BOLD.TTF",BaseFont.WINANSI,BaseFont.EMBEDDED ), 15));
	//headerLine1.setAlignment(Element.ALIGN_CENTER);
	
	Paragraph headerLine2 = new Paragraph("Attendance Sheet for the Admission-Personal-Interview in D.E.I.", new Font(BaseFont.createFont(this.getServletContext().getRealPath( "Fonts") + File.separator+ "BOLD.TTF",BaseFont.WINANSI,BaseFont.EMBEDDED ), 15));
	headerLine2.setAlignment(Element.ALIGN_CENTER);
	
	PdfPTable detailTable = new PdfPTable(new float[]{4F,6F, 9F, 20F, 4F, 4F, 20F, 10F,  10F, 8F});
	//detailTable.getDefaultCell().setBorder(Rectangle.);
	detailTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	detailTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
	detailTable.setWidthPercentage(100);
	detailTable.setSpacingBefore(5);
	detailTable.setSpacingAfter(5);
	detailTable.setHeaderRows(3);
	
	
	PdfPCell headerCell = new PdfPCell(headerLine2);
	headerCell.setBorder(Rectangle.NO_BORDER);
	headerCell.setColspan(10);
	headerCell.setPadding(8);
	headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	detailTable.addCell(headerCell);
	
	PdfPCell vanueNameCell = new PdfPCell(new Phrase("Vanue : " + vanueName + "  |  Board Name : " + board, boldFont));
	vanueNameCell.setBorder(Rectangle.NO_BORDER);
	vanueNameCell.setColspan(10);
	vanueNameCell.setPadding(8);
	vanueNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	vanueNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	detailTable.addCell(vanueNameCell);
	
		
	detailTable.addCell(new Phrase("S.No.", boldFont));
	detailTable.addCell(new Phrase("Photo", boldFont));
	detailTable.addCell(new Phrase("App. Number", boldFont));
	detailTable.addCell(new Phrase("Applicant Name", boldFont));
	detailTable.addCell(new Phrase("G", boldFont));
	detailTable.addCell(new Phrase("CT", boldFont));
	detailTable.addCell(new Phrase("Father Name", boldFont));
	detailTable.addCell(new Phrase("Program Name", boldFont));
	//detailTable.addCell(new Phrase("Center Opted", boldFont));
	//detailTable.addCell(new Phrase("Aadhaar Number", boldFont));
	detailTable.addCell(new Phrase("Contact Number", boldFont));
	//detailTable.addCell(new Phrase("Shift-Date and Time", boldFont));
	detailTable.addCell(new Phrase("Sign.", boldFont));
	
	for(int i = 0; i<applicants.size(); i++) {
		
		String applicantDocumentPath = applicants.get(i).getApplicationDocumentPath();//Added by Arjun
			Image applicantPhoto =null;
			
			try {	
				applicantPhoto = Image.getInstance(applicantDocumentPath+File.separator+"photo.jpg");
			}catch (Exception e) {
				applicantPhoto = Image.getInstance(this.getServletContext().getRealPath("images")+File.separator+"USER2.jpg");
				
			}
			applicantPhoto.scaleAbsolute(200f, 200f);
			Candidate applicant = applicants.get(i);
		
		detailTable.addCell(new Phrase((i+1)+"", boldFont));
		detailTable.addCell(applicantPhoto);
		detailTable.addCell(new Phrase(applicant.getApplicationNumber(), boldFont));
		detailTable.addCell(new Phrase(applicant.getApplicantName(), boldFont));
		detailTable.addCell(new Phrase(applicant.getGender(), boldFont));
		detailTable.addCell(new Phrase(applicant.getCategory(), boldFont));
		detailTable.addCell(new Phrase(applicant.getFatherName(), boldFont));
		detailTable.addCell(new Phrase(applicant.getProgramName(), boldFont));
		//detailTable.addCell(new Phrase(applicant.getExamCenter(), boldFont));
		//detailTable.addCell(new Phrase(applicant.getAadhaarNumber(), boldFont));
		detailTable.addCell(new Phrase(applicant.getContactNumber(), boldFont));
		//detailTable.addCell(new Phrase(applicant.getShift(), boldFont));
		detailTable.addCell(new Phrase(" ", boldFont));
			
	}
	
	document.open();
	//document.add(headerLine1);
	//document.add(headerLine2);
	document.add(detailTable);
	
	document.close();
 		
 	File file = new File(docRootFolder+File.separator+filename);
 	
 	try
 	{
 		if(!file.exists())
 			try 
 			{
 				throw new FileNotFoundException(file.getAbsolutePath());
 			} 
 			catch(FileNotFoundException e) 
 			{
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
 		
 	}
 	
 	 
  
     return null;

 }


 public ModelAndView getAttSheetInterviewDistance(HttpServletRequest request,HttpServletResponse response) throws Exception {

	
	 List<CommonBean> distanceCenters = reportDao.getDistanceCenters();
	 for(CommonBean commonBean : distanceCenters)
	 {
		 List<Candidate> applicants = reportDao.getIntAttdApplicantsDis(commonBean);

		 	String filename = "DISTANCE_"+commonBean.getDescription().replace(":", "").replace(" ", "").replace(".", "").replace("-", "")+".pdf";
		 	String docRootFolder = System.getProperty("user.home") + File.separator + this.getServletContext().getInitParameter("DOC_FOLDER");
		 	File file1 = new File(docRootFolder);
		 	if(!file1.exists()){
				file1.mkdirs();
			}
		 		
			Document document = new Document();
			BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,BaseFont.WINANSI,BaseFont.EMBEDDED );
			BaseFont baseBoldFont = BaseFont.createFont(BaseFont.HELVETICA_BOLD,BaseFont.WINANSI,BaseFont.EMBEDDED );
			Font boldFont = new Font(baseBoldFont, 8);
			Font font = new Font(bf, 8);
			
			PdfWriter.getInstance(document, new FileOutputStream(docRootFolder+File.separator+filename));
			document.setPageSize(PageSize.A4.rotate());
			document.setMargins(5, 5, 5, 5);
			document.setMarginMirroring(true);
			
			//Paragraph headerLine1 = new Paragraph("Dayalbagh Educational Institute".toUpperCase(), new Font(BaseFont.createFont(this.getServletContext().getRealPath( "Fonts") + File.separator+ "BOLD.TTF",BaseFont.WINANSI,BaseFont.EMBEDDED ), 15));
			//headerLine1.setAlignment(Element.ALIGN_CENTER);
			
			Paragraph headerLine2 = new Paragraph("Attendance Sheet for the Admission-Personal-Interview in D.E.I.", new Font(BaseFont.createFont(this.getServletContext().getRealPath( "Fonts") + File.separator+ "BOLD.TTF",BaseFont.WINANSI,BaseFont.EMBEDDED ), 15));
			headerLine2.setAlignment(Element.ALIGN_CENTER);
			
			PdfPTable detailTable = new PdfPTable(new float[]{4F,6F, 9F, 20F, 4F, 4F, 20F, 10F,  10F, 8F});
			//detailTable.getDefaultCell().setBorder(Rectangle.);
			detailTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			detailTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			detailTable.setWidthPercentage(100);
			detailTable.setSpacingBefore(5);
			detailTable.setSpacingAfter(5);
			detailTable.setHeaderRows(3);
			
			
			PdfPCell headerCell = new PdfPCell(headerLine2);
			headerCell.setBorder(Rectangle.NO_BORDER);
			headerCell.setColspan(10);
			headerCell.setPadding(8);
			headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			detailTable.addCell(headerCell);
			
			PdfPCell vanueNameCell = new PdfPCell(new Phrase("Vanue : " + commonBean.getDescription() + "  |  Board Name : " + commonBean.getDescription(), boldFont));
			vanueNameCell.setBorder(Rectangle.NO_BORDER);
			vanueNameCell.setColspan(10);
			vanueNameCell.setPadding(8);
			vanueNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			vanueNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			detailTable.addCell(vanueNameCell);
			
				
			detailTable.addCell(new Phrase("S.No.", boldFont));
			detailTable.addCell(new Phrase("Photo", boldFont));
			detailTable.addCell(new Phrase("App. Number", boldFont));
			detailTable.addCell(new Phrase("Applicant Name", boldFont));
			detailTable.addCell(new Phrase("G", boldFont));
			detailTable.addCell(new Phrase("CT", boldFont));
			detailTable.addCell(new Phrase("Father Name", boldFont));
			detailTable.addCell(new Phrase("Program Name", boldFont));
			//detailTable.addCell(new Phrase("Center Opted", boldFont));
			//detailTable.addCell(new Phrase("Aadhaar Number", boldFont));
			detailTable.addCell(new Phrase("Contact Number", boldFont));
			//detailTable.addCell(new Phrase("Shift-Date and Time", boldFont));
			detailTable.addCell(new Phrase("Sign.", boldFont));
			
			for(int i = 0; i<applicants.size(); i++) {
				
				String applicantDocumentPath = applicants.get(i).getApplicationDocumentPath();//Added by Arjun
					Image applicantPhoto =null;
					
					try {	
						applicantPhoto = Image.getInstance(applicantDocumentPath+File.separator+"photo.jpg");
					}catch (Exception e) {
						applicantPhoto = Image.getInstance(this.getServletContext().getRealPath("images")+File.separator+"USER2.jpg");
						
					}
					applicantPhoto.scaleAbsolute(200f, 200f);
					Candidate applicant = applicants.get(i);
				
				detailTable.addCell(new Phrase((i+1)+"", boldFont));
				detailTable.addCell(applicantPhoto);
				detailTable.addCell(new Phrase(applicant.getApplicationNumber(), boldFont));
				detailTable.addCell(new Phrase(applicant.getApplicantName(), boldFont));
				detailTable.addCell(new Phrase(applicant.getGender(), boldFont));
				detailTable.addCell(new Phrase(applicant.getCategory(), boldFont));
				detailTable.addCell(new Phrase(applicant.getFatherName(), boldFont));
				detailTable.addCell(new Phrase(applicant.getProgramName(), boldFont));
				//detailTable.addCell(new Phrase(applicant.getExamCenter(), boldFont));
				//detailTable.addCell(new Phrase(applicant.getAadhaarNumber(), boldFont));
				detailTable.addCell(new Phrase(applicant.getContactNumber(), boldFont));
				//detailTable.addCell(new Phrase(applicant.getShift(), boldFont));
				detailTable.addCell(new Phrase(" ", boldFont));
					
			}
			
			document.open();
			//document.add(headerLine1);
			//document.add(headerLine2);
			document.add(detailTable);
			
			document.close();

	 }
     return null;

 }

public ModelAndView admissionToUploadLink(HttpServletRequest request, HttpServletResponse response) throws Exception
{
	 HttpSession session = request.getSession(false);
	 session.invalidate();
	 return getDocumentsUploadLogin(request, response);

}



	

}



