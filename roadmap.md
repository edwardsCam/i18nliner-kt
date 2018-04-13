# Roadmap / TODOs

* Auto-generation of default translation file. A cli command
(possibly called `extract` or `dump`?) should statically read the codebase and
find every call to `I18nliner.t()`, pulling out the strings and sticking them
into a `.properties` file. This means no more manual management of
`messages_en_US.properties`
* Support for gender constructs.
* Variable format support (e.g. date, time, number, currency, etc)
* Configure the default language (currently defaults to `en_US`)
