import org.junit.Test
import org.junit.Assert.*
import java.io.File

class GetTranslationsTest {

  @Test
  fun `gets the translations for a given path and locale`() {
    val fileLines = File("src/test/resources/messages_pt_BR.properties").readLines()
    val translations = getTranslations("src/test/resources", "pt_BR")!!
    fileLines.forEach { line ->
      val key = line.split('=')[0]
      val translation = translations[key]
      assertEquals(
        line,
        "$key=$translation"
      )
    }
  }

  @Test
  fun `returns null if no translation file is found`() {
    assertNull(
      getTranslations("src/test/resources", "qw_ERTY")
    )
  }
}