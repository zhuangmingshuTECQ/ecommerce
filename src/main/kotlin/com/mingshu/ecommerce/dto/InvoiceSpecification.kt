package com.mingshu.ecommerce.dto

import com.mingshu.ecommerce.model.Invoice
import org.springframework.data.jpa.domain.Specification
import java.math.BigDecimal
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Root

public class InvoiceSpecification {
    fun containsTextInAttributes(text: String): Specification<Invoice>? {
        var text = text
        var finalText = text
        if (!text.contains("%")) {
            finalText = "%$text%".lowercase()
        }

        return Specification<Invoice> { root: Root<Invoice>, query: CriteriaQuery<*>?, builder: CriteriaBuilder ->
            builder.or(
                builder.like(builder.lower(root.get("invoiceNo")), finalText),
                builder.like(builder.lower(root.get("stockCode")), finalText),
                builder.like(builder.lower(root.get("description")), finalText),
                builder.equal(root.get<BigDecimal>("quantity"), BigDecimal(text)),
                builder.equal(root.get<BigDecimal>("unitPrice"), BigDecimal(text)),
                builder.like(builder.lower(root.get("customerID")), finalText),
                builder.like(builder.lower(root.get("country")), finalText),
            )
        }
    }
}