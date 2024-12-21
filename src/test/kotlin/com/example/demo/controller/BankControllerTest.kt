package com.example.demo.controller

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
//@SpringBootTest is costly use carefully
internal class BankControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    val baseUrl="/api/banks"

    @Nested
    @DisplayName("getBanks()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBanks {
        /**Why Use an Inner Class in Tests?
        Logical Grouping of Tests:

        In this example, the GetBank inner class logically groups all test cases related to the getBank() functionality.
        If your test suite has multiple endpoints or functionalities, using nested classes can make your tests more readable and structured.

        Annotation Purpose:

        @Nested: Indicates that the class is a nested test class and logically groups related tests.

        @DisplayName: Adds a descriptive label for the nested test class when running or reporting tests.

        @TestInstance(TestInstance.Lifecycle.PER_CLASS): Ensures the lifecycle of the test instance is tied to the class, allowing shared setup or state within the nested test.

        Separation of Concerns:
        It helps separate the setup, execution, and assertions for specific scenarios (e.g., getBank()) from others in the test file.

        Readability in Reports:
        Test reports or logs will show nested groupings, making it easier to identify which functionality each test belongs to.*/

        @Test
        fun `should return all banks`() {
            // when/then
            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[1].accountNumber") { value("12231") }
                }
        }
    }


    @Nested
    @DisplayName("getBank()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBank {
        @Test
        fun `should return a bank with account number`() {
            //given
            val accountNumber = "12231"
            // when/then
            mockMvc.get("$baseUrl/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.trust") { value("12.0") }
                    jsonPath("$.transactionFee") { value("23") }
                }
        }
        
        @Test
        fun `should return NOT FOUND on wrong account number`(){
            //given 
            val accountNumber = "does_not_exist"
            
            // when /then
            mockMvc.get("$baseUrl/$accountNumber")
                .andDo { print() }
                .andExpect { status { isNotFound() } }

        }
    }
}