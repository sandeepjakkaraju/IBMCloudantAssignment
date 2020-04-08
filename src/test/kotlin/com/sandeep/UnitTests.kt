package com.sandeep

import com.google.gson.Gson
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.util.*

@WebMvcTest()
class UnitTests(@Autowired val mockMvc: MockMvc) {


    @MockkBean
    private lateinit var phoneBookRepository: PhoneBookRepository

    @Test
    fun `List PhoneBooks`() {
        val sandeep1 = PhoneBook("Jakkaraju", "Sandeep", "9581570500", "IG - 322", 1)
        val sunil1 = PhoneBook("Jakkaraju", "Sunil", "9581570501", "IG - 322", 2)


        every { phoneBookRepository.findAll() } returns listOf(sandeep1, sunil1)
        mockMvc.perform(get("/api/phonebook/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.[0].surName").value(sandeep1.surName))
                .andExpect(jsonPath("\$.[1].surName").value(sunil1.surName))


    }

    @Test
    fun `List PhoneBook By Id`() {
        val sandeep1 = PhoneBook("Jakkaraju", "Sandeep", "9581570500", "IG - 322", 1)
        every { phoneBookRepository.findById(1L) } returns Optional.of(sandeep1)
        mockMvc.perform(get("/api/phonebook/1/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.surName").value(sandeep1.surName))

    }

    @Test
    fun `Delete Phonebook`() {
        val sandeep1 = PhoneBook("Jakkaraju", "Sandeep", "9581570500", "IG - 322", 1)
        every { phoneBookRepository.deleteById(1L) } returns mockk()

        mockMvc.perform(delete("/api/phonebook/1/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)

    }


    @Test
    fun `Search Phonebook`() {
        val sandeep1 = PhoneBook("Jakkaraju", "Sandeep", "9581570500", "IG - 322", 1)
        every { phoneBookRepository.findBySurName("Jakkaraju") } returns listOf(sandeep1)

        mockMvc.perform(get("/api/phonebook/search?surName=Jakkaraju").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.[0].surName").value(sandeep1.surName))

    }


    @Test
    fun `Save Phonebook`() {
        val sandeep1 = PhoneBook("Jakkaraju", "Sandeep", "9581570500", "IG - 322")
        val sandeepInDB = PhoneBook("Jakkaraju", "Sandeep", "9581570500", "IG - 322", 1L)

        every { phoneBookRepository.save(sandeep1) } returns sandeepInDB


        val gson = Gson()

        mockMvc.perform(post("/api/phonebook/").content(gson.toJson(sandeep1)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.surName").value(sandeep1.surName))
                .andExpect(jsonPath("\$.id").value(1L))

    }


    @Test
    fun `Update Phonebook`() {
        val sandeep1 = PhoneBook("Jakkaraju", "Sandeep", "9581570500", "IG - 322", 1L)
        val sandeepInDB = PhoneBook("Jakkaraju1", "Sandeep", "9581570500", "IG - 322", 1L)

        val gson = Gson()

        every { phoneBookRepository.save(sandeep1) } returns sandeepInDB

        mockMvc.perform(post("/api/phonebook/").content(gson.toJson(sandeep1)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.surName").value(sandeepInDB.surName))

    }


}