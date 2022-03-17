package rs.strba.repo.domain.repository

import rs.strba.repo.data.model.Item

interface RepoRepository {
    suspend fun getRepos():List<Item>?
}