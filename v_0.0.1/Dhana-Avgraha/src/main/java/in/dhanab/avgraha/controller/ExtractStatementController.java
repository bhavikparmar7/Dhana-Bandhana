package in.dhanab.avgraha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in.dhanab.avgraha.enums.Banks;
import in.dhanab.avgraha.enums.Products;
import in.dhanab.avgraha.service.ExtractStatementService;

@RestController
@RequestMapping("/avgraha")
public class ExtractStatementController {

    @Autowired
    private ExtractStatementService extractStatementService;

    @PostMapping("/single/upload")
    public ResponseEntity<String> fileUploading(@RequestParam("file") MultipartFile file, @RequestParam("bankName") Banks bankName, @RequestParam("productName") Products productName) {
        extractStatementService.uploadAndExtractStatement(file, bankName, productName);
        return ResponseEntity.ok("Successfully uploaded the file");
    }
}
