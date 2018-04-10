package main

fun generateKey(pattern: String): String {
  // TODO handle special characters, encoding, variables
  return pattern.replace(' ', '_').toLowerCase()
}
