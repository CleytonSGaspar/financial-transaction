package br.com.financialtransaction.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

  @Value("${info.app.name}")
  private String tittle;

  @Value("${info.app.description}")
  private String description;

  @Value("${info.app.version}")
  private String version;

  @Bean
  public Docket docket() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build()
        .useDefaultResponseMessages(false)
        .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title(tittle)
        .description(description)
        .version(version)
        .license("Apache License Version 2.0")
        .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
        .contact(new Contact("Cleyton", "https://github.com/CleytonSGaspar/financial-transaction", "cleyton_s_gaspar@hotmail.com"))
        .build();
  }
}
