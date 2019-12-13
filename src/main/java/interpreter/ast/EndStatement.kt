package interpreter.ast

import interpreter.Interpreter
import kotlin.system.exitProcess

class EndStatement(label: Int) : Statement(label) {
  override fun toString(): String {
    return "END"
  }

  override fun run(interpreter: Interpreter?) {
    exitProcess(0)
  }
}