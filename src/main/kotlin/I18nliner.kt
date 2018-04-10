package main

import java.io.File

object I18nliner {
  private var _path = ""
  private var _locale = "en_US"

  fun setPath(path: String) {
    _path = path
  }

  fun setLocale(locale: String) {
    _locale = locale
  }

  fun t(msg: String): String? {
    if (_path.isEmpty()) {
      println("Translation file not found! You may need to call setPath to set the location of your translation files.")
      return msg
    }
    val translationPath = getTranslationPath(_path, _locale)
    val key = generateKey(msg)
    val translated = File(translationPath)
      .readLines()
      .map { it.split('=', limit = 2)}
      .find { it[0] == key }
    if (translated == null) {
      println("Did not find a translation for $msg (key: $key)")
      return msg
    }
    return interpret(translated[1])
  }

  private fun interpret(sentence: String): String {
    // TODO interpolated variables
    return sentence
  }
}