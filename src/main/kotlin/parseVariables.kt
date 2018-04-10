import java.util.regex.Pattern

fun parseVariables(msg: String): MutableSet<String> {
  val variables = mutableSetOf<String>()
  val matcher = Pattern.compile("\\{\\s*(\\w*)\\s*}").matcher(msg)
  while (matcher.find()) {
    variables.add(
      matcher.group(1)
    )
  }
  return variables
}