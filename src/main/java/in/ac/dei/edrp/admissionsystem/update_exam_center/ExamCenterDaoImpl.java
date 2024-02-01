package in.ac.dei.edrp.admissionsystem.update_exam_center;

import in.ac.dei.edrp.admissionsystem.common.CommonBean;
import in.ac.dei.edrp.admissionsystem.common.User;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ExamCenterDaoImpl extends SqlMapClientDaoSupport implements ExamCenterDao {

	
	public List<CommonBean> getExamCenterList() throws Exception {
		
		List<CommonBean> examCenters = getSqlMapClientTemplate().queryForList("update_exam_center.getExamCenterList");
		return examCenters;
		
	}

	public List<String> checkUserLogin(User user) {
		List<String> users = getSqlMapClientTemplate().queryForList("update_exam_center.checkLogin", user);
		return users;
	}

	public void updateExamCenter(User user) throws Exception {
		
		getSqlMapClientTemplate().update("update_exam_center.updateExamCenterCode", user);
				
	}
	
	public List<String> checkExamCenter(User user) throws Exception {
		List<String> updatedCenterNames = getSqlMapClientTemplate().queryForList("update_exam_center.checkExamCenter", user);
		return updatedCenterNames;
	}


	

}
