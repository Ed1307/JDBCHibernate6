package ex_004_relations;


import ex_004_relations.entity.Author;
import ex_004_relations.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class BookHelper {

    private SessionFactory sessionFactory;

    public BookHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Book> getBookList() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated

        CriteriaQuery cq = cb.createQuery(Book.class);

        Root<Author> root = cq.from(Book.class);// первостепенный, корневой entity (в sql запросе - from)

        cq.select(root);

        //этап выполнения запроса
        Query query = session.createQuery(cq);

        List<Book> bookList = query.getResultList();

        session.close();

        return bookList;
    }

    public void DeleteById (int id){
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        // этап подготовки запроса

        // объект-конструктор запросов для Criteria API
        CriteriaBuilder cb = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated

        CriteriaDelete<Book> cd = cb.createCriteriaDelete(Book.class);

        Root<Book> root = cd.from(Book.class);// первостепенный, корневой entity (в sql запросе - from)

        cd.where(cb.like(root.<String>get("id"), String.valueOf(id)));


        //этап выполнения запроса
        Query query = session.createQuery(cd);
        query.executeUpdate();

        session.getTransaction().commit();

        session.close();

    }

    public void DeleteByAuthor (int authorId){
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        // этап подготовки запроса

        // объект-конструктор запросов для Criteria API
        CriteriaBuilder cb = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated

        CriteriaDelete<Book> cd = cb.createCriteriaDelete(Book.class);

        Root<Book> root = cd.from(Book.class);// первостепенный, корневой entity (в sql запросе - from)

        cd.where(cb.like(root.<String>get("author_id"), String.valueOf(authorId)));


        //этап выполнения запроса
        Query query = session.createQuery(cd);
        query.executeUpdate();

        session.getTransaction().commit();

        session.close();

    }

}
