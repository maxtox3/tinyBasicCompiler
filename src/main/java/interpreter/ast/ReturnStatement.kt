package interpreter.ast

import interpreter.Interpreter

class ReturnStatement(label: Int) : Statement(label) {
  override fun toString(): String {
    return "RETURN"
  }

  override fun run(interpreter: Interpreter?) {
    interpreter?.setStatement(interpreter.stack.pop())
  }
}