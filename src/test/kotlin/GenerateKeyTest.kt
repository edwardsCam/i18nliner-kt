import org.junit.Assert.*
import org.junit.Test

class GenerateKeyTest {

  @Test
  fun `converts to lowercase`() {
    assertEquals(
      generateKey("myLeftFoot"),
      "myleftfoot_2787755297"
    )
  }

  @Test
  fun `converts spaces to underscore`() {
    assertEquals(
      generateKey("a bit of stormy weather"),
      "a_bit_of_stormy_weather_3988560715"
    )
  }

  @Test
  fun `removes non-alphanumerics`() {
    assertEquals(
      generateKey("egads!"),
      "egads_1188453862"
    )
    assertEquals(
      generateKey("oh me, oh my"),
      "oh_me_oh_my_2599975595"
    )
    assertEquals(
      generateKey("no. i don't think so"),
      "no_i_dont_think_so_749203091"
    )
    assertEquals(
      generateKey("here's my answer: 42... got it? great! :)"),
      "heres_my_answer_42_got_it_great_4201846468"
    )
  }

  @Test
  fun `parses out variable interpolation`() {
    val expected = "my_name_is_name_1811303149"
    assertEquals(expected, generateKey("my name is { name }"))
    assertEquals(expected, generateKey("my name is {name}"))
    assertEquals(expected, generateKey("my name is {      name    }"))
  }

  @Test
  fun `trims excessive whitespace`() {
    assertEquals(
      generateKey("  some       greasy   bacon            "),
      "some_greasy_bacon_2384218784"
    )
  }
}