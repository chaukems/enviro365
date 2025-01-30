package com.enviro.assessment.senior001.matimbasydneychauke.controller;

import com.enviro.assessment.senior001.matimbasydneychauke.entity.Report;
import com.enviro.assessment.senior001.matimbasydneychauke.exception.Response;
import com.enviro.assessment.senior001.matimbasydneychauke.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/report-service")
@SecurityRequirement(name = "Authorization")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Operation(summary = "Store report", description = "Add new report")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = Response.class))})})
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveReport(@RequestBody Report payload) throws Exception {
        Response response = Response.builder().
                withData("Report saved successfully")
                .withErrorCode(null)
                .withResultCode("200")
                .withResultMessage("Success").build();
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Operation(summary = "Updates report", description = "Report must exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = Response.class))})})
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateReport(@RequestBody Report payload) throws Exception {
        Response response = Response.builder().
                withData("Report updated successfully")
                .withErrorCode(null)
                .withResultCode("200")
                .withResultMessage("Success").build();
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Operation(summary = "Retrieves all reports", description = "Reports must exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = Response.class))})})
    @GetMapping(path = "/getReports", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllReports() throws Exception {
        Response response = Response.builder().
                withData("Reports retrieved successfully")
                .withErrorCode(null)
                .withResultCode("200")
                .withResultMessage("Success").build();
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Operation(summary = "Retrieve a single report", description = "Reports must exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = Response.class))})})
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getReports(@PathVariable("id") long id) throws Exception {
        Response response = Response.builder().
                withData("Report retrieved successfully")
                .withErrorCode(null)
                .withResultCode("200")
                .withResultMessage("Success").build();
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Operation(summary = "Delete report", description = "Reports must exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = Response.class))})})
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteReport(@PathVariable("id") long id) throws Exception {
        Response response = Response.builder().
                withData("Report deleted successfully")
                .withErrorCode(null)
                .withResultCode("200")
                .withResultMessage("Success").build();
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}

