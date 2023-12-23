package utils.primitive.generators;

import io.netty.util.internal.ThreadLocalRandom;

import java.security.SecureRandom;
import java.util.UUID;

public class StringGenerator implements IPrimitiveGenerator<String, Long> {

    private static final String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMERIC = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()";
    private static final String ALPHA_NUMERIC = AB + NUMERIC;
    private static final String ALPHA_NUMERIC_WITH_SPACE = " " + ALPHA_NUMERIC;
    private static final String ALPHA_NUMERIC_WITH_SPECIAL_CHARS = ALPHA_NUMERIC + SPECIAL_CHARS;
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public static String randomAlpha(final int len) {
        return randomize(NUMERIC, len);
    }

    private static String randomize(String charCollection, int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(charCollection.charAt(SECURE_RANDOM.nextInt(charCollection.length())));
        }
        return sb.toString();
    }

    public static String randomUuid() {
        return UUID.randomUUID().toString();
    }

    public static char randomChar() {
        return AB.charAt(SECURE_RANDOM.nextInt(AB.length()));
    }

    public static String randomSpecificSymbol(int len) {
        return randomize(SPECIAL_CHARS, len).trim();
    }

    @Override
    public String generate() {
        return randomString(8L, 8L);
    }

    @Override
    public String generate(Long min, Long max) {
        return null;
    }

    private String randomString(long minLen, long maxLen) {
        long len = minLen == maxLen ? maxLen : ThreadLocalRandom.current().nextLong(minLen, maxLen);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(SECURE_RANDOM.nextInt(AB.length())));
        }
        return sb.toString();
    }
}
