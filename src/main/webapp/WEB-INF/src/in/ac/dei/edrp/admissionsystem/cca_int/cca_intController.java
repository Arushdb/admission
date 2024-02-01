package in.ac.dei.edrp.admissionsystem.cca_int;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class cca_intController extends MultiActionController
{

private cca_intDao ccaservice;
	
	public void setCcaservice(cca_intDao ccaservice) 
	{
		this.ccaservice = ccaservice;
	}
	
	public ModelAndView checkAuthority(HttpServletRequest request,
			HttpServletResponse response)throws Exception
			{
		
		cca_intBean input = new cca_intBean();
		input.setUserName(request.getParameter("userName"));
		input.setPassword(request.getParameter("password"));
		List <cca_intBean> user_info =ccaservice.getUserInfo(input);
		
		if (user_info.size()>0)
		{
			System.out.println(user_info.get(0).getAuthority());
			System.out.println(user_info.get(0).getModifier());
			String authority = user_info.get(0).getAuthority();
			String modifier = user_info.get(0).getModifier();
			System.out.println("modifier"+modifier);
			//return new ModelAndView("cca_int/UserAuthority", "authority",authority);
			ModelAndView modal = new ModelAndView("cca_int/UserAuthority", "authority",authority);
			modal.addObject("modifier",modifier);
			return modal;
		}
		else
		{
			String authority="InValid";
			String modifier="InValid";
			//return new ModelAndView("cca_int/UserAuthority", "authority",authority);
			ModelAndView modal = new ModelAndView("cca_int/UserAuthority", "authority",authority);
			modal.addObject("modifier",modifier);
			return modal;
		}

		  }
	
	public ModelAndView generateReport(HttpServletRequest request,
			HttpServletResponse response)throws Exception
			{
		
		HttpSession session = request.getSession();
		
		cca_intBean input = new cca_intBean();
		input.setCreator(session.getAttribute("userId").toString());
		input.setAuthority(session.getAttribute("AUTHO").toString());
		
		System.out.println("val::"+input.getCreator());
		System.out.println("val2::"+input.getAuthority());
		String filepath= getServletContext().getRealPath(File.separator)+"CCA_INT_Reports"+File.separator+input.getCreator();
		input.setPath(filepath);
		List <cca_intBean> VerifyUserVelidation =ccaservice.VerifyUser(input);
		
		if (session.getAttribute("userId").toString()!=null)
		{
			
	
		if (VerifyUserVelidation.size()>0)
		{
			//input.setAuthority(VerifyUserVelidation.get(0).getAuthority());
			
			cca_intBean user_info =ccaservice.genrateReport(input);
			System.out.println("user_info.getPath()-"+user_info.getPath());
			File file = new File(user_info.getPath());
			
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
					
					response.setHeader("Content-Type", "application/excel");
					response.setHeader("Content-Length", String.valueOf(file.length()));
					response.setHeader("Content-disposition", "attachment;filename=\"" + "REPORT.xls" + "\"");

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
			catch (Exception e)
			{
				
			}
			
		}
		}
		else 
		{
			return new ModelAndView("../../AdmissionMenu");
		}
				return null;
		
			}
	
	
	
	
	public ModelAndView combodata(HttpServletRequest request,
			HttpServletResponse response)throws Exception
	{
		cca_intBean input = new cca_intBean();
		
		System.out.println("Working"+request.getParameter("AppNumber"));
		System.out.println("Working"+request.getParameter("FLAG"));
		System.out.println("WorkingUserqq"+request.getParameter("USER"));
		input.setFlag(request.getParameter("FLAG"));
		input.setApplication_number(request.getParameter("AppNumber"));
		List <cca_intBean> CandidateInfo =ccaservice.getCandidateInfo(input);
		System.out.println("CandidateInfo-"+CandidateInfo.size());
		input.setCreator(request.getParameter("USER"));
		List <cca_intBean> VerifyUserVelidation =ccaservice.VerifyUser(input);
		if (VerifyUserVelidation.size()>0)
		{
			System.out.println("Velidation Working");
		
		if (input.getFlag().equalsIgnoreCase("CCA"))
		{
			input.setFlag("CA");
			List<cca_intBean> checkCCA= ccaservice.checkRecord(input);
			List<cca_intBean> checkCCA1= ccaservice.checkRecord1(input);
			System.out.println("checkCCA-"+checkCCA.size());
			if (checkCCA.size()>0 || checkCCA1.size()>0) // marks not submitted
			{
				return new ModelAndView("cca_int/CCA", "list",CandidateInfo);
			}
			else if (checkCCA.size()==0 && checkCCA1.size()==0) // marks are submitted
			{
				return new ModelAndView("cca_int/CCA_sub", "list",CandidateInfo);
			}
			
		}
		
		else if (input.getFlag().equalsIgnoreCase("INT"))
		{
			input.setFlag("PW");
			List<cca_intBean> checkINT= ccaservice.checkRecord(input);
			List<cca_intBean> checkINT1= ccaservice.checkRecord1(input);
			if (checkINT.size()>0 || checkINT1.size()>0)
			{
				return new ModelAndView("cca_int/INT", "list",CandidateInfo);
			}
			else if (checkINT.size()==0 && checkINT1.size()==0)
			{
				return new ModelAndView("cca_int/INT_sub", "list",CandidateInfo);
			}
			
			
		}
		else if (input.getFlag().equalsIgnoreCase("BOTH"))
		{
			List<cca_intBean> checkBOTH= ccaservice.checkRecord_both(input);
			
			if (checkBOTH.size()>0)
			{
				if ((checkBOTH.get(0).getSub_status_cca()==null||checkBOTH.get(0).getSub_status_cca().equalsIgnoreCase("") )&& (checkBOTH.get(0).getSub_status_int()==null||checkBOTH.get(0).getSub_status_int().equalsIgnoreCase("")))
				{
					
					System.out.println("Both are allowed");
					return new ModelAndView("cca_int/BOTH", "list",CandidateInfo);
				}
				else if (checkBOTH.get(0).getSub_status_cca().equalsIgnoreCase("ALW") && checkBOTH.get(0).getSub_status_int().equalsIgnoreCase("ALW"))
				{
					System.out.println("Both are editable");
					return new ModelAndView("cca_int/BOTH", "list",CandidateInfo);
				}
				else if (checkBOTH.get(0).getSub_status_cca().equalsIgnoreCase("ALW") && checkBOTH.get(0).getSub_status_int().equalsIgnoreCase("SUB"))
				{
					System.out.println("CCA are editable INT nor Allowed");
					return new ModelAndView("cca_int/BOTH_CCA", "list",CandidateInfo);
				}
				else if (checkBOTH.get(0).getSub_status_cca().equalsIgnoreCase("SUB") && checkBOTH.get(0).getSub_status_int().equalsIgnoreCase("ALW"))
				{
					System.out.println("INT are editable CCA nor Allowed");
					return new ModelAndView("cca_int/BOTH_INT", "list",CandidateInfo);
				}
				else if (checkBOTH.get(0).getSub_status_cca().equalsIgnoreCase("SUB") && checkBOTH.get(0).getSub_status_int().equalsIgnoreCase("SUB"))
				{
					System.out.println("Both are not allwoed");
					
					return new ModelAndView("cca_int/BOTH_sub", "list",CandidateInfo);
				}
				else if ((checkBOTH.get(0).getSub_status_cca()==null || checkBOTH.get(0).getSub_status_cca().equalsIgnoreCase("")) && checkBOTH.get(0).getSub_status_int().equalsIgnoreCase("SUB"))
				{
					System.out.println("CCA are allowed INT nor Allowed");
					return new ModelAndView("cca_int/BOTH_CCA", "list",CandidateInfo);
				}
				else if (checkBOTH.get(0).getSub_status_cca().equalsIgnoreCase("SUB") && (checkBOTH.get(0).getSub_status_int().equalsIgnoreCase("") || (checkBOTH.get(0).getSub_status_int()=="")))
				{
					System.out.println("INT are allowed CCA nor Allowed");
					return new ModelAndView("cca_int/BOTH_INT", "list",CandidateInfo);
				}	
			}
			else 
			{
				return new ModelAndView("cca_int/BOTH_sub", "list",CandidateInfo);
			}
			
			
			
			
		//	List<cca_intBean> checkBOTH1= ccaservice.checkRecord_both1(input);
			
		}
		
		
		}
		else 
		{
			System.out.println("Dhust nahi chalunga");
		}
		
		//PrintWriter out = response.getWriter();
		//out.println("Dingo");
	return null;
	}
	
	public ModelAndView EditDataEnable(HttpServletRequest request,
			HttpServletResponse response)throws Exception
			{
		System.out.println("Working"+request.getParameter("AppNumber"));
		System.out.println("Working"+request.getParameter("FLAG"));
		System.out.println("WorkingUser-"+request.getParameter("USER"));
		cca_intBean input = new cca_intBean();
		input.setApplication_number(request.getParameter("AppNumber"));
		input.setFlag(request.getParameter("FLAG"));
		input.setModifier(request.getParameter("USER"));
		
		ccaservice.EnableUserData(input);
		return null;
			}
	
	public ModelAndView InsertMarks(HttpServletRequest request,
			HttpServletResponse response)throws Exception
			{
		cca_intBean input = new cca_intBean();
		System.out.println("Working"+request.getParameter("AppNumber"));
		System.out.println("Working"+request.getParameter("FLAG"));
		System.out.println("Working"+request.getParameter("MARKS"));
		System.out.println("Working"+request.getParameter("MARKS1"));
		System.out.println("Working"+request.getParameter("MARKS2"));
		System.out.println("WorkingUser-"+request.getParameter("USER"));
		input.setApplication_number(request.getParameter("AppNumber"));
		
	
		if (request.getParameter("FLAG").equalsIgnoreCase("CCA_Marks"))
		{
			input.setFlag("CA");
			input.setCca(request.getParameter("MARKS"));
			input.setCreator(request.getParameter("USER"));
		}
		else if (request.getParameter("FLAG").equalsIgnoreCase("INT_Marks"))
		{
			input.setFlag("PW");
			input.setInterview(request.getParameter("MARKS"));
			input.setCreator(request.getParameter("USER"));
		}
		else if (request.getParameter("FLAG").equalsIgnoreCase("BOTH_Marks"))
		{
			input.setFlag("BOTH");
			input.setCca(request.getParameter("MARKS1"));
			input.setInterview(request.getParameter("MARKS2"));
			System.out.println("FLAG2-"+request.getParameter("FLAG2"));
			input.setSub_status(request.getParameter("FLAG2"));
			input.setCreator(request.getParameter("USER"));
		}
		List<cca_intBean> updateRecord= ccaservice.UpdateRecord(input);
		List<cca_intBean>displayListC=displayList(input.getApplication_number(),"F");
		if (request.getParameter("FLAG").equalsIgnoreCase("CCA_Marks"))
		{
			return new ModelAndView("cca_int/UpdatedRecordCCA", "list",displayListC);
		}
		else if (request.getParameter("FLAG").equalsIgnoreCase("INT_Marks"))
		{
			return new ModelAndView("cca_int/UpdatedRecordINT", "list",displayListC);
		}
		else if (request.getParameter("FLAG").equalsIgnoreCase("BOTH_Marks"))
		{
			return new ModelAndView("cca_int/UpdatedRecord", "list",displayListC);
		}
		return null;
			}
	
	
	private List<cca_intBean> displayList(String app, String Flag)
	{
		cca_intBean input = new cca_intBean();
		input.setApplication_number(app);
		input.setFlag(Flag);
		List<cca_intBean> candidateRecord= ccaservice.GetCandidateRecord(input);
		return  candidateRecord;
	}
	
}
