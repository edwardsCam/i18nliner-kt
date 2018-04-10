import org.junit.Assert.*
import org.junit.Test

class GenerateKeyTest {

  @Test
  fun `converts to lowercase`() {
    assertEquals(
      generateKey("myLeftFoot"),
      "myleftfoot"
    )
  }

  @Test
  fun `converts spaces to underscore`() {
    assertEquals(
      generateKey("a bit of stormy weather"),
      "a_bit_of_stormy_weather"
    )
  }

  @Test
  fun `removes non-alphanumerics`() {
    assertEquals(
      generateKey("egads!"),
      "egads"
    )
    assertEquals(
      generateKey("oh me, oh my"),
      "oh_me_oh_my"
    )
    assertEquals(
      generateKey("no. i don't think so"),
      "no_i_dont_think_so"
    )
    assertEquals(
      generateKey("here's my answer: 42... got it? great! :)"),
      "heres_my_answer_42_got_it_great"
    )
  }

  @Test
  fun `parses out variable interpolation`() {
    assertEquals(
      generateKey("my name is { name }"),
      "my_name_is_name"
    )
    assertEquals(
      generateKey("my name is {name}"),
      "my_name_is_name"
    )
    assertEquals(
      generateKey("my name is {      name    }"),
      "my_name_is_name"
    )
  }

  @Test
  fun `trims excessive whitespace`() {
    assertEquals(
      generateKey("  some       greasy   bacon            "),
      "some_greasy_bacon"
    )
  }
}