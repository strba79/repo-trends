package rs.strba.repo.data.repository.datasource

import rs.strba.repo.data.model.Item
import rs.strba.repo.data.repository.repoIMPL.RepoRemoteDataSourceIMPL

interface RepoRemoteDataSource {
    suspend fun getReposTest(): RepoRemoteDataSourceIMPL.Result
    suspend fun getRepos(): List<Item>
}