package rs.strba.repo.data.repository.datasource

import rs.strba.repo.data.model.Item

interface RepoRemoteDataSource {
    suspend fun getRepos(): List<Item>
}