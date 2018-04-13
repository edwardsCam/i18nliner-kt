fun interpret(
  msg: String,
  args: HashMap<String, Any> = hashMapOf()
): String = (
  parseVariables(msg).fold(msg) { result, variable ->
    if (args.containsKey(variable)) {
      result.replace(
        msgVarRegex(variable),
        args[variable].toString()
      )
    } else {
      warn("Missing a value for $variable")
      result
    }
  }
)

