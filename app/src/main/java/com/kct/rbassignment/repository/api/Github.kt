package com.kct.rbassignment.repository.api

import com.google.gson.annotations.SerializedName


/**
 * Created by krishan on 24/09/17.
 */

data class Repo(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("full_name") val fullName: String,
        @SerializedName("owner") val owner: Owner,
        @SerializedName("private") val private: Boolean,
        @SerializedName("html_url") val htmlUrl: String,
        @SerializedName("description") val description: String,
        @SerializedName("fork") val fork: Boolean,
        @SerializedName("url") val url: String,
        @SerializedName("homepage") val homepage: String,
        @SerializedName("size") val size: Int,
        @SerializedName("watchers_count") val watchersCount: Int,
        @SerializedName("language") val language: String,
        @SerializedName("has_issues") val hasIssues: Boolean,
        @SerializedName("watchers") val watchers: Int
)

data class Owner(
        @SerializedName("login") val login: String,
        @SerializedName("id") val id: Int,
        @SerializedName("avatar_url") val avatarUrl: String,
        @SerializedName("gravatar_id") val gravatarId: String,
        @SerializedName("url") val url: String,
        @SerializedName("html_url") val htmlUrl: String,
        @SerializedName("followers_url") val followersUrl: String,
        @SerializedName("following_url") val followingUrl: String,
        @SerializedName("gists_url") val gistsUrl: String,
        @SerializedName("starred_url") val starredUrl: String
)