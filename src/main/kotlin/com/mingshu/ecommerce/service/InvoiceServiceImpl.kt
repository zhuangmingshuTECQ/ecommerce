package com.mingshu.ecommerce.service

import com.mingshu.ecommerce.dto.InvoiceSpecification
import com.mingshu.ecommerce.dto.SearchCriteria
import com.mingshu.ecommerce.dto.SearchResponse
import com.mingshu.ecommerce.dto.toInvoiceDto
import com.mingshu.ecommerce.model.Invoice
import com.mingshu.ecommerce.repository.InvoiceRepository
import com.mingshu.ecommerce.utils.CSVUtil
import lombok.extern.log4j.Log4j2
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.util.regex.Matcher
import java.util.regex.Pattern
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

    override fun findAll(query: String, page: Int, size: Int, filter: String): SearchResponse {
        val response = SearchResponse()
        val pageable: Pageable = PageRequest.of(page, size)

        var specification: InvoiceSpecification = InvoiceSpecification()
        val pattern: Pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),")
        val matcher: Matcher = pattern.matcher(filter + ",")
        while (matcher.find()) {
            specification.add(SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)))
        }
        val invoicePage: Page<Invoice> = invoiceRepository.findAll(Specification.where(InvoiceSpecification().containsTextInAttributes(query)).and(specification), pageable)
        response.invoices = invoicePage.content.stream().map { it.toInvoiceDto() }.collect(Collectors.toList())
        response.totalElements = invoicePage.totalElements

        return response
    }
}