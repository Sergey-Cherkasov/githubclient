package br.svcdev.githubclient.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubRepo(
        @Expose val id: Int?,
        @Expose val name: String?,
        @Expose val description: String?,
        @Expose val forksCount: Int?
) : Parcelable
