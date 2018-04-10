// TODO handle special characters, encoding
fun generateKey(pattern: String): String  = (
  pattern
    .replace("[^A-Za-z0-9 ]".toRegex(), "")
    .trim()
    .replace("\\s+".toRegex(), "_")
    .toLowerCase()
)
