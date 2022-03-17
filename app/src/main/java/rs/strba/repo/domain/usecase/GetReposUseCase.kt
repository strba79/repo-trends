package rs.strba.repo.domain.usecase

import rs.strba.repo.data.model.Item
import rs.strba.repo.domain.repository.RepoRepository

class GetReposUseCase(private val repoRepository: RepoRepository) {
    suspend fun execute():List<Item>?=repoRepository.getRepos()
}