package ohtu;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;

public class Stepdefs {
    Counter counter;
    
    @Given("^Counter is initialized$")
    public void counter_is_initialized() throws Throwable {
        counter = new Counter();
    }

    @When("^it is incremented$")
    public void it_is_incremented() throws Throwable {
        counter.increase();
    }

    @When("^it is incremented by (\\d+)$")
    public void it_is_incremented_by(int val) throws Throwable {
        counter.increment(val);
    }     

    @Then("^the value should be (\\d+)$")
    public void the_value_should_be(int val) throws Throwable {
        assertEquals(val, counter.value());
    }
  
}
