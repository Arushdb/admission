package in.ac.dei.edrp.admissionsystem.userform;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class userformDAOImpl extends SqlMapClientDaoSupport implements userformDAO
{

	
	public List<UserFormBean> method1() 
	    {
		System.out.println("i am on server");
		List<UserFormBean> l1 = new ArrayList<UserFormBean>();
		try
		{
		l1=getSqlMapClientTemplate().queryForList("userform.getuserformlocations");
		for(int i=0; i<l1.size(); i++)
		{
			System.out.println("val-"+l1.size());
		}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		
			
		return null;
		
		
	}

	
	public void method2() {
	System.out.println("don1");
		
	}

}
