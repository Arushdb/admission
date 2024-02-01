package in.ac.dei.edrp.admissionsystem.userform;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.ibatis.sqlmap.client.SqlMapClient;
import in.ac.dei.edrp.admissionsystem.common.CommonBean;
import in.ac.dei.edrp.admissionsystem.shared.SqlMapManager;
import in.ac.dei.edrp.admissionsystem.testing.testingController;
import in.ac.dei.edrp.admissionsystem.userform.userformDAO;
import in.ac.dei.edrp.admissionsystem.userform.UserFormBean;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
public class userformController extends MultiActionController

{
	SqlMapClient client = SqlMapManager.getSqlMapClient();
	 private boolean isMultipart;
	   private String filePath;
	   private int maxFileSize = 1500 * 1024;
	   private int maxMemSize = 100 * 1024;
	  private File file ;
private userformDAO daoservice;
	
	public void setDaoservice(userformDAO daoservice) 
	{
		this.daoservice = daoservice;
	}
	
	public void method1()
	{
	

	}
	
	
	public ModelAndView combodata(HttpServletRequest request,
			HttpServletResponse response)throws Exception
	{
		for(int i=0 ; i<10 ; i++)
		{
		combodata2(request, response);
		}

	return null;
	
	}
	
	  private int seqnumber()
		{
			int applicationnumber=0;
			int response=0;
			int seqNum=1;
			try
			{
				UserFormBean inputbean = new UserFormBean();
				inputbean.setUgpg("USFILE");
				inputbean.setUniversityId("0001");
				UserFormBean sysValueObj = (UserFormBean) client.queryForObject("userform.getSystemValues", inputbean);
				seqNum=Integer.parseInt(sysValueObj.getSequenceNumber())+1;
		        inputbean.setOldSequenceNumber(sysValueObj.getSequenceNumber());
		        inputbean.setSequenceNumber(String.valueOf(seqNum));
		        response=client.update("userform.updateSystemValuesUserForm", inputbean);
			}
			catch (Exception e)
			{
				
			}
			return  seqNum;
		}
	  private void update_userorm_download(String seq)
	  {
		  try
		  {
			  UserFormBean inputbean = new UserFormBean();
			  inputbean.setSequenceNumber(seq);
			  client.insert("userform.insert_userform_download", inputbean);
		  }
		  catch (Exception e)
		  {
			System.out.println(e);  
		  }
	  }
	  private String writeexcelfile()
		{
		  String downloadflag="";
		  String flag="NOTOK";
		                    try {
		                    	
		                    	UserFormBean bean = new UserFormBean();
		                    	bean.setUgpg("FILSTA");
		                    	bean.setUniversityId("0001");
			                    UserFormBean SQ = (UserFormBean) client.queryForObject("userform.getSystemValues",bean);
			                    SQ.getSequenceNumber();
			                    System.out.println("FILE Status"+ SQ.getSequenceNumber());
			                    downloadflag=SQ.getSequenceNumber();
			                    
		                        } 
		                        catch (SQLException e1) 
		                        {
			                    e1.printStackTrace();
		                        }
            
		              if (downloadflag.equalsIgnoreCase("LOC"))
		              {
		            	  while (!(downloadflag.equalsIgnoreCase("UNL")))
		    		      {
		            		  try 
		            		  {
			                    	UserFormBean bean = new UserFormBean();
			                    	bean.setUgpg("FILSTA");
			                    	bean.setUniversityId("0001");
				                    UserFormBean SQ = (UserFormBean) client.queryForObject("userform.getSystemValues",bean);
				                    SQ.getSequenceNumber();
				                    System.out.println("FILE Status"+ SQ.getSequenceNumber());
				                    downloadflag=SQ.getSequenceNumber();
			                  } 
			                        catch (SQLException e1) 
			                        {
				                    e1.printStackTrace();
			                        }
		    		
		    		          }
                           
		              }

		              flag=che1(); 
		            
		              return flag;       
		}
	
	  private String getcorrentseq()
	  {
		  String file="";
		  try
		  {
		UserFormBean bean = new UserFormBean();
      	bean.setUgpg("USFILE");
      	bean.setUniversityId("0001");
        UserFormBean SQ = (UserFormBean) client.queryForObject("userform.getSystemValues",bean);
        file=SQ.getSequenceNumber();
		  }
		  catch (Exception e)
		  {
			  System.out.println(e);
		  }
		  return file;
	  }
	  private String che1()
	  {
		  String flag01="";
		  String inc="";
		  try 
	        {
			  
	    	UserFormBean bean = new UserFormBean();
	    	bean.setValue("LOC");
			client.update("userform.updatefilesta",bean);
			
		    } 
	    catch (SQLException e1)
		{	
			e1.printStackTrace();
		}
	     inc =getcorrentseq();
		Workbook workbook;
		String path =DirPath();
		//String pathoffile="D:"+File.separator+"DonExcel"+File.separator+"FinalUserFormServerCopy.xls";
		//String pathoffile2="D:"+File.separator+"DonExcel"+File.separator+"FinalUserForm"+inc+".xls";
		String pathoffile=path+File.separator+"FinalUserFormServerCopy.xls";
		String pathoffile2=path+File.separator+"FinalUserForm"+inc+".xls";
		
		File exlFile = new File(pathoffile);
		File exlFile2 = new File(pathoffile2);
		try
		{
			Workbook wk = Workbook.getWorkbook(exlFile);
			WritableWorkbook wkr = Workbook.createWorkbook(exlFile2, wk);
			WritableSheet getsht = wkr.getSheet("Sheet4");
			WritableCell getcl = getsht.getWritableCell(0, 1);
			System.out.println("here1");
            Label l = (Label) getcl; 
			System.out.println("here0");
			int value =seqnumber();
			String val = Integer.toString(value); 
			l.setString(val); 
			update_userorm_download(val);
			System.out.println("here2");
			wkr.write();
			wkr.close();
			wk.close();
			UserFormBean bean = new UserFormBean();
	    	bean.setValue("UNL");
			client.update("userform.updatefilesta",bean);
			
			flag01=inc;
			
		}
		
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		
		return flag01;
	  }
	
	public void combodata2(HttpServletRequest request, HttpServletResponse response)throws Exception
	{
		
		try
		{
			String sta="";
			int va1=0;
			int va2=0;
			System.out.println("downloading");
			sta=writeexcelfile();
			response.setContentType("text/html");  
			PrintWriter out = response.getWriter();  
			String filename = "FinalUserForm"+sta+".xls";   
			String filepath=DirPath();
			response.setContentType("APPLICATION/OCTET-STREAM");   
			response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   
			  
			FileInputStream fileInputStream = new FileInputStream(filepath+File.separator+ filename);  
			            System.out.println(filepath+ filename);
			int i;   
			while ((i=fileInputStream.read()) != -1) 
			{  
			out.write(i);   
			}   
			fileInputStream.close();   
			out.close(); 	
		}
		catch (Exception e)
		{
			System.out.println("Error in generating excel  from the the system"+e);
			  UserFormBean bean = new UserFormBean();
	    	  bean.setValue("UNL");
	    	  client.update("userform.updatefilesta",bean);
		}
		  

		
			}
	
	public ModelAndView combodata1(HttpServletRequest request,
			HttpServletResponse response)
			{
		RequestDispatcher view = request.getRequestDispatcher("/OffLineForm.jsp");
		try
		{
			//ModelAndView model = new ModelAndView("account/ApplicantLogin");
			
			String uloadflag="";
			boolean errorExist = false;
			String error = "";
			try 
			{
	        	
	        	UserFormBean bean = new UserFormBean();
	        	bean.setUgpg("UPLSTA");
	        	bean.setUniversityId("0001");
	            UserFormBean SQ = (UserFormBean) client.queryForObject("userform.getSystemValues",bean);
	            SQ.getSequenceNumber();
	            System.out.println("FILE Status"+ SQ.getSequenceNumber());
	            uloadflag=SQ.getSequenceNumber();
	            
	            } 
	            catch (SQLException e1) 
	            {
	            e1.printStackTrace();
	            }
	            
	            if (uloadflag.equalsIgnoreCase("LOC"))
	            {
	          	  while (!(uloadflag.equalsIgnoreCase("UNL")))
	  		      {
	          		  try 
	          		      {
		                    	UserFormBean bean = new UserFormBean();
		                    	bean.setUgpg("UPLSTA");
		                    	bean.setUniversityId("0001");
			                    UserFormBean SQ = (UserFormBean) client.queryForObject("userform.getSystemValues",bean);
			                    SQ.getSequenceNumber();
			                    System.out.println("FILE Status"+ SQ.getSequenceNumber());
			                    uloadflag=SQ.getSequenceNumber();
		                  } 
		                        catch (SQLException e1) 
		                        {
			                    e1.printStackTrace();
		                        }
	  		
	  		          }
	                 
	            }
	            
	            try 
	            {
	            	uploadUserFormtoServer(request,response);
	        		String check =chaeckfile();
	        		if (check.equalsIgnoreCase("yes"))
	        		{
	        			 int applicationnumber=0;
	                     UserFormBean inputbean = new UserFormBean();
	                     inputbean.setUniversityId("0001");
	                     inputbean.setUgpg("USRAPP");
	                     int filenumber=getApplicationNumber(inputbean);
	                     String filename =Integer.toString(filenumber) ;
	                     rename(filename,"USERFORM");
	                   try
	                   {
	                	  String appnum= UserFormExcel(filename);  //this method extract the data from the excel file
	                		java.io.PrintWriter out = response.getWriter( );   
	          	 			
	          	 			String message = "UserForm Uploaded Successfully.<br/> Kindly download the Pdf Form.<br/> Your application Number is:-" +appnum+ "<br>";
	          	 			request.setAttribute("accountMessage", message);
	          	 			
	          	 			
	                   }
	                   catch(Exception e)
	                   {
	                	   System.out.println("problem in data extraction from userform-"+e);
	                	   UserFormBean bean = new UserFormBean();
	      	 	    	   bean.setValue("UNL");
	      	 			   client.update("userform.updateuplsta",bean);
	      	 			errorExist = true;
	      	 			error = "Error in Uploading User Form.";
	                   }
	                     UserFormBean bean = new UserFormBean();
	         	    	bean.setValue("UNL");
	         			client.update("userform.updateuplsta",bean);
	        		}
	        		else
	        		{
	        			System.out.println("already uploaded");
	        			
	        			errorExist = true;
	      	 			error= "Either UserForm is already uploaded or <br/> It is not Correctly Filled ";
	        			try
	        			{
	        				String path =DirPath();
	        				    File file = new File(path+File.separator+"USERFORM.xls");
	        			  		if(file.delete())
	        			  		{
	        			  			System.out.println(file.getName() + " is deleted!");
	        			  		}else
	        			  		{
	        			  			System.out.println("Delete operation is failed.");
	        			  		}
	        			  		
	        			  		     UserFormBean bean = new UserFormBean();
	        			 	    	 bean.setValue("UNL");
	        			 			client.update("userform.updateuplsta",bean);
	        			}
	        			catch(Exception e)
	        			{
	        				e.printStackTrace();
	        				 UserFormBean bean = new UserFormBean();
	        	 	    	 bean.setValue("UNL");
	        	 			 client.update("userform.updateuplsta",bean);
	        				
	        			}
	        			
	        		}
	            }
	            catch (Exception e)
	            {
	            	System.out.println("error in uploading file "+e);
	            	UserFormBean bean = new UserFormBean();
		 	    	 bean.setValue("UNL");
		 			 client.update("userform.updateuplsta",bean);
	            }
	            if(errorExist)
	            {
	            	//RequestDispatcher view = request.getRequestDispatcher("/OffLineForm.jsp");
	            	request.setAttribute("accountMessage", "<lable style='color:red;font-size:14px;'>"+error+"</lable>");
	            	view.forward(request, response);
	            	return null;
	            	//request.setAttribute("error", error);
	            	//view.forward(request, response);
	            }
	            else
	            {
	            	view.forward(request, response);
	            	return null;
	            }
	    
		}
		catch(Exception ex)
		{
			request.setAttribute("accountMessage", "<lable style='color:red;font-size:14px;'>There is some error in uploading User Form.</lable>");
        	try
        	{
        		view.forward(request, response);
        	}
        	catch(Exception ex2)
        	{
        		
        	}
			
		}
		    return null;    
            	
			}
	
	public void uploadUserFormtoServer(HttpServletRequest request,     // this function upload the file on server with the name USERFORM.xls
			HttpServletResponse response)throws Exception
			{
		  try 
	        {
			  
	    	UserFormBean bean = new UserFormBean();
	    	bean.setValue("LOC");
			client.update("userform.updateuplsta",bean);
			
		    } 
	    catch (SQLException e1)
		{	
			e1.printStackTrace();
		}
		//filePath = "D:/DonExcel/";
		filePath =DirPath();
		  isMultipart = ServletFileUpload.isMultipartContent(request);
	      response.setContentType("text/html");
	      java.io.PrintWriter out = response.getWriter( );
	      if( !isMultipart )
	      {
	         out.println("<html>");
	         out.println("<head>");
	         out.println("<title>Servlet upload</title>");  
	         out.println("</head>");
	         out.println("<body>");
	         out.println("<p>No file uploaded</p>"); 
	         out.println("</body>");
	         out.println("</html>");
	         return;
	      }
	      DiskFileItemFactory factory = new DiskFileItemFactory();
	      factory.setSizeThreshold(maxMemSize);
	      //factory.setRepository(new File("c:\\temp"));
	    //  factory.setRepository(new File("D:\\DonExcel"));
	      factory.setRepository(new File(DirPath()));
	      ServletFileUpload upload = new ServletFileUpload(factory);
	      upload.setSizeMax( maxFileSize );
	      
	      try
	      {
	    	  List fileItems = upload.parseRequest(request);
	    	  System.out.println("dd"+fileItems.get(0));
	    	  Iterator i = fileItems.iterator();
	    	  
	    	  out.println("<html>");
	          out.println("<head>");
	          out.println("<title>Servlet upload</title>");  
	          out.println("</head>");
	          out.println("<body>");
	          while ( i.hasNext () )
	          {
	        	  FileItem fi = (FileItem)i.next();
	        	  if ( !fi.isFormField () )
	        	  {
	        		  String fieldName = fi.getFieldName();
	                  String fileName = fi.getName();
	                  int applicationnumber=0;
	                  String filename ="USERFORM";
	                  fileName=filename+".xls";
	                  String contentType = fi.getContentType();
	                  boolean isInMemory = fi.isInMemory();
	                  long sizeInBytes = fi.getSize();
	                  System.out.println("index of .xls"+fileName.lastIndexOf(".xls"));
	                  if(fileName.lastIndexOf(".xls")>0)
	                  {
	                	  if( fileName.lastIndexOf("\\") >= 0 ){
		                      file = new File( filePath + File.separator+
		                      fileName.substring( fileName.lastIndexOf("\\"))) ;
		                   }else{
		                      file = new File( filePath + File.separator+
		                      fileName.substring(fileName.lastIndexOf("\\")+1)) ;
		                   }
		                   fi.write( file ) ;
		                   
		                  // out.println("Uploaded Filename: " + fileName + "<br>"); 
	                  }
	                  else
	                  {
	                	  out.println("Incorrect File Format: " + fileName + "<br>");
	                  }
	        	  }
	          }
	          out.println("</body>");
	          out.println("</html>");
	      }
	      catch (Exception e)
	      {
	    	  System.out.println(e); 
	      }
		
			}
	
	private String chaeckfile() //this method check the sequence number and status and validate the file
	{
		Workbook workbook;
		String flag="";
		try
		{
			String path =DirPath();
			workbook = Workbook.getWorkbook(new File(path+File.separator+"USERFORM.xls"));
			System.out.println("file path"+path+File.separator+"USERFORM.xls");
			//workbook = Workbook.getWorkbook(new File("D:/DonExcel/"+"USERFORM.xls"));
			Sheet sheet = workbook.getSheet("Sheet4"); 
			Sheet sheet1 = workbook.getSheet("Sheet1"); 
			String seqno= sheet.getCell(0,1).getContents();
			String subvalue= sheet1.getCell(0,100).getContents();
			UserFormBean bean = new UserFormBean();
			bean.setSequenceNumber(seqno);
			UserFormBean SQ = (UserFormBean) client.queryForObject("userform.checkexcelfile",bean);
			System.out.println(SQ.getUpload());
			System.out.println(SQ.getDownload());
			
			if ((SQ.getUpload().equalsIgnoreCase("N") )&&(SQ.getDownload().equalsIgnoreCase("Y")) && (subvalue.equalsIgnoreCase("YES")))
			{
				flag="yes";
				client.update("userform.update_userform_download",bean);
			}
			else
			{
				flag="no";
			}
			System.out.println("flag-"+flag);
		}
		catch(Exception e)
		{
			
		}
		return flag;
	}
	
	private String UserFormExcel(String seqno)
	{
		Workbook workbook;
		//File exlFile = new File("D:/excel_test/FinalUserForm.xls");
		String appnum="";
		
		try {
			String path = DirPath();
			workbook = Workbook.getWorkbook(new File(path+File.separator+seqno+".xls"));
			Sheet sheet = workbook.getSheet("Sheet1"); 
			UserFormBean formbean = new UserFormBean();
			List<UserFormBean> UF= new ArrayList<UserFormBean>();
			List <String> paperoption =new ArrayList<String>();
			List <String> paperoption2 =new ArrayList<String>();
			List <String> paperoption3 =new ArrayList<String>();
			List <String> paperoption4 =new ArrayList<String>();
			List <String> paperoption5 =new ArrayList<String>();
			try {
				UF = client.queryForList("userform.getuserformlocations");
				for (int i=0; i<UF.size(); i++)
				{
					if (UF.get(i).getTitle().equalsIgnoreCase("candidateName"))
					{
						System.out.println("col"+UF.get(i).getCol());
						System.out.println("row"+UF.get(i).getRow());
						formbean.setCandidateName(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());	
					}	
					else if (UF.get(i).getTitle().equalsIgnoreCase("programType"))
					{
						formbean.setProgramType(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
					}
					else if (UF.get(i).getTitle().equalsIgnoreCase("paperGroup"))
					{
						formbean.setPaperGroup(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
						
						formbean.setPaperGroup2(sheet.getCell(UF.get(i).getCol(), 21).getContents());
						formbean.setPaperGroup3(sheet.getCell(UF.get(i).getCol(), 26).getContents());
						formbean.setPaperGroup4(sheet.getCell(UF.get(i).getCol(), 31).getContents());
						formbean.setPaperGroup5(sheet.getCell(UF.get(i).getCol(), 36).getContents());
					}
					else if (UF.get(i).getTitle().equalsIgnoreCase("paperOption"))
					{
						
						for(int j=1;j<5;j++)
						{
							paperoption.add(sheet.getCell(UF.get(i).getCol(), j).getContents());
						}
						for (int j1=21;j1<25;j1++)
						{
							paperoption2.add(sheet.getCell(UF.get(i).getCol(), j1).getContents());
							//System.out.println("as2-"+sheet.getCell(UF.get(i).getCol(), j1).getContents());
						}
						
						for (int j1=26;j1<30;j1++)
						{
							paperoption3.add(sheet.getCell(UF.get(i).getCol(), j1).getContents());
							//System.out.println("as3-"+sheet.getCell(UF.get(i).getCol(), j1).getContents());
						}
						
						for (int j1=31;j1<35;j1++)
						{
							paperoption4.add(sheet.getCell(UF.get(i).getCol(), j1).getContents());
							//System.out.println("as4-"+sheet.getCell(UF.get(i).getCol(), j1).getContents());
						}
						
						for (int j1=36;j1<40;j1++)
						{
							paperoption5.add(sheet.getCell(UF.get(i).getCol(), j1).getContents());
							//System.out.println("as5-"+sheet.getCell(UF.get(i).getCol(), j1).getContents());
						}
						//formbean.setPaperOption(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
					}
					else if (UF.get(i).getTitle().equalsIgnoreCase("programName"))
					{
						formbean.setProgramName(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
						formbean.setProgramName2(sheet.getCell(UF.get(i).getCol(), 21).getContents());
						formbean.setProgramName3(sheet.getCell(UF.get(i).getCol(), 26).getContents());
						formbean.setProgramName4(sheet.getCell(UF.get(i).getCol(), 31).getContents());
						formbean.setProgramName5(sheet.getCell(UF.get(i).getCol(), 36).getContents());
					}
					else if (UF.get(i).getTitle().equalsIgnoreCase("fatherName"))
					{
						formbean.setFatherName(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
					}
					else if (UF.get(i).getTitle().equalsIgnoreCase("MotherName"))
					{
						formbean.setMotherName(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
					}
					else if (UF.get(i).getTitle().equalsIgnoreCase("phoneNumber"))
					{
						formbean.setPhoneNumber(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
					}
					else if (UF.get(i).getTitle().equalsIgnoreCase("category"))
					{
						String cat=sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents();
						
						formbean.setCos(cat+"XX");
						formbean.setCategory(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
					}
					else if (UF.get(i).getTitle().equalsIgnoreCase("addressline1"))
					{
						formbean.setAddressline1(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
					}
					else if (UF.get(i).getTitle().equalsIgnoreCase("addressline2"))
					{
						formbean.setAddressline2(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
					}
					else if (UF.get(i).getTitle().equalsIgnoreCase("state"))
					{
						formbean.setState(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
					}
					else if (UF.get(i).getTitle().equalsIgnoreCase("city"))
					{
						formbean.setCity(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
					}
					else if (UF.get(i).getTitle().equalsIgnoreCase("staff_status"))
					{
						String staffStatus=sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents();
						if (staffStatus.equalsIgnoreCase("YES"))
						{
							formbean.setStaff_status("Y");
						}
						else
						{
							formbean.setStaff_status("N");
						}
						//formbean.setStaff_status(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
					}
					else if (UF.get(i).getTitle().equalsIgnoreCase("staff_code"))
					{
						formbean.setStaff_code(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
					}
					else if (UF.get(i).getTitle().equalsIgnoreCase("hostel"))
					{
						String hostel=sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents();
						if (hostel.equalsIgnoreCase("YES"))
						{
							formbean.setHostel("Y");
						}
						else
						{
							formbean.setHostel("N");
						}
						//formbean.setHostel(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
					}
					else if (UF.get(i).getTitle().equalsIgnoreCase("cca"))
					{
						formbean.setCca(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
					}
					else if (UF.get(i).getTitle().equalsIgnoreCase("phyhandicaped"))
					{
						String phyHandicaped = sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents();
						if(phyHandicaped.equalsIgnoreCase("YES"))
						{
							formbean.setPhyhandicaped("Y");
						}
						else
						{
							formbean.setPhyhandicaped("N");
						}
						//formbean.setPhyhandicaped(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
					}
					else if (UF.get(i).getTitle().equalsIgnoreCase("email_id"))
					{
						formbean.setEmail_id(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
					}
					else if (UF.get(i).getTitle().equalsIgnoreCase("gender"))
					{
						String gender =sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents();
						if (gender.equalsIgnoreCase("Male"))
						{
							formbean.setGender("M");
						}
						else if (gender.equalsIgnoreCase("Female"))
						{
							formbean.setGender("F");
						}
						else
						{
							formbean.setGender("M");
						}
						//formbean.setGender(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
					}
					else if (UF.get(i).getTitle().equalsIgnoreCase("religion"))
					{
						formbean.setReligion(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
					}
					else if (UF.get(i).getTitle().equalsIgnoreCase("minority"))
					{
						String minority=sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents();
						if (minority.equalsIgnoreCase("YES"))
						{
							formbean.setMinority("Y");
						}
						else
						{
							formbean.setMinority("N");
						}
						//formbean.setMinority(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
					}
					else if (UF.get(i).getTitle().equalsIgnoreCase("meritalstatus"))
					{
						String marital= sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents();
						if (marital.equalsIgnoreCase("Unmarried"))
						{
							formbean.setMeritalstatus("UM");
						}
						else if (marital.equalsIgnoreCase("Married"))
						{
							formbean.setMeritalstatus("MA");
						}
						//formbean.setMeritalstatus(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
					}
					else if (UF.get(i).getTitle().equalsIgnoreCase("dob"))
					{
						formbean.setDob(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
						//formbean.setDob("1987-06-21");
					}
					else if (UF.get(i).getTitle().equalsIgnoreCase("fatherincome"))
					{
						formbean.setFatherincome(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
					}
					else if (UF.get(i).getTitle().equalsIgnoreCase("motherincome"))
					{
						formbean.setMotherincome(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
					}
					else if (UF.get(i).getTitle().equalsIgnoreCase("gardianincome"))
					{
						formbean.setGardianincome(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
					}
					else if (UF.get(i).getTitle().equalsIgnoreCase("examCenter"))
					{
						formbean.setExamCenter(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
					}
					else if (UF.get(i).getTitle().equalsIgnoreCase("nationality"))
					{
						formbean.setNationality(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
					}
					else if (UF.get(i).getTitle().equalsIgnoreCase("pincode"))
					{
						formbean.setPincode(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
					}
					else if (UF.get(i).getTitle().equalsIgnoreCase("sessionStartDate"))
					{
						formbean.setSessionStartDate(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
						formbean.setStartdate(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents()+"-07-01");
						
					}
					else if (UF.get(i).getTitle().equalsIgnoreCase("sessionEndDate"))
					{
						formbean.setSessionEndDate(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
						formbean.setEnddate(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents()+"-06-30");
					}
					else if (UF.get(i).getTitle().equalsIgnoreCase("UserId"))
					{
						formbean.setUserId(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
					}
					else if(UF.get(i).getTitle().equalsIgnoreCase("country"))
					{
						formbean.setCountry(sheet.getCell(UF.get(i).getCol(), UF.get(i).getRow()).getContents());
					}
					
				}
				
				formbean.setFirstpapge11(sheet.getCell(0, 50).getContents());
				formbean.setFirstpapge12(sheet.getCell(0, 51).getContents());
				
				formbean.setSecondpapge11(sheet.getCell(1, 50).getContents());
				formbean.setSecondpapge12(sheet.getCell(1, 51).getContents());
				
				formbean.setThirdpapge11(sheet.getCell(2, 50).getContents());
				formbean.setPersonalInfo32(sheet.getCell(2, 51).getContents());
				formbean.setPersonalInfo41(sheet.getCell(3, 50).getContents());
				
				System.out.println(" i am here"+formbean.getSecondpapge11());
	            formbean.setUniversityId("0001");
				int appNumber=0;
		        appNumber = Integer.parseInt(seqno);
		        String applicationNumber=padZero(appNumber, 5)+"";
		        UserFormBean session = (UserFormBean) client.queryForObject("userform.getsession");
		        applicationNumber="2"+applicationNumber;
		        formbean.setAppnumber(applicationNumber);
		        System.out.println("applicationNumber"+applicationNumber);
		       
		        
		        formbean.setUgpg("USRSTU");
            	formbean.setStudentId(getStudentId(formbean));
            	System.out.println("getStudentId"+formbean.getStudentId());
		        
            	  if (formbean.getEmail_id().equalsIgnoreCase("0"))
    	          {
    	        	  formbean.setEmail_id(applicationNumber);
    	          }
				
                  String pass= generatePassword();
    	          formbean.setPassword(pass);
    	          System.out.println("yaha aa gaya");
            	  
    	          if(formbean.getExamCenter().equalsIgnoreCase("0"))
    	          {
    	        	  formbean.setExamCenter("AG");
    	          }
    	          else
    	          {
    	        	  UserFormBean EXAMCEN = (UserFormBean) client.queryForObject("userform.getExamCenterfortest",formbean);
    	        	  formbean.setExamCenter(EXAMCEN.getExamCenter());
    	          }
    	          
				if (formbean.getFirstpapge11().equalsIgnoreCase("Y"))
				{
					System.out.println("first");
					formbean.setUniversityId("0001");
					formbean.setUgpg("USRREG");	
					formbean=getregistrationnumber(formbean);
					formbean.setRegistrationNumber("U"+formbean.getRegistration_number().get(0));
				
	  	            UserFormBean programfee = (UserFormBean) client.queryForObject("userform.getProgramFee",formbean);
		            if (programfee.getFee().equalsIgnoreCase(""))
		            {
		            	formbean.setFee("0");
		            } 
	            	  formbean.setFee(programfee.getFee()); 
	            	  UserFormBean program_code = (UserFormBean) client.queryForObject("userform.getProgramCode",formbean);
	            	  System.out.println("get app"+formbean.getAppnumber());
	            	  System.out.println("get reg"+formbean.getRegistrationNumber());
	            	  System.out.println("get std"+formbean.getStudentId());
	            	  System.out.println("get fee"+formbean.getFee());
	            	  System.out.println("get program_code"+program_code.getProgram_code());

	       	       try 
	    	       {
	           	    client.startTransaction();  
	           	    client.insert("userform.setApplicantAccountInfo",formbean);
	                client.insert("userform.setEntityStudentUserform", formbean);
	                client.insert("userform.setStudentAddressUserForm", formbean);
	                client.insert("userform.setStudentRegistrationUserForm", formbean);
	                //String entity=formbean.getStudentId().substring(1, 9);
	                //formbean.setEntityId(entity);
	                UserFormBean getentity = (UserFormBean) client.queryForObject("userform.getentityid",formbean);
	                formbean.setEntityId(getentity.getEntityId());
	                for(int p=0; p<paperoption.size();p++)
	                {
	                	if (!(paperoption.get(p).equalsIgnoreCase("0")))
	                	{
	                		System.out.println("paperOption-"+paperoption.get(p));
	                		
	                		formbean.setPaperDis(paperoption.get(p));

	                		UserFormBean p1 = (UserFormBean)client.queryForObject("userform.getpapergroupUserForm",formbean);
	                		if (p1!=null)
	                		{
	                			formbean.setGrouping(p1.getGrouping());
	                			formbean.setMainGroup(p1.getMainGroup());
	                			formbean.setPapercode(p1.getPapercode());
	                			client.insert("userform.setStudentPaperUserForm", formbean);			
	                		}
	           	        }

	                }
	                System.out.println("yaha 01");
	                client.insert("userform.setStudentApplicationStatusUserForm",formbean);
	                System.out.println("yaha 02");
	                client.insert("userform.setApplicantProgramRegistrationUserForm",formbean);
	                System.out.println("yaha 03");
	                client.insert("userform.setStudenttestNumberUserForm",formbean);
	                System.out.println("yaha 04");
	                
	                client.commitTransaction();
	                client.endTransaction(); 
	    	       }
	    	       catch (Exception e)
	    	       {
	    	    	   client.endTransaction();
	    	    	   System.out.println("Error in data Extraction"+e);
	    	    	   UserFormBean bean = new UserFormBean();
	     	    	   bean.setValue("UNL");
	     	    	  client.update("userform.updateuplsta",bean);
	    	       }
	     
	            	  
	            	  
				}
				if (formbean.getFirstpapge12().equalsIgnoreCase("Y"))
				{
					System.out.println("second");
					formbean.setUniversityId("0001");
					formbean.setUgpg("USRREG");	
					formbean=getregistrationnumber(formbean);
					formbean.setRegistrationNumber("U"+formbean.getRegistration_number().get(0));
					formbean.setProgramName(formbean.getProgramName2());
					formbean.setPaperGroup(formbean.getPaperGroup2());
	  	            UserFormBean programfee = (UserFormBean) client.queryForObject("userform.getProgramFee",formbean);
		            if (programfee.getFee().equalsIgnoreCase(""))
		            {
		            	formbean.setFee("0");
		            } 
	            	  formbean.setFee(programfee.getFee()); 
	            	  UserFormBean program_code = (UserFormBean) client.queryForObject("userform.getProgramCode",formbean);
	            	  System.out.println("get app"+formbean.getAppnumber());
	            	  System.out.println("get reg"+formbean.getRegistrationNumber());
	            	  System.out.println("get std"+formbean.getStudentId());
	            	  System.out.println("get fee"+formbean.getFee());
	            	  System.out.println("get program_code"+program_code.getProgram_code());
	            	  
	            	  
	       	       try 
	    	       {
	           	    client.startTransaction();  
	      
	                client.insert("userform.setStudentRegistrationUserForm", formbean);
	                //String entity=formbean.getStudentId().substring(1, 9);
	                //formbean.setEntityId(entity);
	                UserFormBean getentity = (UserFormBean) client.queryForObject("userform.getentityid",formbean);
	                formbean.setEntityId(getentity.getEntityId());
	                for(int p=0; p<paperoption2.size();p++)
	                {
	                	if (!(paperoption2.get(p).equalsIgnoreCase("0")))
	                	{
	                		System.out.println("paperOption-"+paperoption2.get(p));
	                		
	                		formbean.setPaperDis(paperoption2.get(p));
	  
	                		UserFormBean p1 = (UserFormBean)client.queryForObject("userform.getpapergroupUserForm",formbean);
	                		if (p1!=null)
	                		{
	                			formbean.setGrouping(p1.getGrouping());
	                			formbean.setMainGroup(p1.getMainGroup());
	                			formbean.setPapercode(p1.getPapercode());
	                			client.insert("userform.setStudentPaperUserForm", formbean);			
	                		}
	           	        }

	                }
	                System.out.println("yaha 01");
	            
	                System.out.println("yaha 02");
	                client.insert("userform.setApplicantProgramRegistrationUserForm",formbean);
	                System.out.println("yaha 03");
	                client.insert("userform.setStudenttestNumberUserForm",formbean);
	                System.out.println("yaha 04");

	                client.commitTransaction();
	                client.endTransaction(); 
	    	       }
	    	       catch (Exception e)
	    	       {
	    	    	   client.endTransaction();
	    	    	   System.out.println("Error in data Extraction"+e);
	    	    	   UserFormBean bean = new UserFormBean();
	     	    	   bean.setValue("UNL");
	     	    	  client.update("userform.updateuplsta",bean);
	    	       }
	     
				}
				if (formbean.getSecondpapge11().equalsIgnoreCase("Y"))
				{
					System.out.println("third");
					
					formbean.setUniversityId("0001");
					formbean.setUgpg("USRREG");	
					formbean=getregistrationnumber(formbean);
					formbean.setProgramName(formbean.getProgramName3());
					formbean.setPaperGroup(formbean.getPaperGroup3());
					formbean.setRegistrationNumber("U"+formbean.getRegistration_number().get(0));
	  	            UserFormBean programfee = (UserFormBean) client.queryForObject("userform.getProgramFee",formbean);
		            if (programfee.getFee().equalsIgnoreCase(""))
		            {
		            	formbean.setFee("0");
		            } 
	            	  formbean.setFee(programfee.getFee()); 
	            	  UserFormBean program_code = (UserFormBean) client.queryForObject("userform.getProgramCode",formbean);
	            	  System.out.println("get app"+formbean.getAppnumber());
	            	  System.out.println("get reg"+formbean.getRegistrationNumber());
	            	  System.out.println("get std"+formbean.getStudentId());
	            	  System.out.println("get fee"+formbean.getFee());
	            	  System.out.println("get program_code"+program_code.getProgram_code());
	            	  
	       	       try 
	    	       {
	           	    client.startTransaction();  

	                client.insert("userform.setStudentRegistrationUserForm", formbean);
	                //String entity=formbean.getStudentId().substring(1, 9);
	                //formbean.setEntityId(entity);
	                UserFormBean getentity = (UserFormBean) client.queryForObject("userform.getentityid",formbean);
	                formbean.setEntityId(getentity.getEntityId());
	                for(int p=0; p<paperoption3.size();p++)
	                {
	                	if (!(paperoption3.get(p).equalsIgnoreCase("0")))
	                	{
	                		System.out.println("paperOption-"+paperoption3.get(p));
	                		
	                		formbean.setPaperDis(paperoption3.get(p));

	                		UserFormBean p1 = (UserFormBean)client.queryForObject("userform.getpapergroupUserForm",formbean);
	                		if (p1!=null)
	                		{
	                			formbean.setGrouping(p1.getGrouping());
	                			formbean.setMainGroup(p1.getMainGroup());
	                			formbean.setPapercode(p1.getPapercode());
	                			client.insert("userform.setStudentPaperUserForm", formbean);			
	                		}
	           	        }

	                }
	                System.out.println("yaha 01");

	                System.out.println("yaha 02");
	                client.insert("userform.setApplicantProgramRegistrationUserForm",formbean);
	                System.out.println("yaha 03");
	                client.insert("userform.setStudenttestNumberUserForm",formbean);
	                System.out.println("yaha 04");
	       
	                client.commitTransaction();
	                client.endTransaction(); 
	    	       }
	    	       catch (Exception e)
	    	       {
	    	    	   client.endTransaction();
	    	    	   System.out.println("Error in data Extraction"+e);
	    	    	   UserFormBean bean = new UserFormBean();
	     	    	   bean.setValue("UNL");
	     	    	  client.update("userform.updateuplsta",bean);
	    	       }
	     
				}
				if (formbean.getSecondpapge12().equalsIgnoreCase("Y"))
				{
					System.out.println("forth");
					
					formbean.setUniversityId("0001");
					formbean.setUgpg("USRREG");	
					formbean=getregistrationnumber(formbean);
					formbean.setRegistrationNumber("U"+formbean.getRegistration_number().get(0));
					formbean.setProgramName(formbean.getProgramName4());
					formbean.setPaperGroup(formbean.getPaperGroup4());
	  	            UserFormBean programfee = (UserFormBean) client.queryForObject("userform.getProgramFee",formbean);
		            if (programfee.getFee().equalsIgnoreCase(""))
		            {
		            	formbean.setFee("0");
		            } 
	            	  formbean.setFee(programfee.getFee()); 
	            	  UserFormBean program_code = (UserFormBean) client.queryForObject("userform.getProgramCode",formbean);
	            	  System.out.println("get app"+formbean.getAppnumber());
	            	  System.out.println("get reg"+formbean.getRegistrationNumber());
	            	  System.out.println("get std"+formbean.getStudentId());
	            	  System.out.println("get fee"+formbean.getFee());
	            	  System.out.println("get program_code"+program_code.getProgram_code());
	            	  
	            	   try 
		    	       {
		           	    client.startTransaction();  
		  
		                client.insert("userform.setStudentRegistrationUserForm", formbean);
		               // String entity=formbean.getStudentId().substring(1, 9);
		               // formbean.setEntityId(entity);
		                UserFormBean getentity = (UserFormBean) client.queryForObject("userform.getentityid",formbean);
		                formbean.setEntityId(getentity.getEntityId());
		                for(int p=0; p<paperoption4.size();p++)
		                {
		                	if (!(paperoption4.get(p).equalsIgnoreCase("0")))
		                	{
		                		System.out.println("paperOption-"+paperoption4.get(p));
		                		
		                		formbean.setPaperDis(paperoption4.get(p));
		                		
		                		UserFormBean p1 = (UserFormBean)client.queryForObject("userform.getpapergroupUserForm",formbean);
		                		if (p1!=null)
		                		{
		                			formbean.setGrouping(p1.getGrouping());
		                			formbean.setMainGroup(p1.getMainGroup());
		                			formbean.setPapercode(p1.getPapercode());
		                			client.insert("userform.setStudentPaperUserForm", formbean);			
		                		}
		           	        }

		                }
		                System.out.println("yaha 01");
		              
		                System.out.println("yaha 02");
		                client.insert("userform.setApplicantProgramRegistrationUserForm",formbean);
		                System.out.println("yaha 03");
		                client.insert("userform.setStudenttestNumberUserForm",formbean);
		                System.out.println("yaha 04");
		         
		                client.commitTransaction();
		                client.endTransaction(); 
		    	       }
		    	       catch (Exception e)
		    	       {
		    	    	   client.endTransaction();
		    	    	   System.out.println("Error in data Extraction"+e);
		    	    	   UserFormBean bean = new UserFormBean();
		     	    	   bean.setValue("UNL");
		     	    	  client.update("userform.updateuplsta",bean);
		    	       }
				}
				if (formbean.getThirdpapge11().equalsIgnoreCase("Y"))
				{
					System.out.println("fifth");
					System.out.println("second");
					formbean.setUniversityId("0001");
					formbean.setUgpg("USRREG");	
					formbean=getregistrationnumber(formbean);
					formbean.setRegistrationNumber("U"+formbean.getRegistration_number().get(0));
					formbean.setProgramName(formbean.getProgramName5());
					formbean.setPaperGroup(formbean.getPaperGroup5());
	  	            UserFormBean programfee = (UserFormBean) client.queryForObject("userform.getProgramFee",formbean);
		            if (programfee.getFee().equalsIgnoreCase(""))
		            {
		            	formbean.setFee("0");
		            } 
	            	  formbean.setFee(programfee.getFee()); 
	            	  UserFormBean program_code = (UserFormBean) client.queryForObject("userform.getProgramCode",formbean);
	            	  System.out.println("get app"+formbean.getAppnumber());
	            	  System.out.println("get reg"+formbean.getRegistrationNumber());
	            	  System.out.println("get std"+formbean.getStudentId());
	            	  System.out.println("get fee"+formbean.getFee());
	            	  System.out.println("get program_code"+program_code.getProgram_code());
	            	  
	            	   try 
		    	       {
		           	    client.startTransaction();  
		                client.insert("userform.setStudentRegistrationUserForm", formbean);
		               // String entity=formbean.getStudentId().substring(1, 9);
		                //formbean.setEntityId(entity);
		                UserFormBean getentity = (UserFormBean) client.queryForObject("userform.getentityid",formbean);
		                formbean.setEntityId(getentity.getEntityId());
		                for(int p=0; p<paperoption5.size();p++)
		                {
		                	if (!(paperoption5.get(p).equalsIgnoreCase("0")))
		                	{
		                		System.out.println("paperOption-"+paperoption5.get(p));
		                		
		                		formbean.setPaperDis(paperoption5.get(p));
		 
		                		UserFormBean p1 = (UserFormBean)client.queryForObject("userform.getpapergroupUserForm",formbean);
		                		if (p1!=null)
		                		{
		                			formbean.setGrouping(p1.getGrouping());
		                			formbean.setMainGroup(p1.getMainGroup());
		                			formbean.setPapercode(p1.getPapercode());
		                			client.insert("userform.setStudentPaperUserForm", formbean);			
		                		}
		           	        }

		                }
		                System.out.println("yaha 01");
		             
		                System.out.println("yaha 02");
		                client.insert("userform.setApplicantProgramRegistrationUserForm",formbean);
		                System.out.println("yaha 03");
		                client.insert("userform.setStudenttestNumberUserForm",formbean);
		                System.out.println("yaha 04");
		             
		                client.commitTransaction();
		                client.endTransaction(); 
		    	       }
		    	       catch (Exception e)
		    	       {
		    	    	   client.endTransaction();
		    	    	   System.out.println("Error in data Extraction"+e);
		    	    	   UserFormBean bean = new UserFormBean();
		     	    	   bean.setValue("UNL");
		     	    	  client.update("userform.updateuplsta",bean);
		    	       }
				}

			


//	       try 
//	       {
//       	    client.startTransaction();  
//            client.insert("userform.setEntityStudentUserform", formbean);
//            client.insert("userform.setStudentAddressUserForm", formbean);
//            client.insert("userform.setStudentRegistrationUserForm", formbean);
//            String entity=formbean.getStudentId().substring(1, 9);
//            formbean.setEntityId(entity);
//            
//            for(int p=0; p<paperoption.size();p++)
//            {
//            	if (!(paperoption.get(p).equalsIgnoreCase("0")))
//            	{
//            		System.out.println("paperOption-"+paperoption.get(p));
//            		
//            		formbean.setPaperDis(paperoption.get(p));
//            		//UserFormBean p0 = (UserFormBean)client.queryForObject("userform.getPaperCodebyNameUserForm",formbean);
//            		//System.out.println(formbean.getProgramName()+"-"+formbean.getPapercode());
//            		UserFormBean p1 = (UserFormBean)client.queryForObject("userform.getpapergroupUserForm",formbean);
//            		if (p1!=null)
//            		{
//            			formbean.setGrouping(p1.getGrouping());
//            			formbean.setMainGroup(p1.getMainGroup());
//            			formbean.setPapercode(p1.getPapercode());
//            			client.insert("userform.setStudentPaperUserForm", formbean);			
//            		}
//       	        }
//
//            }
//            System.out.println("yaha 01");
//            client.insert("userform.setStudentApplicationStatusUserForm",formbean);
//            System.out.println("yaha 02");
//            client.insert("userform.setApplicantProgramRegistrationUserForm",formbean);
//            System.out.println("yaha 03");
//            client.insert("userform.setStudenttestNumberUserForm",formbean);
//            System.out.println("yaha 04");
//            client.insert("userform.setApplicantAccountInfo",formbean);
//            client.commitTransaction();
//            client.endTransaction(); 
//	       }
//	       catch (Exception e)
//	       {
//	    	   client.endTransaction();
//	    	   System.out.println("Error in data Extraction"+e);
//	    	   UserFormBean bean = new UserFormBean();
// 	    	   bean.setValue("UNL");
// 	    	  client.update("userform.updateuplsta",bean);
//	       }
// 
		            

				System.out.println("Registration Number--"+formbean.getRegistration_number().get(0));
				System.out.println("Application Number--"+applicationNumber);
				System.out.println("Student Id--"+formbean.getStudentId());
				System.out.println(formbean.getCategory());
				System.out.println("paperGroup-"+formbean.getPaperGroup());
				System.out.println(formbean.getSessionStartDate()+","+formbean.getSessionEndDate()+","+formbean.getUserId()+","+formbean.getUniversityId());
				System.out.println("programName-"+formbean.getProgramName());
				System.out.println("Name-"+formbean.getCandidateName());
				System.out.println("minority-"+formbean.getMinority());
				System.out.println("city-"+formbean.getCity());
				System.out.println("pincode-"+formbean.getPincode());
				rename(applicationNumber,seqno);
				formbean.getPassword();
				appnum=applicationNumber +","+"password-"+formbean.getPassword();
			
				
			    } 
			catch (SQLException e) 
			{	
				
				e.printStackTrace();
				   UserFormBean bean = new UserFormBean();
	 	    	   bean.setValue("UNL");
	 			   try {
					client.update("userform.updateuplsta",bean);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}

		} catch (BiffException e) {
			
			e.printStackTrace();
			   UserFormBean bean = new UserFormBean();
 	    	   bean.setValue("UNL");
 			   try {
				client.update("userform.updateuplsta",bean);
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		} catch (IOException e) {
			
			e.printStackTrace();
			   UserFormBean bean = new UserFormBean();
 	    	   bean.setValue("UNL");
 			   try {
				client.update("userform.updateuplsta",bean);
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
		return appnum;
		
	}
	private void rename(String newfile,String oldfile)
	{
		try
		{
			String path=DirPath();
		File oldName = new File(path+File.separator+oldfile+".xls");
	      File newName = new File(path+File.separator+newfile+".xls");
	      if(oldName.renameTo(newName)) {
	         System.out.println("renamed");
	      } else {
	         System.out.println("Error");
	      }
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
	private String getStudentId(UserFormBean inputbean)
	{
		String studentId = "";
		int seqNum=0;
		int response=0;
		try 
		{
			UserFormBean sysValueObj = (UserFormBean) client.queryForObject("userform.getSystemValues",inputbean);
			
			 if (sysValueObj != null) 
			    {
				    inputbean.setOldSequenceNumber(sysValueObj.getSequenceNumber());
	                seqNum = Integer.parseInt(sysValueObj.getSequenceNumber()) +1;
	                inputbean.setSequenceNumber(String.valueOf(seqNum));
	                System.out.println("ugpg studid---"+inputbean.getUgpg());
	                response=client.update("userform.updateSystemValuesUserForm", inputbean);
	            }
			 UserFormBean getentity = (UserFormBean) client.queryForObject("userform.getentityid",inputbean);
			 String year=new SimpleDateFormat("yyyy").format(new Date().getTime());
	         studentId = "U" + "00010013"+year + padZero(seqNum, 5);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return studentId;
		
	}
	private int getApplicationNumber(UserFormBean inputbean)
	{
		int applicationnumber=0;
		int response=0;
		int seqNum=1;
		try
		{
			while(response==0)
			{
				UserFormBean sysValueObj = (UserFormBean) client.queryForObject("userform.getSystemValues", inputbean);
				applicationnumber=Integer.parseInt(sysValueObj.getSequenceNumber())+1;
		        inputbean.setOldSequenceNumber(sysValueObj.getSequenceNumber());
		        inputbean.setSequenceNumber(String.valueOf(applicationnumber));
		        response=client.update("userform.updateSystemValuesUserForm", inputbean);
			}
			
		}
		catch (Exception e)
		{
			
		}
		
		return applicationnumber;
		
	}
	
	private UserFormBean getregistrationnumber(UserFormBean inputbean)
	{
		
		UserFormBean regno = new UserFormBean();
		int seqNum=1;
		int response=0;
		try 
		{
			String yy = (new java.sql.Timestamp(new java.util.Date().getTime())).toString().substring(2, 4);
	

			System.out.println("app"+inputbean.getUgpg());
			System.out.println("app"+inputbean.getUniversityId());

			UserFormBean sysValueObj = (UserFormBean) client.queryForObject("userform.getSystemValues", inputbean);
	            	
	        seqNum=Integer.parseInt(sysValueObj.getSequenceNumber())+1;
	        inputbean.setOldSequenceNumber(sysValueObj.getSequenceNumber());
	        inputbean.setSequenceNumber(String.valueOf(seqNum));
	        response=client.update("userform.updateSystemValuesUserForm", inputbean);
	
	        List<String> reg=new ArrayList<String>();
	        reg.add(yy+padZero(seqNum, 7));
	        inputbean.setRegistration_number(reg);
            System.out.println(yy+padZero(seqNum, 7));
           
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return inputbean;
		
	}
	  private String padZero(Integer number, int length) {
	        String output = String.valueOf(number);

	        while (output.length() < length) {
	            output = "0" + output;
	        }

	        return output;
	    }
	
	private String DirPath()
	{
		
		String filepath= getServletContext().getRealPath(File.separator)+"UserForm"+File.separator+"AdmissionSystem"+File.separator+"2014-2015"+File.separator+"ExcelFiles";
		String filepath2= getServletContext().getRealPath(File.separator)+"UserForm"+File.separator+"AdmissionSystem"+File.separator+"2014-2015"+File.separator+"PdfFiles";
		 System.out.println("path1"+filepath);
		 System.out.println("path1"+filepath2);
		boolean success = (new File(filepath)).mkdirs();
		    if (success)
		    {
		      System.out.println("Directories: " + filepath + " created");
		    }
		    else
		    {
		    	System.out.println("directory not created");
		    }
		    
		    
		    boolean success2 = (new File(filepath2)).mkdirs();
		    if (success2)
		    {
		      System.out.println("Directories: " + filepath2 + " created");
		    }
		    else
		    {
		    	System.out.println("directory not created");
		    }
		
		
		return filepath;
	}
	  
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
		//loggerObject.info("password :" + str);

		return str;
	}
	
	
	
}
