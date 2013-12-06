object MyStringInterpolation {
  implicit class SimpleStringInterpolation(val sc: StringContext) extends AnyVal {
    def helloworld(args: Any*): String = "Hello World"

    def tuples(args: Any*): (List[String], List[Any]) =
      (sc.parts.toList, args.toList)

    def noesc(args: Any*): String = {
      val ai = args.iterator
      sc.parts.reduceLeft[String] { case (acc, p) =>
        acc + ai.next + p
      }
    }

    def esc(args: Any*): String = {
      val ai = args.iterator
      sc.parts.tail.foldLeft(StringContext.treatEscapes(sc.parts.head)) {
        case (acc, p) =>
          acc + ai.next + StringContext.treatEscapes(p)
      }
    }
  }
}
