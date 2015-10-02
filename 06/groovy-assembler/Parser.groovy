//from Command import Command
//
class Parser implements Iterable {

    Integer currentCommandIndex = 0
    List<String> syntaxTree

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


//        else:
//            raise StopIteration()
}
//#
//# class Command:
//#     commandType
//#
//#
//# class ACommand(Command):
//#
//#     def __init__(self):
//#         commandType=CommandType.A
//#
//#     def symbol(self):
//#         return ''
//#
//# class CCommand(Command):
//#
//#     def __init__(self):
//#         commandType=CommandType.C
//#
//#     def dest(self):
//#         return ''
//#
//#     def comp(self):
//#         return ''
//#
//# class LCommand(Command):
//#
//#     def __init__(self):
//#         commandType=CommandType.L
//#
//#     def symbol(self):
//#         return ''
//#
//# class CommandType(Enum):
//#     A=1
//#     C=2
//#     L=3
