package com.adidas.bdd.runner;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-reports/failed-scenarios"},
        glue = {"com.adidas.api.bdd.steps"},
        features = {"@rerun/failedScenarios/failed_scenarios.txt"})

@RunWith(CucumberWithSerenity.class)
public class FailedScenariosReRunner {

}