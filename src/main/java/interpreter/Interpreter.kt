package interpreter

import interpreter.ast.Statement
import java.util.*

class Interpreter {
  private val statements: MutableMap<Int, Statement> = LinkedHashMap()
  private val variables: MutableMap<String, Any> = HashMap()
  val stack = Stack<Int>()
  fun addStatement(label: Int, statement: Statement) {
    statements[label] = statement
  }

  private var statement: Statement? = null
  fun setInitialStatement() {
    statement = statements.entries.iterator().next().value
  }

  fun setStatement(label: Int) {
    statement = statements[label]
  }

  fun run() {
    val current = statement ?: throw RuntimeException("RUNTIME ERROR: Missing END")
    statement = statement?.nextStatement
    current.run(this)
  }

  fun setVariable(name: String, value: Any) {
    variables[name.toLowerCase()] = value
  }

  fun getVariable(name: String): Any? {
    return variables[name.toLowerCase()]
  }

  init {
    val vnames = "abcdefghijklmnopqrstuvwxyz".split("").toTypedArray()
    for (vname in vnames) {
      variables[vname] = 0
    }
  }
}