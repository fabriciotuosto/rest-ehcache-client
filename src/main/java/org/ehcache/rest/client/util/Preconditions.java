package org.ehcache.rest.client.util;

public final class Preconditions {

    /**
     * Guarantees non instantiation
     */
    private Preconditions() {
        super();
    }

    /**
     * Checks if argument is null, if it is null it throws
     *
     * @param <T> class of the value to test nullability
     * @param t   value to test nullability
     * @return the same object that was passed as argument
     * this is done in order to provide an easy way to test and assign
     * the given value
     * <p/>
     * <code>
     * Object value = Preconditions.checkNotNull(argument);
     * </code>
     * @throws @{@link IllegalArgumentException} if the value is null
     * @{@link IllegalArgumentException}
     */
    public static <T> T checkNotNull(T t) {
        return checkNotNull(t, "The value shouldn't bee null");
    }


    /**
     * Checks that none of the arguments provided are null
     * if at least one is null it throws
     *
     * @param ts values to tests nullability
     *           <p/>
     *           this is done in order to provide an easy way to test
     *           multiple value at the same point
     *           <p/>
     *           <code>
     *           Object value = Preconditions.checkNotNull(argument,argument2);
     *           </code>
     * @return the same object that was passed as argument
     * @throws @{@link IllegalArgumentException} if the value is null
     * @{@link IllegalArgumentException}
     */
    public static void checkNotNulls(Object... ts) {
        for (Object t : ts) {
            if (t == null) {
                throw new IllegalArgumentException("The values shouldn't bee null");
            }
        }
    }


    /**
     * Checks if argument is null, if it is null it throws
     *
     * @param <T>           class of the value to test nullability
     * @param t             value to test nullability
     * @param messageIfNull the message of the result exception
     *                      if the argument is null
     * @return the same object that was passed as argument
     * this is done in order to provide an easy way to test and assign
     * the given value
     * <p/>
     * <code>
     * Object value = Preconditions.checkNotNull(argument);
     * </code>
     * @throws @{@link IllegalArgumentException} if the value is null
     * @{@link IllegalArgumentException}
     */
    public static <T> T checkNotNull(T t, String messageIfNull) {
        if (t == null) {
            throw new IllegalArgumentException(messageIfNull);
        }
        return t;
    }
}
