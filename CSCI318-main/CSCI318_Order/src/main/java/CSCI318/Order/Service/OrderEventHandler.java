package CSCI318.Order.Service;


import CSCI318.Order.Model.OrderEvent;
import CSCI318.Order.Repository.OrderEventRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventHandler {

    private final OrderEventRepository orderEventRepository;

    OrderEventHandler(OrderEventRepository orderEventRepository) {
        this.orderEventRepository = orderEventRepository;
    }

    @EventListener
    public void handle(OrderEvent orderEvent) {
        orderEventRepository.save(orderEvent);

    }

}
