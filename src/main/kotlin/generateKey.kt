fun generateKey(pattern: String): String {
  // TODO handle special characters, encoding, variables
  return pattern
    .replace("{", "")
    .replace("}", "")
    .trim()
    .replace("\\s+".toRegex(), "_")
    .toLowerCase()
}
