package com.xkcoding.learn.java_functional;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>
 * 亿点点分享（上）- Lambda表达式测试类
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-12-13 23:09
 */
@Slf4j
public class LambdaExpressionTests {

    @Test
    public void givenNewInstance_thenReferenceConstructorInFunction() {
        // Supplier<User> supplier = () -> new User();
        Supplier<User> supplier = User::new;
        assertNotNull(supplier.get());
    }

    @Test
    public void givenInstance_thenReferenceInFunction() {
        User user = User.builder().username("zhangsan").build();
        // Supplier<String> supplier = () -> user.getUsername();
        Supplier<String> supplier = user::getUsername;
        assertEquals("zhangsan", supplier.get());
    }

    @Test
    public void givenStringIndex_thenReferenceInFunction() {
        // BiFunction<String, String, Integer> paramRef = (a, b) -> a.indexOf(b);
        BiFunction<String, String, Integer> paramRef = String::indexOf;
        assertEquals(-1, paramRef.apply("Hello", "World"));
    }

    @Test
    public void givenStaticMethod_thenReferenceInFunction() {
        // Greeting greeting = (a, b) -> Player.sayHello(a, b);
        Greeting greeting = Player::sayHello;
        assertEquals("Hello: World", greeting.sayHello("Hello", "World"));
    }

    interface Greeting {
        String sayHello(String s1, String s2);
    }

    static class Player {
        static String sayHello(String s1, String s2) {
            return s1 + ": " + s2;
        }
    }

    @Test
    public void givenTwoFunctions_thenComposeThemInNewFunction() {
        Predicate<String> startsWithA = (text) -> text.startsWith("A");
        Predicate<String> endsWithX = (text) -> text.endsWith("x");
        Predicate<String> startsWithAAndEndsWithX = (text) -> startsWithA.test(text) && endsWithX.test(text);
        String input = "A man is standing there with a box";
        assertTrue(startsWithAAndEndsWithX.test(input));
    }

    @Test
    public void givenTwoPredicates_thenComposeThemUsingAnd() {
        Predicate<String> startsWithA = (text) -> text.startsWith("A");
        Predicate<String> endsWithX = (text) -> text.endsWith("x");
        Predicate<String> startsWithAAndEndsWithX = startsWithA.and(endsWithX);
        String input = "A man is standing there with a box";
        assertTrue(startsWithAAndEndsWithX.test(input));
    }

    @Test
    public void givenTwoPredicates_thenComposeThemUsingOr() {
        Predicate<String> startsWithA = (text) -> text.startsWith("A");
        Predicate<String> endsWithX = (text) -> text.endsWith("x");
        Predicate<String> startsWithOrEndsWithX = startsWithA.or(endsWithX);
        String input = "A man";
        assertTrue(startsWithOrEndsWithX.test(input));
    }

    @Test
    public void givenTwoPredicates_thenComposeThemUsingCompose() {
        Function<Integer, Integer> squareOp = (value) -> value * value;
        Function<Integer, Integer> doubleOp = (value) -> 2 * value;
        // 先执行 doubleOp 再执行 squareOp
        Function<Integer, Integer> doubleThenSquare = squareOp.compose(doubleOp);
        assertEquals(36, doubleThenSquare.apply(3));
    }

    @Test
    public void givenTwoPredicates_thenComposeThemUsingAndThen() {
        Function<Integer, Integer> squareOp = (value) -> value * value;
        Function<Integer, Integer> doubleOp = (value) -> 2 * value;
        // 先执行 squareOp 再执行 doubleOp
        Function<Integer, Integer> doubleThenSquare = squareOp.andThen(doubleOp);
        assertEquals(18, doubleThenSquare.apply(3));
    }
}
