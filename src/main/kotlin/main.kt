fun main(args: Array<String>) {
  I18nliner.setPath("src/main/resources/")

  testPrint()
  I18nliner.setLocale("pt_BR")
  testPrint()
}

fun testPrint() {
  println(
    I18nliner.t(
      "The name is { lastName }. { firstName } { lastName }.",
      hashMapOf(
        "firstName" to "James",
        "lastName" to "Bond"
      )
    )
  )
}