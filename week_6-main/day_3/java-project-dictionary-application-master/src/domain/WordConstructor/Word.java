package domain.WordConstructor;

public class Word {

    //DECLARING VARIABLES
    private String word;
    private String definition;
    private String part_of_speech;
    private String example_usage;

    //METHODS
    @Override
    public int hashCode(){
        int result = 1337;
        result = 31 * result + word.hashCode();
        result = 31 * result + definition.hashCode();
        result = 31 * result + part_of_speech.hashCode();
        result = 31 * result + example_usage.hashCode();
        return result;
    }

    //Creating constructors for the word
    public Word(String word, String definition, String part_of_speech, String example_usage) {
        this.word = word;
        this.definition = definition;
        this.part_of_speech = part_of_speech;
        this.example_usage = example_usage;
    }

    //Chaining constructors
    public Word(String word) {
        this(word, "", "", "");
    }

    //GETTERS
    public String getWord() {
        return word;
    }

    public String getDefinition() {
        return definition;
    }

    public String getPart_of_speech() {
        return part_of_speech;
    }

    public String getExample_usage() {
        return example_usage;
    }

    //SETTERS
    public void setWord(String Word) {
        this.word = word;
    }
    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setPart_of_speech(String part_of_speech) {
        this.part_of_speech = part_of_speech;
    }

    public void setExample_usage(String example_usage) {
        this.example_usage = example_usage;
    }
}
