package in.ac.dei.edrp.admissionsystem.cca_int;

import java.util.List;

public interface cca_intDao 
{
public List<cca_intBean>checkRecord(cca_intBean input);
public List<cca_intBean>checkRecord_both(cca_intBean input);
public List<cca_intBean>checkRecord1(cca_intBean input);
public List<cca_intBean>getCandidateInfo(cca_intBean input);
public List<cca_intBean>UpdateRecord(cca_intBean input);
public List<cca_intBean>GetCandidateRecord(cca_intBean input);
public List<cca_intBean>EnableUserData(cca_intBean input);
public List<cca_intBean>getUserInfo(cca_intBean input);
public List<cca_intBean>VerifyUser(cca_intBean input);
//public List<cca_intBean>genrateReport(cca_intBean input);
public cca_intBean genrateReport (cca_intBean input);





}
