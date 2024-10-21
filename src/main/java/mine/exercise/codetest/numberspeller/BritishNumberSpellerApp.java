package mine.exercise.codetest.numberspeller;

public class BritishNumberSpellerApp {
    private NumberSpeller numberSpeller;

    public BritishNumberSpellerApp(NumberSpeller numberSpeller) {
        this.numberSpeller = numberSpeller;
    }

    public static void main(String[] args) {
        BritishNumberSpellerApp app = new BritishNumberSpellerApp(new BritishNumberSpeller());
        app.displaySpellingOfNumber(1);
        app.displaySpellingOfNumber(21);
        app.displaySpellingOfNumber(105);
        app.displaySpellingOfNumber(56945781);
        app.displaySpellingOfNumber(999999999);
        app.displaySpellingOfNumber(-999);
    }

    public void displaySpellingOfNumber(int num) {
        System.out.println(num + " spelled as \"" + this.numberSpeller.spellNumber(num) + "\"");
    }
}
