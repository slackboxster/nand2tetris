// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input. 
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel. When no key is pressed, the
// program clears the screen, i.e. writes "white" in every pixel.


///ok. this is confusing but the basic logic is:
//  set up. infinite loop
// grab a keyboard key. if nonzero check if the position is 8192. If so, break; else, increment the current color. If the current color == -1, increment the position pointer (unless we've hit 8192)
// if kb is zero, check if the current color is 0. if not, jump to decrementing current color. If so, check if the position is 0 -- if so, break; decrement the current position; (label) decrement the current color


// Put your code here.
//initialize the screen max and min pointers
@8192
D=A
@max
M=D //put 8192 into the max variable.
@0
D=A
@pos
M=D // put 0 into the counter pointer
(beg)
@24576 // keyboard address
D=M // store current character in register
@knd
D;JLE // if current character <= 0 execute the "key not down" routine
@pos
D=M
@max
D=D-M
@beg
D;JGE // if current position is greater than or equal to the max pos, stop painting.
@SCREEN
D=A
@pos
D=M+D // add pos to the screen ptr
A=D
M=M+1
D=!M // negate the new color -- if its negation is zero, we have finished filling this block and should increment the pointer before jumping.
@beg
D;JNE
@pos
M=M+1 // now increment position
@beg
0;JMP // jump back to the beginning.
(knd) // if key is not currently pressed
// if kb is zero, check if the current color is 0. if not, jump to decrementing current color. If so, check if the position is 0 -- if so, break; if not, decrement the current position; (label) decrement the current color
@pos
D=M
@SCREEN
D=D+M
A=D
@dec
M;JNE // jump to decrementing color if current color has not hit zero.
@pos
D=M
@beg
D;JLE // if current position is less than or equal to zero, (and from previous jump the current color is zero) stop unpainting.
@pos
M=M-1 // now decrement position if current color is zero and current position is not zero
(dec)
@SCREEN
D=A
@pos
D=M+D // add pos to the screen ptr
A=D // put current resolved screen ptr into A register
M=M-1 // decrement current color
@beg
0;JMP // jump back to the beginning to see what's going on.
