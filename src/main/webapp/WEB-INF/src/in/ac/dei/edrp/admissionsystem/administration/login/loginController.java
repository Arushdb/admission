package in.ac.dei.edrp.admissionsystem.administration.login;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class loginController extends MultiActionController{

	
	private loginDao loginservice;
	
	public void setLoginservice(loginDao loginservice)
	{
		this.loginservice=loginservice;
	}
	
	public ModelAndView  logout(HttpServletRequest request,
			HttpServletResponse response)throws Exception
	{
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("../AdmissionMenu.jsp");
		return null;
		
	}
	public ModelAndView checkAuthority(HttpServletRequest request,
			HttpServletResponse response)throws Exception
	{
		loginBean input = new loginBean();
		
		input.setUserName(request.getParameter("userName"));
		input.setPassword(request.getParameter("password"));
		
		System.out.println("Client Side Working");
		
		List<loginBean> authorityList = loginservice.checkMethod(input);
		if (authorityList.size()!=0)
		{
			HttpSession session = request.getSession();
			if(session.isNew())
			{
				session.setAttribute("userName", input.getUserName());
				session.setAttribute("userId", authorityList.get(0).getModifier());
				System.out.println("ff-"+authorityList.size());
				session.setAttribute("authority",authorityList);
				System.out.println(authorityList.get(0).getAuthority());
				System.out.println(authorityList.get(0).getModifier());
				return new ModelAndView("loginPanel/event_click_page");
			}
			else
			{
				if (input.getUserName().equalsIgnoreCase(session.getAttribute("userName").toString()))
				{
					return new ModelAndView("loginPanel/event_click_page", "authority",authorityList);
				}
			}
		}
		else 
		{
			response.sendRedirect("../AdmissionMenu.jsp");
		}
		return null;
		
	}
	
	
	public ModelAndView checkonClick(HttpServletRequest request,
			HttpServletResponse response)throws Exception
	{
		
		HttpSession session = request.getSession(false);
		
		if (session!=null)
		{
			//System.out.println(request.getParameter("AppNumber"));
			
			
			System.out.println(request.getParameter("FLAG"));
			System.out.println(request.getParameter("AUTHO"));
			session.setAttribute("AUTHO",request.getParameter("AUTHO"));
			session.getAttribute("userName").toString();
			System.out.println(session.getAttribute("userId").toString());
			loginBean input = new loginBean();
			input.setModifier(session.getAttribute("userId").toString());
			List<loginBean> checkUserId = loginservice.checkuserId(input);
	     input.setFlag(request.getParameter("FLAG"));
	    session.setAttribute("checkFlag",input);
			      if (checkUserId.size()>0)
			         {
			    	  if (request.getParameter("FLAG").equalsIgnoreCase("cca"))
			    	  { 
			    		  return new ModelAndView("loginPanel/onclick");
			    	  }
			    	  else if (request.getParameter("FLAG").equalsIgnoreCase("int"))
			    	  {
			    		  return new ModelAndView("loginPanel/onclick");
			    	  }
			    	  else if (request.getParameter("FLAG").equalsIgnoreCase("both"))
			    	  {
			    		  return new ModelAndView("loginPanel/onclick");
			    	  }
			    	  else if (request.getParameter("FLAG").equalsIgnoreCase("adm"))
			    	  {
			    		  return new ModelAndView("loginPanel/onclickadm");
			    	  }
			    	  else 
			    	  {
			    		  return new ModelAndView("../../AdmissionMenu");
			    	  }
				      
			         }
			      else
			         {
				     return new ModelAndView("../../AdmissionMenu");
			         }
		}
		
		else
		{
			return new ModelAndView("../../AdmissionMenu");
		}
		
		
		
		
	}
}
