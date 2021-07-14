package ti.techacademy.workshop.customer.repository.impl;

import java.io.InputStream;
import java.util.List;
 
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import ti.techacademy.workshop.customer.model.Customer;
import ti.techacademy.workshop.customer.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Value;

import reactor.core.publisher.Flux;

@Order(-1)
@Component
@Slf4j
public class CustomerRepositoryNoReactive implements CustomerRepository{
 
    @Value("${app.customer.resource.data.no-reactive}")
    Resource resourceFile;

    @Override
    public Flux<Customer> getAll() {
        return Flux.fromIterable(getData());
    }

    private List<Customer> getData(){
        try{
    
            ObjectMapper mapper = new ObjectMapper();
            CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, Customer.class);
            InputStream inputStream = resourceFile.getInputStream();
            log.info("ini");
            List<Customer> r = mapper.readValue(inputStream, type);
            log.info("fin");
            return r;
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        
    }

}
