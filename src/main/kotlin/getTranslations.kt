import java.io.File
import java.io.FileNotFoundException

private val cache: HashMap<String, Map<String, String>> = hashMapOf()

fun getTranslations(basePath: String, locale: String): Map<String, String>? {
  val path = getTranslationPath(basePath, locale)
  return if (cache[path] != null) cache[path] else try {
    cache[path] = File(path).readLines()
      .fold(hashMapOf()) { translations, line ->
        val pair = line.split('=', limit = 2) // [key, value]
        translations[pair[0]] = pair[1]
        translations
      }
    cache[path]
  } catch (e: FileNotFoundException) {
    warn("No translation file found for $locale! (looking for $path)")
    null
  }
}