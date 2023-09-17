package br.ada.customer.crud.integration.email;
import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.usecases.INotifierPlaceOrderUseCase;

public class OrderEmailNotifierPlaceImpl implements INotifierPlaceOrderUseCase {
    private SendEmail sendEmail;

    public OrderEmailNotifierPlaceImpl(SendEmail sendEmail) {this.sendEmail = sendEmail;}

    @Override
    public void notifyPlace(Order order) {
        sendEmail.send("comunicado@loja.com",order.getCustomer().getEmail(), "O pedido esta aguardando o pagamento");
        /*System.out.println("O pedido esta aguardando o pagamento");*/
    }
}
