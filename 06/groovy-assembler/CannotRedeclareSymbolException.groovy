class CannotRedeclareSymbolException extends Exception{

    String symbol

    CannotRedeclareSymbolException() {}

    CannotRedeclareSymbolException(String badSymbol) {
        symbol = badSymbol.trim()
    }
}