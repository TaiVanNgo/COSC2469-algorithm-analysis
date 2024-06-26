package W4;

public class BinaryTreeNode<T extends Comparable<T>> {
    public T data = null;
    public BinaryTreeNode<T> parent = null;
    public BinaryTreeNode<T> left = null;
    public BinaryTreeNode<T> right = null;
    int height;
  
    public BinaryTreeNode(BinaryTreeNode<T> parent, T data) {
      this.parent = parent;
      this.data = data;
      height = 0;
    }
  
    public int getHeight() {
      return height;
    }
  
    // update and return the updated height
    public int updateHeight() {
      int leftHeight = 0;
      if (left != null) {
        leftHeight = left.getHeight();
      }
      int rightHeight = 0;
      if (right != null) {
        rightHeight = right.getHeight();
      }
      height = Math.max(leftHeight, rightHeight) + 1;
      return height;
    }
  
    // get balance factor, to check if tree rotation is needed
    public int getBalanceFactor() {
      int leftHeight = 0;
      if (left != null) {
        leftHeight = left.getHeight();
      }
      int rightHeight = 0;
      if (right != null) {
        rightHeight = right.getHeight();
      }
      return rightHeight - leftHeight;
    }

    // public BinaryTreeNode<T> getRight(){
    //   return this.right;
    // }

    // public BinaryTreeNode<T> getLeft(){
    //   return this.left;
    // }

    // public T getData(){
    //   return data;
    // }
  }
  