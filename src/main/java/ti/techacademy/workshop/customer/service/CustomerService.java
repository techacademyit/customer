package ti.techacademy.workshop.customer.service;
 
import ti.techacademy.workshop.customer.model.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService{

    public Flux<Customer> getCustomers(Integer delay);

    public Mono<Customer> getCustomers(Long code, Integer delay);

 
}