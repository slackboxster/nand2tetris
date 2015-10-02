import java.nio.charset.Charset

class Code {

    String translate(Command command) {
        switch(command.commandType) {
            case Command.A_COMMAND:
                return "0${getValueBinary(command.symbol)}"
                break
            case Command.L_COMMAND:
                return null
                break
            case Command.C_COMMAND:
                return "111${getComputationBinary(command.computation)}${getDestinationBinary(command.destination)}${getJumpBinary(command.jump)}"
                break
            default:
                throw new InvalidCommandTypeException()
                break
        }
    }

    String getValueBinary(String valueDecimal) {
        Integer.toBinaryString(Integer.parseInt(valueDecimal.trim())).padLeft(15,'0')
    }

    String getDestinationBinary(String destinationMnemonic) {
        Map destinationSymbols = [
            (''):    '000',
            ('M'):   '001',
            ('D'):   '010',
            ('MD'):  '011',
            ('A'):   '100',
            ('AM'):  '101',
            ('AD'):  '110',
            ('AMD'): '111'
        ]
        return destinationSymbols."${destinationMnemonic.trim()}"
    }

    String getComputationBinary(String computationMnemonic) {
        Map computationSymbols = [
            ('0'):   '0101010',
            ('1'):   '0111111',
            ('-1'):  '0111010',
            ('D'):   '0001100',
            ('A'):   '0110000',
            ('M'):   '1110000',
            ('!D'):  '0001101',
            ('!A'):  '0110001',
            ('!M'):  '1110001',
            ('-D'):  '0001111',
            ('-A'):  '0110011',
            ('-M'):  '1110011',
            ('D+1'): '0011111',
            ('A+1'): '0110111',
            ('M+1'): '1110111',
            ('D-1'): '0001110',
            ('A-1'): '0110010',
            ('M-1'): '1110010',
            ('D+A'): '0000010',
            ('D+M'): '1000010',
            ('D-A'): '0010011',
            ('D-M'): '1010011',
            ('A-D'): '0000111',
            ('M-D'): '1000111',
            ('D&A'): '0000000',
            ('D&M'): '1000000',
            ('D|A'): '0010101',
            ('D|M'): '1010101'
        ]

        return computationSymbols."${computationMnemonic.trim()}"
    }

    String getJumpBinary(String jumpMnemonic) {
        Map jumpSymbols = [
            (''): '000',
            JGT: '001',
            JEQ: '010',
            JGE: '011',
            JLT: '100',
            JNE: '101',
            JLE: '110',
            JMP: '111'
        ]
        return jumpSymbols."${jumpMnemonic.trim()}"
    }

}