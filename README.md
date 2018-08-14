Parameterized Tests With Rules
==============================

Demos how parameterized test can be implemented using JUnit Rules without a custom TestRunner.

You can define Generators for creating all the parameters to use for the tests. 

Inside the test you can access the current value of the parameter using the value() method of the rule.

There is one working example of a Generator, the ListGenerator which takes the List of parameters as an constructor argument.

Example usage looks like this:


    public class SimpleExampleTest {

        @Rule
        public Generator<String> params = 
            new ListGenerator(asList("alpha", "beta", "gamma"));

        @Test
        public void testSomething() throws Exception {
            assertTrue(params.value().length() >= 4);
        }
    }
    

I also blogged about it here: http://blog.schauderhaft.de/2012/12/16/writing-parameterized-tests-with-junit-rules/
