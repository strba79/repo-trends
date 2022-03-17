package rs.strba.repo.data.repository

import rs.strba.repo.data.model.Item
import rs.strba.repo.data.model.Repo
import rs.strba.repo.data.repository.datasource.RepoCacheDataSource
import rs.strba.repo.data.repository.datasource.RepoRemoteDataSource
import rs.strba.repo.data.repository.repoIMPL.RepoRemoteDataSourceIMPL
import rs.strba.repo.domain.repository.RepoRepository

class RepoRepositoryIMPL(
    private val repoRemoteDataSource: RepoRemoteDataSource,
    private val repoCacheDataSource: RepoCacheDataSource
):RepoRepository {

    override suspend fun getRepos(): List<Item>? {
        return getReposFromCache()
    }

    private suspend fun getReposFromAPI(): List<Item> {
        return repoRemoteDataSource.getRepos()
    }

    private suspend fun getReposFromCache(): List<Item>? {
        lateinit var repoList: List<Item>
        try {
            repoList = repoCacheDataSource.getReposFromCache()
        } catch (exception: Exception) {
        }
        if (repoList.isNotEmpty()) {
            return repoList
        } else {
            repoList = getReposFromAPI()
            repoCacheDataSource.saveReposToCache(repoList)
        }
        return repoList
    }

}