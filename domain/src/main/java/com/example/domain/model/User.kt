package com.example.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

object User {
    class RQ(var value: String)

    class RS(var total_count: Int, var incomplete_results: Boolean, var items: Array<Item>)

    @Entity(tableName = "user")
    data class Item(
        var login: String,
        @PrimaryKey var id: Int,
        var node_id: String,
        var avatar_url: String,
        var gravatar_id: String,
        var url: String,
        var html_url: String,
        var followers_url: String,
        var subscriptions_url: String,
        var organizations_url: String,
        var repos_url: String,
        var received_events_url: String,
        var type: String,
        var score: Int,
        var following_url: String,
        var gists_url: String,
        var starred_url: String,
        var events_url: String,
        var site_admin: Boolean,
        var favorites: Boolean
    ) {
        companion object {
            val DEFAULT = Item(
                "", 0, "", "", "", "", "", "", "", "", "", "", "", 0, "", "", "", "", false, false
            )
        }
    }
}
