package tk.anotherm4.webpress.service;

import tk.anotherm4.webpress.repository.PostRepository;
import tk.anotherm4.webpress.domain.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 主页和管理的增删改查
 */
@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

//    public Page<Posts> getPostPage(Pageable pageable) {
//        return postRepository.findAll(pageable);
//    }

    public List<Posts> getPostList() {
        return postRepository.findAll();
    }

    public void save(Posts posts) {
        postRepository.save(posts);
    }

    public void edit(Posts posts) {
        postRepository.saveAndFlush(posts);
    }

    public void deleteByPid(Long pid) {
        postRepository.deleteByPid(pid);
    }

    public Posts findByPid(Long pid) {
        return postRepository.findByPid(pid);
    }

}
