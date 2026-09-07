/**
 * Node class for doubly linked list implementation
 */
class Node {
    public int val;
    public Node prev;
    public Node next;

    public Node() {
    }

    public Node(int val) {
        this.val = val;
    }
}

/**
 * Doubly linked list implementation with sentinel nodes
 * Used as the underlying stack structure
 */
class DoubleLinkedList {
    private final Node head;  // Sentinel head node
    private final Node tail;  // Sentinel tail node

    public DoubleLinkedList() {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    /**
     * Append a new node with given value to the end of the list (before tail)
     * @param val Value to append
     * @return The newly created node
     */
    public Node append(int val) {
        Node node = new Node(val);
        node.next = tail;
        node.prev = tail.prev;
        tail.prev = node;
        node.prev.next = node;
        return node;
    }

    /**
     * Remove a node from the list
     * @param node Node to remove
     * @return The removed node
     */
    public static Node remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        return node;
    }

    /**
     * Remove and return the last node (stack pop operation)
     * @return The removed node
     */
    public Node pop() {
        return remove(tail.prev);
    }

    /**
     * Get the value of the last node without removing it (stack peek operation)
     * @return Value of the last node
     */
    public int peek() {
        return tail.prev.val;
    }
}

/**
 * MaxStack implementation supporting O(log n) max operations
 * Uses a doubly linked list for stack operations and a TreeMap for tracking maximum values
 */
class MaxStack {
    private DoubleLinkedList stack;                    // Doubly linked list acting as stack
    private TreeMap<Integer, List<Node>> valueToNodes; // TreeMap mapping values to their nodes

    public MaxStack() {
        stack = new DoubleLinkedList();
        valueToNodes = new TreeMap<>();
    }

    /**
     * Push element x onto stack
     * @param x Element to push
     */
    public void push(int x) {
        // Add node to the stack
        Node node = stack.append(x);
        // Add node reference to TreeMap for O(log n) max operations
        valueToNodes.computeIfAbsent(x, k -> new ArrayList<>()).add(node);
    }

    /**
     * Remove and return the element on top of the stack
     * @return The top element
     */
    public int pop() {
        // Remove node from stack
        Node node = stack.pop();
        // Remove node reference from TreeMap
        List<Node> nodes = valueToNodes.get(node.val);
        int value = nodes.remove(nodes.size() - 1).val;
        // Clean up empty list from TreeMap
        if (nodes.isEmpty()) {
            valueToNodes.remove(node.val);
        }
        return value;
    }

    /**
     * Get the element on the top of the stack without removing it
     * @return The top element
     */
    public int top() {
        return stack.peek();
    }

    /**
     * Retrieve the maximum element in the stack without removing it
     * @return The maximum element
     */
    public int peekMax() {
        // TreeMap's lastKey() gives us the maximum value in O(log n)
        return valueToNodes.lastKey();
    }

    /**
     * Remove and return the maximum element in the stack
     * @return The maximum element
     */
    public int popMax() {
        // Get the maximum value
        int maxValue = peekMax();
        // Get list of nodes with this value
        List<Node> nodes = valueToNodes.get(maxValue);
        // Remove the most recent node with max value (last in the list)
        Node node = nodes.remove(nodes.size() - 1);
        // Clean up empty list from TreeMap
        if (nodes.isEmpty()) {
            valueToNodes.remove(maxValue);
        }
        // Remove the node from the doubly linked list
        DoubleLinkedList.remove(node);
        return maxValue;
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */
