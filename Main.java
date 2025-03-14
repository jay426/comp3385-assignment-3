// I would like to note that I used info from tutorialspoint.com and chatgbt to help me understand the code and how to implement it.
import java.util.Scanner;

class BinarySearchTree {
    // Tree node structure
    static class Node {
        int data;
        Node left, right;

        // Constructor
        public Node(int item) {
            data = item;
            left = right = null;
        }
    }

    // Root of the tree
    Node root;

    // Constructor
    public BinarySearchTree() {
        root = null;
    }

    // Method to insert a new node
    public Node insert(Node root, int data) {
        // If the tree is empty, return a new node
        if (root == null) {
            root = new Node(data);
            return root;
        }

        // Otherwise, recur down the tree
        if (data < root.data)
            root.left = insert(root.left, data);
        else if (data > root.data)
            root.right = insert(root.right, data);

        // Return the (unchanged) node pointer
        return root;
    }

    // Method to perform pre-order traversal
    public void preOrderTraversal(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
    }

    // Method to calculate the depth of the tree
    public int calculateDepth(Node root) {
        if (root == null) {
            return 0;
        } else {
            // Get the depth of the left and right subtrees
            int leftDepth = calculateDepth(root.left);
            int rightDepth = calculateDepth(root.right);

            // Return the greater depth plus 1
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }

    // Wrapper methods to interact with the BST
    public void insertValue(int data) {
        root = insert(root, data);
    }

    public void printPreOrder() {
        preOrderTraversal(root);
    }

    public int getTreeDepth() {
        return calculateDepth(root);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the number of elements from the user
        System.out.print("Enter the number of integers you want to insert: ");
        int n = scanner.nextInt();

        BinarySearchTree bst = new BinarySearchTree();

        // Insert values into the BST
        System.out.println("Enter " + n + " integers:");
        for (int i = 0; i < n; i++) {
            int value = scanner.nextInt();
            bst.insertValue(value);
        }

        // Print the pre-order traversal of the tree
        System.out.print("Pre-order traversal: ");
        bst.printPreOrder();
        System.out.println();

        // Print the depth of the tree
        System.out.println("Depth of the tree: " + bst.getTreeDepth());

        scanner.close();
    }
}
