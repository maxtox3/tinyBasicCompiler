package interpreter

import interpreter.ast.Statement
import java.io.File
import java.io.IOException
import java.util.*

object Start {
  @Throws(IOException::class)
  @JvmStatic
  fun main(args: Array<String>) {
    if (args.size != 1) throw RuntimeException("Invalid number of arguments. Use as: filename")
    run(args[0])
  }

  @Throws(IOException::class)
  fun run(path: String?) {
    val scanner = Scanner(File(path))
    var lastLabel = 0
    val lines: MutableList<Statement> = ArrayList()
    val usedLabels: MutableList<Int> = ArrayList()
    var lastStatement: Statement? = null
    while (scanner.hasNextLine()) {
      val line = scanner.nextLine()
      if (line.isNotEmpty()) {
        val tokenizer = Tokenizer()
        val tokens = tokenizer.tokenize(line)
        val parser = Parser(tokens)
        val statement = parser.parse()
        lines.add(statement)
        usedLabels.add(statement.label)
        if (lastStatement != null) lastStatement.nextStatement = statement
        lastStatement = statement
      }
    }
    scanner.close()
    val interpreter = Interpreter()
    /* Assign labels to lines without labels */for (statement in lines) {
      if (statement.label == -1) {
        while (usedLabels.contains(lastLabel)) {
          lastLabel++
        }
        statement.label = lastLabel
        lastLabel++
      }
      interpreter.addStatement(statement.label, statement)
    }
    interpreter.setInitialStatement()
    while (true) interpreter.run()
  }
}