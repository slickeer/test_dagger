package com.example.swipemenu

import android.content.Context
import android.content.SharedPreferences
import com.google.common.truth.Truth
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock


class TestStorageTest {

    val sharedPref = mock<SharedPreferences>()

    lateinit var helper: TestStorage

    @Before
    fun setup() {
        helper = TestStorage(sharedPref)
    }

    @Test
    fun test() {

        val test = SomeTestClass("something")

        Mockito.`when`(sharedPref.getString("KEY", null)).doReturn(Gson().toJson(test))

        val sut = helper.get()

        Truth.assertThat(sut).isEqualTo(test)

    }
}