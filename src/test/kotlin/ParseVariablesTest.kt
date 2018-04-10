import org.junit.Assert.*
import org.junit.Test

class ParseVariablesTest {

  @Test
  fun `parses out everything within { } tags`() {
    assertEquals(
      parseVariables("a string with a { variable }").size,
      1
    )
    assertEquals(
      parseVariables("a string with a { variable }").first(),
      "variable"
    )
  }

  @Test
  fun `ignores whitespace between { } tags`() {
    assertEquals(
      parseVariables("{ asdf }"),
      parseVariables("{       asdf}")
    )
  }

  @Test
  fun `does not contain duplicates (uses a Set)`() {
    assertEquals(
      parseVariables("{ asdf }"),
      parseVariables("{ asdf } oh and by the way also { asdf }")
    )
  }

  @Test
  fun `can parse out any number of variables`() {
    assertEquals(
      parseVariables("{var1}{var2}asdf{var3}aaa{var4}..... {var5}").size,
      5
    )
  }
}