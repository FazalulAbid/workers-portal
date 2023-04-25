package com.fifty.workersportal.util

class ApiException(val code: Int) : Exception("HTTP Error: $code")
