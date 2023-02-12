package com.mingshu.ecommerce.dto

import com.mingshu.ecommerce.model.Invoice
import java.math.BigDecimal
import java.util.*
import kotlin.reflect.full.memberProperties

data class InvoiceDto (

	val invoiceNo: String,
	val stockCode: String,
	val description: String,
	val quantity: Int,
	val invoiceDate: Date,
	val unitPrice: BigDecimal,
	val customerID: String,
	val country: String

)

fun Invoice.toInvoiceDtoReflection() = with(::InvoiceDto) {

	val propertiesByName = Invoice::class.memberProperties.associateBy { it.name }

//	callBy(parameters.associate { parameter ->
//		parameter to when (parameter.name) {
//			InvoiceDto::invoiceNo.name -> invoiceNo
//			InvoiceDto::stockCode.name -> stockCode
//			InvoiceDto::description.name -> description
//			InvoiceDto::quantity.name -> quantity
//			InvoiceDto::invoiceDate.name -> invoiceDate
//			InvoiceDto::unitPrice.name -> unitPrice
//			InvoiceDto::customerID.name -> customerID
//			InvoiceDto::country.name -> country
//			else -> propertiesByName[parameter.name]?.get(this@toInvoiceDtoReflection)
//		}
//	})

}