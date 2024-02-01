package in.ac.dei.edrp.admissionsystem.administration.login;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class loginImpl extends SqlMapClientDaoSupport implements loginDao{

	public List<loginBean> checkMethod(loginBean input ) {
		
		List<loginBean>returnList=getSqlMapClientTemplate().queryForList("loginmenu.GetUserRecord",input);
		System.out.println("Working Serverside");
		return returnList;
	}

	public List<loginBean> checkuserId(loginBean input) {
		
		List<loginBean>returnList=getSqlMapClientTemplate().queryForList("loginmenu.CheckUserId",input);
		System.out.println("Working Serverside");
		return returnList;
	}

}
