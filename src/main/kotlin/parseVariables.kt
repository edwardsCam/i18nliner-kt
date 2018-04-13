import java.util.regex.Pattern

private val cache: HashMap<String, MutableSet<String>> = hashMapOf()

fun parseVariables(msg: String): MutableSet<String> {
  if (cache[msg] == null) {
    val variables = mutableSetOf<String>()
    val matcher = Pattern.compile(msgVarPattern()).matcher(msg)
    while (matcher.find()) {
      variables.add(
        matcher.group(1)
      )
    }
    cache[msg] = variables
  }
  return cache[msg]!!
}
