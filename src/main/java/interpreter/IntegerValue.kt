package interpreter

class IntegerValue(override val value: Int) : Value<Any>() {
  override fun toString(): String {
    return value.toString()
  }

}