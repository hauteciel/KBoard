package com.lec.spring.controller;

import com.lec.spring.domain.QryCommentList;
import com.lec.spring.domain.QryResult;
import com.lec.spring.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController   // 데이터를 response <= @Contoller + @ResponseBody
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/test1")
    public QryCommentList test1(Long id){

        var list = commentService.list(1L);

        return list;
    }

    @GetMapping("/list")
    public QryCommentList list(Long id){
        return commentService.list(id);
    }

    @PostMapping("/write")
    public QryResult write(
            @RequestParam("post_id") Long postId,
            @RequestParam("user_id") Long userId,
            String content){
        return commentService.write(postId, userId, content);
    }

    @PostMapping("/delete")
    public QryResult delete(Long id){
        return commentService.delete(id);
    }


}













