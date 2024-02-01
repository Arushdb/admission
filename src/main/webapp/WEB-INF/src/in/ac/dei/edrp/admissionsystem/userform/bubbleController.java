package in.ac.dei.edrp.admissionsystem.userform;

import in.ac.dei.edrp.admissionsystem.shared.SqlMapManager;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.ibatis.sqlmap.client.SqlMapClient;

public class bubbleController  extends MultiActionController

{
	SqlMapClient client = SqlMapManager.getSqlMapClient();
	List<String> courselist = new ArrayList<String>();
	String pasdob;
	String transfer_app;
	 private boolean isMultipart;
	   private String filePath;
	   private int maxFileSize = 1300 * 1024;
	   private int maxMemSize = 100 * 1024;
	  private File file ;
	  private String num1;
	  private String num2;
private userformDAO daoservicebubble;
	
	public void setDaoservicebubble(userformDAO daoservicebubble) 
	{
		this.daoservicebubble = daoservicebubble;
	}

	public ModelAndView method1(HttpServletRequest request,
			HttpServletResponse response)throws Exception
	{
	System.out.println("working");
	//BubbleFormExcel();
	 num1 = request.getParameter("rowstart");
	 num2 = request.getParameter("rowend");
	System.out.println("Row Start:-"+num1+"-"+"Row End:-"+num2);
	
	return null;
	}
	
	
	public ModelAndView method2(HttpServletRequest request,
			HttpServletResponse response)throws Exception
	{
	System.out.println("working2");
	uploadUserFormtoServer(request,response);
	
	BubbleFormExcel(num1,num2);
	//BubbleFormExcel();
	return null;
	}
	
	//uploadUserFormtoServer
	  private void BubbleFormExcel(String num1,String num2)
	  {
		  Workbook workbook;
		  try
		  {
			  String path =DirPath();
			  workbook = Workbook.getWorkbook(new File(path+File.separator+"BUBBLEFORM.xls"));
				Sheet sheet = workbook.getSheet("Sheet1"); 
				
				BubbleFormBean formbean = new BubbleFormBean();
				BubbleFormBean transferbean = new BubbleFormBean();
				String[] detailData =null;
				String[] detailData1 =null;
				BubbleFormBean papopt=new BubbleFormBean();
				List<BubbleFormBean> BF= new ArrayList<BubbleFormBean>();
				List <BubbleFormBean> paperoption1 =new ArrayList<BubbleFormBean>();
				List <String> COURSES =new ArrayList<String>();
			    List <String> Distinct =new ArrayList<String>();
			    List <String> pap =new ArrayList<String>();
			    List <String> pap2 =new ArrayList<String>();
				//ArrayList<String> Distinct = null;
				BF = client.queryForList("bubbleform.getuserformlocations");
				int nu1=Integer.parseInt(num1);
				int nu2=Integer.parseInt(num2);
				System.out.println("Bubble Value-"+"sv"+nu1+"-"+"Ev"+nu2);
				System.out.println("i am here"+BF.size());
				String dd="01";
				String mm="01";
				String yy="1999";
				for(int R=nu1;R<nu2;R++)
				{
					for (int C=0; C<BF.size(); C++)
					//for (int C=0; C<2; C++)	
						
					{
						
						//if (BF.get(C).getTitle().equalsIgnoreCase("NAME"))
					    if (BF.get(C).getTitle().equalsIgnoreCase("formnumber"))
						{
							//formbean.setCandidateName(sheet.getCell(BF.get(C).getCol(), R).getContents());
							formbean.setFormnumber(sheet.getCell(BF.get(C).getCol(), R).getContents());
							
							transfer_app= formbean.getFormnumber().substring(0, 1);
							if (transfer_app.equalsIgnoreCase("5"))
							{
								String app2 = formbean.getFormnumber().substring(1);
								formbean.setTransferapp("1"+app2);
								 transferbean = (BubbleFormBean)client.queryForObject("bubbleform.checkfortransferapp",formbean);
								 
							}
							
							//System.out.println("NAME:-"+sheet.getCell(BF.get(C).getCol(), R).getContents());
						}
						else if (BF.get(C).getTitle().equalsIgnoreCase("DATE"))
						{
							
							dd=sheet.getCell(BF.get(C).getCol(), R).getContents();
							//System.out.println("DATE:-"+sheet.getCell(BF.get(C).getCol(), R).getContents());
						}
						
						//else if (BF.get(C).getTitle().equalsIgnoreCase("formnumber"))
						else if (BF.get(C).getTitle().equalsIgnoreCase("NAME"))
						{
							//formbean.setFormnumber(sheet.getCell(BF.get(C).getCol(), R).getContents());
							if (transfer_app.equalsIgnoreCase("5"))
							{
								formbean.setCandidateName(transferbean.getCandidateName());
								formbean.setFatherName(transferbean.getFatherName());
								formbean.setMotherName(transferbean.getMotherName());
								formbean.setEmail_id(transferbean.getEmail_id());
							}
							else
							{
							formbean.setCandidateName(sheet.getCell(BF.get(C).getCol(), R).getContents());
							}
						}
						else if (BF.get(C).getTitle().equalsIgnoreCase("MONTH"))
						{
							mm=sheet.getCell(BF.get(C).getCol(), R).getContents();
							
							//System.out.println("MONTH:-"+sheet.getCell(BF.get(C).getCol(), R).getContents());
						}
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("MOBILE_NUMBER"))
						{
							if (transfer_app.equalsIgnoreCase("5"))
							{
								formbean.setPhoneNumber(transferbean.getPhoneNumber());
							}
							else
							{
							formbean.setPhoneNumber(sheet.getCell(BF.get(C).getCol(), R).getContents());
							}
							
							//System.out.println("MONTH:-"+sheet.getCell(BF.get(C).getCol(), R).getContents());
						}
						
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("YEAR"))
						{
							
							if (transfer_app.equalsIgnoreCase("5"))
							{
								formbean.setDob(transferbean.getDob());
								pasdob=transferbean.getDob().substring(0, 4);
							}
							else
							{
							
							yy=sheet.getCell(BF.get(C).getCol(), R).getContents();
							formbean.setDob("19"+yy+"-"+mm+"-"+dd);
							pasdob=19+yy;
							System.out.println(formbean.getDob());
							}
							//System.out.println("YEAR:-"+sheet.getCell(BF.get(C).getCol(), R).getContents());
						}
						else if (BF.get(C).getTitle().equalsIgnoreCase("GENDER"))
						{
							if (transfer_app.equalsIgnoreCase("5"))
							{
								formbean.setGender(transferbean.getGender());
								//pasdob=transferbean.getDob().substring(0, 4);
							}
							else
							{
							
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								formbean.setGender("M");
								//System.out.println("MALE");
							}
							else
							{
								formbean.setGender("F");
								//System.out.println("FEMALE");
							}
							}
							
						}
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("CATEGORY"))
						{
							if (transfer_app.equalsIgnoreCase("5"))
							{
								formbean.setCosval(transferbean.getCategory());
								formbean.setCos(transferbean.getCategory()+"XX");
								//pasdob=transferbean.getDob().substring(0, 4);
							}
							
							else
							{
							
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								formbean.setCos("GN"+"XX");
								formbean.setCosval("GN");
								//System.out.println("GN");
							}
							else if ((sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("2")))
							{
								formbean.setCos("SC"+"XX");
								formbean.setCosval("SC");
								//System.out.println("SC");
							}
							else if ((sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("3")))
							{
								formbean.setCos("ST"+"XX");
								formbean.setCosval("ST");
								//System.out.println("ST");
							}
							else if ((sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("4")))
							{
								formbean.setCos("BC"+"XX");
								formbean.setCosval("BC");
								//System.out.println("BC");
							}
							System.out.println(formbean.getCos());
							}
						}
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("PHY_HAN"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								formbean.setPhyhandicaped("Y");
								//System.out.println("YES");
							}
							else
							{
								formbean.setPhyhandicaped("N");
								//System.out.println("NO");
							}
							
						}
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("STAFF_WARD"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								formbean.setStaff_status("Y");
								//System.out.println("YES");
							}
							else
							{
								formbean.setStaff_status("N");
							//	System.out.println("NO");
							}
							
						}
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("EXAM_CEN"))
						{
							if (transfer_app.equalsIgnoreCase("5"))
							{
								formbean.setExamCenter(transferbean.getExamCenter());
						
							}
							else
							{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								formbean.setExamCenter("AG");
								//System.out.println("DE");
							}
							else if ((sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("2")))
							{
								formbean.setExamCenter("ND");
								//System.out.println("ND");
							}
							else if ((sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("3")))
							{
								formbean.setExamCenter("AM");
								//System.out.println("AM");
							}
							else if ((sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("4")))
							{
								formbean.setExamCenter("MU");
							//	System.out.println("MU");
							}
							else if ((sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("5")))
							{
								formbean.setExamCenter("VI");
								//System.out.println("VI");
							}
							else if ((sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("6")))
							{
								formbean.setExamCenter("MT");
								//System.out.println("MT");
							}
							else if ((sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("7")))
							{
								formbean.setExamCenter("TI");
								//System.out.println("MT");
							}
							else if ((sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("8")))
							{
								formbean.setExamCenter("BE");
								//System.out.println("MT");
							}
							else 
							{
								formbean.setExamCenter("AG");
							}
							}
							
						}
						else if (BF.get(C).getTitle().equalsIgnoreCase("BA(UG DEGREE COURSE)"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								COURSES.add(BF.get(C).getProgram_id());
								System.out.println("Applied Program"+BF.get(C).getDescription());
								List<BubbleFormBean> BA= new ArrayList<BubbleFormBean>();
								BA = client.queryForList("bubbleform.BApaper");
								
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> BACOMP= new ArrayList<BubbleFormBean>();
								BACOMP = client.queryForList("bubbleform.BApapercomp");
								int count1=0;
								
								try
								{
									for (int p1=0;p1<BA.size();p1++)
									{
										if (BA.get(p1).getTitle().equalsIgnoreCase("DP"))
										{
											if (sheet.getCell(BA.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												
												pap2.add(BA.get(p1).getProgram_id()+BA.get(p1).getDescription());
												count1++;
											}
										}
										else if (BA.get(p1).getTitle().equalsIgnoreCase("EC"))
										{
											
											if (sheet.getCell(BA.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												
												pap2.add(BA.get(p1).getProgram_id()+BA.get(p1).getDescription());
												count1++;
											}
										}
										else if (BA.get(p1).getTitle().equalsIgnoreCase("EN"))
										{
									
											if (sheet.getCell(BA.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BA.get(p1).getProgram_id()+BA.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BA.get(p1).getTitle().equalsIgnoreCase("HC"))
										{
											
											if (sheet.getCell(BA.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BA.get(p1).getProgram_id()+BA.get(p1).getDescription());
												count1++;
											}
										}
										else if (BA.get(p1).getTitle().equalsIgnoreCase("HI"))
										{
											
											if (sheet.getCell(BA.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BA.get(p1).getProgram_id()+BA.get(p1).getDescription());
												count1++;
																							}
										}
										else if (BA.get(p1).getTitle().equalsIgnoreCase("HS"))
										{
											
											if (sheet.getCell(BA.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BA.get(p1).getProgram_id()+BA.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BA.get(p1).getTitle().equalsIgnoreCase("MA"))
										{
											
											if (sheet.getCell(BA.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BA.get(p1).getProgram_id()+BA.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BA.get(p1).getTitle().equalsIgnoreCase("MS"))
										{
											
											if (sheet.getCell(BA.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BA.get(p1).getProgram_id()+BA.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BA.get(p1).getTitle().equalsIgnoreCase("MT"))
										{
											
											if (sheet.getCell(BA.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BA.get(p1).getProgram_id()+BA.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BA.get(p1).getTitle().equalsIgnoreCase("PS"))
										{
											
											if (sheet.getCell(BA.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BA.get(p1).getProgram_id()+BA.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BA.get(p1).getTitle().equalsIgnoreCase("PY"))
										{
											
											if (sheet.getCell(BA.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BA.get(p1).getProgram_id()+BA.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BA.get(p1).getTitle().equalsIgnoreCase("SA"))
										{
											
											if (sheet.getCell(BA.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BA.get(p1).getProgram_id()+BA.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BA.get(p1).getTitle().equalsIgnoreCase("SO"))
										{
											
											if (sheet.getCell(BA.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BA.get(p1).getProgram_id()+BA.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BA.get(p1).getTitle().equalsIgnoreCase("GS"))
										{
											
											if (sheet.getCell(BA.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BA.get(p1).getProgram_id()+BA.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BA.get(p1).getTitle().equalsIgnoreCase("PH"))
										{
											
											if (sheet.getCell(BA.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BA.get(p1).getProgram_id()+BA.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BA.get(p1).getTitle().equalsIgnoreCase("ZO"))
										{
											
											if (sheet.getCell(BA.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BA.get(p1).getProgram_id()+BA.get(p1).getDescription());
												count1++;
											}
										}

									}
									
									for (int p1=0;p1<BACOMP.size();p1++)
									{
										if (BACOMP.get(p1).getTitle().equalsIgnoreCase("COMP"))
										{
											pap2.add(BACOMP.get(p1).getProgram_id()+BACOMP.get(p1).getDescription());
											count1++;
										}
										if (BACOMP.get(p1).getTitle().equalsIgnoreCase("COMP1"))
										{
											pap2.add(BACOMP.get(p1).getProgram_id()+BACOMP.get(p1).getDescription());
											count1++;
										}
										if (BACOMP.get(p1).getTitle().equalsIgnoreCase("COMP2"))
										{
											pap2.add(BACOMP.get(p1).getProgram_id()+BACOMP.get(p1).getDescription());
											count1++;
										}
										if (BACOMP.get(p1).getTitle().equalsIgnoreCase("COMP3"))
										{
											pap2.add(BACOMP.get(p1).getProgram_id()+BACOMP.get(p1).getDescription());
											count1++;
										}
									}
									System.out.println("Count in BA --"+count1+"--"+formbean.getCandidateName());
									System.out.println("program_id --"+formbean.getProgramName());
									
									BubbleFormBean BAPAP = (BubbleFormBean)client.queryForObject("bubbleform.papersum",formbean);
									int papcount = Integer.parseInt(BAPAP.getPapsum());
									if(count1<papcount)
									{
										int cc= count1-1;
										System.out.println("the paper options of BA Choosen is-"+cc+"-"+"But Actual Paper potions are--"+papcount);
									}
									else if(count1==papcount)
									{
										System.out.println("the paper options of BA Choosen is-"+count1+"-"+"AND avtual Paper potions are--"+papcount);
									}
									System.out.println("Count in BA --"+count1+"--"+formbean.getCandidateName());
									
									
								}
								catch (Exception e)
								{
									System.out.println("Error in BA:--"+e);
								}
								
								
								
							}
						}
						
						
						
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("B.A. (Social Science)"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								COURSES.add(BF.get(C).getProgram_id());
								System.out.println("Applied Program"+BF.get(C).getDescription());
								
								List<BubbleFormBean> BASOC= new ArrayList<BubbleFormBean>();
								BASOC = client.queryForList("bubbleform.BASocSci");
								
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> BASOCCOMP= new ArrayList<BubbleFormBean>();
								BASOCCOMP = client.queryForList("bubbleform.BASocScicomp",formbean);
								int count1=0;
								
								
								try
								{
									for (int p1=0;p1<BASOC.size();p1++)
									{
										if (BASOC.get(p1).getTitle().equalsIgnoreCase("DP"))
										{
											if (sheet.getCell(BASOC.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												
												pap2.add(BASOC.get(p1).getProgram_id()+BASOC.get(p1).getDescription());
												count1++;
											}
										}
										else if (BASOC.get(p1).getTitle().equalsIgnoreCase("EC"))
										{
											
											if (sheet.getCell(BASOC.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												
												pap2.add(BASOC.get(p1).getProgram_id()+BASOC.get(p1).getDescription());
												count1++;
											}
										}
										else if (BASOC.get(p1).getTitle().equalsIgnoreCase("EN"))
										{
									
											if (sheet.getCell(BASOC.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BASOC.get(p1).getProgram_id()+BASOC.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BASOC.get(p1).getTitle().equalsIgnoreCase("HC"))
										{
											
											if (sheet.getCell(BASOC.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BASOC.get(p1).getProgram_id()+BASOC.get(p1).getDescription());
												count1++;
											}
										}
										else if (BASOC.get(p1).getTitle().equalsIgnoreCase("HI"))
										{
											
											if (sheet.getCell(BASOC.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BASOC.get(p1).getProgram_id()+BASOC.get(p1).getDescription());
												count1++;
											}
										}
										else if (BASOC.get(p1).getTitle().equalsIgnoreCase("HS"))
										{
											
											if (sheet.getCell(BASOC.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BASOC.get(p1).getProgram_id()+BASOC.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BASOC.get(p1).getTitle().equalsIgnoreCase("MA"))
										{
											
											if (sheet.getCell(BASOC.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BASOC.get(p1).getProgram_id()+BASOC.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BASOC.get(p1).getTitle().equalsIgnoreCase("MS"))
										{
											
											if (sheet.getCell(BASOC.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BASOC.get(p1).getProgram_id()+BASOC.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BASOC.get(p1).getTitle().equalsIgnoreCase("MT"))
										{
											
											if (sheet.getCell(BASOC.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BASOC.get(p1).getProgram_id()+BASOC.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BASOC.get(p1).getTitle().equalsIgnoreCase("PS"))
										{
											
											if (sheet.getCell(BASOC.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BASOC.get(p1).getProgram_id()+BASOC.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BASOC.get(p1).getTitle().equalsIgnoreCase("PY"))
										{
											
											if (sheet.getCell(BASOC.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BASOC.get(p1).getProgram_id()+BASOC.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BASOC.get(p1).getTitle().equalsIgnoreCase("SA"))
										{
											
											if (sheet.getCell(BASOC.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BASOC.get(p1).getProgram_id()+BASOC.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BASOC.get(p1).getTitle().equalsIgnoreCase("SO"))
										{
											
											if (sheet.getCell(BASOC.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BASOC.get(p1).getProgram_id()+BASOC.get(p1).getDescription());
												count1++;
											}
										}
										
										
										else if (BASOC.get(p1).getTitle().equalsIgnoreCase("GS"))
										{
											
											if (sheet.getCell(BASOC.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BASOC.get(p1).getProgram_id()+BASOC.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BASOC.get(p1).getTitle().equalsIgnoreCase("PH"))
										{
											
											if (sheet.getCell(BASOC.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BASOC.get(p1).getProgram_id()+BASOC.get(p1).getDescription());
												count1++;
											}
										}
										else if (BASOC.get(p1).getTitle().equalsIgnoreCase("ZO"))
										{
											
											if (sheet.getCell(BASOC.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BASOC.get(p1).getProgram_id()+BASOC.get(p1).getDescription());
												count1++;
											}
										}

									}
								
									
									for (int p1=0;p1<BASOCCOMP.size();p1++)
									{
										if (BASOCCOMP.get(p1).getTitle().equalsIgnoreCase("COMP"))
										{
											pap2.add(BASOCCOMP.get(p1).getProgram_id()+BASOCCOMP.get(p1).getDescription());
											count1++;
										}
										if (BASOCCOMP.get(p1).getTitle().equalsIgnoreCase("COMP1"))
										{
											pap2.add(BASOCCOMP.get(p1).getProgram_id()+BASOCCOMP.get(p1).getDescription());
											count1++;
										}
										if (BASOCCOMP.get(p1).getTitle().equalsIgnoreCase("COMP2"))
										{
											pap2.add(BASOCCOMP.get(p1).getProgram_id()+BASOCCOMP.get(p1).getDescription());
											count1++;
										}
										if (BASOCCOMP.get(p1).getTitle().equalsIgnoreCase("COMP3"))
										{
											pap2.add(BASOCCOMP.get(p1).getProgram_id()+BASOCCOMP.get(p1).getDescription());
											count1++;
										}
									}
									
									
									
									BubbleFormBean BASOCPAP = (BubbleFormBean)client.queryForObject("bubbleform.papersum",formbean);
									int papcount = Integer.parseInt(BASOCPAP.getPapsum());
									if(count1<papcount)
									{
										int cc= count1-1;
										System.out.println("the paper options of BAsocSci Choosen is-"+cc+"-"+"But Actual Paper potions are--"+papcount);
									}
									else if(count1==papcount)
									{
										System.out.println("the paper options of BAsocSci Choosen is-"+count1+"-"+"AND avtual Paper potions are--"+papcount);
									}
									System.out.println("Count in BAsocSci --"+count1+"--"+formbean.getCandidateName());
									
									
								}
								catch (Exception e)
								{
									System.out.println("Error in  Socail Science:--"+e);
								}
								
								
							}
						}
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("B.B.M."))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								System.out.println("Applied Program"+BF.get(C).getDescription());
								COURSES.add(BF.get(C).getProgram_id());
								List<BubbleFormBean> BBM= new ArrayList<BubbleFormBean>();
								BBM = client.queryForList("bubbleform.BBMpaper");
								
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> BBMCOMP= new ArrayList<BubbleFormBean>();
								BBMCOMP = client.queryForList("bubbleform.BASocScicomp",formbean);
								int count1=0;
								
								try
								{
									for (int p1=0;p1<BBM.size();p1++)
									{
										if (BBM.get(p1).getTitle().equalsIgnoreCase("BG"))
										{
											if (sheet.getCell(BBM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												
												pap2.add(BBM.get(p1).getProgram_id()+BBM.get(p1).getDescription());
												count1++;
												
											}
										}
										else if (BBM.get(p1).getTitle().equalsIgnoreCase("BK"))
										{
											
											if (sheet.getCell(BBM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												
												pap2.add(BBM.get(p1).getProgram_id()+BBM.get(p1).getDescription());
												count1++;
											}
										}
										else if (BBM.get(p1).getTitle().equalsIgnoreCase("BO"))
										{
									
											if (sheet.getCell(BBM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BBM.get(p1).getProgram_id()+BBM.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BBM.get(p1).getTitle().equalsIgnoreCase("BT"))
										{
											
											if (sheet.getCell(BBM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BBM.get(p1).getProgram_id()+BBM.get(p1).getDescription());
												count1++;
											}
										}
										else if (BBM.get(p1).getTitle().equalsIgnoreCase("CH"))
										{
											
											if (sheet.getCell(BBM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BBM.get(p1).getProgram_id()+BBM.get(p1).getDescription());
												count1++;
											}
										}
										else if (BBM.get(p1).getTitle().equalsIgnoreCase("CM"))
										{
											
											if (sheet.getCell(BBM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BBM.get(p1).getProgram_id()+BBM.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BBM.get(p1).getTitle().equalsIgnoreCase("EC"))
										{
											
											if (sheet.getCell(BBM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BBM.get(p1).getProgram_id()+BBM.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BBM.get(p1).getTitle().equalsIgnoreCase("HI"))
										{
											
											if (sheet.getCell(BBM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BBM.get(p1).getProgram_id()+BBM.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BBM.get(p1).getTitle().equalsIgnoreCase("HS"))
										{
											
											if (sheet.getCell(BBM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BBM.get(p1).getProgram_id()+BBM.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BBM.get(p1).getTitle().equalsIgnoreCase("MA"))
										{
											
											if (sheet.getCell(BBM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BBM.get(p1).getProgram_id()+BBM.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BBM.get(p1).getTitle().equalsIgnoreCase("PH"))
										{
											
											if (sheet.getCell(BBM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BBM.get(p1).getProgram_id()+BBM.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BBM.get(p1).getTitle().equalsIgnoreCase("PS"))
										{
											
											if (sheet.getCell(BBM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BBM.get(p1).getProgram_id()+BBM.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BBM.get(p1).getTitle().equalsIgnoreCase("PY"))
										{
											
											if (sheet.getCell(BBM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BBM.get(p1).getProgram_id()+BBM.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BBM.get(p1).getTitle().equalsIgnoreCase("SA"))
										{
											
											if (sheet.getCell(BBM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BBM.get(p1).getProgram_id()+BBM.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BBM.get(p1).getTitle().equalsIgnoreCase("SO"))
										{
											
											if (sheet.getCell(BBM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BBM.get(p1).getProgram_id()+BBM.get(p1).getDescription());
												count1++;
											}
										}
										else if (BBM.get(p1).getTitle().equalsIgnoreCase("ZO"))
										{
											if (sheet.getCell(BBM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BBM.get(p1).getProgram_id()+BBM.get(p1).getDescription());
												count1++;
											}
										}
										
									}
									
									for (int p1=0;p1<BBMCOMP.size();p1++)
									{
										if (BBMCOMP.get(p1).getTitle().equalsIgnoreCase("COMP"))
										{
											pap2.add(BBMCOMP.get(p1).getProgram_id()+BBMCOMP.get(p1).getDescription());
											count1++;
										}
										if (BBMCOMP.get(p1).getTitle().equalsIgnoreCase("COMP1"))
										{
											pap2.add(BBMCOMP.get(p1).getProgram_id()+BBMCOMP.get(p1).getDescription());
											count1++;
										}
										if (BBMCOMP.get(p1).getTitle().equalsIgnoreCase("COMP2"))
										{
											pap2.add(BBMCOMP.get(p1).getProgram_id()+BBMCOMP.get(p1).getDescription());
											count1++;
										}
										if (BBMCOMP.get(p1).getTitle().equalsIgnoreCase("COMP3"))
										{
											pap2.add(BBMCOMP.get(p1).getProgram_id()+BBMCOMP.get(p1).getDescription());
											count1++;
										}
								}
									BubbleFormBean BBMPAP = (BubbleFormBean)client.queryForObject("bubbleform.papersum",formbean);
									int papcount = Integer.parseInt(BBMPAP.getPapsum());
									if(count1<papcount)
									{
										int cc= count1-1;
										System.out.println("the paper options of BBM Choosen is-"+cc+"-"+"But Actual Paper potions are--"+papcount);
									}
									else if(count1==papcount)
									{
										System.out.println("the paper options of BBM Choosen is-"+count1+"-"+"AND avtual Paper potions are--"+papcount);
									}
									System.out.println("Count in BA --"+count1+"--"+formbean.getCandidateName());
									
								}
								catch (Exception e)
								{
									System.out.println("Error in BBM:--"+e);
								}

							}
						}
						else if (BF.get(C).getTitle().equalsIgnoreCase("B.Com."))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								System.out.println("Applied Program"+BF.get(C).getDescription());
								COURSES.add(BF.get(C).getProgram_id());
								List<BubbleFormBean> BCOM= new ArrayList<BubbleFormBean>();
								BCOM = client.queryForList("bubbleform.BCOMpaper");

								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> BCOMCOMP= new ArrayList<BubbleFormBean>();
								BCOMCOMP = client.queryForList("bubbleform.BASocScicomp",formbean);
								int count1=0;
								
								try 
								{
									for (int p1=0;p1<BCOM.size();p1++)
									{
										if (BCOM.get(p1).getTitle().equalsIgnoreCase("BG"))
										{
											if (sheet.getCell(BCOM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BCOM.get(p1).getProgram_id()+BCOM.get(p1).getDescription());
												count1++;
												
											}
										}
										else if (BCOM.get(p1).getTitle().equalsIgnoreCase("CM"))
										{
											
											if (sheet.getCell(BCOM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BCOM.get(p1).getProgram_id()+BCOM.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BCOM.get(p1).getTitle().equalsIgnoreCase("HM"))
										{
											
											if (sheet.getCell(BCOM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BCOM.get(p1).getProgram_id()+BCOM.get(p1).getDescription());
												count1++;
											}
										}
										
									}
									
									for (int p1=0;p1<BCOMCOMP.size();p1++)
									{
										if (BCOMCOMP.get(p1).getTitle().equalsIgnoreCase("COMP"))
										{
											pap2.add(BCOMCOMP.get(p1).getProgram_id()+BCOMCOMP.get(p1).getDescription());
											count1++;
										}
										if (BCOMCOMP.get(p1).getTitle().equalsIgnoreCase("COMP1"))
										{
											pap2.add(BCOMCOMP.get(p1).getProgram_id()+BCOMCOMP.get(p1).getDescription());
											count1++;
										}
										if (BCOMCOMP.get(p1).getTitle().equalsIgnoreCase("COMP2"))
										{
											pap2.add(BCOMCOMP.get(p1).getProgram_id()+BCOMCOMP.get(p1).getDescription());
											count1++;
										}
										if (BCOMCOMP.get(p1).getTitle().equalsIgnoreCase("COMP3"))
										{
											pap2.add(BCOMCOMP.get(p1).getProgram_id()+BCOMCOMP.get(p1).getDescription());
											count1++;
										}
									}
									
									
									BubbleFormBean BCOMPAP = (BubbleFormBean)client.queryForObject("bubbleform.papersum",formbean);
									int papcount = Integer.parseInt(BCOMPAP.getPapsum());
									if(count1<papcount)
									{
										int cc= count1-1;
										System.out.println("the paper options of BCOM Choosen is-"+cc+"-"+"But Actual Paper potions are--"+papcount);
									}
									else if(count1==papcount)
									{
										System.out.println("the paper options of BCOM Choosen is-"+count1+"-"+"AND avtual Paper potions are--"+papcount);
									}
									System.out.println("Count in BCOM --"+count1+"--"+formbean.getCandidateName());
									
									

								}
								catch (Exception e)
								{
									System.out.println("Error in BCOM Paper"+e);
								}
							}
						}
						else if (BF.get(C).getTitle().equalsIgnoreCase("B.Sc."))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								System.out.println("Applied Program"+BF.get(C).getDescription());
								COURSES.add(BF.get(C).getProgram_id());
								List<BubbleFormBean> BSC= new ArrayList<BubbleFormBean>();
								BSC = client.queryForList("bubbleform.BSCpaper");
								
								
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> BSCCOMP= new ArrayList<BubbleFormBean>();
								BSCCOMP = client.queryForList("bubbleform.BASocScicomp",formbean);
								int count1=0;
								
								try
								{
									for (int p1=0;p1<BSC.size();p1++)
									{
										if (BSC.get(p1).getTitle().equalsIgnoreCase("BT"))
										{
											if (sheet.getCell(BSC.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BSC.get(p1).getProgram_id()+BSC.get(p1).getDescription());
												count1++;
											}
										}
										else if (BSC.get(p1).getTitle().equalsIgnoreCase("CH"))
										{
											
											if (sheet.getCell(BSC.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BSC.get(p1).getProgram_id()+BSC.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BSC.get(p1).getTitle().equalsIgnoreCase("EC"))
										{
											
											if (sheet.getCell(BSC.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BSC.get(p1).getProgram_id()+BSC.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BSC.get(p1).getTitle().equalsIgnoreCase("MA"))
										{
											
											if (sheet.getCell(BSC.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BSC.get(p1).getProgram_id()+BSC.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BSC.get(p1).getTitle().equalsIgnoreCase("PH"))
										{
											
											if (sheet.getCell(BSC.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BSC.get(p1).getProgram_id()+BSC.get(p1).getDescription());
												count1++;
											}
										}
										
										
										else if (BSC.get(p1).getTitle().equalsIgnoreCase("ZO"))
										{
											
											if (sheet.getCell(BSC.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BSC.get(p1).getProgram_id()+BSC.get(p1).getDescription());
												count1++;
											}
										}
										
									}
									
									for (int p1=0;p1<BSCCOMP.size();p1++)
									{
										if (BSCCOMP.get(p1).getTitle().equalsIgnoreCase("COMP"))
										{
											pap2.add(BSCCOMP.get(p1).getProgram_id()+BSCCOMP.get(p1).getDescription());
											count1++;
										}
										if (BSCCOMP.get(p1).getTitle().equalsIgnoreCase("COMP1"))
										{
											pap2.add(BSCCOMP.get(p1).getProgram_id()+BSCCOMP.get(p1).getDescription());
											count1++;
										}
										if (BSCCOMP.get(p1).getTitle().equalsIgnoreCase("COMP2"))
										{
											pap2.add(BSCCOMP.get(p1).getProgram_id()+BSCCOMP.get(p1).getDescription());
											count1++;
										}
										if (BSCCOMP.get(p1).getTitle().equalsIgnoreCase("COMP3"))
										{
											pap2.add(BSCCOMP.get(p1).getProgram_id()+BSCCOMP.get(p1).getDescription());
											count1++;
										}
									}
									
									BubbleFormBean BSCPAP = (BubbleFormBean)client.queryForObject("bubbleform.papersum",formbean);
									int papcount = Integer.parseInt(BSCPAP.getPapsum());
									if(count1<papcount)
									{
										int cc= count1-1;
										System.out.println("the paper options of BSC Choosen is-"+cc+"-"+"But Actual Paper potions are--"+papcount);
									}
									else if(count1==papcount)
									{
										System.out.println("the paper options of BSC Choosen is-"+count1+"-"+"AND avtual Paper potions are--"+papcount);
									}
									
									else if(count1>papcount)
									{
										System.out.println("the paper options of BSC Choosen is-"+count1+"-"+"BUT avtual Paper potions are--"+papcount);
									}
									System.out.println("Count in BSC --"+count1+"--"+formbean.getCandidateName());
									
									
									
								}
								catch (Exception e)
								{
									System.out.println("Error In B.Sc.");
								}
							}
						}
						else if (BF.get(C).getTitle().equalsIgnoreCase("B.Sc. Home Science"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								System.out.println("Applied Program"+BF.get(C).getDescription());
								COURSES.add(BF.get(C).getProgram_id());
								
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> BSCHSG1= new ArrayList<BubbleFormBean>();	
								List<BubbleFormBean> BSCHSG2= new ArrayList<BubbleFormBean>();
								List<BubbleFormBean> BSCHSG3= new ArrayList<BubbleFormBean>();
								BSCHSG1 = client.queryForList("bubbleform.GetOptionalPaperG1",formbean);
								
								BSCHSG2 = client.queryForList("bubbleform.GetOptionalPaperG2",formbean);
								
								BSCHSG3 = client.queryForList("bubbleform.GetOptionalPaperG3",formbean);
								
								List<BubbleFormBean> BSCHSCOMP= new ArrayList<BubbleFormBean>();
								BSCHSCOMP = client.queryForList("bubbleform.GetCompPaper",formbean);
								int count1=0;
								int count2=0;
								int count3=0;
								try
								{
									for (int p1=0;p1<BSCHSG1.size();p1++)
									{
										if (BSCHSG1.get(p1).getTitle().equalsIgnoreCase("EN"))
										{
											if (sheet.getCell(BSCHSG1.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BSCHSG1.get(p1).getProgram_id()+BSCHSG1.get(p1).getDescription());
												count1++;
											}
										}
										else if (BSCHSG1.get(p1).getTitle().equalsIgnoreCase("HI"))
										{
											
											if (sheet.getCell(BSCHSG1.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BSCHSG1.get(p1).getProgram_id()+BSCHSG1.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BSCHSG1.get(p1).getTitle().equalsIgnoreCase("SA"))
										{
											
											if (sheet.getCell(BSCHSG1.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BSCHSG1.get(p1).getProgram_id()+BSCHSG1.get(p1).getDescription());
												count1++;
											}
										}

									}
									
									for (int p1=0;p1<BSCHSG2.size();p1++)
									{
										
										if (BSCHSG2.get(p1).getTitle().equalsIgnoreCase("BI"))
										{
											if (sheet.getCell(BSCHSG2.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BSCHSG2.get(p1).getProgram_id()+BSCHSG2.get(p1).getDescription());
												count2++;
											}
										}
										else if (BSCHSG2.get(p1).getTitle().equalsIgnoreCase("HS"))
										{
											
											if (sheet.getCell(BSCHSG2.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BSCHSG2.get(p1).getProgram_id()+BSCHSG2.get(p1).getDescription());
												count2++;
											}
										}
										
										else if (BSCHSG2.get(p1).getTitle().equalsIgnoreCase("MA"))
										{
											
											if (sheet.getCell(BSCHSG2.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BSCHSG2.get(p1).getProgram_id()+BSCHSG2.get(p1).getDescription());
												count2++;
											}
										}
										
										
										
									}
									
									for (int p1=0;p1<BSCHSG3.size();p1++)
									{
										if (BSCHSG3.get(p1).getTitle().equalsIgnoreCase("CH"))
										{
											if (sheet.getCell(BSCHSG3.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BSCHSG3.get(p1).getProgram_id()+BSCHSG3.get(p1).getDescription());
												count3++;
											}
										}
										else if (BSCHSG3.get(p1).getTitle().equalsIgnoreCase("GS"))
										{
											
											if (sheet.getCell(BSCHSG3.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BSCHSG3.get(p1).getProgram_id()+BSCHSG3.get(p1).getDescription());
												count3++;
											}
										}
										
										else if (BSCHSG3.get(p1).getTitle().equalsIgnoreCase("PH"))
										{
											
											if (sheet.getCell(BSCHSG3.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BSCHSG3.get(p1).getProgram_id()+BSCHSG3.get(p1).getDescription());
												count3++;
											}
										}
									}
									
									
									for (int p1=0;p1<BSCHSCOMP.size();p1++)
									{
										if (BSCHSCOMP.get(p1).getTitle().equalsIgnoreCase("COMP"))
										{
											pap2.add(BSCHSCOMP.get(p1).getProgram_id()+BSCHSCOMP.get(p1).getDescription());
											
										}
										if (BSCHSCOMP.get(p1).getTitle().equalsIgnoreCase("COMP1"))
										{
											pap2.add(BSCHSCOMP.get(p1).getProgram_id()+BSCHSCOMP.get(p1).getDescription());
											
										}
										if (BSCHSCOMP.get(p1).getTitle().equalsIgnoreCase("COMP2"))
										{
											pap2.add(BSCHSCOMP.get(p1).getProgram_id()+BSCHSCOMP.get(p1).getDescription());
										
										}
										if (BSCHSCOMP.get(p1).getTitle().equalsIgnoreCase("COMP3"))
										{
											pap2.add(BSCHSCOMP.get(p1).getProgram_id()+BSCHSCOMP.get(p1).getDescription());
											
										}
									}
									
									
									
									
									if (count1==1 && count2==1 && count3 ==1)
									{
										System.out.println("every thing is ok");
									}
									else if (count1!=1)
									{
										System.out.println("group 1 has less then one");
									}
									else if (count2!=1)
									{
										System.out.println("group 2 has less then one");
									}
									else if (count3!=1)
									{
										System.out.println("group 3 has less then one");
									}
									else
									{
										System.out.println("some error in group selection");
									}
								}
								catch (Exception e)
								{
									System.out.println(e);
								}
								
								
							}
						}
						
						
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("B.Tech."))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								System.out.println("Applied Program"+BF.get(C).getDescription());
								COURSES.add(BF.get(C).getProgram_id());
							}
						}
						
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("B.TECH PARTTIME(Electrical Engg)"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								System.out.println("Applied Program"+BF.get(C).getDescription());
								COURSES.add(BF.get(C).getProgram_id());
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> BTECPCOMP= new ArrayList<BubbleFormBean>();
								BTECPCOMP = client.queryForList("bubbleform.GetCompPaper",formbean);
								
								for (int p1=0;p1<BTECPCOMP.size();p1++)
								{
									if (BTECPCOMP.get(p1).getTitle().equalsIgnoreCase("COMP"))
									{
										pap2.add(BTECPCOMP.get(p1).getProgram_id()+BTECPCOMP.get(p1).getDescription());
										
									}
									if (BTECPCOMP.get(p1).getTitle().equalsIgnoreCase("COMP1"))
									{
										pap2.add(BTECPCOMP.get(p1).getProgram_id()+BTECPCOMP.get(p1).getDescription());
										
									}
									if (BTECPCOMP.get(p1).getTitle().equalsIgnoreCase("COMP2"))
									{
										pap2.add(BTECPCOMP.get(p1).getProgram_id()+BTECPCOMP.get(p1).getDescription());
									
									}
									if (BTECPCOMP.get(p1).getTitle().equalsIgnoreCase("COMP3"))
									{
										pap2.add(BTECPCOMP.get(p1).getProgram_id()+BTECPCOMP.get(p1).getDescription());
										
									}
								}
								
								
								
							}
						}
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("MA(PG DEGREE COURSE)"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								List<BubbleFormBean> MAPROGRAM= new ArrayList<BubbleFormBean>();
								MAPROGRAM = client.queryForList("bubbleform.MAprog");
								
								List<BubbleFormBean> MAPAPER= new ArrayList<BubbleFormBean>();
								MAPAPER = client.queryForList("bubbleform.MAPAPER");
								int count1=0;
								try 
								{
									for (int p1=0;p1<MAPROGRAM.size();p1++)
									{
										if (MAPROGRAM.get(p1).getTitle().equalsIgnoreCase("M.A. (Drawing and Painting)"))
										{
											if (sheet.getCell(MAPROGRAM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												//pap2.add(MAPROGRAM.get(p1).getProgram_id()+MAPROGRAM.get(p1).getDescription());
												COURSES.add(MAPROGRAM.get(p1).getProgram_id());
												for(int p2=0; p2<MAPAPER.size(); p2++)
												{
													pap2.add(MAPROGRAM.get(p1).getProgram_id()+MAPAPER.get(p2).getDescription());
												}
											}
										}
										else if (MAPROGRAM.get(p1).getTitle().equalsIgnoreCase("M.A.(Applied Economics)"))
										{
											
											if (sheet.getCell(MAPROGRAM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												COURSES.add(MAPROGRAM.get(p1).getProgram_id());
												for(int p2=0; p2<MAPAPER.size(); p2++)
												{
													pap2.add(MAPROGRAM.get(p1).getProgram_id()+MAPAPER.get(p2).getDescription());
												}
											}
										}		
										else if (MAPROGRAM.get(p1).getTitle().equalsIgnoreCase("M.A. (English)"))
										{
											
											if (sheet.getCell(MAPROGRAM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												COURSES.add(MAPROGRAM.get(p1).getProgram_id());
												
												for(int p2=0; p2<MAPAPER.size(); p2++)
												{
													pap2.add(MAPROGRAM.get(p1).getProgram_id()+MAPAPER.get(p2).getDescription());
												}
											}
										}	
										
										else if (MAPROGRAM.get(p1).getTitle().equalsIgnoreCase("M.A.(Hindi)"))
										{
											
											if (sheet.getCell(MAPROGRAM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												COURSES.add(MAPROGRAM.get(p1).getProgram_id());
												
												for(int p2=0; p2<MAPAPER.size(); p2++)
												{
													pap2.add(MAPROGRAM.get(p1).getProgram_id()+MAPAPER.get(p2).getDescription());
												}
											}
										}	
										
										else if (MAPROGRAM.get(p1).getTitle().equalsIgnoreCase("M.A. (Music)"))
										{
											
											if (sheet.getCell(MAPROGRAM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												COURSES.add(MAPROGRAM.get(p1).getProgram_id());
												
												for(int p2=0; p2<MAPAPER.size(); p2++)
												{
													pap2.add(MAPROGRAM.get(p1).getProgram_id()+MAPAPER.get(p2).getDescription());
												}
											}
										}	
										
										else if (MAPROGRAM.get(p1).getTitle().equalsIgnoreCase("M.A. Social Science (Political Science)"))
										{
											
											if (sheet.getCell(MAPROGRAM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												COURSES.add(MAPROGRAM.get(p1).getProgram_id());
												
												for(int p2=0; p2<MAPAPER.size(); p2++)
												{
													pap2.add(MAPROGRAM.get(p1).getProgram_id()+MAPAPER.get(p2).getDescription());
												}
											}
										}
										
										else if (MAPROGRAM.get(p1).getTitle().equalsIgnoreCase("M.A. Social Science (psychology)"))
										{
											
											if (sheet.getCell(MAPROGRAM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												COURSES.add(MAPROGRAM.get(p1).getProgram_id());
												
												for(int p2=0; p2<MAPAPER.size(); p2++)
												{
													pap2.add(MAPROGRAM.get(p1).getProgram_id()+MAPAPER.get(p2).getDescription());
												}
											}
										}
										
										
										else if (MAPROGRAM.get(p1).getTitle().equalsIgnoreCase("M.A.(Sanskrit and Culture)"))
										{
											
											if (sheet.getCell(MAPROGRAM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												COURSES.add(MAPROGRAM.get(p1).getProgram_id());
												
												for(int p2=0; p2<MAPAPER.size(); p2++)
												{
													pap2.add(MAPROGRAM.get(p1).getProgram_id()+MAPAPER.get(p2).getDescription());
												}
											}
										}
	
										
									}
								}
								catch (Exception e)
								{
									System.out.println("Error in M.A. Program");
								}
							}
						}
						
						
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("B.Ed."))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								
								System.out.println("Applied Program"+BF.get(C).getDescription());
								COURSES.add(BF.get(C).getProgram_id());
								List<BubbleFormBean> BED= new ArrayList<BubbleFormBean>();
								
								//BED = client.queryForList("bubbleform.BEDpaper");
								
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> BEDG1= new ArrayList<BubbleFormBean>();	
								List<BubbleFormBean> BEDG2= new ArrayList<BubbleFormBean>();
								List<BubbleFormBean> BEDG3= new ArrayList<BubbleFormBean>();
								BEDG1 = client.queryForList("bubbleform.GetOptionalPaperG1",formbean);
								
								BEDG2 = client.queryForList("bubbleform.GetOptionalPaperG2",formbean);
								
								BEDG3 = client.queryForList("bubbleform.GetOptionalPaperG3",formbean);
								
								List<BubbleFormBean> BEDCOMP= new ArrayList<BubbleFormBean>();
								BEDCOMP = client.queryForList("bubbleform.GetCompPaper",formbean);
								int count1=0;
								int count2=0;
								int count3=0;
								
								
								
								
								try
								{
									for (int p1=0;p1<BEDG1.size();p1++)
									{
										if (BEDG1.get(p1).getTitle().equalsIgnoreCase("EC"))
										{
											if (sheet.getCell(BEDG1.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BEDG1.get(p1).getProgram_id()+BEDG1.get(p1).getDescription());
												count1++;
											}
										}
										else if (BEDG1.get(p1).getTitle().equalsIgnoreCase("EN"))
										{
											
											if (sheet.getCell(BEDG1.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BEDG1.get(p1).getProgram_id()+BEDG1.get(p1).getDescription());
												count1++;
											}
										}
										else if (BEDG1.get(p1).getTitle().equalsIgnoreCase("HI"))
										{
									
											if (sheet.getCell(BEDG1.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BEDG1.get(p1).getProgram_id()+BEDG1.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BEDG1.get(p1).getTitle().equalsIgnoreCase("HS"))
										{
											
											if (sheet.getCell(BEDG1.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BEDG1.get(p1).getProgram_id()+BEDG1.get(p1).getDescription());
												count1++;
											}
										}
										else if (BEDG1.get(p1).getTitle().equalsIgnoreCase("MS"))
										{
											
											if (sheet.getCell(BEDG1.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BEDG1.get(p1).getProgram_id()+BEDG1.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BEDG1.get(p1).getTitle().equalsIgnoreCase("MT"))
										{
											
											if (sheet.getCell(BEDG1.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BEDG1.get(p1).getProgram_id()+BEDG1.get(p1).getDescription());
												count1++;
											}
										}
										else if (BEDG1.get(p1).getTitle().equalsIgnoreCase("PY"))
										{
											
											if (sheet.getCell(BEDG1.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BEDG1.get(p1).getProgram_id()+BEDG1.get(p1).getDescription());
												count1++;
											}
										}
			
										else if (BEDG1.get(p1).getTitle().equalsIgnoreCase("SA"))
										{
											
											if (sheet.getCell(BEDG1.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BEDG1.get(p1).getProgram_id()+BEDG1.get(p1).getDescription());
												count1++;
											}
										}
										else if (BEDG1.get(p1).getTitle().equalsIgnoreCase("SO"))
										{
											
											if (sheet.getCell(BEDG1.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BEDG1.get(p1).getProgram_id()+BEDG1.get(p1).getDescription());
												count1++;
											}
										}
										
										else if (BEDG1.get(p1).getTitle().equalsIgnoreCase("SG"))
										{
											
											if (sheet.getCell(BEDG1.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BEDG1.get(p1).getProgram_id()+BEDG1.get(p1).getDescription());
												count1++;
											}
										}
										else if (BEDG1.get(p1).getTitle().equalsIgnoreCase("DP"))
										{
											
											if (sheet.getCell(BEDG1.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BEDG1.get(p1).getProgram_id()+BEDG1.get(p1).getDescription());
												count1++;
											}
										}
									}
									
									
									//////
									
									for (int p1=0;p1<BEDG2.size();p1++)
									{
										if (BEDG2.get(p1).getTitle().equalsIgnoreCase("AE"))
										{
											if (sheet.getCell(BEDG2.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BEDG2.get(p1).getProgram_id()+BEDG2.get(p1).getDescription());
												count2++;
												
											}
										}
										else if (BEDG2.get(p1).getTitle().equalsIgnoreCase("AL"))
										{
											
											if (sheet.getCell(BEDG2.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BEDG2.get(p1).getProgram_id()+BEDG2.get(p1).getDescription());
												count2++;
											}
										}
										else if (BEDG2.get(p1).getTitle().equalsIgnoreCase("BA"))
										{
									
											if (sheet.getCell(BEDG2.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BEDG2.get(p1).getProgram_id()+BEDG2.get(p1).getDescription());
												count2++;
											}
										}
										
										else if (BEDG2.get(p1).getTitle().equalsIgnoreCase("BC"))
										{
											
											if (sheet.getCell(BEDG2.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BEDG2.get(p1).getProgram_id()+BEDG2.get(p1).getDescription());
												count2++;
											}
										}
										
									}
									
									
									///////////////
									
									
									for (int p1=0;p1<BEDG3.size();p1++)
									{
										if (BEDG3.get(p1).getTitle().equalsIgnoreCase("BT"))
										{
											if (sheet.getCell(BEDG3.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BEDG3.get(p1).getProgram_id()+BEDG3.get(p1).getDescription());
												count3++;
												
											}
										}
										else if (BEDG3.get(p1).getTitle().equalsIgnoreCase("CH"))
										{
											
											if (sheet.getCell(BEDG3.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BEDG3.get(p1).getProgram_id()+BEDG3.get(p1).getDescription());
												count3++;
											}
										}
										else if (BEDG3.get(p1).getTitle().equalsIgnoreCase("MA"))
										{
									
											if (sheet.getCell(BEDG3.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BEDG3.get(p1).getProgram_id()+BEDG3.get(p1).getDescription());
												count3++;
											}
										}
										
										else if (BEDG3.get(p1).getTitle().equalsIgnoreCase("PH"))
										{
											
											if (sheet.getCell(BEDG3.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BEDG3.get(p1).getProgram_id()+BEDG3.get(p1).getDescription());
												count3++;
											}
										}
										else if (BEDG3.get(p1).getTitle().equalsIgnoreCase("ZO"))
										{
											
											if (sheet.getCell(BEDG3.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												pap2.add(BEDG3.get(p1).getProgram_id()+BEDG3.get(p1).getDescription());
												count3++;
											}
										}
			
										
									}
									
									for (int p1=0;p1<BEDCOMP.size();p1++)
									{
										if (BEDCOMP.get(p1).getTitle().equalsIgnoreCase("COMP"))
										{
											pap2.add(BEDCOMP.get(p1).getProgram_id()+BEDCOMP.get(p1).getDescription());
											
										}
										if (BEDCOMP.get(p1).getTitle().equalsIgnoreCase("COMP1"))
										{
											pap2.add(BEDCOMP.get(p1).getProgram_id()+BEDCOMP.get(p1).getDescription());
											
										}
										if (BEDCOMP.get(p1).getTitle().equalsIgnoreCase("COMP2"))
										{
											pap2.add(BEDCOMP.get(p1).getProgram_id()+BEDCOMP.get(p1).getDescription());
										
										}
										if (BEDCOMP.get(p1).getTitle().equalsIgnoreCase("COMP3"))
										{
											pap2.add(BEDCOMP.get(p1).getProgram_id()+BEDCOMP.get(p1).getDescription());
											
										}
									}
									
									
									 if ((count1==3) &&(count2!=3 && count3!=3))
									{
										System.out.println("group 1 OK");
									}
									else if ((count2==3)&&(count3!=3 && count1!=3))
									{
										System.out.println("group 2 OK");
									}
									else if ((count3==3)&&(count2!=3 && count1!=3))
									{
										System.out.println("group 3 OK");
									}
									
									else
									{
										System.out.println("some error in group selection");
									}
									
								
								}
								catch (Exception e)
								{
									System.out.println("Error in BED:--"+e);
								}

							}
							
						}
						
					
						else if (BF.get(C).getTitle().equalsIgnoreCase("BACHELOR OF VOCATION"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								COURSES.add(BF.get(C).getProgram_id());
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> BOV= new ArrayList<BubbleFormBean>();
								BOV = client.queryForList("bubbleform.GetCompPaper",formbean);
								
								for (int p1=0;p1<BOV.size();p1++)
								{
									if (BOV.get(p1).getTitle().equalsIgnoreCase("COMP"))
									{
										pap2.add(BOV.get(p1).getProgram_id()+BOV.get(p1).getDescription());
										
									}
									if (BOV.get(p1).getTitle().equalsIgnoreCase("COMP1"))
									{
										pap2.add(BOV.get(p1).getProgram_id()+BOV.get(p1).getDescription());
										
									}
									if (BOV.get(p1).getTitle().equalsIgnoreCase("COMP2"))
									{
										pap2.add(BOV.get(p1).getProgram_id()+BOV.get(p1).getDescription());
									
									}
									if (BOV.get(p1).getTitle().equalsIgnoreCase("COMP3"))
									{
										pap2.add(BOV.get(p1).getProgram_id()+BOV.get(p1).getDescription());
										
									}
								}
								
							}
						}
						else if (BF.get(C).getTitle().equalsIgnoreCase("M.Phil. Education"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								COURSES.add(BF.get(C).getProgram_id());
								System.out.println("Applied Program"+BF.get(C).getDescription());
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> MPHILEDU= new ArrayList<BubbleFormBean>();
								MPHILEDU = client.queryForList("bubbleform.GetCompPaper",formbean);
								
								for (int p1=0;p1<MPHILEDU.size();p1++)
								{
									if (MPHILEDU.get(p1).getTitle().equalsIgnoreCase("COMP"))
									{
										pap2.add(MPHILEDU.get(p1).getProgram_id()+MPHILEDU.get(p1).getDescription());
										
									}
									
								}
								
							}
						}
						
						
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("M.A.(SS)"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								List<BubbleFormBean> MAPROGRAM= new ArrayList<BubbleFormBean>();
								MAPROGRAM = client.queryForList("bubbleform.MAprog");
								
								List<BubbleFormBean> MAPAPER= new ArrayList<BubbleFormBean>();
								MAPAPER = client.queryForList("bubbleform.MAPAPER");
								int count1=0;
								try 
								{
									for (int p1=0;p1<MAPROGRAM.size();p1++)
									{
										if (MAPROGRAM.get(p1).getTitle().equalsIgnoreCase("M.A. Social Science (psychology)"))
										{
											if (sheet.getCell(MAPROGRAM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												//pap2.add(MAPROGRAM.get(p1).getProgram_id()+MAPROGRAM.get(p1).getDescription());
												COURSES.add(MAPROGRAM.get(p1).getProgram_id());
												for(int p2=0; p2<MAPAPER.size(); p2++)
												{
													pap2.add(MAPROGRAM.get(p1).getProgram_id()+MAPAPER.get(p2).getDescription());
												}
											}
										}
										else if (MAPROGRAM.get(p1).getTitle().equalsIgnoreCase("M.A. Social Science (Sociology)"))
										{
											
											if (sheet.getCell(MAPROGRAM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												COURSES.add(MAPROGRAM.get(p1).getProgram_id());
												for(int p2=0; p2<MAPAPER.size(); p2++)
												{
													pap2.add(MAPROGRAM.get(p1).getProgram_id()+MAPAPER.get(p2).getDescription());
												}
											}
										}		
										else if (MAPROGRAM.get(p1).getTitle().equalsIgnoreCase("M.A. Social Science (Political Science)"))
										{
											
											if (sheet.getCell(MAPROGRAM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												COURSES.add(MAPROGRAM.get(p1).getProgram_id());
												
												for(int p2=0; p2<MAPAPER.size(); p2++)
												{
													pap2.add(MAPROGRAM.get(p1).getProgram_id()+MAPAPER.get(p2).getDescription());
												}
											}
										}	

									}
								}
								catch (Exception e)
								{
									System.out.println("Error in M.A.SS Program");
								}
							}
						}
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("M.B.A."))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								COURSES.add(BF.get(C).getProgram_id());
								System.out.println("Applied Program"+BF.get(C).getDescription());
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> MBA= new ArrayList<BubbleFormBean>();
								MBA = client.queryForList("bubbleform.GetCompPaper",formbean);
								
								for (int p1=0;p1<MBA.size();p1++)
								{
									if (MBA.get(p1).getTitle().equalsIgnoreCase("COMP"))
									{
										pap2.add(MBA.get(p1).getProgram_id()+MBA.get(p1).getDescription());
										
									}
									
								}
								
							}
						}
						
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("M.Com"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								COURSES.add(BF.get(C).getProgram_id());
								System.out.println("Applied Program"+BF.get(C).getDescription());
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> MCOM= new ArrayList<BubbleFormBean>();
								MCOM = client.queryForList("bubbleform.GetCompPaper",formbean);
								
								for (int p1=0;p1<MCOM.size();p1++)
								{
									if (MCOM.get(p1).getTitle().equalsIgnoreCase("COMP"))
									{
										pap2.add(MCOM.get(p1).getProgram_id()+MCOM.get(p1).getDescription());
										
									}
									
								}
								
							}
						}
						
						
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("M.Sc."))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								List<BubbleFormBean> MSCPROGRAM= new ArrayList<BubbleFormBean>();
								MSCPROGRAM = client.queryForList("bubbleform.MAprog");
								
								List<BubbleFormBean> MSCPAPER= new ArrayList<BubbleFormBean>();
								MSCPAPER = client.queryForList("bubbleform.MAPAPER");
								int count1=0;
								try 
								{
									for (int p1=0;p1<MSCPROGRAM.size();p1++)
									{
										if (MSCPROGRAM.get(p1).getTitle().equalsIgnoreCase("MSc Botany"))
										{
											if (sheet.getCell(MSCPROGRAM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												//pap2.add(MAPROGRAM.get(p1).getProgram_id()+MAPROGRAM.get(p1).getDescription());
												COURSES.add(MSCPROGRAM.get(p1).getProgram_id());
												for(int p2=0; p2<MSCPAPER.size(); p2++)
												{
													pap2.add(MSCPROGRAM.get(p1).getProgram_id()+MSCPAPER.get(p2).getDescription());
												}
											}
										}
										else if (MSCPROGRAM.get(p1).getTitle().equalsIgnoreCase("MSc Chemistry"))
										{
											
											if (sheet.getCell(MSCPROGRAM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												COURSES.add(MSCPROGRAM.get(p1).getProgram_id());
												for(int p2=0; p2<MSCPAPER.size(); p2++)
												{
													pap2.add(MSCPROGRAM.get(p1).getProgram_id()+MSCPAPER.get(p2).getDescription());
												}
											}
										}		
										else if (MSCPROGRAM.get(p1).getTitle().equalsIgnoreCase("MSc Computer Science"))
										{
											
											if (sheet.getCell(MSCPROGRAM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												COURSES.add(MSCPROGRAM.get(p1).getProgram_id());
												
												for(int p2=0; p2<MSCPAPER.size(); p2++)
												{
													pap2.add(MSCPROGRAM.get(p1).getProgram_id()+MSCPAPER.get(p2).getDescription());
												}
											}
										}
										
										else if (MSCPROGRAM.get(p1).getTitle().equalsIgnoreCase("MSC  Mathematics"))
										{
											
											if (sheet.getCell(MSCPROGRAM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												COURSES.add(MSCPROGRAM.get(p1).getProgram_id());
												
												for(int p2=0; p2<MSCPAPER.size(); p2++)
												{
													pap2.add(MSCPROGRAM.get(p1).getProgram_id()+MSCPAPER.get(p2).getDescription());
												}
											}
										}
										
										else if (MSCPROGRAM.get(p1).getTitle().equalsIgnoreCase("MSc Zoology"))
										{
											
											if (sheet.getCell(MSCPROGRAM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												COURSES.add(MSCPROGRAM.get(p1).getProgram_id());
												
												for(int p2=0; p2<MSCPAPER.size(); p2++)
												{
													pap2.add(MSCPROGRAM.get(p1).getProgram_id()+MSCPAPER.get(p2).getDescription());
												}
											}
										}

										
										else if (MSCPROGRAM.get(p1).getTitle().equalsIgnoreCase("MSc Physics"))
										{
											
											if (sheet.getCell(MSCPROGRAM.get(p1).getCol(), R).getContents().equalsIgnoreCase("1"))
											{
												COURSES.add(MSCPROGRAM.get(p1).getProgram_id());
												
												for(int p2=0; p2<MSCPAPER.size(); p2++)
												{
													pap2.add(MSCPROGRAM.get(p1).getProgram_id()+MSCPAPER.get(p2).getDescription());
												}
											}
										}

									}
								}
								catch (Exception e)
								{
									System.out.println("Error in M.Sc. Program");
								}
							}
						}
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("M.Sc. Home Science"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								COURSES.add(BF.get(C).getProgram_id());
								System.out.println("Applied Program"+BF.get(C).getDescription());
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> MSCHome= new ArrayList<BubbleFormBean>();
								MSCHome = client.queryForList("bubbleform.GetCompPaper",formbean);
								
								for (int p1=0;p1<MSCHome.size();p1++)
								{
									if (MSCHome.get(p1).getTitle().equalsIgnoreCase("COMP"))
									{
										pap2.add(MSCHome.get(p1).getProgram_id()+MSCHome.get(p1).getDescription());
										
									}
									
								}
								
							}
						}	
						else if (BF.get(C).getTitle().equalsIgnoreCase("M.Tech Computer Science"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								COURSES.add(BF.get(C).getProgram_id());
								System.out.println("Applied Program"+BF.get(C).getDescription());
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> MTECHCS= new ArrayList<BubbleFormBean>();
								MTECHCS = client.queryForList("bubbleform.GetCompPaper",formbean);
								
								for (int p1=0;p1<MTECHCS.size();p1++)
								{
									if (MTECHCS.get(p1).getTitle().equalsIgnoreCase("COMP"))
									{
										pap2.add(MTECHCS.get(p1).getProgram_id()+MTECHCS.get(p1).getDescription());
										
									}
									
								}
								
							}
						}
						
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("M.Ed."))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								COURSES.add(BF.get(C).getProgram_id());
								System.out.println("Applied Program"+BF.get(C).getDescription());
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> MED= new ArrayList<BubbleFormBean>();
								MED = client.queryForList("bubbleform.GetCompPaper",formbean);
								
								for (int p1=0;p1<MED.size();p1++)
								{
									if (MED.get(p1).getTitle().equalsIgnoreCase("COMP"))
									{
										pap2.add(MED.get(p1).getProgram_id()+MED.get(p1).getDescription());
										
									}
									
								}
								
							}
						}
						
						
						
						
						
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("M.Tech Engineering Systems Full Time"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								COURSES.add(BF.get(C).getProgram_id());
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> MTECHPT= new ArrayList<BubbleFormBean>();
								MTECHPT = client.queryForList("bubbleform.GetCompPaper",formbean);
								
								for (int p1=0;p1<MTECHPT.size();p1++)
								{
									if (MTECHPT.get(p1).getTitle().equalsIgnoreCase("COMP"))
									{
										pap2.add(MTECHPT.get(p1).getProgram_id()+MTECHPT.get(p1).getDescription());
										
									}
									if (MTECHPT.get(p1).getTitle().equalsIgnoreCase("COMP1"))
									{
										pap2.add(MTECHPT.get(p1).getProgram_id()+MTECHPT.get(p1).getDescription());
										
									}
									if (MTECHPT.get(p1).getTitle().equalsIgnoreCase("COMP2"))
									{
										pap2.add(MTECHPT.get(p1).getProgram_id()+MTECHPT.get(p1).getDescription());
										
									}
								
								}
								
							}
						}

						
		// Diploma Courses 
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("Diploma in Architecture Assistantship"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								COURSES.add(BF.get(C).getProgram_id());
								System.out.println("Applied Program"+BF.get(C).getDescription());
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> DIP= new ArrayList<BubbleFormBean>();
								DIP = client.queryForList("bubbleform.GetCompPaper",formbean);
								
								for (int p1=0;p1<DIP.size();p1++)
								{
									if (DIP.get(p1).getTitle().equalsIgnoreCase("COMP"))
									{
										pap2.add(DIP.get(p1).getProgram_id()+DIP.get(p1).getDescription());
										
									}
									
								}
								
							}
						}
						
						
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("Diploma in Ayurveda"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								COURSES.add(BF.get(C).getProgram_id());
								System.out.println("Applied Program"+BF.get(C).getDescription());
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> DIP= new ArrayList<BubbleFormBean>();
								DIP = client.queryForList("bubbleform.GetCompPaper",formbean);
								
								for (int p1=0;p1<DIP.size();p1++)
								{
									if (DIP.get(p1).getTitle().equalsIgnoreCase("COMP"))
									{
										pap2.add(DIP.get(p1).getProgram_id()+DIP.get(p1).getDescription());
										
									}
									
								}
								
							}
						}
						
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("Diploma In Engineering"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								COURSES.add(BF.get(C).getProgram_id());
								System.out.println("Applied Program"+BF.get(C).getDescription());
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> DIP= new ArrayList<BubbleFormBean>();
								DIP = client.queryForList("bubbleform.GetCompPaper",formbean);
								
								for (int p1=0;p1<DIP.size();p1++)
								{
									if (DIP.get(p1).getTitle().equalsIgnoreCase("COMP"))
									{
										pap2.add(DIP.get(p1).getProgram_id()+DIP.get(p1).getDescription());
										
									}
									
								}
								
							}
						}
						
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("Diploma in Garment Technology"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								COURSES.add(BF.get(C).getProgram_id());
								System.out.println("Applied Program"+BF.get(C).getDescription());
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> DIP= new ArrayList<BubbleFormBean>();
								DIP = client.queryForList("bubbleform.GetCompPaper",formbean);
								
								for (int p1=0;p1<DIP.size();p1++)
								{
									if (DIP.get(p1).getTitle().equalsIgnoreCase("COMP"))
									{
										pap2.add(DIP.get(p1).getProgram_id()+DIP.get(p1).getDescription());
										
									}
									
								}
								
							}
						}
						
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("DIPLOMA IN HOME SCIENCE"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								COURSES.add(BF.get(C).getProgram_id());
								System.out.println("Applied Program"+BF.get(C).getDescription());
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> DIP= new ArrayList<BubbleFormBean>();
								DIP = client.queryForList("bubbleform.GetCompPaper",formbean);
								
								for (int p1=0;p1<DIP.size();p1++)
								{
									if (DIP.get(p1).getTitle().equalsIgnoreCase("COMP"))
									{
										pap2.add(DIP.get(p1).getProgram_id()+DIP.get(p1).getDescription());
										
									}
									
								}
								
							}
						}
						
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("Diploma In Interior Design and Decoration"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								COURSES.add(BF.get(C).getProgram_id());
								System.out.println("Applied Program"+BF.get(C).getDescription());
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> DIP= new ArrayList<BubbleFormBean>();
								DIP = client.queryForList("bubbleform.GetCompPaper",formbean);
								
								for (int p1=0;p1<DIP.size();p1++)
								{
									if (DIP.get(p1).getTitle().equalsIgnoreCase("COMP"))
									{
										pap2.add(DIP.get(p1).getProgram_id()+DIP.get(p1).getDescription());
										
									}
									
								}
								
							}
						}
						
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("Diploma in Leather Technology Footwear CASD"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								COURSES.add(BF.get(C).getProgram_id());
								System.out.println("Applied Program"+BF.get(C).getDescription());
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> DIP= new ArrayList<BubbleFormBean>();
								DIP = client.queryForList("bubbleform.GetCompPaper",formbean);
								
								for (int p1=0;p1<DIP.size();p1++)
								{
									if (DIP.get(p1).getTitle().equalsIgnoreCase("COMP"))
									{
										pap2.add(DIP.get(p1).getProgram_id()+DIP.get(p1).getDescription());
										
									}
									
								}
								
							}
						}
						
						
						
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("Diploma in Textile Designing"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								COURSES.add(BF.get(C).getProgram_id());
								System.out.println("Applied Program"+BF.get(C).getDescription());
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> DIP= new ArrayList<BubbleFormBean>();
								DIP = client.queryForList("bubbleform.GetCompPaper",formbean);
								
								for (int p1=0;p1<DIP.size();p1++)
								{
									if (DIP.get(p1).getTitle().equalsIgnoreCase("COMP"))
									{
										pap2.add(DIP.get(p1).getProgram_id()+DIP.get(p1).getDescription());
										
									}
									
								}
								
							}
						}
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("Diploma in Textile Designing"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								COURSES.add(BF.get(C).getProgram_id());
								System.out.println("Applied Program"+BF.get(C).getDescription());
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> DIP= new ArrayList<BubbleFormBean>();
								DIP = client.queryForList("bubbleform.GetCompPaper",formbean);
								
								for (int p1=0;p1<DIP.size();p1++)
								{
									if (DIP.get(p1).getTitle().equalsIgnoreCase("COMP"))
									{
										pap2.add(DIP.get(p1).getProgram_id()+DIP.get(p1).getDescription());
										
									}
									
								}
								
							}
						}
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("Diploma in MOM & SP -GROUP(D)"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								COURSES.add(BF.get(C).getProgram_id());
								System.out.println("Applied Program"+BF.get(C).getDescription());
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> DIP= new ArrayList<BubbleFormBean>();
								DIP = client.queryForList("bubbleform.GetCompPaper",formbean);
								
								for (int p1=0;p1<DIP.size();p1++)
								{
									if (DIP.get(p1).getTitle().equalsIgnoreCase("COMP"))
									{
										pap2.add(DIP.get(p1).getProgram_id()+DIP.get(p1).getDescription());
										
									}
									
								}
								
							}
						}
						
						
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("Diploma in MOM & SP -GROUP(D)"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								COURSES.add(BF.get(C).getProgram_id());
								System.out.println("Applied Program"+BF.get(C).getDescription());
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> DIP= new ArrayList<BubbleFormBean>();
								DIP = client.queryForList("bubbleform.GetCompPaper",formbean);
								
								for (int p1=0;p1<DIP.size();p1++)
								{
									if (DIP.get(p1).getTitle().equalsIgnoreCase("COMP"))
									{
										pap2.add(DIP.get(p1).getProgram_id()+DIP.get(p1).getDescription());
										
									}
									
								}
								
							}
						}
						
						
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("DIPLOMA IN PHARMACY"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								COURSES.add(BF.get(C).getProgram_id());
								System.out.println("Applied Program"+BF.get(C).getDescription());
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> DIP= new ArrayList<BubbleFormBean>();
								DIP = client.queryForList("bubbleform.GetCompPaper",formbean);
								
								for (int p1=0;p1<DIP.size();p1++)
								{
									if (DIP.get(p1).getTitle().equalsIgnoreCase("COMP"))
									{
										pap2.add(DIP.get(p1).getProgram_id()+DIP.get(p1).getDescription());
										
									}
									
								}
								
							}
						}
						
						
						
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("VOC. DIPLOMA(AUTOMOBILE)"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								COURSES.add(BF.get(C).getProgram_id());
								System.out.println("Applied Program"+BF.get(C).getDescription());
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> DIP= new ArrayList<BubbleFormBean>();
								DIP = client.queryForList("bubbleform.GetCompPaper",formbean);
								
								for (int p1=0;p1<DIP.size();p1++)
								{
									if (DIP.get(p1).getTitle().equalsIgnoreCase("COMP"))
									{
										pap2.add(DIP.get(p1).getProgram_id()+DIP.get(p1).getDescription());
										
									}
									
								}
								
							}
						}
						
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("VOC. DIPLOMA(INFO. TECH.)"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								COURSES.add(BF.get(C).getProgram_id());
								System.out.println("Applied Program"+BF.get(C).getDescription());
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> DIP= new ArrayList<BubbleFormBean>();
								DIP = client.queryForList("bubbleform.GetCompPaper",formbean);
								
								for (int p1=0;p1<DIP.size();p1++)
								{
									if (DIP.get(p1).getTitle().equalsIgnoreCase("COMP"))
									{
										pap2.add(DIP.get(p1).getProgram_id()+DIP.get(p1).getDescription());
										
									}
									
								}
								
							}
						}
						
						
						
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("DIPLOMA IN VETERINARY AND LIVESTOCK ASSISTANT"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								COURSES.add(BF.get(C).getProgram_id());
								System.out.println("Applied Program"+BF.get(C).getDescription());
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> DIP= new ArrayList<BubbleFormBean>();
								DIP = client.queryForList("bubbleform.GetCompPaper",formbean);
								
								for (int p1=0;p1<DIP.size();p1++)
								{
									if (DIP.get(p1).getTitle().equalsIgnoreCase("COMP"))
									{
										pap2.add(DIP.get(p1).getProgram_id()+DIP.get(p1).getDescription());
										
									}
									
								}
								
							}
						}
					    
					    
					    
					    
						else if (BF.get(C).getTitle().equalsIgnoreCase("Bachelor of Vocation: Dairy Technology"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								COURSES.add(BF.get(C).getProgram_id());
								System.out.println("Applied Program"+BF.get(C).getDescription());
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> DIP= new ArrayList<BubbleFormBean>();
								DIP = client.queryForList("bubbleform.GetCompPaper",formbean);
								
								for (int p1=0;p1<DIP.size();p1++)
								{
									if (DIP.get(p1).getTitle().equalsIgnoreCase("COMP"))
									{
										pap2.add(DIP.get(p1).getProgram_id()+DIP.get(p1).getDescription());
										
									}
									
								}
								
							}
						}
					    
					    
					    
						else if (BF.get(C).getTitle().equalsIgnoreCase("Bachelor of Vocation: Renewable Energy"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								COURSES.add(BF.get(C).getProgram_id());
								System.out.println("Applied Program"+BF.get(C).getDescription());
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> DIP= new ArrayList<BubbleFormBean>();
								DIP = client.queryForList("bubbleform.GetCompPaper",formbean);
								
								for (int p1=0;p1<DIP.size();p1++)
								{
									if (DIP.get(p1).getTitle().equalsIgnoreCase("COMP"))
									{
										pap2.add(DIP.get(p1).getProgram_id()+DIP.get(p1).getDescription());
										
									}
									
								}
								
							}
						}
					    
					    
					    
						else if (BF.get(C).getTitle().equalsIgnoreCase("Bachelor of Vocation: Food Processing and Preservation"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								COURSES.add(BF.get(C).getProgram_id());
								System.out.println("Applied Program"+BF.get(C).getDescription());
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> DIP= new ArrayList<BubbleFormBean>();
								DIP = client.queryForList("bubbleform.GetCompPaper",formbean);
								
								for (int p1=0;p1<DIP.size();p1++)
								{
									if (DIP.get(p1).getTitle().equalsIgnoreCase("COMP"))
									{
										pap2.add(DIP.get(p1).getProgram_id()+DIP.get(p1).getDescription());
										
									}
									
								}
								
							}
						}
					    
					    
						else if (BF.get(C).getTitle().equalsIgnoreCase("Bachelor of Vocation: Apparel Design and Manufacturing"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								COURSES.add(BF.get(C).getProgram_id());
								System.out.println("Applied Program"+BF.get(C).getDescription());
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> DIP= new ArrayList<BubbleFormBean>();
								DIP = client.queryForList("bubbleform.GetCompPaper",formbean);
								
								for (int p1=0;p1<DIP.size();p1++)
								{
									if (DIP.get(p1).getTitle().equalsIgnoreCase("COMP"))
									{
										pap2.add(DIP.get(p1).getProgram_id()+DIP.get(p1).getDescription());
										
									}
									
								}
								
							}
						}
					    
					    
						else if (BF.get(C).getTitle().equalsIgnoreCase("Textile Design and Development"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								COURSES.add(BF.get(C).getProgram_id());
								System.out.println("Applied Program"+BF.get(C).getDescription());
								formbean.setProgramName(BF.get(C).getProgram_id());
								List<BubbleFormBean> DIP= new ArrayList<BubbleFormBean>();
								DIP = client.queryForList("bubbleform.GetCompPaper",formbean);
								
								for (int p1=0;p1<DIP.size();p1++)
								{
									if (DIP.get(p1).getTitle().equalsIgnoreCase("COMP"))
									{
										pap2.add(DIP.get(p1).getProgram_id()+DIP.get(p1).getDescription());
										
									}
									
								}
								
							}
						}
					    
					    
					    
					    
					    
						
						
						else if (BF.get(C).getTitle().equalsIgnoreCase("Community College Dip.(Automobile Engg)"))
						{
							if (sheet.getCell(BF.get(C).getCol(), R).getContents().equalsIgnoreCase("1"))
							{
								COURSES.add(BF.get(C).getProgram_id());
								System.out.println("Applied Program"+BF.get(C).getDescription());
								
							}
						}

					}	 //column end here
					
					
					
					formbean.setUniversityId("0001");
					BubbleFormBean inputbean = new BubbleFormBean();
					inputbean.setUgpg("BFRAPP");
					inputbean.setUniversityId("0001");
					 int filenumber=getApplicationNumber(inputbean);
					 String applicationNumber=padZero(filenumber, 6)+"";
	    		     UserFormBean session = (UserFormBean) client.queryForObject("userform.getsession");
	    		     //applicationNumber=session.getSession()+"B"+applicationNumber;
	    		     applicationNumber=formbean.getFormnumber();
	    		     formbean.setAppnumber(applicationNumber);
	    		     if (!(transfer_app.equalsIgnoreCase("5")))
	    		     {
	    		    	 formbean.setEmail_id(applicationNumber);
	    		     }
	    		     
	    		     System.out.println("applicationNumber"+applicationNumber); 
	    		     
	    		     
	    		     formbean.setUgpg("BFRSTU");
	             	formbean.setStudentId(getStudentId(formbean));
	             	System.out.println("getStudentId"+formbean.getStudentId());
	    		     
	             	
					
					BubbleFormBean EntitySession = (BubbleFormBean) client.queryForObject("bubbleform.EntitySession");
					
					formbean.setSession(EntitySession.getSession());
					formbean.setStartdate(EntitySession.getStartdate());
					formbean.setEnddate(EntitySession.getEnddate());
					System.out.println("pone Number :---"+formbean.getPhoneNumber());
					if(formbean.getPhoneNumber()==null)
					{
						formbean.setPhoneNumber("9999999999");
						formbean.setCategory("GN");
						formbean.setCity("agra");
					}
					
					String na1= formbean.getCandidateName().substring(0,3);
					String asd =na1+pasdob;
					System.out.println("Password :---"+asd);
					formbean.setPassword(asd);
					
					
/////////////////////////////					
			try 
			{
				client.startTransaction();  		
					try 
		    	       {
						    //client.startTransaction();  
						    
			                if (transfer_app.equalsIgnoreCase("5"))
			                {
			                	formbean.setAddressline1(transferbean.getAddressline1());
			                	formbean.setAddressline2(transferbean.getAddressline2());
			                	formbean.setState(transferbean.getState());
			                	formbean.setCity(transferbean.getCity());
			                	formbean.setPincode(transferbean.getPincode());
			                	client.insert("bubbleform.setApplicantAccountInfo",formbean);
				                client.insert("bubbleform.setEntityStudentUserform", formbean);
			                	client.insert("bubbleform.setStudentAddressUserFormtransfer", formbean);
			                	
			                }
			                else
			                {
			                	client.insert("bubbleform.setApplicantAccountInfo",formbean);
				                client.insert("bubbleform.setEntityStudentUserform", formbean);
			                	client.insert("bubbleform.setStudentAddressUserForm", formbean);
			                }
			                
			                
			               // client.commitTransaction();
			               // client.endTransaction(); 
		    	       }
					 catch(Exception e)
					 {
						 System.out.println("Error"+e);
						// client.endTransaction(); 
					 }
			            
					for(int jj=0 ; jj<COURSES.size();jj++)
					{
						System.out.println("-----------------------------------------------------"+jj);
						System.out.println("ROW NUMBER:"+R);
						System.out.println("Program_id:"+COURSES.get(jj));
						System.out.println("Application Number"+formbean.getAppnumber());
						System.out.println("Registration Number"+formbean.getRegistrationNumber());
						System.out.println("Student Id"+formbean.getStudentId());
						System.out.println("NAME"+formbean.getCandidateName());
						System.out.println("DOB"+formbean.getDob());
						System.out.println("GENDER"+formbean.getGender());
						System.out.println("CATEGORY"+formbean.getCos());
						System.out.println("Exam Center"+formbean.getExamCenter());
						System.out.println("PHY Handicaped"+formbean.getPhyhandicaped());
						System.out.println("staff"+formbean.getStaff_status());
						System.out.println("Session"+formbean.getSession());	
						formbean.setProgramName(COURSES.get(jj));
						
						formbean.setUgpg("BFRREG");	
						formbean=getregistrationnumber(formbean);
						formbean.setRegistrationNumber("B"+formbean.getRegistration_number().get(0));
						System.out.println("getRegistration:"+formbean.getRegistrationNumber());
						
						
						
						// UserFormBean programfee = (UserFormBean) client.queryForObject("userform.getProgramFee",formbean);
//				            if (programfee.getFee().equalsIgnoreCase(""))
//				            {
				            	formbean.setFee("0");
				           // } 
				            
		                UserFormBean getentity = (UserFormBean) client.queryForObject("userform.getentityid",formbean);
		                formbean.setEntityId(getentity.getEntityId());
		                BubbleFormBean p11 = (BubbleFormBean)client.queryForObject("bubbleform.getpapergroupUserForm",formbean);
		                
						try 
			    	       {
							   // client.startTransaction(); 
				                client.insert("bubbleform.setStudentRegistrationUserForm", formbean);
				                client.insert("bubbleform.setApplicantProgramRegistrationUserForm",formbean);
				                client.insert("bubbleform.setStudenttestNumberUserForm",formbean);
				               // client.commitTransaction();
				               // client.endTransaction(); 
			    	       }
						 catch(Exception e)
						 {
							 System.out.println("Error"+e);
							// client.endTransaction(); 
						 }

							for (int p1=0;p1<pap2.size();p1++)
							{
								if (pap2.get(p1).substring(0,7).equalsIgnoreCase(COURSES.get(jj)))
								{
									formbean.setProgramName(COURSES.get(jj));
									formbean.setPaperGroup(pap2.get(p1).substring(7));
									BubbleFormBean papcodeinfo = (BubbleFormBean)client.queryForObject("bubbleform.getpapergroupUserForm",formbean);
									System.out.println("----**paperOption00000**-----"+pap2.get(p1).substring(7));
									
									if (papcodeinfo!=null)
			                		{
			                			formbean.setGrouping(papcodeinfo.getGrouping());
			                			formbean.setMainGroup(papcodeinfo.getMainGroup());
			                			formbean.setPapercode(papcodeinfo.getPapercode());
			                			client.insert("userform.setStudentPaperUserForm", formbean);			
			                		}
								}

							}

						System.out.println("-----------------------------------------------------"+jj);
					}
					
					COURSES.clear();
					pap2.clear();
					BubbleFormBean DOCPATH = (BubbleFormBean)client.queryForObject("bubbleform.DOCPATH");
					String doc= DOCPATH.getDocPath()+formbean.getAppnumber()+"/";
					formbean.setDocPath(doc);
					try 
		    	       {
						  //  client.startTransaction();  
						    System.out.println("asdas1");
						    
						    if (transfer_app.equalsIgnoreCase("5"))
			    		     {
						    	formbean.setDocPath(transferbean.getDocPath());
						    	formbean.setVerification(transferbean.getVerification());
						    	client.insert("bubbleform.setStudentApplicationStatusUserFormTrasfer",formbean);
			    		     }
						    else
						    {
						    	client.insert("bubbleform.setStudentApplicationStatusUserForm",formbean);
						    }
						    
						    System.out.println("asdas2");
						  
						    System.out.println("asdas3");
						    
						    System.out.println("asdas4");
						   
			               // client.commitTransaction();
			               // client.endTransaction(); 
		    	       }
					 catch(Exception e)
					 {
						 System.out.println("Error"+e);
						// client.endTransaction(); 
					 }
					 client.commitTransaction();
					 client.endTransaction(); 
	///////////////////////////////				
			}
			catch (Exception e)
			{
				 System.out.println("Error"+e);
				 client.endTransaction();
			}
					
				} //row end here
				
				//System.out.println("UF Size"+BF.size());
				
		  }
		  catch (Exception e)
		  {
			  System.out.println(e);
		  }
	  }
	  
	  private int getApplicationNumber(BubbleFormBean inputbean)
		{
			int applicationnumber=0;
			int response=0;
			int seqNum=1;
			try
			{
				BubbleFormBean sysValueObj = (BubbleFormBean) client.queryForObject("bubbleform.getSystemValues", inputbean);
				applicationnumber=Integer.parseInt(sysValueObj.getSequenceNumber())+1;
		        inputbean.setOldSequenceNumber(sysValueObj.getSequenceNumber());
		        inputbean.setSequenceNumber(String.valueOf(applicationnumber));
		        response=client.update("bubbleform.updateSystemValuesUserForm", inputbean);
			}
			catch (Exception e)
			{
				
			}
			
			return applicationnumber;
			
		} 
		
		private String getStudentId(BubbleFormBean inputbean)
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
				// UserFormBean getentity = (UserFormBean) client.queryForObject("userform.getentityid",inputbean);
				 String year=new SimpleDateFormat("yyyy").format(new Date().getTime());
		         studentId = "B" + "00010013"+year + padZero(seqNum, 5);
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
			return studentId;
			
		}
		private BubbleFormBean getregistrationnumber(BubbleFormBean inputbean)
		{
			
			UserFormBean regno = new UserFormBean();
			int seqNum=1;
			int response=0;
			try 
			{
				String yy = (new java.sql.Timestamp(new java.util.Date().getTime())).toString().substring(2, 4);
		

				System.out.println("app"+inputbean.getUgpg());
				inputbean.setUniversityId("0001");
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
	
		  
		  public void uploadUserFormtoServer(HttpServletRequest request,     // this function upload the file on server with the name USERFORM.xls
					HttpServletResponse response)throws Exception
					{
				
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
			     // upload.setSizeMax( maxFileSize );
			      
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
			                  String filename ="BUBBLEFORM";
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
				
				
				return filepath2;
			}
		  
	
}
