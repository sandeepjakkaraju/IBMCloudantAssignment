package com.sandeep

import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import kotlin.test.assertTrue

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests() {

    @Autowired
    val restTemplate : RestTemplate? = null;

    @Test
    fun `Assert blog page title, content and status code`() {
        val entity = restTemplate!!.getForEntity<String>("/")
        assertTrue(entity.statusCode.is2xxSuccessful)
    }

}