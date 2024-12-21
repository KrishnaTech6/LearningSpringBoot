package com.example.demo.service

import com.example.demo.datasource.BankDataSource
import com.example.demo.model.Bank
import org.springframework.stereotype.Service

@Service
/**The @Service annotation in Spring Boot is used to define a service layer component in your application.
 * It is a specialization of the @Component annotation and
 * is typically used for business logic or service-related functionality.*/
class BankService(private val dataSource: BankDataSource) {
    fun getBanks(): Collection<Bank> = dataSource.retrieveBanks()
    fun getBank(accountNumber: String): Bank {
        return dataSource.retrieveBank(accountNumber)
    }
}
