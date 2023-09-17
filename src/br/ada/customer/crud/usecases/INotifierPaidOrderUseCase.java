package br.ada.customer.crud.usecases;
import br.ada.customer.crud.model.Order;

public interface INotifierPaidOrderUseCase {
    void notify(Order oder);

}
