package com.mingshu.ecommerce.utils

import com.mingshu.ecommerce.model.Invoice
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.math.BigDecimal
import java.math.RoundingMode
import java.nio.charset.StandardCharsets

class CSVUtil {

	enum class InvoiceHeaders {
		INVOICE_NO,
		STOCK_CODE,
		DESCRIPTION,
		QUANTITY,
		INVOICE_DATE,
		UNIT_PRICE,
		CUSTOMER_ID,
		COUNTRY
	}

	fun hasCSVFormat(file: MultipartFile) {

	}

	fun csvToInvoice(inputStream: InputStream) : List<Invoice>  {

		val invoices = ArrayList<Invoice>()

		try {
			val bufferedReader = BufferedReader(InputStreamReader(inputStream, StandardCharsets.UTF_8))

			val csvParser = CSVParser(
				bufferedReader,
				CSVFormat.Builder.create()
					.setTrim(true)
					.setHeader(InvoiceHeaders::class.java)
					.setSkipHeaderRecord(true)
					.setIgnoreHeaderCase(true)
					.setAllowMissingColumnNames(false)
					.setAllowDuplicateHeaderNames(false)
					.build()
			)

			for (csvRecord in csvParser) {

				val invoice = Invoice()
				invoice.invoiceNo = csvRecord.get(InvoiceHeaders.INVOICE_NO)
				invoice.invoiceDate = csvRecord.get(InvoiceHeaders.INVOICE_DATE)
				invoice.country = csvRecord.get(InvoiceHeaders.COUNTRY)
				invoice.unitPrice =	BigDecimal(csvRecord.get(InvoiceHeaders.UNIT_PRICE)).setScale(2, RoundingMode.HALF_UP)
				invoice.quantity = Integer.parseInt(csvRecord.get(InvoiceHeaders.QUANTITY))
				invoice.stockCode = csvRecord.get(InvoiceHeaders.STOCK_CODE)
				invoice.customerID = csvRecord.get(InvoiceHeaders.CUSTOMER_ID)
				invoice.description = csvRecord.get(InvoiceHeaders.DESCRIPTION)

				invoices.add(invoice)

			}

			bufferedReader.close()
			csvParser.close()
		} catch (ex: IOException) {
			println(ex)
		}

		return invoices

	}

}