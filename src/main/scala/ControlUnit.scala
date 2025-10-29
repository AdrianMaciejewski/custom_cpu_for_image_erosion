import chisel3._
import chisel3.util._

class ControlUnit extends Module {
  val io = IO(new Bundle {
    val opcode = Input(UInt(4.W))

    // Outputs
    val stop = Output(Bool())
    val aluOp = Output(AluOp())
    val aluUsingImm = Output(Bool())
    val branch = Output(Bool())
    val dataReadEnable = Output(Bool())
    val dataWriteEnable = Output(Bool())
    val registerWrite = Output(Bool())

  })

  //Implement this module here
  switch(io.opcode) {
    is(0.U){} // No opcode

    is(1.U){ // Addition
      io.aluOp := AluOp.add
      io.aluUsingImm := false.B
      io.stop := false.B
      io.branch := false.B
      io.dataReadEnable := false.B
      io.dataWriteEnable := false.B
      io.registerWrite := true.B}


    is(2.U){ // Subtraction
      io.aluOp := AluOp.sub
      io.aluUsingImm := false.B
      io.stop := false.B
      io.branch := false.B
      io.dataReadEnable := false.B
      io.dataWriteEnable := false.B
      io.registerWrite := true.B}


    is(3.U) { // Immediate Addition (addition with a number rather than a register)
      io.aluOp := AluOp.add
      io.aluUsingImm := true.B
      io.stop := false.B
      io.branch := false.B
      io.dataReadEnable := false.B
      io.dataWriteEnable := false.B
      io.registerWrite := true.B
    }

    is(4.U) { // Immediate Multiplication
      io.aluOp := AluOp.mul
      io.aluUsingImm := true.B
      io.stop := false.B
      io.branch := false.B
      io.dataReadEnable := false.B
      io.dataWriteEnable := false.B
      io.registerWrite := true.B
    }

    is(5.U){ // Save Data
      io.aluOp := AluOp.add
      io.aluUsingImm := false.B
      io.stop := false.B
      io.branch := false.B
      io.dataReadEnable := false.B
      io.dataWriteEnable := true.B
      io.registerWrite := false.B

    }


    is(6.U) { // Load Data
      io.aluOp := AluOp.add
      io.aluUsingImm := false.B
      io.stop := false.B
      io.branch := false.B
      io.dataReadEnable := true.B
      io.dataWriteEnable := false.B
      io.registerWrite := true.B
    }

    is(8.U){
    } // Unconditional Jump


    is(9.U) { // Branch if Equal
      io.aluOp := AluOp.eql
      io.aluUsingImm := false.B
      io.stop := false.B
      io.branch := true.B
      io.dataReadEnable := false.B
      io.dataWriteEnable := false.B
      io.registerWrite := false.B
    }

    is(10.U){// Branch not Equal
      io.aluOp := AluOp.neq
      io.aluUsingImm := false.B
      io.stop := false.B
      io.branch := true.B
      io.dataReadEnable := false.B
      io.dataWriteEnable := false.B
      io.registerWrite := false.B
      }


    is(11.U){ // Greater Than or Equal
      io.aluOp := AluOp.geq
      io.aluUsingImm := false.B
      io.stop := false.B
      io.branch := true.B
      io.dataReadEnable := false.B
      io.dataWriteEnable := false.B
      io.registerWrite := false.B
    }


    is(15.U){io.stop := true.B} // Exit

  }



}