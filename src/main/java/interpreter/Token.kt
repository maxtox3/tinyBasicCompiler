package interpreter

class Token(var type: TokenType, var value: String) {
  enum class TokenType {
    KEYWORD,
    VARIABLE,
    NUMBER,
    OPERATOR,
    RELOPERATOR,
    STRING,
    SPLITTER,
    EOF,
    RIGHTPAREN,
    LEFTPAREN
  }

  override fun toString(): String {
    return type.name + ": '" + value + "'"
  }

}