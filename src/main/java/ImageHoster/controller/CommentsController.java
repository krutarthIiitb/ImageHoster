package ImageHoster.controller;

import ImageHoster.model.Comments;
import ImageHoster.model.Image;
import ImageHoster.model.Tag;
import ImageHoster.model.User;
import ImageHoster.service.CommentsService;
import ImageHoster.service.ImageService;
import ImageHoster.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private ImageService imageService;

    //add comment
    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String addComment(@RequestParam("comment") String comment, Model model, HttpSession session, @PathVariable int imageId, @PathVariable String imageTitle) {
        Comments com = new Comments();
        com.setCreatedDate(new java.sql.Date(new Date().getTime()).toLocalDate());
        com.setText(comment);
        com.setUser((User) session.getAttribute("loggeduser"));
        Image image = imageService.getImageById(imageId);
        com.setImage(new Image(imageId));
        commentsService.addComment(com);
        model.addAttribute("comments", commentsService.getAllCommentsByImage(imageId));
        model.addAttribute("image", image);
        if (image != null && image.getTags() != null) {
            model.addAttribute("tags", image.getTags());
        }

        return "redirect:images/image";
    }
}
