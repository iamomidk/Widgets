# Widgets

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

## Description

Widgets is a collection of customizable UI widgets for Android app development in Kotlin. These widgets are designed to provide developers with a versatile set of components that can be easily tailored to suit their app's unique requirements.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)
- [Acknowledgments](#acknowledgments)

## Installation

To include Widgets in your Android project, follow these steps:

1. Add the following dependency to your app's build.gradle file:

```gradle
implementation 'com.aperfectpolygon.widgets:widgets:1.0.0'
```
Synchronize your project with the Gradle files.

## Usage
- AutoCompleteTextView
```kotlin
// Example of how to use the AutoCompleteTextView widget.
val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
autoCompleteTextView.setAdapter(ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, suggestions))
```
- Button
```kotlin
// Example of how to use the Button widget.
val button = findViewById<Button>(R.id.button)
button.text = "Click Me!"
button.setOnClickListener { /* Handle button click event */ }
```
- Chip
```kotlin
// Example of how to use the Chip widget.
val chipGroup = findViewById<ChipGroup>(R.id.chipGroup)
val chip = Chip(this)
chip.text = "Tag 1"
chipGroup.addView(chip)
```
- EditText
```kotlin
// Example of how to use the EditText widget.
val editText = findViewById<EditText>(R.id.editText)
editText.addTextChangedListener(object : TextWatcher {
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        /* Handle text changes */
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun afterTextChanged(s: Editable?) {}
})
```
- ErrorHandler
```kotlin
// Example of how to use the ErrorHandler utility class.
try {
    // Code that may throw an exception
} catch (e: Exception) {
    ErrorHandler.showErrorToast(this, e.message)
}
```
- Font Extensions
```kotlin
// Example of how to use the Font Extensions.
val textView = findViewById<TextView>(R.id.textView)
textView.applyFont("custom_font.ttf")
```
- Loading
```kotlin
// Example of how to use the Loading widget.
val loadingView = findViewById<LoadingView>(R.id.loadingView)
loadingView.startLoading()
```
- TextInputLayout
```kotlin
// Example of how to use the TextInputLayout widget.
val textInputLayout = findViewById<TextInputLayout>(R.id.textInputLayout)
textInputLayout.hint = "Enter your name"
```
- TextView
```kotlin
// Example of how to use the TextView widget.
val textView = findViewById<TextView>(R.id.textView)
textView.setTextColor(Color.RED)
```
- TextView with Suffix or Prefix
```kotlin
// Example of how to use the TextView with Suffix or Prefix widget.
val textView = findViewById<TextViewWithSuffix>(R.id.textView)
textView.setSuffixText(" kg")
textView.setPrefixText("$")
```

## Contributing
We welcome contributions to make Widgets even better. If you want to contribute, please follow these steps:

- Fork the repository and clone it to your local machine.
- Create a new branch for your feature or bug fix.
- Make your changes and ensure they are well-tested.
- Commit your changes and push them to your forked repository.
- Submit a pull request, detailing the changes you made.
- Please ensure your code adheres to our coding standards and includes appropriate tests.

## License
This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments
We want to express our gratitude to the open-source community for their invaluable contributions and inspiration.

