public class BinarySearchTreeNode<T extends Comparable<T>> {
	private T data;
	private BinarySearchTreeNode<T> left;
	private BinarySearchTreeNode<T> right;

	public BinarySearchTreeNode(T data) {
		this.data = data;
	}

	public boolean add(T data) {
		if (data == null) {
			return false;
		}
		int result = data.compareTo(this.data);
		if (result < 0) {
			if (left == null) {
				left = new BinarySearchTreeNode<T>(data);
				return true;
			} else {
				return left.add(data);
			}
		}
		if (result > 0) {
			if (right == null) {
				right = new BinarySearchTreeNode<T>(data);
				return true;
			} else {
				return right.add(data);
			}
		}
		return false;
	}

	/**
	 * Kollar om det angivna elementet finns i trädet. Returnerar true om data.compareTo(t) == 0,
	 * där data är det angivna elementet och t är ett element i trädet. 
	 * 
	 * @param data elementet vars närvaro ska kollas.
	 * @return true om det angivna elementet finns i trädet.
	 */
	public boolean contains(T data) {
		if (data == null) {
			return false;
		}
		int result = data.compareTo(this.data);
		if (result == 0) {
			return true;
		}
		if (result < 0 && left != null) {
			return left.contains(data);
		}
		if (result > 0 && right != null) {
			return right.contains(data);
		}
		return false;
	}

	public int size() {
		int leftSize = 0;
		int rightSize = 0;
		if (left != null) {
			leftSize = left.size();
		}
		if (right != null) {
			rightSize = right.size();
		}
		return leftSize + rightSize + 1;
	}

	public int depth() {
		int leftDepth = 0;
		int rightDepth = 0;
		if (left != null) {
			leftDepth = left.depth() + 1;
		}
		if (right != null) {
			rightDepth = right.depth() + 1;
		}
		if (leftDepth > rightDepth) {
			return leftDepth;
		} else {
			return rightDepth;
		}
	}

	public BinarySearchTreeNode<T> remove(T data) {
		int result = data.compareTo(this.data);
		if (result < 0) {
			if (left != null) {
				left = left.remove(data);
			}
		}
		if (result > 0) {
			if (right != null) {
				right = right.remove(data);
			}
		}
		if (result == 0) {
			 BinarySearchTreeNode<T> temp = this;
			if (left == null && right == null) {
				return null;
			}
			if (left != null && right == null) {
				return temp = temp.left;
			}
			if (left == null && right != null) {
				return temp = temp.right;
			}
			if (left != null && right != null) {
				this.data = right.findMin();
				right = right.remove(this.data);
			}
		}
		return this;
	}
	
	private T findMin() {
		if (left == null) {
			return data;
		} else {
			return left.findMin();
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		toString(sb);
		return sb.toString();
	}

	private void toString(StringBuilder sb) {
		if (left != null) {
			left.toString(sb);
			sb.append(", ");
		}
		sb.append(data);
		
		if (right != null) {
			sb.append(", ");
			right.toString(sb);
		}
	}
}