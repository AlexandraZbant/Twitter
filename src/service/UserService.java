package service;

import model.Follow;
import model.Like;
import model.Post;
import model.User;
import repository.FollowRepository;
import repository.LikeRepository;
import repository.PostRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class UserService {

    private Scanner sc = new Scanner(System.in);
    private Scanner scInt = new Scanner(System.in);
    private UserRepository userRepository = new UserRepository();
    private PostRepository postRepository = new PostRepository();
    private LikeRepository likeRepository = new LikeRepository();
    private FollowRepository followRepository = new FollowRepository();

    public void createUser() {
        System.out.println("Introduceti username-ul");
        String userName = sc.nextLine();
        System.out.println("Introduceti firstName-ul");
        String firstName = sc.nextLine();
        System.out.println("Introduceti lastName-ul");
        String lastName = sc.nextLine();
        System.out.println("Introduceti email-ul");
        String email = sc.nextLine();
        System.out.println("Introduceti parola");
        String password = sc.nextLine();
        System.out.println("Confirmati parola");
        String passConfirm = sc.nextLine();

        if (password.equals(passConfirm)) {
            userRepository.createUser(userName, firstName, lastName, email, password);
        } else {
            System.out.println("Parolele nu coincid");
        }
    }

    public void readAll() {
        ArrayList<User> allUsers = userRepository.readAll();

        for (User user : allUsers) {
            ArrayList<Post> userPost = postRepository.readUserPost(user.getId());
            user.setPosts(userPost);
            for (Post post : userPost) {
                ArrayList<Like> likes = likeRepository.readLikes(post.getId());
                post.setLikes(likes);
            }
            ArrayList<Follow> follows = followRepository.readUserFollowers(user.getId());
            user.setFollows(follows);
        }
        for (User user : allUsers) {
            System.out.println(user);
        }
    }

    public void readByID() {
        System.out.println("Introduceti id-ul userului dorit:");
        int userId = scInt.nextInt();
        User myUser = userRepository.read_by_id(userId);

        if (myUser != null) {
            ArrayList<Post> posts = postRepository.readUserPost(userId);
            myUser.setPosts(posts);
            for (Post post : posts) {
                ArrayList<Like> likes = likeRepository.readLikes(post.getId());
                post.setLikes(likes);
            }
            ArrayList<Follow> follows = followRepository.readUserFollowers(userId);
            myUser.setFollows(follows);
        }

        System.out.println(myUser == null ? "User-ul nu exista" : myUser);
    }


    public void update() {
        System.out.println("Introduceti id-ul userului pentru care doriti sa faceti modificari");
        int id = scInt.nextInt();
        User myUser = userRepository.read_by_id(id);
        verifyUser(id, myUser);
        String parolaIntrodusa = sc.nextLine();

        if (parolaIntrodusa.equals(myUser.getPassword()) || parolaIntrodusa.equals(AdminCredentials.adminPass)) {
            modifyUserColumns(id);
        } else {
            System.out.println("Nu ai introdus parola corecta");
        }
    }

    private void modifyUserColumns(int id) {
        boolean stillUpdate = true;

        while (stillUpdate) {
            System.out.println("Ce doriti sa modificati? (u_firstName, u_lastName, u_email, u_password) stop - stop flow");
            String coloana = sc.nextLine();
            switch (coloana) {
                case "u_firstName":
                    userRepository.modifyColumn(id, "u_firstName");
                    break;
                case "u_lastName":
                    userRepository.modifyColumn(id, "u_lastName");
                    break;
                case "u_email":
                    userRepository.modifyColumn(id, "u_email");
                    break;
                case "u_password":
                    userRepository.modifyColumn(id, "u_password");
                    break;
                case "stop":
                    stillUpdate = false;
                    break;
                default:
                    System.out.println("Nu ai introdus o coloana valida");
            }
        }
    }

    public void delete() {
        System.out.println("Ce user doriti sa stergeti? (Introduceti id-ul)");
        int id = scInt.nextInt();
        User myUser = userRepository.read_by_id(id);
        verifyUser(id, myUser);
        String parolaIntrodusa = sc.nextLine();

        if (parolaIntrodusa.equals(myUser.getPassword()) || parolaIntrodusa.equals(AdminCredentials.adminPass)) {
            postRepository.deleteAllUserPost(id);
            followRepository.unfollow(id);
            userRepository.delete(id);
        } else {
            System.out.println("Nu ai introdus parola corecta");
        }
    }

    private void verifyUser(int id, User myUser) {
        if (myUser == null) {
            System.out.println("User-ul cu id " + id + " nu exista.");
        } else {
            System.out.println("Introduceti parola");
        }
    }
}