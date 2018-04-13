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
    args: HashMap<String, Any> = hashMapOf(),
    locale: String = _locale
  ): String {
    if (_path.isEmpty()) {
      warn("Translation file not found! You may need to call setPath to set the location of your translation files.")
      return msg
    }

    val translations = getTranslations(_path, locale) ?: return msg
    val key = generateKey(msg)
    val translation = translations[key]
    if (translation == null) {
      warn("Did not find a translation for \"$msg\"! (key: $key)")
      return msg
    }
    return interpret(translation, args)
  }

  fun t(args: HashMap<String, Any>): String {
    args["one"] ?: warn("Pluralization: You need to provide a \"one\" string!")
    args["plural"] ?: warn("Pluralization: You need to provide a \"plural\" string!")
    args["count"] ?: warn("Pluralization: You need to provide a \"count\" string!")
    val singularMsg = args["one"]!!.toString()
    val pluralMsg = args["plural"]!!.toString()
    val count = args["count"]!!
    return t(
      if (count == 1) singularMsg else pluralMsg,
      args
    )
  }

  fun t(msg: String, locale: String): String = t(msg, hashMapOf(), locale)
}
