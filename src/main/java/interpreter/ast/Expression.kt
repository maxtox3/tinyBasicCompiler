package interpreter.ast

import interpreter.Interpreter
import interpreter.Value

abstract class Expression {
  abstract fun evaluate(interpreter: Interpreter?): Value<Any>?
}