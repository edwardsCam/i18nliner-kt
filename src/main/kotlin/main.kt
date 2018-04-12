fun main(args: Array<String>) {
  I18nliner.setPath("src/test/resources/")
  print(1)
  print(42)
}

fun print(count: Int) {
  println(
    I18nliner.t(
      hashMapOf(
        "one" to "There is one light!",
        "plural" to "There are { count } lights!",
        "count" to count
      )
    )
  )
}
