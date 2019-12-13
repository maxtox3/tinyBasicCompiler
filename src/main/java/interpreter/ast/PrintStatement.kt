package interpreter.ast

import interpreter.Interpreter
import java.util.*

class PrintStatement(label: Int) : Statement(label) {
  private val expressions: MutableList<Expression> = ArrayList()
  fun getExpressions(): List<Expression> {
    return expressions
  }

  fun addExpression(expression: Expression) {
    expressions.add(expression)
  }

  override fun toString(): String {
    var exprs = ""
    for (expr in expressions) {
      exprs += expr
    }
    return "PRINT $exprs"
  }

  override fun run(interpreter: Interpreter?) {
    for (expression in expressions) {
      print(expression.evaluate(interpreter))
    }
    println()
  }
}