#!/usr/bin/env groovy

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
List<Command> commands = parser.collect()
SymbolTable symbolTable = new SymbolTable(commands)
Code code = new Code(symbolTable: symbolTable)

File outfile = options.o ? new File(options.o) : new File("${infile.path[0..-4]}hack")

List<String> instructions = commands.findResults { command ->
    return code.translate(command)
}

outfile.write("${instructions.join('\n')}\n")

