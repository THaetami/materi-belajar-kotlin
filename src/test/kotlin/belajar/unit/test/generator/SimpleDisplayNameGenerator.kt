package belajar.unit.test.generator

import org.junit.jupiter.api.DisplayNameGenerator
import java.lang.reflect.Method


class SimpleDisplayNameGenerator: DisplayNameGenerator {

    override fun generateDisplayNameForClass(p0: Class<*>): String {
        return "Test ${p0.simpleName}"
    }

    override fun generateDisplayNameForNestedClass(p0: Class<*>): String {
        return "Test ${p0.simpleName}"
    }

    override fun generateDisplayNameForMethod(p0: Class<*>, p1: Method): String {
        return "Test ${p0.simpleName}.${p1.name}"
    }

}