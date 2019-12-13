package interpreter.ast

import interpreter.Interpreter

class LetStatement(
  label: Int,
  private val variable: Expression,
  val expression: Expression
) : Statement(label) {

  override fun toString(): String {
    return "LET $variable = $expression"
  }

  override fun run(interpreter: Interpreter?) {
    expression.evaluate(interpreter)?.value.let {
      it?.let { it1 -> interpreter?.setVariable((variable as VariableExpression).name, it1) }
    }
  }

}