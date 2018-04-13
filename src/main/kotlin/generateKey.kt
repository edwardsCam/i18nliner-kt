import java.util.zip.CRC32

private val cache: HashMap<String, String> = hashMapOf()

// TODO handle special characters, encoding
fun generateKey(pattern: String): String {
  val normalized = normalize(pattern)
  if (cache[normalized] == null) {
    val crc = CRC32()
    crc.update(normalized.toByteArray())
    cache[normalized] = normalized
      .replace("[^A-Za-z0-9 ]".toRegex(), "")
      .trim()
      .replace("\\s+".toRegex(), "_")
      .toLowerCase()
      .plus('_')
      .plus(crc.value)
  }
  return cache[normalized]!!
}
