/*
    Reflection

    * kemampuan melihat struktur aplikasi kita pada saat berjalan
    * biasanya sangat berguna saat kita ingin membuat library atau
      framework, sehingga bisa meng-otomatisasi pekerjaan
    * untuk mengakses reflection class dari sebuah object, kita
      bisa menggunakan keyword `::class` atau `NamaClass::class`
*/
import Annotation.NotBlank
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties


data class CreateProductRequest(
    @NotBlank val id: String,
    @NotBlank val name: String,
    @NotBlank val price: Long
    )

data class CreateCategoryRequest(
    @NotBlank val id: String,
    @NotBlank val name: String
    )

fun validateRequest(request: Any) {
    val clazz = request::class //  proses reflection
    val properties = clazz.memberProperties // proses reflection

    // iterate dan cek jika memiliki annotation @NotBlank
    for (property in properties) {
        if (property.findAnnotation<NotBlank>() != null) {
            val value = property.call(request)
            when (value) {
                is String -> {
                    if ("" == value) {
                        /*
                            ValidateException adalah class yang
                            berada di file ExceptionHandling
                        */
                        throw ValidationException("${property.name} is blank")
                    }
                }
            }
        }
    }
}

fun main() {
    val request = CreateProductRequest("2", "tatang", 200)
    validateRequest(request)
}