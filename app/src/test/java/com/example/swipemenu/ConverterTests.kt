package com.example.swipemenu

import com.google.common.truth.Truth
import com.google.gson.Gson
import com.squareup.moshi.Moshi
import org.junit.Test
import kotlin.reflect.full.declaredMemberProperties

class ConverterTests {


    val jsonTest = "{\n" +
            "   \"test\":\"someString\",\n" +
            "   \"someString\":\"someString\",\n" +
            "   \"testInt\":1,\n" +
            "   \"testArray\":[\n" +
            "      \"test\",\n" +
            "      \"test\"\n" +
            "   ],\n" +
            "   \"testArrayNext\":null\n" +
            "}".trimMargin()




    @Test
    fun testGSON() {
        val testObj = Gson().fromJson(jsonTest, SomeTestClass::class.java)

        testObj.detectNullability()

        //Truth.assertThat(testObj.test).isNotNull()
    }

    @Test
    fun testMSOHI() {
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(SomeTestClass::class.java)

        val testObj = adapter.fromJson(jsonTest)

        Truth.assertThat(testObj?.test).isNotNull()
    }


    fun Any.detectNullability() {
        this::class.declaredMemberProperties.forEach {
            if(!it.returnType.isMarkedNullable) {
                if(it.getter.call(this) == null)
                    throw IllegalStateException("Field |${it.name}| marked as non-nullable is null")
            }
        }
    }
}


data class TestClass(
    val test: String,
    val testInt: Int,
    val someString: String?,
    val testArray: ArrayList<String>?,
    val testArrayNext: ArrayList<String>
)