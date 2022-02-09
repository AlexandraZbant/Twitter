package service;

import model.Like;
import model.Post;
import model.User;
import repository.LikeRepository;
import repository.PostRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class PostService {

    private static Scanner sc = new Scanner(System.in);
    private static Scanner scInt = new Scanner(System.in);
    private static UserRepository userRepository = new UserRepository();
    private static PostRepository postRepository = new PostRepository();
    private static LikeRepository likeRepository = new LikeRepository();

    public void createPost() {
        System.out.println("Introduceti id-ul de utilizator");
        int idUser = scInt.nextInt();
        User myUser = userRepository.read_by_id(idUser);
        verifyUser(idUser, myUser);
        String parolaIntrodusa = sc.nextLine();

        if (parolaIntrodusa.equals(myUser.getPassword()) || parolaIntrodusa.equals(AdminCredentials.adminPass)) {
            System.out.println("Introduceti mesajul postarii");
            String message = sc.nextLine();
            System.out.println("Introduceti anul postarii");
            int year = scInt.nextInt();
            System.out.println("Introduceti luna postarii");
            int month = scInt.nextInt();
            System.out.println("Introduceti ziua postarii");
            int day = scInt.nextInt();
            System.out.println("Introduceti ora postarii");
            int hour = scInt.nextInt();
            System.out.println("Introduceti minutul postarii");
            int minute = scInt.nextInt();
            postRepository.createPost(message, idUser, year, month, day, hour, minute);
        } else {
            System.out.println("Nu ai introdus parola corecta");
        }
    }

    public void readAll() {
        System.out.println("Introduceti id-ul userului pentru care doriti sa cititi postarile:");
        int id = scInt.nextInt();
        User myUser = userRepository.read_by_id(id);

        if (myUser != null) {
            ArrayList<Post> posts = postRepository.readUserPost(id);
            for (Post post : posts) {
                ArrayList<Like> likes = likeRepository.readLikes(post.getId());
                post.setLikes(likes);
            }
            myUser.setPosts(posts);
            System.out.println(posts == null ? "Post-ul nu exista" : posts);
        } else {
            System.out.println("User-ul nu exista");
        }
    }

    public void readByID() {
        System.out.println("Introduceti id-ul userului pentru care doriti sa cititi postarea:");
        int userId = scInt.nextInt();
        User myUser = userRepository.read_by_id(userId);

        if (myUser != null) {
            System.out.println("Introduceti id-ul postarii dorite");
            int postId = scInt.nextInt();
            Post myPost = postRepository.readPostById(postId);
                ArrayList<Like> likes = likeRepository.readLikes(myPost.getId());
                myPost.setLikes(likes);
            System.out.println(myPost == null ? "Post-ul nu exista" : myPost);
        } else {
            System.out.println("User-ul nu exista");
        }
    }

    public void updatePost() {
        System.out.println("Introduceti id-ul userului pentru care doriti modificarea poostarii");
        int idUser = scInt.nextInt();
        User myUser = userRepository.read_by_id(idUser);
        verifyUser(idUser, myUser);
        String parolaIntrodusa = sc.nextLine();

        if (parolaIntrodusa.equals(myUser.getPassword()) || parolaIntrodusa.equals(AdminCredentials.adminPass)) {
            System.out.println("Introduceti id-ul postarii pe care doriti sa o modificati");
            int idPost = scInt.nextInt();
            Post myPost = postRepository.readPostById(idPost);

            if (myPost != null) {
                postRepository.updatePost(idPost);
            } else {
                System.out.println("Postarea cu id-ul " + idPost + " nu exista.");
            }
        } else {
            System.out.println("Nu ai introdus parola corecta");
        }
    }

    public void deleteAllUserPost() {
        System.out.println("Introduceti id-ul user-ului pentru care doriti sa stergeti postarea");
        int idUser = scInt.nextInt();
        User myUser = userRepository.read_by_id(idUser);
        verifyUser(idUser, myUser);
        String parolaIntrodusa = sc.nextLine();

        if (parolaIntrodusa.equals(myUser.getPassword()) || parolaIntrodusa.equals(AdminCredentials.adminPass)) {
            likeRepository.deleteLikes(idUser);
            postRepository.deleteAllUserPost(idUser);
        } else {
            System.out.println("Nu ai introdus parola corecta");
        }
    }

    public void deletePostById() {
        System.out.println("Introduceti id-ul user-ului pentru care doriti sa stergeti postarea");
        int idUser = scInt.nextInt();
        User myUser = userRepository.read_by_id(idUser);
        verifyUser(idUser, myUser);
        String parolaIntrodusa = sc.nextLine();

        if (parolaIntrodusa.equals(myUser.getPassword()) || parolaIntrodusa.equals(AdminCredentials.adminPass)) {
            System.out.println("Introduceti id-ul postarii pe care doriti sa o stergeti");
            int idPost = scInt.nextInt();
            Post myPost = postRepository.readPostById(idPost);
            if (myPost != null) {
                likeRepository.deleteLikes(idPost);
                postRepository.deletePostById(idPost);
            } else {
                System.out.println("Postarea cu id-ul " + idPost + " nu exista.");
            }
        } else {
            System.out.println("Nu ai introdus parola corecta");
        }
    }

    private void verifyUser(int idUser, User myUser) {
        if (myUser == null) {
            System.out.println("User-ul cu id " + idUser + " nu exista.");
        } else {
            System.out.println("Introduceti parola");
        }
    }
}


