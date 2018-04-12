fun main(args: Array<String>) {
  I18nliner.setPath("src/main/resources/")

  I18nliner.setLocale("pt_BR")
  println(
    I18nliner.t(
      hashMapOf(
        "one" to "There is one light!",
        "plural" to "There are { count } lights!",
        "count" to 1
      )
    )
  )

  println(
    I18nliner.t(
      hashMapOf(
        "one" to "There is one light!",
        "plural" to "There are { count } lights!",
        "count" to 42
      )
    )
  )
}
