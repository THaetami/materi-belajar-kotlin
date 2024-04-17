package belajar.unit.test

import jdk.jfr.Enabled
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.condition.*
import org.junit.jupiter.api.condition.DisabledIfSystemProperty
import org.junit.jupiter.api.condition.EnabledIfSystemProperty

// test berdasarkan beberapa kondisi
class ConditionalTest {

    @Test
    @EnabledOnOs(value = [OS.WINDOWS, OS.LINUX])
    fun enableOnWindowsOrLinux() {
        println("test berjalan karena di windows")
    }

    @Test
    @DisabledOnOs(value = [OS.WINDOWS])
    fun disabledOnWindows() {
        println("test tidak berjalan karna di windows")
    }

//    @Test
//    @EnabledOnJre(value = [JRE.JAVA_21])
//    fun onlyRunOnJava21() {
//        //
//    }
//
//    @Test
//    @DisabledOnJre(value = [JRE.JAVA_21])
//    fun disabledRunOnJava21() {
//        //
//    }
//
//    @Test
//    @EnabledForJreRange(min = JRE.JAVA_18, max = JRE.JAVA_21)
//    fun onlyRunOnJava18ToJava21() {
//        //
//    }

//    @Test
//    @DisabledForJreRange(min = JRE.JAVA_18, max = JRE.JAVA_21)
//    fun disabledRunOnJava18ToJava21() {
//        //
//    }

    @Test
    @EnabledIfSystemProperties(
        value = [
            EnabledIfSystemProperty(named = "java.vendor", matches = "Azul Systems, Inc."),
            EnabledIfSystemProperty(named = "os.name", matches = "Windows 10")
        ]
    )
    fun enabledOnAzulzuluAndWindows() {
        // Your test code here
    }

    @Test
    @DisabledIfSystemProperty(named = "java.vendor", matches = "Azul Systems, Inc.")
    fun disabledOnAzulzulu() {
        // Your test code here
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "PROFILE", matches = "DEV")
    fun enabledOnDev() {

    }

    @Test
    @DisabledIfEnvironmentVariable(named = "PROFILE", matches = "DEV")
    fun disabledOnDev() {

    }


//    @Test
//    fun printSystemProperties() { // mengecek system property
//        System.getProperties().forEach { key, value ->
//            println("$key => $value")
//        }
//    }



}