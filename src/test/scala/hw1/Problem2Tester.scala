// See README.md for license details.

package hw1

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class Problem2Tester extends AnyFlatSpec with ChiselScalatestTester {
  behavior of "Problem2"
  it should "correctly pass tests on all 8 inputs" in {
    test(new Problem2) { dut =>
      val values = Seq(true, false)
      for (a <- values) {
        for (b <- values) {
          for (c <- values) {
            dut.io.a.poke(a.B)
            dut.io.b.poke(b.B)
            dut.io.c.poke(c.B)
            dut.io.out.expect((a & (b ^ c)).B)
          }
        }
      }
    }
  }
}
