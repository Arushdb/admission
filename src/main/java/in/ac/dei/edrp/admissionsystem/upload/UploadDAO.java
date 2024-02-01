package in.ac.dei.edrp.admissionsystem.upload;


public interface UploadDAO 
{
	public Integer updateDocumentPath(UploadDocumentBean applicant) ;
	public UploadDocumentBean getDocumentSize();
}
