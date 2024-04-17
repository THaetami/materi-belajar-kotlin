package belajar.unit.test

import org.junit.jupiter.api.Test
import org.mockito.Mockito
import kotlin.test.assertEquals

/*
    * mengetes hanya ke satu function saja (1 class saja)
      merupakan unit test yang baik, jika harus mengetes
      interaksi antar class, lebih disarankan membuat
      integration test.
    * mocking adalah solusi ketika kita ingin membuat unit test
      untuk sebuah class yang ketergantungan class lain
    * mocking sedehananya adalah membuat object tiruan, sehingga
      behavior object tersebut bisa kita tentukan sesuai
      keinginan.
    * mocking juga bisa membuat request response seolah object2
      tersebut benar-benar dibuat.
*/

class MockTest {

    @Test
    fun testMock() {

        val list: List<String> = Mockito.mock(List::class.java) as List<String> // membuat mock List | membuat object tiruan

        Mockito.`when`(list.get(0)).thenReturn("tatang") // menambah behavior/method `get(0)`
        Mockito.`when`(list.get(1)).thenReturn("haetami") // menambah behavior/method `get(1)`

        assertEquals("tatang", list.get(0))
        assertEquals("haetami", list.get(1))
        assertEquals("haetami", list.get(1))

        Mockito.verify(list).get(0) // memverifikasi bahwa list.get(0) pernah dipanggil (1 kali)
        Mockito.verify(list, Mockito.times(2)).get(1) // // memverifikasi bahwa list.get(0) pernah dipanggil (2 kali)

    }

}