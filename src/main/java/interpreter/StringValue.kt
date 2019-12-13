package interpreter

class StringValue(
  override val value: String
) : Value<Any>() {

  override fun toString(): String {
    return value
  }

}