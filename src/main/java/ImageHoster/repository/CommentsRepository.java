package ImageHoster.repository;

import ImageHoster.model.Comments;
import ImageHoster.model.Image;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

//The annotation is a special type of @Component annotation which describes that the class defines a data repository
@Repository
public class CommentsRepository {

    //Get an instance of EntityManagerFactory from persistence unit with name as 'imageHoster'
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;


    //The method receives the Comment object to be persisted in the database
    //Creates an instance of EntityManager
    //Starts a transaction
    //The transaction is committed if it is successful
    //The transaction is rolled back in case of unsuccessful transaction
    public Comments addComment(Comments comment) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(comment);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return comment;
    }

    //The method creates an instance of EntityManager
    //Executes JPQL query to fetch all the images from the database
    //Returns the list of all the comments for specific image fetched from the database
    public List<Comments> getAllCommentsByImage(int imageId) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Comments> query = em.createQuery("SELECT i from Comments i where i.image.id=:image_id", Comments.class).setParameter("image_id", imageId);
        List<Comments> resultList = query.getResultList();

        return resultList;
    }


}
