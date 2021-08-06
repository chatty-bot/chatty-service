package chatty.util;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micronaut.spring.tx.annotation.Transactional;

@Transactional
public abstract class AbstractRepository<T> {

    @PersistenceContext
    private EntityManager entityManager;


    protected abstract Class<T> getEntityClass();


    protected JPAQueryFactory queryFactory() {
        return new JPAQueryFactory(this.entityManager);
    }


    protected JPAQuery<T> query() {
        return new JPAQuery(entityManager);
    }


    public T persist(T entity, boolean useFlush) {
        this.entityManager.persist(entity);
        if (useFlush) {
            this.entityManager.flush();
        }

        return entity;
    }


    public T persist(T entity) {
        return this.persist(entity, false);
    }


    public List<T> findAll() {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(this.getEntityClass());
        query.from(this.getEntityClass());
        return this.entityManager.createQuery(query).getResultList();
    }


    public Optional<T> findById(int id) {
        return Optional.ofNullable(entityManager.find(this.getEntityClass(), id));
    }


    public T merge(T entity) {
        return this.merge(entity, false);
    }


    public T merge(T entity, boolean useFlush) {
        entityManager.merge(entity);

        if (useFlush) {
            this.entityManager.flush();
        }

        return entity;
    }


    public EntityManager getEntityManager() {
        return this.entityManager;
    }


}
