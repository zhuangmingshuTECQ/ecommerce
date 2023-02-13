package com.mingshu.ecommerce.controller

import com.mingshu.ecommerce.dto.GenericSpecification
import com.mingshu.ecommerce.dto.SearchRequest
import com.mingshu.ecommerce.dto.SearchResponse
import com.mingshu.ecommerce.dto.UploadResponse
import com.mingshu.ecommerce.model.Invoice
import com.mingshu.ecommerce.service.InvoiceService
import lombok.extern.log4j.Log4j2
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@Log4j2
@RestController
class InvoiceController(private val invoiceService: InvoiceService) {
    @PostMapping("/upload", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun uploadCsv(@RequestParam(value = "file") file: MultipartFile): UploadResponse {
        invoiceService.uploadCsv(file)

        return UploadResponse("Success")
    }

    @PostMapping("/search", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun displayInvoices(@RequestBody searchRequest: SearchRequest): ResponseEntity<SearchResponse> {
        val specification: GenericSpecification<Invoice> = GenericSpecification()
        searchRequest.searchCriteriaList.forEach(specification::add)

        return ResponseEntity.ok(invoiceService.displayInvoices(specification, searchRequest.page, searchRequest.size))
    }
}