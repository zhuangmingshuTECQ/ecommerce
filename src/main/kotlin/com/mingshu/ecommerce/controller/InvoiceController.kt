package com.mingshu.ecommerce.controller

import com.mingshu.ecommerce.dto.*
import com.mingshu.ecommerce.service.InvoiceService
import lombok.extern.log4j.Log4j2
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@CrossOrigin("http://localhost:3000")
@Log4j2
@RestController
class InvoiceController(private val invoiceService: InvoiceService) {
    @PostMapping("/upload", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun uploadCsv(@RequestParam(value = "file") file: MultipartFile): UploadResponse {
        invoiceService.uploadCsv(file)

        return UploadResponse("Success")
    }

    @GetMapping("/transactions")
    fun showAll(
        @RequestParam(name = "query") query: String,
        @RequestParam(name = "page") page: Int,
        @RequestParam(name = "size") size: Int,
        @RequestParam(name = "filter") filter: String,
    ): ResponseEntity<SearchResponse> {
        return ResponseEntity.ok(invoiceService.findAll(query, page, size, filter))
    }
}