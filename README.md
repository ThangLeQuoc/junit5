# JUnit 5
Unlike previous versions of JUnit, JUnit 5 is composed of several different modules
from three different sub projects
> **JUnit 5** = **JUnit Platform** + **JUnit Jupiter** + **Junit Vintage**
## A deeper look
### Installation
```
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <version>5.1.0</version>
    <scope>test</scope>
</dependency>
```
>JUnit 5 required Java 8 to work

### JUnit Platform
The platform is responsible for launching testing frameworks on the JVM. It defines a stable and powerful interface between JUnit and its client such as build tools.

The final objective is how its clients get integrated easily with JUnit in discovering and executing the tests.

It also defines the TestEngine API for developing a testing framework that runs on the JUnit platform. By that, you can plug-in 3rd party testing libraries, directly into JUnit, by implementing custom TestEngine.
### JUnit Jupiter
JUnit Jupiter is the combination of the new programming model and extension model for writing tests and extensions in JUnit 5
### JUnit Vintage
Support running JUnit 3 and JUnit 4 based tests on JUnit 5 platform
### Basic Annotations
#### `@BeforeAll` and `@BeforeEach`, `@AfterAll` and `@AfterEach`
- `@BeforeAll` acts as a replacement for JUnit 4 `@BeforeClass`, specifies this method will execute once before all tests in the class
- `@BeforeEach` acts as a replacement for JUnit 4 `@Before`, specifies this method
will execute before each test method in the class
- `@AfterAll` acts as a replacement for JUnit 4 `@AfterClass`, specifies this method will execute one after all tests in the class
- `@AfterEach` acts as a replacement for JUnit 4 `@After`, specifies this method will execute after each test method in the class

```
@BeforeAll
public static void setUp() {
    System.out.println("To be printed before all test");
}

@BeforeEach
public void init() {
    System.out.println("To be printed before each test execution");
}

@AfterEach
public void tearDown() {
    System.out.println("To be printed after each test execution");
}

@AfterAll
public static void done() {
    System.out.println("To be printed after all tests execution");
}
```

#### `@Disable`
`@Disable` is a replacement for JUnit 4 `@Ignore`

```
@Disabled
public void testDisableAnnotation() {
    System.out.println("This test will not be executed");
}
```
#### `@DisplayName`
Test classes and test methods can declare custom display names  with spaces, special characters, and even emojis   that will be displayed by test runners and test reporting.
```
@Test
@DisplayName("╯°□°）╯ OMG I'm about to test a shark")
public void testFishCreation() {
    Fish fish = new Fish();
    String fishName = "Shark";
    fish.setName(fishName);
    assertEquals(fishName, fish.getName());
}
```
![Result with display name](https://i.imgur.com/tGdk445.png)

#### `@Tag`
(work in progress)
#### `@Nested`
(work in progress)
#### Assertion
##### `assertAll()`
It is now possible to group assertions with `assertAll()` which will report any failed assertions within the group with a `MultipleFailuresError`
```
@Test
public void testFishAllAssertion() {
    // given
    String name = "Golden Fish";
    int price = 10;

    //when
    Fish fish = new Fish();
    fish.setName(name);
    fish.setPrice(price);

    // then
    assertAll("Fish Creation", () -> assertEquals(name, fish.getName()),
            () -> assertEquals(price, fish.getPrice()));


}
```
> What is the benefit of `assertAll()` over multiple assertions ?

Clearly we can write multiple assertions
```
assertEquals(name, fish.getName());
assertEquals(price, fish.getClass());
```
So what is the best place for `assertAll()` ?
>`assertAll` is that it always checks all of the assertions that are passed to it, no matter how many fail. If all pass, all is fine - if at least one fails you get a detailed result of all that went wrong

So if the `fish.getName()` throws assertion error, the test would halt immediately, and you won't be able to see the assertion result of the fish price.

> It (`assertAll`) is best used for asserting a set of properties that belong together conceptionally. Something where your first instinct would be, "I want to assert this as one".

With `assertAll`, you will get a detail error of what went wrong

```
org.opentest4j.MultipleFailuresError: Fish Creation (2 failures)
	expected: <sharky> but was: <Golden Fish>
	expected: <150> but was: <10>
```

##### Exception Testing
* JUnit 4 Exception Test
```
@Test(expected = IllegalArgumentException.class)
public void testSetPrice_ShouldThrowException_JUnit4Expression() {
    int price = -1;
    Fish fish = new Fish();

    try {
        fish.setPrice(price);
        fail("should throw exception earlier");
    } catch (IllegalArgumentException e) {
        assertEquals("Illegal Price", e.getMessage());
        throw e;
    }
}
```
* JUnit 5 comes with new `assertThrows()`, which make the test more declarative
```
@Test
public void testSetPrice_ShouldThrowException_JUnit5Expression() {
    int price = -1;
    Fish fish = new Fish();
    Throwable actualException = assertThrows(IllegalArgumentException.class, () -> {
        fish.setPrice(price);
    });
    assertEquals("Illegal Price", actualException.getMessage());
}
```
#### Assumptions
Assumptions are typically used whenever it does not make sense to continue execution of a given test method — for example, if the test depends on something that does not exist in the current runtime environment.
```
assumeTrue()
assumeFalse()
assumeNoException()
assumeNotNull()
```

```
public class AppTest {
    @Test
    void testOnDev()
    {
        System.setProperty("ENV", "DEV");
        Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")));
        //remainder of test will proceed
    }

    @Test
    void testOnProd()
    {
        System.setProperty("ENV", "PROD");
        Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")), AppTest::message);
        //remainder of test will be aborted
    }

    private static String message () {
        return "TEST Execution Failed :: ";
    }
}
```
#### Dynamic Test with `@TestFactory`
(work in progress)
###### References
http://www.baeldung.com/junit-5

http://www.baeldung.com/junit-5-preview

https://howtodoinjava.com/junit-5/junit-5-assumptions-examples/

https://stackoverflow.com/questions/40796756/assertall-vs-multiple-assertions-in-junit5

http://www.baeldung.com/junit5-dynamic-tests


