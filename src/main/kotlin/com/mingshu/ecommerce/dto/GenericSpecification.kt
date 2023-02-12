package com.mingshu.ecommerce.dto

import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class GenericSpecification<T>: Specification<T> {

	private var list: List<SearchCriteria> = listOf()
	fun add(criteria: SearchCriteria) {
		with(list) { add(criteria) }
	}

	override fun toPredicate(root: Root<T>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate? {

		var predicates = ArrayList<Predicate>()

		for (searchCriteria in list) {
			when (searchCriteria.operation) {
				SearchOperation.EQUAL -> predicates.add(criteriaBuilder.equal(root.get<String>(searchCriteria.key), searchCriteria.value))
				SearchOperation.CONTAIN -> TODO()
				SearchOperation.GREATER_THAN -> TODO()
				SearchOperation.LESS_THAN -> TODO()
			}

		}

		return criteriaBuilder.and(*predicates.toTypedArray())

	}

	public enum class SearchOperation {
		EQUAL,
		CONTAIN,
		GREATER_THAN,
		LESS_THAN

	}

}