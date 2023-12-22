package com.lec.spring.repository;

import com.lec.spring.domain.Comment;
import com.lec.spring.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentRepositoryTest {
    @Autowired
    private SqlSession sqlSession;

    @Test
    void test1(){
        CommentRepository commentRepository = sqlSession.getMapper(CommentRepository.class);
        UserRepository userRepository = sqlSession.getMapper(UserRepository.class);

        List<Comment> list = commentRepository.findByPost(1L);
        list.forEach(System.out::println);


        User user = userRepository.findById(1L);
        Comment comment = Comment.builder()
                .content("댓글 또 작성")
                .post_id(1L)
                .user(user)
                .build();

        System.out.println("Comment 작성전: " + comment);
        commentRepository.save(comment);
        System.out.println("Comment 작성후: " + comment);

        commentRepository.findByPost(1L).forEach(System.out::println);

        commentRepository.deleteById(comment.getId());
        System.out.println("Comment 삭제후:");
        commentRepository.findByPost(1L).forEach(System.out::println);

    }
}

























