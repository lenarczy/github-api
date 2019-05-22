package com.rale.githubapi.repositories

data class RepositoryModel(var name: String, var language: String, var description: String)

fun fromEntityToModel(entity: RepositoryEntity): RepositoryModel {
    with(entity) {
        return RepositoryModel(name, language?:"There is no language specified", description?:"There is no description specified")
    }
}

fun fromEntitiesToModels(entities: List<RepositoryEntity>) = entities.map { fromEntityToModel(it) }