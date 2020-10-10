import java.util.*;

public class HeightOfBinaryTree {

    class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
        public TreeNode(int value) {
            this.value = value;
        }
    }
    /*
    7
    1 2 3 4 5 6 7
     */
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        Arrays.sort(arr);
        HeightOfBinaryTree hbt = new HeightOfBinaryTree();
        TreeNode root = createBinarySearchTree(arr,0, arr.length-1,  hbt);
        printTreeNodes(root);
        getHeightOFBinaryTree(root, 7);
        System.out.println();
        levelOrderTraversal(root);
    }

    public static void printTreeNodes (TreeNode node) {
        if (node == null) {
            return;
        }
        printTreeNodes(node.left);
        System.out.print(node.value + " ");
        printTreeNodes(node.right);
    }

    public static TreeNode createBinarySearchTree(int[] arr, int start, int end, HeightOfBinaryTree hbt) {
        if (start > end) {
            return null;
        }
        int middleIndex = (start+end)/2;
        TreeNode node = hbt.new TreeNode(arr[middleIndex]);
        node.left = createBinarySearchTree(arr, start, middleIndex - 1, hbt);
        node.right = createBinarySearchTree(arr, middleIndex + 1, end, hbt);
        return node;
    }

    public static int getHeightOFBinaryTree (TreeNode root, int n) {
        Queue<TreeNode> track = new LinkedList<>();
        track.add(root);
        Set<TreeNode> taken = new HashSet<>();
        int[] distances = new int[n];
        Arrays.fill(distances, -1);
        distances[root.value-1] = 0;
        while (!track.isEmpty()) {
            TreeNode node = track.poll();
            if (!taken.contains(node)) {
                if (node.left != null) {
                    distances[node.left.value - 1] = distances[node.value - 1] + 1;
                    track.add(node.left);
                }
                if (node.right != null) {
                    distances[node.right.value - 1] = distances[node.value - 1] + 1;
                    track.add(node.right);
                }
                taken.add(node);
            }
        }
        System.out.print(Arrays.toString(distances));
        Arrays.sort(distances);
        return(distances[distances.length-1]);
    }

    public static void levelOrderTraversal(TreeNode node) {
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(node);
        while (!nodes.isEmpty()) {
            TreeNode current = nodes.poll();
            System.out.print(current.value + " ");
            if (current.left != null) {
                nodes.add(current.left);
            }
            if (current.right != null) {
                nodes.add(current.right);
            }
        }
    }
}
