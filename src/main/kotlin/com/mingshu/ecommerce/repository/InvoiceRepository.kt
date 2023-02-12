package com.mingshu.ecommerce.repository

import com.mingshu.ecommerce.model.Invoice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface InvoiceRepository: JpaRepository<Invoice, Int>, JpaSpecificationExecutor<Invoice> {
}