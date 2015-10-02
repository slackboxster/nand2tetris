#!/usr/bin/env groovy

//import Parser
//import Code
//import SymbolTable

//import argparse
//import sys
import java.nio.charset.Charset

CliBuilder cli = new CliBuilder(
        usage:"assembler <infile>\ninfile: .asm file that should be assembled",
        header:"Assembler from .asm to Hack machine language\nOptions:"
)
cli.o(longOpt:"outfile","ouput file target. if not specified, the input file will be renamed to have a .hack extension")

OptionAccessor options = cli.parse(args)

if (options.arguments().size() == 0) {
    println cli.usage()
    return
}

File infile = new File(options.arguments()[0])
Parser parser = new Parser(infile)
Code code = new Code()

File outfile = options.o ? new File(options.o) : new File("${infile.path[0..-4]}hack")

    List instructions = parser.collect { command ->
        return code.translate(command)
    }

    outfile.write("${instructions.join('\n')}\n")

