// See README.md for license details.

package hw1

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class HW1Tester extends AnyFlatSpec with ChiselScalatestTester {
  behavior of "CombLogic"
  it should  "correctly calculate out0" in {
    test(new CombLogic) { c =>
      for (b <- Seq(0, 1)) {
        for (x <- 0 to 63) {
          for (y <- 0 to 63) {
            c.io.b.poke(b.B)
            c.io.x.poke(x.U)
            c.io.y.poke(y.U)
            //println(b + " " + x + " " + y)
            c.io.out0.expect((b ^ ((x & y & (1 << 5)) >> 5)).B)
            c.io.out1.expect((x & (7 << 3) | y & 7).U)
          }
        }
      }
    }
  }

  // it should "correctly calculate out1" in {
  //   test(new CombLogic) { c =>
  //     ???
  //   }
  // }

  // See src/test/scala/hw1/Problem2Tester.scala for Problem2

  behavior of "PolyEval"
  it should "correctly calculate out" in {
    val c0 = 5
    test(new PolyEval(c0, c0, c0)) { dut =>
      for (en <- Seq(true, false)) {
        for (x <- 0 to 31) {
          dut.io.en.poke(en.B)
          dut.io.x.poke(x.U)
          dut.io.out.expect(if (en) (c0 + c0 * x + c0 * x * x).U else 0.U)
        }
      } 
    }
  }

  behavior of "ComplexALU"
  it should "correctly calculate realOut onlyAdd=true" in {
    test(new ComplexALU(onlyAdder=true)) { dut =>
      ???
    }
  }
  it should "correctly calculate realOut onlyAdd=false" in {
    test(new ComplexALU(onlyAdder = false)) { dut =>
      ???
    }
  }
  it should "correctly calculate imagOut onlyAdd=true" in {
    test(new ComplexALU(onlyAdder = true)) { dut =>
      ???
    }
  }
  it should "correctly calculate imagOut onlyAdd=false" in {
    test(new ComplexALU(onlyAdder = false)) { dut =>
      ???
    }
  }
}
