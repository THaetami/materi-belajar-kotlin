package belajar.unit.test.repository

import belajar.unit.test.model.Person

interface PersonRepository {

    fun selectById(id: String): Person?

    fun insert(person: Person): Unit

}