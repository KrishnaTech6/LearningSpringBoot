package com.example.demo.datasource.mock

import com.example.demo.datasource.BankDataSource
import com.example.demo.model.Bank
import org.springframework.stereotype.Repository

@Repository
/**
 * The @Repository annotation in Spring marks a class as a Data Access Object (DAO).
 * It is part of Spring's stereotype annotations (like @Component, @Service, and @Controller)
 * and is specifically used to indicate that the class is responsible for interacting with the database.
 * */
class MockBankDataSource: BankDataSource {
    val banks = listOf(
        Bank("12", 1.0, 123),
        Bank("12231", 12.0, 23),
        Bank("1123", 0.0, 123),
        Bank("122", 4.0, 0),
    )

    override fun retrieveBanks(): Collection<Bank> = banks
    override fun retrieveBank(accountNumber: String): Bank =
        banks.first { it.accountNumber == accountNumber }
}