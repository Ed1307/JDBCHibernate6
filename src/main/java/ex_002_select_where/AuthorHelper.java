package ex_002_select_where;


import ex_002_select_where.entity.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by Asus on 01.11.2017.
 */
public class AuthorHelper {

    private SessionFactory sessionFactory;

    public AuthorHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Author> getAuthorListLike(String likeField, String expression){
        // открыть сессию - для манипуляции с персист. объектами
        Session session = sessionFactory.openSession();


        // этап подготовки запроса

        // объект-конструктор запросов для Criteria API
        CriteriaBuilder cb = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated

        CriteriaQuery cq = cb.createQuery(Author.class);

        Root<Author> root = cq.from(Author.class);// первостепенный, корневой entity (в sql запросе - from)

        Selection[] selections = {root.get("id"), root.get("lastName")};


        cq.select(cb.construct(Author.class, selections))
                .where(cb.like(root.<String>get(likeField), "%" + expression + "%"));


         //этап выполнения запроса
        Query query = session.createQuery(cq);


        List<Author> authorList = query.getResultList();

        session.close();

        return authorList;
    }

    public Author getAuthorById(long id) {
        Session session = sessionFactory.openSession();
        Author author = session.get(Author.class, id); // получение объекта по id
        session.close();
        return author;
    }

    public Author addAuthor(Author author){

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.save(author); // сгенерит ID и вставит в объект

        session.getTransaction().commit();

        session.close();


        return author;

    }

//    public void lastNameUpdate(){
//        Session session = sessionFactory.openSession();
//
//        session.beginTransaction();
//
//        CriteriaBuilder cb = session.getCriteriaBuilder();
//
//        CriteriaUpdate<Author> update = cb.createCriteriaUpdate(Author.class);
//
//        Root<ex_002_select_where.entity.Author> root = update.from(ex_002_select_where.entity.Author.class);
//
//        update.set("name", "1");
//        update.where(cb.greaterThan(root.get("last_name"));
//
//
//        //этап выполнения запроса
//        Query query = session.createQuery(update);
//        query.executeUpdate();
//
//        session.getTransaction().commit();
//
//        session.close();
//    }
}
