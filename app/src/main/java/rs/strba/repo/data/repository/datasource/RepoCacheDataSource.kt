package rs.strba.repo.data.repository.datasource

import rs.strba.repo.data.model.Item

interface RepoCacheDataSource {
    suspend fun getReposFromCache():List<Item>
    suspend fun saveReposToCache(repos:List<Item>)
}