fun getTranslationPath(base: String, locale: String): String = (
  String.format(
    if (base.endsWith('/'))
      "%smessages_%s.properties"
    else
      "%s/messages_%s.properties",
    base,
    locale
  )
)
