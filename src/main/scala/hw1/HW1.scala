// See README.md for license details.

package hw1

import chisel3._
import chisel3.util._

// Note ??? will compile but not work at runtime.

/**
  * io.b: Bool Input
  * io.x: 6-bit UInt Input
  * io.y: 6-bit UInt Input
  * io.out0: Bool Output
  * io.out1: 6-bit UInt Output
  */
class CombLogic extends Module {
    val io = IO(new Bundle {
      val b = Input(Bool())
      val x = Input(UInt(6.W))
      val y = Input(UInt(6.W))
      val out0 = Output(Bool())
      val out1 = Output(UInt(6.W))
    })
    io.out0 := io.b ^  (io.x & io.y)(5)
    io.out1 := Cat(io.x(5,3), io.y(2,0))
}

class Problem2 extends Module {
    val io = IO(new Bundle {
        val a   = Input(Bool())
        val b   = Input(Bool())
        val c   = Input(Bool())
        val out = Output(Bool())
    })
    
    io.out := io.a & (io.b ^ io.c)
}

/**
  * c0: 5-bit Int
  * c1: 5-bit Int
  * c2: 5-bit Int
  * io.enable: Bool Input
  * io.x: 5-bit UInt Input
  * io.out: ???-bit UInt Output
  */
class PolyEval(c0: Int, c1: Int, c2: Int) extends Module {
    require (c0 >= 0 && c0 < 32)
    require (c1 >= 0 && c1 < 32)
    require (c2 >= 0 && c2 < 32)
    val io = IO(new Bundle {
      val en = Input(Bool())
      val x = Input(UInt(5.W))
      val out = Output(UInt())
    })

    io.out := 0.U
    when (io.en) {
      io.out := c0.U +& c1.U * io.x +& c2.U * io.x * io.x
    }
}


/**
  * onlyAdder: Boolean
  * io.doAdd: Bool Input
  * io.real0: 5-bit SInt Input
  * io.imag0: 5-bit SInt Input
  * io.real1: 5-bit SInt Input
  * io.imag1: 5-bit SInt Input
  * io.realOut: SInt() Output
  * io.imagOut: SInt() Output
  */
class ComplexALU(onlyAdder: Boolean) extends Module {
  val io = IO(new Bundle {
      val doAdd   = Input(Bool())
      val real0   = Input(SInt(5.W))
      val imag0   = Input(SInt(5.W))
      val real1   = Input(SInt(5.W))
      val imag1   = Input(SInt(5.W))
      val realOut = Output(SInt())
      val imagOut = Output(SInt())
  })
  if (onlyAdder == true) {
    when (io.doAdd) {
      io.realOut := io.real0 +& io.real1
      io.imagOut := io.imag0 +& io.imag1
    } .otherwise {
      // what does expanding substraction mean?
      io.realOut := io.real0 -& io.real1
      io.imagOut := io.imag0 -& io.imag1
    }
  } else {
    io.realOut := io.real0 +& io.real1
    io.imagOut := io.imag0 +& io.imag1
  }
}
