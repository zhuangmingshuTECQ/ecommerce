package com.mingshu.ecommerce.service

import com.mingshu.ecommerce.dto.GenericSpecification
import com.mingshu.ecommerce.dto.SearchResponse
import com.mingshu.ecommerce.dto.toInvoiceDto
import com.mingshu.ecommerce.model.Invoice
import com.mingshu.ecommerce.repository.InvoiceRepository
import com.mingshu.ecommerce.utils.CSVUtil
import lombok.extern.log4j.Log4j2
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.util.stream.Collectors

@Log4j2
@Service
class InvoiceServiceImpl(private val invoiceRepository: InvoiceRepository) : InvoiceService {
    override fun uploadCsv(file: MultipartFile) {
        if (CSVUtil().hasCSVFormat(file)) try {
            invoiceRepository.saveAllAndFlush(CSVUtil().csvToInvoice(file.inputStream))
        } catch (ex: IOException) {
            println(ex)
        }
    }

    override fun displayInvoices(specification: GenericSpecification<Invoice>, page: Int, size: Int): SearchResponse {
        val response = SearchResponse()
        val pageable: Pageable = PageRequest.of(page, size)
        val invoicePage: Page<Invoice> = invoiceRepository.findAll(specification, pageable)
        response.invoices = invoicePage.content.stream().map { it.toInvoiceDto() }.collect(Collectors.toList())
        response.totalElements = invoicePage.totalElements

        return response
    }
    
    override fun findAll(page: Int): SearchResponse {
        val response = SearchResponse()
        val pageable: Pageable = PageRequest.of(page, 30)
        val invoicePage: Page<Invoice> = invoiceRepository.findAll(pageable)
        response.invoices = invoicePage.content.stream().map { it.toInvoiceDto() }.collect(Collectors.toList())
        response.totalElements = invoicePage.totalElements

        return response
    }
}