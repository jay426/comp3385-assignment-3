// I would like to note that I used info from tutorialspoint.com and chatgbt to help me understand the code and how to implement it.
import java.util.Scanner;

class PrinterJobPriorityQueue {
    // Node class for the binary search tree
    static class Node {
        int pages;
        Node left, right;

        // Constructor to create a new node
        public Node(int pages) {
            this.pages = pages;
            left = right = null;
        }
    }

    // Root of the priority queue (BST)
    Node root;

    // Constructor
    public PrinterJobPriorityQueue() {
        root = null;
    }

    // Method to insert a print job with a given number of pages
    public Node insert(Node root, int pages) {
        if (root == null) {
            root = new Node(pages);
            return root;
        }

        if (pages > root.pages) {
            root.left = insert(root.left, pages);
        } else if (pages < root.pages) {
            root.right = insert(root.right, pages);
        }

        return root;
    }

    // Method to remove the job with the greatest number of pages (root)
    public Node removeMax(Node root) {
        if (root == null) {
            return null;
        }

        // If the left child exists, go down to the left subtree to find the job
        if (root.left != null) {
            root = root.left;
        } else {
            return root.right; // If no left child, the right child becomes the new root
        }

        return root;
    }

    // Method to remove a specific job by its number of pages
    public Node removeJob(Node root, int pages) {
        if (root == null) {
            return root;
        }

        // Recursively find the job to remove
        if (pages < root.pages) {
            root.right = removeJob(root.right, pages);
        } else if (pages > root.pages) {
            root.left = removeJob(root.left, pages);
        } else {
            // Job found, remove it
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Job has two children, replace with the smallest node in the right subtree
            root.pages = minValue(root.right);
            root.right = removeJob(root.right, root.pages);
        }
        return root;
    }

    // Find the minimum value node in a subtree
    public int minValue(Node root) {
        int minValue = root.pages;
        while (root.left != null) {
            minValue = root.left.pages;
            root = root.left;
        }
        return minValue;
    }

    // In-order traversal to print the queue
    public void inOrderTraversal(Node root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.print(root.pages + " ");
            inOrderTraversal(root.right);
        }
    }

    // Wrapper methods to interact with the BST
    public void insertJob(int pages) {
        root = insert(root, pages);
    }

    public void removeNextJob() {
        root = removeMax(root);
    }

    public void cancelJob(int pages) {
        root = removeJob(root, pages);
    }

    public void printQueue() {
        System.out.print("Current print queue: ");
        inOrderTraversal(root);
        System.out.println();
    }
}

public class PrinterJobSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PrinterJobPriorityQueue queue = new PrinterJobPriorityQueue();
        int choice;

        while (true) {
            // Show menu options
            System.out.println("Select an operation:");
            System.out.println("1. Submit a print job");
            System.out.println("2. Send the next job to print");
            System.out.println("3. Cancel a print job");
            System.out.println("4. Print current queue (In-order traversal)");
            System.out.println("5. Exit");
            System.out.print("Your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1: // Submit a print job
                    System.out.print("Enter the number of pages for the job: ");
                    int pagesToSubmit = scanner.nextInt();
                    queue.insertJob(pagesToSubmit);
                    queue.printQueue();
                    break;

                case 2: // Send the next job to print
                    queue.removeNextJob();
                    queue.printQueue();
                    break;

                case 3: // Cancel a print job
                    System.out.print("Enter the number of pages of the job to cancel: ");
                    int pagesToCancel = scanner.nextInt();
                    queue.cancelJob(pagesToCancel);
                    queue.printQueue();
                    break;

                case 4: // Print the current queue
                    queue.printQueue();
                    break;

                case 5: // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
