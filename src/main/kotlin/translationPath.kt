fun getTranslationPath(base: String, locale: String): String {
  if (base.endsWith('/')) {
    return String.format("%smessages_%s.properties", base, locale)
  }
  return String.format("%s/messages_%s.properties", base, locale)
}
