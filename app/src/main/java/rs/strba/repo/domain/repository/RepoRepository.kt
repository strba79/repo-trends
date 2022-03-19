package rs.strba.repo.domain.repository

import rs.strba.repo.data.model.Item
import rs.strba.repo.data.repository.repoIMPL.RepoRemoteDataSourceIMPL

interface RepoRepository {
    suspend fun getRepos():List<Item>
    suspend fun getReposTest(): RepoRemoteDataSourceIMPL.Result
}