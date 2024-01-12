package sbsdemo2

import org.springframework.data.repository.CrudRepository
import sbsdemo2.entity.Article
import sbsdemo2.entity.User

interface ArticleRepository : CrudRepository<Article, Long> {
    fun findBySlug(slug: String): Article?
    fun findAllByOrderByAddedAtDesc(): Iterable<Article>
}

interface UserRepository : CrudRepository<User, Long> {
    fun findByLogin(login: String): User?
}