package interpreter

abstract class Value<TYPE> {
  abstract override fun toString(): String
  abstract val value: TYPE
}