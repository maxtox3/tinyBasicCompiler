package interpreter

import java.util.*

class Tokenizer {
  fun tokenize(line: String?): List<Token> {
    val list: MutableList<Token> = ArrayList()
    val buffer = line?.let { CharacterBuffer(it) }
    while (buffer?.hasNext() == true) {
      val token = nextToken(buffer)
      list.add(token)
    }
    return list
  }

  private enum class CharacterType {
    CHARACTER, DIGIT, OPERATOR, TOPERATOR, RELOPERATOR, QUOTE, SPLITTER, RIGHTPAREN, LEFTPAREN;

    companion object {
      fun getType(token: String): CharacterType {
        return when {
          characters.contains(token.toLowerCase()) -> CHARACTER
          numbers.contains(token) -> DIGIT
          operators.contains(token) -> OPERATOR
          relationalOperators.contains(token) -> RELOPERATOR
          token == "," -> SPLITTER
          token == "\"" -> QUOTE
          token == "(" -> LEFTPAREN
          token == ")" -> RIGHTPAREN
          else -> throw RuntimeException("TOKENIZER ERROR: Invalid token: '$token'")
        }
      }
    }
  }

  private fun nextToken(buffer: CharacterBuffer): Token {
    var token = buffer.next
    token = readWhitespace(token, buffer)
    val type = CharacterType.getType(
      token
    )
    return when (type) {
      CharacterType.SPLITTER -> Token(
        Token.TokenType.SPLITTER,
        token
      )
      CharacterType.CHARACTER -> if (!buffer.hasNext() || buffer.hasNext() && !characters.contains(
          buffer.peek(0).toLowerCase()
        )) {
        Token(Token.TokenType.VARIABLE, token)
      } else {
        readKeyword(token, buffer)
      }
      CharacterType.DIGIT -> readNumber(token, buffer)
      CharacterType.OPERATOR -> Token(Token.TokenType.OPERATOR, token)
      CharacterType.RELOPERATOR -> readRelationalOperator(token, buffer)
      CharacterType.QUOTE -> readString(buffer)
      CharacterType.LEFTPAREN -> Token(Token.TokenType.LEFTPAREN, "(")
      CharacterType.RIGHTPAREN -> Token(Token.TokenType.RIGHTPAREN, ")")
      else -> throw RuntimeException("TOKENIZER ERROR - Invalid token: '$token'")
    }
  }

  private fun readWhitespace(token: String, buffer: CharacterBuffer): String {
    return if (whitespace.contains(token)) { //Skip whitespace
      while (buffer.hasNext() && whitespace.contains(buffer.peek(0))) {
        buffer.next
      }
      buffer.next
    } else {
      token
    }
  }

  private fun readKeyword(
    token: String,
    buffer: CharacterBuffer
  ): Token { //Read remaining characters
    var newToken = token
    while (buffer.hasNext() && characters.contains(buffer.peek(0).toLowerCase())) {
      newToken += buffer.next
    }
    //Check if valid keyword
    return if (keywords.contains(newToken.toLowerCase())) {
      Token(Token.TokenType.KEYWORD, newToken.toLowerCase())
    } else {
      throw RuntimeException("TOKENIZER ERROR - Invalid keyword: $newToken")
    }
  }

  private fun readNumber(token: String, buffer: CharacterBuffer): Token {
    var newToken = token
    while (buffer.hasNext() && numbers.contains(buffer.peek(0))) {
      newToken += buffer.next
    }
    return Token(Token.TokenType.NUMBER, newToken)
  }

  private fun readString(buffer: CharacterBuffer): Token {
    var token = "" //Ignore first quotation mark.
    while (buffer.hasNext() && !(buffer.peek(0) == "\"" && buffer.peek(-1) != "\\")) {
      token += buffer.next
    }
    buffer.next //Ignore ending quotation mark.
    return Token(Token.TokenType.STRING, token)
  }

  private fun readRelationalOperator(
    token: String,
    buffer: CharacterBuffer
  ): Token { //Relational Operator
    return if (token != "=" && buffer.hasNext() && relationalOperators.contains(
        buffer.peek(0)
      )) {
      Token(Token.TokenType.RELOPERATOR, token + buffer.next)
    } else {
      Token(Token.TokenType.RELOPERATOR, token)
    }
  }

  companion object {
    private val whitespace: List<String> = listOf(
      " ",
      "	"
    )
    private val keywords: List<String> = listOf(
      "print",
      "rem",
      "if",
      "then",
      "goto",
      "input",
      "let",
      "gosub",
      "return",
      "clear",
      "end",
      "0123456789"
    )
    private val numbers: List<String> = listOf(
      "0",
      "1",
      "2",
      "3",
      "4",
      "5",
      "6",
      "7",
      "8",
      "9"
    )
    private val characters: List<String> = "abcdefghijklmnopqrstuvwxyz".split("")
    private val operators: List<String> = listOf(
      "*",
      "+",
      "-",
      "/"
    )
    private val relationalOperators: List<String> = listOf(
      ">",
      "<",
      "="
    )
  }
}