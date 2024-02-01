package in.ac.dei.edrp.admissionsystem.paymentDetails;

public interface PaymentDAO {

	public boolean getAlreadyVoucherGenerated(PaymentDocumentBean input);	
	public void insertVoucherDetails(PaymentDocumentBean input);
}
