package trial.sbdemo.app

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.assertEquals

class MessageUtilsTest {
    @Test fun testGetMessage() {
        assertEquals("Hello      World!", MessageUtils.getMessage())
    }
}
