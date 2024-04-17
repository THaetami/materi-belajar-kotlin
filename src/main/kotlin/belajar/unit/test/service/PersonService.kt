package belajar.unit.test.service

import belajar.unit.test.model.Person
import belajar.unit.test.repository.PersonRepository
import java.util.*

class PersonService(private val personRepository: PersonRepository) {

    fun get(id: String): Person {

        if (id.isBlank()) {
            throw IllegalArgumentException("Person is not valid")
        }

        val person = personRepository.selectById(id)
        if (person != null) {
            return person
        } else {
            throw Exception("person not found")
        }
    }

    fun register(name: String): Person {
        if (name.isBlank()) {
            throw IllegalArgumentException("name is blank")
        }

        val id = UUID.randomUUID().toString()
        val person = Person(id, name)

        personRepository.insert(person)

        return person
    }

}