import org.scalatest.FunSpec
import org.scalatest.Matchers._

import MyStringInterpolation._

class MyStringInterpolationTest extends FunSpec {
  describe("MyStringInterpolationのテスト") {
    describe("helloworld") {
      it("foobar") {
        helloworld"foobar" should be ("Hello World")
      }

      it("foobar${i}foobar") {
        val i = 10
        helloworld"foobar${i}foobar" should be ("Hello World")
      }
    }

    describe("tuples") {
      it("Hello${v1}World${v2}!!") {
        val (v1, v2) = ("str1", "str2")
        tuples"Hello${v1}World${v2}!!" should be (List("Hello", "World", "!!"),
                                                  List("str1", "str2"))
      }

      it("Hello${1 + 2}World") {
        tuples"Hello${1 + 2}World" should be (List("Hello", "World"),
                                              List(3))
      }
    }

    describe("noesc") {
      it("\\t\\n$s\\t\\n") {
        val s = "str"
        noesc"\\t\\n$s\\t\\n" should be ("\\\\t\\\\nstr\\\\t\\\\n")
      }
    }

    describe("esc") {
      it("\\t\\n$s\\t\\n") {
        val s = "str"
        esc"\\t\\n$s\\t\\n" should be ("\\t\\nstr\\t\\n")
      }

      it("esc == s") {
        val str = "foo"
        val i = 10
        esc"Hello $str \\t \\n World $i" should be (s"Hello $str \\t \\n World $i")
      }
    }
  }
}
