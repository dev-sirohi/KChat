package com.example.kchat

// This class is only for storing user data and we could have done it with a data class but we are using firebase and it requires
// an empty constructor
class User {
    var name: String? = null
    var email: String? = null
    var uid: String? = null

    constructor() {}

    constructor(name: String?, email: String?, uid: String?) {
        this.name = name
        this.email = email
        this.uid = uid
    }
}