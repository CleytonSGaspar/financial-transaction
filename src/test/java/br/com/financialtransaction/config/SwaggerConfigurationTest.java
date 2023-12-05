package br.com.financialtransaction.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import springfox.documentation.spring.web.plugins.Docket;

class SwaggerConfigurationTest {
    /**
     * Method under test: {@link SwaggerConfiguration#docket()}
     */
    @Test
    void testDocket() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        Docket actualDocketResult = (new SwaggerConfiguration()).docket();
        assertTrue(actualDocketResult.isEnabled());
        assertEquals("default", actualDocketResult.getGroupName());
    }
}

