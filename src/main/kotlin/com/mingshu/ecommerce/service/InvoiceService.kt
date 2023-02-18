package com.mingshu.ecommerce.service

import com.mingshu.ecommerce.dto.GenericSpecification
import com.mingshu.ecommerce.dto.SearchResponse
import com.mingshu.ecommerce.model.Invoice
import org.springframework.web.multipart.MultipartFile

interface InvoiceService {

	fun uploadCsv(file: MultipartFile)

	fun displayInvoices(specification: GenericSpecification<Invoice>, page: Int, size: Int): SearchResponse
	fun findAll(query: String, page: Int): SearchResponse
}