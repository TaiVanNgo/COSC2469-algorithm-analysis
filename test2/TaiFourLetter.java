public class TaiFourLetter {
    String letter;
    char[] alphabet;

    TaiFourLetter(String letter) {
        this.letter = letter;

        this.alphabet = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
    }

    // time complexity: O(logN)
    public int freeTransform() {
        // TARGET: 'RMIT'
        int count = 0;

        if (this.letter.charAt(0) != 'R') {
            count += Math.abs(findPositionAlphabet('R') - findPositionAlphabet(this.letter.charAt(0)));
        }
        if (this.letter.charAt(1) != 'M') {
            count += Math.abs(findPositionAlphabet('M') - findPositionAlphabet(this.letter.charAt(1)));
        }
        if (this.letter.charAt(2) != 'I') {
            count += Math.abs(findPositionAlphabet('I') - findPositionAlphabet(this.letter.charAt(2)));
        }
        if (this.letter.charAt(3) != 'T') {
            count += Math.abs(findPositionAlphabet('T') - findPositionAlphabet(this.letter.charAt(3)));
        }

        return count;
    }

    //time complexity: O(N)
    public int constraintTransform(String[] forbidden) {
        // below are my pseudo code
        // Forbidden: RMIU

        // V must go backward 2 time
        System.out.println(findPositionAlphabet('T') - findPositionAlphabet(this.letter.charAt(3)));

        // RMIU, U go backward 1 timetter.
        System.out.println(findPositionAlphabet('T') - findPositionAlphabet('U'));
        // they collapse, we need to increase the step by 2, since we need to move the
        // another position of the current letter to another letter, then we need to
        // move it back

        return 0;
    }

    private int findPositionAlphabet(char character) {
        // apply binary search
        int min = 0;
        int max = 26;

        while (max - min > 1) {
            int mid = (min + max) / 2;
            // I compare bettween 2 character,
            if (this.alphabet[mid] < character) {
                // it on the right side
                min = mid;
            } else {
                // on the left side
                max = mid;
            }

        }

        if (this.alphabet[max] == character) {
            return max;
        }
        return min;
    }

    public static void main(String[] args) {
        TaiFourLetter letter1 = new TaiFourLetter("RMIV");
        System.out.println("Test RMIV: ");
        System.out.println(letter1.freeTransform());

        TaiFourLetter letter2 = new TaiFourLetter("OMIT");
        System.out.println("Test OMIT: ");
        System.out.println(letter2.freeTransform());

        System.out.println("Test Forbidden: ");
        TaiFourLetter letter3 = new TaiFourLetter("RMIV");
        letter3.constraintTransform(new String[] { "RMIU" });

    }
}
