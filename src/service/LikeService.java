package service;

import model.Follow;
import model.Like;
import model.Post;
import model.User;
import repository.LikeRepository;
import repository.PostRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class LikeService {

    Scanner scInt = new Scanner(System.in);
    Scanner sc = new Scanner(System.in);

    private static PostRepository postRepository = new PostRepository();
    private static UserRepository userRepository = new UserRepository();
    private static LikeRepository likeRepository = new LikeRepository();

    public void createLike() {

        System.out.println("Introduceti id-ul postarii:");
        int postId = scInt.nextInt();

        Post myPost = postRepository.readPostById(postId);
        if (myPost == null) {
            System.out.println("Postarea cu id-ul " + postId + " nu exista.");
        } else {
            System.out.println("Introduceti id-ul user-ului");
            int userId = scInt.nextInt();
            User myUser = userRepository.read_by_id(userId);
            if (myUser == null) {
                System.out.println("User-ul cu id-ul " + userId + " nu exista");
            } else {
                System.out.println("Introduceti parola");
            }
                String parolaIntrodusa = sc.nextLine();
                if (parolaIntrodusa.equals(myUser.getPassword())) {
                    likeRepository.createLike(postId, userId);
                }else{
                    System.out.println("Nu ai introdus o parola valida");
                }
            }
        }

    public void deleteLike() {

        System.out.println("Introduceti id-ul user-ului");
        int userId = scInt.nextInt();
        System.out.println("Introduceti parola");
        String parolaIntrodusa = sc.nextLine();
        User myUser = userRepository.read_by_id(userId);
        if (myUser == null || !parolaIntrodusa.equals(myUser.getPassword())) {
            System.out.println("User-ul sau parola gresita");
        } else {
            System.out.println("Introduceti id-ul postarii pentru care doriti sa stergeti like-ul:");
            int postId = scInt.nextInt();
            Post myPost = postRepository.readPostById(postId);
            if (myPost == null) {
                System.out.println("Postarea cu id-ul " + postId + " nu exista.");
            } else {
                Like myPossibleLike = likeRepository.readLike(userId, postId);
                if (myPossibleLike == null) {
                    System.out.println("Nu ai dat like la aceasta postare");
                } else {
                    likeRepository.deleteLikes(myPossibleLike.getId());
                }
            }
        }
    }
}






