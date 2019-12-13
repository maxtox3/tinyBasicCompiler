package interpreter.ast

import interpreter.Interpreter

class GotoStatement(label: Int, val number: Expression) :
  Statement(label) {

  override fun toString(): String {
    return "GOTO $number"
  }

  override fun run(interpreter: Interpreter?) {
    interpreter!!.setStatement((number as NumberExpression).number)
  }

}