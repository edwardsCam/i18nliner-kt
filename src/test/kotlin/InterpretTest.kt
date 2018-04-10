import org.junit.Assert.*
import org.junit.Test

class InterpretTest {

  @Test
  fun `returns the same string if there are no interpolations`() {
    assertEquals(
      interpret("asdf"),
      "asdf"
    )
  }

  @Test
  fun `interpolates variables with the provided values`() {
    assertEquals(
      interpret("my name is count { name }", hashMapOf("name" to "chocula")),
      "my name is count chocula"
    )
    assertEquals(
      interpret("{ count }! { count } itsy bitsy { insects }!", hashMapOf("count" to "two", "insects" to "spiders")),
      "two! two itsy bitsy spiders!"
    )
  }

  @Test
  fun `does not interpolate variables if you don't provide values`() {
    assertEquals(
      interpret("{ val1 } is good, but { val2 } is not", hashMapOf("val1" to "cereal")),
      "cereal is good, but { val2 } is not"
    )
  }

  @Test
  fun `can handle any value type, just converts it to string`() {
    assertEquals(
      interpret(
        "{ v1 } is a number, { v2 } is a bool, { v3 } is a string",
        hashMapOf(
          "v1" to 42,
          "v2" to false,
          "v3" to "this one"
        )
      ),
      "42 is a number, false is a bool, this one is a string"
    )
  }

  @Test
  fun `ignores excessive whitespace in variables`() {
    assertEquals(
      interpret("my name is {        name  }", hashMapOf("name" to "Cameron")),
      "my name is Cameron"
    )
  }
}