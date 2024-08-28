package com.example.demo.api.controllers;


import com.example.demo.infraestructuras.abstract_service.ReportService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Tag(name ="Reportes")
public class Repportcontrollers {

    private final ReportService reportService;

    @GetMapping("/report")
    public ResponseEntity<Resource> get(){
        var headers= new HttpHeaders();
        headers.setContentType(FORCE_DOWNLOAD);
        headers.set(HttpHeaders.CONTENT_DISPOSITION,FORCE_DOWNLOAD_HEADER_VALUE);


        var fileByte=this.reportService.readFile();
        ByteArrayResource response=new ByteArrayResource(fileByte);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(fileByte.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(response);

    }

    private static final MediaType FORCE_DOWNLOAD=new MediaType("application","force-download");
    private static final String FORCE_DOWNLOAD_HEADER_VALUE="attachment; filename=report.xlsx";

}
