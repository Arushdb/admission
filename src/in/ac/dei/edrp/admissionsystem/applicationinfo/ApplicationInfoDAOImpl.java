package in.ac.dei.edrp.admissionsystem.applicationinfo;

import in.ac.dei.edrp.admissionsystem.common.CommonBean;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;


public class ApplicationInfoDAOImpl extends SqlMapClientDaoSupport implements ApplicationInfoDAO {

	
	public List<CommonBean> getApplicationsStatus(String emailID) {
		List<CommonBean> appsStatusList = null;
		try
		{
			appsStatusList = getSqlMapClientTemplate().queryForList("applicationInfo.getAppsStatus", emailID);
			
		}
		catch(Exception ex)
		{
			
		}
		return appsStatusList;
	}

	
	public List<CommonBean> getAppliedPrograms(String emailID) {
		List<CommonBean> programs = null;
		try
		{
			programs = getSqlMapClientTemplate().queryForList("applicationInfo.getAppliedPrograms", emailID);
			
		}
		catch(Exception ex)
		{
			
		}
		return programs;
	}

}
