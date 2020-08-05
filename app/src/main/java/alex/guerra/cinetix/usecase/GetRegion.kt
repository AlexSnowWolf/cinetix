package alex.guerra.cinetix.usecase

import alex.guerra.cinetix.repository.RegionRepository

class GetRegion(private val regionRepository: RegionRepository) {
    suspend operator fun invoke() = regionRepository.findLastRegion()
}
