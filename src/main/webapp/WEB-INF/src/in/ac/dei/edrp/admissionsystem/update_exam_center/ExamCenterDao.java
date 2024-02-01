package in.ac.dei.edrp.admissionsystem.update_exam_center;

import in.ac.dei.edrp.admissionsystem.common.CommonBean;
import in.ac.dei.edrp.admissionsystem.common.User;

import java.util.List;

public interface ExamCenterDao {
	
	public List<CommonBean> getExamCenterList() throws Exception;
	
	public List<String> checkUserLogin(User user) throws Exception;
	
	public void updateExamCenter(User user) throws Exception;
	
	public List<String> checkExamCenter(User user) throws Exception;

}
