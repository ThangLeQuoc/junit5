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

### Migration from JUnit 4 to Junit 5


##### Tiny Note
Remaining Issues
- Maven Surefire Plugin doesn't detect Junit 5 tests

###### References
http://www.baeldung.com/junit-5-preview



