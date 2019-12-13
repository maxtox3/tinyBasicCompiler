package interpreter.ast

import interpreter.*

class ArithmeticExpression(
  private val operator: OperatorType,
  private val left: Expression,
  private val right: Expression
) : Expression() {

  override fun toString(): String {
    return "($left $operator $right)"
  }

  override fun evaluate(interpreter: Interpreter?): Value<Any>? {
    val leftval = left.evaluate(interpreter)?.value as Int
    val rightval = right.evaluate(interpreter)?.value as Int
    val value = when (operator) {
      OperatorType.ADD -> leftval + rightval
      OperatorType.SUBTRACT -> leftval - rightval
      OperatorType.MULTIPLY -> leftval * rightval
      OperatorType.DIVIDE -> leftval / rightval
      else -> throw AssertionError()
    }
    return IntegerValue(value)
  }

}