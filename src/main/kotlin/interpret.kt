fun interpret(msg: String, args: HashMap<String, Any>): String = (
  parseVariables(msg).fold(msg) { result, variable ->
    if (args.containsKey(variable)) {
      result.replace(
        regex(variable),
        args[variable].toString()
      )
    } else {
      println("I18nliner-kt: Missing a value for $variable")
      result
    }
  }
)

private fun regex(variable: String): Regex = "\\{\\s*$variable\\s*}".toRegex()
