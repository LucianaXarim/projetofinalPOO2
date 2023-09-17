package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.integration.email.OrderEmailNotifierPlaceImpl;
import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderItem;
import br.ada.customer.crud.model.OrderStatus;
import br.ada.customer.crud.usecases.INotifierPlaceOrderUseCase;
import br.ada.customer.crud.usecases.IOrderPlaceUseCase;
import br.ada.customer.crud.usecases.repository.OrderRepository;

import java.math.BigDecimal;

public class OrderPlaceUseCaseImpl implements IOrderPlaceUseCase {
    private OrderRepository orderRepository;
    private INotifierPlaceOrderUseCase notifierPlace;

    public OrderPlaceUseCaseImpl(OrderRepository orderRepository, OrderEmailNotifierPlaceImpl orderEmailNotifierPlace){
        this.orderRepository = orderRepository;
    }

    /*public OrderPayUseCaseImpl(OrderRepository orderRepository, OrderEmailNotifierImpl orderEmailNotifier) {
        this.orderRepository = orderRepository;
    }
    * */

    @Override
    public void placeOrder(Order order) {
        BigDecimal sum = BigDecimal.ZERO;

        if (order.getStatus() != OrderStatus.OPEN){
            throw new RuntimeException("Pedido não está aberto. Abre um novo pedido");
        }
        if (order.getItems() == null || order.getItems().isEmpty()){
            throw new RuntimeException("Não há nenhum item no pedido");
        }

        for (OrderItem item : order.getItems()){
            sum = sum.add(item.getSaleValue());
        }

        if(sum.compareTo(BigDecimal.ZERO) <=0){
            throw new RuntimeException("A soma do valor dos produtos é igual ou menor que zero. Tente novamente");
        }

        notifierPlace.notifyPlace(order);
        order.setStatus(OrderStatus.PENDING_PAYMENT);
        orderRepository.update(order);



        /*order.getStatus()*/
        /* order.setStatus(OrderStatus.OPEN);*/
        /*Okkk -  4 - Notificar o cliente que esta aguardando o pagamento
         *OKKK -  5 - Pedido deve passar a ter o status igual OrderStatus.PENDING_PAYMENT
         *Okkk -  6 - atualizar o status repositorio
         * 7 - Instanciar com construtor
         * 8 - atualizar o Order factory */


    }
}
