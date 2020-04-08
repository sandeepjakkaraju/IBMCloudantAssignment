package com.sandeep

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/phonebook")
class Controller(private val repository: PhoneBookRepository) {

    @GetMapping("/")
    fun findAll() = repository.findAll();

    @DeleteMapping("/{id}")
    fun deletePhoneEntry(@PathVariable id: Long) = repository.deleteById(id);

    @GetMapping("/{id}")
    fun getPhoneEntry(@PathVariable id: Long) = repository.findById(id);

    @PostMapping("/", consumes = ["application/json"])
    fun save(@RequestBody phoneBook: PhoneBook): PhoneBook = repository.save(phoneBook);

    @GetMapping("/search")
    fun searchBySurname(@RequestParam surName: String) = repository.findBySurName(surName);
}