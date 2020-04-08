package com.sandeep

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
data class PhoneBook constructor(
    val surName: String?=null,
    val firsthName: String?=null,
    val phoneNumber: String?=null,
    val Address: String?=null, @Id @GeneratedValue var id: Long? = null)
