package com.sandeep
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
class HttpControllersTests(@Autowired val mockMvc: MockMvc) {


    @MockkBean
    private lateinit var phoneBookRepository: PhoneBookRepository

    @Test
    fun `List PhoneBooks`() {
        val sandeep1 = PhoneBook("Jakkaraju","Sandeep","9581570500","IG - 322",1)
        val sunil1 = PhoneBook("Jakkaraju","Sunil","9581570501","IG - 322",2)


        every { phoneBookRepository.findAll() } returns listOf(sandeep1,sunil1)
        mockMvc!!.perform(get("/api/article/").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("\$.[0].surName").value(sandeep1.surName))

    }

}