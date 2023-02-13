package com.mingshu.ecommerce.utils

import com.mingshu.ecommerce.exception.CSVException
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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CSVUtil {
    enum class InvoiceHeaders {
        InvoiceNo,
        StockCode,
        Description,
        Quantity,
        InvoiceDate,
        UnitPrice,
        CustomerID,
        Country,
    }

    fun hasCSVFormat(file: MultipartFile): Boolean {
        if (file.isEmpty) throw CSVException("File is empty")
        if (null == file.contentType) throw CSVException("Content type is empty")
        if (!arrayOf("text/csv", "application/vnd.ms-excel").contains(file.contentType))
            throw CSVException("Unaccepted media type. Only csv files are accepted")

        return true
    }

    fun csvToInvoice(inputStream: InputStream): List<Invoice> {
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
                invoice.invoiceNo = csvRecord.get(InvoiceHeaders.InvoiceNo)
                invoice.invoiceDate =
                    LocalDateTime.parse(
                        csvRecord.get(InvoiceHeaders.InvoiceDate),
                        DateTimeFormatter.ofPattern("M/d/yyyy H:mm")
                    )
                invoice.country = csvRecord.get(InvoiceHeaders.Country)
                invoice.unitPrice =
                    BigDecimal(csvRecord.get(InvoiceHeaders.UnitPrice)).setScale(2, RoundingMode.HALF_UP)
                invoice.quantity = Integer.parseInt(csvRecord.get(InvoiceHeaders.Quantity))
                invoice.stockCode = csvRecord.get(InvoiceHeaders.StockCode)
                invoice.customerID = csvRecord.get(InvoiceHeaders.CustomerID)
                invoice.description = csvRecord.get(InvoiceHeaders.Description)

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