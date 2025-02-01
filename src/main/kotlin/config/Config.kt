package config

import kotlinx.serialization.Serializable

@Serializable
data class Config(
    val token: String,
    val username: String,
) {
    companion object {
        fun empty() = Config(token = "", username = "")
    }
}
