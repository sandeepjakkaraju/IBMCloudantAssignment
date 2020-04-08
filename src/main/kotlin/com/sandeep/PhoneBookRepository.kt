package com.sandeep

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
@Qualifier("phoneBookRepository")
interface PhoneBookRepository : CrudRepository<PhoneBook, Long> {
    fun findBySurName(surName: String): List<PhoneBook>
}