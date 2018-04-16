object I18nliner {
  private var _path = ""
  private var _locale = "en_US"
  private var _warn_missing_translations = false

  fun setPath(path: String) {
    _path = path
  }

  fun setLocale(locale: String) {
    _locale = locale
  }

  fun warnOnMissingTranslations(warning: Boolean) {
    _warn_missing_translations = warning
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
      if (_warn_missing_translations) {
        warn("Did not find a translation for \"$msg\"! (key: $key)")
      }
      return msg
    }
    return interpret(translation, args)
  }

  fun tPlural(
    count: Number,
    zero: String?,
    one: String?,
    other: String?,
    args: HashMap<String, Any> = hashMapOf(),
    locale: String = _locale
  ): String {
    args["count"] = count
    return t(
      when (count) {
        0 -> zero!!
        1 -> one!!
        else -> other!!
      },
      args,
      locale
    )
  }

  fun tGender(
    gender: String,
    male: String?,
    female: String?,
    other: String?,
    args: HashMap<String, Any> = hashMapOf(),
    locale: String = _locale
  ): String = (
    t(
      when (gender.toLowerCase()) {
        "m", "male" -> male!!
        "f", "female" -> female!!
        else -> other!!
      },
      args,
      locale
    )
  )
}
