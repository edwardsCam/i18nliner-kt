private val cache: HashMap<String, String> = hashMapOf()

fun getTranslationPath(base: String, locale: String): String {
  val cacheKey = "$base $locale"
  if (cache[cacheKey] == null) {
    cache[cacheKey] = String.format(
      if (base.endsWith('/'))
        "%smessages_%s.properties"
      else
        "%s/messages_%s.properties",
      base,
      locale
    )
  }
  return cache[cacheKey]!!
}
