# I18nliner-kt

_I18n made simple - for Kotlin!_

---

## Key Features
* No more manual management of english translation files
(or whatever your default development language is)
* Use human language strings in your code - no more referencing
internationalized strings by their keys in the language file

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

## Development setup

### Intellij
1. Import Project
2. Check `Import project from external model` and select Gradle
