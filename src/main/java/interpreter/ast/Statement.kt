package interpreter.ast

import interpreter.Interpreter

abstract class Statement(var label: Int) {
  var nextStatement: Statement? = null

  abstract fun run(interpreter: Interpreter?)

}