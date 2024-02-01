package in.ac.dei.edrp.admissionsystem.applicationinfo;

import in.ac.dei.edrp.admissionsystem.common.CommonBean;

import java.util.List;

public interface ApplicationInfoDAO {

	public List<CommonBean> getApplicationsStatus(String emailID);
	public List<CommonBean> getAppliedPrograms(String emailID);
}
