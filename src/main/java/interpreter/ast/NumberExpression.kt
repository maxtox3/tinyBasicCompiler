package interpreter.ast

import interpreter.*

class NumberExpression(val number: Int) : Expression() {

  override fun toString(): String {
    return number.toString()
  }

  override fun evaluate(interpreter: Interpreter?): Value<Any>? {
    return IntegerValue(number)
  }

}