package interpreter

class CharacterBuffer(private val data: String) {
  private var offset = 0
  val next: String
    get() = data.substring(offset, ++offset)

  operator fun hasNext(): Boolean {
    return offset < data.length
  }

  fun peek(offset: Int): String {
    return data.substring(this.offset + offset, this.offset + offset + 1)
  }

}