package interpreter.ast

import interpreter.*

class VariableExpression(val name: String) : Expression() {

  override fun toString(): String {
    return name
  }

  override fun evaluate(interpreter: Interpreter?): Value<Any>? {
    return when (val variable = interpreter?.getVariable(name)) {
      is Number -> (variable as? Int)?.let { IntegerValue(it) }
      is String -> (variable as? String)?.let { StringValue(it) }
      else -> null
    }
  }

}