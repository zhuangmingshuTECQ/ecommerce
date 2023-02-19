package com.mingshu.ecommerce.dto

import lombok.Getter
import lombok.Setter

@Getter
@Setter
class SearchCriteria(key: String?, value: Any?, operation: String, ) {
    val key: String = ""
    val value = null
    val operation: String = "="
}