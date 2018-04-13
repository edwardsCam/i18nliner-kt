import org.junit.Assert.*
import org.junit.Test

class NormalizeTest {

  @Test
  fun `parses out excessive whitespace in variable blocks`() {
    val expected = "I drive a { car }"
    assertEquals(expected, normalize("I drive a {car}"))
    assertEquals(expected, normalize("I drive a { car}"))
    assertEquals(expected, normalize("I drive a {      car     }"))
  }
}