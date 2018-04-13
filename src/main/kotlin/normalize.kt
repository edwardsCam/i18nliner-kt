private val cache: HashMap<String, String> = hashMapOf()

fun normalize(pattern: String): String {
  if (cache[pattern] == null) {
    cache[pattern] = parseVariables(pattern).fold(pattern) { normalized, varName ->
      normalized.replace(
        msgVarRegex(varName),
        "{ $varName }"
      )
    }
  }
  return cache[pattern]!!
}

fun msgVarPattern(p: String = "(\\w*)"): String = "\\{\\s*$p\\s*}"
fun msgVarRegex(varName: String): Regex = Regex(msgVarPattern(varName))