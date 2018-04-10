// TODO handle special characters, encoding, variables
fun generateKey(pattern: String): String  = (
  pattern
    .replace("{", "")
    .replace("}", "")
    .trim()
    .replace("\\s+".toRegex(), "_")
    .toLowerCase()
)
