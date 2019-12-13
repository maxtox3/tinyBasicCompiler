package interpreter.ast

import interpreter.Interpreter

class GosubStatement(label: Int, val number: Expression) :
  Statement(label) {

  override fun toString(): String {
    return "GOSUB $number"
  }

  override fun run(interpreter: Interpreter?) {
    interpreter?.stack?.push(nextStatement?.label)
    interpreter?.setStatement((number as NumberExpression).number)
  }

}