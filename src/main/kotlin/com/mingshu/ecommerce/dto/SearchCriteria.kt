package com.mingshu.ecommerce.dto

import com.mingshu.ecommerce.dto.GenericSpecification.SearchOperation
import lombok.Getter
import lombok.Setter

@Getter
@Setter
class SearchCriteria {

	val key: String = ""
	val value: String = ""
	val operation: SearchOperation = SearchOperation.EQUAL
}