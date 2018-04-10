import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class I18nlinerTest {

  @Before
  fun reset() {
    I18nliner.setPath("src/main/resources")
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
  fun `defaults the locale to en_US`() {
    assertEquals(
      I18nliner.getLocale(),
      "en_US"
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
}