package com.mingshu.ecommerce.controller

import com.mingshu.ecommerce.dto.*
import com.mingshu.ecommerce.model.Invoice
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

    @PostMapping("/transactions", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun displayInvoices(@RequestBody searchRequest: SearchRequest): ResponseEntity<SearchResponse> {
        val specification: GenericSpecification<Invoice> = GenericSpecification()
        searchRequest.searchCriteriaList.forEach(specification::add)

        return ResponseEntity.ok(invoiceService.displayInvoices(specification, searchRequest.page, searchRequest.size))
    }

    @GetMapping("/transactions")
    fun showAll(
        @RequestParam(name = "query") query: String,
        @RequestParam(name = "page") page: Int
    ): ResponseEntity<SearchResponse> {
        return ResponseEntity.ok(invoiceService.findAll(query, page))
    }
}