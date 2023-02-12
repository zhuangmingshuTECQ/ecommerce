package com.mingshu.ecommerce.model

import java.math.BigDecimal
import javax.persistence.*


@Entity
@Table(name = "INVOICE")
class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	var id = 0

	@Column
	var invoiceNo = ""

	@Column
	var stockCode = ""

	@Column
	var description = ""

	@Column
	var quantity = 0

	@Column
	var invoiceDate = ""

	@Column
	var unitPrice = BigDecimal(0.00)

	@Column
	var customerID = ""

	@Column
	var country = ""

}