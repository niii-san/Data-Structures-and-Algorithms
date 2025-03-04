package trees;

public class AVLTree {
    public static class Node {
        Node left;
        Node right;
        int data;
        int height;

        public Node(int data) {
            this.data = data;
            this.height = 1;
            this.left = this.right = null;
        }
    }

    public Node creatBST(Node root, int data) {
        if (root == null) {
            return new Node(data);
        }

        if (data < root.data) {
            root.left = creatBST(root.left, data);
        } else if (data > root.data) {
            root.right = creatBST(root.right, data);
        }
        // else{
        // return root;
        // }

        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));

        int balanceFactor = getBalanceFactor(root);

        if (balanceFactor > 1 && data < root.left.data) {
            // LL
            return rightRotation(root);
        }
        if (balanceFactor > 1 && data > root.left.data) {
            // LR
            root.left = leftRotation(root.left);
            return rightRotation(root);
        }
        if (balanceFactor < -1 && data > root.right.data) {
            // RR
            return leftRotation(root);
        }
        if (balanceFactor < -1 && data < root.right.data) {
            // RL
            root.right = rightRotation(root.right);
            return leftRotation(root);
        }

        return root;
    }

    int getHeight(Node root) {
        if (root == null) {
            return 0;
        }
        return root.height;
    }

    int getBalanceFactor(Node root) {
        if (root == null) {
            return 0;
        }
        return getHeight(root.left) - getHeight(root.right);
    }

    Node rightRotation(Node y) {
        Node x = y.left;
        Node t2 = x.right;

        x.right = y;
        y.left = t2;
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight((x.right)));
        return x;
    }

    Node leftRotation(Node x) {
        Node y = x.right;
        Node t2 = y.left;
        y.left = x;
        x.right = t2;
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        return y;
    }

}