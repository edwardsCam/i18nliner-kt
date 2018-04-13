fun main(args: Array<String>) {
  I18nliner.setPath("src/test/resources/")
  print(0)
  print(1)
  print(42)

  I18nliner.setLocale("pt_BR")

  print(0)
  print(1)
  print(42)
}

fun print(count: Int) {
  println(
    I18nliner.tPlural(
      count,
      "There aren't any lights!",
      "There is one light!",
      "There are { count } lights!"
    )
  )
}
