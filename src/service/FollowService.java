package service;

import model.Follow;
import model.User;
import repository.FollowRepository;
import repository.PostRepository;
import repository.UserRepository;

import java.util.Scanner;

public class FollowService {

    Scanner scInt = new Scanner(System.in);
    Scanner sc = new Scanner(System.in);

    private static UserRepository userRepository = new UserRepository();
    private static PostRepository postRepository = new PostRepository();
    private static FollowRepository followRepository = new FollowRepository();

    public void follow() {
        System.out.println("Introduceti id-ul persoanei pe care doriti sa o urmariti:");
        int userId1 = scInt.nextInt();
        User user = userRepository.read_by_id(userId1);

        if (user == null) {
            System.out.println("User-ul cu id-ul " + userId1 + " nu exista.");
        } else {
            System.out.println("Introduceti id-ul dvs");
            int userId2 = scInt.nextInt();
            User myUser = userRepository.read_by_id(userId2);
            verifyUser(userId2, myUser);
            String parolaMea = sc.nextLine();
            if (parolaMea.equals(myUser.getPassword())) {
                Follow myfolow = followRepository.readByUserId(userId1);
                if (myfolow == null) {
                    followRepository.follow(userId1, userId2);
                } else {
                    System.out.println("Deja urmaresti acest user");
                }
            } else {
                System.out.println("Nu ai introdus o parola valida");
            }
        }
    }

    public void unfollow() {
        System.out.println("Introduceti id-ul persoanei pe care nu mai doriti sa o urmariti:");
        int userId1 = scInt.nextInt();
        User user = userRepository.read_by_id(userId1);

        if (user == null) {
            System.out.println("User-ul cu id-ul " + userId1 + " nu exista.");
        } else {
            System.out.println("Introduceti id-ul dvs");
            int userId2 = scInt.nextInt();
            User myUser = userRepository.read_by_id(userId2);
            verifyUser(userId2, myUser);
            String parolaIntrodusa = sc.nextLine();
            if (parolaIntrodusa.equals(myUser.getPassword())) {
                followRepository.unfollow(userId1);
            } else {
                System.out.println("Nu ai introdus o parola valida");
            }
        }
    }

    private void verifyUser(int userId2, User myUser) {
        if (myUser == null) {
            System.out.println("User-ul cu id-ul " + userId2 + " nu exista");
        } else {
            System.out.println("Introduceti parola dvs.");
        }
    }
}