package interpreter.ast

import interpreter.Interpreter
import java.util.*

class InputStatement(label: Int) : Statement(label) {
  private val variables: MutableList<Expression> = ArrayList()
  fun getVariables(): List<Expression> {
    return variables
  }

  fun addVariable(variable: Expression) {
    variables.add(variable)
  }

  override fun toString(): String {
    var vars = ""
    for (varr in variables) {
      vars += "$varr, "
    }
    return "INPUT $vars"
  }

  override fun run(interpreter: Interpreter?) {
    val scanner = Scanner(System.`in`)
    for (varr in variables) {
      interpreter?.setVariable(
        (varr as VariableExpression).name, scanner.next().toInt()
      )
    }
    scanner.close()
  }
}