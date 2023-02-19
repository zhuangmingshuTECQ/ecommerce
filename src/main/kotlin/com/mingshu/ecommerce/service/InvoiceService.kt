package com.mingshu.ecommerce.service

import com.mingshu.ecommerce.dto.SearchResponse
import org.springframework.web.multipart.MultipartFile

interface InvoiceService {

    fun uploadCsv(file: MultipartFile)

    fun findAll(query: String, page: Int, size: Int, filter: String): SearchResponse
}