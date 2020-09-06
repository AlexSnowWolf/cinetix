package alex.guerra.data.source

interface LocationDataSource {
    suspend fun findLastRegion(): String?
}
