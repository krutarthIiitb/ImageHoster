package ImageHoster.service;

import ImageHoster.model.Comments;
import ImageHoster.model.Image;
import ImageHoster.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsService {
    @Autowired
    private CommentsRepository commentRepository;

    //The method calls the addComment() method in the Repository and passes the comment to be persisted in the database
    public void addComment(Comments comment) {
        commentRepository.addComment(comment);
    }

    //Call the getAllImages() method in the Repository and obtain a List of all the images in the database
    public List<Comments> getAllCommentsByImage(int imageId) {
        return commentRepository.getAllCommentsByImage(imageId);
    }


}
