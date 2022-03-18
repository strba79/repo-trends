package rs.strba.repo.data.repository.repoIMPL

import rs.strba.repo.data.model.Item
import rs.strba.repo.data.repository.datasource.RepoCacheDataSource

class RepoCacheDataSourceIMPL:RepoCacheDataSource {
    private var repoCacheList=ArrayList<Item>()
    override suspend fun getReposFromCache(): List<Item> {
        return repoCacheList
    }
    override suspend fun saveReposToCache(repos: List<Item>) {
        repoCacheList.clear()
        repoCacheList=ArrayList(repos)
    }
}