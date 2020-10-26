package com.adidas.bdd.runner;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@CucumberOptions(

        plugin = {"pretty", "html:target/cucumber-reports", "rerun:rerun/failedScenarios/failed_scenarios.txt"},
        glue = {"com.adidas.bdd.steps"},
        features = {"src/test/resources/features/PurchaseSonyLaptop.feature"}

)

@RunWith(CucumberWithSerenity.class)
public class CucumberTestRunner {

}