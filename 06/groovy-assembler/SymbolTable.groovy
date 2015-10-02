class SymbolTable {

    Map symbols = [
        SP:         0,
        LCL:        1,
        ARG:        2,
        THIS:       3,
        THAT:       4,
        R0:         0,
        R1:         1,
        R2:         2,
        R3:         3,
        R4:         4,
        R5:         5,
        R6:         6,
        R7:         7,
        R8:         8,
        R9:         9,
        R10:       10,
        R11:       11,
        R12:       12,
        R13:       13,
        R14:       14,
        R15:       15,
        SCREEN: 16384,
        KBD:    24576
    ]

    int nextRamLocation = 16

    SymbolTable(List<Command> commandList) {
        int programCounter = 0
        //first we need to create a symbol for all L commands so that they don't get allocated as A commands if used
        //before their declaration.
        commandList.each { command ->
            if (command.isL()) {
                addEntry(command.symbol,0)
            }
        }

        commandList.each { Command command ->
            if (command.isL()) {
                symbols."$command.symbol" = programCounter
            } else {
                if (command.isA()) {
                    //an A command doesn't allocate a new symbol if it is referencing an existing symbol
                    //or if it is a constant.
                    if (!symbols.containsKey(command.symbol) && ! command.symbol.isInteger()) {
                        addEntry(command.symbol)
                    }
                }
                programCounter++
            }
        }
    }

    /**
     * if an address is not specified, allocate one in RAM. If an address is specified, make sure the given symbol isn't
     * already allocated. If it is, throw an exception.
     * @param symbol
     * @param address
     */
    void addEntry(String symbol, Integer address = -1) {
        if (symbols.containsKey(symbol)) {
            throw new CannotRedeclareSymbolException(symbol)
        }
        symbols."$symbol" = (address >= 0) ? address : allocateVariable()
    }

    int allocateVariable() {
        if (nextRamLocation >= symbols.SCREEN) {
            throw new OutOfMemoryException()
        }
        int allocatedAddress = nextRamLocation
        nextRamLocation++
        return allocatedAddress
    }

    int getAddress(symbol) {
        symbols."$symbol"
    }

    Boolean contains(symbol) {
        symbols.containsKey(symbol)
    }
}