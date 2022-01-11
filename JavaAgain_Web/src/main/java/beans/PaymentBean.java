package beans;

import repositories.PaymentRepository;
import entity.Payment;
import utils.DateUtils;

import java.util.List;

public class PaymentBean {
    /* NOTE: Uncomment if server is TomEE
    @EJB
    */

    private final PaymentRepository paymentRepository;
    private Payment workingModel;

    public PaymentBean() {
        paymentRepository = new PaymentRepository();
        workingModel = new Payment();
    }

    public List<Payment> getPayments() {
        return paymentRepository.getAllPayments();
    }

    // NOTE: working model was already set from the front
    public String savePayment() {

        if (!validateRequest()) {
            return "index";
        }

        workingModel.setCreateDate(DateUtils.getUTCdatetimeAsDate());
        paymentRepository.addPayment(workingModel);

        return "index";
    }

    private boolean validateRequest() {

        final String code = workingModel.getCreditCode();

        return code != null && code.length() > 0;
    }

    public Payment getWorkingModel() {
        return workingModel;
    }

    public void setWorkingModel(Payment workingModel) {
        this.workingModel = workingModel;
    }
}
