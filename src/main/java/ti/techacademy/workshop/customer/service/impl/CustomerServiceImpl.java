package ti.techacademy.workshop.customer.service.impl;
 
 
import java.time.Duration;

import ti.techacademy.workshop.customer.model.Customer;
import ti.techacademy.workshop.customer.repository.CustomerRepository;
import ti.techacademy.workshop.customer.service.CustomerService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService{
 
    private CustomerRepository repository;

    public CustomerServiceImpl( ApplicationContext applicationContext,
                                @Value("${app.customer.repository}") String qualifier){
        this.repository = (CustomerRepository) applicationContext.getBean(qualifier);
    }

    @Override
    public Flux<Customer> getCustomers(  Integer delay) {
        return repository.getAll()
                         .delayElements(Duration.ofMillis(delay));
    }  

    @Override
    public Mono<Customer> getCustomers(Long code, Integer delay) {
        return repository.getAll()  
                        .filter(p -> code.equals(p.getCode()))
                        .take(1)
                        .delayElements(Duration.ofMillis(delay))
                        .next();
    } 
}