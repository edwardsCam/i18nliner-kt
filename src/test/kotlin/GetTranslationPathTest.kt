import org.junit.Assert.*
import org.junit.Test

class GetTranslationPathTest {

  @Test
  fun `appends the locale and pathname with messages_properties`() {
    assertEquals(
      getTranslationPath("src/main", "pt_BR"),
      "src/main/messages_pt_BR.properties"
    )
  }

  @Test
  fun `handles trailing slash`() {
    assertEquals(
      getTranslationPath("src/main/", "en_US"),
      "src/main/messages_en_US.properties"
    )
  }

  @Test
  fun `handles no trailing slash`() {
    assertEquals(
      getTranslationPath("src/main", "en_US"),
      "src/main/messages_en_US.properties"
    )
  }
}