// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[3], respectively.)

// Put your code here.
@R1 
D=M // put R1 into D
@R3
M=D // set R3 to what R1 starts at, we will increment it down.
@R2
M=0 // initialize to 0 so that we start multiplying from there.
(WHL) 
@R3
D=M // put R3 in D so that we can check it for while loop condition
@END
D;JLE // if R3 is less than / equal to 0, we have finished multiplying.
@R2
D=M // continue accumulating on R2
@R0
D=D+M // loaded the accumulating sum into D, now add our first number to it once
@R2
M=D // store the latest multiplication result into R2
@R3
M=M-1 // decrement R3
@WHL
0;JMP // jump back to beginning of while loop where the while condition will be evaluated again.
(END)
@END
0;JMP
