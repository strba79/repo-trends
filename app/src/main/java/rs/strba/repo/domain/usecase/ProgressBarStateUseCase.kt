package rs.strba.repo.domain.usecase

import rs.strba.repo.domain.repository.ProgressBarRepository

class ProgressBarStateUseCase(private val progressBarRepository: ProgressBarRepository) {
    fun hideProgressBar()=progressBarRepository.hideProgressBar()
    fun showProgressBar()=progressBarRepository.showProgressbar()

}