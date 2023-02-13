package com.mingshu.ecommerce.dto

import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class GenericSpecification<T> : Specification<T> {
    enum class SearchOperation {
        EQUAL,
        CONTAIN,
        GREATER_THAN,
        LESS_THAN,
    }

    private var list: ArrayList<SearchCriteria> = arrayListOf()
    fun add(criteria: SearchCriteria) {
        list.add(criteria)
    }

    override fun toPredicate(root: Root<T>, query: CriteriaQuery<*>, builder: CriteriaBuilder): Predicate? {
        val predicates = ArrayList<Predicate>()
        for (criteria in list) {
            when (criteria.operation) {
                SearchOperation.EQUAL -> predicates.add(builder.equal(root.get<String>(criteria.key),criteria.value))
                SearchOperation.CONTAIN -> TODO()
                SearchOperation.GREATER_THAN -> TODO()
                SearchOperation.LESS_THAN -> TODO()
            }
        }
        return builder.and(*predicates.toTypedArray())
    }
}