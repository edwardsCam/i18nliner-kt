fun main(args: Array<String>) {
  I18nliner.setPath("src/test/resources/")
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
