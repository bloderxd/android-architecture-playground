package bloder.com.data.payloads

import bloder.com.data.values.Company

data class CompanyPayload(private val name: String) {

    fun toValue(): Company = Company(name)
}