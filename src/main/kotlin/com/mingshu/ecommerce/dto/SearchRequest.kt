package com.mingshu.ecommerce.dto

data class SearchRequest(
    val searchCriteriaList: List<SearchCriteria>,
    val page: Int,
    val size: Int,
)