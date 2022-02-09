package controler;

import service.FollowService;
import service.LikeService;
import service.PostService;
import service.UserService;

import java.util.Scanner;

public class Controller {

    private static Scanner sc = new Scanner(System.in);

    private static UserService userService = new UserService();
    private static PostService postService = new PostService();
    private static LikeService likeService = new LikeService();
    private static FollowService followService = new FollowService();

    public static void main(String[] args) {

        while (true) {
            System.out.println("Alegeti flow-ul(users/posts/likes/follows):");
            String flow = sc.nextLine();
            switch (flow) {
                case "users":
                    startUserFlow();
                    break;
                case "posts":
                    startPostFlow();
                    break;
                case "likes":
                    startLikesFlow();
                    break;
                case "follows":
                    startFollowFlow();
                    break;
                default:
                    System.out.println("Not implemented yet.");
            }
        }
    }

    public static void startUserFlow() {
        System.out.println("Alegeti operatia (Introduceti: CREATE, READ_ALL, READ_BY_ID, UPDATE, DELETE)");
        String operatie = sc.nextLine();
        switch (operatie) {
            case "CREATE":
                userService.createUser();
                break;
            case "READ_ALL":
                userService.readAll();
                break;
            case "READ_BY_ID":
                userService.readByID();
                break;
            case "UPDATE":
                userService.update();
                break;
            case "DELETE":
                userService.delete();
                break;
            default:
                System.out.println("Nu ai introdus nicio operatie pentru flow-ul de users");
        }
    }

    public static void startPostFlow() {
        System.out.println("Alegeti operatia pentru flow-ul de postari (Introduceti: CREATE, READ_ALL, READ_BY_ID, UPDATE, DELETE_All, DELETE_BY_ID)");
        String operatie = sc.nextLine();
        switch (operatie) {
            case "CREATE":
                postService.createPost();
                break;
            case "READ_ALL":
                postService.readAll();
                break;
            case "READ_BY_ID":
                postService.readByID();
                break;
            case "UPDATE":
                postService.updatePost();
                break;
            case "DELETE_ALL":
                postService.deleteAllUserPost();
                break;
            case "DELETE_BY_ID":
                postService.deletePostById();
                break;
            default:
                System.out.println("Nu ai introdus nicio operatie valabila pentru flow-ul de posts");
        }
    }

    public static void startLikesFlow() {
        System.out.println("Alegeti operatia pentru flow-ul de like-uri (CREATE / DELETE)");
        String operatie = sc.nextLine();
        switch (operatie) {
            case "CREATE":
                likeService.createLike();
                break;
            case "DELETE":
                likeService.deleteLike();
                break;
            default:
                System.out.println("Nu ai introdus nicio operatie valabila pentru flow-ul de likes");
        }
    }

    public static void startFollowFlow() {
        System.out.println("Alegeti operatia pentru flow-ul de follow (CREATE / DELETE)");
        String operatie = sc.nextLine();
        switch (operatie) {
            case "CREATE":
                followService.follow();
                break;
            case "DELETE":
                followService.unfollow();
                break;
            default:
                System.out.println("Nu ai introdus nicio operatie valabila pentru flow-ul de follow");
        }
    }
}