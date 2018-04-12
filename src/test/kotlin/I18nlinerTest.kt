import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class I18nlinerTest {

  @Before
  fun reset() {
    I18nliner.setPath("src/main/resources")
    I18nliner.setLocale("en_US")
  }

  @Test
  fun `sets the path with setPath()`() {
    I18nliner.setPath("")
    assertEquals(
      I18nliner.getPath(),
      ""
    )
    I18nliner.setPath("src/main/resources")
    assertEquals(
      I18nliner.getPath(),
      "src/main/resources"
    )
  }

  @Test
  fun `sets the locale with setLocale()`() {
    I18nliner.setLocale("pt_BR")
    assertEquals(
      I18nliner.getLocale(),
      "pt_BR"
    )
  }

  @Test
  fun `t() translates literal strings with no variables`() {
    I18nliner.setLocale("en_US")
    assertEquals(
      I18nliner.t("I am only but a test!"),
      "I am only but a test!"
    )
  }

  @Test
  fun `t() pulls the translation from the current locale`() {
    I18nliner.setLocale("pt_BR")
    assertEquals(
      I18nliner.t("I am only but a test!"),
      "Eu sou apenas um teste!"
    )
  }

  @Test
  fun `t() returns the string if there's no corresponding translation`() {
    assertEquals(
      I18nliner.t("An unused string"),
      "An unused string"
    )
  }

  @Test
  fun `interprets variables`() {
    I18nliner.setLocale("pt_BR")
    assertEquals(
      I18nliner.t(
        "The name is { lastName }. { firstName } { lastName }.",
        hashMapOf(
          "firstName" to I18nliner.t("James"),
          "lastName" to "Bond"
        )
      ),
      "Meu nome é Bond. Tiago Bond."
    )
  }

  @Test
  fun `pluralization returns the singular message if count is 1`() {
    assertEquals(
      I18nliner.t(
        hashMapOf(
          "one" to "There is one light!",
          "plural" to "There are { count } lights!",
          "count" to 1
        )
      ),
      "There is one light!"
    )
  }

  @Test
  fun `pluralization returns the plural message if count is a number other than 1`() {
    assertEquals(
      I18nliner.t(
        hashMapOf(
          "one" to "There is one light!",
          "plural" to "There are { count } lights!",
          "count" to 0
        )
      ),
      "There are 0 lights!"
    )

    assertEquals(
      I18nliner.t(
        hashMapOf(
          "one" to "There is one light!",
          "plural" to "There are { count } lights!",
          "count" to 3.14
        )
      ),
      "There are 3.14 lights!"
    )

    assertEquals(
      I18nliner.t(
        hashMapOf(
          "one" to "There is one light!",
          "plural" to "There are { count } lights!",
          "count" to -999
        )
      ),
      "There are -999 lights!"
    )
  }

  @Test(expected = NullPointerException::class)
  fun `pluralization requires a "one" property`() {
    I18nliner.t(
      hashMapOf(
        "plural" to "asdf",
        "count" to 42
      )
    )
  }

  @Test(expected = NullPointerException::class)
  fun `pluralization requires a "plural" property`() {
    I18nliner.t(
      hashMapOf(
        "one" to "asdf",
        "count" to 42
      )
    )
  }

  @Test(expected = NullPointerException::class)
  fun `pluralization requires a "count" property`() {
    I18nliner.t(
      hashMapOf<String, Any>(
        "one" to "asdf",
        "plural" to "wat"
      )
    )
  }
}