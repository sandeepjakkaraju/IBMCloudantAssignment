package com.sandeep

import org.springframework.data.repository.CrudRepository
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

interface PhoneBookRepository : CrudRepository<PhoneBook, Long> {
    abstract fun findBySurName(surName: String?): List<PhoneBook>;
}

@Entity
data class PhoneBook constructor(
    val surName: String?=null,
    val firsthName: String?=null,
    val phoneNumber: String?=null,
    val Address: String?=null, @Id @GeneratedValue var id: Long? = null)
