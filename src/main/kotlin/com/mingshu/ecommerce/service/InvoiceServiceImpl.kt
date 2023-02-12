package com.mingshu.ecommerce.service

import com.mingshu.ecommerce.dto.GenericSpecification
import com.mingshu.ecommerce.dto.SearchResponse
import com.mingshu.ecommerce.model.Invoice
import com.mingshu.ecommerce.repository.InvoiceRepository
import com.mingshu.ecommerce.utils.CSVUtil
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.util.stream.Collectors


@Service
class InvoiceServiceImpl(private val invoiceRepository: InvoiceRepository): InvoiceService {
	override fun uploadCsv(file: MultipartFile) {

		try {

			invoiceRepository.saveAllAndFlush(CSVUtil().csvToInvoice(file.inputStream))

		} catch (ex: IOException) {

			println(ex)

		}

	}

	override fun displayInvoices(specification: GenericSpecification<Invoice>, page: Int, size: Int) : SearchResponse {

		var response = SearchResponse()

		val pageable: Pageable = PageRequest.of(page, size)

		val invoicePage: Page<Invoice> = invoiceRepository.findAll(specification, pageable)

//		response.invoices = invoicePage.content.stream().map(invoice -> InvoiceDto(invoice.invoice)).collect(Collectors.toList())
		response.totalElements = invoicePage.numberOfElements

		return response
	}


}