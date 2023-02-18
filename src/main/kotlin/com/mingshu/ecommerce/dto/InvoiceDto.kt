package com.mingshu.ecommerce.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.mingshu.ecommerce.model.Invoice
import java.math.BigDecimal
import java.time.LocalDateTime

data class InvoiceDto(
    val invoiceNo: String,
    val stockCode: String,
    val description: String,
    val quantity: Int,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val invoiceDate: LocalDateTime,
    val unitPrice: BigDecimal,
    val customerID: String,
    val country: String,
)

fun Invoice.toInvoiceDto() = InvoiceDto(
    invoiceNo = invoiceNo,
    stockCode = stockCode,
    description = description,
    quantity = quantity,
    invoiceDate = invoiceDate,
    unitPrice = unitPrice,
    customerID = customerID,
    country = country
)