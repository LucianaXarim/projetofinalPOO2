package br.ada.customer.crud.integration.email;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.usecases.INotifierPaidOrderUseCase;

public class OrderEmailNotifierImpl implements INotifierPaidOrderUseCase {

    private SendEmail sendEmail;

    public OrderEmailNotifierImpl(SendEmail sendEmail) {this.sendEmail = sendEmail;}
    @Override
    public void notify(Order oder) {
        sendEmail.send("comunicado@loja.com",oder.getCustomer().getEmail(), "O pedido foi pago!")
        /*System.out.println("O pedido foi pago!");*/
    }
}
