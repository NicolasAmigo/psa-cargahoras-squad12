package com.squad12.cargaHoras;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.test.context.TestPropertySource;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/cucumber")
@TestPropertySource(locations = "src/test/resources:test.properties")
public class CucumberTest {}
