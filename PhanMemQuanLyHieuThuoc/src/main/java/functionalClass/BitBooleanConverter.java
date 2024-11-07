package functionalClass;

public class BitBooleanConverter {

    // Convert bit (0 or 1) to boolean (false or true)
    public static boolean bitToBool(int bit) {
        return bit == 1; // Return true if bit is 1, false if bit is 0
    }

    // Convert boolean (true or false) to bit (1 or 0)
    public static int boolToBit(boolean value) {
        return value ? 1 : 0; // Return 1 if true, 0 if false
    }

    public static void main(String[] args) {
        // Testing the conversion methods
        int bit = 1;
        boolean boolValue = bitToBool(bit);
        System.out.println("Bit to Bool: " + bit + " -> " + boolValue); // Output: true

        boolean value = true;
        int bitValue = boolToBit(value);
        System.out.println("Bool to Bit: " + value + " -> " + bitValue); // Output: 1
    }
}
