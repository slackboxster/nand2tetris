class Command {

    static String A_COMMAND = 'A_COMMAND'
    static String C_COMMAND = 'C_COMMAND'
    static String L_COMMAND = 'L_COMMAND'

    String originalLine = ''
    String commandType = ''
    String symbol = ''
    String destination = ''
    String computation = ''
    String jump = ''

    Command(String line) {

        originalLine = line

        if (line.allWhitespace || line.trim().startsWith('//'))
            throw new BlankLineException()
        if (line.startsWith('@')) {
            commandType = A_COMMAND
            symbol = line - '@'
        } else if (line.startsWith('(')) {
            commandType = L_COMMAND
            symbol = line - '(' -')'
        } else {
            commandType = C_COMMAND

            computation = line
            if (computation.contains('=')) {
                (destination, computation) = computation.split('=')
            }
            if (computation.contains(';')) {
                (computation, jump) = computation.split(';')
            }
        }
    }

    String toString() {
        """\
        Command:
            OriginalLine: $originalLine
            CommandType: $commandType
            Symbol: $symbol
            Destination: $destination
            Computation: $computation
            Jump: $jump
        """.stripIndent()
    }
}
