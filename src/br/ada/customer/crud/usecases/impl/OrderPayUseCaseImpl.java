package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.integration.email.OrderEmailNotifierImpl;
import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderStatus;
import br.ada.customer.crud.usecases.IOrderPayUseCase;
import br.ada.customer.crud.usecases.INotifierPaidOrderUseCase;
import br.ada.customer.crud.usecases.IPayUseCase;
import br.ada.customer.crud.usecases.repository.OrderRepository;

public class OrderPayUseCaseImpl implements IOrderPayUseCase, IPayUseCase {
    private OrderRepository orderRepository;
    private INotifierPaidOrderUseCase notifier;
    private IPayUseCase paid;


    public OrderPayUseCaseImpl(OrderRepository orderRepository, OrderEmailNotifierImpl orderEmailNotifier) {
        this.orderRepository = orderRepository;
    }
    public void pay (Order order){
        if (order.getStatus() != OrderStatus.PENDING_PAYMENT){
            throw new RuntimeException();

        }
        order.setStatus(OrderStatus.PAID);
        orderRepository.update(order);
        notifier.notify(order);
        paid.pay(order);
    }

}
