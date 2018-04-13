import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class I18nlinerTest {

  @Before
  fun reset() {
    I18nliner.setPath("src/test/resources")
    I18nliner.setLocale("en_US")
  }

  @Test
  fun `sets the path with setPath()`() {
    I18nliner.setPath("")
    assertEquals(
      I18nliner.getPath(),
      ""
    )
    I18nliner.setPath("src/test/resources")
    assertEquals(
      I18nliner.getPath(),
      "src/test/resources"
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
        hashMapOf<String, Any>(
          "firstName" to I18nliner.t("James"),
          "lastName" to "Bond"
        )
      ),
      "Meu nome é Bond. Tiago Bond."
    )
  }

  @Test
  fun `pluralization returns the "zero" message if count is 0`() {
    assertEquals(
      I18nliner.tPlural(
        0,
        "There aren't any lights!",
        "There is one light!",
        "There are { count } lights!"
      ),
      "There aren't any lights!"
    )
  }

  @Test
  fun `pluralization returns the "one" message if count is 1`() {
    assertEquals(
      I18nliner.tPlural(
        1,
        "There aren't any lights!",
        "There is one light!",
        "There are { count } lights!"
      ),
      "There is one light!"
    )
  }

  @Test
  fun `pluralization returns the "other" message if count is a number other than 1 or 0`() {
    assertEquals(
      I18nliner.tPlural(
        3.14,
        "There aren't any lights!",
        "There is one light!",
        "There are { count } lights!"
      ),
      "There are 3.14 lights!"
    )

    assertEquals(
      I18nliner.tPlural(
        -999,
        "There aren't any lights!",
        "There is one light!",
        "There are { count } lights!"
      ),
      "There are -999 lights!"
    )
  }

  @Test
  fun `pluralization allows other args`() {
    assertEquals(
      I18nliner.tPlural(
        0,
        "There aren't any lights! But there is a { object }.",
        "There is one light!",
        "There are { count } lights!",
        hashMapOf("object" to "flashlight")
      ),
      "There aren't any lights! But there is a flashlight."
    )

    assertEquals(
      I18nliner.tPlural(
        1,
        "There aren't any lights!",
        "There is one light! We don't need the { object }.",
        "There are { count } lights!",
        hashMapOf("object" to "flashlight")
      ),
      "There is one light! We don't need the flashlight."
    )

    assertEquals(
      I18nliner.tPlural(
        3.14,
        "There aren't any lights!",
        "There is one light!",
        "There are { count } lights! We don't need the { object }.",
        hashMapOf("object" to "flashlight")
      ),
      "There are 3.14 lights! We don't need the flashlight."
    )
  }

  @Test
  fun `pluralization allows you to pass an override locale`() {
    I18nliner.setLocale("en_US")
    assertEquals(
      I18nliner.tPlural(
        3.14,
        "There aren't any lights!",
        "There is one light!",
        "There are { count } lights! We don't need the { object }.",
        hashMapOf("object" to I18nliner.t("flashlight", locale = "pt_BR")),
        locale = "pt_BR"
      ),
      "Existem 3.14 luzes! Nós não precisamos da lanterna."
    )
  }

  @Test
  fun `allows you to pass an override locale`() {
    I18nliner.setLocale("en_US")
    assertEquals(
      I18nliner.t("I am only but a test!", locale = "pt_BR"),
      "Eu sou apenas um teste!"
    )
    assertEquals(
      I18nliner.t("There are { count } lights!", hashMapOf("count" to 42), "pt_BR"),
      "Há 42 luzes!"
    )
  }
}
