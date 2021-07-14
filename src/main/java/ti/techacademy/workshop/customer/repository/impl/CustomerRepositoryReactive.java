package ti.techacademy.workshop.customer.repository.impl;

import ti.techacademy.workshop.customer.model.Customer;
import ti.techacademy.workshop.customer.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ResolvableType;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.stereotype.Component; 

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Order(-1)
@Slf4j
@Component
public class CustomerRepositoryReactive implements CustomerRepository{
    
    @Value("${app.customer.resource.data.reactive}")
    Resource resourceFile;

    @Override
    public Flux<Customer> getAll() {
        return new Jackson2JsonDecoder()
                    .decode(getData(),ResolvableType.forClass(Customer.class) , null, null)
                    .cast(Customer.class)
                    .doOnNext( f -> log.info(f.toString()));
    }

    private Flux<DataBuffer> getData(){
        return DataBufferUtils.read( resourceFile, new DefaultDataBufferFactory(), 4096);
    }
}
