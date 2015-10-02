// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/01/And16.tst

load And16to1.hdl,
output-file And16to1.out,
compare-to And16to1.cmp,
output-list a%B1.16.1  b%B3.1.3 out%B1.16.1;

set a %B0000000000000000,
set b 0, 
eval,
output;

set a %B0000000000000000,
set b 1, 
eval,
output;

set a %B1111111111111111,
set b 1,
eval,
output;

set a %B1010101010101010,
set b 0, 
eval,
output;

set a %B0011110011000011,
set b 1, 
eval,
output;

set a %B0001001000110100,
set b 1, 
eval,
output;
