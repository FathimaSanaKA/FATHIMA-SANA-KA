// File: TextProcessor.java
public class TextProcessor {
    // This is our backend logic
    public String reverseText(String input) {
        if (input == null || input.isEmpty()) {
            return "Please type something!";
        }
        return new StringBuilder(input).reverse().toString();
    }
}
