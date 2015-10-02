//from Command import Command
//
class Parser implements Iterable {

    Integer currentCommandIndex = 0
    List<Command> syntaxTree

    Parser(File infile) {
        List<String> lines = infile.text.split("\n")
        syntaxTree = lines.findResults {
            try {
                return new Command(it)
            } catch (BlankLineException e) {
                return null
            }
        }
    }

    @Override
    Iterator iterator() {
        [
            hasNext: {
                currentCommandIndex < syntaxTree.size()
            },
            next: {
                Integer tempIndex = currentCommandIndex
                currentCommandIndex += 1
                syntaxTree[tempIndex]
            }
        ] as Iterator
    }

}
