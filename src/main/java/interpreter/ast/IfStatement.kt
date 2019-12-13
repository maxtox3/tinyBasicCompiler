package interpreter.ast

import interpreter.Interpreter
import interpreter.ast.OperatorType

class IfStatement(
  label: Int,
  private val leftExpression: Expression,
  private val rightExpression: Expression,
  private val relationalOperator: OperatorType,
  val statement: Statement
) : Statement(label) {

  override fun toString(): String {
    return "IF $leftExpression $relationalOperator $rightExpression THEN $statement "
  }

  override fun run(interpreter: Interpreter?) {
    val left = leftExpression.evaluate(interpreter)?.value as Int
    val right = rightExpression.evaluate(interpreter)?.value as Int
    val flag: Boolean
    flag = when (relationalOperator) {
      OperatorType.EQUAL -> left == right
      OperatorType.LESSTHAN -> left < right
      OperatorType.LESSTHANOREQUAL -> left <= right
      OperatorType.GREATERTHAN -> left > right
      OperatorType.GREATERTHANOREQUAL -> left >= right
      OperatorType.NOTEQUAL -> left != right
      else -> false
    }
    if (flag) {
      statement.run(interpreter)
    }
  }

}