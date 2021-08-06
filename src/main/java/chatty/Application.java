package chatty;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info =

        @Info(
                title = "Data Collection API",
                version = "0.0",
                description = "Class informations that are required for communication between the WoZ Frontend and the Backend"

        )
)
public class Application {


    public static void main(String[] args) {
        Micronaut.run(Application.class);
    }
}