package com.rale.githubapi.user

data class UserModel(var login: String, var name: String, var followers: String, var following: String, var avatar: String, var email: String)

fun fromEntity(entity: UserEntity): UserModel {
    with(entity) {
        val loginTmp = "LogIn: $login"
        val nameTmp = if (name == null) "No name provided" else "Username: $name"
        val followersTmp = "Followers: $followers"
        val followingTmp = "Following: $following"
        val emailTmp = if (email == null) "No email provided" else "Email: $email"
        return UserModel(loginTmp, nameTmp, followersTmp, followingTmp, avatar, emailTmp)
    }
}