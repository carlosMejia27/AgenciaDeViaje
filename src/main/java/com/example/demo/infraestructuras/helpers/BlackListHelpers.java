package com.example.demo.infraestructuras.helpers;

import com.example.demo.util.exceptions.ForbiddenCustomerException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
@AllArgsConstructor
public class BlackListHelpers {

    public  void  isInBlackListCustomer(String customerId){
        if (customerId.equals("GOTW771012HMRGR087")){
            throw new ForbiddenCustomerException();
        }
    }
}
