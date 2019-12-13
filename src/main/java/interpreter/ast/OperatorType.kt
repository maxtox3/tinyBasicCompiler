package interpreter.ast

enum class OperatorType {
  LESSTHAN, LESSTHANOREQUAL, GREATERTHAN, GREATERTHANOREQUAL, NOTEQUAL, EQUAL, ADD, SUBTRACT, MULTIPLY, DIVIDE;

  companion object {
    @JvmStatic
	fun forString(data: String): OperatorType {
      return when (data) {
        "<>", "><" -> NOTEQUAL
        "<" -> LESSTHAN
        ">" -> GREATERTHAN
        "<=" -> LESSTHANOREQUAL
        ">=" -> GREATERTHANOREQUAL
        "=" -> EQUAL
        "+" -> ADD
        "-" -> SUBTRACT
        "/" -> DIVIDE
        "*" -> MULTIPLY
        else -> throw RuntimeException("PARSING ERROR -  Invalid operator '$data'")
      }
    }
  }
}