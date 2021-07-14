package ti.techacademy.workshop.customer.web;

import java.time.Duration;

import ti.techacademy.workshop.customer.model.Customer;
import ti.techacademy.workshop.customer.service.CustomerService;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
public class CustomerRestController {

    private CustomerService service;

    @GetMapping( path = "customer",
                 produces = { MediaType.APPLICATION_STREAM_JSON_VALUE, 
                              MediaType.APPLICATION_NDJSON_VALUE, 
                              MediaType.APPLICATION_JSON_VALUE}
                )
    public Flux<Customer> getAll(@RequestParam(name="delay", defaultValue = "0") Integer delay){   
        return service.getCustomers(delay);
                      
    }

    @GetMapping( path = "customer/{code}",
    produces = { MediaType.APPLICATION_STREAM_JSON_VALUE, 
                 MediaType.APPLICATION_NDJSON_VALUE, 
                 MediaType.APPLICATION_JSON_VALUE}
   )
    public Mono<Customer> get(@RequestParam(name="delay", defaultValue = "0") Integer delay, @PathVariable("code") Long code){   
        return service.getCustomers(code, delay);
            
    }

  
   
}
