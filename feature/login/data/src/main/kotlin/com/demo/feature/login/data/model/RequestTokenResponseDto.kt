package com.demo.feature.login.data.model

import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class RequestTokenResponseDto(
    val success: Boolean,
    val request_token: String,
    @Serializable(TMDBInstantSerializer::class)
    val expires_at: Instant
)


//"account_id": "4bc8892a017a3c0z92001001",
//"access_token": "eyJhbGciOiJIUzI1NiIsInR5cCIdIkpXVCJ9.eyJuYmYiOjE0ODM1NzM4MzUsInZlcnNpb24iOjEsInN1YiI6IjRiYzg4OTJhMDE3YTNjMGY5MjAwMDAwMiIsImF1ZCI6IlNmODc4NTdiZTIwOWQzNTE5ODMzYjMwMGExM2QwZTEyIiwic2NvcGVzIjpbImFwaV9yZWFkIiwiYXBpX3dyaXRlIl0sImp0aSI6Ijg4In0.b76OiEs10gdp9oNOoGpBJ94nO9Zi17Y7SvAXJQW8nH2",
//"success": true,
//"status_message": "Success.",
//"status_code": 1
//
//"account_id": "4bc8892a017a3c0z92001001",
//"access_token": "eyJhbGciOiJIUzI1NiIsInR5cCIdIkpXVCJ9.eyJuYmYiOjE0ODM1NzM4MzUsInZlcnNpb24iOjEsInN1YiI6IjRiYzg4OTJhMDE3YTNjMGY5MjAwMDAwMiIsImF1ZCI6IlNmODc4NTdiZTIwOWQzNTE5ODMzYjMwMGExM2QwZTEyIiwic2NvcGVzIjpbImFwaV9yZWFkIiwiYXBpX3dyaXRlIl0sImp0aSI6Ijg4In0.b76OiEs10gdp9oNOoGpBJ94nO9Zi17Y7SvAXJQW8nH2",
//"success": true,
//"status_message": "Success.",
//"status_code": 1
