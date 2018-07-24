package cz.dmn.cpska.extensions

fun  String.trimBrackets(): String {
    var text = this
    if (!text.isEmpty() && text.get(0) == '(') text = text.substring(1)
    if (!text.isEmpty() && text.get(text.length - 1) == ')') text = text.substring(0, text.length - 1)
    return text
}