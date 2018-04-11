# I18nliner-kt

_I18n made simple - for Kotlin!_

---

## Key Features
* (Coming soon!) Auto-generation of your default translation file (en_US by default).
No more manual management of your english translation file
(or whatever your team's lingua franca is).
* Use human language strings in your code - no more referencing
internationalized strings by their key in the translation file.
* (Coming soon!) Support for pluralization constructs.
* (Coming soon!) Support for variable formats (date, number, currency, etc).

---

## Usage
1. Call `I18nliner.setPath("path/to/your/language/files")`, so that I18nliner
knows where to find your translations.
2. Call `I18nliner.setLocale()` with the language tag you want your app to display
(if using `en_US` you can skip this step, that's the default).
    * Whenever the displayed locale changes (e.g. UserA logs out, UserB who has
    their profile set to `pt_BR` logs in),
    you will need to call `setLocale()` with the new locale, so that all
    subsequent calls to `t()` will reflect the correct language.
3. When developing, call `I18nliner.t("A string to be translated!")` instead of
the literal string.
This will pull from the current locale and return the translated string.

---

## Guidelines and Gotchas

#### Only pass literal strings to `t()`
Do not pass variables, or condition which argument you pass.
This is because we do static code analysis to find all calls to `t()`
and generate the default translation file, and every sentence needs to be a
literal string for that to happen.
If you need to condition on which string to translate, just repeat the call:
```kotlin
// bad, static analysis can't figure out what strings need to be translated
I18nliner.t(
  if (someCondition) "That condition was true" else "No way man"
)
```

```kotlin
// correct, static analysis can easily parse both branches
if (someCondition)
  I18nliner.t("That condition was true")
else
  I18nliner.t("No way man")
```

#### Interpolation over concatenation
Do not concatenate strings together. Instead, write one string for your whole sentence.
If you need to include variables, just stick them inline and I18nliner will
interpolate them. Again, this enables static analysis, but it also makes it easier
on your translators and ensures better translations. Many languages have entirely
different structures that makes it impossible to translate ( A ) + ( B ) + ( C ) as
one sentence. It's generally better to translate ( A + B + C ).

```kotlin
// bad, static analysis has a hard time making a sentence out of this
I18nliner.t("Your exam is due on " + I18nliner.t("Tuesday"))
```

```kotlin
// correct, one sentence with interpolated variables.
// your translators can see the entire logic of the sentence.
I18nliner.t(
  "Your exam is due on { dueDate }",
  hashMapOf("dueDate" to I18nliner.t("Tuesday"))
)
```

---

## Example

in `resources/translations/messages_en_US.properties`:
```
inertia_is_a_property_of_matter=Inertia is a property of matter!
the_name_is_lastname_firstname_lastname=The name is { lastName }. { firstName } { lastName }
```

in `resources/translations/messages_pt_BR.properties`:
```
inertia_is_a_property_of_matter=A inércia é uma propriedade da matéria!
the_name_is_lastname_firstname_lastname=Meu nome é { lastName }. { firstName } { lastName }.
```

in `main.kt`:
```kotlin
fun main(args: Array<String>) {
  I18nliner.setPath("resources/translations/") // or wherever your translation files live

  println(
    I18nliner.t("Inertia is a property of matter!") // Inertia is a property of matter!
  )

  // upon changing locale, calls to t() will produce different results
  I18nliner.setLocale("pt_BR")

  println(
    I18nliner.t("Inertia is a property of matter!") // A inércia é uma propriedade da matéria!
  )

  // variable interpolation
  println(
    I18nliner.t(
      "The name is { lastName }. { firstName } { lastName }.", // Meu nome é Bond. James Bond.
      hashMapOf(
        "firstName" to "James",
        "lastName" to "Bond"
      )
    )
  )
}
```

---

## Development setup

### Intellij
1. Import Project
2. Check `Import project from external model` and select Gradle

### Testing
`gradle test`
