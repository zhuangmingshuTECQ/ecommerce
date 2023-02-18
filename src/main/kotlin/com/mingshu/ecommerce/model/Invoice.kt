package com.mingshu.ecommerce.model

import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "INVOICE")
class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0L
    
    @Column
    var invoiceNo = ""
    
    @Column
    var stockCode = ""
    
    @Column
    var description = ""
    
    @Column
    var quantity = 0

    @Column
    var invoiceDate: LocalDateTime = LocalDateTime.now()
    
    @Column(scale = 2)
    var unitPrice: BigDecimal = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)
    
    @Column
    var customerID = ""
    
    @Column
    var country = ""
}