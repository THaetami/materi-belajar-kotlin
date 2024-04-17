package belajar.unit.test.service

import belajar.unit.test.model.Person
import belajar.unit.test.repository.PersonRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.lang.Exception
import java.lang.IllegalArgumentException
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@Extensions(value = [
    ExtendWith(MockitoExtension::class)
])
class PersonServiceTest {

    @Mock
    lateinit var personRepository: PersonRepository

    @Mock
    lateinit var personService: PersonService

    @BeforeEach
    fun beforeEach() {
        personService = PersonService(personRepository)
    }

    @Test
    fun testPersonIdNotValid() {
        assertThrows<IllegalArgumentException> {
            personService.get("  ")
        }
    }

    @Test
    fun testPersonNotFound() {
        assertThrows<Exception> {
            personService.get("not found")
        }
    }

    @Test
    fun testPersonSuccess() {
        Mockito.`when`(personRepository.selectById("tatang")).thenReturn(Person("tatang", "tatang haetami"))

        val person = personService.get("tatang")
        assertEquals("tatang", person.id)
        assertEquals("tatang haetami", person.name)
    }

    @Test
    fun testRegisterPersonNameIsBlank () {
        assertThrows<IllegalArgumentException> {
            personService.register("  ")
        }
    }

    @Test
    fun testRegisterSuccess() {
        val person = personService.register("tatang")

        assertEquals("tatang", person.name)
        assertNotNull(person.id)

        Mockito.verify(personRepository, Mockito.times(1)).insert(Person(person.id, person.name))
    }

}