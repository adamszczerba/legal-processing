/*import org.apache.commons.cli.CommandLine;
//public class CommandLine
//extends Object
//implements Serializable
//Represents list of arguments parsed against a Options descriptor.
import org.apache.commons.cli.Options;
//public class Options
//extends Object
//implements Serializable
//Options represents a collection of Option objects,
// which describe the possible options for a command-line.
import org.apache.commons.cli.Option;
//public class Option
//extends Object
//implements Cloneable, Serializable





public class OptionInterpreter {

    private Options option;

    public Options getOptions(){

        return this.options;
    }

    boolean hasOption(String opt);

    OptionInterpreter(String opt);

    public Option[] getOptions();
    //Returns an array of the processed Options

}
*/