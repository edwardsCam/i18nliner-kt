import main.I18nliner

fun main(args: Array<String>) {
  I18nliner.setPath("src/main/resources/")

  println(I18nliner.t("I am only but a test"))
  println(I18nliner.t("I have no translations"))

  println()

  I18nliner.setLocale("pt_BR")
  println(I18nliner.t("I am only but a test")) // Eu sou apenas um teste
}
