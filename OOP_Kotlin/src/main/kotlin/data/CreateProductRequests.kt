package data

import annotations.NotBlank

class CreateProductRequests(
        @NotBlank val id: String,
        @NotBlank val name: String,
        val price: Long
)

class CreateCategoryRequests(
        @NotBlank val id: String,
        @NotBlank val name: String
)