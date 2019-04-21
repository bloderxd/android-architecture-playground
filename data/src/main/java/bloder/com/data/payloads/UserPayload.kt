package bloder.com.data.payloads

import bloder.com.data.values.User

data class UserPayload(
        private val id: Long,
        private val name: String,
        private val userName: String,
        private val email: String,
        private val company: CompanyPayload
) {

    fun toValue(): User = User(id, name, userName, email, company.toValue())
}