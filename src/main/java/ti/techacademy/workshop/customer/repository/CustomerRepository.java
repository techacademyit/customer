package ti.techacademy.workshop.customer.repository;

import ti.techacademy.workshop.customer.model.Customer;

import reactor.core.publisher.Flux; 

public interface CustomerRepository {

    public Flux<Customer> getAll();
    
}
