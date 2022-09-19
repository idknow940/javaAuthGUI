import java.util.Random;

public class PasswordHasher extends Hasher {
    @Override
    public String hash(String input) {
        byte[] bytes = input.getBytes();
        Random rand = new Random(bytes[0] + bytes[bytes.length - 1]);
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int next = rand.nextInt();
            chars[i] += next % 0b11111111 < 33 ? (next % 0b11111111) + 33 : next % 0b11111111;
        }
        return String.valueOf(chars);
    }

    public static Hasher create() {
        return new PasswordHasher();
    }
}
