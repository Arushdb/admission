package in.ac.dei.edrp.admissionsystem.cca_int;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class cca_intImpl extends SqlMapClientDaoSupport implements cca_intDao 
{

	public List<cca_intBean> checkRecord(cca_intBean input) 
	{
		List<cca_intBean> cca = getSqlMapClientTemplate().queryForList("ccaint.checkRecord",input);
		
		
		return cca;
	}

	public List<cca_intBean> getCandidateInfo(cca_intBean input) {
		List<cca_intBean> CandidateInfo = getSqlMapClientTemplate().queryForList("ccaint.candidateInfo",input);
		return CandidateInfo;
	}

	public List<cca_intBean> UpdateRecord(cca_intBean input) {
		
		if (input.getFlag().equalsIgnoreCase("BOTH"))
		{
			if (input.getSub_status().equalsIgnoreCase("IC"))
			{
				input.setFlag("CA");
				input.setMarks(input.getCca());
				getSqlMapClientTemplate().update("ccaint.updateMarks",input);
				input.setFlag("PW");
				input.setMarks(input.getInterview());
				getSqlMapClientTemplate().update("ccaint.updateMarks",input);	
			}
			else if (input.getSub_status().equalsIgnoreCase("C"))
			{
				input.setFlag("CA");
				input.setMarks(input.getCca());
				getSqlMapClientTemplate().update("ccaint.updateMarks",input);
			}
			else if (input.getSub_status().equalsIgnoreCase("I"))
			{
				input.setFlag("PW");
				input.setMarks(input.getInterview());
				getSqlMapClientTemplate().update("ccaint.updateMarks",input);
			}
			
		}
		else 
		{
			if (input.getFlag().equalsIgnoreCase("CA"))
			{
				input.setMarks(input.getCca());
			}
			else if (input.getFlag().equalsIgnoreCase("PW"))
			{
				input.setMarks(input.getInterview());
			}
			getSqlMapClientTemplate().update("ccaint.updateMarks",input);
		}
		return null;
	}

	public List<cca_intBean> checkRecord1(cca_intBean input) {
		
       List<cca_intBean> cca = getSqlMapClientTemplate().queryForList("ccaint.checkRecord1",input);
		
		return cca;
	}

	public List<cca_intBean> checkRecord_both(cca_intBean input) {
		   List<cca_intBean> cca = getSqlMapClientTemplate().queryForList("ccaint.checkRecord_both",input);
			
			return cca;
	}

	public List<cca_intBean> GetCandidateRecord(cca_intBean input) {
		List<cca_intBean> candidate = getSqlMapClientTemplate().queryForList("ccaint.GetCandidateRecord",input);
		return candidate;
	}

	public List<cca_intBean> EnableUserData(cca_intBean input) {
		
		if (input.getFlag().equalsIgnoreCase("ccaE"))
		{
			getSqlMapClientTemplate().update("ccaint.EnableUserCCA",input);
		}
		else if (input.getFlag().equalsIgnoreCase("intE"))
		{
			getSqlMapClientTemplate().update("ccaint.EnableUserINT",input);
		}
		else if (input.getFlag().equalsIgnoreCase("bothE"))
		{
			getSqlMapClientTemplate().update("ccaint.EnableUserBOTH",input);
		}

		
		return null;
	}

	public List<cca_intBean> getUserInfo(cca_intBean input) {
		
		List<cca_intBean> User = getSqlMapClientTemplate().queryForList("ccaint.GetUserRecord",input);
		
		return User;
	}

	public List<cca_intBean> VerifyUser(cca_intBean input) {
		
		List<cca_intBean> User = getSqlMapClientTemplate().queryForList("ccaint.GetRecordToVerify",input);
		return User;
	}

	public cca_intBean genrateReport(cca_intBean input) {
		
		cca_intBean bean1 = new cca_intBean();
		String path= input.getPath();
		
		 boolean success = (new File(path)).mkdirs();
		 if (success)
		    {
		      System.out.println("Directories: " + path + " created");
		    }
		 else
		    {
		    	System.out.println("directory not created");
		    }

		
		
		
		File exlFile = new File(path+File.separator+input.getCreator()+input.getAuthority()+".xls");
		WritableWorkbook writableWorkbook;
		Workbook workbook;
		List<cca_intBean> listA=null;
		String pathh1= path+File.separator+input.getCreator()+input.getAuthority()+".xls";
		 try 
		  {
			writableWorkbook = Workbook.createWorkbook(exlFile);
			WritableSheet writableSheet = writableWorkbook.createSheet("Sheet1", 0);
			if (input.getAuthority().equalsIgnoreCase("MCB"))
			{
				input.setEvaluation_component("PW");
				listA= getSqlMapClientTemplate().queryForList("ccaint.GetCandidateListConditional",input);
			}
			else if (input.getAuthority().equalsIgnoreCase("MCA"))
			{
				input.setEvaluation_component("CA");
				listA= getSqlMapClientTemplate().queryForList("ccaint.GetCandidateListConditional",input);
			}
			else if (input.getAuthority().equalsIgnoreCase("MCC"))
			{
				 listA= getSqlMapClientTemplate().queryForList("ccaint.GetCandidateList",input);
			}
			else 
			{
				
			}
			
			System.out.println("CandidateList :-"+listA.size());
			
			 WritableFont cellFont = new WritableFont(WritableFont.TIMES, 16);
			   cellFont.setColour(Colour.BLACK);
			   WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
			   cellFormat.setBackground(Colour.YELLOW);
			
			Label sl1 = new Label(0,0,"ApplicationNumber",cellFormat);
			Label sl2 = new Label(1,0,"FirstName",cellFormat);
			Label sl3 = new Label(2,0,"FatherName",cellFormat);
			Label sl4 = new Label(3,0,"Gender",cellFormat);
			Label sl5 = new Label(4,0,"Category",cellFormat);
			Label sl6 = new Label(5,0,"CCA",cellFormat);
			Label sl7 = new Label(6,0,"Interview",cellFormat);
			Label sl8 = new Label(7,0,"Date",cellFormat);
			
			writableSheet.addCell(sl1);
			writableSheet.addCell(sl2);
			writableSheet.addCell(sl3);
			writableSheet.addCell(sl4);
			writableSheet.addCell(sl5);
			writableSheet.addCell(sl6);
			writableSheet.addCell(sl7);
			writableSheet.addCell(sl8);
			
			
			for (int i=0;i<listA.size();i++)
			{
				Label sl11 = new Label(0,i+1,listA.get(i).getApp_number());
				Label sl12 = new Label(1,i+1,listA.get(i).getCandidateName());
				Label sl13 = new Label(2,i+1,listA.get(i).getFatherName());
				Label sl14 = new Label(3,i+1,listA.get(i).getGender());
				Label sl15 = new Label(4,i+1,listA.get(i).getCategory());
				if (input.getAuthority().equalsIgnoreCase("MCC"))
				{
					Label sl16 = new Label(5,i+1,listA.get(i).getCca());
					Label sl17 = new Label(6,i+1,listA.get(i).getInterview());	
					writableSheet.addCell(sl16);
					writableSheet.addCell(sl17);
				}
				else if (input.getAuthority().equalsIgnoreCase("MCA"))
				{
					Label sl16 = new Label(5,i+1,listA.get(i).getMarks());
					Label sl17 = new Label(6,i+1,"--");
					writableSheet.addCell(sl16);
					writableSheet.addCell(sl17);
					
				}
				else if (input.getAuthority().equalsIgnoreCase("MCB"))
				{
					Label sl16 = new Label(5,i+1,"--");
					Label sl17 = new Label(6,i+1,listA.get(i).getMarks());
					writableSheet.addCell(sl16);
					writableSheet.addCell(sl17);
				}
				
				Label sl18 = new Label(7,i+1,listA.get(i).getInsert_Date());
				
				writableSheet.addCell(sl11);
				writableSheet.addCell(sl12);
				writableSheet.addCell(sl13);
				writableSheet.addCell(sl14);
				writableSheet.addCell(sl15);
				writableSheet.addCell(sl18);
				
				
			}
			
			writableWorkbook.write();
		    writableWorkbook.close();
			
		    bean1.setPath(pathh1);
		    
		  }
		 catch (Exception e)
		 {
			 
		 }
		return bean1;
	}

	

}
