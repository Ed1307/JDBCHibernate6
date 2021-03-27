package ex_002_select_where;




import ex_002_select_where.entity.Author;

import java.util.List;

/**
 * Created by Asus on 01.11.2017.
 */
public class Main {



    public static void main(String[] args) {
        AuthorHelper ah = new AuthorHelper();

//        Author author = new Author();
//
//        author.setName("Michael");
//        author.setLastName("Lermontov");
//
//        ah.addAuthor(author);

        List<Author> authorList = ah.getAuthorListLike( "name", "ara");

        for (Author author : authorList) {
            System.out.println(author.getId() + " " + author.getName() + " " + author.getLastName());
        }


    }

}
