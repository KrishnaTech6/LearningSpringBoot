package com.example.demo.datasource.mock

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class MockBankDataSourceTest {
    //Testing data source as POJO (Plain Old Java Object) and not using DI
    private val mockDataSource  = MockBankDataSource()
    @Test
    fun `should provide a collection of bank`() {
        /**Given-When-Then Pattern in Testing
         * makes it more readable and organised
         * */
        // when
        val banks = mockDataSource.retrieveBanks()

        // then
        Assertions.assertThat(banks).isNotEmpty

    }

    @Test
    fun `should provide some mock data`() {
        // when
        val banks = mockDataSource.retrieveBanks()

        // then
        Assertions.assertThat(banks).allMatch { it.accountNumber.isNotBlank() }
        Assertions.assertThat(banks).allMatch { it.trust != 0.0 }
        Assertions.assertThat(banks).anyMatch { it.transactionFee != 0 } //there should be not every case

    }


}