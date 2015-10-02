// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input. 
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel. When no key is pressed, the
// program clears the screen, i.e. writes "white" in every pixel.

// Put your code here.
@0
D=A
@R1
M=D // start with bitwise all 1s
(beg)
@R1
M=!M //negate Mem
@8192
D=A
@R0
M=D // start with size of screen in words, stored in R0
(wh2)
@R0
D=M // store the current value of R0 (our word counter) into D
@beg
D;JLE //if current word hits zero, we start again with another character
@SCREEN
D=A
@R0
D=D+M // add the current screen offset to the screen pointer
@R2
M=D //store the current actual screen offset into temp memory.
@R1
D=M // store current color in register
@R2
A=M // set current screen pixel to current color
M=D
@R0
M=M-1 // decrement the memory screen offset counter
@wh2
0;JMP // jump to begin of while loop.
@beg
0;JMP // jump to begin of main loop, where we will invert the color and try again.
