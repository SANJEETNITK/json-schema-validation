package com.example.jsonschemavalidation.controller;


import com.example.jsonschemavalidation.request.EmployeeRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.Set;

@RestController
public class EmployeeValidationController {

    @PostMapping("/validate-employee")
    public String validateEmployee(@RequestBody String requestBody) throws JsonProcessingException {

        InputStream schemaAsStream = EmployeeValidationController.class.getClassLoader().getResourceAsStream("model/employeerequestschema.json");
        JsonSchema schema = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7).getSchema(schemaAsStream);

        ObjectMapper om = new ObjectMapper();
        om.setPropertyNamingStrategy(PropertyNamingStrategy.KEBAB_CASE);
        JsonNode jsonNode = om.readTree(requestBody);

        Set<ValidationMessage> errors = schema.validate(jsonNode);
        String errorsCombined = "";
        for (ValidationMessage error : errors) {
            errorsCombined += error.toString() + "\n";
        }

        if (errors.size() > 0) {
            return errorsCombined;
        }

        EmployeeRequest request = om.readValue(requestBody, EmployeeRequest.class);
        return "Request Validated successfully" + "\n" + request.toString();
    }

}
