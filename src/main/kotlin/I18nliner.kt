import java.io.File
import java.io.FileNotFoundException

object I18nliner {
  private var _path = ""
  private var _locale = "en_US"

  fun setPath(path: String) {
    _path = path
  }

  fun setLocale(locale: String) {
    _locale = locale
  }

  fun getPath(): String = _path
  fun getLocale(): String = _locale

  fun t(
    msg: String,
    args: HashMap<String, Any> = hashMapOf()
  ): String {
    if (_path.isEmpty()) {
      warn("Translation file not found! You may need to call setPath to set the location of your translation files.")
      return msg
    }

    val translationFilePath = getTranslationPath(_path, _locale)
    val translations = try {
      File(translationFilePath).readLines()
    } catch (e: FileNotFoundException) {
      warn("No translation file found for $_locale! (looking for $translationFilePath)")
      null
    } ?: return msg

    val key = generateKey(msg)
    val translatedKeyValue = translations
      .map { it.split('=', limit = 2)}
      .find { it[0] == key }

    if (translatedKeyValue == null) {
      warn("Did not find a translation for \"$msg\"! (key: $key)")
      return msg
    }
    return interpret(translatedKeyValue[1], args)
  }
}
