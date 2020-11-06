package walter.com.tottustest.infraestructure.model

/**
 * Project name: TottusTest
 * Created by Walter Cojal on 11/4/20.
 */

sealed class Result<out T: Any> {
    data class Success<out T: Any>(val body: T): Result<T>()
    data class Error(val error: ApiError): Result<Nothing>()
}

data class ApiError (
    val code: Int,
    val message: String
)