fun interpret(msg: String, args: HashMap<String, Any>): String = (
  parseVariables(msg).fold(msg) { result, variable ->
    if (args.containsKey(variable)) {
      result.replace(
        regex(variable),
        args[variable].toString()
      )
    } else {
      warn("Missing a value for $variable")
      result
    }
  }
)

private fun regex(variable: String): Regex = "\\{\\s*$variable\\s*}".toRegex()
