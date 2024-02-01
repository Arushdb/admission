package in.ac.dei.edrp.admissionsystem.administration.login;

import java.util.List;

public interface loginDao {
List<loginBean> checkMethod(loginBean input);
List<loginBean> checkuserId(loginBean input);
}
