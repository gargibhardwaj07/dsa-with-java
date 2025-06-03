class MinStack {
    Stack<Long> s;
     Long minval;

    public MinStack() {
        s = new Stack<>();
        minval = Long.MAX_VALUE;
    }

 public void push(int val) {
    Long lval = Long.valueOf(val);

        if (s.isEmpty()) {
            s.push(lval);
            minval = lval;
        } else if (lval < minval) {
            s.push(2 * lval - minval);
            minval = lval;
        } else {
            s.push(lval);
        }
    }

    public void pop() {
        if (s.isEmpty()) return;

        long top = s.pop();
        if (top < minval) {
            minval = 2 * minval - top;
        }
    }

    public int top() {
        long top = s.peek();
        if (top < minval) {
            return minval.intValue();
        } else {
           return (int) top;
        }
    }

    public int getMin() {
        return minval.intValue();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */