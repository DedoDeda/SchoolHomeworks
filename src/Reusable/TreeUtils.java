package Reusable;

import unit4.collectionsLib.BinNode;
import unit4.collectionsLib.BinTreeNode;

public class TreeUtils {

    public static void main(String[] args) {
        BinTreeNode<Integer> tree = new BinTreeNode<>(1);
        tree.setLeft(new BinTreeNode<>(2));
        tree.getLeft().setLeft(new BinTreeNode<>(4));
        tree.getLeft().setRight(new BinTreeNode<>(5));
        tree.getLeft().getRight().setLeft(new BinTreeNode<>(10));
        tree.setRight(new BinTreeNode<>(3));
        tree.getRight().setLeft(new BinTreeNode<>(6));
        tree.getRight().setRight(new BinTreeNode<>(7));
        tree.getRight().getRight().setLeft(new BinTreeNode<>(9));
        tree.getRight().getRight().setRight(new BinTreeNode<>(10));
        System.out.println(max(tree));
    }

    public static <T> void printInorder(BinTreeNode<T> root) {
        if (root == null) {
            return;
        }

        printInorder(root.getLeft());
        System.out.print(root + " ");
        printInorder(root.getRight());
    }

    public static <T> void printPreorder(BinTreeNode<T> tree) {
        if (tree == null) {
            return;
        }

        System.out.print(tree + " ");
        printPreorder(tree.getLeft());
        printPreorder(tree.getRight());
    }

    public static BinTreeNode<Integer> max(BinTreeNode<Integer> tree) {
        // The tree will be null only if the USER passed an empty tree.
        if (tree == null) {
            return null;
        }

        // If we don't have left, we get the max from right only, or just tree if we don't have right.
        if (!tree.hasLeft()) {
            if (!tree.hasRight()) {
                return tree;
            }
            return max2(max(tree.getRight()), tree);
        }

        // If we don't have right, we get the max from left only (we have left - we already checked).
        if (!tree.hasRight()) {
            return max2(max(tree.getLeft()), tree);
        }

        // We have both left and right - return the max of each side's max (and also compare with the root).
        return max3(max(tree.getLeft()), max(tree.getRight()), tree);
    }

    private static BinTreeNode<Integer> max2(BinTreeNode<Integer> n1, BinTreeNode<Integer> n2) {
        return n1.getValue() > n2.getValue() ? n1 : n2;
    }

    private static BinTreeNode<Integer> max3(BinTreeNode<Integer> n1, BinTreeNode<Integer> n2, BinTreeNode<Integer> n3) {
        if (n1.getValue() > n2.getValue()) {
            return max2(n1, n3);
        }

        return max2(n2, n3);
    }
}
