import kotlin.properties.Delegates

/*
    Observable Properties

    * salah satu standar library kotlin untuk delegating di
      properties
    * dengan observable properties, kita bisa tahu value suatu
      properties, sebelum dan sesudah diubah
    * mendeteksi perubahan properties ketika diubah
*/

class ObservableProperties(name: String = "") {
    var name: String by Delegates.observable(name) {
        property, oldValue, newValue -> // lambda dengan tiga parameter
            println("${property.name} is changed from $oldValue to $newValue")
    }
}

fun main() {
    // penggunaan observable properties
    val observableProperties = ObservableProperties()
    observableProperties.name = "haetami"
    observableProperties.name = "diubah"
}