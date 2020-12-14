package com.xkcoding.learn.java_functional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <p>
 * 亿点点分享（上）- 函数式编程测试类
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-12-13 23:03
 */
@Slf4j
public class FunctionalTests {

    @AllArgsConstructor
    @Getter
    static class Book {
        private final String title;
        private final String author;
        private final Integer pages;
    }

    @Test
    public void givenListOfBooks_thenExplainTheAdvantageOfFunction() {
        String authorA = "张三";
        String authorB = "李四";
        String authorC = "王五";
        List<Book> books = Arrays.asList(
                new Book("C++编程", authorA, 1216),
                new Book("C#编程", authorA, 365),
                new Book("Java编程", authorB, 223),
                new Book("Ruby编程", authorB, 554),
                new Book("PHP天下第一", authorB, 607),
                new Book("Python牛逼", authorC, 352)
        );
        List<Book> booksFiltered = new ArrayList<>();
        for (Book book : books) {
            if (!book.getTitle()
                    .contains("C")) {
                booksFiltered.add(book);
            }
        }
        booksFiltered.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o2.getPages()
                        .compareTo(o1.getPages());
            }
        });
        for (int i = 0; i < 3; i++) {
            System.out.println(booksFiltered.get(i)
                    .getAuthor());
        }

        System.out.println("==========我是分割线==========");
        books.stream()
                .filter(b -> !b.getTitle()
                        .contains("C"))
                .sorted((b1, b2) -> b2.getPages()
                        .compareTo(b1.getPages()))
                .limit(3)
                .map(Book::getAuthor)
                .distinct()
                .forEach(System.out::println);
    }

    static class ObjectPureFunction {
        public int sum(int a, int b) {
            return a + b;
        }
    }

    static class ObjectNotPureFunction {
        private int value = 0;

        public int sum(int countValue) {
            this.value += countValue;
            return this.value;
        }
    }

    @Test
    public void givenFunctionalInterface_thenFunctionAsArgumentsOrReturnValue() {
        HigherOrderFunctionClass higherOrderFunctionClass = new HigherOrderFunctionClass();
        IFactory<User> factory = higherOrderFunctionClass.createFactory(() -> User.builder()
                .id(100L)
                .name("xxx")
                .build(), (user) -> {
            log.debug("用户信息: {}", user);
            user.setMobile("13012345678");
        });
        User user = factory.create();
        assertEquals("xxx", user.getName());
        assertEquals(100L, user.getId());
        assertEquals("13012345678", user.getMobile());
    }

    interface IFactory<T> {
        T create();
    }

    interface IProducer<T> {
        T produce();
    }

    interface IConfigurator<T> {
        void configure(T t);
    }

    class TestProducer implements IProducer {
        @Override
        public Object produce() {
            return null;
        }
    }

    static class HigherOrderFunctionClass {
        public <T> IFactory<T> createFactory(IProducer<T> producer, IConfigurator<T> configurator) {
            return () -> {
                T instance = producer.produce();
                configurator.configure(instance);
                return instance;
            };
        }
    }
}
