package by.itacademy.korobchits

import org.junit.Assert
import org.junit.Test

class FruitUnitTest {

    @Test
    fun test_1() {
        val result = checkWin(
            listOf(
                listOf("orange"),
                listOf("apple", "apple"),
                listOf("banana", "orange", "apple"),
                listOf("banana")
            ),
            listOf("orange", "apple", "apple", "banana", "orange", "apple", "banana")
        )

        Assert.assertTrue(result == 1)
    }

    @Test
    fun test_2() {
        val result = checkWin(
            listOf(
                listOf("apple", "apricot"),
                listOf("banana", "anything", "guava"),
                listOf("papaya", "anything")
            ),
            listOf("banana", "orange", "guava", "apple", "apricot", "papaya", "kiwi")
        )

        Assert.assertTrue(result == 0)
    }

    @Test
    fun test_3() {
        val result = checkWin(
            listOf(
                listOf("orange"),
                listOf("apple", "apple"),
                listOf("banana", "orange", "apple"),
                listOf("banana")
            ),
            listOf("orange", "apple", "apple", "guava", "banana", "orange", "apple", "banana")
        )

        Assert.assertTrue(result == 1)
    }
}