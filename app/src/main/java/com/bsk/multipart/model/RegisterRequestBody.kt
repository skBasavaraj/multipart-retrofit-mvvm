package com.bsk.multipart.model

class RegisterRequestBody {
    var userName: String? = null
    var emailId: String? = null
    var password: String? = null

    constructor(userName: String?, emailId: String?, password: String?) {
        this.userName = userName
        this.emailId = emailId
        this.password = password
    }
}