package interpreter.ast

import interpreter.*

class StringExpression(val string: String) : Expression() {

  override fun toString(): String {
    return "\"" + string + "\""
  }

  override fun evaluate(interpreter: Interpreter?): Value<Any>? {
    return StringValue(string)
  }

}