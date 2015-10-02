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
// grab a keyboard key. if nonzero, set color to negated (for all black), fill the current pixel bar (starting with zero) and increment the pixel bar(unless we've hit 8192)
// if kb is zero, set color to 0 (for all white), decrement the pixel bar counter (unless it's zero) and fill the new position with all white (because we would assume that the black filler will have incremented past its original position.


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
@scr
M=D // store current pos ptr into scr
@0
D=!A // put black into D
@scr
A=M // put the current pos ptr into A
M=D // put the current color into that new position
@pos
M=M+1 // now increment position
@beg
0;JMP // jump back to the beginning.
(knd) // if key is not currently pressed
@pos
D=M
@beg
D;JLE // if current position is less than or equal to zero, stop unpainting.
@pos
M=M-1 // now decrement position
@SCREEN
D=A
@pos
D=M+D // add pos to the screen ptr
@scr
M=D // store current pos ptr into scr
@0
D=A // put white into D
@scr
A=M // put the current pos ptr into A
M=D // put the current color into that new position
@beg
0;JMP // jump back to the beginning to see what's going on.
